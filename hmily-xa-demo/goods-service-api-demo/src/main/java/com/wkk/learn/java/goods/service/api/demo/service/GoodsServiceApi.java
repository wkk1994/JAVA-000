package com.wkk.learn.java.goods.service.api.demo.service;

import com.wkk.learn.java.common.api.demo.entity.Result;
import com.wkk.learn.java.goods.service.api.demo.entity.GoodsInfoEntity;
import org.dromara.hmily.annotation.Hmily;

/**
 * @Description 商品服务api
 * @Author Wangkunkun
 * @Date 2020/12/10 01:05
 */
public interface GoodsServiceApi {

    /**
     * 减库存
     * @param goodsId
     * @param num
     * @return
     */
    @Hmily
    Result<Boolean> reduceInventory(Long goodsId, int num);

    /**
     * 查询商品信息
     * @param goodsId
     * @return
     */
    Result<GoodsInfoEntity> getGoodsInfoById(Long goodsId);
}
