package com.wkk.learn.java.springdemo.work05;

import com.wkk.learn.java.springdemo.work05.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description
 * @Author Wangkunkun
 * @Date 2020/11/18 23:31
 */
public class JdbcCrudDemo {

    private static String url = "jdbc:h2:mem:test";
    private static String userName = "sa";
    private static String password = null;

    public static void main(String[] args) {

        TestClass testClass = () -> System.out.println("test");
        try {
            // 创建表
            createTable();
            //插入数据
            User user = new User();
            user.setName("hello");
            insertUser(user);
            System.out.println(user);
            insertUser(user);

            // 查询
            List<User> users = queryUserByName("hello");
            System.out.println(users);

            // 更新
            user = new User();
            user.setId(users.get(0).getId());
            user.setName("world");
            updateUser(user);

            // 查询
            users = queryUserByName("world");
            System.out.println(users);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建表结构
     * @throws Exception
     */
    public static void createTable() throws Exception {
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection(url, userName, password);
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE USER (ID INT IDENTITY, NAME VARCHAR(64));");
    }

    /**
     * 创建表结构
     * @throws Exception
     */
    public static void insertUser(User user) throws Exception {
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection(url, userName, password);
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO USER(NAME) VALUES (?);");
        preparedStatement.setString(1, user.getName());
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement("select @identity as id;");
        /*ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            System.out.println(resultSet.getInt(1));
        }
        System.out.println(resultSet);*/
    }

    /**
     * 创建表结构
     * @throws Exception
     */
    public static List<User> queryUserByName(String name) throws Exception {
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection(url, userName, password);
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM USER WHERE NAME = ?;");
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<User> userList = new LinkedList<>();
        while (resultSet.next()) {
            userList.add(new User(resultSet.getInt(1), resultSet.getString(2)));
        }
        return userList;
    }

    public static void updateUser(User user) throws Exception {
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection(url, userName, password);
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE USER SET NAME = ? WHERE ID=?");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setInt(2, user.getId());
        int i = preparedStatement.executeUpdate();
        System.out.println(i);
    }
}
