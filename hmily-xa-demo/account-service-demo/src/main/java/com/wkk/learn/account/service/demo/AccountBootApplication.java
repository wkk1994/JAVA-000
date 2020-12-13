package com.wkk.learn.account.service.demo;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @Description 应用启动类
 * @Author Wangkunkun
 * @Date 2020/12/9 23:08
 */
@SpringBootApplication
@EnableJpaRepositories
@EnableDubboConfiguration
public class AccountBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountBootApplication.class, args);
    }
}
