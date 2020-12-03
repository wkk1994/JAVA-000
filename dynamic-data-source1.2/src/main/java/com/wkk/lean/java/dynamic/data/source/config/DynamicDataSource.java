package com.wkk.lean.java.dynamic.data.source.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 动态数据源
 * @Author Wangkunkun
 * @Date 2020/12/2 22:07
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();



    public DynamicDataSource(DataSource defaultTargetDataSource, Map<String, DataSource> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(new HashMap<>(targetDataSources));
        super.afterPropertiesSet();
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

    protected Object determineCurrentLookupKey() {
        String dataSourceName = CONTEXT_HOLDER.get();
        if(dataSourceName == null) {
            // 设置默认数据源
            DynamicDataSource.CONTEXT_HOLDER.set(DataSourceEnum.MASTER.getValue());
        }
        return CONTEXT_HOLDER.get();
    }
}
