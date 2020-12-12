package com.wkk.learn.java.netty.gateway.filter;

/**
 * @Description 过滤器配置
 * @Author Wangkunkun
 * @Date 2020/11/4 22:41
 */
public class FilterConfig {

    public static FilterChain getFilterChain() {
        RequestFilter[] customFilters = new RequestFilter[]{new RequestHeaderHandlerFilter()};
        return new FilterChain(customFilters);
    }
}
