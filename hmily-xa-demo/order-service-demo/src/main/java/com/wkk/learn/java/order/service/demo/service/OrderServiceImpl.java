package com.wkk.learn.java.order.service.demo.service;


import com.alibaba.dubbo.config.annotation.Reference;
import com.wkk.learn.java.account.service.api.demo.service.AccountServiceApi;
import com.wkk.learn.java.common.api.demo.entity.Result;
import com.wkk.learn.java.goods.service.api.demo.entity.GoodsInfoEntity;
import com.wkk.learn.java.goods.service.api.demo.service.GoodsServiceApi;
import com.wkk.learn.java.order.service.demo.dao.OrderInfoDao;
import com.wkk.learn.java.order.service.demo.entity.OrderInfo;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @Description 订单服务
 * @Author Wangkunkun
 * @Date 2020/12/10 14:00
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderInfoDao orderInfoDao;

    @Reference
    private AccountServiceApi accountServiceApi;

    @Reference
    private GoodsServiceApi goodsServiceApi;

    /**
     * 下单
     * @param userId 用户id
     * @param goodsId 商品id
     * @param num 商品数量
     */

    @Override
    @Transactional
    @HmilyTCC(confirmMethod = "userOrderConfirm", cancelMethod = "userOrderCancel")
    public OrderInfo userOrder(Long userId, Long goodsId, int num) {
        // 查询商品信息
        Result<GoodsInfoEntity> goodsInfoById = goodsServiceApi.getGoodsInfoById(goodsId);
        GoodsInfoEntity goodsInfo = goodsInfoById.getData();
        if(goodsInfo == null) {
            throw new RuntimeException("商品信息不存在");
        }
        BigDecimal goodsPrice = goodsInfo.getGoodsPrice();
        BigDecimal amount = goodsPrice.multiply(BigDecimal.valueOf(num));
        Result<Boolean> booleanResult = accountServiceApi.reduceAccountBalance(userId, amount);

        Result<Boolean> booleanResult1 = goodsServiceApi.reduceInventory(goodsId, num);
        // 保存订单信息
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(userId);
        orderInfo.setGoodsId(goodsId);
        orderInfo.setAmount(amount);
        orderInfo.setGoodsNum(Long.valueOf(num));
        orderInfo.setOrderTime(System.currentTimeMillis());
        orderInfo.setCreatedAt(System.currentTimeMillis());
        orderInfoDao.save(orderInfo);
        return orderInfo;
    }

    public OrderInfo userOrderConfirm(Long userId, Long goodsId, int num) {
        System.out.println("userOrderConfirm...");
        return null;
    }

    public OrderInfo userOrderCancel(Long userId, Long goodsId, int num) {
        System.out.println("userOrderCancel...");
        return null;
    }

}
