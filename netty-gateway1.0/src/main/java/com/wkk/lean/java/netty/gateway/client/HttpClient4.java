package com.wkk.lean.java.netty.gateway.client;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @Description 使用apache http client发送http请求
 * @Author Wangkunkun
 * @Date 2020/10/28 21:03
 */
public class HttpClient4 extends HttpClient {

    private static CloseableHttpClient httpClient;

    public HttpClient4(String serverUrl) {
        super(serverUrl);
        httpClient = HttpClientBuilder
                .create().setDefaultCookieStore(new BasicCookieStore()).build();
    }

    @Override
    public void sendGetRequest(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx) {
        FullHttpResponse httpResponse = null;
        HttpGet request = new HttpGet(serverUrl + fullHttpRequest.uri());
        try {
            fullHttpRequest.headers().forEach(header -> {
                request.setHeader(header.getKey(), header.getValue());
            });
            CloseableHttpResponse response = httpClient.execute(request);
            byte[] body = EntityUtils.toByteArray(response.getEntity());
            httpResponse = new DefaultFullHttpResponse(HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(body));
            for (Header allHeader : response.getAllHeaders()) {
                httpResponse.headers().set(allHeader.getName(), allHeader.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(fullHttpRequest != null) {
                if (!HttpUtil.isKeepAlive(fullHttpRequest)) {
                    ctx.write(httpResponse).addListener(ChannelFutureListener.CLOSE);
                } else {
                    //response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(httpResponse);
                }
            }
            ctx.flush();
        }
    }
}
