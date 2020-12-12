package com.wkk.learn.java.batch.insert.demo;

import com.wkk.learn.java.batch.insert.demo.entity.GoodsInfo;
import com.wkk.learn.java.batch.insert.demo.entity.UserInfo;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description 使用拼接PreparedStatement批量插入
 * @Author Wangkunkun
 * @Date 2020/11/30 12:31
 */
public class SplicingPreparedStatementBatchInsertDemo {

    private static final String url = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=UTF-8";
    private static final String userName = "test";
    private static final String password = "test";

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, userName, password);
        // 获取用户信息
        PreparedStatement preparedStatement = connection.prepareStatement("select * from user_info;");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<UserInfo> userInfoList = new LinkedList<UserInfo>();
        while (resultSet.next()) {
            UserInfo userInfo = new UserInfo();
            userInfo.setId(resultSet.getInt(1));
            userInfoList.add(userInfo);
        }

        // 获取商品信息
        preparedStatement = connection.prepareStatement("select * from goods_info;");
        resultSet = preparedStatement.executeQuery();
        List<GoodsInfo> goodsInfoList = new LinkedList<GoodsInfo>();
        while (resultSet.next()) {
            GoodsInfo goodsInfo = new GoodsInfo();
            goodsInfo.setId(resultSet.getInt(1));
            goodsInfo.setGoodsPrice(resultSet.getBigDecimal(3));
            goodsInfoList.add(goodsInfo);
        }
        // 批量插入数据信息
        long begin = System.currentTimeMillis();
        preparedStatement = connection.prepareStatement("insert into order_info(id, user_id, goods_id, goods_num, amount, order_time, created_at) " +
                "values(?,?,?,?,?,?,?)");
        int id = 1;
        for (UserInfo userInfo : userInfoList) {
            for (GoodsInfo goodsInfo : goodsInfoList) {
                for (int i = 1; i <= 100; i++) {
                    preparedStatement.setInt(1, id ++);
                    preparedStatement.setInt(2, userInfo.getId());
                    preparedStatement.setInt(3, goodsInfo.getId());
                    preparedStatement.setInt(4, i);
                    preparedStatement.setBigDecimal(5, goodsInfo.getGoodsPrice().multiply(BigDecimal.valueOf(i)));
                    preparedStatement.setLong(6, System.currentTimeMillis());
                    preparedStatement.setLong(7, System.currentTimeMillis());
                    preparedStatement.addBatch();
                    if(id % 1000 == 0) {
                        preparedStatement.executeBatch();
                    }
                }

            }
        }
        if(id % 1000 != 0) {
            preparedStatement.executeBatch();
        }
        long end = System.currentTimeMillis();
        System.out.println("用时： " + (end - begin));
    }

}
