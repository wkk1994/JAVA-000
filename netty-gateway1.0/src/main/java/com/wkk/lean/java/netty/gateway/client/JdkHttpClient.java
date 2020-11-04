package com.wkk.lean.java.netty.gateway.client;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @Description 使用jdk http api发送get请求
 * @Author Wangkunkun
 * @Date 2020/11/3 22:50
 */
public class JdkHttpClient extends HttpClient{

    public JdkHttpClient(String serverUrl) {
        super(serverUrl);
    }

    /**
     * 发送get请求
     */
    public void sendGetRequest(final FullHttpRequest fullHttpRequest, final ChannelHandlerContext ctx) {
        FullHttpResponse fullHttpResponse = null;
        try {

            URL url = new URL(serverUrl + fullHttpRequest.uri());
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(25 * 1000);
            httpURLConnection.setReadTimeout(25 * 1000);
            Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
            byte[] body = null;
            InputStream inputStream = httpURLConnection.getInputStream();
            if (inputStream != null) {
                body = new byte[inputStream.available()];
                if(body.length > 0) {
                    inputStream.read(body);
                }
            }
            fullHttpResponse = new DefaultFullHttpResponse(HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(body));
            FullHttpResponse finalFullHttpResponse = fullHttpResponse;
            headerFields.forEach((key, value) -> {
                if(key != null) {
                    finalFullHttpResponse.headers().set(key, value);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fullHttpRequest != null) {
                if (!HttpUtil.isKeepAlive(fullHttpRequest)) {
                    ctx.write(fullHttpResponse).addListener(ChannelFutureListener.CLOSE);
                } else {
                    //response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(fullHttpResponse);
                }
            }
            ctx.flush();
        }
    }
}
