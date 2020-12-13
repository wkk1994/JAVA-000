drop schema if exists xatest;

create schema xatest;

drop table if exists xatest.`account_info`;
drop table if exists xatest.`goods_info`;
drop table if exists xatest.`order_info`;


CREATE TABLE xatest.`account_info` (
`id` bigint(20) NOT NULL COMMENT '主键',
`user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
`amount` decimal(15,2) DEFAULT NULL COMMENT '账户余额',
`freeze_amount` decimal(15,2) NULL COMMENT '冻结金额，扣款暂存余额',
`created_at` bigint(20) DEFAULT NULL COMMENT '创建时间',
`modified_at` bigint(20) DEFAULT NULL COMMENT '修改时间',
PRIMARY KEY (`id`),
KEY `user_id_index` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账户信息';


CREATE TABLE xatest.`goods_info` (
`id` bigint NOT NULL  COMMENT '主键',
`goods_name` varchar(255) NULL  COMMENT '商品名称',
`goods_price` decimal(12, 2) NULL  COMMENT '商品价格（元）',
`goods_inventory` decimal(12, 0) NULL  COMMENT '商品库存',
`freeze_inventory` decimal(12, 0) NULL  COMMENT '冻结商品库存',
`created_at` bigint NULL  COMMENT '创建时间',
`modified_at` bigint NULL  COMMENT '修改时间',
PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '商品信息表';

CREATE TABLE xatest.`order_info` (
`id` bigint NOT NULL AUTO_INCREMENT  COMMENT '主键',
`user_id` bigint NULL  COMMENT '用户id',
`goods_id` bigint NULL  COMMENT '商品id',
`goods_num` bigint NULL  COMMENT '商品数量',
`amount` decimal(15, 2) NULL  COMMENT '订单金额（元）',
`order_time` bigint NULL  COMMENT '下单时间',
`created_at` bigint NULL  COMMENT '创建时间',
`modified_at` bigint NULL  COMMENT '修改时间',
PRIMARY KEY (`id`), KEY user_id_index(`user_id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '订单信息表';

insert into xatest.account_info(id, user_id, amount,freeze_amount, created_at) values(1,1001,8000,0,now()),(2,1002,9000,0,now()),(3,1003,10000,0,now());

insert into xatest.goods_info(id, goods_name, goods_price, goods_inventory,freeze_inventory, created_at) values (2001, '商品1', 100, 1000,0, now()),(2002, '商品2', 200, 1000,0, now()),(2003, '商品3', 300, 1000,0, now());
