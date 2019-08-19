/*
Navicat MySQL Data Transfer

Source Server         : Mysql8本地
Source Server Version : 80013
Source Host           : localhost:3312
Source Database       : im_friend

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2019-08-19 10:18:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for pm_friend
-- ----------------------------
DROP TABLE IF EXISTS `pm_friend`;
CREATE TABLE `pm_friend` (
  `pm_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pm_user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `pm_friend_id` bigint(20) DEFAULT NULL COMMENT '关注的id',
  `is_like` tinyint(1) DEFAULT NULL COMMENT '是否互相关注',
  `crt_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `upd_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`pm_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of pm_friend
-- ----------------------------
INSERT INTO `pm_friend` VALUES ('1', '1', '2', null, null, null);
