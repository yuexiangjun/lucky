CREATE TABLE `admin_user` (
                              `id` bigint(20) NOT NULL,
                              `name` varchar(255) DEFAULT NULL COMMENT '姓名',
                              `phone` varchar(255) DEFAULT NULL COMMENT '手机号码',
                              `password` varchar(255) DEFAULT NULL COMMENT '密码',
                              `enabled` tinyint(1) DEFAULT '1' COMMENT '是否启用',
                              `last_login_time` datetime DEFAULT NULL COMMENT '最后一次登录时间',
                              `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `grade` (
                         `id` bigint(20) NOT NULL,
                         `type` int(10) DEFAULT NULL COMMENT '类别 1:隐藏 2：普通级别',
                         `name` varchar(255) DEFAULT NULL COMMENT '名称',
                         `sort` varchar(255) DEFAULT NULL COMMENT '排序',
                         `probability` decimal(10,4) DEFAULT NULL COMMENT '概率',
                         `status` tinyint(1) DEFAULT '1' COMMENT '是否启用',
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `order` (
                         `id` bigint(20) NOT NULL,
                         `wechat_user_id` bigint(20) DEFAULT NULL COMMENT '微信用户id',
                         `topic_id` bigint(20) DEFAULT NULL COMMENT '主题id',
                         `session_id` bigint(20) DEFAULT NULL COMMENT '场次id',
                         `status` int(10) DEFAULT NULL COMMENT '订单状态  0-待发货 1-待收货 2-完成',
                         `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                         `finish_time` datetime DEFAULT NULL COMMENT '完成时间',
                         `send_time` datetime DEFAULT NULL COMMENT '发货时间',
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `pay_order` (
                             `id` bigint(20) NOT NULL,
                             `pay_money` decimal(10,3) DEFAULT NULL COMMENT '支付金额',
                             `pay_status` int(10) DEFAULT NULL COMMENT '支付状态',
                             `wechat_user_id` bigint(20) DEFAULT NULL COMMENT '支付人',
                             `topic_id` bigint(20) DEFAULT NULL COMMENT '主题id',
                             `session_id` bigint(20) DEFAULT NULL COMMENT '场次id',
                             `times` int(10) DEFAULT NULL COMMENT '抽奖次数',
                             `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
                             `total_money` decimal(10,3) DEFAULT NULL COMMENT '总金额',
                             `complete_time` datetime DEFAULT NULL COMMENT '完成时间',
                             `third_pay_id` varchar(100) DEFAULT NULL COMMENT '三方支付id',
                             `prize_id` json DEFAULT NULL COMMENT '商品id',
                             `pay_params` text COMMENT '支付前端需要的参数',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `prize_info` (
                              `id` bigint(20) NOT NULL,
                              `type` int(10) DEFAULT NULL COMMENT '类别 1:隐藏 2：普通级别',
                              `topic_id` bigint(20) DEFAULT NULL COMMENT '主题id',
                              `grade_id` bigint(20) DEFAULT NULL COMMENT '奖品等级',
                              `prize_name` varchar(255) DEFAULT NULL COMMENT '奖品名称',
                              `prize_url` varchar(500) DEFAULT NULL COMMENT '奖品图片',
                              `inventory` int(100) DEFAULT NULL COMMENT '库存',
                               `price` decimal(10,2) DEFAULT NULL COMMENT '价格（多少钱一抽）',

                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `series_topic` (
                                `id` bigint(20) NOT NULL,
                                `name` varchar(255) DEFAULT NULL COMMENT '名称',
                                `topic_color` varchar(255) DEFAULT NULL COMMENT '主题颜色',
                                `session` int(100) DEFAULT NULL COMMENT '场次(一共多少场)',
                                `topic_url` varchar(500) DEFAULT NULL COMMENT '主题图片',
                                `status` tinyint(1) DEFAULT '0' COMMENT '是否启用',
                                `price` decimal(10,2) DEFAULT NULL COMMENT '价格（多少钱一抽）',
                                `grade_id` json DEFAULT NULL COMMENT '奖项',
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `session_info` (
                                `id` bigint(20) NOT NULL,
                                `topic_id` bigint(20) DEFAULT NULL COMMENT '主题id',
                                `status` int(10) DEFAULT NULL COMMENT '状态 0：禁用 1：启用 2：结束',
                                `session_number` int(100) DEFAULT NULL COMMENT '场次编号',
                                `prize_inventory` json DEFAULT NULL COMMENT '库存',
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `wechat_user` (
                               `id` bigint(20) NOT NULL,
                               `avatar` varchar(255) DEFAULT NULL COMMENT '头像地址',
                               `openid` varchar(255) DEFAULT NULL COMMENT 'openid',
                               `name` varchar(255) DEFAULT NULL COMMENT '姓名',
                               `phone` varchar(255) DEFAULT NULL COMMENT '手机号码',
                               `enabled` tinyint(1) DEFAULT '1' COMMENT '是否启用',
                               `last_login_time` datetime DEFAULT NULL COMMENT '最后一次登录时间',
                               `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;