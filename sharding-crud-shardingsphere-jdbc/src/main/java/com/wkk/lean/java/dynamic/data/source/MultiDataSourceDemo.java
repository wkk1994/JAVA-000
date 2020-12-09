package com.wkk.lean.java.dynamic.data.source;

import com.wkk.lean.java.dynamic.data.source.dao.OrderInfoDao;
import com.wkk.lean.java.dynamic.data.source.entity.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;

/**
 * @Description 在分库分表下使用shardingsphere-jdbc实现简单的crud
 * @Author Wangkunkun
 * @Date 2020/12/2 21:17
 */

@SpringBootApplication(exclude = JtaAutoConfiguration.class)
public class MultiDataSourceDemo implements CommandLineRunner {

    @Autowired
    private OrderInfoDao orderInfoDao;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(MultiDataSourceDemo.class).web(WebApplicationType.NONE).run(args);
    }

    public void run(String... args) throws Exception {
        for (int i = 0; i < 32; i++) {
            // 插入
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setId(orderInfoDao.queryMaxId()+1);
            orderInfo.setUserId(1L + i);
            orderInfo.setGoodsId(1001L + i);
            orderInfo.setAmount(BigDecimal.ONE);
            orderInfo.setOrderTime(System.currentTimeMillis());
            orderInfo.setCreatedAt(System.currentTimeMillis());
            orderInfoDao.save(orderInfo);
            orderInfo = orderInfoDao.findById(orderInfo.getId());
            System.out.println(orderInfo);
        }
    }



}
