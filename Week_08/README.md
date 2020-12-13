# 学习笔记

## 第 15 节课作业实践

### 2、（必做）设计对前面的订单表数据进行水平分库分表，拆分2个库，每个库16张表。 并在新结构在演示常见的增删改查操作。代码、sql和配置文件，上传到github。

主要代码：[application.yml](https://github.com/wkk1994/JAVA-000/blob/main/sharding-crud-shardingsphere-jdbc/src/main/resources/application.yml) [OrderInfoDao.java](https://github.com/wkk1994/JAVA-000/blob/main/sharding-crud-shardingsphere-jdbc/src/main/java/com/wkk/learn/java/dynamic/data/source/dao/OrderInfoDao.java)

### 3、（选做）模拟1000万的订单单表数据，迁移到上面作业2的分库分表中。

迁移命令请求：

```text
curl -X POST \
  http://localhost:8888/scaling/job/start \
  -H 'content-type: application/json' \
  -d '{
    "ruleConfiguration": {
      "source": {
        "type": "shardingSphereJdbc",
        "parameter": {
          "dataSource":"
            dataSources:
              ds_0:
                dataSourceClassName: com.zaxxer.hikari.HikariDataSource
                props:
                  driverClassName: com.mysql.jdbc.Driver
                  jdbcUrl: jdbc:mysql://127.0.0.1:3306/test?useSSL=false
                  username: root
                  password: root!@#
            ",
          "rule":"
            rules:
            - !SHARDING
              tables:
                order_info:
                  actualDataNodes: ds_0.order_info
            "
        }
      },
      "target": {
          "type": "shardingSphereJdbc",
          "parameter": {
            "dataSource":"
              dataSources:
                test0:
                  dataSourceClassName: com.zaxxer.hikari.HikariDataSource
                  props:
                    driverClassName: com.mysql.jdbc.Driver
                    jdbcUrl: jdbc:mysql://127.0.0.1:3306/test0?useSSL=false
                    username: root
                    password: root!@#
                test1:
                  dataSourceClassName: com.zaxxer.hikari.HikariDataSource
                  props:
                    driverClassName: com.mysql.jdbc.Driver
                    jdbcUrl: jdbc:mysql://127.0.0.1:3306/test1?useSSL=false
                    username: root
                    password: root!@#
              ",
            "rule":"
              rules:
              - !SHARDING
                tables:
                  order_info:
                    actualDataNodes: test$->{0..1}.order_info$->{0..15}
                    databaseStrategy:
                      standard:
                        shardingColumn: user_id
                        shardingAlgorithmName: t_order_db_algorith
                    logicTable: order_info
                    tableStrategy:
                      standard:
                        shardingColumn: user_id
                        shardingAlgorithmName: t_order_tbl_algorith
                shardingAlgorithms:
                  t_order_db_algorith:
                    type: INLINE
                    props:
                      algorithm-expression: test$->{(user_id/16).intValue()%2}
                  t_order_tbl_algorith:
                    type: INLINE
                    props:
                      algorithm-expression: order_info$->{user_id % 16}
              "
          }
      }
    },
    "jobConfiguration":{
      "concurrency":"6"
    }
  }'
```

## 第 16 课作业实践

### 2、（必做）基于hmily TCC或ShardingSphere的Atomikos XA实现一个简单的分布式 事务应用demo（二选一），提交到github。

* 基于hmily实现分布式事务

代码地址：[hmily-xa-demo](https://github.com/wkk1994/JAVA-000/blob/main/hmily-xa-demo)