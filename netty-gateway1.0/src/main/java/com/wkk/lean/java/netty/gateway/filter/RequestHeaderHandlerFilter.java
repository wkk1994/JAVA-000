package com.wkk.lean.java.netty.gateway.filter;

import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @Description 请求头处理filter
 * @Author Wangkunkun
 * @Date 2020/11/4 22:39
 */
public class RequestHeaderHandlerFilter implements RequestFilter {


    @Override
    public void doFilter(FullHttpRequest request, FilterChain filterChain) {
        request.headers().set("nio", "wangkunkun");
        filterChain.doFilter(request, filterChain);
    }
}
