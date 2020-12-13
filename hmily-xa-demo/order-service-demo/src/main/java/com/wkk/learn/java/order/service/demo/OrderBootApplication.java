package com.wkk.learn.java.order.service.demo;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @Description 订单服务
 * @Author Wangkunkun
 * @Date 2020/12/10 13:52
 */
@SpringBootApplication
@EnableJpaRepositories
@EnableDubboConfiguration
public class OrderBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderBootApplication.class, args);
    }
}
