package com.wkk.learn.java.dynamic.data.source.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 动态数据源
 * @Author Wangkunkun
 * @Date 2020/12/2 22:07
 */
public class DynamicDataSource extends AbstractRoutingDataSource {




    public DynamicDataSource(DataSource defaultTargetDataSource, Map<String, DataSource> targetDataSources) {
        this(defaultTargetDataSource, targetDataSources, null);
    }

    public DynamicDataSource(DataSource defaultTargetDataSource, Map<String, DataSource> targetDataSources, List<DataSource> slaveDataSourceList) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(new HashMap<>(targetDataSources));
        super.afterPropertiesSet();
    }



    protected Object determineCurrentLookupKey() {
        String dataSourceName = DynamicDataSourceHolder.getDataSource();
        if(dataSourceName == null) {
            // 设置默认数据源
            DynamicDataSourceHolder.setDataSource(DataSourceEnum.MASTER.getValue());
        }
        return DynamicDataSourceHolder.getDataSource();
    }

}
