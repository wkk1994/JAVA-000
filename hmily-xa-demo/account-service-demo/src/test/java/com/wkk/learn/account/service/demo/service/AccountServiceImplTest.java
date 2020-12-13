package com.wkk.learn.account.service.demo.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wkk.learn.account.service.demo.AccountBootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.wkk.learn.java.account.service.api.demo.service.AccountServiceApi ;


import java.math.BigDecimal;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = AccountBootApplication.class)
public class AccountServiceImplTest {

    @Reference
    private AccountServiceApi accountServiceApi;

    @Test
    public void reduceAccountBalance() {
        accountServiceApi.reduceAccountBalance(1001L, BigDecimal.ONE);
    }

}
