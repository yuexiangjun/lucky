

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user`  (
  `id` bigint(0) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `phone` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '密码',
  `enabled` tinyint(1) NULL DEFAULT 1 COMMENT '是否启用',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '最后一次登录时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '后台用户' ;

-- ----------------------------
-- Table structure for customer_info
-- ----------------------------
DROP TABLE IF EXISTS `customer_info`;
CREATE TABLE `customer_info`  (
  `id` bigint(0) NOT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客服电话',
  `wechat` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客服微信',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客服名称',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '客服' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for delivery_address
-- ----------------------------
DROP TABLE IF EXISTS `delivery_address`;
CREATE TABLE `delivery_address`  (
  `id` bigint(0) NOT NULL,
  `wechat_user_id` bigint(0) NULL DEFAULT NULL COMMENT '微信用户id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收货人名',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收货人手机号',
  `province` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '省',
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '市',
  `area` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '区',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收货人地址',
  `is_default` tinyint(1) NULL DEFAULT 0 COMMENT '是否默认',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '收货地址' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade`  (
  `id` bigint(0) NOT NULL,
  `type` int(0) NULL DEFAULT NULL COMMENT '类别 1:隐藏 2：普通级别',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '名称',
  `sort` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '排序',
  `probability` decimal(10, 4) NULL DEFAULT NULL COMMENT '概率',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '是否启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '奖项等级' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for logistics_order
-- ----------------------------
DROP TABLE IF EXISTS `logistics_order`;
CREATE TABLE `logistics_order`  (
  `id` bigint(0) NOT NULL,
  `wechat_user_id` bigint(0) NULL DEFAULT NULL COMMENT '微信用户id',
  `number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '编号',
  `logistics_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '快递单号',
  `logistics_company` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '快递公司',
  `status` int(0) NULL DEFAULT 1 COMMENT '订单状态 1：待发送 2：已完成',
  `address` json NULL COMMENT '物流地址',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '物流订单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for logistics_order_prize
-- ----------------------------
DROP TABLE IF EXISTS `logistics_order_prize`;
CREATE TABLE `logistics_order_prize`  (
  `id` bigint(0) NOT NULL,
  `product_id` bigint(0) NULL DEFAULT NULL COMMENT '商品id',
  `order_prize_id` bigint(0) NULL DEFAULT NULL COMMENT '订单商品id',
  `logistics_order_id` bigint(0) NULL DEFAULT NULL COMMENT '物流订单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '物流商品' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` bigint(0) NOT NULL,
  `wechat_user_id` bigint(0) NULL DEFAULT NULL COMMENT '微信用户id',
  `topic_id` bigint(0) NULL DEFAULT NULL COMMENT '主题id',
  `session_id` bigint(0) NULL DEFAULT NULL COMMENT '场次id',
  `status` int(0) NULL DEFAULT NULL COMMENT '订单状态  0-待发货 1-待收货 2-完成',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `finish_time` datetime(0) NULL DEFAULT NULL COMMENT '完成时间',
  `send_time` datetime(0) NULL DEFAULT NULL COMMENT '发货时间',
  `pay_order_id` bigint(0) NULL DEFAULT NULL COMMENT '支付订单id',
  `product_id` bigint(0) NULL DEFAULT NULL COMMENT '商品id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '订单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for order_prize
-- ----------------------------
DROP TABLE IF EXISTS `order_prize`;
CREATE TABLE `order_prize`  (
  `id` bigint(0) NOT NULL,
  `wechat_user_id` bigint(0) NULL DEFAULT NULL COMMENT '微信用户id',
  `order_id` bigint(0) NULL DEFAULT NULL COMMENT '订单id',
  `product_id` bigint(0) NULL DEFAULT NULL COMMENT '商品id',
  `session_id` bigint(0) NULL DEFAULT NULL COMMENT '场次id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `is_delivery` tinyint(1) NULL DEFAULT 0 COMMENT '是否发货',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '订单商品' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pay_order
-- ----------------------------
DROP TABLE IF EXISTS `pay_order`;
CREATE TABLE `pay_order`  (
  `id` bigint(0) NOT NULL,
  `pay_money` decimal(10, 3) NULL DEFAULT NULL COMMENT '支付金额',
  `pay_status` int(0) NULL DEFAULT NULL COMMENT '支付状态',
  `wechat_user_id` bigint(0) NULL DEFAULT NULL COMMENT '支付人',
  `topic_id` bigint(0) NULL DEFAULT NULL COMMENT '主题id',
  `session_id` bigint(0) NULL DEFAULT NULL COMMENT '场次id',
  `times` int(0) NULL DEFAULT NULL COMMENT '抽奖次数',
  `pay_time` datetime(0) NULL DEFAULT NULL COMMENT '支付时间',
  `total_money` decimal(10, 3) NULL DEFAULT NULL COMMENT '总金额',
  `complete_time` datetime(0) NULL DEFAULT NULL COMMENT '完成时间',
  `third_pay_id` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '三方支付id',
  `prize_id` json NULL COMMENT '商品id',
  `pay_params` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '支付前端需要的参数',
  `pay_type` int(0) NULL DEFAULT NULL COMMENT '支付类型 1 微信支付 2：平台积分支付',
  `order_type` int(0) NULL DEFAULT NULL COMMENT '订单类型 1 抽奖订单 2：充值订单',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '支付订单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for prize_info
-- ----------------------------
DROP TABLE IF EXISTS `prize_info`;
CREATE TABLE `prize_info`  (
  `id` bigint(0) NOT NULL,
  `type` int(0) NULL DEFAULT NULL COMMENT '类别 1:隐藏 2：普通级别',
  `topic_id` bigint(0) NULL DEFAULT NULL COMMENT '主题id',
  `grade_id` bigint(0) NULL DEFAULT NULL COMMENT '奖品等级',
  `prize_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '奖品名称',
  `prize_url` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '奖品图片',
  `inventory` int(0) NULL DEFAULT NULL COMMENT '库存',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格（多少钱一抽）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '商品详情' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for series_topic
-- ----------------------------
DROP TABLE IF EXISTS `series_topic`;
CREATE TABLE `series_topic`  (
  `id` bigint(0) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '名称',
  `topic_color` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '主题颜色',
  `session` int(0) NULL DEFAULT NULL COMMENT '场次(一共多少场)',
  `topic_url` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '主题图片',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '是否启用',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格（多少钱一抽）',
  `grade_id` json NULL COMMENT '奖项',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '商品系列' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for session_info
-- ----------------------------
DROP TABLE IF EXISTS `session_info`;
CREATE TABLE `session_info`  (
  `id` bigint(0) NOT NULL,
  `topic_id` bigint(0) NULL DEFAULT NULL COMMENT '主题id',
  `status` int(0) NULL DEFAULT NULL COMMENT '状态 0：禁用 1：启用 2：结束',
  `session_number` int(0) NULL DEFAULT NULL COMMENT '场次编号',
  `prize_inventory` json NULL COMMENT '库存',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '场次' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wechat_user
-- ----------------------------
DROP TABLE IF EXISTS `wechat_user`;
CREATE TABLE `wechat_user`  (
  `id` bigint(0) NOT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  `openid` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT 'openid',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `phone` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `enabled` tinyint(1) NULL DEFAULT 1 COMMENT '是否启用',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '最后一次登录时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `balance` decimal(10, 3) NULL DEFAULT NULL COMMENT '账户余额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '小程序用户' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
