package com.wkk.learn.account.service.demo.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.wkk.learn.account.service.demo.dao.AccountInfoDao;
import com.wkk.learn.account.service.demo.entity.AccountInfo;
import com.wkk.learn.java.common.api.demo.entity.Result;
import com.wkk.learn.java.account.service.api.demo.service.AccountServiceApi;
import org.dromara.hmily.annotation.HmilyTAC;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @Description
 * @Author Wangkunkun
 * @Date 2020/12/9 23:11
 */
@Component
@Service(interfaceClass = AccountServiceApi.class)
public class AccountServiceImpl implements AccountServiceApi {

    @Autowired
    private AccountInfoDao accountInfoDao;

    /**
     * 扣除账户余额
     *
     * @param userId
     * @param amount
     * @return
     */
    @Override
    @HmilyTCC(confirmMethod = "reduceAccountBalanceConfirm", cancelMethod = "reduceAccountBalanceCancel")
    @Transactional
    public Result<Boolean> reduceAccountBalance(Long userId, BigDecimal amount) {
        Result<Boolean> result = new Result<>();
        result.setData(true);
        AccountInfo accountInfo = accountInfoDao.getAccountInfoByUserIdForUpdate(userId);
        if(accountInfo == null) {
            throw new RuntimeException("账户信息不存在");
        }
        BigDecimal subtract = accountInfo.getAmount().subtract(amount);
        if(subtract.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("账户余额不足");
        }
        accountInfo.setAmount(subtract);
        accountInfo.setFreezeAmount((accountInfo.getFreezeAmount() == null ? BigDecimal.ZERO : accountInfo.getFreezeAmount()).add(amount));
        accountInfo.setModifiedAt(System.currentTimeMillis());
        accountInfoDao.save(accountInfo);
        return result;
    }

    /**
     * TCC中的Confirm-提交事务
     * @param userId
     * @param amount
     * @return
     */
    @Transactional
    public Result<Boolean> reduceAccountBalanceConfirm(Long userId, BigDecimal amount) {
        System.out.println("reduceAccountBalanceConfirm...");
        Result<Boolean> result = new Result<>();
        result.setData(true);
        AccountInfo accountInfo = accountInfoDao.getAccountInfoByUserIdForUpdate(userId);
        if(accountInfo == null) {
            throw new RuntimeException("账户信息不存在");
        }
        accountInfo.setFreezeAmount((accountInfo.getFreezeAmount() == null ? BigDecimal.ZERO : accountInfo.getFreezeAmount()).subtract(amount));
        accountInfo.setModifiedAt(System.currentTimeMillis());
        accountInfoDao.save(accountInfo);
        return result;
    }

    /**
     * TCC中的Cancel-取消事务
     * @param userId
     * @param amount
     * @return
     */
    @Transactional
    public Result<Boolean> reduceAccountBalanceCancel(Long userId, BigDecimal amount) {
        System.out.println("reduceAccountBalanceCancel...");
        Result<Boolean> result = new Result<>();
        result.setData(true);
        AccountInfo accountInfo = accountInfoDao.getAccountInfoByUserIdForUpdate(userId);
        if(accountInfo == null) {
            throw new RuntimeException("账户信息不存在");
        }
        BigDecimal subtract = accountInfo.getAmount().add(amount);
        accountInfo.setAmount(subtract);
        accountInfo.setFreezeAmount((accountInfo.getFreezeAmount() == null ? BigDecimal.ZERO : accountInfo.getFreezeAmount()).subtract(amount));
        accountInfo.setModifiedAt(System.currentTimeMillis());
        accountInfoDao.save(accountInfo);
        return result;
    }
}


