package com.wkk.learn.java.dynamic.data.source.config;

/**
 * @Description 数据源holder
 * @Author Wangkunkun
 * @Date 2020/12/3 00:38
 */
public class DynamicDataSourceHolder {

    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * 获取数据源
     * @return
     */
    public static String getDataSource() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 设置数据源
     *
     * @param dataSourceName
     */
    public static void setDataSource(String dataSourceName) {
        CONTEXT_HOLDER.set(dataSourceName);
    }

    /**
     * 清除数据源
     */
    public static void clearDataSource() {
        CONTEXT_HOLDER.remove();
    }
}
