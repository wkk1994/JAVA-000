package com.wkk.lean.java.netty.gateway.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @Description 使用netty实现发送http请求
 * @Author Wangkunkun
 * @Date 2020/11/3 23:32
 */
public class NettyHttpClient extends HttpClient {

    public NettyHttpClient(String serverUrl) {
        super(serverUrl);
    }

    @Override
    public void sendGetRequest(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx) {
        // 创建bossEventLoopGroup
        EventLoopGroup bossEventLoopGroup = new NioEventLoopGroup(1);
        // 创建启动工具类
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.option(ChannelOption.SO_BACKLOG, 128) //保持连接数量
                .option(ChannelOption.SO_REUSEADDR, true) // 地址重用
                .option(ChannelOption.SO_RCVBUF, 32 * 1024) // 接收缓冲区
                .option(ChannelOption.SO_SNDBUF, 32 * 1024)  //发送缓冲区
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(EpollChannelOption.SO_REUSEPORT, true); // 端口重用
        try {
            bootstrap.group(bossEventLoopGroup).channel(NioSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO)).handler(new ChannelInitializer<SocketChannel>() {

                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ChannelPipeline pipeline = socketChannel.pipeline();
                    pipeline.addLast(new HttpResponseDecoder());
                    pipeline.addLast(new HttpRequestEncoder());
                    pipeline.addLast(new HttpObjectAggregator(1024 * 1024));
                    pipeline.addLast(new NettyInboundHandler(fullHttpRequest, ctx));
                }
            });
            URI uri = new URI(serverUrl);
            ChannelFuture connect = bootstrap.connect(uri.getHost(), uri.getPort());
            connect.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture)
                        throws Exception {
                    if (channelFuture.isSuccess()) {
                        System.out.println("连接建立");
                    } else {
                        System.err.println("连接失败");
                        channelFuture.cause().printStackTrace();
                    }
                }
            });
            Channel channel = connect.sync().channel();
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } finally {
            bossEventLoopGroup.shutdownGracefully();
        }
    }
}
