package com.wkk.learn.goods.service.demo.dao;


import com.wkk.learn.goods.service.demo.entity.GoodsInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface GoodsInfoDao extends CrudRepository<GoodsInfo, Long> {

    GoodsInfo getGoodsInfoById(Long goodsId);

    @Query(value = "select id,goods_name,goods_price,goods_inventory,freeze_inventory,created_at,modified_at from goods_info where id=? for update",
    nativeQuery = true)
    GoodsInfo getGoodsInfoByIdForUpdate(Long goodsId);
}
