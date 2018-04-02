-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: smarketdb
-- ------------------------------------------------------
-- Server version	5.7.18-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `category_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='类别';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'手机','手机'),(2,'电脑','电脑'),(4,'女装','女装'),(6,'学习工具','学习工具'),(7,'运动','运动'),(8,'美食','美食'),(9,'家具','家具'),(10,'汽车','汽车'),(11,'电子产品','电子产品'),(12,'课表','课表');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `goods_id` bigint(20) unsigned DEFAULT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `comment_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (3,'辣鸡','2018-03-19 03:43:28',11,37),(9,'啊啊啊啊啊啊啊','2018-03-19 05:40:22',9,36),(10,'活该哈哈','2018-03-19 05:40:47',9,36),(11,'你爹','2018-03-19 05:44:06',9,36),(12,'2333','2018-03-19 06:26:34',8,22),(14,'23333','2018-03-19 07:29:35',14,22),(15,'2333','2018-03-19 08:09:56',13,22),(17,'fuck','2018-03-19 08:33:54',10,37),(19,'评论+1','2018-03-19 15:28:17',17,22),(20,'0','2018-03-19 15:28:51',16,22),(21,'啊','2018-03-19 16:42:13',19,22),(22,'啊','2018-03-21 12:42:24',16,38),(23,'辣鸡东西谁买呢','2018-03-21 14:09:56',23,22),(25,'啊啊啊<br>','2018-03-22 07:28:53',24,22),(26,'aaa<br>aaa','2018-03-22 07:29:06',24,22),(27,'辣鸡玩意','2018-03-23 12:23:46',23,22),(28,'欲购从速！','2018-03-23 16:40:32',26,43),(29,'啊','2018-03-24 17:51:29',18,22),(30,'哈哈','2018-03-24 17:51:36',11,22),(31,'想喝','2018-03-24 18:01:26',29,45),(32,'为什么呢','2018-03-26 10:47:11',25,22),(33,'哈哈哈哈哈哈哈哈','2018-03-27 00:51:48',36,49),(34,'辣鸡','2018-03-27 00:52:00',35,49),(35,'11111','2018-03-27 01:08:21',36,22),(36,'评论','2018-04-01 09:15:26',33,22);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goods`
--

DROP TABLE IF EXISTS `goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goods` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `description` text,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_change_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `view_number` bigint(20) unsigned DEFAULT '0',
  `is_sell` tinyint(3) unsigned DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `goods_id_uindex` (`id`),
  KEY `goods_user_id_fk` (`user_id`),
  CONSTRAINT `goods_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COMMENT='出售物品';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods`
--

LOCK TABLES `goods` WRITE;
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
INSERT INTO `goods` VALUES (10,'test',222.00,'test','2018-03-18 08:56:15','2018-03-18 08:56:15',36,5,0),(11,'sss',98000000.00,'no','2018-03-19 03:43:00','2018-03-19 03:43:00',37,30,0),(15,'测试一下长一点的标题啊啊啊啊啊啊啊啊',233.00,'啊啊啊啊aaaa','2018-03-19 08:17:44','2018-03-19 15:27:34',22,4,0),(16,'test',112.00,'testaaaa','2018-03-19 15:21:46','2018-03-21 15:56:16',22,4,0),(18,'随便起个名先',1111.00,'aaaaaa','2018-03-19 15:38:11','2018-03-19 15:52:42',22,19,0),(23,'出售极品26速招财猫',999.00,'出售极品5号位26速招财猫，欲购从速！！','2018-03-21 13:41:56','2018-03-21 13:41:56',43,24,1),(24,'test goods',233.00,'啊啊啊','2018-03-22 07:28:14','2018-03-22 07:28:14',22,12,0),(25,'便宜一点的',333.00,'啊ԅ(¯㉨¯ԅ)啊','2018-03-23 16:36:52','2018-03-23 16:36:52',22,7,0),(26,'aaaaaaaaaa',423.00,'fafewf awefw faf waefwae<strong>强壮</strong>','2018-03-23 16:39:24','2018-03-23 16:39:24',43,19,0),(27,'Hasee/神舟 战神1060独显 吃鸡游戏本笔记本电脑',4899.00,'版本类型: 中国大陆能效等级: 三级品牌: Hasee/神舟神舟系列型号: 优雅系列优雅系列配置: 优雅A470P-B8D2屏幕尺寸: 15.6CPU: 英特尔 酷睿 i7-7700HQ显卡类型: NVIDIA GeForce GTX1060 (Desktop)显存容量: 6G机械硬盘容量: 1t内存容量: 8GB适用场景: 学生 商务办公 尊贵旗舰售后服务: 全国联保颜色分类: Z7-KP7D2 8G 1TB GTX1060 Z7-KP7D2游戏版 8G 1T 72% Z7-KP7GT 8G 128G+1TB Z7-KP7GT战斗版 8G 128+1T 72% Z7-KP7GT精英版 16G 128+1T 72% Z7-KP7GT PCIE 128 45% Z7-KP7GT吃鸡版 PCIE 120HZ IPS Z7-KP7GT战斗版 16G PCIE 128 Z7-KP7GT精英版 16G PCIE 72% Z7-KP7GT尊享A PCIE 256 Z7-KP7GT尊享版 PCIE 256 72% Z7-KP7GT至尊版A 16G PCIE 256 Z7-KP7GT至尊版 PCIE 256 72% T6TI-X5 8G 128+1TB T6TI-X5战斗版 8G 1TB T6TI-X5精英版 8G 72色域 Z7-KP7GT乐享版 120HZ电竞屏 Z7M-KP7G1 经济版 45色域 Z7M-KP7G1 72色域 GTX1050TI Z7-KP7S1 8G 256G+1TB Z7-KP7S1游戏版 72色域 Z7-KP7GS 8G 256G+1TB 72% Z7-KP7GS 16G版 256G+1TB 72% Z7-KP7GT游戏版 PCIE 128 72%通信技术类型: 3G套餐类型: 套餐一 套餐二 套餐三 官方标配分辨率: 1920x1080是否触摸屏: 非触摸屏','2018-03-24 05:48:41','2018-03-24 05:54:05',22,12,0),(30,'苹果6、16G、带指纹',1200.00,'苹果616G国行指纹识别灵敏、自用换新手机了，无拆无修，全原装正品，无暗病，系统10.33，外观较新，无锁无lD，装卡就能使用，非卡贴，非官换黑机，可刷机，（刷机有风险、请找专业维修师傅刷机）可查序列号，信息与机壳一致，可小刀，三天手机自身质量问题包退（注：拆机，进水，摔坏，自己刷机出现问题、不退货）','2018-03-25 07:39:13','2018-03-26 16:24:42',22,9,0),(32,'#Macbook#闲置苹果电脑便宜出售',1780.00,'出自用办公苹果笔记本电脑 ，电脑买来一直保护的很好，无暗病，无刮痕，九成新，贴了膜。由于工作调动，电脑一直没怎么用过，现在低价转手给更有需要的朋友。 配置，独立显卡.运行4g内存 320G硬盘，13.3寸屏幕苹果和win7双系统 蓝牙 无线 摄像头dv都有，运行流畅，网络游戏，带有鼠标，充电器，键盘膜，电脑包需要了解的联系哦！没诚意的请勿扰','2018-03-25 07:48:37','2018-03-25 07:48:37',22,15,0),(33,'苹果笔记本电脑',2450.00,'苹果笔记本电脑 苹果笔记本电脑 刚上班电脑很少用自己也缺钱 就把心爱的电脑卖了去 以后赚到钱买个新的用 需要的哥哥姐姐们欢迎骚扰电脑详细配置：4G运行内存 高清13.3寸屏幕 装了双系统（苹果系统和 win7系统）电脑一点都不卡上网看视频 非常流畅 也可以打游戏 ，价格很便宜了诚心要私聊我','2018-03-25 07:57:17','2018-03-25 07:57:17',22,12,0),(34,'尼康D90经典单反配适马28_70的头',2200.00,'盒子，发票什么都齐全。很少玩了，闲置在家卖了。三个电池，快门线，手柄，都松了，7788小配件也很多。反正很齐全，拿回去直接用。这个价格不刀，还价勿扰。快门次数不超过一万。头脑发热买来的，不会玩，放家里四年了。爽快的2100，发顺丰，邮费你付。其他还价勿扰。','2018-03-25 08:26:11','2018-03-26 10:32:08',22,10,1),(36,'出售偷拍照一张',0.50,'美滋滋的一张照片','2018-03-26 14:02:52','2018-03-26 14:02:52',47,9,0);
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goods_category`
--

DROP TABLE IF EXISTS `goods_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goods_category` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `goods_id` bigint(20) unsigned DEFAULT NULL,
  `category_id` smallint(5) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `goods_category_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods_category`
--

LOCK TABLES `goods_category` WRITE;
/*!40000 ALTER TABLE `goods_category` DISABLE KEYS */;
INSERT INTO `goods_category` VALUES (17,10,2),(18,11,3),(31,15,2),(36,18,1),(37,18,3),(42,23,3),(43,16,3),(44,24,2),(45,25,1),(46,26,1),(48,27,2),(53,32,2),(54,33,2),(71,34,11),(73,36,7),(74,30,1);
/*!40000 ALTER TABLE `goods_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goods_image`
--

DROP TABLE IF EXISTS `goods_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goods_image` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `goods_id` bigint(20) unsigned DEFAULT NULL,
  `image_id` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `goods_image_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods_image`
--

LOCK TABLES `goods_image` WRITE;
/*!40000 ALTER TABLE `goods_image` DISABLE KEYS */;
INSERT INTO `goods_image` VALUES (5,10,5),(6,11,6),(12,15,12),(13,16,13),(14,16,14),(25,15,25),(26,18,26),(27,NULL,27),(28,NULL,28),(29,NULL,29),(30,NULL,30),(31,NULL,31),(32,NULL,32),(33,NULL,33),(34,23,34),(35,23,35),(36,24,36),(37,25,37),(38,25,38),(39,26,39),(40,26,40),(41,27,41),(42,28,42),(43,28,43),(44,18,44),(45,29,45),(46,30,46),(47,30,47),(48,31,48),(49,31,49),(50,31,50),(51,31,51),(52,32,52),(53,32,53),(54,32,54),(55,33,55),(56,33,56),(57,33,57),(58,34,58),(60,35,60),(61,36,61);
/*!40000 ALTER TABLE `goods_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goods_order`
--

DROP TABLE IF EXISTS `goods_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goods_order` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `order_number` varchar(100) NOT NULL,
  `goods_id` bigint(20) unsigned DEFAULT NULL,
  `buyer_user_id` bigint(20) unsigned DEFAULT NULL,
  `seller_user_id` bigint(20) unsigned DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `order_status` tinyint(3) unsigned DEFAULT '0' COMMENT '0:未完成 1:已完成 2:已关闭',
  `recipients` varchar(4) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `finish_time` timestamp NULL DEFAULT NULL,
  `goods_price` decimal(10,2) DEFAULT NULL,
  `goods_name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_id_uindex` (`id`),
  UNIQUE KEY `order_order_number_uindex` (`order_number`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='订单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods_order`
--

LOCK TABLES `goods_order` WRITE;
/*!40000 ALTER TABLE `goods_order` DISABLE KEYS */;
INSERT INTO `goods_order` VALUES (21,'1522047914763',23,22,43,'2018-03-26 07:05:14',1,'echi','aaa123','15521319461','2018-03-26 07:05:15',999.00,'出售极品26速招财猫'),(22,'1522081278459',35,22,47,'2018-03-26 16:21:18',0,'echi','美滋滋','15521319461',NULL,0.50,'出售课程表'),(23,'1522081289469',35,22,47,'2018-03-26 16:21:29',2,'echi','美滋滋','15521319461','2018-03-26 16:21:36',0.50,'出售课程表'),(24,'1522112550470',34,44,22,'2018-03-27 01:02:30',1,'ss','afwef','1222','2018-03-27 01:02:30',2200.00,'尼康D90经典单反配适马28_70的头'),(25,'1522112713874',36,22,47,'2018-03-27 01:05:13',2,'echi','美滋滋','15521319461','2018-03-27 01:05:24',0.50,'出售偷拍照一张');
/*!40000 ALTER TABLE `goods_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `image` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `image_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (1,'/image/2018/3/17/1521299446974.png'),(2,'/image/2018/3/17/1521299610100.jpg'),(3,'/image/2018/3/17/1521299610105.jpg'),(4,'/image/2018/4/18/1521357493875.png'),(5,'/image/2018/4/18/1521363373261.jpg'),(6,'/image/2018/4/19/1521430973779.jpg'),(7,'/image/2018/4/19/1521441248873.jpg'),(8,'/image/2018/4/19/1521441409317.jpg'),(9,'/image/2018/4/19/1521441409328.jpg'),(10,'/image/2018/4/19/1521444014095.jpg'),(11,'/image/2018/4/19/1521444014107.jpg'),(12,'/image/2018/4/19/1521447461760.jpg'),(13,'/image/2018/4/19/1521453585546.jpg'),(14,'/image/2018/4/19/1521453585553.jpg'),(15,'/image/2018/4/19/1521466071642.jpg'),(16,'/image/2018/4/19/1521466071650.jpg'),(17,'/image/2018/4/19/1521466071660.jpg'),(18,'/image/2018/4/19/1521466071666.jpg'),(19,'/image/2018/4/19/1521466071724.jpg'),(20,'/image/2018/4/19/1521473888710.jpg'),(21,'/image/2018/4/20/1521477694372.jpg'),(22,'/image/2018/4/20/1521477694379.jpg'),(23,'/image/2018/3/20/1521510590651.jpg'),(24,'/image/2018/3/20/1521510590658.jpg'),(25,'/image/2018/3/20/1521512913373.jpg'),(26,'/image/2018/3/20/1521513873242.jpg'),(27,'/image/2018/3/21/1521637834628.jpg'),(28,'/image/2018/3/21/1521637834655.jpg'),(29,'/image/2018/3/21/1521637971205.jpg'),(30,'/image/2018/3/21/1521637971213.jpg'),(31,'/image/2018/3/21/1521638051225.png'),(32,'/image/2018/3/21/1521639281290.jpg'),(33,'/image/2018/3/21/1521639281296.jpg'),(34,'/image/2018/3/21/1521639714513.jpg'),(35,'/image/2018/3/21/1521639714522.jpg'),(36,'/image/2018/4/22/1521703693000.png'),(37,'/image/2018/4/24/1521823007425.jpg'),(38,'/image/2018/4/24/1521823007433.jpg'),(39,'/image/2018/4/24/1521823144815.png'),(40,'/image/2018/4/24/1521823162300.png'),(41,'/image/2018/4/24/1521870519226.png'),(42,'/image/2018/4/24/1521878982904.png'),(43,'/image/2018/4/24/1521878998984.jpg'),(44,'/image/2018/4/25/1521914144840.png'),(45,'/image/2018/4/25/1521914422595.jpg'),(46,'/image/2018/4/25/1521963551548.jpg'),(47,'/image/2018/4/25/1521963551564.jpg'),(48,'/image/2018/4/25/1521963996462.jpg'),(49,'/image/2018/4/25/1521963996473.jpg'),(50,'/image/2018/4/25/1521963996481.jpg'),(51,'/image/2018/4/25/1521963996488.jpg'),(52,'/image/2018/4/25/1521964114630.jpg'),(53,'/image/2018/4/25/1521964114656.jpg'),(54,'/image/2018/4/25/1521964114664.jpg'),(55,'/image/2018/4/25/1521964635239.jpg'),(56,'/image/2018/4/25/1521964635247.jpg'),(57,'/image/2018/4/25/1521964635592.jpg'),(58,'/image/2018/3/25/1521966369264.jpg'),(59,'/image/2018/3/26/1522050344885.jpg'),(60,'/image/2018/3/26/1522072523642.jpg'),(61,'/image/2018/3/26/1522072968038.jpg');
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission` (
  `id` tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
  `code_name` varchar(50) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `description` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `permission_id_uindex` (`id`),
  UNIQUE KEY `permission_code_name_uindex` (`code_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
  `code_name` varchar(20) NOT NULL,
  `description` varchar(30) DEFAULT NULL COMMENT '角色的描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_id_uindex` (`id`),
  UNIQUE KEY `role_code_name_uindex` (`code_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'super_admin','超级管理员'),(2,'normal_admin','普通管理员'),(3,'normal_user','普通用户');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_permission` (
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `role_id` tinyint(3) unsigned DEFAULT NULL,
  `permission_id` tinyint(3) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_permission_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `salt` varchar(50) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_login_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_lock` tinyint(3) unsigned DEFAULT '0' COMMENT '1:是 0:否',
  `face` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_uindex` (`id`),
  UNIQUE KEY `user_username_uindex` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (22,'echisan','c82a028434aece560f40ddfaf1ba7762','3472c7a4720216c1827674af3643bbfb','2018-03-08 11:00:42','2018-03-08 11:00:42',0,NULL),(23,'test','b2de8f86c531b1186d650ffa426f60b3','7f683df44201232cb119b5b4e46c5a7f','2018-03-08 15:26:46','2018-03-08 15:59:13',0,NULL),(29,'echisan23','0864869f78f818f012357b945a5e9b1d','432e8d99c7ebc87d62aaa6faeba6d2e2','2018-03-12 16:32:54','2018-03-12 16:32:54',0,NULL),(30,'echisaneeee','47bce5c74f589f4867dbd57e9ca9f808','d5eab65e7197db13122ff022ad460d3c','2018-03-12 16:37:52','2018-03-12 16:37:52',0,NULL),(31,'echisanrrr','47bce5c74f589f4867dbd57e9ca9f808','b01dca13d25947714c58e12bc86f2864','2018-03-13 00:49:22','2018-03-13 00:49:22',0,NULL),(32,'eeee','47bce5c74f589f4867dbd57e9ca9f808','a15217eca00d9b5a8eda9208c539f299','2018-03-13 00:51:04','2018-03-13 00:51:04',0,NULL),(36,'admin','b83bb9f8ef6881cc6ff978ef706f8f43','f42253bba27b22adaa6faed7dd316f39','2018-03-18 07:57:10','2018-03-18 07:57:10',0,NULL),(37,'pltm','5ca1995bf287920f25d65232e0a7dcd8','652d73abb932f3f3b6e763335a737caf','2018-03-19 03:41:22','2018-03-19 03:41:22',0,NULL),(38,'testa','53d4498af86cacfdb36b5b4b9b96211c','b136885574b9be435bcff419ae70130a','2018-03-21 12:26:09','2018-03-21 12:26:09',0,NULL),(41,'hahaha','24135fae491428dcc3757921728e93e0','21f15c83e1333bfb3acc9e44e2a0322b','2018-03-21 13:23:59','2018-03-21 13:23:59',0,NULL),(43,'gaygui','fec212ab351ca85106c4b50968c51667','333512f1a457f102735df12615c14033','2018-03-21 13:33:27','2018-03-21 13:33:27',0,NULL),(44,'123','93610f130c4c536000e540c48720f658','88003a640d60e2858d3407dd420bd7a5','2018-03-24 08:07:52','2018-03-24 08:07:52',0,NULL),(45,'echi','9f6691a9a0920b100e4a9ef4e3144602','63b40cfb0bd7d820a23c3df23c6eaf67','2018-03-24 18:01:11','2018-03-24 18:01:11',0,NULL),(46,'aaaa','5b8986194dbdbed349aca2ceec278cb2','92e3c8e4b26b764566b8df6a1195a293','2018-03-26 13:48:50','2018-03-26 13:48:50',0,NULL),(47,'bbb','12ebb3a830f094834483eec6697de050','f4f0e61571347ad436800d7ebd707075','2018-03-26 13:51:37','2018-03-26 13:51:37',0,NULL),(48,'ccc','52d53a69776d64b02c5922009b695254','d3c86c4124b7b1ec235be6e6235b30df','2018-03-26 16:53:42','2018-03-26 16:53:42',0,NULL),(49,'eee','9d10784869c658fc323179578e437e7e','3acbfcb2963cfbf81e350e206911e9c3','2018-03-27 00:51:38','2018-03-27 00:51:38',0,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_address`
--

DROP TABLE IF EXISTS `user_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_address` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `is_default_address` tinyint(3) unsigned DEFAULT '1',
  `user_id` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_address_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_address`
--

LOCK TABLES `user_address` WRITE;
/*!40000 ALTER TABLE `user_address` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `real_name` varchar(4) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_info_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES (1,22,'echi','15521319461','美滋滋'),(2,43,'eee','12344333','afwfaregratgt'),(3,45,'古拉','4535345','阿格拉按合同罗老师同楼上'),(4,47,'安委会','234324','agwfgraegfgdrg'),(5,44,'ss','1222','afwef');
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_lock_info`
--

DROP TABLE IF EXISTS `user_lock_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_lock_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `target_user_id` bigint(20) unsigned DEFAULT NULL,
  `execute_user_id` bigint(20) unsigned DEFAULT NULL,
  `execute_lock_type` tinyint(3) unsigned DEFAULT NULL,
  `execute_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_lock_info_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_lock_info`
--

LOCK TABLES `user_lock_info` WRITE;
/*!40000 ALTER TABLE `user_lock_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_lock_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `role_id` tinyint(3) unsigned DEFAULT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_role_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (2,3,21),(3,1,22),(4,1,23),(5,3,24),(6,3,25),(7,3,26),(8,3,27),(9,3,28),(10,3,29),(11,3,30),(12,3,31),(13,3,32),(14,3,33),(15,3,34),(16,3,35),(17,3,36),(18,3,37),(19,3,38),(20,3,39),(21,3,40),(22,3,41),(23,3,42),(24,3,43),(25,3,44),(26,3,45),(27,3,46),(28,3,47),(29,3,48),(30,3,49);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wallet`
--

DROP TABLE IF EXISTS `wallet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wallet` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `balance` decimal(10,2) DEFAULT '0.00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `wallet_id_uindex` (`id`),
  KEY `wallet_user_id_fk` (`user_id`),
  CONSTRAINT `wallet_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wallet`
--

LOCK TABLES `wallet` WRITE;
/*!40000 ALTER TABLE `wallet` DISABLE KEYS */;
INSERT INTO `wallet` VALUES (1,22,991335.01),(2,43,994640.01),(3,44,991335.01),(4,45,334.00),(5,37,996638.01),(6,47,123.00),(7,48,0.00);
/*!40000 ALTER TABLE `wallet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wallet_record`
--

DROP TABLE IF EXISTS `wallet_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wallet_record` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `behaviour_type` tinyint(3) unsigned DEFAULT NULL,
  `money` decimal(10,2) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `remarks` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `wallet_recode_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wallet_record`
--

LOCK TABLES `wallet_record` WRITE;
/*!40000 ALTER TABLE `wallet_record` DISABLE KEYS */;
INSERT INTO `wallet_record` VALUES (1,22,1,450.00,'2018-03-23 09:11:30','充值'),(2,22,0,222.00,'2018-03-23 09:12:01','test'),(3,22,1,9999.00,'2018-03-23 12:23:24','充值'),(4,22,0,999.00,'2018-03-23 12:23:55','出售极品26速招财猫'),(5,43,1,999999.00,'2018-03-23 13:19:43','充值'),(6,43,0,233.00,'2018-03-23 13:20:14','test goods'),(7,43,0,233.00,'2018-03-23 13:31:06','测试一下长一点的标题啊啊啊啊啊啊啊啊'),(8,43,0,333.00,'2018-03-23 16:37:15','便宜一点的'),(9,43,0,112.00,'2018-03-23 16:44:07','test'),(10,43,1,0.01,'2018-03-23 16:45:25','充值'),(11,22,1,200.00,'2018-03-24 08:01:06','充值'),(12,22,1,1.00,'2018-03-24 08:01:56','充值'),(13,22,1,1.00,'2018-03-24 08:02:00','充值'),(14,22,1,222.00,'2018-03-24 08:02:04','充值'),(15,22,1,20.00,'2018-03-24 08:02:08','充值'),(16,22,1,100.00,'2018-03-24 08:02:12','充值'),(17,22,1,20000.00,'2018-03-24 08:02:18','充值'),(18,22,1,3.00,'2018-03-24 08:02:25','充值'),(19,22,1,30.00,'2018-03-24 08:02:30','充值'),(20,22,1,300.00,'2018-03-24 08:02:35','充值'),(21,22,1,3000.00,'2018-03-24 08:02:40','充值'),(22,22,1,1.00,'2018-03-24 08:02:47','充值'),(23,22,1,2.00,'2018-03-24 08:02:50','充值'),(24,22,1,3.00,'2018-03-24 08:02:54','充值'),(25,22,1,4.00,'2018-03-24 08:02:57','充值'),(26,22,1,5.00,'2018-03-24 08:03:00','充值'),(27,22,1,5.00,'2018-03-24 08:03:04','充值'),(28,44,1,100000.00,'2018-03-24 08:11:16','充值'),(29,44,0,4899.00,'2018-03-24 08:11:49','Hasee/神舟 战神1060独显 吃鸡游戏本笔记本电脑'),(30,43,0,123.00,'2018-03-24 08:20:58','哈额晚饭'),(31,44,1,123.00,'2018-03-24 08:20:58','哈额晚饭'),(32,45,1,1.00,'2018-03-24 18:03:42','充值'),(33,22,0,423.00,'2018-03-25 11:49:44','aaaaaaaaaa'),(34,44,0,2450.00,'2018-03-25 11:51:07','苹果笔记本电脑'),(35,22,1,2450.00,'2018-03-25 11:51:07','苹果笔记本电脑'),(36,44,0,1780.00,'2018-03-25 11:51:32','#Macbook#闲置苹果电脑便宜出售'),(37,44,0,1200.00,'2018-03-25 11:54:46','苹果6、16G、带指纹'),(38,22,1,123.00,'2018-03-25 15:36:48','充值'),(39,45,1,333.00,'2018-03-25 16:44:33','充值'),(40,22,1,98000000.00,'2018-03-26 06:13:57','充值'),(41,22,0,98000000.00,'2018-03-26 06:14:33','sss'),(42,37,1,98000000.00,'2018-03-26 06:14:33','sss'),(43,22,0,999.00,'2018-03-26 07:05:14','出售极品26速招财猫'),(44,43,1,999.00,'2018-03-26 07:05:14','出售极品26速招财猫'),(45,47,1,123.00,'2018-03-26 13:54:05','充值'),(46,44,0,2200.00,'2018-03-27 01:02:30','尼康D90经典单反配适马28_70的头'),(47,22,1,2200.00,'2018-03-27 01:02:30','尼康D90经典单反配适马28_70的头');
/*!40000 ALTER TABLE `wallet_record` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-01 18:50:12
