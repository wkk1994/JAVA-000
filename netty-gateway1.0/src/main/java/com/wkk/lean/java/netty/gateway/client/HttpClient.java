package com.wkk.lean.java.netty.gateway.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @Description 发送客户端请求接口
 * @Author Wangkunkun
 * @Date 2020/11/3 23:25
 */
public abstract class HttpClient {

    protected String serverUrl;

    public HttpClient(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public abstract void sendGetRequest(final FullHttpRequest fullHttpRequest, final ChannelHandlerContext ctx);
}
