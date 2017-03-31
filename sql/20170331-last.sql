/*
Navicat MySQL Data Transfer

Source Server         : ims
Source Server Version : 50554
Source Host           : localhost:3306
Source Database       : ims

Target Server Type    : MYSQL
Target Server Version : 50554
File Encoding         : 65001

Date: 2017-03-31 23:03:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`title`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`content`  mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=6

;

-- ----------------------------
-- Records of blog
-- ----------------------------
BEGIN;
INSERT INTO `blog` VALUES ('1', 'JFinal Demo Title here', 'JFinal Demo Content here'), ('2', 'test 1', 'test 1'), ('3', 'test 2', 'test 2'), ('4', 'test 3', 'test 3'), ('5', 'test 4', 'test 4');
COMMIT;

-- ----------------------------
-- Table structure for buy_in
-- ----------------------------
DROP TABLE IF EXISTS `buy_in`;
CREATE TABLE `buy_in` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`input_date`  varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL ,
`input_time`  varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL ,
`car_no`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`mao_heavy`  bigint(20) NULL DEFAULT NULL ,
`pi_heavy`  bigint(20) NULL DEFAULT NULL ,
`jin_heavy`  bigint(20) NULL DEFAULT NULL ,
`saler_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`buy_type`  int(11) NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=latin1 COLLATE=latin1_swedish_ci
AUTO_INCREMENT=11

;

-- ----------------------------
-- Records of buy_in
-- ----------------------------
BEGIN;
INSERT INTO `buy_in` VALUES ('1', null, null, '1234', null, null, null, 'wrrr', null), ('2', '2017-03-31', '12:03:50', '1234', null, null, null, '1234', null), ('3', '2017-03-31', '12:03:57', '1234', '1', '1', '1', 'ffgg', null), ('4', '2017-03-31', '17:03:05', '车1', '1', '1', '1', '1', '1'), ('5', '2017-03-31', '17:03:28', '车2', null, null, null, '66', '1'), ('6', '2017-03-31', '17:03:05', '车2', '12', '12', null, '212', '1'), ('10', '2017-03-31', '17:03:55', '车2', '2', '2', '2', '2', '2');
COMMIT;

-- ----------------------------
-- Table structure for s_dic
-- ----------------------------
DROP TABLE IF EXISTS `s_dic`;
CREATE TABLE `s_dic` (
`en`  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`ch`  varchar(225) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`op`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`demo`  varchar(225) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`op`, `en`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of s_dic
-- ----------------------------
BEGIN;
INSERT INTO `s_dic` VALUES ('1', '车1', 'CAR', '车号'), ('2', '车2', 'CAR', '车号'), ('1', '驾驶员1', 'DRIVER', '驾驶员'), ('2', '驾驶员2', 'DRIVER', '驾驶员'), ('1', '烧结圆孔配砖', 'PRD', '产品'), ('2', '烧结方孔配砖', 'PRD', '产品');
COMMIT;

-- ----------------------------
-- Table structure for s_resource
-- ----------------------------
DROP TABLE IF EXISTS `s_resource`;
CREATE TABLE `s_resource` (
`id`  bigint(225) NOT NULL AUTO_INCREMENT ,
`name`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`url`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`parent_id`  bigint(225) NULL DEFAULT NULL ,
`level`  int(11) NULL DEFAULT NULL ,
`order_id`  int(11) NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=9

;

-- ----------------------------
-- Records of s_resource
-- ----------------------------
BEGIN;
INSERT INTO `s_resource` VALUES ('1', '系统管理', '', '0', '1', '1'), ('2', '用户管理', 'user/', '1', '2', '1'), ('3', '角色管理', 'role/', '1', '2', '2'), ('4', '菜单管理', 'resource/', '1', '1', '3'), ('5', '出库管理', '', '0', '1', '2'), ('6', '出库记录', 'sale/tab', '5', '2', '1'), ('7', '入库管理', '', '0', '1', '3'), ('8', '入库记录', 'buy/tab', '7', '2', '1');
COMMIT;

-- ----------------------------
-- Table structure for s_role
-- ----------------------------
DROP TABLE IF EXISTS `s_role`;
CREATE TABLE `s_role` (
`id`  bigint(225) NOT NULL AUTO_INCREMENT ,
`name`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`descption`  varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`is_admin`  int(11) NOT NULL DEFAULT 2 ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=3

;

-- ----------------------------
-- Records of s_role
-- ----------------------------
BEGIN;
INSERT INTO `s_role` VALUES ('1', '系统管理员', '系统管理员', '1'), ('2', '测试', '测', '2');
COMMIT;

-- ----------------------------
-- Table structure for s_roleright
-- ----------------------------
DROP TABLE IF EXISTS `s_roleright`;
CREATE TABLE `s_roleright` (
`role_id`  bigint(225) NOT NULL ,
`resource_id`  bigint(225) NOT NULL ,
`op_flg`  int(11) NOT NULL ,
PRIMARY KEY (`role_id`, `resource_id`, `op_flg`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of s_roleright
-- ----------------------------
BEGIN;
INSERT INTO `s_roleright` VALUES ('1', '1', '0'), ('1', '2', '0'), ('1', '2', '1'), ('1', '2', '2'), ('1', '2', '3'), ('1', '3', '0'), ('1', '3', '1'), ('1', '3', '2'), ('1', '3', '3'), ('1', '4', '0'), ('1', '4', '1'), ('1', '4', '2'), ('1', '4', '3'), ('1', '5', '0'), ('1', '5', '1'), ('1', '5', '2'), ('1', '5', '3'), ('1', '6', '0'), ('1', '6', '1'), ('1', '6', '2'), ('1', '6', '3'), ('1', '7', '0'), ('1', '7', '1'), ('1', '7', '2'), ('1', '7', '3'), ('1', '8', '0'), ('1', '8', '1'), ('1', '8', '2'), ('1', '8', '3');
COMMIT;

-- ----------------------------
-- Table structure for s_roleuser
-- ----------------------------
DROP TABLE IF EXISTS `s_roleuser`;
CREATE TABLE `s_roleuser` (
`role_id`  bigint(255) NOT NULL ,
`user_id`  bigint(225) NOT NULL ,
PRIMARY KEY (`role_id`, `user_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of s_roleuser
-- ----------------------------
BEGIN;
INSERT INTO `s_roleuser` VALUES ('1', '1');
COMMIT;

-- ----------------------------
-- Table structure for s_user
-- ----------------------------
DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user` (
`id`  bigint(225) NOT NULL AUTO_INCREMENT ,
`name`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`login_name`  varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`login_passwd`  varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`img_src`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=2

;

-- ----------------------------
-- Records of s_user
-- ----------------------------
BEGIN;
INSERT INTO `s_user` VALUES ('1', 'admin', 'admin', '123456', '0010023956779853_b.jpg');
COMMIT;

-- ----------------------------
-- Table structure for sales_out
-- ----------------------------
DROP TABLE IF EXISTS `sales_out`;
CREATE TABLE `sales_out` (
`prd_name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`driver`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`car_no`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`count_num`  bigint(20) NULL DEFAULT NULL ,
`xu_count`  bigint(20) NULL DEFAULT NULL ,
`give_count`  bigint(20) NULL DEFAULT NULL ,
`buyer_name`  varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL ,
`input_date`  varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL ,
`input_time`  varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL ,
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`car_type`  int(11) NOT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=latin1 COLLATE=latin1_swedish_ci
AUTO_INCREMENT=6

;

-- ----------------------------
-- Records of sales_out
-- ----------------------------
BEGIN;
INSERT INTO `sales_out` VALUES ('3424', '242', null, '24', '24', null, '24111', '2017-03-31', '11:03:42', '2', '1'), ('烧结圆孔配砖', '驾驶员2', '车1', '12', '12', '12', '12', '2017-03-31', '16:03:06', '3', '1'), ('烧结方孔配砖', '驾驶员2', '车2', '11', '11', '12', '11', '2017-03-31', '16:03:51', '4', '2');
COMMIT;

-- ----------------------------
-- Auto increment value for blog
-- ----------------------------
ALTER TABLE `blog` AUTO_INCREMENT=6;

-- ----------------------------
-- Auto increment value for buy_in
-- ----------------------------
ALTER TABLE `buy_in` AUTO_INCREMENT=11;

-- ----------------------------
-- Auto increment value for s_resource
-- ----------------------------
ALTER TABLE `s_resource` AUTO_INCREMENT=9;

-- ----------------------------
-- Auto increment value for s_role
-- ----------------------------
ALTER TABLE `s_role` AUTO_INCREMENT=3;

-- ----------------------------
-- Auto increment value for s_user
-- ----------------------------
ALTER TABLE `s_user` AUTO_INCREMENT=2;

-- ----------------------------
-- Auto increment value for sales_out
-- ----------------------------
ALTER TABLE `sales_out` AUTO_INCREMENT=6;
SET FOREIGN_KEY_CHECKS=1;
