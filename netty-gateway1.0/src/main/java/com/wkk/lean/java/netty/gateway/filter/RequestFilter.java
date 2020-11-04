package com.wkk.lean.java.netty.gateway.filter;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;

/**
 * @Description
 * @Author Wangkunkun
 * @Date 2020/11/4 22:34
 */
public interface RequestFilter {

    void doFilter(FullHttpRequest request, FilterChain filterChain);
}
