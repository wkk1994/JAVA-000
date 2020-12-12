package com.wkk.learn.java.dynamic.data.source.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Description 多数据源配置
 * @Author Wangkunkun
 * @Date 2020/12/2 22:25
 */
@Configuration
public class DynamicDataSourceConfig implements EnvironmentAware {

    private DataSource slaveDataSource;

    @Bean
    @ConfigurationProperties(value = "spring.datasource.master")
    public DataSourceProperties masterDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource masterDataSource(DataSourceProperties masterDataSourceProperties){
        return masterDataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource(@Qualifier("masterDataSource") DataSource masterDataSource) {
        Map<String, DataSource> targetDataSources = new HashMap<>(2);
        targetDataSources.put(DataSourceEnum.MASTER.getValue(), masterDataSource);
        targetDataSources.put(DataSourceEnum.SLAVE.getValue(), slaveDataSource);
        return new DynamicDataSource(masterDataSource, targetDataSources);
    }

    /**
     * Set the {@code Environment} that this component runs in.
     *
     * @param environment
     */
    @Override
    public void setEnvironment(Environment environment) {
        // 初始化从库数据源
        String property = environment.getProperty("spring.datasource.slave.name");
        if(StringUtils.isEmpty(property)) {
            return;
        }
        Map<String, DataSource> targetDataSources = new HashMap<>();
        for (String slave : property.split(",")) {
            DataSourceProperties dataSourceProperties = new DataSourceProperties();
            dataSourceProperties.setUrl(environment.getProperty("spring.datasource.slave." + slave + ".url"));
            dataSourceProperties.setUsername(environment.getProperty("spring.datasource.slave." + slave + ".username"));
            dataSourceProperties.setPassword(environment.getProperty("spring.datasource.slave." + slave + ".password"));
            targetDataSources.put(slave, dataSourceProperties.initializeDataSourceBuilder().build());
        }
        slaveDataSource = new SlaveDataSource(null, targetDataSources);
    }
}
