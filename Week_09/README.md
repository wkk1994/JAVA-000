# 学习笔记

## 第 17 课作业实践

### 3、（必做）改造自定义RPC的程序，提交到github： 

* 1）尝试将服务端写死查找接口实现类变成泛型和反射 
* 2）尝试将客户端动态代理改成AOP
  参考dubbo的BeanPostProcessor对注解Reference的处理，实现对RpcfxService注解属性的代理。
  
  [RpxfxAutoconfig.java](https://github.com/wkk1994/JAVA-000/blob/main/rpc01/rpcfx-core/src/main/java/io/kimmking/rpcfx/processor/RpxfxAutoconfig.java)
  [RpxfxBeanPostProcessor.java](https://github.com/wkk1994/JAVA-000/blob/main/rpc01/rpcfx-core/src/main/java/io/kimmking/rpcfx/processor/RpxfxBeanPostProcessor.java)
  [RpcfxCglib.java](https://github.com/wkk1994/JAVA-000/blob/main/rpc01/rpcfx-core/src/main/java/io/kimmking/rpcfx/client/RpcfxCglib.java)
  [QueryUserService.java](https://github.com/wkk1994/JAVA-000/blob/main/rpc01/rpcfx-demo-consumer/src/main/java/io/kimmking/rpcfx/demo/consumer/QueryUserService.java)
  
* 3）尝试使用Netty+HTTP作为client端传输方式

  [NettyHttpClient.java](https://github.com/wkk1994/JAVA-000/blob/main/rpc01/rpcfx-core/src/main/java/io/kimmking/rpcfx/client/http/NettyHttpClient.java)
  [NettyClientHandler.java](https://github.com/wkk1994/JAVA-000/blob/main/rpc01/rpcfx-core/src/main/java/io/kimmking/rpcfx/client/http/NettyClientHandler.java)
  
## Week09 作业题目（周六）：

### 3.（必做）结合 dubbo+hmily，实现一个 TCC 外汇交易处理，代码提交到 GitHub:

* 用户 A 的美元账户和人民币账户都在 A 库，使用 1 美元兑换 7 人民币 ;
* 用户 B 的美元账户和人民币账户都在 B 库，使用 7 人民币兑换 1 美元 ;
* 设计账户表，冻结资产表，实现上述两个本地事务的分布式事务。

步骤：

* 设计表结构

```sql

CREATE TABLE `user_account_info` (
`id` bigint(20) NOT NULL COMMENT '主键',
`user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
`rmb_amount` decimal(15,2) DEFAULT NULL COMMENT '人民币余额',
`dollar_amount` decimal(15,2) DEFAULT NULL COMMENT '美元余额',
`created_at` bigint(20) DEFAULT NULL COMMENT '创建时间',
`modified_at` bigint(20) DEFAULT NULL COMMENT '修改时间',
PRIMARY KEY (`id`),
KEY `user_id_index` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账户信息';

CREATE TABLE `user_account_amount_freeze_info` (
`id` bigint(20) NOT NULL COMMENT '主键',
`user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
`account_id` bigint(20) DEFAULT NULL COMMENT '账户id',
`freeze_rmb_amount` decimal(15,2) DEFAULT NULL COMMENT '冻结人民币余额',
`freeze_dollar_amount` decimal(15,2) DEFAULT NULL COMMENT '冻结美元余额',
`created_at` bigint(20) DEFAULT NULL COMMENT '创建时间',
`modified_at` bigint(20) DEFAULT NULL COMMENT '修改时间',
PRIMARY KEY (`id`),
KEY `user_id_index` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账户金额冻结信息';
```