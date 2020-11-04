# 学习笔记

## 作业一：实现简单的网关 gateway 1.0

请求经过网关后，在网关处通过HttpClient调用后端服务地址。

关键代码地址：[HttpClient4](../netty-gateway1.0/src/main/java/com/wkk/lean/java/netty/gateway/client/HttpClient4.java)

## 作业二：通过netty实现后端服务的调用

请求经过网关后，在网关处通过netty实现http客户端调用后端服务地址。

关键代码地址：[NettyInboundHandler](../netty-gateway1.0/src/main/java/com/wkk/lean/java/netty/gateway/client/NettyInboundHandler.java) [NettyHttpClient](../netty-gateway1.0/src/main/java/com/wkk/lean/java/netty/gateway/client/NettyHttpClient.java) 

## 作业三：实现网关的filter功能

在网关上添加request filter，在请求头中添加nio:your name。

关键代码地址：[HttpInboundHandler](../netty-gateway1.0/src/main/java/com/wkk/lean/java/netty/gateway/inbound/HttpInboundHandler.java) [FilterChain](../netty-gateway1.0/src/main/java/com/wkk/lean/java/netty/gateway/filter/FilterChain.java) [RequestHeaderHandlerFilter](../netty-gateway1.0/src/main/java/com/wkk/lean/java/netty/gateway/filter/RequestHeaderHandlerFilter.java)

## 作业四：实现网关的router功能

在请求时，对多个后端地址通过路由算法，选取一个地址进行访问。**未实现**
