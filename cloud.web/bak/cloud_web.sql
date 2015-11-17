/*
Navicat MySQL Data Transfer

Source Server         : 127
Source Server Version : 50519
Source Host           : 127.0.0.1:3306
Source Database       : cloud_web

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2015-11-17 20:04:38
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_article
-- ----------------------------
INSERT INTO `t_article` VALUES ('1', '802', 'test_title', 'test_content');
INSERT INTO `t_article` VALUES ('2', '802', 'test_title_2', 'test_content_2');
INSERT INTO `t_article` VALUES ('3', '802', 'test_title_3', 'test_content_3');
INSERT INTO `t_article` VALUES ('4', '802', 'test_title_4', 'test_content_4');
INSERT INTO `t_article` VALUES ('5', '802', '5', '5');
INSERT INTO `t_article` VALUES ('6', '802', '6', '6');
INSERT INTO `t_article` VALUES ('7', '802', '7', '7');
INSERT INTO `t_article` VALUES ('8', '802', '8', '8');
INSERT INTO `t_article` VALUES ('9', '802', '9', '9');
INSERT INTO `t_article` VALUES ('10', '802', '10', '10');
INSERT INTO `t_article` VALUES ('11', '802', '11', '11');

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
) ENGINE=InnoDB AUTO_INCREMENT=840 DEFAULT CHARSET=utf8;

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
INSERT INTO `t_parameter_app` VALUES ('801', '800', '0', '首页', '/blog/index', '9999', '1', '1', '', null, '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('802', '800', '0', '文章', '/blog/article/list', '9999', '1', '1', '以下是文章列表, 希望您能在这找到您需要的~', null, '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('803', '800', '0', '软件', '/blog/software', '9999', '1', '1', '以下是软件推荐列表, 希望您能在这找到您需要的~', null, '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('804', '800', '0', 'About', '/blog/about', '9999', '1', '1', '', null, '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('805', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('806', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('807', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('808', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('809', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('810', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('811', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('812', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('813', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('814', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('815', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('816', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('817', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('818', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('819', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('820', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('821', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('822', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('823', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('824', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('825', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('826', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('827', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('828', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('829', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('830', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('831', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('832', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('833', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('834', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('835', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('836', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('837', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('838', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO `t_parameter_app` VALUES ('839', '0', '0', '羊山公园', '', '0', '1', '0', '栖园', '0', '1970-01-01 08:00:00', '4000-01-01 08:00:00', '1970-01-01 08:00:00', '0');

-- ----------------------------
-- Table structure for `t_parameter_tag`
-- ----------------------------
DROP TABLE IF EXISTS `t_parameter_tag`;
CREATE TABLE `t_parameter_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(32) DEFAULT NULL,
  `tag_type` int(11) DEFAULT NULL COMMENT '1:文章标签 2:参数代码（对应表t_parameter_app字段type）',
  `enable` tinyint(1) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_parameter_tag
-- ----------------------------
INSERT INTO `t_parameter_tag` VALUES ('1', '下载', '1', '1', null);
INSERT INTO `t_parameter_tag` VALUES ('2', 'Java', '1', '1', null);
INSERT INTO `t_parameter_tag` VALUES ('3', 'linux', '1', '1', null);

-- ----------------------------
-- Table structure for `t_parameter_tag_link`
-- ----------------------------
DROP TABLE IF EXISTS `t_parameter_tag_link`;
CREATE TABLE `t_parameter_tag_link` (
  `business_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL DEFAULT '0',
  `business_sort_no` int(11) DEFAULT '0' COMMENT '根据tag_id查出的list按此字段排序',
  `tag_sort_no` int(11) DEFAULT '0' COMMENT '根据business_id查出的taglist按此字段排序',
  `enable` tinyint(1) DEFAULT '1',
  `start_time` bigint(20) DEFAULT NULL,
  `end_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`business_id`,`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_parameter_tag_link
-- ----------------------------
INSERT INTO `t_parameter_tag_link` VALUES ('1', '2', '0', '0', '1', null, null);
INSERT INTO `t_parameter_tag_link` VALUES ('2', '2', '0', '0', '1', null, null);
INSERT INTO `t_parameter_tag_link` VALUES ('3', '1', '0', '0', '1', null, null);
INSERT INTO `t_parameter_tag_link` VALUES ('3', '2', '0', '0', '1', null, null);
INSERT INTO `t_parameter_tag_link` VALUES ('4', '2', '0', '0', '1', null, null);
INSERT INTO `t_parameter_tag_link` VALUES ('5', '2', '0', '0', '1', null, null);
INSERT INTO `t_parameter_tag_link` VALUES ('6', '2', '0', '0', '1', null, null);
INSERT INTO `t_parameter_tag_link` VALUES ('7', '2', '0', '0', '1', null, null);
INSERT INTO `t_parameter_tag_link` VALUES ('8', '3', '0', '0', '1', null, null);
INSERT INTO `t_parameter_tag_link` VALUES ('9', '3', '0', '0', '1', null, null);
INSERT INTO `t_parameter_tag_link` VALUES ('10', '3', '0', '0', '1', null, null);
INSERT INTO `t_parameter_tag_link` VALUES ('11', '3', '0', '0', '1', null, null);
