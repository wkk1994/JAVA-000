package com.wkk.learn.java.dynamic.data.source;

import com.wkk.learn.java.dynamic.data.source.dao.OrderInfoDao;
import com.wkk.learn.java.dynamic.data.source.entity.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * @Description 在分库分表下使用shardingsphere-jdbc实现简单的crud
 * @Author Wangkunkun
 * @Date 2020/12/2 21:17
 */

@ComponentScan("com.wkk.learn.java.dynamic.data.source")
@EntityScan(basePackages = "com.wkk.learn.java.dynamic.data.source.entity")
@SpringBootApplication(exclude = JtaAutoConfiguration.class)
public class MultiDataSourceDemo implements CommandLineRunner {

    //@Autowired
    private OrderInfoDao orderInfoDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(MultiDataSourceDemo.class).web(WebApplicationType.NONE).run(args);
    }

    public void run(String... args) throws Exception {
        // 插入
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(1001L);
        orderInfo.setGoodsId(1001L);
        orderInfo.setAmount(BigDecimal.ONE);
        orderInfo.setOrderTime(System.currentTimeMillis());
        orderInfo.setCreatedAt(System.currentTimeMillis());
        //OrderInfo save = orderInfoDao.save(orderInfo);
        //System.out.println(save);
        //Optional<OrderInfo> byId = orderInfoDao.findById(save.getId());
        //System.out.println(byId.get());

    }



}
