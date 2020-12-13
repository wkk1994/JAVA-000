package com.wkk.learn.java.common.api.demo.entity;

import java.io.Serializable;

/**
 * @Description 响应结果
 * @Author Wangkunkun
 * @Date 2020/12/9 23:14
 */
public class Result<T> implements Serializable {

    /**
     * 错误code 默认成功
     */
    private int code = 200;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 响应数据
     */
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", errorMsg='" + errorMsg + '\'' +
                ", data=" + data +
                '}';
    }
}
