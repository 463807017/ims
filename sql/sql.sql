/*
Navicat MySQL Data Transfer

Source Server         : ims
Source Server Version : 50554
Source Host           : localhost:3306
Source Database       : ims

Target Server Type    : MYSQL
Target Server Version : 50599
File Encoding         : 65001

Date: 2017-03-30 00:18:13
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
AUTO_INCREMENT=8

;

-- ----------------------------
-- Records of s_resource
-- ----------------------------
BEGIN;
INSERT INTO `s_resource` VALUES ('1', '系统管理', '', '0', '1', '1'), ('2', '用户管理', 'user/', '1', '2', '1'), ('3', '角色管理', 'role/', '1', '2', '2');
COMMIT;

-- ----------------------------
-- Table structure for s_role
-- ----------------------------
DROP TABLE IF EXISTS `s_role`;
CREATE TABLE `s_role` (
`id`  bigint(225) NOT NULL AUTO_INCREMENT ,
`name`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`descption`  varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=2

;

-- ----------------------------
-- Records of s_role
-- ----------------------------
BEGIN;
INSERT INTO `s_role` VALUES ('1', '系统管理员', '系统管理员');
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
INSERT INTO `s_roleright` VALUES ('1', '1', '0'), ('1', '2', '0'), ('1', '2', '1'), ('1', '2', '2'), ('1', '2', '3'), ('1', '3', '0'), ('1', '3', '1'), ('1', '3', '2'), ('1', '3', '3');
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
INSERT INTO `s_user` VALUES ('1', 'admin', 'admin', '123456');
COMMIT;

-- ----------------------------
-- Auto increment value for blog
-- ----------------------------
ALTER TABLE `blog` AUTO_INCREMENT=6;

-- ----------------------------
-- Auto increment value for s_resource
-- ----------------------------
ALTER TABLE `s_resource` AUTO_INCREMENT=8;

-- ----------------------------
-- Auto increment value for s_role
-- ----------------------------
ALTER TABLE `s_role` AUTO_INCREMENT=2;

-- ----------------------------
-- Auto increment value for s_user
-- ----------------------------
ALTER TABLE `s_user` AUTO_INCREMENT=2;
SET FOREIGN_KEY_CHECKS=1;
