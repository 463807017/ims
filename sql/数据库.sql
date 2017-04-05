/*
Navicat MySQL Data Transfer

Source Server         : ims
Source Server Version : 50554
Source Host           : localhost:3306
Source Database       : ims

Target Server Type    : MYSQL
Target Server Version : 50554
File Encoding         : 65001

Date: 2017-04-05 22:53:02
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
`mao_heavy`  decimal(20,4) NULL DEFAULT NULL ,
`pi_heavy`  decimal(20,4) NULL DEFAULT NULL ,
`jin_heavy`  decimal(20,4) NULL DEFAULT NULL ,
`saler_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`buy_type`  int(11) NULL DEFAULT NULL ,
`driver`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=latin1 COLLATE=latin1_swedish_ci
AUTO_INCREMENT=21

;

-- ----------------------------
-- Records of buy_in
-- ----------------------------
BEGIN;
INSERT INTO `buy_in` VALUES ('1', null, null, '1234', null, null, null, 'wrrr', null, null), ('2', '2017-03-31', '12:03:50', '1234', null, null, null, '1234', null, null), ('3', '2017-03-31', '12:03:57', '1234', '1.0000', '1.0000', '1.0000', 'ffgg', null, null), ('4', '2017-03-31', '17:03:05', '车1', '1.0000', '1.0000', '1.0000', '1', '1', null), ('5', '2017-03-31', '17:03:28', '车2', null, null, null, '66', '1', null), ('6', '2017-03-31', '17:03:05', '车2', '12.0000', '12.0000', null, '212', '1', null), ('10', '2017-03-31', '17:03:55', '车2', '2.0000', '2.0000', '2.0000', '2', '2', null), ('11', '2017-04-02', '10:04:16', '车2', '1.0000', '1.0000', '1.0000', '12', '1', null), ('12', '2017-04-03', '19:04:59', '1,2222', '1.0000', '1.0000', '1.0000', '1', '1', null), ('13', '2017-04-03', '19:04:21', ' ', '11.0000', '11.0000', '11.0000', '11', '1', null), ('14', '2017-04-03', '21:04:30', 'ddd,111,大大方方,的方法大大大', '11.0000', '11.0000', '1.0000', '1', '1', null), ('15', '2017-04-04', '15:04:09', '车2', '12.0000', '12.0000', '12.0000', '12', '1', null), ('16', '2017-04-04', '16:04:34', '车1', '1.0000', '1.0000', '1.0000', '出货方2', '1', null), ('17', '2017-04-04', '17:04:45', '车1', '1.0000', '1.0000', '1.0000', '测试', '1', null), ('18', '2017-04-05', '19:04:45', '车2', '12.1000', '1.2340', '10.8660', '出货方2', '1', null), ('19', '2017-04-05', '21:04:24', '车2', '12.0000', '1.1000', '10.9000', '出货方3', '1', '驾驶员2'), ('20', '2017-04-05', '21:04:17', '车2', '12.0000', '0.0000', '12.0000', '出货方3', '2', '驾驶员2');
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
INSERT INTO `s_dic` VALUES ('1', '购货方1', 'BUYER', '	购货方'), ('2', '购货方2', 'BUYER', '	购货方'), ('1', '车1', 'CAR', '车号'), ('2', '车2', 'CAR', '车号'), ('1', '驾驶员1', 'DRIVER', '驾驶员'), ('2', '驾驶员2', 'DRIVER', '驾驶员'), ('BUYER', '购货方', 'OP_TYPE', 'op对应的描述信息'), ('CAR', '车号', 'OP_TYPE', 'op对应的描述信息'), ('DRIVER', '驾驶员', 'OP_TYPE', 'op对应的描述信息'), ('PRD', '产品', 'OP_TYPE', 'op对应的描述信息'), ('SALER', '出货方', 'OP_TYPE', 'op对应的描述信息'), ('1', '烧结圆孔配砖', 'PRD', '产品'), ('2', '烧结方孔配砖', 'PRD', '产品'), ('3', '测试2', 'PRD', '产品信息'), ('5', '7', 'PRD', '	产品'), ('0', '查询', 'RIGHT', '权限'), ('1', '新增', 'RIGHT', '权限'), ('2', '修改', 'RIGHT', '权限'), ('3', '删除', 'RIGHT', '权限'), ('1', '出货方1', 'SALER', '	出货方'), ('2', '出货方2', 'SALER', '	出货方'), ('3', '出货方3', 'SALER', '	出货方');
COMMIT;

-- ----------------------------
-- Table structure for s_excel
-- ----------------------------
DROP TABLE IF EXISTS `s_excel`;
CREATE TABLE `s_excel` (
`id`  int(11) NOT NULL ,
`name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`title`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`sheet_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`sql`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of s_excel
-- ----------------------------
BEGIN;
INSERT INTO `s_excel` VALUES ('1', '入库记录', '日期,时间,产品,车号,驾驶员,数量,虚数,购货方,交货数量,厂车', '入库记录', 'select input_date,input_time,prd_name,car_no,driver,count_num,xu_count,buyer_name,give_count,if(car_type=1,\'是\',\'否\') from sales_out where left(input_date,7)=date_format(now(),\'%Y-%m\')'), ('2', '出库记录', '日期,时间,车号,毛重,皮重,净重,出货方,类型', '出库记录', 'select input_date,input_time,car_no,mao_heavy,pi_heavy,jin_heavy,saler_name,if(buy_type=1,\' 煤炭\',\'页岩\') from buy_in where left(input_date,7)=date_format(now(),\'%Y-%m\')');
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
AUTO_INCREMENT=15

;

-- ----------------------------
-- Records of s_resource
-- ----------------------------
BEGIN;
INSERT INTO `s_resource` VALUES ('1', '系统管理', '', '0', '1', '1'), ('2', '用户管理', 'user/', '1', '2', '1'), ('3', '角色管理', 'role/', '1', '2', '2'), ('4', '菜单管理', 'resource/', '1', '1', '3'), ('5', '出库管理', '', '0', '1', '2'), ('6', '出库记录', 'sale/tab', '5', '2', '1'), ('7', '入库管理', '', '0', '1', '3'), ('8', '入库记录', 'buy/tab', '7', '2', '1'), ('9', '常用信息设置', '', '0', '1', '4'), ('10', '车辆信息', 'sdic?sDic.op=CAR', '9', '2', '1'), ('11', '驾驶员信息', 'sdic?sDic.op=DRIVER', '9', '2', '2'), ('12', '产品信息', 'sdic?sDic.op=PRD', '9', '2', '3'), ('13', '购货方', 'sdic?sDic.op=BUYER', '9', '2', '4'), ('14', '出货方', 'sdic?sDic.op=SALER', '9', '2', '5');
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
INSERT INTO `s_roleright` VALUES ('1', '2', '0'), ('1', '2', '1'), ('1', '2', '2'), ('2', '1', '0'), ('2', '1', '1'), ('2', '1', '3'), ('2', '2', '0'), ('2', '2', '1'), ('2', '2', '2'), ('2', '2', '3'), ('2', '5', '0'), ('2', '6', '0'), ('2', '6', '1'), ('2', '6', '2'), ('2', '7', '0'), ('2', '7', '2'), ('2', '8', '0'), ('2', '8', '1'), ('2', '8', '2'), ('2', '8', '3');
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
INSERT INTO `s_roleuser` VALUES ('1', '1'), ('2', '2');
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
`input_time`  varchar(19) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=6

;

-- ----------------------------
-- Records of s_user
-- ----------------------------
BEGIN;
INSERT INTO `s_user` VALUES ('1', '系统管理员', 'admin', '123456', '2017-04-05 22:39:21'), ('2', '测试', 'test123', '123456', '2017-04-05 22:39:21'), ('3', 'tetet', 'tetet', 'tetete', '2017-04-05 22:39:21'), ('4', '2314', '2342542', '543535', '2017-04-05 22:39:21'), ('5', '13124235', '34654646', '64674757', '2017-04-05 22:39:21');
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
`buyer_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`input_date`  varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL ,
`input_time`  varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL ,
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`car_type`  int(11) NOT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=latin1 COLLATE=latin1_swedish_ci
AUTO_INCREMENT=12

;

-- ----------------------------
-- Records of sales_out
-- ----------------------------
BEGIN;
INSERT INTO `sales_out` VALUES ('', '242', '', '24', '24', null, '24111', '2017-03-31', '11:03:42', '2', '1'), ('烧结圆孔配砖', '驾驶员2', '车1', '12', '12', '12', '12', '2017-03-31', '16:03:06', '3', '1'), ('烧结方孔配砖', '驾驶员2', '车2', '11', '11', '12', '22', '2017-03-31', '16:03:51', '4', '2'), ('烧结方孔配砖', '驾驶员1', '车1', '1', '1', '1', '1', '2017-04-04', '15:04:46', '6', '1'), (null, null, null, '1', '1', '1', '购货方2', '2017-04-04', '16:04:01', '7', '1'), (null, null, null, '1', '1', '1', '购货方1', '2017-04-04', '16:04:47', '8', '1'), (null, null, null, '1', '1', '1', '购货方1', '2017-04-04', '16:04:19', '9', '1'), (null, null, null, '1', '1', '2', null, '2017-04-04', '16:04:03', '10', '1'), ('测试2', '驾驶员1', '车1', '1', '1', '1', '购货方1', '2017-04-04', '16:04:05', '11', '1');
COMMIT;

-- ----------------------------
-- Auto increment value for blog
-- ----------------------------
ALTER TABLE `blog` AUTO_INCREMENT=6;

-- ----------------------------
-- Auto increment value for buy_in
-- ----------------------------
ALTER TABLE `buy_in` AUTO_INCREMENT=21;

-- ----------------------------
-- Auto increment value for s_resource
-- ----------------------------
ALTER TABLE `s_resource` AUTO_INCREMENT=15;

-- ----------------------------
-- Auto increment value for s_role
-- ----------------------------
ALTER TABLE `s_role` AUTO_INCREMENT=3;

-- ----------------------------
-- Auto increment value for s_user
-- ----------------------------
ALTER TABLE `s_user` AUTO_INCREMENT=6;

-- ----------------------------
-- Auto increment value for sales_out
-- ----------------------------
ALTER TABLE `sales_out` AUTO_INCREMENT=12;
SET FOREIGN_KEY_CHECKS=1;
