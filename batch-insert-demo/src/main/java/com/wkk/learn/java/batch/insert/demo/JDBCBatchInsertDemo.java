package com.wkk.learn.java.batch.insert.demo;

import com.wkk.learn.java.batch.insert.demo.entity.GoodsInfo;
import com.wkk.learn.java.batch.insert.demo.entity.UserInfo;

import java.math.BigDecimal;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description 使用jdbc批量插入
 * @Author Wangkunkun
 * @Date 2020/11/30 12:31
 */
public class JDBCBatchInsertDemo {

    private static final String url = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=UTF-8";
    private static final String userName = "root";
    private static final String password = "root!@#";

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
        StringBuilder stringBuilder = new StringBuilder();

        int id = 1;
        for (UserInfo userInfo : userInfoList) {
            for (GoodsInfo goodsInfo : goodsInfoList) {
                for (int i = 1; i <= 1000; i++) {
                    stringBuilder.append("(").append(id++).append(",").append(userInfo.getId()).append(",").append(goodsInfo.getId())
                            .append(",").append(i).append(",").append(goodsInfo.getGoodsPrice().multiply(BigDecimal.valueOf(i)))
                            .append(",").append(System.currentTimeMillis()).append(",").append(System.currentTimeMillis()).append("),");
                    if(id % 1000 == 0) {
                        executeSql(connection, stringBuilder);
                        stringBuilder = new StringBuilder();
                    }
                }
            }
        }
        if(id % 1000 != 0) {
            executeSql(connection, stringBuilder);
        }
        long end = System.currentTimeMillis();
        System.out.println("用时： " + (end - begin));
    }

    private static void executeSql(Connection connection, StringBuilder stringBuilder) throws SQLException {
        PreparedStatement preparedStatement;
        String sql = "insert into order_info(id, user_id, goods_id, goods_num, amount, order_time, created_at) values" +
                stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1) + ";";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
    }

}
