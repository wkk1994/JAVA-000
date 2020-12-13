package com.wkk.learn.java.account.service.api.demo.service;


import com.wkk.learn.java.common.api.demo.entity.Result;
import org.dromara.hmily.annotation.Hmily;

import java.math.BigDecimal;

/**
 * @Description 账户服务api
 * @Author Wangkunkun
 * @Date 2020/12/9 23:02
 */
public interface AccountServiceApi {

    /**
     * 扣除账户余额
     * @param userId
     * @param amount
     * @return
     */
    @Hmily
    Result<Boolean> reduceAccountBalance(Long userId, BigDecimal amount);

}
