package com.wkk.learn.java.order.service.demo.controller;

import com.wkk.learn.java.order.service.demo.entity.OrderInfo;
import com.wkk.learn.java.order.service.demo.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 订单controller
 * @Author Wangkunkun
 * @Date 2020/12/10 14:03
 */
@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping
    public OrderInfo userOrder(Long userId, Long goodsId, int num) {
        return orderService.userOrder(userId, goodsId, num);
    }
}
