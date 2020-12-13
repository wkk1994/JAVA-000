package com.wkk.learn.java.order.service.demo.service;


import com.wkk.learn.java.order.service.demo.entity.OrderInfo;

/**
 * @Description
 * @Author Wangkunkun
 * @Date 2020/12/10 14:49
 */
public interface OrderService {

    OrderInfo userOrder(Long userId, Long goodsId, int num);
}
