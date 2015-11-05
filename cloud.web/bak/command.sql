/*
Navicat MySQL Data Transfer

Source Server         : 127
Source Server Version : 50519
Source Host           : 127.0.0.1:3306
Source Database       : cloud_web

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2015-11-05 11:52:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `command`
-- ----------------------------
DROP TABLE IF EXISTS `command`;
CREATE TABLE `command` (
  `id` int(11) NOT NULL,
  `name` varchar(64) DEFAULT NULL,
  `description` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of command
-- ----------------------------

-- ----------------------------
-- Table structure for `command_content`
-- ----------------------------
DROP TABLE IF EXISTS `command_content`;
CREATE TABLE `command_content` (
  `id` int(11) NOT NULL,
  `content` varchar(1024) DEFAULT NULL,
  `command_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of command_content
-- ----------------------------
