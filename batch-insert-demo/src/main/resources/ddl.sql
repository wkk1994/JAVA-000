CREATE TABLE `user_info` (
                             `id` bigint NOT NULL  COMMENT '主键',
                             `user_name` char(50) NULL  COMMENT '姓名',
                             `user_sex` tinyint NULL  COMMENT '性别0 男 1 女',
                             `user_mobile` char(20) NULL  COMMENT '手机号',
                             `user_email` varchar(50) NULL  COMMENT '电子邮箱',
                             `created_at` bigint NULL  COMMENT '创建时间',
                             `modified_at` bigint NULL  COMMENT '修改时间',
                             PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '用户信息表';

CREATE TABLE `goods_info` (
                              `id` bigint NOT NULL  COMMENT '主键',
                              `goods_name` varchar(255) NULL  COMMENT '商品名称',
                              `goods_price` decimal(12, 2) NULL  COMMENT '商品价格（元）',
                              `goods_inventory` decimal(12, 0) NULL  COMMENT '商品库存',
                              `created_at` bigint NULL  COMMENT '创建时间',
                              `modified_at` bigint NULL  COMMENT '修改时间',
                              PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '商品信息表';

CREATE TABLE `order_info` (
                              `id` bigint NOT NULL  COMMENT '主键',
                              `user_id` bigint NULL  COMMENT '用户id',
                              `goods_id` bigint NULL  COMMENT '商品id',
                              `goods_num` bigint NULL  COMMENT '商品数量',
                              `amount` decimal(15, 2) NULL  COMMENT '订单金额（元）',
                              `order_time` bigint NULL  COMMENT '下单时间',
                              `created_at` bigint NULL  COMMENT '创建时间',
                              `modified_at` bigint NULL  COMMENT '修改时间',
                              PRIMARY KEY (`id`), KEY user_id_index(`user_id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '订单信息表';