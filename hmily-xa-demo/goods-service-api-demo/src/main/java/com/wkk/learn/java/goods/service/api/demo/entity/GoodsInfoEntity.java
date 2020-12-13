package com.wkk.learn.java.goods.service.api.demo.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description 商品信息
 * @Author Wangkunkun
 * @Date 2020/12/10 14:35
 */
public class GoodsInfoEntity implements Serializable {


    private Long id;

    private String goodsName;

    private BigDecimal goodsPrice;

    private Long goodsInventory;

    private Long freezeInventory;

    private Long createdAt;

    private Long modifiedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getGoodsInventory() {
        return goodsInventory;
    }

    public void setGoodsInventory(Long goodsInventory) {
        this.goodsInventory = goodsInventory;
    }

    public Long getFreezeInventory() {
        return freezeInventory;
    }

    public void setFreezeInventory(Long freezeInventory) {
        this.freezeInventory = freezeInventory;
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
