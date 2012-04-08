-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.67-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema love320cms
--

CREATE DATABASE IF NOT EXISTS love320cms;
USE love320cms;

--
-- Definition of table `acct_authority`
--

DROP TABLE IF EXISTS `acct_authority`;
CREATE TABLE `acct_authority` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `acct_authority`
--

/*!40000 ALTER TABLE `acct_authority` DISABLE KEYS */;
INSERT INTO `acct_authority` (`id`,`name`) VALUES 
 (11,'修改单页面'),
 (18,'修改商品'),
 (26,'修改在线订单'),
 (23,'修改数据模型'),
 (8,'修改文章'),
 (25,'修改权限'),
 (9,'修改标签'),
 (5,'修改栏目'),
 (2,'修改用户'),
 (28,'修改留言'),
 (14,'修改系统配置'),
 (4,'修改角色'),
 (21,'修改评论'),
 (19,'回收站'),
 (32,'广告管理'),
 (16,'文件上传'),
 (15,'更新缓存'),
 (12,'浏览单页面'),
 (17,'浏览商品'),
 (27,'浏览在线订单'),
 (22,'浏览数据模型'),
 (7,'浏览文章'),
 (24,'浏览权限'),
 (10,'浏览标签'),
 (6,'浏览栏目'),
 (1,'浏览用户'),
 (29,'浏览留言'),
 (13,'浏览系统配置'),
 (3,'浏览角色'),
 (20,'浏览评论'),
 (30,'生成静态页面'),
 (31,'辅助功能');
/*!40000 ALTER TABLE `acct_authority` ENABLE KEYS */;


--
-- Definition of table `acct_authority_role_list`
--

DROP TABLE IF EXISTS `acct_authority_role_list`;
CREATE TABLE `acct_authority_role_list` (
  `acct_authority` bigint(20) NOT NULL,
  `role_list` bigint(20) NOT NULL,
  KEY `FKC8D12A9F93B93284` (`role_list`),
  KEY `FKC8D12A9F41776A73` (`acct_authority`),
  CONSTRAINT `FKC8D12A9F41776A73` FOREIGN KEY (`acct_authority`) REFERENCES `acct_authority` (`id`),
  CONSTRAINT `FKC8D12A9F93B93284` FOREIGN KEY (`role_list`) REFERENCES `acct_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `acct_authority_role_list`
--

/*!40000 ALTER TABLE `acct_authority_role_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `acct_authority_role_list` ENABLE KEYS */;


--
-- Definition of table `acct_role`
--

DROP TABLE IF EXISTS `acct_role`;
CREATE TABLE `acct_role` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `acct_role`
--

/*!40000 ALTER TABLE `acct_role` DISABLE KEYS */;
INSERT INTO `acct_role` (`id`,`name`) VALUES 
 (4,'测试管理'),
 (2,'用户'),
 (1,'管理员'),
 (3,'网站制作员');
/*!40000 ALTER TABLE `acct_role` ENABLE KEYS */;


--
-- Definition of table `acct_role_authority`
--

DROP TABLE IF EXISTS `acct_role_authority`;
CREATE TABLE `acct_role_authority` (
  `role_id` bigint(20) NOT NULL,
  `authority_id` bigint(20) NOT NULL,
  KEY `FKAE243466DE3FB930` (`role_id`),
  KEY `FKAE2434663FE97564` (`authority_id`),
  CONSTRAINT `FKAE2434663FE97564` FOREIGN KEY (`authority_id`) REFERENCES `acct_authority` (`id`),
  CONSTRAINT `FKAE243466DE3FB930` FOREIGN KEY (`role_id`) REFERENCES `acct_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `acct_role_authority`
--

/*!40000 ALTER TABLE `acct_role_authority` DISABLE KEYS */;
INSERT INTO `acct_role_authority` (`role_id`,`authority_id`) VALUES 
 (2,7),
 (2,6),
 (2,3),
 (2,1),
 (2,8),
 (2,19),
 (2,16),
 (2,15),
 (1,31),
 (1,30),
 (1,29),
 (1,28),
 (1,27),
 (1,26),
 (1,25),
 (1,24),
 (1,23),
 (1,22),
 (1,21),
 (1,20),
 (1,19),
 (1,18),
 (1,17),
 (1,16),
 (1,15),
 (1,14),
 (1,13),
 (1,12),
 (1,11),
 (1,10),
 (1,9),
 (1,8),
 (1,7),
 (1,6),
 (1,5),
 (1,4),
 (1,3),
 (1,2),
 (1,1),
 (1,32),
 (3,19),
 (3,15),
 (3,14),
 (3,13),
 (3,12),
 (3,11),
 (3,10),
 (3,9),
 (3,8),
 (3,7),
 (3,6),
 (3,5),
 (4,11),
 (4,9),
 (4,21),
 (4,17),
 (4,6),
 (4,30);
/*!40000 ALTER TABLE `acct_role_authority` ENABLE KEYS */;


--
-- Definition of table `acct_user`
--

DROP TABLE IF EXISTS `acct_user`;
CREATE TABLE `acct_user` (
  `id` bigint(20) NOT NULL auto_increment,
  `email` varchar(255) default NULL,
  `login_name` varchar(255) NOT NULL,
  `name` varchar(255) default NULL,
  `password` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `login_name` (`login_name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `acct_user`
--

/*!40000 ALTER TABLE `acct_user` DISABLE KEYS */;
INSERT INTO `acct_user` (`id`,`email`,`login_name`,`name`,`password`) VALUES 
 (1,'admin@love320.com','admin','Admin','admin'),
 (2,'user@love320.com','user','user','user'),
 (3,'jack@love320.com','user2','user2','user2'),
 (7,'binbin@163.com','binbin','冰冰','123456'),
 (8,'siteweb@love320.com','siteweb','siteweb','siteweb');
/*!40000 ALTER TABLE `acct_user` ENABLE KEYS */;


--
-- Definition of table `acct_user_role`
--

DROP TABLE IF EXISTS `acct_user_role`;
CREATE TABLE `acct_user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FKFE85CB3EDE3FB930` (`role_id`),
  KEY `FKFE85CB3E836A7D10` (`user_id`),
  CONSTRAINT `FKFE85CB3E836A7D10` FOREIGN KEY (`user_id`) REFERENCES `acct_user` (`id`),
  CONSTRAINT `FKFE85CB3EDE3FB930` FOREIGN KEY (`role_id`) REFERENCES `acct_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `acct_user_role`
--

/*!40000 ALTER TABLE `acct_user_role` DISABLE KEYS */;
INSERT INTO `acct_user_role` (`user_id`,`role_id`) VALUES 
 (3,2),
 (2,2),
 (7,2),
 (8,3),
 (1,1);
/*!40000 ALTER TABLE `acct_user_role` ENABLE KEYS */;


--
-- Definition of table `addonarticle`
--

DROP TABLE IF EXISTS `addonarticle`;
CREATE TABLE `addonarticle` (
  `aid` bigint(20) NOT NULL auto_increment,
  `body` varchar(255) default NULL,
  `typeid` int(11) NOT NULL,
  PRIMARY KEY  (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `addonarticle`
--

/*!40000 ALTER TABLE `addonarticle` DISABLE KEYS */;
/*!40000 ALTER TABLE `addonarticle` ENABLE KEYS */;


--
-- Definition of table `advertising`
--

DROP TABLE IF EXISTS `advertising`;
CREATE TABLE `advertising` (
  `id` bigint(20) NOT NULL auto_increment,
  `adname` varchar(255) default NULL,
  `endtime` datetime default NULL,
  `expbody` longtext,
  `normbody` longtext,
  `starttime` datetime default NULL,
  `tagname` varchar(255) default NULL,
  `timeset` int(11) NOT NULL,
  `typeid` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `advertising`
--

/*!40000 ALTER TABLE `advertising` DISABLE KEYS */;
INSERT INTO `advertising` (`id`,`adname`,`endtime`,`expbody`,`normbody`,`starttime`,`tagname`,`timeset`,`typeid`) VALUES 
 (1,'请一个见风使舵','2011-08-26 00:00:00','     <div id=\"gsr6\"> \r\n      <img src=\"/skin/image/imgzs_3.gif\"> \r\n    </div> \r\n    <div id=\"gsr7\"> \r\n      <div id=\"gsr77\"> \r\n        湖南常德市精益眼镜<br> \r\n        联系人：李进<br> \r\n        座机：0736-7155718<br> \r\n        联系电话：13873647762<br> \r\n        联系地址：湖南省常德市朝阳路585号（老常嵩路）\r\n      </div> \r\n    </div>\r\n   ','第一不念旧恶广告第一不念旧恶广告第一不念旧恶广告第一不念旧恶广告第一不念旧恶广告第一不念旧恶广告第一不念旧恶广告第一不念旧恶广告第一不念旧恶广告第一不念旧恶广告第一不念旧恶广告第一不念旧恶广告第一不念旧恶广告第一不念旧恶广告第一不念旧恶广告第一不念旧恶广告第一不念旧恶广告第一不念旧恶广告第一不念旧恶广告第一不念旧恶广告','2011-08-19 00:00:00','第一不念旧恶广告',1,NULL),
 (2,'请sdfds风使舵','2011-11-17 11:01:22','过期显示内容过期显示内容过期显示内容过期显示内容过期显示内容过期显示内容过期显示内容过期显示内容过期显示内容过期显示内容过期显示内容过期显示内容过期显示内容过期显示内容过期显示内容过期显示内容过期显期显示内容过期显示内容过期显示内容过期显示内容过期显示内容过期显示内容过期显示内容过期显示内容','广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二广告二','2011-11-20 11:50:34','广告二',1,NULL);
/*!40000 ALTER TABLE `advertising` ENABLE KEYS */;


--
-- Definition of table `arcatt`
--

DROP TABLE IF EXISTS `arcatt`;
CREATE TABLE `arcatt` (
  `id` bigint(20) NOT NULL auto_increment,
  `att` varchar(255) default NULL,
  `att_name` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arcatt`
--

/*!40000 ALTER TABLE `arcatt` DISABLE KEYS */;
INSERT INTO `arcatt` (`id`,`att`,`att_name`) VALUES 
 (1,'z','love320'),
 (5,'h','头条'),
 (6,'c','推荐'),
 (7,'f','幻灯'),
 (8,'a','特荐'),
 (9,'s','滚动'),
 (10,'b','加粗');
/*!40000 ALTER TABLE `arcatt` ENABLE KEYS */;


--
-- Definition of table `archives`
--

DROP TABLE IF EXISTS `archives`;
CREATE TABLE `archives` (
  `id` bigint(20) NOT NULL auto_increment,
  `arcrank` int(11) NOT NULL,
  `badpost` int(11) NOT NULL,
  `body` mediumtext,
  `channel` int(11) NOT NULL,
  `click` int(11) NOT NULL,
  `color` varchar(255) default NULL,
  `description` varchar(255) default NULL,
  `goodpost` int(11) NOT NULL,
  `ismake` int(11) NOT NULL,
  `keywords` varchar(255) default NULL,
  `lastpost` int(11) NOT NULL,
  `litpic` varchar(255) default NULL,
  `mid` int(11) NOT NULL,
  `moeny` int(11) NOT NULL,
  `notpost` int(11) NOT NULL,
  `pubdate` datetime default NULL,
  `redirecturl` varchar(255) default NULL,
  `scores` int(11) NOT NULL,
  `senddate` datetime default NULL,
  `shorttitle` varchar(255) default NULL,
  `sortrank` datetime default NULL,
  `source` varchar(255) default NULL,
  `templet` varchar(255) default NULL,
  `title` varchar(255) default NULL,
  `typeid` int(11) NOT NULL,
  `userip` varchar(255) default NULL,
  `writer` varchar(255) default NULL,
  `flag` varchar(255) default NULL,
  `mname` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `archives`
--

/*!40000 ALTER TABLE `archives` DISABLE KEYS */;
INSERT INTO `archives` (`id`,`arcrank`,`badpost`,`body`,`channel`,`click`,`color`,`description`,`goodpost`,`ismake`,`keywords`,`lastpost`,`litpic`,`mid`,`moeny`,`notpost`,`pubdate`,`redirecturl`,`scores`,`senddate`,`shorttitle`,`sortrank`,`source`,`templet`,`title`,`typeid`,`userip`,`writer`,`flag`,`mname`) VALUES 
 (1,0,0,'<p>&nbsp;fghfgh</p>',0,3,NULL,'fghfgh',0,0,'fghfgh',0,'',0,0,0,'2011-11-18 00:16:37',NULL,0,'2011-07-06 16:14:03','','2011-11-18 00:16:37','中国人',NULL,'zhangdi中国全国人大代表',3,NULL,'张迪',NULL,'admin'),
 (4,0,0,'<p> ghfgh</p>',0,283,NULL,'ghfg中国1109',0,0,'fgh',0,'中国1109',0,0,0,'2011-11-19 12:45:55',NULL,0,'2011-07-22 14:51:35','中国1109','2011-11-19 12:45:55','中国1109',NULL,'中国1109',1,NULL,'fghfg',NULL,'admin'),
 (5,0,0,'<p>&nbsp;ghjghjg</p>',0,249,NULL,'jghjghj',0,0,'ghjhg',0,'',0,0,0,'2011-07-29 09:41:11',NULL,0,'2011-07-22 14:51:43','','2011-07-29 09:41:11','',NULL,'ghjghjgh',1,NULL,'',NULL,'admin'),
 (6,0,0,NULL,0,0,NULL,'',0,0,'',0,'',0,0,0,'2011-11-10 21:56:30',NULL,0,'2011-11-10 21:45:44','','2011-11-10 21:56:30','',NULL,'sdfsdf',3,NULL,'',NULL,NULL),
 (7,0,0,NULL,0,0,NULL,'',0,0,'',0,'',0,0,0,'2011-11-10 21:45:50',NULL,0,'2011-11-10 21:45:50','',NULL,'',NULL,'sdfsd',3,NULL,'',NULL,NULL),
 (8,0,0,NULL,0,0,NULL,'',0,0,'',0,'',0,0,0,'2011-11-10 21:45:58',NULL,0,'2011-11-10 21:45:58','',NULL,'',NULL,'sdfsdf',3,NULL,'',NULL,NULL),
 (9,0,0,NULL,0,0,NULL,'',0,0,'',0,'',0,0,0,'2011-11-10 21:46:06',NULL,0,'2011-11-10 21:46:06','dfgd',NULL,'',NULL,'fdgdf',3,NULL,'',NULL,NULL),
 (10,0,0,'',0,0,NULL,'',0,0,'',0,'/Love111119/love142750psbe (11).jpg',0,0,0,'2011-11-19 14:50:52',NULL,0,'2011-11-10 21:46:16','dfgfd','2011-11-19 14:50:52','',NULL,'dfgfdfg',3,NULL,'',NULL,NULL),
 (11,0,0,NULL,0,0,NULL,'',0,0,'',0,'',0,0,0,'2011-11-10 21:46:22',NULL,0,'2011-11-10 21:46:22','',NULL,'',NULL,'dfgd',3,NULL,'',NULL,NULL),
 (12,-2,0,NULL,0,0,NULL,'',0,0,'',0,'',0,0,0,'2011-11-10 22:14:10',NULL,0,'2011-11-10 21:46:33','','2011-11-10 22:14:10','',NULL,'dfgdfgd',3,NULL,'',NULL,NULL),
 (15,0,0,'',0,0,NULL,'',0,0,'',0,'',0,0,0,'2011-11-19 15:47:52',NULL,0,'2011-11-10 21:56:04','','2011-11-19 15:47:52','',NULL,'删除 的',3,NULL,'',NULL,NULL),
 (16,0,0,NULL,0,0,NULL,'',0,0,'',0,'',0,0,0,'2011-11-19 11:25:33',NULL,0,'2011-11-16 20:39:21','','2011-11-19 11:25:33','',NULL,'sdfsdf',3,NULL,'',NULL,NULL),
 (17,0,0,NULL,0,0,NULL,'',0,0,'',0,'',0,0,0,'2011-11-18 20:07:00',NULL,0,'2011-11-18 00:07:20','','2011-11-18 20:07:00','',NULL,'zzzzs',3,NULL,'',NULL,NULL),
 (18,0,0,NULL,0,0,NULL,'',0,0,'',0,'',0,0,0,'2011-11-18 20:06:01',NULL,0,'2011-11-18 20:06:01','',NULL,'',NULL,'sdfsdf',3,NULL,'',NULL,NULL),
 (19,0,0,NULL,0,0,NULL,'',0,0,'',0,'',0,0,0,'2011-11-18 20:06:12',NULL,0,'2011-11-18 20:06:12','',NULL,'',NULL,'sdfsdf',3,NULL,'',NULL,NULL),
 (20,0,0,'<p> sfsdfdsfds</p>',0,0,NULL,'',0,0,'',0,'',0,0,0,'2011-11-19 12:46:39',NULL,0,'2011-11-19 12:45:08','','2011-11-19 12:46:39','',NULL,'dfgdfgd',3,NULL,'',NULL,NULL),
 (21,0,0,'',0,0,NULL,'',0,0,'',0,'',0,0,0,'2011-11-19 14:45:43',NULL,0,'2011-11-19 12:46:53','','2011-11-19 14:45:43','',NULL,'sdfsdsd',1,NULL,'',NULL,NULL),
 (22,0,0,'',0,0,NULL,'',0,0,'',0,'',0,0,0,'2011-11-19 14:50:20',NULL,0,'2011-11-19 12:49:34','','2011-11-19 14:50:20','',NULL,'sdfsd',3,NULL,'',NULL,NULL),
 (23,0,0,'',0,0,NULL,'',0,0,'',0,'/Love111119/love142750psbe (11).jpg',0,0,0,'2011-11-19 14:44:25',NULL,0,'2011-11-19 12:50:30','','2011-11-19 14:44:25','',NULL,'sdfdsf',3,NULL,'',NULL,NULL),
 (24,0,0,'',0,16,NULL,'',0,0,'',0,'',0,0,0,'2011-11-19 12:53:36',NULL,0,'2011-11-19 12:51:16','','2011-11-19 12:53:36','',NULL,'fghfhg',3,NULL,'',NULL,NULL),
 (26,0,0,'et',0,0,NULL,'',0,0,'',0,NULL,0,0,0,'2011-11-19 18:13:11',NULL,0,'2011-11-19 18:13:11','',NULL,'',NULL,'ertdfgf',3,NULL,'',NULL,NULL),
 (27,0,0,'a',0,0,NULL,'a',0,0,'',0,NULL,0,0,0,'2011-11-19 18:13:34',NULL,0,'2011-11-19 18:13:34','',NULL,'',NULL,'aaa',2,NULL,'',NULL,NULL),
 (28,0,0,'',0,1,NULL,'',0,0,'',0,'',0,0,0,'2011-11-19 18:18:59',NULL,0,'2011-11-19 18:18:59','',NULL,'',NULL,'cvcvcv',3,NULL,'',NULL,NULL);
/*!40000 ALTER TABLE `archives` ENABLE KEYS */;


--
-- Definition of table `archives_arcatt`
--

DROP TABLE IF EXISTS `archives_arcatt`;
CREATE TABLE `archives_arcatt` (
  `archives_id` bigint(20) NOT NULL,
  `arcatt_id` bigint(20) NOT NULL,
  KEY `FK1FF72B1DAC6893E1` (`archives_id`),
  KEY `FK1FF72B1DDC981CE1` (`arcatt_id`),
  CONSTRAINT `FK1FF72B1DAC6893E1` FOREIGN KEY (`archives_id`) REFERENCES `archives` (`id`),
  CONSTRAINT `FK1FF72B1DDC981CE1` FOREIGN KEY (`arcatt_id`) REFERENCES `arcatt` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `archives_arcatt`
--

/*!40000 ALTER TABLE `archives_arcatt` DISABLE KEYS */;
INSERT INTO `archives_arcatt` (`archives_id`,`arcatt_id`) VALUES 
 (4,5),
 (4,6),
 (4,7),
 (4,8),
 (4,9),
 (12,10),
 (6,1),
 (6,10),
 (6,5),
 (16,5),
 (10,1),
 (10,5),
 (10,10),
 (17,10),
 (18,1),
 (18,10),
 (19,5),
 (15,5),
 (23,6),
 (24,1),
 (22,5),
 (22,1);
/*!40000 ALTER TABLE `archives_arcatt` ENABLE KEYS */;


--
-- Definition of table `archives_arctype`
--

DROP TABLE IF EXISTS `archives_arctype`;
CREATE TABLE `archives_arctype` (
  `arctype_id` bigint(20) default NULL,
  `archives_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`archives_id`),
  KEY `FKDEF6EE3EAC6893E1` (`archives_id`),
  KEY `FKDEF6EE3EABDD0D93` (`arctype_id`),
  CONSTRAINT `FKDEF6EE3EABDD0D93` FOREIGN KEY (`arctype_id`) REFERENCES `arctype` (`id`),
  CONSTRAINT `FKDEF6EE3EAC6893E1` FOREIGN KEY (`archives_id`) REFERENCES `archives` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `archives_arctype`
--

/*!40000 ALTER TABLE `archives_arctype` DISABLE KEYS */;
INSERT INTO `archives_arctype` (`arctype_id`,`archives_id`) VALUES 
 (1,4),
 (1,5),
 (1,21),
 (2,27),
 (3,1),
 (3,6),
 (3,7),
 (3,8),
 (3,9),
 (3,10),
 (3,11),
 (3,12),
 (3,15),
 (3,16),
 (3,17),
 (3,18),
 (3,19),
 (3,20),
 (3,22),
 (3,23),
 (3,24),
 (3,26),
 (3,28);
/*!40000 ALTER TABLE `archives_arctype` ENABLE KEYS */;


--
-- Definition of table `arctype`
--

DROP TABLE IF EXISTS `arctype`;
CREATE TABLE `arctype` (
  `id` bigint(20) NOT NULL auto_increment,
  `channeltype` int(11) NOT NULL,
  `content` varchar(255) default NULL,
  `corank` int(11) NOT NULL,
  `crossid` varchar(255) default NULL,
  `defaultname` varchar(255) default NULL,
  `description` varchar(255) default NULL,
  `isdefault` int(11) NOT NULL,
  `ishidden` int(11) NOT NULL,
  `ispart` int(11) NOT NULL,
  `issend` int(11) NOT NULL,
  `keywords` varchar(255) default NULL,
  `maxpage` int(11) NOT NULL,
  `modname` varchar(255) default NULL,
  `moresite` int(11) NOT NULL,
  `namerule` varchar(255) default NULL,
  `namerule2` varchar(255) default NULL,
  `reid` int(11) NOT NULL,
  `sitepath` varchar(255) default NULL,
  `siteurl` varchar(255) default NULL,
  `sortrank` int(11) NOT NULL,
  `temparticle` varchar(255) default NULL,
  `tempindex` varchar(255) default NULL,
  `templist` varchar(255) default NULL,
  `topid` int(11) NOT NULL,
  `typedir` varchar(255) default NULL,
  `typename` varchar(255) default NULL,
  `temparticlename` varchar(255) default NULL,
  `templistname` varchar(255) default NULL,
  `clickmethods` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arctype`
--

/*!40000 ALTER TABLE `arctype` DISABLE KEYS */;
INSERT INTO `arctype` (`id`,`channeltype`,`content`,`corank`,`crossid`,`defaultname`,`description`,`isdefault`,`ishidden`,`ispart`,`issend`,`keywords`,`maxpage`,`modname`,`moresite`,`namerule`,`namerule2`,`reid`,`sitepath`,`siteurl`,`sortrank`,`temparticle`,`tempindex`,`templist`,`topid`,`typedir`,`typename`,`temparticlename`,`templistname`,`clickmethods`) VALUES 
 (1,0,NULL,0,NULL,NULL,NULL,0,0,0,0,'',0,NULL,0,NULL,NULL,0,NULL,NULL,0,'/default/arc.jsp',NULL,'/default/list.jsp',1,'','订单','love320arc1311318475125','love320list1311318475125',NULL),
 (2,0,NULL,0,NULL,NULL,NULL,0,0,0,0,'',0,NULL,0,NULL,NULL,1,NULL,NULL,0,'/default/arc.jsp',NULL,'/default/list.jsp',1,'','订单测试仪','love320arc1310190653375','love320list1310190653375',NULL),
 (3,0,NULL,0,NULL,NULL,NULL,0,0,0,0,'',0,NULL,0,NULL,NULL,2,NULL,NULL,0,'/default/arc.jsp',NULL,'/default/list.jsp',1,NULL,'测试三','love320arc1310556396484','love320list1310556396484',NULL);
/*!40000 ALTER TABLE `arctype` ENABLE KEYS */;


--
-- Definition of table `arctype_entitymode`
--

DROP TABLE IF EXISTS `arctype_entitymode`;
CREATE TABLE `arctype_entitymode` (
  `entitymode_id` bigint(20) default NULL,
  `arctype_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`arctype_id`),
  KEY `FK6682A9998C5C3E21` (`entitymode_id`),
  KEY `FK6682A999ABDD0D93` (`arctype_id`),
  CONSTRAINT `FK6682A9998C5C3E21` FOREIGN KEY (`entitymode_id`) REFERENCES `entity_mode` (`id`),
  CONSTRAINT `FK6682A999ABDD0D93` FOREIGN KEY (`arctype_id`) REFERENCES `arctype` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arctype_entitymode`
--

/*!40000 ALTER TABLE `arctype_entitymode` DISABLE KEYS */;
INSERT INTO `arctype_entitymode` (`entitymode_id`,`arctype_id`) VALUES 
 (1,1),
 (1,2),
 (1,3);
/*!40000 ALTER TABLE `arctype_entitymode` ENABLE KEYS */;


--
-- Definition of table `arctype_shop`
--

DROP TABLE IF EXISTS `arctype_shop`;
CREATE TABLE `arctype_shop` (
  `arctype_id` bigint(20) default NULL,
  `shop_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`shop_id`),
  KEY `FKCF827AC9FAEAF541` (`shop_id`),
  KEY `FKCF827AC9ABDD0D93` (`arctype_id`),
  CONSTRAINT `FKCF827AC9ABDD0D93` FOREIGN KEY (`arctype_id`) REFERENCES `arctype` (`id`),
  CONSTRAINT `FKCF827AC9FAEAF541` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arctype_shop`
--

/*!40000 ALTER TABLE `arctype_shop` DISABLE KEYS */;
INSERT INTO `arctype_shop` (`arctype_id`,`shop_id`) VALUES 
 (1,2),
 (3,1),
 (3,6);
/*!40000 ALTER TABLE `arctype_shop` ENABLE KEYS */;


--
-- Definition of table `entity_mode`
--

DROP TABLE IF EXISTS `entity_mode`;
CREATE TABLE `entity_mode` (
  `id` bigint(20) NOT NULL auto_increment,
  `add_table` varchar(255) default NULL,
  `article_tmpl` varchar(255) default NULL,
  `entity_name` varchar(255) default NULL,
  `list_tmpl` varchar(255) default NULL,
  `mode_name` varchar(255) default NULL,
  `nid` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `entity_mode`
--

/*!40000 ALTER TABLE `entity_mode` DISABLE KEYS */;
INSERT INTO `entity_mode` (`id`,`add_table`,`article_tmpl`,`entity_name`,`list_tmpl`,`mode_name`,`nid`) VALUES 
 (1,'addarchives','archivesarc.html','archives','archivesList.html','普通文章','archives'),
 (2,'addimages','imagesarc.html','images','images.html','图片集','images'),
 (3,'addshop','shop.html','shopentity','shopList.html','商品','shop'),
 (4,'addsoft','soft.html','softEntity','softList.html','软件','soft');
/*!40000 ALTER TABLE `entity_mode` ENABLE KEYS */;


--
-- Definition of table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `id` bigint(20) NOT NULL auto_increment,
  `bad` int(11) NOT NULL,
  `dtime` datetime default NULL,
  `face` int(11) NOT NULL,
  `ftype` varchar(255) default NULL,
  `good` int(11) NOT NULL,
  `ip` varchar(255) default NULL,
  `ischeck` int(11) NOT NULL,
  `mid` int(11) NOT NULL,
  `msg` varchar(255) default NULL,
  `title` varchar(255) default NULL,
  `typeid` int(11) NOT NULL,
  `username` varchar(255) default NULL,
  `aid` bigint(20) default NULL,
  `shopid` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKF495EB856D8DA4F4` (`aid`),
  KEY `FKF495EB8549E0CCCE` (`shopid`),
  CONSTRAINT `FKF495EB8549E0CCCE` FOREIGN KEY (`shopid`) REFERENCES `archives` (`id`),
  CONSTRAINT `FKF495EB856D8DA4F4` FOREIGN KEY (`aid`) REFERENCES `archives` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `feedback`
--

/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` (`id`,`bad`,`dtime`,`face`,`ftype`,`good`,`ip`,`ischeck`,`mid`,`msg`,`title`,`typeid`,`username`,`aid`,`shopid`) VALUES 
 (1,0,'2011-11-19 17:10:13',2,NULL,0,NULL,0,0,'sdfsdfsfs','sdf',0,'sdfs',24,NULL),
 (2,0,'2011-11-19 17:10:46',1,NULL,0,NULL,0,0,'我说的和衣而卧','中国人',0,'张迪',24,NULL),
 (3,0,'2011-11-19 17:11:38',2,NULL,0,NULL,0,0,'dfgdfg','dfgdfgdfg',0,'fdgfd',24,NULL),
 (4,0,'2011-11-19 17:11:44',2,NULL,0,NULL,0,0,'dfgd','dfgd',0,'dfgdf',24,NULL),
 (5,0,'2011-11-19 17:11:48',0,NULL,0,NULL,0,0,'sdfsf','sdfsd',0,'sdfs',24,NULL),
 (6,0,'2011-11-19 17:26:44',0,NULL,0,NULL,0,0,'','dfsf1234567',0,'dfgd',24,NULL),
 (7,0,'2011-11-19 17:11:57',0,NULL,0,NULL,0,0,'','sdfdsf',0,'',24,NULL),
 (10,0,'2011-11-19 17:26:29',0,NULL,0,NULL,0,0,'dfg','fdgd',0,'dfgdg123456',24,NULL);
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;


--
-- Definition of table `guestbook`
--

DROP TABLE IF EXISTS `guestbook`;
CREATE TABLE `guestbook` (
  `id` bigint(20) NOT NULL auto_increment,
  `dtime` datetime default NULL,
  `email` varchar(255) default NULL,
  `face` varchar(255) default NULL,
  `homepage` varchar(255) default NULL,
  `ip` varchar(255) default NULL,
  `ischeck` varchar(255) default NULL,
  `mid` varchar(255) default NULL,
  `msg` longtext,
  `postime` datetime default NULL,
  `qq` varchar(255) default NULL,
  `title` varchar(255) default NULL,
  `uname` varchar(255) default NULL,
  `replymsg` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `guestbook`
--

/*!40000 ALTER TABLE `guestbook` DISABLE KEYS */;
INSERT INTO `guestbook` (`id`,`dtime`,`email`,`face`,`homepage`,`ip`,`ischeck`,`mid`,`msg`,`postime`,`qq`,`title`,`uname`,`replymsg`) VALUES 
 (8,'2011-05-07 12:51:11','zkdf@163.com','2','个人主页','127.0.0.1',NULL,NULL,'dfgdfg',NULL,'右ksdf dskf','标题','右ksdf dskf',NULL),
 (11,'2011-05-16 14:11:46','admin@love320.com','2','http://www.love320.com','127.0.0.1',NULL,NULL,'冰冰冰冰冰冰',NULL,'277191621','冰冰标题 ','冰冰',NULL),
 (12,'2011-05-16 14:14:34','admin@love320.com','2','个人主页','127.0.0.1',NULL,NULL,'dfgfdg',NULL,'277191621','右ksdf dskf','右ksdf dskf',NULL),
 (13,'2011-05-20 17:34:36','dfg','2','dfg','127.0.0.1',NULL,NULL,'dfg',NULL,'dfgdfg','dfgdf','dfgdf',NULL),
 (14,'2011-05-20 17:38:23','admin@love320.com','2','http://www.love320.com','127.0.0.1',NULL,NULL,'dfgdf右ksdf dskf右ksdf dskf右ksdf dskf右ksdf dskf右ksdf dskf右ksdf dskf右ksdf dskf右ksdf dskf右ksdf dskf右ksdf dskf右ksdf dskf右ksdf dskf右ksdf dskf右ksdf dskf右ksdf dskf右ksdf dskf右ksdf dskf右ksdf dskf右ksdf dskf右ksdf dskf右ksdf dskf右ksdf dskf',NULL,'277191621','右ksdf dskf','右ksdf dskf',NULL),
 (15,'2011-07-13 08:47:42','sdfsd@qq.com','1','http://www.baidu.com','127.0.0.1',NULL,NULL,'msgmgsgmsmgsdmfmsdfklsd',NULL,'277191621','标题','姓名:','回复信息回复信息回复信息回复信息回复信息'),
 (16,'2011-07-29 18:18:52','fghf','2','fghfg','127.0.0.1',NULL,NULL,'fghfgh',NULL,'fghfg','ghfgh','ghfgh',NULL);
/*!40000 ALTER TABLE `guestbook` ENABLE KEYS */;


--
-- Definition of table `online_order`
--

DROP TABLE IF EXISTS `online_order`;
CREATE TABLE `online_order` (
  `id` bigint(20) NOT NULL auto_increment,
  `address` varchar(255) default NULL,
  `company` varchar(255) default NULL,
  `date_time` datetime default NULL,
  `email` varchar(255) default NULL,
  `fax` varchar(255) default NULL,
  `money` double NOT NULL,
  `num` int(11) NOT NULL,
  `perple` varchar(255) default NULL,
  `postal_code` varchar(255) default NULL,
  `prp_name` varchar(255) default NULL,
  `tel` varchar(255) default NULL,
  `test` varchar(255) default NULL,
  `web_site` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `online_order`
--

/*!40000 ALTER TABLE `online_order` DISABLE KEYS */;
INSERT INTO `online_order` (`id`,`address`,`company`,`date_time`,`email`,`fax`,`money`,`num`,`perple`,`postal_code`,`prp_name`,`tel`,`test`,`web_site`) VALUES 
 (1,'常德','苹果公司','2011-07-02 13:57:11','admin@love320.com','0736-2519030',0,10,'张三','415000','苹果','1378667921','订购要求和其他','冰迪'),
 (2,'','冰迪网络公司','2011-11-20 18:46:17','','',0,0,'张三','','苹果1','13786679221','',''),
 (3,'常德','冰迪网络公司','2011-07-02 15:26:03','admin@love320.com','0736-2519030',1002,15,'张三','415000','天下无双','13786679221','购要求和其他购要求和其他购要求和其他购要求和其他购要求和其他购要求和其他','www.love320.com');
/*!40000 ALTER TABLE `online_order` ENABLE KEYS */;


--
-- Definition of table `sgpage`
--

DROP TABLE IF EXISTS `sgpage`;
CREATE TABLE `sgpage` (
  `id` bigint(20) NOT NULL auto_increment,
  `body` longtext,
  `description` varchar(255) default NULL,
  `file_name` varchar(255) default NULL,
  `keywords` varchar(255) default NULL,
  `likeid` varchar(255) default NULL,
  `template` varchar(255) default NULL,
  `title` varchar(255) default NULL,
  `uptime` datetime default NULL,
  `typeid` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sgpage`
--

/*!40000 ALTER TABLE `sgpage` DISABLE KEYS */;
INSERT INTO `sgpage` (`id`,`body`,`description`,`file_name`,`keywords`,`likeid`,`template`,`title`,`uptime`,`typeid`) VALUES 
 (2,'<p>zz外链接单页面:sgpage.action?id=2外链接单页面:sgpage.action?id=2外链接单页面:sgpage.action?id=2外链接单页面:sgpage.action?id=2标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题zz</p>','摘要摘要','love320cmssgpage1312776018843','关键字关键字','love320','/default/arc.jsp','1111111111111111111',NULL,3),
 (5,'<p style=\"text-align: left;\">lb520内容提要人内容提要人内容提要人内容提要人内容提要人内容提要人内容提要人内容提要人内容提要人内容提要人内容提要人内容提要人内容提要人xxx内容提要人内容提要人内容提要人内vbnvbnvbn容提要人内容提要人内容提要人内容提要人内容提要人内容提要人内容提要人内容提要人内容提要人内容提要人内容提要人内容提要人内容提要人内容提要人内容提要人内容提要人内容提要人内容提要人内容提要人</p>','gdfgdfg','love320cmssgpage1312774676953','dfgfd','sdfsdf','/default/arc.jsp','张迪233333333',NULL,2),
 (6,'<p>&nbsp;zzzzz</p>','zzzz','love320cmssgpage1312776058640','zzz','zzzz','/default/arc.jsp','zzzzzzzzzzzzzzzzz',NULL,2),
 (7,'qw','qw','love320cmssgpage1321697651183','qw','','/default/guestbook.jsp','qw',NULL,2),
 (8,'xcv','xcv','love320cmssgpage1321697861524','xcv','','xcvxc','dsfs',NULL,2);
/*!40000 ALTER TABLE `sgpage` ENABLE KEYS */;


--
-- Definition of table `shop`
--

DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop` (
  `id` bigint(20) NOT NULL auto_increment,
  `arcrank` int(11) NOT NULL,
  `badpost` int(11) NOT NULL,
  `body` longtext,
  `channel` int(11) NOT NULL,
  `click` int(11) NOT NULL,
  `color` varchar(255) default NULL,
  `description` varchar(255) default NULL,
  `goodpost` int(11) NOT NULL,
  `ismake` int(11) NOT NULL,
  `keywords` varchar(255) default NULL,
  `lastpost` int(11) NOT NULL,
  `litpic` varchar(255) default NULL,
  `mid` int(11) NOT NULL,
  `moeny` int(11) NOT NULL,
  `notpost` int(11) NOT NULL,
  `pubdate` datetime default NULL,
  `redirecturl` varchar(255) default NULL,
  `scores` int(11) NOT NULL,
  `senddate` datetime default NULL,
  `shorttitle` varchar(255) default NULL,
  `sortrank` datetime default NULL,
  `source` varchar(255) default NULL,
  `templet` varchar(255) default NULL,
  `title` varchar(255) default NULL,
  `typeid` int(11) NOT NULL,
  `userip` varchar(255) default NULL,
  `writer` varchar(255) default NULL,
  `brand` varchar(255) default NULL,
  `price` double NOT NULL,
  `trueprice` double NOT NULL,
  `units` varchar(255) default NULL,
  `origin` varchar(255) default NULL,
  `specifications` varchar(255) default NULL,
  `mname` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `shop`
--

/*!40000 ALTER TABLE `shop` DISABLE KEYS */;
INSERT INTO `shop` (`id`,`arcrank`,`badpost`,`body`,`channel`,`click`,`color`,`description`,`goodpost`,`ismake`,`keywords`,`lastpost`,`litpic`,`mid`,`moeny`,`notpost`,`pubdate`,`redirecturl`,`scores`,`senddate`,`shorttitle`,`sortrank`,`source`,`templet`,`title`,`typeid`,`userip`,`writer`,`brand`,`price`,`trueprice`,`units`,`origin`,`specifications`,`mname`) VALUES 
 (1,0,0,'',0,0,NULL,'',0,0,'',0,'/Love111119/love142750psbe (11).jpg',0,0,0,NULL,NULL,0,'2011-11-19 14:53:30','sdfsdfsd',NULL,'',NULL,'fsdfs',3,NULL,'','sdfsdf',0,0,'','sdfds','',NULL),
 (2,0,0,'sdf',0,0,NULL,'sdf',0,0,'sdf',0,'/Love111119/love142750psbe (11).jpg',0,0,0,NULL,NULL,0,'2011-11-19 15:03:12','','2011-11-19 15:03:12','sdf',NULL,'sddsf',1,NULL,'sdf','sdf',23,0,'','sdf','',NULL),
 (6,0,0,'',0,0,NULL,'',0,0,'',0,'',0,0,0,NULL,NULL,0,'2011-11-19 15:06:27','',NULL,'',NULL,'fghffggffgfg',3,NULL,'','',0,0,'','','',NULL);
/*!40000 ALTER TABLE `shop` ENABLE KEYS */;


--
-- Definition of table `shop_arcatt`
--

DROP TABLE IF EXISTS `shop_arcatt`;
CREATE TABLE `shop_arcatt` (
  `shop_id` bigint(20) NOT NULL,
  `arcatt_id` bigint(20) NOT NULL,
  KEY `FK2A2D0878FAEAF541` (`shop_id`),
  KEY `FK2A2D0878DC981CE1` (`arcatt_id`),
  CONSTRAINT `FK2A2D0878DC981CE1` FOREIGN KEY (`arcatt_id`) REFERENCES `arcatt` (`id`),
  CONSTRAINT `FK2A2D0878FAEAF541` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `shop_arcatt`
--

/*!40000 ALTER TABLE `shop_arcatt` DISABLE KEYS */;
INSERT INTO `shop_arcatt` (`shop_id`,`arcatt_id`) VALUES 
 (1,5),
 (1,7),
 (2,5),
 (2,7),
 (2,10),
 (2,6),
 (6,6),
 (6,7),
 (6,8);
/*!40000 ALTER TABLE `shop_arcatt` ENABLE KEYS */;


--
-- Definition of table `shop_arctype`
--

DROP TABLE IF EXISTS `shop_arctype`;
CREATE TABLE `shop_arctype` (
  `arctype_id` bigint(20) default NULL,
  `shop_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`shop_id`),
  KEY `FK1B7CBC43FAEAF541` (`shop_id`),
  KEY `FK1B7CBC43ABDD0D93` (`arctype_id`),
  CONSTRAINT `FK1B7CBC43ABDD0D93` FOREIGN KEY (`arctype_id`) REFERENCES `arctype` (`id`),
  CONSTRAINT `FK1B7CBC43FAEAF541` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `shop_arctype`
--

/*!40000 ALTER TABLE `shop_arctype` DISABLE KEYS */;
/*!40000 ALTER TABLE `shop_arctype` ENABLE KEYS */;


--
-- Definition of table `tag_label`
--

DROP TABLE IF EXISTS `tag_label`;
CREATE TABLE `tag_label` (
  `id` bigint(20) NOT NULL auto_increment,
  `orderby` varchar(255) default NULL,
  `row` int(11) NOT NULL,
  `tag_name` varchar(255) default NULL,
  `titlelen` int(11) NOT NULL,
  `typeid` int(11) NOT NULL,
  `body` mediumtext,
  `orderby_type` varchar(255) default NULL,
  `tltid` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK7C32060F1D9D6D32` (`tltid`),
  CONSTRAINT `FK7C32060F1D9D6D32` FOREIGN KEY (`tltid`) REFERENCES `tag_label_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tag_label`
--

/*!40000 ALTER TABLE `tag_label` DISABLE KEYS */;
INSERT INTO `tag_label` (`id`,`orderby`,`row`,`tag_name`,`titlelen`,`typeid`,`body`,`orderby_type`,`tltid`) VALUES 
 (10,'pubdate',0,'动态新闻',0,147,'<li><b><a href=\"${ctx}/html/article-${id}.html\">__${title}___</a></b><span>2010-08-20</span></li>','asc',2),
 (11,'sortrank',3,'测试栏目',0,0,'<li>${st.index+1}d<b><a href=\"${ctx}/html/list-${id}.html\">__${typename}___</a></b><span></span></li>','asc',1),
 (12,'sortrank',10,'顶栏目',0,0,'<li><b>${st.index+1}x<a href=\"${ctx}/html/list-${id}.html\">__${typename}___</a></b><span>${typename}</span></li>','asc',1),
 (13,'sortrank',0,'内容标签',0,172,'<li><b><a href=\"${ctx}/html/article-${id}.html\">__${title}___</a></b><span><s:date name=\'senddate\' format=\'yyyy-MM-dd\'/></span></li>','asc',2),
 (14,'senddate',1,'通用列表',0,0,'<li><b><a href=\"${ctx}/html/article-${id}.html\">__${title}_zhangdi__</a></b><span><s:date name=\"senddate\" format=\"yyyy-MM-dd\"/></span></li>','desc',4),
 (15,'sortrank',0,'动态新闻1',0,165,'<li><b><a href=\"${ctx}/html/article-${id}.html\">__${title}___</a></b><span><s:date name=\'senddate\' format=\'yyyy-MM-dd\'/></span></li>','asc',4),
 (16,'senddate',0,'动态新闻12',0,171,'<li><b><a href=\"${ctx}/html/article-${id}.html\">__${title}___</a></b><span><s:date name=\'senddate\' format=\'yyyy-MM-dd\'/></span></li>','desc',2),
 (17,'sortrank',0,'文章名',0,0,'${title }','asc',3),
 (18,'sortrank',0,'文章内容',0,0,'${body}','asc',3),
 (19,'sortrank',0,'分页信息',0,0,'第${page.pageNo}页, 共${page.totalPages}页','asc',3),
 (20,'sortrank',0,'分页首页',0,0,'<a href=\"${ctx}/html/list-${id}-1.html\">首页</a>','asc',3),
 (21,'sortrank',0,'上一页',0,0,'<s:if test=\"pagecontent.listLabel.get(pagecontent.temIterationId).page.result\"><a href=\"${ctx}/html/list-${id}-${pagecontent.listLabel[pagecontent.temIterationId].page.prePage}.html\">上一页</a></s:if>','asc',3),
 (22,'sortrank',0,'下一页',0,0,'<s:if test=\"pagecontent.listLabel.get(pagecontent.temIterationId).page.result\"><a href=\"${ctx}/html/list-${id}-${pagecontent.listLabel[pagecontent.temIterationId].page.nextPage}.html\">下一页</a></s:if>','asc',3),
 (23,'sortrank',0,'分页末页',0,0,'<a href=\"${ctx}/html/list-${id}-${pagecontent.listLabel[pagecontent.temIterationId].page.totalPages}.html\">末页</a>','asc',3),
 (24,'sortrank',10,'通用栏目',0,0,'<li><b><a href=\"${ctx}/html/list-${id}.html\">__${typename}___</a></b><span>${description}${keywords}</span></li>','asc',5),
 (25,'sortrank',0,'位置导航',0,0,'${pagecontent.position}','asc',3),
 (26,'sortrank',0,'留言板列表',0,0,'							<table class=\"guestTab\">\r\n								<tbody>\r\n									<tr>\r\n										<td width=\"100\" align=\"center\" rowspan=\"3\">\r\n											<img height=\"100\" width=\"100\" vspace=\"6\"\r\n												src=\"${ctx}/skin/system/images/pic/${face}.gif\">\r\n											<div style=\"text-align: center;font-size:12px;\">\r\n											[来自<span style=\"color: rgb(255, 0, 0);\">IP:${ip}</span>]\r\n											</div>\r\n										</td>\r\n										<td>\r\n											<div style=\"float: left; padding-top: 2px;\">\r\n												留言时间：\r\n												<s:date name=\"dtime\" format=\"yyyy-MM-dd\" />\r\n											</div>\r\n											<div style=\"float: right;\">\r\n												<a target=\"_blank\" href=\"${homepage}\"><img border=\"0\"\r\n														align=\"absmiddle\" style=\"display: inline;\"\r\n														src=\"${ctx}/skin/system/images/pic/homepage.gif\">\r\n												</a>\r\n												<a href=\"mailto:${email}\"><img border=\"0\"\r\n														align=\"absmiddle\" style=\"display: inline;\"\r\n														src=\"${ctx}/skin/system/images/pic/email.gif\">\r\n												</a>\r\n												<a target=\"blank\"\r\n													href=\"http://wpa.qq.com/msgrd?v=1&uin=${qq}&menu=yes\"><img\r\n														border=\"0\" align=\"absmiddle\" style=\"display: inline;\"\r\n														src=\"http://wpa.qq.com/pa?p=2:${qq}:10\">\r\n												</a>\r\n											</div>\r\n										</td>\r\n									</tr>\r\n									<tr>\r\n										<td height=\"60\" valign=\"top\" style=\"padding-top: 10px;\">\r\n											<p>\r\n												<samp style=\"color: rgb(0, 0, 255);\">\r\n													${uname}\r\n												</samp>\r\n												说：${msg}\r\n											</p>\r\n										</td>\r\n									</tr>\r\n									<tr>\r\n										<td bgcolor=\"#f3f3f3\">\r\n											<font color=\"#0000ff\">回复:</font>${replymsg}\r\n										</td>\r\n									</tr>\r\n								</tbody>\r\n							</table>\r\n						','asc',6),
 (29,'sortrank',0,'发留言',0,0,'<%@ include file=\"/common/guestbookSend.jsp\" %>','asc',3),
 (30,'sortrank',0,'网站名称',0,0,'${pagecontent.configFile.indexname}','asc',3),
 (31,'sortrank',0,'网站关键字',0,0,'${pagecontent.configFile.keywords}','asc',3),
 (32,'sortrank',0,'站点描述',0,0,'${pagecontent.configFile.description}','asc',3),
 (33,'sortrank',0,'当前栏目名',0,0,'${pagecontent.theTypeName}','asc',3),
 (34,'sortrank',0,'栏目路径',0,0,'${pagecontent.typeName}','asc',3),
 (35,'sortrank',0,'下一篇',0,0,'<s:if test=\"beforeId\">\r\n<a href=\"${ctx}/html/article-${beforeId}.html\">下一篇</a>\r\n</s:if>','asc',3),
 (36,'sortrank',0,'上一篇',0,0,'<s:if test=\"afterId\">\r\n<a href=\"${ctx}/html/article-${afterId}.html\">上一篇</a>\r\n</s:if>','asc',3),
 (37,'sortrank',0,'文章关键字',0,0,'${keywords }','asc',3),
 (38,'sortrank',0,'文章摘要',0,0,'${description}','asc',3),
 (39,'sortrank',0,'发评论',0,0,'<%@ include file=\"/common/feedbackSend.jsp\" %>','asc',3),
 (40,'sortrank',0,'通用分页号',0,0,' <s:bean name=\"org.apache.struts2.util.Counter\" id=\"counter\">\r\n        <s:param name=\"first\" value=\"1\" />\r\n        <s:param name=\"last\" value=\"%{pagecontent.listLabel.get(pagecontent.temIterationId).page.totalPages}\" />\r\n        <s:iterator>\r\n          <s:if test=\"%{pagecontent.listLabel.get(pagecontent.temIterationId).page.pageNo} == <s:property /> \"> <a href=\"javascript:;\" class=\"number current\"\r\n																	title=\"<s:property />\">\r\n            <s:property />\r\n            </a> </s:if>\r\n          <s:else> <a href=\"${ctx}/html/list-${id}-<s:property/>.html\"\r\n																	class=\"number\" title=\"<s:property />\">\r\n            <s:property />\r\n            </a> </s:else>\r\n        </s:iterator>\r\n      </s:bean>','asc',3),
 (41,'sortrank',0,'单页面标题',0,0,'${title}','asc',3),
 (42,'sortrank',0,'单页面内容',0,0,'${body}','asc',3),
 (43,'sortrank',0,'通用留言板列表',0,0,'							<table class=\"guestTab\">\r\n								<tbody>\r\n									<tr>\r\n										<td width=\"100\" align=\"center\" rowspan=\"3\">\r\n											<img height=\"100\" width=\"100\" vspace=\"6\"\r\n												src=\"${ctx}/skin/system/images/pic/${face}.gif\">\r\n											<div style=\"text-align: center;font-size:12px;\">\r\n											[来自<span style=\"color: rgb(255, 0, 0);\">IP:${ip}</span>]\r\n											</div>\r\n										</td>\r\n										<td>\r\n											<div style=\"float: left; padding-top: 2px;\">\r\n												留言时间：\r\n												<s:date name=\"dtime\" format=\"yyyy-MM-dd\" />\r\n											</div>\r\n											<div style=\"float: right;\">\r\n												<a target=\"_blank\" href=\"${homepage}\"><img border=\"0\"\r\n														align=\"absmiddle\" style=\"display: inline;\"\r\n														src=\"${ctx}/skin/system/images/pic/homepage.gif\">\r\n												</a>\r\n												<a href=\"mailto:${email}\"><img border=\"0\"\r\n														align=\"absmiddle\" style=\"display: inline;\"\r\n														src=\"${ctx}/skin/system/images/pic/email.gif\">\r\n												</a>\r\n												<a target=\"blank\"\r\n													href=\"http://wpa.qq.com/msgrd?v=1&uin=${qq}&menu=yes\"><img\r\n														border=\"0\" align=\"absmiddle\" style=\"display: inline;\"\r\n														src=\"http://wpa.qq.com/pa?p=2:${qq}:10\">\r\n												</a>\r\n											</div>\r\n										</td>\r\n									</tr>\r\n									<tr>\r\n										<td height=\"60\" valign=\"top\" style=\"padding-top: 10px;\">\r\n											<p>\r\n												<samp style=\"color: rgb(0, 0, 255);\">\r\n													${uname}\r\n												</samp>\r\n												说：${msg}\r\n											</p>\r\n										</td>\r\n									</tr>\r\n									<tr>\r\n										<td bgcolor=\"#f3f3f3\">\r\n											<font color=\"#0000ff\">回复:</font>${replymsg}\r\n										</td>\r\n									</tr>\r\n								</tbody>\r\n							</table>\r\n						','asc',6),
 (44,'sortrank',0,'留言板分页 ',0,0,'<s:if test=\"pageList.listLabel.get(pageList.temIterationId).page.result\"><a href=\"${ctx}/html/guestbook.html?pageNo=${pageList.listLabel[pageList.temIterationId].page.prePage}\">上一页</a></s:if>\r\n\r\n\r\n <s:bean name=\"org.apache.struts2.util.Counter\" id=\"counter\">\r\n        <s:param name=\"first\" value=\"1\" />\r\n        <s:param name=\"last\" value=\"%{pageList.listLabel.get(pageList.temIterationId).page.totalPages}\" />\r\n        <s:iterator>\r\n          <s:if test=\"%{pageList.listLabel.get(pageList.temIterationId).page.pageNo} == <s:property /> \"> <a href=\"javascript:;\" class=\"number current\"\r\n																	title=\"<s:property />\">\r\n            <s:property />\r\n            </a> </s:if>\r\n          <s:else> <a href=\"${ctx}/html/guestbook.html?pageNo=<s:property/>\"\r\n																	class=\"number\" title=\"<s:property />\">\r\n            <s:property />\r\n            </a> </s:else>\r\n        </s:iterator>\r\n      </s:bean>\r\n\r\n<s:if test=\"pageList.listLabel.get(pageList.temIterationId).page.result\"><a href=\"${ctx}/html/guestbook.html?pageNo=${pageList.listLabel[pageList.temIterationId].page.nextPage}\">下一页</a></s:if>\r\n','asc',3),
 (45,'sortrank',0,'评论列表',0,0,'<s:iterator value=\"feedbackList\">\r\n	ID:${id}<br/>\r\n	aid:${aid}<br/>\r\n	评论标题:${title}<br/>\r\n	评论时间:${dtime}<br/>\r\n	评论内容:${msg}<br/>\r\n</s:iterator>','asc',3),
 (46,'sortrank',0,'点击率',0,0,'<script language=\"javascript\" src=\"${ctx}/ajax/arclick.action?id=${id}\"></script>','asc',3),
 (47,'sortrank',0,'网站搜索',0,0,'<li><b><a href=\"${ctx}/html/article-${id}.html\">__${title}___</a></b><span><s:date name=\'senddate\' format=\'yyyy-MM-dd\'/></span></li>','asc',7),
 (48,'senddate',0,'测试商品',0,176,'<li><b><a href=\"${ctx}/html/shop-${id}.html\">_$${price}_${title}__$${trueprice}${brand}_${units}</a></b><span><s:date name=\'senddate\' format=\'yyyy-MM-dd\'/></span></li>','desc',8),
 (49,'sortrank',0,'通用商品测试',0,0,'<li><b><a href=\"${ctx}/html/shop-${id}.html\">__${title}___</a></b><span><s:date name=\'senddate\' format=\'yyyy-MM-dd\'/></span></li>','asc',9),
 (50,'sortrank',0,'下一篇名称',0,0,'<s:if test=\"beforeId\">\r\n<a href=\"${ctx}/html/article-${beforeId}.html\">下一篇：${beforeArc.title}</a>\r\n</s:if>','asc',3),
 (51,'sortrank',0,'上一篇名称',0,0,'<s:if test=\"afterId\">\r\n<a href=\"${ctx}/html/article-${afterId}.html\">上一篇：${afterArc.title}</a>\r\n</s:if>','asc',3);
/*!40000 ALTER TABLE `tag_label` ENABLE KEYS */;


--
-- Definition of table `tag_label_type`
--

DROP TABLE IF EXISTS `tag_label_type`;
CREATE TABLE `tag_label_type` (
  `id` bigint(20) NOT NULL auto_increment,
  `class_name` varchar(255) default NULL,
  `type_name` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tag_label_type`
--

/*!40000 ALTER TABLE `tag_label_type` DISABLE KEYS */;
INSERT INTO `tag_label_type` (`id`,`class_name`,`type_name`) VALUES 
 (1,'arcTypeLabel','栏目标签'),
 (2,'archiverLabel','文档标签'),
 (3,'staticLabel','静态标签'),
 (4,'generalArcPageLabel','通用文章标签'),
 (5,'generalArcTypeLabel','通用栏目标签'),
 (6,'guestBookLabel','留言板标签'),
 (7,'searcherLabel','搜索标签'),
 (8,'shopLabel','商品标签'),
 (9,'generalShopPageLabel','通用商品标签');
/*!40000 ALTER TABLE `tag_label_type` ENABLE KEYS */;


--
-- Definition of table `taglabel_arcatt`
--

DROP TABLE IF EXISTS `taglabel_arcatt`;
CREATE TABLE `taglabel_arcatt` (
  `taglabel_id` bigint(20) NOT NULL,
  `arcatt_id` bigint(20) NOT NULL,
  KEY `FK104DB354DC981CE1` (`arcatt_id`),
  KEY `FK104DB354DF731721` (`taglabel_id`),
  CONSTRAINT `FK104DB354DC981CE1` FOREIGN KEY (`arcatt_id`) REFERENCES `arcatt` (`id`),
  CONSTRAINT `FK104DB354DF731721` FOREIGN KEY (`taglabel_id`) REFERENCES `tag_label` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `taglabel_arcatt`
--

/*!40000 ALTER TABLE `taglabel_arcatt` DISABLE KEYS */;
INSERT INTO `taglabel_arcatt` (`taglabel_id`,`arcatt_id`) VALUES 
 (16,1),
 (16,6);
/*!40000 ALTER TABLE `taglabel_arcatt` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
