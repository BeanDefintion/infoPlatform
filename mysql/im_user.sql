/*
Navicat MySQL Data Transfer

Source Server         : Mysql8本地
Source Server Version : 80013
Source Host           : localhost:3312
Source Database       : im_user

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2019-08-19 10:18:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_permit
-- ----------------------------
DROP TABLE IF EXISTS `tb_permit`;
CREATE TABLE `tb_permit` (
  `permit_id` int(11) NOT NULL AUTO_INCREMENT,
  `permit_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `crt_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `upd_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`permit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of tb_permit
-- ----------------------------

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `crt_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `upd_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of tb_role
-- ----------------------------

-- ----------------------------
-- Table structure for tb_role_permit
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_permit`;
CREATE TABLE `tb_role_permit` (
  `role_permit_id` int(11) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  `permit_id` int(11) DEFAULT NULL,
  `crt_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `upd_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`role_permit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of tb_role_permit
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `register_type` int(2) DEFAULT NULL COMMENT '注册类型 0是web 1是小程序',
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号码',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '密码',
  `nick_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '昵称',
  `openid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '微信标志',
  `login_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '登陆名称',
  `sex` int(2) DEFAULT NULL COMMENT '性别 0未知 1男 2女',
  `avatar` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'E-Mail',
  `last_login_date` datetime DEFAULT NULL COMMENT '最后登陆日期',
  `fans_count` int(20) DEFAULT '0' COMMENT '粉丝数',
  `follow_count` int(20) DEFAULT '0' COMMENT '关注数',
  `crt_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `upd_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('2', '0', null, '4e0d390a27d4e6eaa6ced3e2a2bead4aed9f9047', null, null, '1', null, null, null, null, '0', '0', '2019-08-15 13:56:33', '2019-08-15 13:56:33');
INSERT INTO `tb_user` VALUES ('4', '0', null, '95e399589465c92fcf7dc71c8e6d6bd6527d783d', null, null, '田帅傻逼', null, null, null, null, '0', '0', '2019-08-16 14:24:03', '2019-08-16 14:24:03');

-- ----------------------------
-- Table structure for tb_user_permit
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_permit`;
CREATE TABLE `tb_user_permit` (
  `user_permit_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `permit_id` int(11) DEFAULT NULL,
  `crt_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `upd_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_permit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of tb_user_permit
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `crt_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `upd_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user_salt
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_salt`;
CREATE TABLE `tb_user_salt` (
  `user_salt_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `user_salt` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户盐值',
  `crt_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_salt_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of tb_user_salt
-- ----------------------------
INSERT INTO `tb_user_salt` VALUES ('2', '1', '47ee5', '2019-08-15 13:56:38', '2019-08-15 13:56:38');
INSERT INTO `tb_user_salt` VALUES ('4', '4', '54f138', '2019-08-16 14:24:04', '2019-08-16 14:24:04');
