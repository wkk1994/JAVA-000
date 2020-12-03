# 学习笔记

## Week07 作业题目（周四）

### 2.（必做）按自己设计的表结构，插入 100 万订单模拟数据，测试不同方式的插入效率 

* 使用jdbc拼接inser into的方式实现批量插入，用时23s。
  [JDBCBatchInsertDemo.java]()

* 使用PreparedStatement#addBatch的方式实现批量插入，用时1640s（27分钟）。
  [PreparedStatementBatchInsertDemo.java]()

* 使用jdbcTemplate#batchUpdate的方式实现批量插入，用时1478s（25分钟）
  [JdbcTemplateBatchInsertDemo.java]()

## Week07 作业题目（周六）

### 1.（选做）配置一遍异步复制，半同步复制、组复制

异步复制配置

* 创建网络：`docker network create mysql_net`
* 启动2个mysql容器，相关docker-compose.yml如下

  ```yml
  version: "3.1"
  services:
      mysql:
          image: mysql:5.7.22
          #restart: always
          ports:
             - "3307:3306"
          volumes:
             - $PWD/data:/var/lib/mysql
             - $PWD/logs:/logs
             - $PWD/conf:/etc/mysql/conf.d
          environment:
             - MYSQL_ROOT_PASSWORD=root!@#
          networks:
             mysql_net:
                 aliases:
                     - mysql1
          expose:
             - "3306"
  networks:
     mysql_net:
        external:
           name: "mysql_net"  
  ```
  
  ```yml
  version: "3.1"
  services:
      mysql:
          image: mysql:5.7.22
          #restart: always
          ports:
             - "3308:3306"
          volumes:
             - $PWD/data:/var/lib/mysql
             - $PWD/logs:/logs
             - $PWD/conf:/etc/mysql/conf.d
          environment:
             - MYSQL_ROOT_PASSWORD=root!@#
          networks:
             mysql_net:
                 aliases:
                     - mysql2
          expose:
             - "3306"
  networks:
     mysql_net:
        external:
           name: "mysql_net"  
  ```

* 在主节点mysql1上创建用户

  ```text
  mysql> CREATE USER 'repl'@'%' IDENTIFIED BY '123456';
  Query OK, 0 rows affected (0.03 sec)

  mysql> GRANT REPLICATION SLAVE ON *.* TO 'repl'@'%';
  Query OK, 0 rows affected (0.00 sec)
  ```

* 配置从节点mysql2上配置同步复制

  ```text
  mysql> CHANGE MASTER TO MASTER_HOST='mysql1', MASTER_PORT = 3306, MASTER_USER='repl', MASTER_PASSWORD='123456', MASTER_LOG_FILE='mysql-bin.000003', MASTER_LOG_POS=0;
  Query OK, 0 rows affected, 1 warning (0.04 sec)

  mysql> start slave;
  Query OK, 0 rows affected (0.01 sec)
  ```

半同步复制

* 创建网络：`docker network create mysql_semisync`
* 启动2个mysql容器，相关docker-compose.yml如下

  ```yml
  version: "3.1"
  services:
      mysql:
          image: mysql:5.7.22
          #restart: always
          ports:
             - "3309:3306"
          volumes:
             - $PWD/data:/var/lib/mysql
             - $PWD/logs:/logs
             - $PWD/conf:/etc/mysql/conf.d
          environment:
             - MYSQL_ROOT_PASSWORD=root!@#
          networks:
             mysql_semisync:
                 aliases:
                     - mysql1
          expose:
             - "3306"
  networks:
     mysql_semisync:
        external:
           name: "mysql_semisync"  
  ```
  
  ```yml
  version: "3.1"
  services:
      mysql:
          image: mysql:5.7.22
          #restart: always
          ports:
             - "3310:3306"
          volumes:
             - $PWD/data:/var/lib/mysql
             - $PWD/logs:/logs
             - $PWD/conf:/etc/mysql/conf.d
          environment:
             - MYSQL_ROOT_PASSWORD=root!@#
          networks:
             mysql_semisync:
                 aliases:
                     - mysql2
          expose:
             - "3306"
  networks:
     mysql_semisync:
        external:
           name: "mysql_semisync"  
  ```

  my.cnf配置
  主节点：

  ```text
  [mysqld]
  server_id = 1
  sql_mode=NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES 
  log_bin=mysql-bin
  binlog-format=Row
  plugin-load="rpl_semi_sync_master=semisync_master.so"
  rpl_semi_sync_master_timeout=1000 # 1 second
  rpl_semi_sync_master_enabled=1
  ```
  
  从节点：

  ```text
  [mysqld]
  server_id = 2
  sql_mode=NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES 
  log_bin=mysql-bin
  binlog-format=Row
  plugin-load="rpl_semi_sync_slave=semisync_slave.so"
  rpl_semi_sync_slave_enabled=1
  ```

* 在主节点mysql1上创建用户

  ```text
  mysql> CREATE USER 'repl'@'%' IDENTIFIED BY '123456';
  Query OK, 0 rows affected (0.03 sec)

  mysql> GRANT REPLICATION SLAVE ON *.* TO 'repl'@'%';
  Query OK, 0 rows affected (0.00 sec)
  ```

* 配置从节点mysql2上配置同步复制

  ```text
  mysql> CHANGE MASTER TO MASTER_HOST='mysql1', MASTER_PORT = 3306, MASTER_USER='repl', MASTER_PASSWORD='123456', MASTER_LOG_FILE='mysql-bin.000003', MASTER_LOG_POS=0;
  Query OK, 0 rows affected, 1 warning (0.04 sec)

  mysql> start slave;
  Query OK, 0 rows affected (0.01 sec)
  ```

组复制

### 2.（必做）读写分离 - 动态切换数据源版本 1.0

* 配置多个数据源，根据操作的不同选择不同的数据源。
  实现方式：通过定义多个jdbcTemplate实现多数据源操作，读操作使用slaveJdbcTemplate,写操作使用masterJdbcTemplate。
  主要代码：[MultiDataSourceDemo.java]() [UserInfoDao.java]()

* 基于AbstractRoutingDataSource和自定义注解简化数据源切换
  主要代码：[DynamicDataSource.java]() [DynamicDataSourceConfig.java]() [DataSourceAspect.java]()

* 改进二下1.2：支持配置多个从库，并使用随机算法获取从库
  主要代码：[DynamicDataSource.java]() [SlaveDataSource.java]() [DynamicDataSourceConfig.java]() [DataSourceAspect.java]()

* 改进三下1.3：支持多个从库的负载均衡
  未实现。。。

### 3.（必做）读写分离 - 数据库框架版本 2.0
  通过shardingsphere-jdbc方式实现多数据源动态切换.
  
  依赖版本：
  
  ```xml
  <dependency>
    <groupId>org.apache.shardingsphere</groupId>
    <artifactId>shardingsphere-jdbc-core-spring-boot-starter</artifactId>
    <version>5.0.0-alpha</version>
  </dependency>
  ```

  配置文件:

  ```yml
  spring:
  shardingsphere:
    datasource:
      common:
        type: com.zaxxer.hikari.HikariDataSource
        driver-class-name: com.mysql.jdbc.Driver
      names: master1,slave1,slave2
      master1:
        type: com.zaxxer.hikari.HikariDataSource
        driver-class-name: com.mysql.jdbc.Driver
        jdbc-url: jdbc:mysql://127.0.0.1:3309/test?characterEncoding=UTF-8&useSSL=false
        username: root
        password: root!@#
      slave1:
        type: com.zaxxer.hikari.HikariDataSource
        driver-class-name: com.mysql.jdbc.Driver
        jdbc-url: jdbc:mysql://127.0.0.1:3308/test?characterEncoding=UTF-8&useSSL=false
        username: root
        password: root!@#
      slave2:
        type: com.zaxxer.hikari.HikariDataSource
        driver-class-name: com.mysql.jdbc.Driver
        jdbc-url: jdbc:mysql://127.0.0.1:3310/test?characterEncoding=UTF-8&useSSL=false
        username: root
        password: root!@#
    rules:
      replica-query:
        data-sources:
          ds_ms:
            name: ds_ms
            primary-data-source-name: master1
            replica-data-source-names: slave1,slave2
            load-balancer-name: round
        load-balancers:
          round:
            type: ROUND_ROBIN
            props:
              test: test
  ```