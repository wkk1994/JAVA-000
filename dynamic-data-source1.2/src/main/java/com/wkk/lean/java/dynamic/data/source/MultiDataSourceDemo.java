package com.wkk.lean.java.dynamic.data.source;

import com.wkk.lean.java.dynamic.data.source.dao.UserInfoDao;
import com.wkk.lean.java.dynamic.data.source.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @Description 通过实现AbstractRoutingDataSource和注解的方式实现多数据源动态切换
 * @Author Wangkunkun
 * @Date 2020/12/2 21:17
 */
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class
})
public class MultiDataSourceDemo implements CommandLineRunner {

    @Autowired
    private UserInfoDao userInfoDao;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(MultiDataSourceDemo.class).web(WebApplicationType.NONE).run(args);
    }

    public void run(String... args) throws Exception {
        for (int i = 1; i <= 100; i++) {
            int id = 10000 + i;
            UserInfo userInfo = new UserInfo();
            userInfo.setId(id);
            userInfo.setUserName("用户" + id);
            userInfo.setUserSex(1);
            userInfo.setUserMobile("123456789");
            userInfo.setUserEmail("123456789@163.com");
            userInfo.setCreatedAt(System.currentTimeMillis());
            userInfoDao.save(userInfo);

            UserInfo query = userInfoDao.query(userInfo.getId());
            System.out.println("query : " + query);
        }


    }



}
