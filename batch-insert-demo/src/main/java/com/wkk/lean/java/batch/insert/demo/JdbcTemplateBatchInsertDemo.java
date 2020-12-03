package com.wkk.lean.java.batch.insert.demo;

import com.wkk.lean.java.batch.insert.demo.entity.GoodsInfo;
import com.wkk.lean.java.batch.insert.demo.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 使用jdbcTemplate批量插入
 * @Author Wangkunkun
 * @Date 2020/11/30 12:31
 */
@SpringBootApplication
public class JdbcTemplateBatchInsertDemo {

    private static final String url = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=UTF-8";
    private static final String userName = "test";
    private static final String password = "test";

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(JdbcTemplateBatchInsertDemo.class).web(WebApplicationType.NONE)
                .run(args);
        JdbcTemplate jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);

        batchInsert(jdbcTemplate);
        applicationContext.close();
    }

    public static void batchInsert(JdbcTemplate jdbcTemplate) {
        List<UserInfo> userInfoList = jdbcTemplate.query("select * from user_info", (rs, rowNum) -> {
            UserInfo userInfo = new UserInfo();
            userInfo.setId(rs.getInt(1));
            return userInfo;
        });
        List<GoodsInfo> goodsInfoList = jdbcTemplate.query("select * from goods_info", (rs, rowNum) -> {
            GoodsInfo goodsInfo = new GoodsInfo();
            goodsInfo.setId(rs.getInt(1));
            goodsInfo.setGoodsPrice(rs.getBigDecimal(3));
            return goodsInfo;
        });

        long begin = System.currentTimeMillis();
        int id = 1;
        List<Object[]> objectList = new ArrayList<Object[]>(1000000);
        for (UserInfo userInfo : userInfoList) {
            for (GoodsInfo goodsInfo : goodsInfoList) {
                for (int i = 1; i <= 100; i++) {
                    Object[] objects = {id++, userInfo.getId(), goodsInfo.getId(), i, goodsInfo.getGoodsPrice().multiply(BigDecimal.valueOf(i)),
                            System.currentTimeMillis(), System.currentTimeMillis()};
                    objectList.add(objects);
                }

            }
        }
        jdbcTemplate.batchUpdate("insert into order_info(id, user_id, goods_id, goods_num, amount, order_time, created_at) values (?,?,?,?,?,?,?)", objectList);
        long end = System.currentTimeMillis();
        System.out.println("用时： " + (end - begin));

    }



}
