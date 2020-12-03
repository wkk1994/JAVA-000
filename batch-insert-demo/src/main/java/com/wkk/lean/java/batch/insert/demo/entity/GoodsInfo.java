package com.wkk.lean.java.batch.insert.demo.entity;

import java.math.BigDecimal;

/**
 * @Description 商品信息
 * @Author Wangkunkun
 * @Date 2020/11/30 13:20
 */
public class GoodsInfo {

    private Integer id;

    private String goodsName;

    private BigDecimal goodsPrice;

    private BigDecimal goodsInventory;

    private Long createdAt;

    private Long modifiedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getGoodsInventory() {
        return goodsInventory;
    }

    public void setGoodsInventory(BigDecimal goodsInventory) {
        this.goodsInventory = goodsInventory;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Long modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}


