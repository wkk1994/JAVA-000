package io.kimmking.rpcfx.client.http;

import com.alibaba.fastjson.JSON;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author Wangkunkun
 * @Date 2020/12/18 21:15
 */
public class RpcHttpClient implements RpcHttpClientAbstract{

    public static final MediaType JSONTYPE = MediaType.get("application/json; charset=utf-8");

    private OkHttpClient client;

    public RpcHttpClient() {
        client = new OkHttpClient();
        OkHttpClient.Builder builder =  client.newBuilder();
        builder.connectionPool(new ConnectionPool(50, 1, TimeUnit.MINUTES));
    }

    public RpcfxResponse post(RpcfxRequest req, String url) throws IOException {
        String reqJson = JSON.toJSONString(req);
        System.out.println("req json: "+reqJson);

        // 1.可以复用client
        // 2.尝试使用httpclient或者netty client
        Request request = null;
        Response execute = null;
        try {
            request = new Request.Builder()
                    .url(url)
                    .post(RequestBody.create(JSONTYPE, reqJson))
                    .build();
            execute = client.newCall(request).execute();
            String respJson = execute.body().string();
            System.out.println("resp json: "+respJson);
            return JSON.parseObject(respJson, RpcfxResponse.class);
        } finally {
            execute.close();
        }
    }
}
