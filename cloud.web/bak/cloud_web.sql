/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1_3306
Source Server Version : 50619
Source Host           : 127.0.0.1:3306
Source Database       : cloud_web

Target Server Type    : MYSQL
Target Server Version : 50619
File Encoding         : 65001

Date: 2015-11-04 23:56:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `message`
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL DEFAULT '0',
  `command` varchar(32) DEFAULT NULL,
  `description` varchar(32) DEFAULT NULL,
  `content` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------

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
-- Table structure for `t_article`
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `param_id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `content` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_article
-- ----------------------------
INSERT INTO `t_article` VALUES ('1', '1', 'test_title', 'test_content');
INSERT INTO `t_article` VALUES ('2', '1', 'test_title_2', 'test_content_2');
INSERT INTO `t_article` VALUES ('3', '1', 'test_title_3', 'test_content_3');
INSERT INTO `t_article` VALUES ('4', '4', 'test_title_4', 'test_content_4');

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
) ENGINE=InnoDB AUTO_INCREMENT=808 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_parameter_app
-- ----------------------------
INSERT INTO `t_parameter_app` VALUES ('1', '1', '0', 'two', null, '9999', '1', '1', 'two', null, '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('4', '1', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '2015-10-25 13:30:27', '2015-10-25 13:30:27', '2015-10-25 13:30:27', '0');
INSERT INTO `t_parameter_app` VALUES ('5', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '2015-10-25 13:32:47', '2015-10-25 13:32:47', '2015-10-25 13:32:47', '0');
INSERT INTO `t_parameter_app` VALUES ('7', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '2015-10-25 13:36:22', '2015-10-25 13:36:22', '2015-10-25 13:36:22', '0');
INSERT INTO `t_parameter_app` VALUES ('8', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '2015-10-25 13:37:31', '2015-10-25 13:37:31', '2015-10-25 13:37:31', '0');
INSERT INTO `t_parameter_app` VALUES ('9', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '2015-10-25 13:44:52', '2015-10-25 13:44:52', '2015-10-25 13:44:52', '0');
INSERT INTO `t_parameter_app` VALUES ('10', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '2015-10-25 13:45:23', '2015-10-25 13:45:23', '2015-10-25 13:45:23', '0');
INSERT INTO `t_parameter_app` VALUES ('800', '0', '0', 'blog根节点', null, '9999', '1', '1', null, null, '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('801', '800', '0', '首页', null, '9999', '1', '1', 'index', null, '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('802', '800', '0', '文章', null, '9999', '1', '1', 'article', null, '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('803', '800', '0', 'Demo', null, '9999', '1', '1', 'demo', null, '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('804', '800', '0', 'About', null, '9999', '1', '1', 'about', null, '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('805', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('806', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('807', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
