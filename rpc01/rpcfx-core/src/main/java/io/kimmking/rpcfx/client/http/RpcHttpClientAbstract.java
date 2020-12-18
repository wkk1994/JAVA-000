package io.kimmking.rpcfx.client.http;

import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;

import java.io.IOException;

/**
 * @Description
 * @Author Wangkunkun
 * @Date 2020/12/18 21:15
 */
public interface RpcHttpClientAbstract {



    RpcfxResponse post(RpcfxRequest req, String url) throws IOException;

}
