package com.wkk.learn.java.netty.gateway.inbound;

import com.wkk.learn.java.netty.gateway.client.HttpClient;
import com.wkk.learn.java.netty.gateway.client.HttpClient4;
import com.wkk.learn.java.netty.gateway.client.JdkHttpClient;
import com.wkk.learn.java.netty.gateway.client.NettyHttpClient;
import com.wkk.learn.java.netty.gateway.filter.FilterChain;
import com.wkk.learn.java.netty.gateway.filter.FilterConfig;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description netty服务配置类
 * @Author Wangkunkun
 * @Date 2020/11/2 22:55
 */
public class NettyHttpServer {

    private static Logger logger = LoggerFactory.getLogger(NettyHttpServer.class);

    private int port;

    private String backendUrl;

    public NettyHttpServer(int port, String backendUrl) {
        this.port = port;
        this.backendUrl = backendUrl;
    }

    public void run() {
        // 创建bossEventLoopGroup和workEventLoopGroup
        EventLoopGroup bossEventLoopGroup = new NioEventLoopGroup(1);
        EventLoopGroup workEventLoopGroup = new NioEventLoopGroup(16);
        // 创建启动工具类
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.option(ChannelOption.SO_BACKLOG, 128) //保持连接数量
                .option(ChannelOption.SO_REUSEADDR, true) // 地址重用
                .option(ChannelOption.SO_RCVBUF, 32 * 1024) // 接收缓冲区
                .option(ChannelOption.SO_SNDBUF, 32 * 1024)  //发送缓冲区
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(EpollChannelOption.SO_REUSEPORT, true) // 端口重用
                .childOption(ChannelOption.SO_KEEPALIVE, true);
        try {
            bootstrap.group(bossEventLoopGroup, workEventLoopGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO)).childHandler(new ChannelInitializer<SocketChannel>() {

                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ChannelPipeline pipeline = socketChannel.pipeline();
                    pipeline.addLast(new HttpServerCodec());
                    pipeline.addLast(new HttpObjectAggregator(1024 * 1024));
                    // 初始化inbound
                    pipeline.addLast(new HttpInboundHandler(nettyHttpClient(), filterChain()));
                }
            });
            Channel ch = bootstrap.bind(port).sync().channel();
            logger.info("开启netty http服务器，监听地址和端口为 http://127.0.0.1:" + port + '/');
            ch.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossEventLoopGroup.shutdownGracefully();
            workEventLoopGroup.shutdownGracefully();
        }
    }

    private HttpClient httpClient() {
        return new HttpClient4(backendUrl);
    }

    private HttpClient jdkHttpClient() {
        return new JdkHttpClient(backendUrl);
    }

    private HttpClient nettyHttpClient() {
        return new NettyHttpClient(backendUrl);
    }

    private FilterChain filterChain() {
        return FilterConfig.getFilterChain();
    }
}
