package io.kimmking.rpcfx.client.http;

import com.alibaba.fastjson.JSON;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @Description 使用netty发生请求
 * @Author Wangkunkun
 * @Date 2020/12/17 16:18
 */
public class NettyHttpClient implements RpcHttpClientAbstract{


    public NettyHttpClient() {

    }

    public RpcfxResponse post(RpcfxRequest req, String url) throws IOException {
        URI uri = null;
        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup bossEventLoopGroup = new NioEventLoopGroup(1);
        try {
            NettyClientHandler nettyClientHandler = new NettyClientHandler();
            bootstrap.group(bossEventLoopGroup).channel(NioSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO)).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ChannelPipeline pipeline = socketChannel.pipeline();
                    pipeline.addLast(new HttpResponseDecoder());
                    pipeline.addLast(new HttpRequestEncoder());
                    pipeline.addLast(new HttpObjectAggregator(512 * 1024));// 将多个消息转换成单一的FullHttpRequest或FullHttpResponse，原因：HTTP解码器在每个HTTP消息中会生成多个消息对象。
                                                                                            // (1) HttpRequest/HttpResponse; (2) HttpContent; (3) LastHttpContent;
                    pipeline.addLast(nettyClientHandler);
                }
            });
            uri = new URI(url);
            ChannelFuture channelFuture = bootstrap.connect(uri.getHost(), uri.getPort()).sync();
            String reqJson = JSON.toJSONString(req);
            System.out.println("req json: "+reqJson);
            byte[] bytes = reqJson.getBytes();
            ByteBuf byteBuf = Unpooled.directBuffer(bytes.length);
            byteBuf.writeBytes(bytes);
            DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST, "/", byteBuf);
            request.headers().set(HttpHeaderNames.HOST, uri.getHost());
            request.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            request.headers().set(HttpHeaderNames.CONTENT_LENGTH, request.content().readableBytes());
            request.headers().set(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON);
            ChannelPromise channelPromise = nettyClientHandler.sendMessage(request);
            channelPromise.await();
            channelFuture.channel().closeFuture();
            return JSON.parseObject(String.valueOf(nettyClientHandler.getData()), RpcfxResponse.class);
        } catch (URISyntaxException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
