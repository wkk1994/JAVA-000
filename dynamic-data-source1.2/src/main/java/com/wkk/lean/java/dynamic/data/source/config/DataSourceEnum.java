package com.wkk.lean.java.dynamic.data.source.config;

/**
 * @Description 数据源类型
 * @Author Wangkunkun
 * @Date 2020/12/2 22:26
 */
public enum DataSourceEnum {
    MASTER("master"),
    SLAVE("slave");

    private String value;


    DataSourceEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
