package com.wkk.learn.java.dynamic.data.source.dao;

import com.wkk.learn.java.dynamic.data.source.entity.OrderInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description 订单信息
 * @Author Wangkunkun
 * @Date 2020/12/9 21:25
 */
//@Component
@Repository
public interface OrderInfoDao extends CrudRepository<OrderInfo, Long> {


}
