package com.wkk.lean.java.batch.insert.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Random;

/**
 * @Description 初始化订单需要的数据
 * @Author Wangkunkun
 * @Date 2020/11/30 12:48
 */
public class InitOrderNeedDataDemo {

    private static final String url = "jdbc:mysql://127.0.0.1:3309/test?characterEncoding=UTF-8";
    private static final String userName = "root";
    private static final String password = "root!@#";

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, userName, password);
        // 初始化用户信息
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user_info(`id`,`user_name`,`user_sex` ,`user_mobile`,`user_email`,`created_at`) " +
                "VALUES (?,?,?,?,?,?);");
        Random random = new Random();
        for (int i = 1; i <= 100; i++) {
            preparedStatement.setInt(1, 1000 + i);
            preparedStatement.setString(2, "用户"+ i);
            preparedStatement.setInt(3, random.nextInt(2));
            String userMobile = "135" + getRandom1(8);
            preparedStatement.setString(4, userMobile);
            preparedStatement.setString(5, userMobile+"@163.com");
            preparedStatement.setLong(6, System.currentTimeMillis());
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();

        // 初始化商品信息
        preparedStatement = connection.prepareStatement("INSERT INTO goods_info(id,goods_name,goods_price,goods_inventory,created_at) " +
                "VALUES (?,?,?,?,?);");
        for (int i = 1; i <= 100; i++) {
            preparedStatement.setInt(1, 1000 + i);
            preparedStatement.setString(2, "商品"+ i);
            preparedStatement.setInt(3, random.nextInt(1000));
            String userMobile = "135" + getRandom1(8);
            preparedStatement.setInt(4, 1000000);
            preparedStatement.setLong(5, System.currentTimeMillis());
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
    }

    public static String getRandom1(int len) {
        int rs = (int) ((Math.random() * 9 + 1) * Math.pow(10, len - 1));
        return String.valueOf(rs);
    }
}
