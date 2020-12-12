package com.wkk.learn.java.netty.gateway.inbound;

import com.wkk.learn.java.netty.gateway.client.HttpClient;
import com.wkk.learn.java.netty.gateway.filter.FilterChain;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description 入站处理器
 * @Author Wangkunkun
 * @Date 2020/11/2 23:11
 */
public class HttpInboundHandler extends ChannelInboundHandlerAdapter {

    private static Logger logger = LoggerFactory.getLogger(HttpInboundHandler.class);

    private HttpClient httpClient;

    private FilterChain filterChain;

    public HttpInboundHandler(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public HttpInboundHandler(HttpClient httpClient, FilterChain filterChain) {
        this.httpClient = httpClient;
        this.filterChain = filterChain;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
            // filter
            if(filterChain != null) {
                filterChain.doFilter(fullRequest, filterChain);
            }
            System.out.println(fullRequest.headers());
            logger.info("接收到请求：uri: {}, method : {}", fullRequest.uri(), fullRequest.method());
            httpClient.sendGetRequest(fullRequest, ctx);
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
