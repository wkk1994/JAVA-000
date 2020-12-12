package com.wkk.learn.java.dynamic.data.source.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.*;

/**
 * @Description slave据源
 * @Author Wangkunkun
 * @Date 2020/12/2 22:07
 */
public class SlaveDataSource extends AbstractRoutingDataSource {

    /**
     * slave数据源数量
     */
    private int dataSourceSize;

    private Random random = new Random();

    /**
     * slave数据源key
     */
    private List<String> dataSourceNameSet;

    public SlaveDataSource(DataSource defaultTargetDataSource, Map<String, DataSource> targetDataSources) {
        dataSourceNameSet = new ArrayList(targetDataSources.keySet());
        dataSourceSize = dataSourceNameSet.size();
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(new HashMap<>(targetDataSources));
        super.afterPropertiesSet();
    }


    protected Object determineCurrentLookupKey() {
        String dataSourceName = DynamicDataSourceHolder.getDataSource();
        if(dataSourceName == null) {
            // 设置随机数据源
            DynamicDataSourceHolder.setDataSource(getRomDataSource());
        }else if(dataSourceName.equals(DataSourceEnum.SLAVE.getValue())) {
            // 设置随机数据源
            DynamicDataSourceHolder.setDataSource(getRomDataSource());
        }
        System.out.println(DynamicDataSourceHolder.getDataSource());
        return DynamicDataSourceHolder.getDataSource();
    }

    public String getRomDataSource() {
        int index = random.nextInt(dataSourceSize);
        return dataSourceNameSet.get(index);
    }
}
