package com.wkk.lean.java.dynamic.data.source.dao;

import com.wkk.lean.java.dynamic.data.source.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author Wangkunkun
 * @Date 2020/12/2 21:25
 */
@Component
public class UserInfoDao {

    @Autowired
    private JdbcTemplate masterJdbcTemplate;

    @Resource(name = "slaveJdbcTemplate")
    private JdbcTemplate slaveJdbcTemplate;

    @Transactional
    public void save(UserInfo userInfo) {
        masterJdbcTemplate.update("INSERT INTO user_info(`id`,`user_name`,`user_sex` ,`user_mobile`,`user_email`,`created_at`) " +
                "VALUES (?,?,?,?,?,?)", userInfo.getId(), userInfo.getUserName(), userInfo.getUserSex(),
                userInfo.getUserMobile(), userInfo.getUserEmail(), userInfo.getCreatedAt());
    }


    public UserInfo query(int id) {
        List<UserInfo> query = slaveJdbcTemplate.query("select `id`,`user_name`,`user_sex` ,`user_mobile`,`user_email`,`created_at`, `modified_at` from user_info where id = ?",
                new Object[]{id}, (rs, rowNum) -> {
                    UserInfo userInfo = new UserInfo();
                    userInfo.setId(rs.getInt(1));
                    userInfo.setUserName(rs.getString(2));
                    userInfo.setUserSex(rs.getInt(3));
                    userInfo.setUserMobile(rs.getString(4));
                    userInfo.setUserEmail(rs.getString(5));
                    userInfo.setCreatedAt(rs.getLong(6));
                    userInfo.setModifiedAt(rs.getLong(7));
                    return userInfo;
                });
        if(query.isEmpty()) {
            return null;
        }
        return query.get(0);
    }
}
