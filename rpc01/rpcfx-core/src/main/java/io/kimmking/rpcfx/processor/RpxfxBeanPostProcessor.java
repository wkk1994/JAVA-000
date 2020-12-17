package io.kimmking.rpcfx.processor;

import io.kimmking.rpcfx.ClassIdBean;
import io.kimmking.rpcfx.annotation.RpcfxService;
import io.kimmking.rpcfx.client.Rpcfx;
import io.kimmking.rpcfx.client.RpcfxCglib;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description 实现对 {@link RpcfxService} 注解属性的代理
 * @Author Wangkunkun
 * @Date 2020/12/16 21:38
 */
public class RpxfxBeanPostProcessor implements BeanPostProcessor {

    private static final Map<ClassIdBean, Object> RPXFX_BEAN_MAP = new ConcurrentHashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> aClass = bean.getClass();
        for (Field declaredField : aClass.getDeclaredFields()) {
            RpcfxService annotation = declaredField.getAnnotation(RpcfxService.class);
            if(annotation == null) {
                return bean;
            }
            if (!declaredField.getType().isInterface()) {
                return bean;
            }
            Class className = declaredField.getType();
            String url = annotation.url();
            ClassIdBean classIdBean = new ClassIdBean(url, className);
            Object object = getProxy(classIdBean);
            declaredField.setAccessible(true);
            try {
                declaredField.set(bean, object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return bean;
    }

    private Object getProxy(ClassIdBean classIdBean) {
        Object object = RPXFX_BEAN_MAP.get(classIdBean);
        if(object != null) {
            return object;
        }
        synchronized (this) {
            object = RPXFX_BEAN_MAP.get(classIdBean);
            if(object != null) {
                return object;
            }
            // 选择使用jdk动态代理还是cglib代理
            //object = Rpcfx.create(classIdBean.getInterfaceClass(), classIdBean.getUrl());
            object = RpcfxCglib.create(classIdBean.getInterfaceClass(), classIdBean.getUrl());
            RPXFX_BEAN_MAP.put(classIdBean, object);
        }
        return object;
    }
}
