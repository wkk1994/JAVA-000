package com.wkk.learn.goods.service.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 */
@Entity
@Table(name = "goods_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoodsInfo implements Serializable {

    @Id
    @GeneratedValue
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
