package com.wkk.learn.java.order.service.demo.dao;

import com.wkk.learn.java.order.service.demo.entity.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description 订单信息dao
 * @Author Wangkunkun
 * @Date 2020/12/10 13:57
 */
public interface OrderInfoDao extends JpaRepository<OrderInfo, Long> {

}
