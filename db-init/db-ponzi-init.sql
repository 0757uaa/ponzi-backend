-- MySQL dump 10.13  Distrib 5.7.28, for Win64 (x86_64)
--
-- Host: localhost    Database: ponzi
-- ------------------------------------------------------
-- Server version	5.7.26

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
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menu` (
  `id` varchar(64) NOT NULL,
  `menu_name` varchar(20) NOT NULL COMMENT '菜单名称',
  `menu_title` varchar(100) DEFAULT NULL COMMENT '备用字段',
  `pid` varchar(64) NOT NULL DEFAULT '#' COMMENT '父菜单',
  `menu_icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `route_path` varchar(100) DEFAULT NULL COMMENT '路由路径',
  `route_name` varchar(50) DEFAULT NULL COMMENT '路由名称',
  `route_component` varchar(100) DEFAULT NULL COMMENT '路由组件',
  `deleted` tinyint(1) NOT NULL COMMENT '0-有效,1-无效',
  `disabled` tinyint(1) NOT NULL COMMENT '0-启用,1-禁用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `disabled_time` datetime DEFAULT NULL COMMENT '禁用时间',
  `version` timestamp NULL DEFAULT NULL COMMENT '乐观锁',
  `orders` int(11) DEFAULT '0' COMMENT '排序(越小越靠前)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统菜单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES ('1331444639905452034','系统管理',NULL,'#','dashboard','sys','sys','Sys/index',0,0,'2020-11-25 11:48:34',NULL,NULL,'2020-11-25 03:48:34',0),('1331471364634775553','用户管理',NULL,'1331444639905452034',NULL,'admin_manage','admin_manage_index','Sys/Admin/index',0,0,'2020-11-25 13:34:46',NULL,NULL,'2020-11-25 05:34:46',1),('1331471404753293314','角色管理',NULL,'1331444639905452034',NULL,'role_manage','role_manage_index','Sys/Role/index',0,0,'2020-11-25 13:34:55',NULL,NULL,'2020-11-25 05:34:55',2),('1331471508776226818','菜单管理',NULL,'1331444639905452034',NULL,'menu_manage','menu_manage_index','Sys/Menu/index',0,0,'2020-11-25 13:35:20',NULL,NULL,'2020-11-25 05:35:20',4),('1335863897909456898','账务管理',NULL,'#','','money','money','Money/index',0,0,'2020-12-07 16:29:07',NULL,NULL,'2020-12-07 08:29:07',1),('1335865602759815169','赠送产品',NULL,'1335863897909456898','','product_gift','product_gift','Money/productGift',0,0,'2020-12-07 16:35:54',NULL,NULL,'2020-12-07 08:35:54',1);
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_permission`
--

DROP TABLE IF EXISTS `sys_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_permission` (
  `id` varchar(64) NOT NULL,
  `permission_name` varchar(100) NOT NULL COMMENT '权限名称',
  `permission_code` varchar(100) NOT NULL COMMENT '权限代码',
  `permission_type` smallint(2) NOT NULL COMMENT '权限类型: 0-url访问, 1-按钮操作',
  `sys_menu_id` varchar(64) NOT NULL COMMENT 'sys_menu.id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `permission_code` (`permission_code`),
  KEY `fk_permission_menu_id` (`sys_menu_id`),
  CONSTRAINT `fk_permission_menu_id` FOREIGN KEY (`sys_menu_id`) REFERENCES `sys_menu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_permission`
--

LOCK TABLES `sys_permission` WRITE;
/*!40000 ALTER TABLE `sys_permission` DISABLE KEYS */;
INSERT INTO `sys_permission` VALUES ('1','管理员-按钮-查询','admin:button:query',1,'1331471364634775553'),('10','管理员-按钮-禁用','admin:button:disable',1,'1331471364634775553'),('11','管理员-链接-启用','admin:link:enable',0,'1331471364634775553'),('12','管理员-链接-禁用','admin:link:disable',0,'1331471364634775553'),('1338735367220228097','管理员-链接-详细-保存','admin:link:detail:save',0,'1331471364634775553'),('1339866502885072897','角色-链接-列表','role:link:list',0,'1331471404753293314'),('1339867447819821058','角色-按钮-添加','role:button:add',1,'1331471404753293314'),('1339867820932521986','角色-链接-添加-保存','role:link:add:save',0,'1331471404753293314'),('1339867906701844481','角色-按钮-添加-保存','role:button:add:save',1,'1331471404753293314'),('1339868133269757954','角色-按钮-删除','role:button:delete',1,'1331471404753293314'),('1339868188164808705','角色-链接-删除','role:link:delete',0,'1331471404753293314'),('1339868611932119042','角色-按钮-设置菜单权限','role:button:set_menu_permission',1,'1331471404753293314'),('1339868784896827394','角色-链接-保存设置','role:link:save_set',0,'1331471404753293314'),('1340196113212862466','角色-按钮-保存设置','role:button:save_set',1,'1331471404753293314'),('1340198732253720577','角色-链接-设置菜单权限','role:link:set_menu_permission',0,'1331471404753293314'),('1340217963246522369','菜单-链接-列表','menu:link:list',0,'1331471508776226818'),('1340218070033502210','菜单-按钮-添加菜单','menu:button:add',1,'1331471508776226818'),('1340218423432974338','菜单-按钮-添加菜单-保存','menu:button:add:save',1,'1331471508776226818'),('1340218538965078017','菜单-链接-添加菜单-保存','menu:link:add:save',0,'1331471508776226818'),('1340218915030568962','菜单-按钮-添加子菜单','menu:button:add_sub',1,'1331471508776226818'),('1340219422788816898','菜单-按钮-删除','menu:button:delete',1,'1331471508776226818'),('1340219478564671489','菜单-链接-删除','menu:link:delete',0,'1331471508776226818'),('1340219706277629954','菜单-按钮-权限设置','menu:button:permission_set',1,'1331471508776226818'),('1340220071328878594','菜单-链接-权限设置-菜单权限-列表','menu:link:permission_set:menu_permission:list',0,'1331471508776226818'),('1340220355862073345','菜单-按钮-权限设置-菜单权限-删除','menu:button:permission_set:menu_permission:delete',1,'1331471508776226818'),('1340220429501468673','菜单-链接-权限设置-菜单权限-删除','menu:link:permission_set:menu_permission:delete',0,'1331471508776226818'),('1340220714990964737','菜单-链接-权限设置-添加权限-提交','menu:link:permission_set:add_permission:save',0,'1331471508776226818'),('1340220800898699265','菜单-按钮-权限设置-添加权限-提交','menu:button:permission_set:add_permission:save',1,'1331471508776226818'),('2','管理员-链接-列表','admin:link:query:list',0,'1331471364634775553'),('3','管理员-按钮-新增','admin:button:add',1,'1331471364634775553'),('4','管理员-按钮-新增-保存','admin:button:add:save',1,'1331471364634775553'),('5','管理员-按钮-编辑','admin:button:edit',1,'1331471364634775553'),('6','管理员-链接-详细','admin:link:edit:detail',0,'1331471364634775553'),('7','管理员-按钮-编辑-保存','admin:button:edit:save',1,'1331471364634775553'),('8','管理员-链接-新增-保存','admin:link:add:save',0,'1331471364634775553'),('9','管理员-按钮-启用','admin:button:enable',1,'1331471364634775553');
/*!40000 ALTER TABLE `sys_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `id` varchar(64) NOT NULL,
  `role_code` varchar(30) NOT NULL COMMENT '角色代码',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `deleted` tinyint(1) NOT NULL COMMENT '0-有效,1-无效',
  `disabled` tinyint(1) NOT NULL COMMENT '0-启用,1-禁用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `disabled_time` datetime DEFAULT NULL COMMENT '禁用时间',
  `version` timestamp NULL DEFAULT NULL COMMENT '乐观锁',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_code` (`role_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES ('1','admin','管理员',0,0,'2020-11-18 17:12:48',NULL,NULL,'2020-11-18 09:12:48'),('1338673731423780865','finance','财务员',0,0,'2020-12-15 10:34:24',NULL,NULL,'2020-12-15 02:34:24'),('1339870832828690433','admin_view','管理员_查看',0,0,'2020-12-18 17:51:15',NULL,NULL,'2020-12-18 09:51:15');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_menu` (
  `id` varchar(64) NOT NULL,
  `sys_role_id` varchar(64) NOT NULL,
  `sys_menu_id` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` VALUES ('1339813496521023490','1338673731423780865','1335863897909456898'),('1339813496663629825','1338673731423780865','1335865602759815169'),('1340221014514601986','1','1331444639905452034'),('1340221014514601987','1','1331471364634775553'),('1340221014527184898','1','1331471404753293314'),('1340221014531379201','1','1331471508776226818'),('1340221121939116033','1339870832828690433','1331444639905452034'),('1340221121939116034','1339870832828690433','1331471364634775553'),('1340221121943310337','1339870832828690433','1331471404753293314'),('1340221121951698945','1339870832828690433','1331471508776226818');
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_permission`
--

DROP TABLE IF EXISTS `sys_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_permission` (
  `id` varchar(64) NOT NULL,
  `sys_permission_id` varchar(30) NOT NULL COMMENT 'sys_permission.id',
  `sys_role_id` varchar(30) NOT NULL COMMENT 'sys_role.id',
  PRIMARY KEY (`id`),
  KEY `sys_permission_id` (`sys_permission_id`),
  KEY `sys_role_id` (`sys_role_id`),
  CONSTRAINT `sys_role_permission_ibfk_1` FOREIGN KEY (`sys_permission_id`) REFERENCES `sys_permission` (`id`),
  CONSTRAINT `sys_role_permission_ibfk_2` FOREIGN KEY (`sys_role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_permission`
--

LOCK TABLES `sys_role_permission` WRITE;
/*!40000 ALTER TABLE `sys_role_permission` DISABLE KEYS */;
INSERT INTO `sys_role_permission` VALUES ('1340221014543962114','1','1'),('1340221014992752641','10','1'),('1340221014996946945','11','1'),('1340221015001141249','12','1'),('1340221015001141250','1338735367220228097','1'),('1340221015005335554','2','1'),('1340221015009529858','3','1'),('1340221015009529859','4','1'),('1340221015013724162','5','1'),('1340221015013724163','6','1'),('1340221015017918465','7','1'),('1340221015017918466','8','1'),('1340221015022112770','9','1'),('1340221015026307073','1339866502885072897','1'),('1340221015026307074','1339867447819821058','1'),('1340221015030501377','1339867820932521986','1'),('1340221015030501378','1339867906701844481','1'),('1340221015034695681','1339868133269757954','1'),('1340221015034695682','1339868188164808705','1'),('1340221015038889985','1339868611932119042','1'),('1340221015038889986','1339868784896827394','1'),('1340221015038889987','1340196113212862466','1'),('1340221015043084290','1340198732253720577','1'),('1340221015047278594','1340217963246522369','1'),('1340221015047278595','1340218070033502210','1'),('1340221015051472897','1340218423432974338','1'),('1340221015051472898','1340218538965078017','1'),('1340221015059861505','1340218915030568962','1'),('1340221015068250115','1340219422788816898','1'),('1340221015072444418','1340219478564671489','1'),('1340221015076638721','1340219706277629954','1'),('1340221015076638722','1340220071328878594','1'),('1340221015080833026','1340220355862073345','1'),('1340221015080833027','1340220429501468673','1'),('1340221015085027329','1340220714990964737','1'),('1340221015089221634','1340220800898699265','1'),('1340221121951698946','1','1339870832828690433'),('1340221122681507842','2','1339870832828690433'),('1340221122685702146','1339866502885072897','1339870832828690433'),('1340221122685702147','1339868611932119042','1339870832828690433'),('1340221122689896450','1340198732253720577','1339870832828690433'),('1340221122694090753','1340217963246522369','1339870832828690433');
/*!40000 ALTER TABLE `sys_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `id` varchar(64) NOT NULL,
  `username` varchar(30) NOT NULL COMMENT '用户名',
  `dencrypt_password` varchar(64) NOT NULL COMMENT '明文',
  `encrypt_password` varchar(64) NOT NULL COMMENT '密文',
  `salt` varchar(64) NOT NULL COMMENT '盐值',
  `sex` tinyint(2) NOT NULL COMMENT '0-不公开,1-男,2-女',
  `deleted` tinyint(1) NOT NULL COMMENT '0-有效,1-无效',
  `disabled` tinyint(1) NOT NULL COMMENT '0-启用,1-禁用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `disabled_time` datetime DEFAULT NULL COMMENT '禁用时间',
  `version` timestamp NULL DEFAULT NULL COMMENT '乐观锁',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES ('1328989520135733249','123456','123456','3a8665f4ef9606d2553d9b7d330f2ee7','b2ac92e6df9a38d8182066b5314336e4',1,0,0,'2020-11-18 17:12:48','2020-12-18 11:52:16','2020-12-18 11:48:15','2020-11-18 09:12:48'),('1328995909801467905','aa','aaa','24608bad76a882a714fad7894dbc6a22','859f6599c34b62c782e8869072511bae',0,0,0,'2020-11-18 17:38:11','2020-12-05 16:53:22','2020-12-04 17:44:03','2020-11-18 09:38:11'),('1329332672893874177','bb','bb','0d63f94b52c72c9dc54dd9f7ba51ffa0','b2a779949421193f27dfcfd96aee505c',2,0,0,'2020-11-19 15:56:22','2020-12-14 16:51:40','2020-12-14 16:31:58','2020-11-19 07:56:22'),('1330700258541895682','admin','123456','ba437f410ac792e9c733535e529242e5','99e3aca73cc43166777186d088137bdb',0,0,0,'2020-11-23 10:30:40','2020-12-04 17:36:51',NULL,'2020-11-23 02:30:40'),('1330721236072304642','admin02','123456','0a256cd0ce8e97a34c3d7528e1082aab','a6dde1741a7e42d51ae3dea4920c14da',0,0,0,'2020-11-23 11:54:01','2020-12-04 17:42:32','2020-11-23 14:30:38','2020-11-23 03:54:01'),('1338671191726587905','admin01','123456789','a21d245125ddeb448060cc04a553be20','662e61c72da47efcaefb075d1a03cd3d',0,0,0,'2020-12-15 10:24:18','2020-12-18 17:52:38',NULL,'2020-12-15 02:24:18'),('1338674088816230401','admin03','123456','6dee6e260ac71e56ad3f214e35c5aaca','033771e14effed991552c5b3e0d849ff',0,0,0,'2020-12-15 10:35:49',NULL,NULL,'2020-12-15 02:35:49');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_role` (
  `id` varchar(64) NOT NULL,
  `sys_user_id` varchar(30) NOT NULL COMMENT '角色代码',
  `sys_role_id` varchar(30) NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`),
  KEY `sys_user_id` (`sys_user_id`),
  KEY `sys_role_id` (`sys_role_id`),
  CONSTRAINT `sys_user_role_ibfk_1` FOREIGN KEY (`sys_user_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `sys_user_role_ibfk_2` FOREIGN KEY (`sys_role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES ('1330770536051601410','1330700258541895682','1'),('1338674088833007618','1338674088816230401','1338673731423780865'),('1339871181656371201','1338671191726587905','1339870832828690433');
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'ponzi'
--
/*!50003 DROP FUNCTION IF EXISTS `func_countMenuByPid` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `func_countMenuByPid`(pid varchar(64) charset utf8) RETURNS int(11)
begin

	declare finished int default 0;
	declare counts int;
	declare cur cursor for select count(1) from sys_menu sm where sm.pid = pid;
	declare continue handler for not found set finished = 1;
	open cur;
	fetch cur into counts;
	close cur;
	if counts is null then
		return 0;
	else
		return counts;
	end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `proc_deleteMenuById` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_deleteMenuById`(in id varchar(64) charset utf8)
begin
	declare crand varchar(64) default rand();

	create temporary table if not exists temp_tb_menu_delete(
		menu_id varchar(64),
		crand varchar(64)
	)Engine=InnoDB default charset=utf8;

	call proc_findMenuByPid(id, crand);

	set @mysql = concat('insert into temp_tb_menu_delete(menu_id, crand) values("', id, '","', crand, '")');
	prepare stmt from @mysql;
	execute stmt;


	set @mysql = concat('delete from sys_role_menu where sys_menu_id in (select tt.menu_id from temp_tb_menu_delete tt where tt.crand="', crand, '")');
	prepare stmt from @mysql;
	execute stmt;


	set @mysql = concat('delete from sys_permission where sys_menu_id in (select tt.menu_id from temp_tb_menu_delete tt where tt.crand="', crand, '")');
	prepare stmt from @mysql;
	execute stmt;


	set @mysql = concat('delete from sys_menu where id in (select tt.menu_id from temp_tb_menu_delete tt where tt.crand="', crand, '")');
	prepare stmt from @mysql;
	execute stmt;

	drop temporary table if exists temp_tb_menu_delete;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `proc_findMenuByPid` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_findMenuByPid`(in pid varchar(64) charset utf8, in crand varchar(64) charset utf8)
begin

  declare finished int default 0;
  declare menuId varchar(64);
  declare cur cursor for select sm.id as id from sys_menu sm where sm.pid = pid;
  declare continue handler for not found set finished = 1;
  open cur;
  fetch cur into menuId;
  while finished = 0
    do
     if func_countMenuByPid(menuId) > 0 then
     	call proc_findMenuByPid(menuId, crand);
     end if;

 	 set @sql = concat('insert into temp_tb_menu_delete(menu_id, crand) values("', menuId, '","', crand, '")');
 	 prepare stmt from @sql;
     execute stmt;

     fetch cur into menuId;
  end while;
  close cur;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-21  9:36:47
