package com.wkk.learn.account.service.demo.dao;

import com.wkk.learn.account.service.demo.entity.AccountInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @Description 用户账户信息dao
 * @Author Wangkunkun
 * @Date 2020/12/9 23:23
 */
public interface AccountInfoDao extends JpaRepository<AccountInfo, Long> {

    AccountInfo getAccountInfoByUserId(Long userId);

    @Query(value="select id, user_id, amount,freeze_amount, created_at,modified_at from account_info where user_id =? for update",
    nativeQuery = true)
    AccountInfo getAccountInfoByUserIdForUpdate(Long userId);
}
