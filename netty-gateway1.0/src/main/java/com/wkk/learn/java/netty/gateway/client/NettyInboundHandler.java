package com.wkk.learn.java.netty.gateway.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import org.apache.http.protocol.HTTP;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @Description netty发送http请求处理
 * @Author Wangkunkun
 * @Date 2020/11/3 23:48
 */
public class NettyInboundHandler extends ChannelInboundHandlerAdapter {

    private FullHttpRequest fullHttpRequest;

    private ChannelHandlerContext requestCtx;

    public NettyInboundHandler(FullHttpRequest fullHttpRequest, ChannelHandlerContext requestCtx) {
        this.fullHttpRequest = fullHttpRequest;
        this.requestCtx = requestCtx;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //发送请求
        FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_0, HttpMethod.GET, fullHttpRequest.uri());
        request.headers().set(fullHttpRequest.headers());
        request.headers().add(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
        ctx.writeAndFlush(request);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            FullHttpResponse fullHttpResponse = (FullHttpResponse) msg;
            ByteBuf content = fullHttpResponse.content();
            String result = content.toString(CharsetUtil.UTF_8);
            System.out.println("response -> "+result);
            //响应请求
            FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(result.getBytes()));
            response.headers().set(fullHttpResponse.headers());
            requestCtx.writeAndFlush(response);
            ctx.close();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
