/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1_3306
Source Server Version : 50619
Source Host           : 127.0.0.1:3306
Source Database       : cloud_web

Target Server Type    : MYSQL
Target Server Version : 50619
File Encoding         : 65001

Date: 2015-10-26 00:45:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `test`
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `id` int(11) NOT NULL,
  `name` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES ('1', 'wendellup');

-- ----------------------------
-- Table structure for `t_parameter_app`
-- ----------------------------
DROP TABLE IF EXISTS `t_parameter_app`;
CREATE TABLE `t_parameter_app` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '父节点编号',
  `type` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '分类代码',
  `name` varchar(64) DEFAULT NULL COMMENT '分类名称',
  `param` varchar(128) DEFAULT NULL,
  `sort` int(10) unsigned NOT NULL DEFAULT '9999' COMMENT '分类权重',
  `enable` tinyint(1) NOT NULL DEFAULT '1',
  `depth` int(10) unsigned NOT NULL DEFAULT '1',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注信息',
  `pic_id` bigint(16) DEFAULT NULL COMMENT '图片编号',
  `begin_time` datetime DEFAULT '1970-01-01 08:00:00' COMMENT '开始时间',
  `end_time` datetime DEFAULT '4000-01-01 08:00:00' COMMENT '结束时间',
  `update_time` datetime DEFAULT '1970-01-01 08:00:00' COMMENT '更新时间',
  `operator_id` int(11) DEFAULT '0' COMMENT '操作人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_parameter_app
-- ----------------------------
INSERT INTO `t_parameter_app` VALUES ('3', '1', '0', 'two', null, '9999', '1', '1', 'two', null, '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('4', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '2015-10-25 13:30:27', '2015-10-25 13:30:27', '2015-10-25 13:30:27', '0');
INSERT INTO `t_parameter_app` VALUES ('5', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '2015-10-25 13:32:47', '2015-10-25 13:32:47', '2015-10-25 13:32:47', '0');
INSERT INTO `t_parameter_app` VALUES ('7', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '2015-10-25 13:36:22', '2015-10-25 13:36:22', '2015-10-25 13:36:22', '0');
INSERT INTO `t_parameter_app` VALUES ('8', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '2015-10-25 13:37:31', '2015-10-25 13:37:31', '2015-10-25 13:37:31', '0');
INSERT INTO `t_parameter_app` VALUES ('9', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '2015-10-25 13:44:52', '2015-10-25 13:44:52', '2015-10-25 13:44:52', '0');
INSERT INTO `t_parameter_app` VALUES ('10', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '2015-10-25 13:45:23', '2015-10-25 13:45:23', '2015-10-25 13:45:23', '0');
INSERT INTO `t_parameter_app` VALUES ('20', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '2015-10-25 13:59:32', '2015-10-25 13:59:32', '2015-10-25 13:59:32', '0');
INSERT INTO `t_parameter_app` VALUES ('21', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '2015-10-25 13:56:15', '2015-10-25 13:56:15', '2015-10-25 13:56:15', '0');
INSERT INTO `t_parameter_app` VALUES ('22', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '2015-10-25 13:55:05', '2015-10-25 13:55:05', '2015-10-25 13:55:05', '0');
INSERT INTO `t_parameter_app` VALUES ('23', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '2015-10-25 13:52:09', '2015-10-25 13:52:09', '2015-10-25 13:52:09', '0');
INSERT INTO `t_parameter_app` VALUES ('24', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '2015-10-25 13:48:21', '2015-10-25 13:48:21', '2015-10-25 13:48:21', '0');
INSERT INTO `t_parameter_app` VALUES ('25', '0', '0', 'test1', null, '9999', '1', '1', 'remark', null, '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('100', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', null, null, '2015-10-25 18:37:11', '0');
INSERT INTO `t_parameter_app` VALUES ('104', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('108', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', null, null, '2015-10-25 18:38:16', '0');
INSERT INTO `t_parameter_app` VALUES ('109', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('110', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('111', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');

Drop TABLE IF EXISTS `t_article`;
Create TABLE `t_article` (
  `id` int(11) NOT NULL auto_increment,
  `param_id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `content` text NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
