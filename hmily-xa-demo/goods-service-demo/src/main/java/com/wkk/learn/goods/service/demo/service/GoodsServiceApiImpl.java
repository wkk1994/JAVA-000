package com.wkk.learn.goods.service.demo.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.wkk.learn.goods.service.demo.dao.GoodsInfoDao;
import com.wkk.learn.goods.service.demo.entity.GoodsInfo;
import com.wkk.learn.java.common.api.demo.entity.Result;
import com.wkk.learn.java.goods.service.api.demo.entity.GoodsInfoEntity;
import com.wkk.learn.java.goods.service.api.demo.service.GoodsServiceApi;
import org.apache.commons.beanutils.BeanUtils;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;

/**
 * @Description
 * @Author Wangkunkun
 * @Date 2020/12/10 09:40
 */
@Component
@Service(interfaceClass = GoodsServiceApi.class)
public class GoodsServiceApiImpl implements GoodsServiceApi {

    @Autowired
    private GoodsInfoDao goodsInfoDao;

    /**
     * 减库存
     * @param goodsId
     * @param num
     * @return
     */
    @Override
    @Transactional
    @HmilyTCC(confirmMethod = "reduceInventoryConfirm", cancelMethod = "reduceInventoryCancel")
    public Result<Boolean> reduceInventory(Long goodsId, int num) {
        Result<Boolean> result = new Result<>();
        result.setData(true);
        GoodsInfo goodsInfo = goodsInfoDao.getGoodsInfoByIdForUpdate(goodsId);
        if(goodsInfo == null) {
            throw new RuntimeException("商品信息不存在");
        }
        long inventory = goodsInfo.getGoodsInventory() == null ? 0L : goodsInfo.getGoodsInventory();
        if (inventory - num < 0) {
            throw new RuntimeException("库存不足");
        }
        goodsInfo.setGoodsInventory(inventory - num);
        goodsInfo.setFreezeInventory((goodsInfo.getFreezeInventory() == null ? 0L : goodsInfo.getFreezeInventory()) + num);
        goodsInfo.setModifiedAt(System.currentTimeMillis());
        goodsInfoDao.save(goodsInfo);
        return result;
    }

    @Transactional
    public Result<Boolean> reduceInventoryConfirm(Long goodsId, int num) {
        System.out.println("reduceInventoryConfirm...");
        Result<Boolean> result = new Result<>();
        result.setData(true);
        GoodsInfo goodsInfo = goodsInfoDao.getGoodsInfoByIdForUpdate(goodsId);
        if(goodsInfo == null) {
            throw new RuntimeException("商品信息不存在");
        }
        goodsInfo.setFreezeInventory((goodsInfo.getFreezeInventory() == null ? 0L : goodsInfo.getFreezeInventory()) - num);
        goodsInfo.setModifiedAt(System.currentTimeMillis());
        goodsInfoDao.save(goodsInfo);
        return result;
    }

    @Transactional
    public Result<Boolean> reduceInventoryCancel(Long goodsId, int num) {
        System.out.println("reduceInventoryCancel...");
        Result<Boolean> result = new Result<>();
        result.setData(true);
        GoodsInfo goodsInfo = goodsInfoDao.getGoodsInfoByIdForUpdate(goodsId);
        if(goodsInfo == null) {
            throw new RuntimeException("商品信息不存在");
        }
        long inventory = goodsInfo.getGoodsInventory() == null ? 0L : goodsInfo.getGoodsInventory();
        goodsInfo.setGoodsInventory(inventory + num);
        goodsInfo.setFreezeInventory((goodsInfo.getFreezeInventory() == null ? 0L : goodsInfo.getFreezeInventory()) - num);
        goodsInfo.setModifiedAt(System.currentTimeMillis());
        goodsInfoDao.save(goodsInfo);
        return result;
    }

    /**
     * 查询商品信息
     *
     * @param goodsId
     * @return
     */
    @Override
    public Result<GoodsInfoEntity> getGoodsInfoById(Long goodsId) {
        Result<GoodsInfoEntity> result = new Result<>();

        GoodsInfo goodsInfo = goodsInfoDao.getGoodsInfoById(goodsId);
        GoodsInfoEntity goodsInfoEntity = new GoodsInfoEntity();
        try {
            BeanUtils.copyProperties(goodsInfoEntity, goodsInfo);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        result.setData(goodsInfoEntity);
        return result;
    }
}
