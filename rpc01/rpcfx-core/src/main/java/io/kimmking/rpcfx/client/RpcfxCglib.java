package io.kimmking.rpcfx.client;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.kimmking.rpcfx.client.http.NettyHttpClient;
import io.kimmking.rpcfx.client.http.RpcHttpClientAbstract;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Description cglib代理
 * @Author Wangkunkun
 * @Date 2020/12/17 11:17
 */
public final class RpcfxCglib {

    static {
        ParserConfig.getGlobalInstance().addAccept("io.kimmking");
    }

    public static Object create(final Class serviceClass, final String url) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(serviceClass);
        enhancer.setCallback(new CglibMethodInterceptor(serviceClass, url));
        //4.创建子类(代理对象)
        return enhancer.create();

    }

    public static class CglibMethodInterceptor implements MethodInterceptor {


        private final Class<?> serviceClass;

        private final String url;

        private RpcHttpClientAbstract client;

        public CglibMethodInterceptor(Class<?> serviceClass, String url) {
            this.serviceClass = serviceClass;
            this.url = url;
            this.client = new NettyHttpClient();
        }


        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            if (method.getDeclaringClass().equals(Object.class)) {
                return method.invoke(o, objects);
            }
            RpcfxRequest request = new RpcfxRequest();
            request.setServiceClass(serviceClass.getName());
            request.setMethod(method.getName());
            request.setParams(objects);

            //RpcfxResponse response = NettyHttpClient.post(request, url);
            RpcfxResponse response = client.post(request, url);

            // 这里判断response.status，处理异常
            // 考虑封装一个全局的RpcfxException

            return JSON.parse(response.getResult().toString());
        }
    }
}
