package com.wkk.lean.java.netty.gateway.filter;

import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @Description 过滤器链
 * @Author Wangkunkun
 * @Date 2020/11/4 22:30
 */
public class FilterChain {

    /**
     * 标示当前过滤器执行的位置
     */
    private int current = 0;

    private RequestFilter[] requestFilters;

    public FilterChain() {
    }

    public FilterChain(RequestFilter[] requestFilters) {
        this.requestFilters = requestFilters;
    }

    /**
     * 过滤器执行
     * @param request
     */
    public void doFilter(FullHttpRequest request, FilterChain filterChain) {
        if(requestFilters != null && requestFilters.length <= 0) {
            return;
        }
        if(current >= requestFilters.length) {
            return;
        }
        requestFilters[current++].doFilter(request, filterChain);
    }
}
