/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50625
Source Host           : localhost:3306
Source Database       : hustimetable

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-12-21 22:57:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tbljuryrooms`
-- ----------------------------
DROP TABLE IF EXISTS `tbljuryrooms`;
CREATE TABLE `tbljuryrooms` (
  `JuryRoom_ID` int(11) NOT NULL AUTO_INCREMENT,
  `JuryRoom_Index` int(11) NOT NULL,
  `JuryRoom_Code` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `JuryRoom_DefenseSessionCode` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `JuryRoom_StaffCode` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`JuryRoom_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tbljuryrooms
-- ----------------------------
INSERT INTO `tbljuryrooms` VALUES ('1', '0', 'D3-101', '20102015', 'dungpq');
INSERT INTO `tbljuryrooms` VALUES ('2', '0', 'D3-201', '20102015', 'dungpq');
