package com.wkk.learn.java.dynamic.data.source.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 多数据源配置
 * @Author Wangkunkun
 * @Date 2020/12/2 22:25
 */
@Configuration
public class DynamicDataSourceConfig {

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
    @ConfigurationProperties(value = "spring.datasource.slave")
    public DataSourceProperties slaveDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource slaveDataSource(DataSourceProperties slaveDataSourceProperties){
        return slaveDataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource(@Qualifier("masterDataSource") DataSource masterDataSource, @Qualifier("slaveDataSource") DataSource slaveDataSource) {
        Map<String, DataSource> targetDataSources = new HashMap<>(2);
        targetDataSources.put(DataSourceEnum.MASTER.getValue(), masterDataSource);
        targetDataSources.put(DataSourceEnum.SLAVE.getValue(), slaveDataSource);
        return new DynamicDataSource(masterDataSource, targetDataSources);
    }
}
