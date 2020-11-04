package com.wkk.lean.java.netty.gateway;

import com.wkk.lean.java.netty.gateway.inbound.NettyHttpServer;

/**
 * @Description 服务器启动类
 * @Author Wangkunkun
 * @Date 2020/11/2 22:55
 */
public class NettyGatewayApplication {

    public static void main(String[] args) {
        NettyHttpServer nettyHttpServer = new NettyHttpServer(8809, "http://127.0.0.1:8808");
        nettyHttpServer.run();
    }
}
