-- MySQL dump 10.13  Distrib 5.7.32, for Linux (x86_64)
--
-- Host: 117.141.244.6    Database: teacher_business
-- ------------------------------------------------------
-- Server version	5.7.32-0ubuntu0.18.04.1

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
-- Table structure for table `sys_captcha`
--

DROP TABLE IF EXISTS `sys_captcha`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_captcha` (
  `uuid` char(36) NOT NULL COMMENT 'uuid',
  `code` varchar(6) NOT NULL COMMENT '验证码',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统验证码';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_captcha`
--

LOCK TABLES `sys_captcha` WRITE;
/*!40000 ALTER TABLE `sys_captcha` DISABLE KEYS */;
INSERT INTO `sys_captcha` VALUES ('03060c17-d2ae-44b9-8527-76fdcc4bb5e8','74mbe','2020-09-27 10:08:36'),('04f346dc-d1b6-4d54-8de4-344c93304f9d','72365','2020-09-29 20:58:08'),('078f1b03-bb79-41de-8f12-99bc66d914e3','xcg6n','2020-11-17 11:37:39'),('07e40d23-8a62-40a6-8bd4-e55fd26cff12','325aa','2020-09-14 09:19:33'),('087c6697-2a75-4ac3-8c9a-8faae09971c7','npnyp','2020-09-09 15:21:23'),('09b8f268-96cb-4b80-80bd-543b0341f08d','527f3','2020-10-22 17:39:23'),('0de1aeb1-5999-4dc5-8454-3c762ae5c143','nfn4y','2020-11-17 11:37:36'),('11d9774f-e5a7-4cbc-8885-77ae730b96fa','ag34y','2020-10-23 21:26:25'),('1246adbb-5bab-49dc-88db-5ff568068665','22844','2020-11-17 11:37:38'),('134ec278-ed1d-491f-8179-4be405a67d34','yywfe','2020-11-17 10:44:48'),('135d3133-314d-4a40-8bb8-5751fa5ed741','2xdac','2020-08-17 17:42:49'),('1604e342-2bf3-41fe-8646-6d96875d9717','dn878','2020-09-18 09:24:26'),('1a3cdc58-3250-4b20-875e-7715366b34d4','7yggw','2020-11-17 10:44:47'),('1c38883c-45f6-4edf-8963-1166ac16b196','mewmd','2020-08-17 17:41:09'),('1caddd7f-f850-49ea-8a70-0db39cf205bf','w56cp','2020-09-08 16:31:04'),('1cdc6d51-60d1-43f1-8313-298115597ae8','xann3','2020-09-20 08:58:06'),('1ce81bc4-445b-4b77-844d-e3bc79d83868','c44ng','2020-09-29 20:58:02'),('1f8c32c8-9b85-40e5-8885-da04736588f2','ebypw','2020-08-17 12:31:40'),('205138ce-5709-4986-8e8a-352718909d8d','mg6p3','2020-08-19 10:53:11'),('20dc24ca-0106-4a59-82b3-d04052563412','n2nn4','2020-08-17 12:44:21'),('2448f397-ef6e-4290-8427-980cd2398c96','65w6c','2020-06-26 10:14:22'),('257de66e-41e6-4dc1-8f8b-99f48fcfbbeb','x8w56','2020-09-29 20:58:10'),('26abc9e2-50bb-4d87-8006-e96efb4764b4','4f2ap','2020-08-11 11:57:41'),('27e9751d-be64-4d04-88bf-8ff106c93680','wbmn8','2020-08-18 12:56:03'),('2896e3fa-9937-4f6a-8737-3eb9c1c0a33b','73g33','2020-09-16 09:42:18'),('2b3c4291-d643-4ac9-8266-180685075835','wcd2e','2020-08-12 17:38:21'),('2b90e7bc-9583-4ff7-82d2-773c9ebe7062','nppc2','2020-08-17 12:26:59'),('2c21631e-ed63-47dd-8c06-c2d5f13d7240','62xd4','2020-08-17 12:24:16'),('2c873116-82e9-4f6b-8020-1fcee119384a','4cdp7','2020-11-30 15:49:17'),('2e8b9b06-9c7a-43d0-8398-2052875a2f9c','ag7dg','2020-11-17 11:37:37'),('2eb26513-7757-4c2f-807c-c869f6770989','33exe','2020-08-17 12:53:46'),('2ff4a61b-22e6-46a4-80cc-2b47b0226208','nydf8','2020-08-17 12:33:34'),('30603568-a4a8-4190-8881-33c0f03a3517','xbbdc','2020-11-17 11:31:19'),('319fe048-5d6a-497b-83ec-b839ab08d1d1','mcfnb','2020-08-17 12:29:18'),('31ca8fb6-dae1-4e0e-8099-919f909cd30a','x5bbn','2020-09-16 19:42:18'),('323aebe2-b270-49f2-8671-6af46c2c7fd8','wp8cd','2020-11-17 10:44:47'),('348155f8-e831-4d08-8b63-cc82f0820769','exf4y','2020-09-08 17:32:15'),('359c48ea-6d94-4f1e-8538-a9d690dc48e1','nwc4c','2020-08-17 12:46:30'),('3619e16c-ec85-4bbb-881f-8b34a935d5dc','8acwc','2020-09-30 05:38:51'),('368d4413-7274-4702-8109-6110a8e5c2bf','anbec','2020-08-18 11:15:00'),('370feaf6-e253-47fb-8566-229d25309ad6','6mgx3','2020-08-17 13:05:49'),('37443c43-949e-4d76-8b80-7c926fa4ecef','6mcgw','2020-11-17 10:44:42'),('38c44b99-2329-4c50-8b46-e61b37ddf7c4','n4e4d','2020-09-10 10:38:32'),('3ab59d1a-089c-44f6-8487-d5970c29769e','4m4c8','2020-11-17 10:44:47'),('3fb416ee-7066-4d20-8a6e-9d5b8c400c99','e8w3b','2020-08-17 17:43:21'),('4491ff7f-c426-4854-896d-1ad708554e36','8gxfd','2020-08-11 11:04:07'),('45b270ef-8f4c-45eb-814e-fd824ec7d3be','fxgwx','2020-08-17 12:48:43'),('4be7543f-07bc-40ce-8d0b-5ebb944c4fd0','2b68d','2020-09-14 09:17:53'),('4f67ba78-f453-4658-8b45-fed2a683c45f','m5444','2020-08-17 18:10:50'),('54deff6e-4db8-4e36-8a72-68b5f528e7a6','w5exc','2020-09-24 23:26:40'),('5677ff71-c265-4641-8590-4e5324ba7c56','5d52f','2020-08-18 12:53:54'),('57bb77d9-fc3e-4d9d-8d5a-f677648f8883','fcd8a','2020-08-11 11:22:12'),('584151b5-af34-48c2-84fd-1a3c77bd05b8','a3pn2','2020-08-17 18:13:31'),('5ae063f9-ce3b-4375-8a93-1722fbdb455d','x2dwn','2020-08-17 12:34:24'),('5f103864-4a4c-43e5-8770-f9cb0f7c8a98','428py','2020-09-10 10:37:22'),('5f561bb7-ad58-433a-854d-0b29eabf3633','p8474','2020-08-17 12:59:50'),('5f81e5c0-edeb-41fd-87e6-3194d5fcbc26','wamyb','2020-09-23 08:52:19'),('616fb517-aa30-4877-8db0-e539d15129f8','fd3nm','2020-08-17 12:46:35'),('61f502cb-6b4c-46c8-8f47-8ba375e22b80','xbc5n','2020-11-22 15:55:40'),('6541f172-1d0c-40d3-82cb-3fbea01aefed','6bgmc','2020-10-01 11:45:08'),('66593c2e-b4bc-41b0-8e72-80fa8f14de4b','5n3y8','2020-06-10 10:33:22'),('6718df94-c0e0-4be5-80c4-55de54e9f535','fncdy','2020-10-15 10:21:55'),('675bb24a-ef15-4876-8f62-550d70e49ca2','p33nd','2020-11-17 10:44:46'),('684de3e3-7f65-472e-89b0-81d3aed0aa57','af77p','2020-10-20 23:50:54'),('69400f66-d0e5-44e0-8315-9163a71b4962','ddn6b','2020-08-17 18:08:38'),('6dc9bf1d-2c27-48e8-8895-c0c96706d60d','7pm3c','2020-11-17 10:49:12'),('6ee9eefb-cd43-4a9d-89f7-97391209b416','mbgbg','2020-09-16 09:41:19'),('744b8bd2-c424-42cc-879f-22456a4865df','34x6c','2020-09-29 12:58:59'),('769b056a-0001-45b4-87b8-79828bcc8d35','y8g6c','2020-10-22 12:05:14'),('781c7495-603a-4fa9-8c0d-2a7d10ac9818','5eae8','2020-11-18 09:13:22'),('7a1c1407-d583-42df-868c-0666a49d425f','wxadx','2020-11-17 10:46:55'),('7de6d915-7a53-4419-803c-4eb2110051d9','femb7','2020-09-24 10:31:13'),('7f5caba7-be38-41ea-8e1a-26d2435ed21c','bb33f','2020-09-28 11:32:11'),('8076dec9-530d-4fc4-8b17-6ef7455027d5','7f4bg','2020-08-19 12:00:09'),('80f05d90-ee15-482a-8235-c40dc2fcdac1','5xndd','2020-08-11 11:05:44'),('81bee316-38b9-4eba-80fe-fd45ea484b62','pnxpy','2020-08-17 18:05:13'),('83292a7f-0543-4b5c-86c3-ae2b9585fee2','8x8pc','2020-08-17 17:11:24'),('83af5511-ac36-49f9-8be6-cdd504cde425','2afce','2020-06-26 10:14:22'),('86c4e103-e11f-493e-8ad4-632b21ec6560','e8nym','2020-11-17 11:37:34'),('8792238d-6359-4445-8bc4-e9dce939ba04','w4py2','2020-09-17 13:51:24'),('8972fc14-b248-4623-825d-84404c5ab149','p7d4n','2020-08-17 12:10:14'),('89cbf3da-41ed-4718-85f9-1ec74ad3b2bf','m87p8','2020-08-18 12:54:24'),('8e5d3375-a5d3-424c-821d-4811bea27515','8ndn7','2020-08-17 12:23:05'),('927b12b1-b1e0-46da-8787-d28d5753c402','5m537','2020-09-25 12:29:57'),('92f61cfa-d949-4704-837f-41758538cfee','gany8','2020-09-24 10:00:16'),('947d2cde-ba5a-48af-8b2d-9156a4483c6b','4ypxb','2020-08-22 18:34:12'),('9b0b4175-d435-4262-8186-bdea74db0d60','4n26p','2020-11-13 15:10:28'),('9b69842d-145d-471f-8017-4d70f1af1cb4','27nn8','2020-08-15 11:47:25'),('9b90741f-9878-46de-8e0a-433a7db107f8','368an','2020-08-17 12:19:09'),('9c3055e0-7453-4fd8-8304-8e0556b86ccb','wnc57','2020-08-17 12:44:12'),('9d3f4fae-3efd-40de-837b-67d4003a1b9e','ea2eg','2020-08-18 12:55:58'),('9e657c59-fa05-442a-84bc-d5cd92bebc44','ye3nd','2020-09-15 20:17:07'),('9f39654a-a3cf-4e96-8b92-7f04f73de623','a8mp8','2020-08-18 11:12:35'),('a196d207-15a3-4b01-8082-323c33a57b1f','c6ben','2020-08-17 12:17:20'),('a294bf36-fa5a-452e-8b56-5d40e382d410','37aeb','2020-11-17 11:45:27'),('a505ed41-f52f-4b9f-8b12-7e5b6e401901','d22p5','2020-09-14 10:47:46'),('a9ca9d62-478b-4900-89cf-cce222c64f72','886c5','2020-09-29 20:58:07'),('ac23ad05-b8d2-404a-8acd-3ea12c9fbd3f','mc687','2020-09-22 10:20:08'),('ad665056-9757-4428-8885-3d998ad25f47','ccwab','2020-11-24 11:10:29'),('b4af5e11-b3b0-4b4d-89ef-68c4d020fa0b','bgney','2020-08-18 10:34:52'),('b525829a-6d42-4e45-8805-eb9dcfbc63ed','f6gpp','2020-11-30 14:57:52'),('b71b88b2-e8ba-4e7d-8aaf-e16ffa42d648','g4x2n','2020-09-16 09:38:07'),('ba66861f-2ae4-4155-8282-5b1eb4cfcd57','cn386','2020-11-17 10:44:48'),('bac3dc91-c93f-48af-8fd2-2433e7394487','4f768','2020-06-12 16:17:58'),('bd79da58-79b6-4d55-86b7-b0dcdc40019e','nddn6','2020-06-25 12:08:50'),('bff2bb97-9274-4af6-82f6-eda8b9ea3b22','827c6','2020-11-17 10:44:47'),('c0940228-a834-49db-840b-1de5bcd06bf9','y6pdn','2020-11-17 11:33:37'),('c0c025cb-bfd4-4f5c-849c-5d1a7d6d8f79','fbeab','2020-09-29 20:58:07'),('c32a9e33-b127-493b-8b6d-cb68284a9638','7wbxn','2020-09-15 16:03:25'),('c5470268-7697-4df6-8fbc-53490b19a4c8','368gf','2020-08-17 12:54:02'),('c5c0e4b4-0000-4d17-871d-b6ea3e59df2b','5d6bp','2020-11-25 18:46:54'),('c7106a91-abd0-4e43-84a2-3db6387b49db','xx4wp','2020-11-17 10:20:47'),('c8d155b2-c127-4aa5-8a53-8eabe8326e03','f6abp','2020-06-12 16:21:48'),('c951570a-4eba-4bce-822f-1e5ec9b2ad06','fpdnw','2020-08-17 12:43:48'),('ca89ce6c-bf66-413c-8722-763e5715d465','w6f5d','2020-09-09 15:20:16'),('cb2c2012-e27b-45d6-8dca-49a3f7cd6e2a','fnbwe','2020-10-23 21:23:01'),('cb88c968-eaa7-4d8e-83a8-1479302b76f4','b8cpn','2020-11-13 13:36:06'),('cdfe7592-0490-4c02-8372-2e866080f125','bfw7y','2020-06-12 16:18:00'),('d05537ec-5d7a-48cb-8663-557fec42e80e','cy6fy','2020-11-30 14:58:05'),('d2ee1870-a223-4a43-8d24-241162ead215','d4g7e','2020-08-17 12:31:18'),('d3506795-8816-4d9e-8a3c-8a2448ad4ce0','4bnne','2020-09-29 20:58:09'),('d7ec28e4-6898-4ad1-8612-84747c8fe4f3','e67fw','2020-11-17 10:50:42'),('da91986d-a89c-4d49-87cb-a858d6a1bb08','7g65a','2020-09-09 09:26:05'),('db26fa70-f62d-4210-8560-f2ad50885476','4nb3n','2020-08-21 11:22:28'),('dc0fc632-a5f3-4dc5-8196-09a8819842a8','wm63n','2020-10-02 16:06:41'),('dc948cfb-835b-482f-8baa-3a45618550ed','c6nfg','2020-10-22 21:38:20'),('dce3184d-fe97-4389-816b-eb83d2532d2c','n2pe4','2020-11-17 10:43:21'),('de1350c6-f888-404c-8c7e-dd25bbe2dbad','d5cxg','2020-08-13 10:26:06'),('e01d5e16-4ad8-4f5a-86e0-6cbaea87984e','wap8n','2020-06-25 17:45:30'),('e145f752-8687-4472-8542-feff14f37760','wgg3y','2020-08-18 13:01:28'),('e4cb60ed-1ab8-473c-8ce4-d9d2f8578536','5f2ww','2020-08-17 17:41:17'),('e65225ec-1938-4b5c-8e66-b0a531bd5f37','np86f','2020-11-17 10:42:52'),('ea0af1eb-f998-4a56-82ba-5b4e5f3b3364','wbfa7','2020-08-17 12:43:56'),('ed087f3c-a714-4e75-8c31-252cf3e75bfb','bfene','2020-10-22 21:36:27'),('edbdc6c7-ee86-4755-8912-89f6010d9700','eexnp','2020-08-17 12:25:13'),('f0918bf8-5a35-4049-8a0e-fab465924fc4','7wmg8','2020-11-30 14:58:14'),('f2a7c6d6-ef60-4b54-81b2-dca9047f544e','ddpfx','2020-09-23 17:39:02'),('f48b546f-6a51-4f43-8b6f-d11ef0323509','7dn7b','2020-09-09 21:08:36'),('f55109a0-9263-41fd-886d-a147226cc654','f7dcd','2020-09-14 11:05:44'),('f55bd368-62d4-4c49-8044-a3d13c95142c','wfxfg','2020-08-18 12:56:44'),('f60441c0-fcac-4a4e-83ce-0bea6d6138e0','am283','2020-09-09 15:20:23'),('fbe5ef23-2172-4c74-82e5-e95d9b8172fd','x3em2','2020-11-20 09:28:07');
/*!40000 ALTER TABLE `sys_captcha` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_config`
--

DROP TABLE IF EXISTS `sys_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `param_key` varchar(50) DEFAULT NULL COMMENT 'key',
  `param_value` varchar(2000) DEFAULT NULL COMMENT 'value',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `param_key` (`param_key`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='系统配置信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_config`
--

LOCK TABLES `sys_config` WRITE;
/*!40000 ALTER TABLE `sys_config` DISABLE KEYS */;
INSERT INTO `sys_config` VALUES (1,'CLOUD_STORAGE_CONFIG_KEY','{\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"aliyunDomain\":\"\",\"aliyunEndPoint\":\"\",\"aliyunPrefix\":\"\",\"qcloudBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qiniuAccessKey\":\"NrgMfABZxWLo5B-YYSjoE8-AZ1EISdi1Z3ubLOeZ\",\"qiniuBucketName\":\"ios-app\",\"qiniuDomain\":\"http://7xqbwh.dl1.z0.glb.clouddn.com\",\"qiniuPrefix\":\"upload\",\"qiniuSecretKey\":\"uIwJHevMRWU0VLxFvgy0tAcOdGqasdtVlJkdy6vV\",\"type\":1}',0,'云存储配置信息');
/*!40000 ALTER TABLE `sys_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dept`
--

DROP TABLE IF EXISTS `sys_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父部门ID，一级部门(学校)为0',
  `person_id` bigint(20) DEFAULT NULL COMMENT '部门负责人ID，为教师ID',
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `phone` varchar(50) DEFAULT NULL COMMENT '部门电话',
  `type` int(11) DEFAULT NULL COMMENT '类型     0：二级学院   1：教研室/实验室',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COMMENT='系统部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dept`
--

LOCK TABLES `sys_dept` WRITE;
/*!40000 ALTER TABLE `sys_dept` DISABLE KEYS */;
INSERT INTO `sys_dept` VALUES (1,0,0,'大数据与软件工程学院','18277404022',0),(2,0,0,'宝石与艺术设计学院','18277404022',0),(3,0,0,'外国语学院','18277404022',0),(4,0,0,'商学院','18277404022',0),(5,0,0,'机械与材料工程学院、化学工程与资源再利用学院','18277404022',0),(6,0,0,'教师教育学院','18277404022',0),(7,0,0,'电子与信息工程学院','18277404022',0),(8,0,0,'文学与传媒学院','18277404022',0),(9,0,0,'法学与公共管理学院','18277404022',0),(10,0,0,'马克思主义学院','18277404022',0),(11,0,0,'继续教育学院、高等职业技术学院','18277404022',0),(14,1,30,'软件工程教研室','13877417021',1),(15,1,27,'数据科学与大数据技术','13877417021',1),(16,1,47,'数字媒体技术','',1);
/*!40000 ALTER TABLE `sys_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dept_admin_info`
--

DROP TABLE IF EXISTS `sys_dept_admin_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_dept_admin_info` (
  `dept_admin_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dept_id` bigint(20) DEFAULT NULL COMMENT '管理部门ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`dept_admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='系统部门管理员信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dept_admin_info`
--

LOCK TABLES `sys_dept_admin_info` WRITE;
/*!40000 ALTER TABLE `sys_dept_admin_info` DISABLE KEYS */;
INSERT INTO `sys_dept_admin_info` VALUES (5,1,2),(9,2,10),(10,7,76);
/*!40000 ALTER TABLE `sys_dept_admin_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_log`
--

DROP TABLE IF EXISTS `sys_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb4 COMMENT='系统日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_log`
--

LOCK TABLES `sys_log` WRITE;
/*!40000 ALTER TABLE `sys_log` DISABLE KEYS */;
INSERT INTO `sys_log` VALUES (1,'admin','修改用户','com.soldier.modules.sys.controller.SysUserController.update()','[{\"userId\":1,\"username\":\"superAdmin\",\"password\":\"6e1142e2463827c49ca40ca6c8433ef25010bd6a2d5c07b5022a00b7abdef360\",\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"email\":\"583403411@qq.com\",\"mobile\":\"18277404022\",\"status\":1,\"roleIdList\":[],\"createUserId\":1}]',89,'0:0:0:0:0:0:0:1','2020-05-28 10:43:41'),(2,'superAdmin','保存菜单','com.soldier.modules.sys.controller.SysMenuController.save()','[{\"menuId\":31,\"parentId\":0,\"name\":\"教师信息\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"menu\",\"orderNum\":0}]',14,'0:0:0:0:0:0:0:1','2020-05-28 10:44:46'),(3,'superAdmin','保存角色','com.soldier.modules.sys.controller.SysRoleController.save()','[{\"roleId\":1,\"roleName\":\"二级学院管理员\",\"remark\":\"只能管理该学院下的所有教师\",\"createUserId\":1,\"menuIdList\":[-666666],\"createTime\":\"May 28, 2020 11:11:23 AM\"}]',60,'0:0:0:0:0:0:0:1','2020-05-28 11:11:24'),(4,'superAdmin','修改菜单','com.soldier.modules.sys.controller.SysMenuController.update()','[{\"menuId\":69,\"parentId\":42,\"name\":\"系统部门列表\",\"url\":\"business/sysdept/superAdmin\",\"type\":1,\"icon\":\"daohang\",\"orderNum\":6}]',21,'0:0:0:0:0:0:0:1','2020-05-28 11:33:57'),(5,'superAdmin','保存菜单','com.soldier.modules.sys.controller.SysMenuController.save()','[{\"menuId\":139,\"parentId\":42,\"name\":\"二级学院部门列表\",\"url\":\"business/sysdept/er\",\"perms\":\"\",\"type\":1,\"icon\":\"daohang\",\"orderNum\":0}]',13,'0:0:0:0:0:0:0:1','2020-05-28 11:34:55'),(6,'superAdmin','修改菜单','com.soldier.modules.sys.controller.SysMenuController.update()','[{\"menuId\":71,\"parentId\":69,\"name\":\"新增\",\"perms\":\"business:sysdept:save,business:sysdept:select\",\"type\":2,\"orderNum\":6}]',13,'0:0:0:0:0:0:0:1','2020-05-28 15:24:36'),(7,'superAdmin','修改菜单','com.soldier.modules.sys.controller.SysMenuController.update()','[{\"menuId\":72,\"parentId\":69,\"name\":\"修改\",\"perms\":\"business:sysdept:update,business:sysdept:select\",\"type\":2,\"orderNum\":6}]',7,'0:0:0:0:0:0:0:1','2020-05-28 15:25:02'),(8,'superAdmin','修改菜单','com.soldier.modules.sys.controller.SysMenuController.update()','[{\"menuId\":71,\"parentId\":69,\"name\":\"新增\",\"perms\":\"business:sysdept:save,business:sysdept:select,business:teacherinfo:select\",\"type\":2,\"orderNum\":6}]',16,'0:0:0:0:0:0:0:1','2020-05-28 15:39:13'),(9,'superAdmin','修改菜单','com.soldier.modules.sys.controller.SysMenuController.update()','[{\"menuId\":72,\"parentId\":69,\"name\":\"修改\",\"perms\":\"business:sysdept:update,business:sysdept:select,business:teacherinfo:select\",\"type\":2,\"orderNum\":6}]',12,'0:0:0:0:0:0:0:1','2020-05-28 15:39:30'),(10,'superAdmin','修改菜单','com.soldier.modules.sys.controller.SysMenuController.update()','[{\"menuId\":71,\"parentId\":69,\"name\":\"新增\",\"perms\":\"business:sysdept:save,business:sysdept:select,business:teacherinfo:select,business:teacherofdept:list\",\"type\":2,\"orderNum\":6}]',14,'0:0:0:0:0:0:0:1','2020-05-29 17:04:29'),(11,'superAdmin','修改菜单','com.soldier.modules.sys.controller.SysMenuController.update()','[{\"menuId\":72,\"parentId\":69,\"name\":\"修改\",\"perms\":\"business:sysdept:update,business:sysdept:select,business:teacherinfo:select,business:teacherofdept:list\",\"type\":2,\"orderNum\":6}]',11,'0:0:0:0:0:0:0:1','2020-05-29 17:04:42'),(12,'superAdmin','修改用户','com.soldier.modules.sys.controller.SysUserController.update()','[{\"userId\":2,\"username\":\"dsjxyAdmin\",\"password\":\"3d26020b181e668f695534990195129146357576b3bf0d758311ca174538484e\",\"salt\":\"pK06O873IBP2SgEUcie5\",\"email\":\"110@qq.com\",\"mobile\":\"18277404022\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1}]',10217,'0:0:0:0:0:0:0:1','2020-06-09 16:29:06'),(13,'superAdmin','修改用户','com.soldier.modules.sys.controller.SysUserController.update()','[{\"userId\":2,\"username\":\"jhxyAdmin\",\"password\":\"3d26020b181e668f695534990195129146357576b3bf0d758311ca174538484e\",\"salt\":\"pK06O873IBP2SgEUcie5\",\"email\":\"110@qq.com\",\"mobile\":\"18277404022\",\"status\":1,\"roleIdList\":[1,1],\"createUserId\":1}]',49358,'0:0:0:0:0:0:0:1','2020-06-09 16:30:44'),(14,'superAdmin','修改用户','com.soldier.modules.sys.controller.SysUserController.update()','[{\"userId\":2,\"username\":\"dsjxyAdmin\",\"password\":\"3d26020b181e668f695534990195129146357576b3bf0d758311ca174538484e\",\"salt\":\"pK06O873IBP2SgEUcie5\",\"email\":\"11222220@qq.com\",\"mobile\":\"18277404022\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1}]',39,'0:0:0:0:0:0:0:1','2020-06-09 16:51:49'),(15,'superAdmin','修改用户','com.soldier.modules.sys.controller.SysUserController.update()','[{\"userId\":2,\"username\":\"dsjxyAdmin\",\"password\":\"3d26020b181e668f695534990195129146357576b3bf0d758311ca174538484e\",\"salt\":\"pK06O873IBP2SgEUcie5\",\"email\":\"11222220@qq.com\",\"mobile\":\"18277404022\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1}]',201242,'0:0:0:0:0:0:0:1','2020-06-09 18:12:07'),(16,'superAdmin','修改用户','com.soldier.modules.sys.controller.SysUserController.update()','[{\"userId\":2,\"username\":\"dsjxyAdmin22\",\"password\":\"3d26020b181e668f695534990195129146357576b3bf0d758311ca174538484e\",\"salt\":\"pK06O873IBP2SgEUcie5\",\"email\":\"11222220@qq.com\",\"mobile\":\"18277404022\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1}]',4259,'0:0:0:0:0:0:0:1','2020-06-09 18:23:26'),(17,'superAdmin','修改角色','com.soldier.modules.sys.controller.SysRoleController.update()','[{\"roleId\":1,\"roleName\":\"二级学院管理员\",\"remark\":\"只能管理该学院下的所有教师\",\"createUserId\":1,\"menuIdList\":[139,-666666,42]}]',26,'0:0:0:0:0:0:0:1','2020-06-09 18:29:25'),(18,'superAdmin','修改角色','com.soldier.modules.sys.controller.SysRoleController.update()','[{\"roleId\":1,\"roleName\":\"二级学院管理员\",\"remark\":\"只能管理该学院下的所有教师\",\"createUserId\":1,\"menuIdList\":[140,141,142,143,144,145,-666666,42]}]',19,'0:0:0:0:0:0:0:1','2020-06-09 18:34:17'),(19,'superAdmin','保存角色','com.soldier.modules.sys.controller.SysRoleController.save()','[{\"roleId\":2,\"roleName\":\"系统管理员\",\"remark\":\"管理全校信息\",\"createUserId\":1,\"menuIdList\":[-666666],\"createTime\":\"Jun 12, 2020 4:04:47 PM\"}]',97,'0:0:0:0:0:0:0:1','2020-06-12 16:04:47'),(20,'superAdmin','修改角色','com.soldier.modules.sys.controller.SysRoleController.update()','[{\"roleId\":2,\"roleName\":\"超级管理员\",\"remark\":\"管理全校信息\",\"createUserId\":1,\"menuIdList\":[-666666]}]',16,'0:0:0:0:0:0:0:1','2020-06-12 16:05:05'),(21,'superAdmin','修改角色','com.soldier.modules.sys.controller.SysRoleController.update()','[{\"roleId\":2,\"roleName\":\"二级学院管理员\",\"remark\":\"只能管理该学院下的所有教师\",\"createUserId\":1,\"menuIdList\":[31,84,85,86,87,88,140,141,142,143,144,145,-666666,42]}]',44,'0:0:0:0:0:0:0:1','2020-06-12 16:08:41'),(22,'superAdmin','修改菜单','com.soldier.modules.sys.controller.SysMenuController.update()','[{\"menuId\":84,\"parentId\":31,\"name\":\"部门教师列表\",\"url\":\"business/teacherinfo\",\"type\":1,\"icon\":\"config\",\"orderNum\":6}]',22,'0:0:0:0:0:0:0:1','2020-06-12 16:09:08'),(23,'superAdmin','修改菜单','com.soldier.modules.sys.controller.SysMenuController.update()','[{\"menuId\":84,\"parentId\":31,\"name\":\"部门教师列表\",\"url\":\"business/teacherinfo/er\",\"type\":1,\"icon\":\"config\",\"orderNum\":6}]',11,'0:0:0:0:0:0:0:1','2020-06-12 16:10:37'),(24,'superAdmin','保存菜单','com.soldier.modules.sys.controller.SysMenuController.save()','[{\"menuId\":146,\"parentId\":31,\"name\":\"学校教师列表\",\"url\":\"business/teacherinfo/superAdmin\",\"perms\":\"\",\"type\":1,\"icon\":\"daohang\",\"orderNum\":0}]',17,'0:0:0:0:0:0:0:1','2020-06-12 16:11:32'),(25,'superAdmin','修改菜单','com.soldier.modules.sys.controller.SysMenuController.update()','[{\"menuId\":84,\"parentId\":31,\"name\":\"部门教师列表\",\"url\":\"business/teacherinfo/er\",\"type\":1,\"icon\":\"daohang\",\"orderNum\":6}]',14,'0:0:0:0:0:0:0:1','2020-06-12 16:11:45'),(26,'superAdmin','修改角色','com.soldier.modules.sys.controller.SysRoleController.update()','[{\"roleId\":2,\"roleName\":\"二级学院管理员\",\"remark\":\"只能管理该学院下的所有教师\",\"createUserId\":1,\"menuIdList\":[84,85,86,87,88,140,141,142,143,144,145,-666666,31,42]}]',23,'0:0:0:0:0:0:0:1','2020-06-12 16:12:23'),(27,'superAdmin','保存角色','com.soldier.modules.sys.controller.SysRoleController.save()','[{\"roleId\":3,\"roleName\":\"教师\",\"remark\":\"系统最基础角色\",\"createUserId\":1,\"menuIdList\":[-666666],\"createTime\":\"Jun 13, 2020 11:25:06 AM\"}]',154,'127.0.0.1','2020-06-13 11:25:06'),(28,'superAdmin','修改角色','com.soldier.modules.sys.controller.SysRoleController.update()','[{\"roleId\":2,\"roleName\":\"二级学院管理员\",\"remark\":\"只能管理该学院下的所有教师\",\"createUserId\":1,\"menuIdList\":[3,19,20,21,22,84,85,86,87,88,140,141,142,143,144,145,-666666,1,31,42]}]',54,'127.0.0.1','2020-06-13 11:26:04'),(29,'dsjxyAdmin','修改角色','com.soldier.modules.sys.controller.SysRoleController.update()','[{\"roleId\":3,\"roleName\":\"大数据与软件工程学院教师\",\"remark\":\"本学院教职工\",\"createUserId\":2,\"menuIdList\":[-666666]}]',16,'0:0:0:0:0:0:0:1','2020-06-13 11:27:11'),(30,'superAdmin','修改角色','com.soldier.modules.sys.controller.SysRoleController.update()','[{\"roleId\":2,\"roleName\":\"二级学院管理员\",\"remark\":\"只能管理该学院下的所有教师\",\"createUserId\":1,\"menuIdList\":[84,85,86,87,88,140,141,142,143,144,145,-666666,31,42]}]',31,'127.0.0.1','2020-06-13 11:31:44'),(31,'superAdmin','修改菜单','com.soldier.modules.sys.controller.SysMenuController.update()','[{\"menuId\":5,\"parentId\":1,\"name\":\"SQL监控\",\"url\":\"http://localhost:8080/druid/sql.html\",\"type\":1,\"icon\":\"sql\",\"orderNum\":4}]',18,'0:0:0:0:0:0:0:1','2020-06-24 16:33:02'),(32,'superAdmin','修改菜单','com.soldier.modules.sys.controller.SysMenuController.update()','[{\"menuId\":44,\"parentId\":32,\"name\":\"个人学术论文列表\",\"url\":\"business/academicpapers/oneself\",\"type\":1,\"icon\":\"menu\",\"orderNum\":6}]',14,'0:0:0:0:0:0:0:1','2020-06-24 16:44:55'),(33,'superAdmin','修改角色','com.soldier.modules.sys.controller.SysRoleController.update()','[{\"roleId\":3,\"roleName\":\"教师\",\"remark\":\"系统最基础角色\",\"createUserId\":1,\"menuIdList\":[32,44,45,46,47,48,-666666]}]',126,'127.0.0.1','2020-06-25 12:01:31'),(34,'superAdmin','修改角色','com.soldier.modules.sys.controller.SysRoleController.update()','[{\"roleId\":3,\"roleName\":\"教师\",\"remark\":\"系统最基础角色\",\"createUserId\":1,\"menuIdList\":[32,44,45,46,47,48,33,59,60,61,62,63,34,79,80,81,82,83,35,54,55,56,57,58,36,37,38,39,134,135,136,137,138,40,124,125,126,127,128,41,129,130,131,132,133,43,49,50,51,52,53,-666666]}]',98,'127.0.0.1','2020-06-25 12:02:13'),(35,'superAdmin','保存用户','com.soldier.modules.sys.controller.SysUserController.save()','[{\"userId\":74,\"username\":\"wzxyAdmin\",\"password\":\"a05ea90c04d8ba6614772d4b2687d21f3e1659b5ecdc639134c165afbf178dd9\",\"salt\":\"vnoVnMOl5VJYXMH1OJcj\",\"email\":\"18288888888@qq.com\",\"mobile\":\"18288888888\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1,\"createTime\":\"Jul 7, 2020 11:44:09 AM\"}]',113,'0:0:0:0:0:0:0:1','2020-07-07 11:44:09'),(36,'superAdmin','修改角色','com.soldier.modules.sys.controller.SysRoleController.update()','[{\"roleId\":1,\"roleName\":\"学校管理员\",\"remark\":\"管理全校信息\",\"createUserId\":1,\"menuIdList\":[146,-666666,31]}]',31,'0:0:0:0:0:0:0:1','2020-07-07 11:45:05'),(37,'superAdmin','修改角色','com.soldier.modules.sys.controller.SysRoleController.update()','[{\"roleId\":2,\"roleName\":\"二级学院管理员\",\"remark\":\"只能管理该学院下的所有教师\",\"createUserId\":1,\"menuIdList\":[151,152,153,154,155,140,141,142,143,144,145,43,49,50,51,52,53,-666666,31,42]}]',118,'0:0:0:0:0:0:0:1','2020-08-09 21:29:18'),(38,'superAdmin','修改角色','com.soldier.modules.sys.controller.SysRoleController.update()','[{\"roleId\":3,\"roleName\":\"教师\",\"remark\":\"系统最基础角色\",\"createUserId\":1,\"menuIdList\":[32,44,45,46,47,48,59,60,61,62,63,34,79,80,81,82,83,35,54,55,56,57,58,36,37,38,39,134,135,136,137,138,40,124,125,126,127,128,41,129,130,131,132,133,43,49,50,51,52,53,-666666,33]}]',269,'127.0.0.1','2020-08-10 18:24:20'),(39,'superAdmin','修改角色','com.soldier.modules.sys.controller.SysRoleController.update()','[{\"roleId\":3,\"roleName\":\"教师\",\"remark\":\"系统最基础角色\",\"createUserId\":1,\"menuIdList\":[167,32,44,45,46,47,48,59,60,61,62,63,34,79,80,81,82,83,35,54,55,56,57,58,36,37,38,39,134,135,136,137,138,40,124,125,126,127,128,41,129,130,131,132,133,43,49,50,51,52,53,166,-666666,31,151,33]}]',115,'127.0.0.1','2020-08-10 18:29:40'),(40,'superAdmin','修改角色','com.soldier.modules.sys.controller.SysRoleController.update()','[{\"roleId\":2,\"roleName\":\"二级学院管理员\",\"remark\":\"只能管理该学院下的所有教师\",\"createUserId\":1,\"menuIdList\":[151,152,153,154,155,167,156,157,158,159,160,141,142,143,144,43,49,50,51,52,53,166,-666666,31,33,42,140]}]',52,'127.0.0.1','2020-08-10 18:33:59'),(41,'superAdmin','修改角色','com.soldier.modules.sys.controller.SysRoleController.update()','[{\"roleId\":2,\"roleName\":\"二级学院管理员\",\"remark\":\"只能管理该学院下的所有教师\",\"createUserId\":1,\"menuIdList\":[151,152,153,154,155,167,168,169,170,171,172,156,157,158,159,160,140,141,142,143,144,43,49,50,51,52,53,166,-666666,31,32,33,42]}]',60,'127.0.0.1','2020-08-10 18:39:40'),(42,'superAdmin','修改角色','com.soldier.modules.sys.controller.SysRoleController.update()','[{\"roleId\":1,\"roleName\":\"学校管理员\",\"remark\":\"管理全校信息\",\"createUserId\":1,\"menuIdList\":[146,147,148,149,150,173,174,175,176,177,161,162,163,164,165,69,70,71,72,73,145,43,49,50,51,52,53,166,-666666,31,32,33,42]}]',91,'127.0.0.1','2020-08-10 18:54:04'),(43,'superAdmin','修改角色','com.soldier.modules.sys.controller.SysRoleController.update()','[{\"roleId\":3,\"roleName\":\"教师\",\"remark\":\"系统最基础角色\",\"createUserId\":1,\"menuIdList\":[167,44,45,46,47,48,59,60,61,62,63,79,80,81,82,83,35,54,55,56,57,58,36,37,38,39,134,135,136,137,138,40,124,125,126,127,128,41,129,130,131,132,133,43,49,50,51,52,53,166,-666666,31,151,32,33,34]}]',153,'127.0.0.1','2020-08-11 10:54:35'),(44,'superAdmin','修改角色','com.soldier.modules.sys.controller.SysRoleController.update()','[{\"roleId\":2,\"roleName\":\"二级学院管理员\",\"remark\":\"只能管理该学院下的所有教师\",\"createUserId\":1,\"menuIdList\":[151,152,153,154,155,167,168,169,170,171,172,156,157,158,159,160,178,179,180,181,182,140,141,142,143,144,43,49,50,51,52,53,166,-666666,31,32,33,34,42]}]',73,'127.0.0.1','2020-08-11 10:55:03'),(45,'superAdmin','修改角色','com.soldier.modules.sys.controller.SysRoleController.update()','[{\"roleId\":1,\"roleName\":\"学校管理员\",\"remark\":\"管理全校信息\",\"createUserId\":1,\"menuIdList\":[146,147,148,149,150,173,174,175,176,177,161,162,163,164,165,183,184,185,186,187,69,70,71,72,73,145,43,49,50,51,52,53,166,-666666,31,32,33,34,42]}]',67,'127.0.0.1','2020-08-11 10:55:32'),(46,'superAdmin','修改角色','com.soldier.modules.sys.controller.SysRoleController.update()','[{\"roleId\":3,\"roleName\":\"教师\",\"remark\":\"系统最基础角色\",\"createUserId\":1,\"menuIdList\":[44,45,46,47,48,59,60,61,62,63,79,80,81,82,83,35,54,55,56,57,58,36,37,38,39,134,135,136,137,138,40,124,125,126,127,128,41,129,130,131,132,133,43,49,50,51,52,53,166,-666666,32,33,34]}]',163,'0:0:0:0:0:0:0:1','2020-08-11 12:53:23'),(47,'superAdmin','修改角色','com.soldier.modules.sys.controller.SysRoleController.update()','[{\"roleId\":3,\"roleName\":\"教师\",\"remark\":\"系统最基础角色\",\"createUserId\":1,\"menuIdList\":[44,45,46,47,48,59,60,61,62,63,79,80,81,82,83,54,55,56,57,58,36,37,38,39,134,135,136,137,138,40,124,125,126,127,128,41,129,130,131,132,133,43,49,50,51,52,53,166,-666666,32,33,34,35]}]',168,'0:0:0:0:0:0:0:1','2020-08-11 20:42:32'),(48,'superAdmin','修改角色','com.soldier.modules.sys.controller.SysRoleController.update()','[{\"roleId\":2,\"roleName\":\"二级学院管理员\",\"remark\":\"只能管理该学院下的所有教师\",\"createUserId\":1,\"menuIdList\":[151,152,153,154,155,168,169,170,171,172,156,157,158,159,160,178,179,180,181,182,188,189,190,191,192,140,141,142,143,144,43,49,50,51,52,53,166,-666666,31,32,33,34,35,42]}]',79,'0:0:0:0:0:0:0:1','2020-08-11 20:43:07'),(49,'superAdmin','修改角色','com.soldier.modules.sys.controller.SysRoleController.update()','[{\"roleId\":1,\"roleName\":\"学校管理员\",\"remark\":\"管理全校信息\",\"createUserId\":1,\"menuIdList\":[146,147,148,149,150,173,174,175,176,177,161,162,163,164,165,183,184,185,186,187,193,194,195,196,197,69,70,71,72,73,145,43,49,50,51,52,53,166,-666666,31,32,33,34,35,42]}]',77,'0:0:0:0:0:0:0:1','2020-08-11 20:43:26'),(50,'superAdmin','修改角色','com.soldier.modules.sys.controller.SysRoleController.update()','[{\"roleId\":3,\"roleName\":\"教师\",\"remark\":\"系统最基础角色\",\"createUserId\":1,\"menuIdList\":[44,45,46,47,48,59,60,61,62,63,79,80,81,82,83,54,55,56,57,58,134,135,136,137,138,124,125,126,127,128,129,130,131,132,133,43,49,50,51,52,53,166,-666666,32,33,34,35,39,40,41]}]',303,'0:0:0:0:0:0:0:1','2020-08-13 10:58:51'),(51,'superAdmin','修改角色','com.soldier.modules.sys.controller.SysRoleController.update()','[{\"roleId\":2,\"roleName\":\"二级学院管理员\",\"remark\":\"只能管理该学院下的所有教师\",\"createUserId\":1,\"menuIdList\":[151,152,153,154,155,168,169,170,171,172,156,157,158,159,160,178,179,180,181,182,188,189,190,191,192,208,209,210,211,212,218,219,220,221,222,198,199,200,201,202,140,141,142,143,144,43,49,50,51,52,53,166,-666666,31,32,33,34,35,39,40,41,42]}]',151,'0:0:0:0:0:0:0:1','2020-08-13 10:59:32'),(52,'superAdmin','修改角色','com.soldier.modules.sys.controller.SysRoleController.update()','[{\"roleId\":1,\"roleName\":\"学校管理员\",\"remark\":\"管理全校信息\",\"createUserId\":1,\"menuIdList\":[146,147,148,149,150,173,174,175,176,177,161,162,163,164,165,183,184,185,186,187,193,194,195,196,197,213,214,215,216,217,223,224,225,226,227,203,204,205,206,207,69,70,71,72,73,145,43,49,50,51,52,53,166,-666666,31,32,33,34,35,39,40,41,42]}]',127,'0:0:0:0:0:0:0:1','2020-08-13 11:00:12'),(53,'superAdmin','修改角色','com.soldier.modules.sys.controller.SysRoleController.update()','[{\"roleId\":3,\"roleName\":\"教师\",\"remark\":\"系统最基础角色\",\"createUserId\":1,\"menuIdList\":[44,45,46,47,48,59,60,61,62,63,79,80,81,82,83,54,55,56,57,58,228,229,230,231,232,243,244,245,246,247,258,259,260,261,262,134,135,136,137,138,124,125,126,127,128,129,130,131,132,133,43,49,50,51,52,53,166,-666666,32,33,34,35,36,37,38,39,40,41]}]',531,'0:0:0:0:0:0:0:1','2020-08-18 10:17:37'),(54,'superAdmin','修改角色','com.soldier.modules.sys.controller.SysRoleController.update()','[{\"roleId\":2,\"roleName\":\"二级学院管理员\",\"remark\":\"只能管理该学院下的所有教师\",\"createUserId\":1,\"menuIdList\":[151,152,153,154,155,168,169,170,171,172,156,157,158,159,160,178,179,180,181,182,188,189,190,191,192,233,234,235,236,237,248,249,250,251,252,263,264,265,266,267,208,209,210,211,212,218,219,220,221,222,198,199,200,201,202,140,141,142,143,144,43,49,50,51,52,53,166,-666666,31,32,33,34,35,36,37,38,39,40,41,42]}]',216,'0:0:0:0:0:0:0:1','2020-08-18 10:18:25'),(55,'superAdmin','修改角色','com.soldier.modules.sys.controller.SysRoleController.update()','[{\"roleId\":1,\"roleName\":\"学校管理员\",\"remark\":\"管理全校信息\",\"createUserId\":1,\"menuIdList\":[146,147,148,149,150,173,174,175,176,177,161,162,163,164,165,183,184,185,186,187,193,194,195,196,197,238,239,240,241,242,253,254,255,256,257,268,269,270,271,272,213,214,215,216,217,223,224,225,226,227,203,204,205,206,207,69,70,71,72,73,145,43,49,50,51,52,53,166,-666666,31,32,33,34,35,36,37,38,39,40,41,42]}]',121,'0:0:0:0:0:0:0:1','2020-08-18 10:18:56'),(56,'superAdmin','修改角色','com.soldier.modules.sys.controller.SysRoleController.update()','[{\"roleId\":3,\"roleName\":\"教师\",\"remark\":\"系统最基础角色\",\"createUserId\":1,\"menuIdList\":[44,45,46,47,48,59,60,61,62,63,79,80,81,82,83,54,55,56,57,58,228,229,230,231,232,243,244,245,246,247,258,259,260,261,262,134,135,136,137,138,124,125,126,127,128,129,130,131,132,133,43,49,50,51,52,53,166,-666666,32,33,34,35,36,37,38,39,40,41]}]',842,'0:0:0:0:0:0:0:1','2020-08-18 10:27:25'),(57,'superAdmin','修改菜单','com.soldier.modules.sys.controller.SysMenuController.update()','[{\"menuId\":38,\"parentId\":0,\"name\":\"科研成果\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"menu\",\"orderNum\":8}]',50,'222.217.220.228','2020-09-14 11:04:43'),(58,'superAdmin','修改用户','com.soldier.modules.sys.controller.SysUserController.update()','[{\"userId\":1,\"username\":\"soldier\",\"password\":\"6e1142e2463827c49ca40ca6c8433ef25010bd6a2d5c07b5022a00b7abdef360\",\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"email\":\"583403411@qq.com\",\"mobile\":\"18277404022\",\"status\":1,\"roleIdList\":[],\"createUserId\":1}]',48,'222.217.220.228','2020-09-14 11:05:35'),(59,'soldier','修改用户','com.soldier.modules.sys.controller.SysUserController.update()','[{\"userId\":2,\"username\":\"dsjxyGLY\",\"password\":\"e01d6efb815545f3044e98ae9703c2bd6956f7d4edfe440051180b234670a7e1\",\"salt\":\"pK06O873IBP2SgEUcie5\",\"email\":\"11222220@qq.com\",\"mobile\":\"18277404022\",\"status\":1,\"roleIdList\":[2],\"createUserId\":1}]',53,'222.217.220.228','2020-09-14 11:06:22'),(60,'soldier','修改用户','com.soldier.modules.sys.controller.SysUserController.update()','[{\"userId\":10,\"username\":\"byxyGLY\",\"password\":\"84a62f30790593a7d0d4cc40449abcba310307b9fe5ca7d57da458aac350d26e\",\"salt\":\"xtq80jiNMFIq3ui0GPHs\",\"email\":\"11@qq.com\",\"mobile\":\"18277404022\",\"status\":1,\"roleIdList\":[2],\"createUserId\":1}]',61,'222.217.220.228','2020-09-14 11:07:02'),(61,'soldier','修改用户','com.soldier.modules.sys.controller.SysUserController.update()','[{\"userId\":74,\"username\":\"wzxyGLY\",\"password\":\"1eb96f794ef51633daa3848007f7a959146b41b18fa225fd9eb0c2dc9a721d62\",\"salt\":\"vnoVnMOl5VJYXMH1OJcj\",\"email\":\"18288888888@qq.com\",\"mobile\":\"18288888888\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1}]',42,'222.217.220.228','2020-09-14 11:08:00'),(62,'soldier','修改菜单','com.soldier.modules.sys.controller.SysMenuController.update()','[{\"menuId\":59,\"parentId\":33,\"name\":\"我指导的学生竞赛\",\"url\":\"business/studentcompetition/oneself\",\"type\":1,\"icon\":\"daohang\",\"orderNum\":6}]',82,'222.217.220.228','2020-09-14 17:23:18'),(63,'soldier','修改菜单','com.soldier.modules.sys.controller.SysMenuController.update()','[{\"menuId\":43,\"parentId\":0,\"name\":\"基础数据管理\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"menu\",\"orderNum\":12}]',88,'222.217.220.228','2020-09-15 20:07:44'),(64,'soldier','修改角色','com.soldier.modules.sys.controller.SysRoleController.update()','[{\"roleId\":3,\"roleName\":\"教师\",\"remark\":\"系统最基础角色\",\"createUserId\":1,\"menuIdList\":[44,45,46,47,48,59,60,61,62,63,79,80,81,82,83,54,55,56,57,58,228,229,230,231,232,243,244,245,246,247,258,259,260,261,262,134,135,136,137,138,124,125,126,127,128,129,130,131,132,133,43,49,50,51,52,53,166,273,274,275,276,277,278,279,280,281,282,283,284,285,286,287,288,289,290,291,292,293,294,295,296,297,298,299,300,301,302,-666666,32,33,34,35,36,37,38,39,40,41]}]',347,'222.217.220.228','2020-09-15 20:07:56'),(65,'soldier','修改角色','com.soldier.modules.sys.controller.SysRoleController.update()','[{\"roleId\":2,\"roleName\":\"二级学院管理员\",\"remark\":\"只能管理该学院下的所有教师\",\"createUserId\":1,\"menuIdList\":[151,152,153,154,155,168,169,170,171,172,156,157,158,159,160,178,179,180,181,182,188,189,190,191,192,233,234,235,236,237,248,249,250,251,252,263,264,265,266,267,208,209,210,211,212,218,219,220,221,222,198,199,200,201,202,140,141,142,143,144,43,49,50,51,52,53,166,273,274,275,276,277,278,279,280,281,282,283,284,285,286,287,288,289,290,291,292,293,294,295,296,297,298,299,300,301,302,-666666,31,32,33,34,35,36,37,38,39,40,41,42]}]',282,'222.217.220.228','2020-09-15 20:08:06'),(66,'soldier','修改角色','com.soldier.modules.sys.controller.SysRoleController.update()','[{\"roleId\":1,\"roleName\":\"学校管理员\",\"remark\":\"管理全校信息\",\"createUserId\":1,\"menuIdList\":[146,147,148,149,150,173,174,175,176,177,161,162,163,164,165,183,184,185,186,187,193,194,195,196,197,238,239,240,241,242,253,254,255,256,257,268,269,270,271,272,213,214,215,216,217,223,224,225,226,227,203,204,205,206,207,69,70,71,72,73,145,43,49,50,51,52,53,166,273,274,275,276,277,278,279,280,281,282,283,284,285,286,287,288,289,290,291,292,293,294,295,296,297,298,299,300,301,302,-666666,31,32,33,34,35,36,37,38,39,40,41,42]}]',282,'222.217.220.228','2020-09-15 20:08:18'),(67,'soldier','修改菜单','com.soldier.modules.sys.controller.SysMenuController.update()','[{\"menuId\":37,\"parentId\":0,\"name\":\"科研成果\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"menu\",\"orderNum\":6}]',358,'0:0:0:0:0:0:0:1','2020-09-16 21:37:15'),(68,'soldier','修改菜单','com.soldier.modules.sys.controller.SysMenuController.update()','[{\"menuId\":37,\"parentId\":0,\"name\":\"大创项目\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"menu\",\"orderNum\":6}]',205,'0:0:0:0:0:0:0:1','2020-09-16 21:37:51'),(69,'soldier','修改菜单','com.soldier.modules.sys.controller.SysMenuController.update()','[{\"menuId\":37,\"parentId\":0,\"name\":\"大创项目\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"menu\",\"orderNum\":6}]',203,'0:0:0:0:0:0:0:1','2020-09-16 21:42:57'),(70,'soldier','修改菜单','com.soldier.modules.sys.controller.SysMenuController.update()','[{\"menuId\":140,\"parentId\":42,\"name\":\"教研室列表\",\"url\":\"business/sysdept/er\",\"perms\":\"\",\"type\":1,\"icon\":\"daohang\",\"orderNum\":0}]',242,'0:0:0:0:0:0:0:1','2020-09-16 21:44:20'),(71,'soldier','修改菜单','com.soldier.modules.sys.controller.SysMenuController.update()','[{\"menuId\":38,\"parentId\":0,\"name\":\"科研成果\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"menu\",\"orderNum\":8}]',321,'0:0:0:0:0:0:0:1','2020-09-16 22:26:13'),(72,'soldier','修改菜单','com.soldier.modules.sys.controller.SysMenuController.update()','[{\"menuId\":39,\"parentId\":0,\"name\":\"自编教材管理\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"menu\",\"orderNum\":9}]',83,'222.217.220.228','2020-09-23 10:58:25');
/*!40000 ALTER TABLE `sys_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=303 DEFAULT CHARSET=utf8mb4 COMMENT='菜单管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (1,0,'系统管理',NULL,NULL,0,'system',0),(2,1,'管理员列表','sys/user',NULL,1,'admin',1),(3,1,'角色管理','sys/role',NULL,1,'role',2),(4,1,'菜单管理','sys/menu',NULL,1,'menu',3),(5,1,'SQL监控','http://localhost:8080/druid/sql.html',NULL,1,'sql',4),(15,2,'查看',NULL,'sys:user:list,sys:user:info',2,NULL,0),(16,2,'新增',NULL,'sys:user:save,sys:role:select',2,NULL,0),(17,2,'修改',NULL,'sys:user:update,sys:role:select',2,NULL,0),(18,2,'删除',NULL,'sys:user:delete',2,NULL,0),(19,3,'查看',NULL,'sys:role:list,sys:role:info',2,NULL,0),(20,3,'新增',NULL,'sys:role:save,sys:menu:list',2,NULL,0),(21,3,'修改',NULL,'sys:role:update,sys:menu:list',2,NULL,0),(22,3,'删除',NULL,'sys:role:delete',2,NULL,0),(23,4,'查看',NULL,'sys:menu:list,sys:menu:info',2,NULL,0),(24,4,'新增',NULL,'sys:menu:save,sys:menu:select',2,NULL,0),(25,4,'修改',NULL,'sys:menu:update,sys:menu:select',2,NULL,0),(26,4,'删除',NULL,'sys:menu:delete',2,NULL,0),(27,1,'参数管理','sys/config','sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete',1,'config',6),(29,1,'系统日志','sys/log','sys:log:list',1,'log',7),(30,1,'文件上传','oss/oss','sys:oss:all',1,'oss',6),(31,0,'教师信息','','',0,'menu',1),(32,0,'学术论文','','',0,'menu',2),(33,0,'学生竞赛','','',0,'menu',3),(34,0,'教师竞赛','','',0,'menu',4),(35,0,'项目立项','','',0,'menu',5),(36,0,'项目结题','','',0,'menu',6),(37,0,'大创项目','','',0,'menu',7),(38,0,'科研成果','','',0,'menu',8),(39,0,'自编教材管理','','',0,'menu',9),(40,0,'教学成果','','',0,'menu',10),(41,0,'教学荣誉','','',0,'menu',11),(42,0,'部门管理','','',0,'menu',13),(43,0,'基础数据管理','','',0,'menu',12),(44,32,'个人学术论文列表','business/academicpapers/oneself',NULL,1,'daohang',6),(45,44,'查看',NULL,'business:academicpapers:list,business:academicpapers:info',2,NULL,6),(46,44,'新增',NULL,'business:academicpapers:save',2,NULL,6),(47,44,'修改',NULL,'business:academicpapers:update',2,NULL,6),(48,44,'删除',NULL,'business:academicpapers:delete',2,NULL,6),(49,43,'赛事信息','business/basics/matchinfo',NULL,1,'daohang',6),(50,49,'查看',NULL,'business:matchinfo:list,business:matchinfo:info',2,NULL,6),(51,49,'新增',NULL,'business:matchinfo:save',2,NULL,6),(52,49,'修改',NULL,'business:matchinfo:update',2,NULL,6),(53,49,'删除',NULL,'business:matchinfo:delete',2,NULL,6),(54,35,'个人项目立项列表','business/projectcreate/oneself',NULL,1,'daohang',6),(55,54,'查看',NULL,'business:projectcreate:list,business:projectcreate:info',2,NULL,6),(56,54,'新增',NULL,'business:projectcreate:save',2,NULL,6),(57,54,'修改',NULL,'business:projectcreate:update',2,NULL,6),(58,54,'删除',NULL,'business:projectcreate:delete',2,NULL,6),(59,33,'我指导的学生竞赛','business/studentcompetition/oneself',NULL,1,'daohang',6),(60,59,'查看',NULL,'business:studentcompetition:list,business:studentcompetition:info',2,NULL,6),(61,59,'新增',NULL,'business:studentcompetition:save',2,NULL,6),(62,59,'修改',NULL,'business:studentcompetition:update',2,NULL,6),(63,59,'删除',NULL,'business:studentcompetition:delete',2,NULL,6),(64,1,'系统附件','business/sysattach',NULL,1,'daohang',6),(65,64,'查看',NULL,'business:sysattach:list,business:sysattach:info',2,NULL,6),(66,64,'新增',NULL,'business:sysattach:save',2,NULL,6),(67,64,'修改',NULL,'business:sysattach:update',2,NULL,6),(68,64,'删除',NULL,'business:sysattach:delete',2,NULL,6),(69,42,'二级学院列表','business/sysdept/school',NULL,1,'daohang',6),(70,69,'查看',NULL,'business:sysdept:list,business:sysdept:info',2,NULL,6),(71,69,'新增',NULL,'business:sysdept:update,business:teacherinfo:select,business:sysdept:select,business:teacherofdept:list',2,NULL,6),(72,69,'修改',NULL,'business:sysdept:save,business:teacherinfo:select,business:sysdept:select,business:teacherofdept:list',NULL,'business:sysdept:select',6),(73,69,'删除',NULL,'business:sysdept:delete',2,NULL,6),(79,34,'个人教师竞赛列表','business/teachercompetition/oneself',NULL,1,'daohang',6),(80,79,'查看',NULL,'business:teachercompetition:list,business:teachercompetition:info',2,NULL,6),(81,79,'新增',NULL,'business:teachercompetition:save',2,NULL,6),(82,79,'修改',NULL,'business:teachercompetition:update',2,NULL,6),(83,79,'删除',NULL,'business:teachercompetition:delete',2,NULL,6),(124,40,'个人教学成果列表','business/teachingachievements/oneself',NULL,1,'daohang',6),(125,124,'查看',NULL,'business:teachingachievements:list,business:teachingachievements:info',2,NULL,6),(126,124,'新增',NULL,'business:teachingachievements:save',2,NULL,6),(127,124,'修改',NULL,'business:teachingachievements:update',2,NULL,6),(128,124,'删除',NULL,'business:teachingachievements:delete',2,NULL,6),(129,41,'个人教学荣誉列表','business/teachinghonor/oneself',NULL,1,'daohang',6),(130,129,'查看',NULL,'business:teachinghonor:list,business:teachinghonor:info',2,NULL,6),(131,129,'新增',NULL,'business:teachinghonor:save',2,NULL,6),(132,129,'修改',NULL,'business:teachinghonor:update',2,NULL,6),(133,129,'删除',NULL,'business:teachinghonor:delete',2,NULL,6),(134,39,'个人教材列表','business/teachingmaterial/oneself',NULL,1,'daohang',6),(135,134,'查看',NULL,'business:teachingmaterial:list,business:teachingmaterial:info',2,NULL,6),(136,134,'新增',NULL,'business:teachingmaterial:save',2,NULL,6),(137,134,'修改',NULL,'business:teachingmaterial:update',2,NULL,6),(138,134,'删除',NULL,'business:teachingmaterial:delete',2,NULL,6),(139,1,'接口文档','http://localhost:8080/swagger-ui.html',NULL,1,'bianji',0),(140,42,'教研室列表','business/sysdept/er','',1,'daohang',0),(141,140,'查看',NULL,'business:sysdept:list,business:sysdept:info',2,NULL,6),(142,140,'新增',NULL,'business:sysdept:save,business:sysdept:select,business:teacherofdept:list',2,NULL,6),(143,140,'修改',NULL,'business:sysdept:update,business:sysdept:select,business:teacherofdept:list',2,NULL,6),(144,140,'删除',NULL,'business:sysdept:delete',2,NULL,6),(145,69,'管理员',NULL,'business:sysdeptadmininfo:info,business:sysdeptadmininfo:save,business:sysdeptadmininfo:update,sys:user:info,sys:user:save,sys:user:update',2,NULL,6),(146,31,'学校教师列表','business/teacherinfo/school','',1,'daohang',0),(147,146,'查看',NULL,'business:teacherinfo:list,business:teacherinfo:info',2,NULL,6),(148,146,'新增',NULL,'business:teacherinfo:save',2,NULL,6),(149,146,'修改',NULL,'business:teacherinfo:update',2,NULL,6),(150,146,'删除',NULL,'business:teacherinfo:delete',2,NULL,6),(151,31,'二级学院教师列表','business/teacherinfo/er','',1,'daohang',0),(152,151,'查看',NULL,'business:teacherinfo:list,business:teacherinfo:info',2,NULL,6),(153,151,'新增',NULL,'business:teacherinfo:save',2,NULL,6),(154,151,'修改',NULL,'business:teacherinfo:update',2,NULL,6),(155,151,'删除',NULL,'business:teacherinfo:delete',2,NULL,6),(156,33,'部门学生竞赛列表','business/studentcompetition/er',NULL,1,'daohang',6),(157,156,'查看',NULL,'business:studentcompetition:list,business:studentcompetition:info',2,NULL,6),(158,156,'新增',NULL,'business:studentcompetition:save',2,NULL,6),(159,156,'修改',NULL,'business:studentcompetition:update',2,NULL,6),(160,156,'删除',NULL,'business:studentcompetition:delete',2,NULL,6),(161,33,'学校学生竞赛列表','business/studentcompetition/school',NULL,1,'daohang',6),(162,161,'查看',NULL,'business:studentcompetition:list,business:studentcompetition:info',2,NULL,6),(163,161,'新增',NULL,'business:studentcompetition:save',2,NULL,6),(164,161,'修改',NULL,'business:studentcompetition:update',2,NULL,6),(165,161,'删除',NULL,'business:studentcompetition:delete',2,NULL,6),(166,49,'选择',NULL,'business:matchinfo:select',2,NULL,6),(168,32,'部门学术论文列表','business/academicpapers/er',NULL,1,'daohang',6),(169,168,'查看',NULL,'business:academicpapers:list,business:academicpapers:info',2,NULL,6),(170,168,'新增',NULL,'business:academicpapers:save',2,NULL,6),(171,168,'修改',NULL,'business:academicpapers:update',2,NULL,6),(172,168,'删除',NULL,'business:academicpapers:delete',2,NULL,6),(173,32,'学校学术论文列表','business/academicpapers/school',NULL,1,'daohang',6),(174,173,'查看',NULL,'business:academicpapers:list,business:academicpapers:info',2,NULL,6),(175,173,'新增',NULL,'business:academicpapers:save',2,NULL,6),(176,173,'修改',NULL,'business:academicpapers:update',2,NULL,6),(177,173,'删除',NULL,'business:academicpapers:delete',2,NULL,6),(178,34,'部门教师竞赛列表','business/teachercompetition/er',NULL,1,'daohang',6),(179,178,'查看',NULL,'business:teachercompetition:list,business:teachercompetition:info',2,NULL,6),(180,178,'新增',NULL,'business:teachercompetition:save',2,NULL,6),(181,178,'修改',NULL,'business:teachercompetition:update',2,NULL,6),(182,178,'删除',NULL,'business:teachercompetition:delete',2,NULL,6),(183,34,'学校教师竞赛列表','business/teachercompetition/school',NULL,1,'daohang',6),(184,183,'查看',NULL,'business:teachercompetition:list,business:teachercompetition:info',2,NULL,6),(185,183,'新增',NULL,'business:teachercompetition:save',2,NULL,6),(186,183,'修改',NULL,'business:teachercompetition:update',2,NULL,6),(187,183,'删除',NULL,'business:teachercompetition:delete',2,NULL,6),(188,35,'部门项目立项列表','business/projectcreate/er',NULL,1,'daohang',6),(189,188,'查看',NULL,'business:projectcreate:list,business:projectcreate:info',2,NULL,6),(190,188,'新增',NULL,'business:projectcreate:save',2,NULL,6),(191,188,'修改',NULL,'business:projectcreate:update',2,NULL,6),(192,188,'删除',NULL,'business:projectcreate:delete',2,NULL,6),(193,35,'学校项目立项列表','business/projectcreate/school',NULL,1,'daohang',6),(194,193,'查看',NULL,'business:projectcreate:list,business:projectcreate:info',2,NULL,6),(195,193,'新增',NULL,'business:projectcreate:save',2,NULL,6),(196,193,'修改',NULL,'business:projectcreate:update',2,NULL,6),(197,193,'删除',NULL,'business:projectcreate:delete',2,NULL,6),(198,41,'部门教学荣誉列表','business/teachinghonor/er',NULL,1,'daohang',6),(199,198,'查看',NULL,'business:teachinghonor:list,business:teachinghonor:info',2,NULL,6),(200,198,'新增',NULL,'business:teachinghonor:save',2,NULL,6),(201,198,'修改',NULL,'business:teachinghonor:update',2,NULL,6),(202,198,'删除',NULL,'business:teachinghonor:delete',2,NULL,6),(203,41,'学校教学荣誉列表','business/teachinghonor/school',NULL,1,'daohang',6),(204,203,'查看',NULL,'business:teachinghonor:list,business:teachinghonor:info',2,NULL,6),(205,203,'新增',NULL,'business:teachinghonor:save',2,NULL,6),(206,203,'修改',NULL,'business:teachinghonor:update',2,NULL,6),(207,203,'删除',NULL,'business:teachinghonor:delete',2,NULL,6),(208,39,'部门教材列表','business/teachingmaterial/er',NULL,1,'daohang',6),(209,208,'查看',NULL,'business:teachingmaterial:list,business:teachingmaterial:info',2,NULL,6),(210,208,'新增',NULL,'business:teachingmaterial:save',2,NULL,6),(211,208,'修改',NULL,'business:teachingmaterial:update',2,NULL,6),(212,208,'删除',NULL,'business:teachingmaterial:delete',2,NULL,6),(213,39,'学校教材列表','business/teachingmaterial/school',NULL,1,'daohang',6),(214,213,'查看',NULL,'business:teachingmaterial:list,business:teachingmaterial:info',2,NULL,6),(215,213,'新增',NULL,'business:teachingmaterial:save',2,NULL,6),(216,213,'修改',NULL,'business:teachingmaterial:update',2,NULL,6),(217,213,'删除',NULL,'business:teachingmaterial:delete',2,NULL,6),(218,40,'部门教学成果列表','business/teachingachievements/er',NULL,1,'daohang',6),(219,218,'查看',NULL,'business:teachingachievements:list,business:teachingachievements:info',2,NULL,6),(220,218,'新增',NULL,'business:teachingachievements:save',2,NULL,6),(221,218,'修改',NULL,'business:teachingachievements:update',2,NULL,6),(222,218,'删除',NULL,'business:teachingachievements:delete',2,NULL,6),(223,40,'学校教学成果列表','business/teachingachievements/school',NULL,1,'daohang',6),(224,223,'查看',NULL,'business:teachingachievements:list,business:teachingachievements:info',2,NULL,6),(225,223,'新增',NULL,'business:teachingachievements:save',2,NULL,6),(226,223,'修改',NULL,'business:teachingachievements:update',2,NULL,6),(227,223,'删除',NULL,'business:teachingachievements:delete',2,NULL,6),(228,36,'个人项目结题列表','business/projectfinish/oneself',NULL,1,'daohang',6),(229,228,'查看',NULL,'business:projectfinish:list,business:projectfinish:info',2,NULL,6),(230,228,'新增',NULL,'business:projectfinish:save',2,NULL,6),(231,228,'修改',NULL,'business:projectfinish:update',2,NULL,6),(232,228,'删除',NULL,'business:projectfinish:delete',2,NULL,6),(233,36,'部门项目结题列表','business/projectfinish/er',NULL,1,'daohang',6),(234,233,'查看',NULL,'business:projectfinish:list,business:projectfinish:info',2,NULL,6),(235,233,'新增',NULL,'business:projectfinish:save',2,NULL,6),(236,233,'修改',NULL,'business:projectfinish:update',2,NULL,6),(237,233,'删除',NULL,'business:projectfinish:delete',2,NULL,6),(238,36,'学校项目结题列表','business/projectfinish/school',NULL,1,'daohang',6),(239,238,'查看',NULL,'business:projectfinish:list,business:projectfinish:info',2,NULL,6),(240,238,'新增',NULL,'business:projectfinish:save',2,NULL,6),(241,238,'修改',NULL,'business:projectfinish:update',2,NULL,6),(242,238,'删除',NULL,'business:projectfinish:delete',2,NULL,6),(243,37,'指导学生大创列表','business/innovateproject/oneself',NULL,1,'daohang',6),(244,243,'查看',NULL,'business:innovateproject:list,business:innovateproject:info',2,NULL,6),(245,243,'新增',NULL,'business:innovateproject:save',2,NULL,6),(246,243,'修改',NULL,'business:innovateproject:update',2,NULL,6),(247,243,'删除',NULL,'business:innovateproject:delete',2,NULL,6),(248,37,'部门学生大创列表','business/innovateproject/er',NULL,1,'daohang',6),(249,248,'查看',NULL,'business:innovateproject:list,business:innovateproject:info',2,NULL,6),(250,248,'新增',NULL,'business:innovateproject:save',2,NULL,6),(251,248,'修改',NULL,'business:innovateproject:update',2,NULL,6),(252,248,'删除',NULL,'business:innovateproject:delete',2,NULL,6),(253,37,'学校学生大创列表','business/innovateproject/school',NULL,1,'daohang',6),(254,253,'查看',NULL,'business:innovateproject:list,business:innovateproject:info',2,NULL,6),(255,253,'新增',NULL,'business:innovateproject:save',2,NULL,6),(256,253,'修改',NULL,'business:innovateproject:update',2,NULL,6),(257,253,'删除',NULL,'business:innovateproject:delete',2,NULL,6),(258,38,'个人科研成果列表','business/researchgain/oneself',NULL,1,'daohang',6),(259,258,'查看',NULL,'business:researchgain:list,business:researchgain:info',2,NULL,6),(260,258,'新增',NULL,'business:researchgain:save',2,NULL,6),(261,258,'修改',NULL,'business:researchgain:update',2,NULL,6),(262,258,'删除',NULL,'business:researchgain:delete',2,NULL,6),(263,38,'部门科研成果列表','business/researchgain/er',NULL,1,'daohang',6),(264,263,'查看',NULL,'business:researchgain:list,business:researchgain:info',2,NULL,6),(265,263,'新增',NULL,'business:researchgain:save',2,NULL,6),(266,263,'修改',NULL,'business:researchgain:update',2,NULL,6),(267,263,'删除',NULL,'business:researchgain:delete',2,NULL,6),(268,38,'学校科研成果列表','business/researchgain/school',NULL,1,'daohang',6),(269,268,'查看',NULL,'business:researchgain:list,business:researchgain:info',2,NULL,6),(270,268,'新增',NULL,'business:researchgain:save',2,NULL,6),(271,268,'修改',NULL,'business:researchgain:update',2,NULL,6),(272,268,'删除',NULL,'business:researchgain:delete',2,NULL,6),(273,43,'获奖级别列表','business/basics/competitiongrade',NULL,1,'daohang',6),(274,273,'查看',NULL,'business:competitiongrade:list,business:competitiongrade:info',2,NULL,6),(275,273,'新增',NULL,'business:competitiongrade:save',2,NULL,6),(276,273,'修改',NULL,'business:competitiongrade:update',2,NULL,6),(277,273,'删除',NULL,'business:competitiongrade:delete',2,NULL,6),(278,43,'获奖等次列表','business/basics/competitionrank',NULL,1,'daohang',6),(279,278,'查看',NULL,'business:competitionrank:list,business:competitionrank:info',2,NULL,6),(280,278,'新增',NULL,'business:competitionrank:save',2,NULL,6),(281,278,'修改',NULL,'business:competitionrank:update',2,NULL,6),(282,278,'删除',NULL,'business:competitionrank:delete',2,NULL,6),(283,43,'成果类型列表','business/basics/gaintype',NULL,1,'daohang',6),(284,283,'查看',NULL,'business:gaintype:list,business:gaintype:info',2,NULL,6),(285,283,'新增',NULL,'business:gaintype:save',2,NULL,6),(286,283,'修改',NULL,'business:gaintype:update',2,NULL,6),(287,283,'删除',NULL,'business:gaintype:delete',2,NULL,6),(288,43,'论文等级列表','business/basics/papersgrade',NULL,1,'daohang',6),(289,288,'查看',NULL,'business:papersgrade:list,business:papersgrade:info',2,NULL,6),(290,288,'新增',NULL,'business:papersgrade:save',2,NULL,6),(291,288,'修改',NULL,'business:papersgrade:update',2,NULL,6),(292,288,'删除',NULL,'business:papersgrade:delete',2,NULL,6),(293,43,'项目类别列表','business/basics/projectcategory',NULL,1,'daohang',6),(294,293,'查看',NULL,'business:projectcategory:list,business:projectcategory:info',2,NULL,6),(295,293,'新增',NULL,'business:projectcategory:save',2,NULL,6),(296,293,'修改',NULL,'business:projectcategory:update',2,NULL,6),(297,293,'删除',NULL,'business:projectcategory:delete',2,NULL,6),(298,43,'项目级别列表','business/basics/projectlevel',NULL,1,'daohang',6),(299,298,'查看',NULL,'business:projectlevel:list,business:projectlevel:info',2,NULL,6),(300,298,'新增',NULL,'business:projectlevel:save',2,NULL,6),(301,298,'修改',NULL,'business:projectlevel:update',2,NULL,6),(302,298,'删除',NULL,'business:projectlevel:delete',2,NULL,6);
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_oss`
--

DROP TABLE IF EXISTS `sys_oss`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_oss` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(200) DEFAULT NULL COMMENT 'URL地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文件上传';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_oss`
--

LOCK TABLES `sys_oss` WRITE;
/*!40000 ALTER TABLE `sys_oss` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_oss` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'学校管理员','管理全校信息',1,'2020-05-28 11:11:24'),(2,'二级学院管理员','只能管理该学院下的所有教师',1,'2020-06-12 16:04:47'),(3,'教师','系统最基础角色',1,'2020-06-13 11:25:06');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1482 DEFAULT CHARSET=utf8mb4 COMMENT='角色与菜单对应关系';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` VALUES (1163,3,44),(1164,3,45),(1165,3,46),(1166,3,47),(1167,3,48),(1168,3,59),(1169,3,60),(1170,3,61),(1171,3,62),(1172,3,63),(1173,3,79),(1174,3,80),(1175,3,81),(1176,3,82),(1177,3,83),(1178,3,54),(1179,3,55),(1180,3,56),(1181,3,57),(1182,3,58),(1183,3,228),(1184,3,229),(1185,3,230),(1186,3,231),(1187,3,232),(1188,3,243),(1189,3,244),(1190,3,245),(1191,3,246),(1192,3,247),(1193,3,258),(1194,3,259),(1195,3,260),(1196,3,261),(1197,3,262),(1198,3,134),(1199,3,135),(1200,3,136),(1201,3,137),(1202,3,138),(1203,3,124),(1204,3,125),(1205,3,126),(1206,3,127),(1207,3,128),(1208,3,129),(1209,3,130),(1210,3,131),(1211,3,132),(1212,3,133),(1213,3,43),(1214,3,49),(1215,3,50),(1216,3,51),(1217,3,52),(1218,3,53),(1219,3,166),(1220,3,273),(1221,3,274),(1222,3,275),(1223,3,276),(1224,3,277),(1225,3,278),(1226,3,279),(1227,3,280),(1228,3,281),(1229,3,282),(1230,3,283),(1231,3,284),(1232,3,285),(1233,3,286),(1234,3,287),(1235,3,288),(1236,3,289),(1237,3,290),(1238,3,291),(1239,3,292),(1240,3,293),(1241,3,294),(1242,3,295),(1243,3,296),(1244,3,297),(1245,3,298),(1246,3,299),(1247,3,300),(1248,3,301),(1249,3,302),(1250,3,-666666),(1251,3,32),(1252,3,33),(1253,3,34),(1254,3,35),(1255,3,36),(1256,3,37),(1257,3,38),(1258,3,39),(1259,3,40),(1260,3,41),(1261,2,151),(1262,2,152),(1263,2,153),(1264,2,154),(1265,2,155),(1266,2,168),(1267,2,169),(1268,2,170),(1269,2,171),(1270,2,172),(1271,2,156),(1272,2,157),(1273,2,158),(1274,2,159),(1275,2,160),(1276,2,178),(1277,2,179),(1278,2,180),(1279,2,181),(1280,2,182),(1281,2,188),(1282,2,189),(1283,2,190),(1284,2,191),(1285,2,192),(1286,2,233),(1287,2,234),(1288,2,235),(1289,2,236),(1290,2,237),(1291,2,248),(1292,2,249),(1293,2,250),(1294,2,251),(1295,2,252),(1296,2,263),(1297,2,264),(1298,2,265),(1299,2,266),(1300,2,267),(1301,2,208),(1302,2,209),(1303,2,210),(1304,2,211),(1305,2,212),(1306,2,218),(1307,2,219),(1308,2,220),(1309,2,221),(1310,2,222),(1311,2,198),(1312,2,199),(1313,2,200),(1314,2,201),(1315,2,202),(1316,2,140),(1317,2,141),(1318,2,142),(1319,2,143),(1320,2,144),(1321,2,43),(1322,2,49),(1323,2,50),(1324,2,51),(1325,2,52),(1326,2,53),(1327,2,166),(1328,2,273),(1329,2,274),(1330,2,275),(1331,2,276),(1332,2,277),(1333,2,278),(1334,2,279),(1335,2,280),(1336,2,281),(1337,2,282),(1338,2,283),(1339,2,284),(1340,2,285),(1341,2,286),(1342,2,287),(1343,2,288),(1344,2,289),(1345,2,290),(1346,2,291),(1347,2,292),(1348,2,293),(1349,2,294),(1350,2,295),(1351,2,296),(1352,2,297),(1353,2,298),(1354,2,299),(1355,2,300),(1356,2,301),(1357,2,302),(1358,2,-666666),(1359,2,31),(1360,2,32),(1361,2,33),(1362,2,34),(1363,2,35),(1364,2,36),(1365,2,37),(1366,2,38),(1367,2,39),(1368,2,40),(1369,2,41),(1370,2,42),(1371,1,146),(1372,1,147),(1373,1,148),(1374,1,149),(1375,1,150),(1376,1,173),(1377,1,174),(1378,1,175),(1379,1,176),(1380,1,177),(1381,1,161),(1382,1,162),(1383,1,163),(1384,1,164),(1385,1,165),(1386,1,183),(1387,1,184),(1388,1,185),(1389,1,186),(1390,1,187),(1391,1,193),(1392,1,194),(1393,1,195),(1394,1,196),(1395,1,197),(1396,1,238),(1397,1,239),(1398,1,240),(1399,1,241),(1400,1,242),(1401,1,253),(1402,1,254),(1403,1,255),(1404,1,256),(1405,1,257),(1406,1,268),(1407,1,269),(1408,1,270),(1409,1,271),(1410,1,272),(1411,1,213),(1412,1,214),(1413,1,215),(1414,1,216),(1415,1,217),(1416,1,223),(1417,1,224),(1418,1,225),(1419,1,226),(1420,1,227),(1421,1,203),(1422,1,204),(1423,1,205),(1424,1,206),(1425,1,207),(1426,1,69),(1427,1,70),(1428,1,71),(1429,1,72),(1430,1,73),(1431,1,145),(1432,1,43),(1433,1,49),(1434,1,50),(1435,1,51),(1436,1,52),(1437,1,53),(1438,1,166),(1439,1,273),(1440,1,274),(1441,1,275),(1442,1,276),(1443,1,277),(1444,1,278),(1445,1,279),(1446,1,280),(1447,1,281),(1448,1,282),(1449,1,283),(1450,1,284),(1451,1,285),(1452,1,286),(1453,1,287),(1454,1,288),(1455,1,289),(1456,1,290),(1457,1,291),(1458,1,292),(1459,1,293),(1460,1,294),(1461,1,295),(1462,1,296),(1463,1,297),(1464,1,298),(1465,1,299),(1466,1,300),(1467,1,301),(1468,1,302),(1469,1,-666666),(1470,1,31),(1471,1,32),(1472,1,33),(1473,1,34),(1474,1,35),(1475,1,36),(1476,1,37),(1477,1,38),(1478,1,39),(1479,1,40),(1480,1,41),(1481,1,42);
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) DEFAULT NULL COMMENT '盐',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8mb4 COMMENT='系统用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,'soldier','6e1142e2463827c49ca40ca6c8433ef25010bd6a2d5c07b5022a00b7abdef360','YzcmCZNvbXocrsz9dm8e','583403411@qq.com','18277404022',1,1,'2016-11-11 11:11:11'),(2,'dsjxyGLY','e01d6efb815545f3044e98ae9703c2bd6956f7d4edfe440051180b234670a7e1','pK06O873IBP2SgEUcie5','11222220@qq.com','18277404022',1,1,'2020-06-09 15:59:07'),(10,'byxyGLY','84a62f30790593a7d0d4cc40449abcba310307b9fe5ca7d57da458aac350d26e','xtq80jiNMFIq3ui0GPHs','11@qq.com','18277404022',1,1,'2020-06-09 18:21:23'),(11,'18074','13234f4c7bfa967b67a66a4cd311bf57423bc1aa31d8ed0ed4c22323495506a9','1AfxS53dFnbNVsDsgpzn','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:10'),(12,'17005','63f3a4a0720e0eed26e8baff307da044be792f7c57eb39127ba765e745975340','eaKsDb7QQ6quwKEwULlL','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(13,'30008','568503854864b0f48503d0dc365534dc956d96346d17f1c6eaa6d9ef903e47a9','vjfJEWLwxHeiKf8U5Ln6','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(14,'17702','28d5f3d0ffa94efd3670c7f590d6ee13e3514971e38bec37e43cc923598cd9b7','RoLwh8KaI3xBAwjp9EfI','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(15,'17703','0f3d74e47b33a9fc980022b0d1172d1c4ecced6f65553a74a2b6f697a9549e83','ze1djsACXmqZTgsU2dbm','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(16,'17704','a5dd773eb73901405dd6f42c22816c9e6b800314722a3278bc7e95330a4c4627','YE0TG7CDRdPvk2GVWCZy','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(17,'17049','c934fc1c36ae6c7d5c6155a760ae31d0a56c40d10a68dad86f1326d0973e208c','riKgV4lmVcflcH4vgxUr','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(18,'17020','613ba7d8125e3170f93bcfd09fdb9b47511af5e1054b6e16c36b5b03bd6c044b','aVacQiLpsbeig08VpGsh','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(19,'17011','7f8cc3f53ea49df3e425277a5d7f703c10bae4a9f1632bc378b8d3cfe8e7fdf8','TMlp5clIQQ6qXAwhvE2P','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(20,'17022','fb735565f6c93660849b272f42807f4e3f067c50367a30bc07467d60102d0d22','bSSPMSHaFoiFMuepKuDQ','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(21,'27013','dbb98f98e7a36512fd1af7450ef819a88c0d12a2545abc642e0b833301c870c9','bgIHaBI1tDPtzZacF2Py','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(22,'17064','457ddf0888909de73da8292a81f0675139602383b4688e3e0a9b3f07229a333a','bNZhsm0ugNMK6R46p6Kg','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(23,'17018','7963a6385e19061cf2a9c1c1b634aef916e2a312ce0c88a08bd1c812565dd4e5','lc0VzWGMsMMUzvTt17Bs','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(24,'07003','e17e3e4004853a277ac467265014609e91c1a07c70f407979164871cad431ad6','bJh7qLxgSmeqeC2fkQ3L','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(25,'17037','f87be6fe81139c7a6acff450330ac3b02f9dc3798a504826d8740530b611a668','z496bsbLfs7pMh3z8EWU','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(26,'31047','69b5464d7aee4d0fa0bb08e37212905d2dbd8a5b63f001dcecb7087c55ccc004','E67dRgDfaSO0wfdNmpeT','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(27,'17202','cf3ec0efee17ffc1841664784e303da219eced751edfffb731bb943f3b704f8e','IvglE6rZwY3UjV9U82u3','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(28,'17132','41af414c619a361ab30f201126ff6e4e71a509a86ca379a729af4587701b4b07','zoOWkt4VLQEhM7XiWpSM','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(29,'17039','c0cc623a19433150b7668f61b9a17d8b0870720cb5143fe144938891661f65ad','wsrMgfaDDNfHGrXQlFYS','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(30,'17038','54edf0eb58672289357e1747e7dfcb4f66ffa171431b0891d9bea8042cde1bbf','TRrt5U5yHHqFKwqpCZIM','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(31,'17133','ad857718a2575dc3dacaf07d1bdaf775e4838a45df63f4e2a2369acc8c589289','wFUExQ5OXTB99IJZSH2U','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(32,'17213','12d237d9a9f8a0e8ef082d1f7315dc6a6b8eceb1ea9d1c2925d526d75a1191a7','Ux0Z03Cz3hsH3vajYck9','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(33,'31046','7cad5e517e19465985a7dc971881994bd85996e9d5c7b6cc2c1cd88f8bab9bda','i1u9Vd9Zs6ldt33a0tbo','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(34,'17041','5a86a25865b67e9a5b3abc9a4f599971a30a3e6356b985c3f99095d647c5d827','71Tsc93lFAFcmHxJSeYv','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(35,'17025','9e9dd2a1abf27a135e927e83acb6be61d27e5932a092ca91bac67a3fd2748616','oDXrAWzQUC9hswZhbj54','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(36,'17221','a363b959193db813617a689dffff9da0905e172b1ac50483f9522ccfcd3d1f68','bxfC9gCDH6krCawStauK','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(37,'17134','c21445de4cb2e1100cd1fe40054ee43c25c06a15376c43774576631598ec977a','WSUmE88PmQ9J9SMBP25v','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(38,'27105','973fbe5eaf75995fc1f797b980eef629d646104b39444a654cf24a62104255e4','zEUBgAsuAwHgfyNOQY6N','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(39,'17224','e231e1aae64aad444256a9421d5fef01ced66b623d895732b51270c0966af9c4','erDOFTa0cPTBONczxaYv','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(40,'17074','7e08c2966062d46372fcee8cfd025e351f1270290bb0831b601f4accb4605a41','0TifIoufYLZHzxEIOdJy','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(41,'17701','744d3f2c05d22ab63380dbb33c4d6a71129d0e7132cfcc71b6e7fcf7c904d2ad','m7ES9HDI65VTDQTZXdI6','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(42,'21016','d21baaa04fe4c123393ddb17eb05cf43a1fda19e469055038bfc92e3e74f8768','zcxjN5SaGplqKKE0cZ5x','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(43,'30011','f4aed8dd43dedf9cd7c5b028388604ae797a23a191e10673d12aea9c51eea698','vOu7xsu9gQx66PYMw03C','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(44,'21022','708619eedfd972b208faacfaf6ace309c2147805f711f8269954ea531a2f9bee','KmVwYOP3DDtVTvoAZ1uf','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(45,'21011','61821b22e9ebd9812d6f4ecd212bbd2e81ec92b8a7b9d6e56d3ff2b68bbd80d6','WdpytQkGYJejnM7nDRUb','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(46,'21054','3b99e132bdac2466c578c225a6d6f495ffa7cf5abadcf1819e766fc94e379767','H9q7pw7JyEQTiWBD0wWi','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(47,'21012','a49f8eea69b4f1433a8624ec68afb3887d213eea149632d03dcd3671779b9e6f','XOLPlvyfiixXJFJXcy2I','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(48,'28009','03fbeaad38270eba0e2b9524380124d30a9e472a6b263953d90be3a281cb3ad8','7k8ZH5ydqW3R0b0AB7hp','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(49,'21005','f2447659a50c1d97673260a174adf7ffe0cf63a7bcdf6fbc1e23c999fcf0236d','lMg8CCxwEFiISs2yUz44','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(50,'21014','40682c2980041872f5933ec9410f6cc7f92adc8982ca9829b5a51d640d95076d','Vnu3fYsaCjQ5qRXpuSLb','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(51,'30013','c84ee9affa8f1d0c3d8dd271e714d7f039de15efc9152f47594ca0f09e667c38','aP1CRIjobPeooyyKPOuq','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(52,'06003','588555ec315c08174122bb1e6d37cf5af0fc0ca0a8c2d88fec099641be1d018e','apdnKMT8OCnNpYSwkeHi','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(53,'21018','7a17ddc34afcdad57a7488b318f561cc256e07e29f28be8850cc6ed8a61c526b','7cF3NDsJVnuswUZr970q','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(54,'21017','a56c6e2ccc66a96e8ab6fc565c7dffc49676cc50ae89f0a63534b8e0311f17fb','lETSe1vPyTHBsOGeU82g','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(55,'23098','cfc07b54c24dae1edf44f26d3367cfa1fb0426b6d521e3a5948c5728ee0fac41','pOdnRsbAj9Gbis0kKAv1','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(56,'17010','7280d11086de86619f991b3a8e9671e005de92e5ec547968b8448108bb5ce235','5em1y4sgEaIxkIuxbjnX','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(57,'17044','4ac72a49a88ba8e766321b6f6e174d6417dd0a107689fbd27bbed843fbbf483d','3wVzNIYF7mkGXygxCAOU','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(58,'17062','a84b5e46453abbeb23c2e6b508e98e7febdaa9cb147cefebf3ec2d3b4e70431f','51gSiIelaBcE7iELXpH3','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(59,'17031','10fe4c40da651d64a82d090c9a8d12eca6933933aa6135ca1698f2654d52b3c6','HBdESFRFptfWbWOToenn','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(60,'17033','4c41fb416b045a72aaf3893196421e766010f01383c1cbd01be1918a2bbee064','cSLWVBCEsz2qa3myN7Iz','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(61,'17214','5f496946d7f28c0e7e350249827a534ce8531b0a4081e8fe26d12874481789de','q0HqjHRqntkz0CCMkqpo','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(62,'17046','79c6ec72841378c1a7443663a0d4ec82c79c243360838e6adc869412ffe7053a','GP7ro9IxfQ96aOvwVSGv','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(63,'17027','19733f17a26d02ccac1534e2bee5fc4a1ea6db8b7cd35709dd5bb8642ce8e5c4','gGnEsKDWUgFv5wsQxjtY','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(64,'17019','89d4e2fe5864c15cd0d45a826631e553fe0c814f8eb079be826c839091f7d252','RRo5d3NCKdnmmsEQ88f8','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(65,'17021','3b81910e4f5b51025491d7eee53549b2d24d1cd999faa8df81aeb27ff6f97a3f','d4oxLSSGCJZl97oHti9B','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(66,'21010','0a3ab24a74cb9fdb5556fb27bed3435d322315c7a1d137554b5f42469c831c44','DlzbftBRjlWYh52Dw9UM','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(67,'21019','35836bfd88e5206742fa9bdda9a80054b7fd3d6d0f4aed72536d4aa349265cad','V5KnaINZAKrgcbIMvsGD','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:16'),(68,'17001','99b134d593a4ef973a1ce540bf2092a0fd3bfe2effe94b2fc84b3e4a5d0f0034','oTKRPDAYWyl07KGwWPqF','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:17'),(69,'17042','b1957590c226bdc3b77649ac3768da429e7314cb3cbcb41fe38ccd614da3e99c','nWwXU45KP6GZNieMnIlO','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:17'),(70,'17003','935ad4f072a426ba56c666628f236eded36227577f1da3de7233d54be141d5b3','Gk6Q6WEyABJ3u2zkKtzZ','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:17'),(71,'17004','9468bafc8f4c42d558b24ea9874108532867127ade10e829dc0af3f9f80cb94c','HqYSanwK0YpYAJfDIa2L','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:17'),(72,'17007','3d5c813990a84e809f10b708f6285c1b57381567fae15ab5ac1fd3aaf08b7e97','sNEiExSq9MKXdobbzVEW','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:17'),(73,'17006','e8614f1788f03943f569589fb73f3f0ac3f8d431284b1cd9e8aad588fa9d5f88','Kopkosa4UKnkMASzIxGZ','123456@qq.com','18888888888',1,2,'2020-06-13 11:57:17'),(74,'wzxyGLY','1eb96f794ef51633daa3848007f7a959146b41b18fa225fd9eb0c2dc9a721d62','vnoVnMOl5VJYXMH1OJcj','18288888888@qq.com','18288888888',1,1,'2020-07-07 11:44:09'),(76,'dxxyGLY','b6af8547f835077907d14dbe9b19483ed909374be8885092d7ae292c91dad3f4','0W8GfueH4axPB47gm826','dxx@163.com','',1,74,'2020-09-25 13:22:54');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4 COMMENT='用户与角色对应关系';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES (12,11,3),(13,12,3),(14,13,3),(15,14,3),(16,15,3),(17,16,3),(18,17,3),(19,18,3),(20,19,3),(21,20,3),(22,21,3),(23,22,3),(24,23,3),(25,24,3),(26,25,3),(27,26,3),(28,27,3),(29,28,3),(30,29,3),(31,30,3),(32,31,3),(33,32,3),(34,33,3),(35,34,3),(36,35,3),(37,36,3),(38,37,3),(39,38,3),(40,39,3),(41,40,3),(42,41,3),(43,42,3),(44,43,3),(45,44,3),(46,45,3),(47,46,3),(48,47,3),(49,48,3),(50,49,3),(51,50,3),(52,51,3),(53,52,3),(54,53,3),(55,54,3),(56,55,3),(57,56,3),(58,57,3),(59,58,3),(60,59,3),(61,60,3),(62,61,3),(63,62,3),(64,63,3),(65,64,3),(66,65,3),(67,66,3),(68,67,3),(69,68,3),(70,69,3),(71,70,3),(72,71,3),(73,72,3),(74,73,3),(77,2,2),(78,10,2),(79,74,1),(80,76,2);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_token`
--

DROP TABLE IF EXISTS `sys_user_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_token` (
  `user_id` bigint(20) NOT NULL,
  `token` varchar(100) NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统用户Token';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_token`
--

LOCK TABLES `sys_user_token` WRITE;
/*!40000 ALTER TABLE `sys_user_token` DISABLE KEYS */;
INSERT INTO `sys_user_token` VALUES (1,'0dc8324f8f5c9fbb1f2911521e16eca3','2020-11-25 22:47:54','2020-11-25 10:47:54'),(2,'113a8a8ef2d83954d0f5ca48933c75b9','2020-11-26 06:42:37','2020-11-25 18:42:37'),(10,'cf271707b2e66e958c7dddefefc2d7a1','2020-09-18 01:48:04','2020-09-17 13:48:04'),(11,'825d8c8c7f9f6e779bad808aa7f30416','2020-11-29 08:29:02','2020-11-28 20:29:02'),(34,'d95f93dcef2bdaa07c14a3eeeda29df8','2020-12-01 07:05:50','2020-11-30 19:05:50'),(39,'ac6013247e414f629a7bd48b34b5b188','2020-10-01 04:06:55','2020-09-30 16:06:55'),(74,'20b25f212b93ece2571668d9a34c5400','2020-11-25 22:57:05','2020-11-25 10:57:05'),(76,'a84d8e424832c93eedcf1191f1b94a40','2020-09-26 01:23:30','2020-09-25 13:23:30');
/*!40000 ALTER TABLE `sys_user_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_academic_papers`
--

DROP TABLE IF EXISTS `tb_academic_papers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_academic_papers` (
  `ap_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '论文名称',
  `periodical_name` varchar(255) DEFAULT NULL COMMENT '发布期刊名称',
  `project_name` varchar(255) DEFAULT NULL COMMENT '归属项目名称及编号',
  `author_type` int(11) DEFAULT NULL COMMENT '作者类型:1-第一作者或通讯作者 2-独立作者 3-其他',
  `paper_type` int(11) DEFAULT NULL COMMENT '论文类型:1-教改类 2-科研类',
  `grade` bigint(20) DEFAULT NULL COMMENT '论文等级:国家级等',
  `publish_time` timestamp NULL DEFAULT NULL COMMENT '发表时间',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ap_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='学术论文';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_academic_papers`
--

LOCK TABLES `tb_academic_papers` WRITE;
/*!40000 ALTER TABLE `tb_academic_papers` DISABLE KEYS */;
INSERT INTO `tb_academic_papers` VALUES (2,'One novel representation of DNA sequence based on the global and local position information','scientific reports',NULL,1,2,4,'2017-12-31 16:00:00','2020-09-23 00:50:46','2020-09-23 00:50:46'),(3,'工科中升本人才培养模式探讨','教育教学论坛',NULL,1,1,1,'2018-12-31 16:00:00','2020-09-23 00:54:41','2020-09-23 00:54:41'),(4,'以大型工程案例为中心专业课程体系研究——以软件工程专业为例','教育现代化',NULL,1,1,1,'2018-12-31 16:00:00','2020-09-23 00:57:32','2020-09-23 00:57:32'),(5,'Cloud computing service center model for medical service and data processing','advances in engineering research',NULL,1,2,7,'2015-12-31 16:00:00','2020-09-23 01:02:59','2020-09-23 01:02:59');
/*!40000 ALTER TABLE `tb_academic_papers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_competition_grade`
--

DROP TABLE IF EXISTS `tb_competition_grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_competition_grade` (
  `value` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `label` varchar(255) DEFAULT NULL COMMENT '获奖级别',
  PRIMARY KEY (`value`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='竞赛获奖级别';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_competition_grade`
--

LOCK TABLES `tb_competition_grade` WRITE;
/*!40000 ALTER TABLE `tb_competition_grade` DISABLE KEYS */;
INSERT INTO `tb_competition_grade` VALUES (1,'国家级'),(2,'区级（省级）'),(3,'校级');
/*!40000 ALTER TABLE `tb_competition_grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_competition_rank`
--

DROP TABLE IF EXISTS `tb_competition_rank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_competition_rank` (
  `value` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `label` varchar(255) DEFAULT NULL COMMENT '获奖等次',
  PRIMARY KEY (`value`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='竞赛获奖等次';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_competition_rank`
--

LOCK TABLES `tb_competition_rank` WRITE;
/*!40000 ALTER TABLE `tb_competition_rank` DISABLE KEYS */;
INSERT INTO `tb_competition_rank` VALUES (1,'一等奖'),(2,'二等奖'),(3,'三等奖'),(4,'特等奖'),(5,'优秀奖');
/*!40000 ALTER TABLE `tb_competition_rank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_gain_type`
--

DROP TABLE IF EXISTS `tb_gain_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_gain_type` (
  `value` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `label` varchar(255) DEFAULT NULL COMMENT '成果类型',
  PRIMARY KEY (`value`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='成果类型';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_gain_type`
--

LOCK TABLES `tb_gain_type` WRITE;
/*!40000 ALTER TABLE `tb_gain_type` DISABLE KEYS */;
INSERT INTO `tb_gain_type` VALUES (1,'发明专利'),(2,'实用新型专利'),(3,'软件著作权'),(4,'科研成果登记');
/*!40000 ALTER TABLE `tb_gain_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_innovate_project`
--

DROP TABLE IF EXISTS `tb_innovate_project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_innovate_project` (
  `ip_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `entry_stu` varchar(255) DEFAULT NULL COMMENT '项目组成员',
  `money` float DEFAULT NULL COMMENT '经费/万元',
  `award_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '立项时间',
  `grade` int(11) DEFAULT NULL COMMENT '立项级别 国家级等',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`ip_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='大创项目表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_innovate_project`
--

LOCK TABLES `tb_innovate_project` WRITE;
/*!40000 ALTER TABLE `tb_innovate_project` DISABLE KEYS */;
INSERT INTO `tb_innovate_project` VALUES (1,'基于区块链的政校企共享信息平台设计与实现','莫智帆201700208424，陈瀚文/201700208102,蔡晓梅/201700208433,陈水兰/201700208532,冯天华/201809512204',10,'2019-12-31 16:00:00',1,'2020-09-25 05:32:43','2020-09-25 05:32:43'),(2,'交通执法预警平台','刘清旭/201600208212,谈茵茵/201600208446,杨长官/201600208230,莫智帆/201700208424,陈瀚文/201700208102',1,'2018-12-31 16:00:00',1,'2020-09-25 05:35:43','2020-09-25 05:35:43'),(3,'项目建设过程监管云服务平台','莫东林/201600208417,孙艺/201600208225,商文/201600208444,叶城廷/201600208231,莫智帆/201700208424',1,'2018-12-31 16:00:00',1,'2020-09-25 05:37:09','2020-09-25 05:37:09'),(4,'党务工作建设及管理信息平台','孙艺/201600208225,莫东林/201600208417,刘玉霜/201600208442,杨长官/201600208230,谭文小/201700208342',1,'2018-12-31 16:00:00',1,'2020-09-25 05:38:00','2020-09-25 05:38:00'),(5,'毕业设计自动归档及过程监管信息平台','叶城廷/201600208231,魏祥/201600208426,黄结/201700208203,杨彪/201700208524,陈水兰/201700208532',1,'2018-12-31 16:00:00',1,'2020-09-25 05:38:52','2020-09-25 05:38:52');
/*!40000 ALTER TABLE `tb_innovate_project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_match_info`
--

DROP TABLE IF EXISTS `tb_match_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_match_info` (
  `match_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `organizer` varchar(255) DEFAULT NULL COMMENT '主办单位',
  `contractor` varchar(255) DEFAULT NULL COMMENT '承办单位',
  `name` varchar(255) DEFAULT NULL COMMENT '赛事名称',
  `grade` int(11) DEFAULT NULL COMMENT '赛事等级：1-国家级 2-省级 3-校级',
  `type` int(11) DEFAULT NULL COMMENT '赛事类型：1-学生竞赛 2-教师竞赛',
  PRIMARY KEY (`match_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COMMENT='赛事信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_match_info`
--

LOCK TABLES `tb_match_info` WRITE;
/*!40000 ALTER TABLE `tb_match_info` DISABLE KEYS */;
INSERT INTO `tb_match_info` VALUES (1,'中国大学生计算机设计大赛组织委员会','梧州学院','中国大学生计算机设计大赛',1,1),(2,'暂无','暂无','全国教育教学信息化大赛',1,2),(3,'暂无','暂无','广西高等教育教学软件应用大赛',2,2),(4,'暂无','暂无','全区高校青年教师教学竞赛',2,2),(5,'暂无','暂无','全国软件和信息技术专业人才大赛',1,2),(6,'梧州学院','梧州学院','全国大学生数字媒体科技作品及创意竞赛',1,1),(7,'梧州学院','梧州学院','中国“互联网+”大学生创新创业大赛',1,1),(8,'梧州学院','梧州学院','“创青春”全国大学生创新创业大赛',1,1),(9,'梧州学院','梧州学院','全国信息安全与对抗技术竞赛',1,1),(10,'梧州学院','梧州学院','广西高校计算机应用大赛',2,1),(11,'梧州学院','梧州学院','广西大学生程序设计大赛',2,1),(12,'梧州学院','梧州学院','中国软件杯大学生软件设计大赛',1,1),(13,'梧州学院','梧州学院','全国高校物联网应用创新大赛',1,1),(14,'梧州学院','梧州学院','全国大学生数学建模大赛',1,1),(15,'共青团广西壮族自治区委员会、广西壮族自治区科技厅、广西壮族自治区教育厅','广西大学','\"挑战杯”全国大学生课外科技作品大赛',1,1),(16,'广西教育厅、广西人工智能学会','广西人工智能学会','广西大学生人工智能设计大赛',2,1);
/*!40000 ALTER TABLE `tb_match_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_papers_grade`
--

DROP TABLE IF EXISTS `tb_papers_grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_papers_grade` (
  `value` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `label` varchar(255) DEFAULT NULL COMMENT '论文等级',
  PRIMARY KEY (`value`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='论文等级';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_papers_grade`
--

LOCK TABLES `tb_papers_grade` WRITE;
/*!40000 ALTER TABLE `tb_papers_grade` DISABLE KEYS */;
INSERT INTO `tb_papers_grade` VALUES (1,'国家级'),(2,'省级'),(3,'SCI2区'),(4,'SCI3区'),(5,'SCI4区'),(6,'EI检索期刊论文JA类型'),(7,'ISTP、EI检索会议论文CA类型'),(8,'梧州学院核心A级'),(9,'梧州学院核心B级'),(10,'梧州学院核心C级'),(11,'其它');
/*!40000 ALTER TABLE `tb_papers_grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_project_category`
--

DROP TABLE IF EXISTS `tb_project_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_project_category` (
  `value` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `label` varchar(255) DEFAULT NULL COMMENT '项目类别',
  PRIMARY KEY (`value`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='项目类别';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_project_category`
--

LOCK TABLES `tb_project_category` WRITE;
/*!40000 ALTER TABLE `tb_project_category` DISABLE KEYS */;
INSERT INTO `tb_project_category` VALUES (1,'国家级地区项目'),(2,'省级地区项目'),(3,'广西科技厅科技开发项目'),(4,'广西教育厅科技开发项目'),(5,'广西中青年教师基础能力提升项目'),(6,'横向'),(7,'纵向'),(8,'广西高等教育本科教学改革工程项目'),(9,'梧州学院教育教学改革工程项目'),(10,'梧州市科技开发项目');
/*!40000 ALTER TABLE `tb_project_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_project_create`
--

DROP TABLE IF EXISTS `tb_project_create`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_project_create` (
  `pc_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '课题名称',
  `number` varchar(255) DEFAULT NULL COMMENT '项目编号 ',
  `type` int(11) DEFAULT NULL COMMENT '项目类型:1-科研类 2-教改类',
  `category` int(11) DEFAULT NULL COMMENT '项目类别：横向等',
  `level` int(11) DEFAULT NULL COMMENT '项目级别：一般项目等',
  `money` float DEFAULT NULL COMMENT '项目经费 万元',
  `is_finish` int(11) DEFAULT '0' COMMENT '结题状态',
  `start_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间',
  `end_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '结束时间',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '立项时间',
  `creation_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`pc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COMMENT='教师项目立项';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_project_create`
--

LOCK TABLES `tb_project_create` WRITE;
/*!40000 ALTER TABLE `tb_project_create` DISABLE KEYS */;
INSERT INTO `tb_project_create` VALUES (1,'桂平市脱贫攻坚辅助数据平台','暂无',1,6,6,70,1,'2016-09-30 16:00:00','2020-12-30 16:00:00','2016-08-24 16:00:00','2020-08-11 12:18:57','2020-09-15 01:41:22'),(2,'贵港市港南区脱贫攻坚辅助数据平台','暂无',1,6,6,70,0,'2016-11-04 16:00:00','2020-11-04 16:00:00','2015-12-31 16:00:00','2020-09-15 12:31:24','2020-09-15 12:31:24'),(3,'贵港市覃塘区脱贫攻坚辅助数据平台','暂无',1,6,6,70,0,'2016-09-30 16:00:00','2020-12-30 16:00:00','2015-12-31 16:00:00','2020-09-15 12:35:18','2020-09-15 12:35:18'),(5,'数字城管视频综合管理平台构建及应用示范','桂科AC16380090',1,3,4,6,0,'2015-12-31 16:00:00','2017-12-30 16:00:00','2015-12-31 16:00:00','2020-09-15 12:44:39','2020-09-15 13:28:13'),(6,'贵港市港北区脱贫攻坚辅助数据平台技术服务','暂无',1,6,1,8.5,0,'2016-08-31 16:00:00','2020-12-30 16:00:00','2015-12-31 16:00:00','2020-09-15 12:46:18','2020-09-15 12:46:18'),(7,'岑溪市精准扶贫政务系统','暂无',1,6,6,80,0,'2017-11-04 16:00:00','2020-12-30 16:00:00','2016-12-31 16:00:00','2020-09-15 12:50:27','2020-09-15 12:50:27'),(8,'梧州市食品药品检验所报告书系统','暂无',1,6,6,11.5,0,'2016-02-04 16:00:00','2016-04-30 16:00:00','2015-12-31 16:00:00','2020-09-15 12:53:22','2020-09-15 12:53:22'),(9,'苍梧县新型农村合作医疗系统（二期）','暂无',1,6,6,4,0,'2014-12-31 16:00:00','2015-12-30 16:00:00','2014-12-31 16:00:00','2020-09-15 12:55:07','2020-09-15 12:55:07'),(10,'苍梧县新型农村合作医疗系统维护','2万元/年，滚动支持',1,6,6,2,0,'2014-12-31 16:00:00','2017-12-30 16:00:00','2014-12-31 16:00:00','2020-09-15 12:56:47','2020-09-15 12:56:47'),(11,'梧州市医疗卫生云计算服务平台与应用示范','201301004-3',2,10,10,2,0,'2012-12-31 16:00:00','2020-12-30 16:00:00','2012-12-31 16:00:00','2020-09-15 12:59:40','2020-09-15 12:59:40'),(12,'梧州市宏观经济监测预测预警管理系统','暂无',1,6,6,20,0,'2014-08-04 16:00:00','2015-08-07 16:00:00','2013-12-31 16:00:00','2020-09-15 13:02:31','2020-09-15 13:02:31'),(13,'苍梧县新型农村合作医疗一卡通及村卫接口建设','暂无',1,6,6,12.9,0,'2013-12-27 16:00:00','2014-03-27 16:00:00','2012-12-31 16:00:00','2020-09-15 13:04:09','2020-09-25 04:18:08'),(14,'新工科背景下以工程教育专业认证为导向的地方应用型本科高校软件工程专业建设研究——以梧州学院为例','2020JGZ151',2,8,11,3,0,'2019-12-31 16:00:00','2020-12-30 16:00:00','2019-12-31 16:00:00','2020-09-15 13:06:40','2020-09-30 08:04:17'),(17,'广西一流本科课程《软件工程》','',2,9,9,1,0,'2019-08-31 16:00:00','2024-08-31 16:00:00','2018-12-31 16:00:00','2020-09-25 04:20:07','2020-09-25 04:20:07');
/*!40000 ALTER TABLE `tb_project_create` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_project_finish`
--

DROP TABLE IF EXISTS `tb_project_finish`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_project_finish` (
  `pf_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pc_id` bigint(20) DEFAULT NULL COMMENT '立项项目id',
  `tea_id` bigint(20) DEFAULT NULL COMMENT '结题教师id',
  `finish_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '结题时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`pf_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='项目结题表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_project_finish`
--

LOCK TABLES `tb_project_finish` WRITE;
/*!40000 ALTER TABLE `tb_project_finish` DISABLE KEYS */;
INSERT INTO `tb_project_finish` VALUES (1,1,24,'2020-08-02 16:00:00','给钱22','2020-08-18 02:38:30','2020-08-18 03:41:06');
/*!40000 ALTER TABLE `tb_project_finish` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_project_level`
--

DROP TABLE IF EXISTS `tb_project_level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_project_level` (
  `value` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `label` varchar(255) DEFAULT NULL COMMENT '项目级别',
  PRIMARY KEY (`value`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='项目级别';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_project_level`
--

LOCK TABLES `tb_project_level` WRITE;
/*!40000 ALTER TABLE `tb_project_level` DISABLE KEYS */;
INSERT INTO `tb_project_level` VALUES (1,'国家自然科学基金'),(2,'省级自然科学基金'),(3,'省级科技开发项目'),(4,'教育厅科技开发项目'),(5,'教育厅科研项目'),(6,'社会服务'),(7,'一般项目A类'),(8,'一般项目B类'),(9,'一般项目'),(10,'梧州市科技开发项目'),(11,'重点项目');
/*!40000 ALTER TABLE `tb_project_level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_research_gain`
--

DROP TABLE IF EXISTS `tb_research_gain`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_research_gain` (
  `rg_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '成果名称',
  `entry_stu` varchar(255) DEFAULT NULL COMMENT '参与学生(可为空)',
  `award_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '获得时间',
  `gain` int(11) DEFAULT NULL COMMENT '成果类型',
  `grade` int(11) DEFAULT NULL COMMENT '成果级别 国家级等',
  `rank` int(11) DEFAULT NULL COMMENT '成果等次 一等奖等',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`rg_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='科研成果表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_research_gain`
--

LOCK TABLES `tb_research_gain` WRITE;
/*!40000 ALTER TABLE `tb_research_gain` DISABLE KEYS */;
INSERT INTO `tb_research_gain` VALUES (1,'高校疫情防控管理信息系统','17软件工程：董冰雪、曾加伟、黄结、杨秋月，莫智帆，陈楚文，梁宇兰，杨彪，龙凤銮，19软件工程：戴润芳','2019-12-31 16:00:00',3,1,NULL,'2020-09-25 04:37:04','2020-09-25 04:37:04'),(2,'高校毕业设计管理及过程监管平台','17软件工程：代红玉，陈熙官，陈少云，韦永恒，莫智帆，杨彪，黄结，方莉鑫，郑俊彬，周丽云','2019-12-31 16:00:00',3,1,NULL,'2020-09-25 04:39:50','2020-09-25 04:39:50'),(3,'政校企合作信息交互共享管理平台','2017级软件工程：李培玉、黄华露，汤海明，陈水兰，莫智帆，杨彪，程健学，陈俞可，劳奕谋','2019-12-31 16:00:00',3,1,NULL,'2020-09-25 04:42:51','2020-09-25 04:42:51'),(4,'脱贫攻坚辅助数据平台','','2016-12-31 16:00:00',3,1,NULL,'2020-09-25 04:48:06','2020-09-25 04:48:06');
/*!40000 ALTER TABLE `tb_research_gain` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_student_competition`
--

DROP TABLE IF EXISTS `tb_student_competition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_student_competition` (
  `sc_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `works_name` varchar(255) DEFAULT NULL COMMENT '作品名称',
  `entry_stu` varchar(255) DEFAULT NULL COMMENT '参赛学生',
  `grade` int(11) DEFAULT NULL COMMENT '获奖级别 国家级等',
  `rank` int(11) DEFAULT NULL COMMENT '获奖等次 一等奖等',
  `prize_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '获奖时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`sc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='学生竞赛';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_student_competition`
--

LOCK TABLES `tb_student_competition` WRITE;
/*!40000 ALTER TABLE `tb_student_competition` DISABLE KEYS */;
INSERT INTO `tb_student_competition` VALUES (1,'毕业选题档案管理系统','2017级软件工程（杨彪、陈水兰）、2016级软件工程（刘清旭）',1,1,'2019-01-07 16:00:00','2020-08-10 04:03:29','2020-09-16 00:52:03'),(2,'市政项目监管','2016级软件工程（潘宸、廖梦青）、2016级（尧志欣）',1,2,'2018-08-14 16:00:00','2020-09-15 00:39:04','2020-09-16 00:52:11'),(3,'互联网+扶贫精准数据协同平台','2014级软件工程（何志明、张燕、俸捷、韦曼玲、何金燕）',1,3,'2017-09-13 16:00:00','2020-09-15 00:40:36','2020-09-16 00:51:53'),(4,'“互联网+扶贫”扶贫精准数据协同平台','2014级软件工程（何志明、张燕、俸捷）',2,1,'2017-09-12 16:00:00','2020-09-15 00:41:45','2020-09-16 00:52:35'),(5,'脱贫攻坚数据协同平台','2015级软件工程（唐泽、黎兆祥）、2016级软件工程（李逸）',2,1,'2018-09-19 16:00:00','2020-09-15 00:42:43','2020-09-16 00:52:57'),(6,'市政项目监管','2016级软件工程（潘宸、廖梦青）、2015级软件工程（尧志欣）',2,2,'2018-09-19 16:00:00','2020-09-15 00:44:31','2020-09-16 00:53:17'),(7,'绩效任务管理平台','2017软件工程（黄结）、2016软件工程（谈茵茵、杨长官）',2,3,'2019-09-19 16:00:00','2020-09-15 00:45:42','2020-09-16 00:54:20'),(8,'毕业选题档案管理系统','2017级软件工程（杨彪、陈水兰）、2016级软件工程（刘清旭）',2,1,'2019-09-11 16:00:00','2020-09-15 00:46:33','2020-09-16 00:54:43'),(9,'基层党务工作建设及管理信息平台','2017级软件工程（莫智帆）、2016级软件工程（叶城廷、刘玉霜）',2,2,'2019-09-08 16:00:00','2020-09-15 00:47:17','2020-09-16 00:55:01'),(10,'食品药品检验所检验报告系统','2016级软件工程（孙艺、莫东林、商文鑫）',2,2,'2019-09-27 16:00:00','2020-09-15 00:47:58','2020-09-16 00:55:23'),(11,'项目建设过程监管服务平台','唐泽、尧志欣、廖梦青、莫东林、邓彦松',3,4,'2018-09-21 16:00:00','2020-09-15 00:49:21','2020-09-16 00:55:56'),(12,'“创青春”全国大学生创新创业大赛：项目建设过程监管云服务平台','唐泽、尧志欣、黎兆祥、莫东林、廖梦青、徐丽梅、姚如玉',2,3,'2018-09-06 16:00:00','2020-09-15 00:50:09','2020-09-16 00:56:18'),(13,'“创青春”全国大学生创新创业大赛：项目建设过程监管云服务平台','唐泽、尧志欣、黎兆祥、莫东林、廖梦青、徐丽梅、姚如玉',3,1,'2018-09-21 16:00:00','2020-09-15 00:51:08','2020-09-16 00:56:36'),(14,'“互联网+扶贫”扶贫精准数据协同平台','2016级软件工程（刘清旭、陈宣媛、孙艺、莫东林、商文鑫）',2,1,'2019-09-23 16:00:00','2020-09-15 00:52:32','2020-09-16 00:57:02'),(15,'基层党务工作建设管理信息平台','2017级软件工程（黄结、杨彪、莫智帆、陈水兰、兰云）',2,3,'2019-09-19 16:00:00','2020-09-15 00:53:18','2020-09-16 00:57:28'),(16,'项目建设过程监管云服务平台','2016级软件工程（杨长官、刘玉霜、谈茵茵、谭文小、叶城廷）',2,2,'2019-09-03 16:00:00','2020-09-15 00:54:07','2020-09-25 04:28:28'),(17,'“互联网+扶贫”扶贫精准数据协同平台','2017级软件工程：刘清旭、陈宣媛、刘春福、孙艺、莫东林',1,3,'2018-12-31 16:00:00','2020-09-25 04:27:15','2020-09-25 04:27:15');
/*!40000 ALTER TABLE `tb_student_competition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_sys_attach`
--

DROP TABLE IF EXISTS `tb_sys_attach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_sys_attach` (
  `attach_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '附件id',
  `function_id` varchar(255) DEFAULT NULL COMMENT '功能id：如论文附件=AP+paper_id',
  `attach_name` varchar(100) DEFAULT NULL COMMENT '附件名称',
  `attach_path` varchar(250) DEFAULT NULL COMMENT '附件路径',
  `is_del` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`attach_id`)
) ENGINE=InnoDB AUTO_INCREMENT=214 DEFAULT CHARSET=utf8mb4 COMMENT='系统附件';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_sys_attach`
--

LOCK TABLES `tb_sys_attach` WRITE;
/*!40000 ALTER TABLE `tb_sys_attach` DISABLE KEYS */;
INSERT INTO `tb_sys_attach` VALUES (15,'TC-1','教师logo.jpg','/home/soldier/Downloads/teacher-business/34/TC/教师logo.jpg',0),(21,'TH-2','教师logo.jpg','/home/soldier/Downloads/teacher-business/34/TH/教师logo.jpg',0),(23,'TH-3','教师logo.jpg','/home/soldier/Downloads/teacher-business/34/TH/教师logo.jpg',0),(24,'TH-1','教师logo.jpg','/home/soldier/Downloads/teacher-business/17041-莫智懿/TH/教师logo.jpg',0),(25,'TM-1','教师logo.jpg','/home/soldier/Downloads/teacher-business/17041-莫智懿/TM/教师logo.jpg',0),(26,'PF-1','教师logo.jpg','/home/soldier/Downloads/teacher-business/17041-莫智懿/PF/教师logo.jpg',0),(29,'PA-1','教师logo.jpg','/home/soldier/Downloads/teacher-business/17041-莫智懿/PA/教师logo.jpg',0),(30,'PA-1','教师logo(1).jpg','/home/soldier/Downloads/teacher-business/17041-莫智懿/PA/教师logo(1).jpg',0),(43,'software-1','简历模板.doc','/home/soldier/Downloads/teacher-business/17041-莫智懿/software/简历模板.doc',0),(47,'AP-1','网站头像.png','/home/soldier/Downloads/teacher-business/17041-莫智懿/AP/网站头像.png',0),(48,'AP-1','pexels-jeshootscom-714698.jpg','/home/soldier/Downloads/teacher-business/17041-莫智懿/AP/pexels-jeshootscom-714698.jpg',0),(49,'AP-1','alipay.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/AP/alipay.jpg',0),(50,'MATCH-1','xfwx-xfwx.zip','/home/soldier/Downloads/teacher-business/34/MATCH/xfwx-xfwx.zip',0),(53,'MATCH-4','xfwx-xfwx.zip','/home/soldier/Downloads/teacher-business/34/MATCH/xfwx-xfwx.zip',0),(54,'MATCH-5','xfwx-xfwx.zip','/home/soldier/Downloads/teacher-business/34/MATCH/xfwx-xfwx.zip',0),(55,'MATCH-6','xfwx-xfwx.zip','/home/soldier/Downloads/teacher-business/34/MATCH/xfwx-xfwx.zip',0),(56,'MATCH-7','xfwx-xfwx.zip','/home/soldier/Downloads/teacher-business/34/MATCH/xfwx-xfwx.zip',0),(57,'MATCH-8','xfwx-xfwx.zip','/home/soldier/Downloads/teacher-business/34/MATCH/xfwx-xfwx.zip',0),(58,'MATCH-9','xfwx-xfwx.zip','/home/soldier/Downloads/teacher-business/34/MATCH/xfwx-xfwx.zip',0),(59,'MATCH-10','xfwx-xfwx.zip','/home/soldier/Downloads/teacher-business/34/MATCH/xfwx-xfwx.zip',0),(60,'MATCH-11','xfwx-xfwx.zip','/home/soldier/Downloads/teacher-business/34/MATCH/xfwx-xfwx.zip',0),(61,'MATCH-12','xfwx-xfwx.zip','/home/soldier/Downloads/teacher-business/34/MATCH/xfwx-xfwx.zip',0),(62,'MATCH-13','xfwx-xfwx.zip','/home/soldier/Downloads/teacher-business/34/MATCH/xfwx-xfwx.zip',0),(63,'MATCH-14','xfwx-xfwx.zip','/home/soldier/Downloads/teacher-business/34/MATCH/xfwx-xfwx.zip',0),(64,'MATCH-15','xfwx-xfwx.zip','/home/soldier/Downloads/teacher-business/34/MATCH/xfwx-xfwx.zip',0),(65,'MATCH-16','xfwx-xfwx.zip','/home/soldier/Downloads/teacher-business/34/MATCH/xfwx-xfwx.zip',0),(66,'MATCH-2','教师logo.jpg','/home/soldier/Downloads/teacher-business/34/MATCH/教师logo.jpg',0),(67,'MATCH-3','教师logo.jpg','/home/soldier/Downloads/teacher-business/34/MATCH/教师logo.jpg',0),(93,'PC-1','gp001.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/gp001.jpg',0),(94,'PC-1','gp002.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/gp002.jpg',0),(95,'PC-1','gp003.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/gp003.jpg',0),(96,'PC-1','gp004.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/gp004.jpg',0),(97,'PC-1','gp005.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/gp005.jpg',0),(98,'PC-2','gn001.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/gn001.jpg',0),(99,'PC-2','gn002.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/gn002.jpg',0),(100,'PC-2','gn003.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/gn003.jpg',0),(101,'PC-2','gn004.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/gn004.jpg',0),(102,'PC-2','gn005.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/gn005.jpg',0),(103,'PC-3','Scan0004.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/Scan0004.jpg',0),(104,'PC-3','Scan0001.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/Scan0001.jpg',0),(105,'PC-3','Scan0014.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/Scan0014.jpg',0),(106,'PC-3','Scan0013.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/Scan0013.jpg',0),(107,'PC-3','Scan0011.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/Scan0011.jpg',0),(117,'PC-6','港北维护合同扫描.docx','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/港北维护合同扫描.docx',0),(118,'PC-7','第9页.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/第9页.jpg',0),(119,'PC-7','第11页.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/第11页.jpg',0),(120,'PC-7','第8页.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/第8页.jpg',0),(121,'PC-7','第1页.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/第1页.jpg',0),(122,'PC-7','封面.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/封面.jpg',0),(123,'PC-7','第12页.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/第12页.jpg',0),(124,'PC-7','第13页.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/第13页.jpg',0),(125,'PC-7','第10页.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/第10页.jpg',0),(126,'PC-7','第6页.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/第6页.jpg',0),(127,'PC-7','第7页.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/第7页.jpg',0),(128,'PC-8','4.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/4.jpg',0),(129,'PC-8','3.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/3.jpg',0),(130,'PC-8','2.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/2.jpg',0),(131,'PC-8','5.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/5.jpg',0),(132,'PC-8','7.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/7.jpg',0),(133,'PC-8','1.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/1.jpg',0),(134,'PC-8','6.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/6.jpg',0),(135,'PC-8','9.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/9.jpg',0),(136,'PC-8','8.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/8.jpg',0),(137,'PC-8','10.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/10.jpg',0),(138,'PC-9','苍梧新农合（二期）.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/苍梧新农合（二期）.jpg',0),(139,'PC-9','苍梧新农合（二期）3.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/苍梧新农合（二期）3.jpg',0),(140,'PC-9','苍梧新农合（二期）2.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/苍梧新农合（二期）2.jpg',0),(141,'PC-10','梧州学院技术服务合同（20000元）.pdf','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/梧州学院技术服务合同（20000元）.pdf',0),(142,'PC-11','附件.txt','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/附件.txt',0),(143,'PC-12','宏观经济合同 003.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/宏观经济合同 003.jpg',0),(144,'PC-12','宏观经济合同 000.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/宏观经济合同 000.jpg',0),(145,'PC-12','宏观经济合同 004.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/宏观经济合同 004.jpg',0),(146,'PC-12','宏观经济合同 005.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/宏观经济合同 005.jpg',0),(147,'PC-12','宏观经济合同 006.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/宏观经济合同 006.jpg',0),(148,'PC-12','宏观经济合同 001.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/宏观经济合同 001.jpg',0),(149,'PC-12','宏观经济合同 002.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/宏观经济合同 002.jpg',0),(152,'PC-15','宏观经济合同 003(1).jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/宏观经济合同 003(1).jpg',0),(165,'PC-5','广西科技计划项目合同（3）(1).jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/广西科技计划项目合同（3）(1).jpg',0),(166,'PC-5','广西科技计划项目合同（2）(1).jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/广西科技计划项目合同（2）(1).jpg',0),(167,'PC-5','广西科技计划项目合同（6）(1).jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/广西科技计划项目合同（6）(1).jpg',0),(168,'PC-5','广西科技计划项目合同（7）(1).jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/广西科技计划项目合同（7）(1).jpg',0),(169,'PC-5','广西科技计划项目合同（4）(2).jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/广西科技计划项目合同（4）(2).jpg',0),(170,'PC-5','广西科技计划项目合同（1）(1).jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/广西科技计划项目合同（1）(1).jpg',0),(171,'PC-5','广西壮族自治区科学技术厅文件（2）(1).jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/广西壮族自治区科学技术厅文件（2）(1).jpg',0),(172,'PC-5','广西壮族自治区科学技术厅文件（1）(1).jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/广西壮族自治区科学技术厅文件（1）(1).jpg',0),(173,'PC-5','广西科技计划项目合同（首页）(1).jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/广西科技计划项目合同（首页）(1).jpg',0),(174,'PC-5','广西科技计划项目合同（5）(1).jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/广西科技计划项目合同（5）(1).jpg',0),(177,'SC-3','中国大学生计算机设计大赛-2014级软件工程2班何志明-国家级三等奖-2017年.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/SC/中国大学生计算机设计大赛-2014级软件工程2班何志明-国家级三等奖-2017年.jpg',0),(178,'SC-1','大设赛一等奖.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/SC/大设赛一等奖.jpg',0),(179,'SC-2','中共大学生计算机设计大赛-2016级软件工程1班潘宸-国赛二等奖-2018.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/SC/中共大学生计算机设计大赛-2016级软件工程1班潘宸-国赛二等奖-2018.jpg',0),(180,'SC-4','中国大学生计算机设计大赛-2014级软件工程2班何志明-省级一等奖-2017年.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/SC/中国大学生计算机设计大赛-2014级软件工程2班何志明-省级一等奖-2017年.jpg',0),(181,'SC-5','中国大学生计算机设计大赛-2015级软件工程2班唐泽-省级一等奖-2018.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/SC/中国大学生计算机设计大赛-2015级软件工程2班唐泽-省级一等奖-2018.jpg',0),(182,'SC-6','中国大学生计算机设计大赛-2016级软件工程1班潘宸-省级二等奖-2018.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/SC/中国大学生计算机设计大赛-2016级软件工程1班潘宸-省级二等奖-2018.jpg',0),(183,'SC-7','绩效任务管理平台.jpeg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/SC/绩效任务管理平台.jpeg',0),(184,'SC-8','毕业选题档案管理系统.jpeg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/SC/毕业选题档案管理系统.jpeg',0),(185,'SC-9','基层党务工作2.jpeg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/SC/基层党务工作2.jpeg',0),(186,'SC-10','食品药品检验所检验报告系统.jpeg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/SC/食品药品检验所检验报告系统.jpeg',0),(187,'SC-11','项目建设过程.png','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/SC/项目建设过程.png',0),(188,'SC-12','“创青春”全国大学生创新创业大赛区级铜奖：项目建设过程监管云服务平台.png','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/SC/“创青春”全国大学生创新创业大赛区级铜奖：项目建设过程监管云服务平台.png',0),(189,'SC-13','校级创新春.png','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/SC/校级创新春.png',0),(190,'SC-14','挑战杯一等奖.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/SC/挑战杯一等奖.jpg',0),(191,'SC-15','挑战杯三等奖.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/SC/挑战杯三等奖.jpg',0),(193,'AP-2','One novel representation of DNA sequence based on the global and local position information论文电子版.pdf','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/AP/One novel representation of DNA sequence based on the global and local position information论文电子版.pdf',0),(194,'AP-3','教育教学论坛.rar','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/AP/教育教学论坛.rar',0),(195,'AP-4','教育现代化.rar','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/AP/教育现代化.rar',0),(196,'AP-5','Cloud computing service center model for medical service and data processing论文及检索证明.pdf','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/AP/Cloud computing service center model for medical service and data processing论文及检索证明.pdf',0),(198,'PC-13','2013苍梧一卡通合同扫描件.rar','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/2013苍梧一卡通合同扫描件.rar',0),(199,'PC-17','桂教高教〔2020〕1号自治区教育厅关于公布2019年自治区级一流本科课程名单的通知.pdf','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/桂教高教〔2020〕1号自治区教育厅关于公布2019年自治区级一流本科课程名单的通知.pdf',0),(200,'SC-17','挑战杯全国三等奖.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/SC/挑战杯全国三等奖.jpg',0),(201,'SC-16','挑战杯二等奖.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/SC/挑战杯二等奖.jpg',0),(202,'RG-1','1.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/RG/1.jpg',0),(203,'RG-2','高校毕业设计管理及过程监管平台.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/RG/高校毕业设计管理及过程监管平台.jpg',0),(204,'RG-3','政校企合作信息交互平台.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/RG/政校企合作信息交互平台.jpg',0),(205,'RG-4','软件著作权-莫智懿.jpeg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/RG/软件著作权-莫智懿.jpeg',0),(206,'TH-4','2011-2012优秀科研工作者.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/TH/2011-2012优秀科研工作者.jpg',0),(207,'TH-5','2013-2014优秀科研工作者.jpg','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/TH/2013-2014优秀科研工作者.jpg',0),(208,'IP-1','附件2：2020年自治区级大学生创新创业训练计划立项项目名单(梧州学院）.xlsx','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/IP/附件2：2020年自治区级大学生创新创业训练计划立项项目名单(梧州学院）.xlsx',0),(209,'IP-2','梧州学院2019年自治区级和国家级大学生创新创业训练计划项目推荐名单.xls','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/IP/梧州学院2019年自治区级和国家级大学生创新创业训练计划项目推荐名单.xls',0),(210,'IP-3','梧州学院2019年自治区级和国家级大学生创新创业训练计划项目推荐名单(1).xls','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/IP/梧州学院2019年自治区级和国家级大学生创新创业训练计划项目推荐名单(1).xls',0),(211,'IP-4','梧州学院2019年自治区级和国家级大学生创新创业训练计划项目推荐名单(2).xls','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/IP/梧州学院2019年自治区级和国家级大学生创新创业训练计划项目推荐名单(2).xls',0),(212,'IP-5','梧州学院2019年自治区级和国家级大学生创新创业训练计划项目推荐名单(3).xls','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/IP/梧州学院2019年自治区级和国家级大学生创新创业训练计划项目推荐名单(3).xls',0),(213,'PC-14','桂教高教〔2020〕54号自治区教育厅关于公布2020年度广西高等教育本科教学改革工程项目的通知（高教处）.pdf','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/PC/桂教高教〔2020〕54号自治区教育厅关于公布2020年度广西高等教育本科教学改革工程项目的通知（高教处）.pdf',0);
/*!40000 ALTER TABLE `tb_sys_attach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_teacher_competition`
--

DROP TABLE IF EXISTS `tb_teacher_competition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_teacher_competition` (
  `tc_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '竞赛名称',
  `grade` int(11) DEFAULT NULL COMMENT '获奖级别 国家级等',
  `rank` int(11) DEFAULT NULL COMMENT '获奖等次 一等奖等',
  `prize_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '获奖时间',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`tc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='教师竞赛';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_teacher_competition`
--

LOCK TABLES `tb_teacher_competition` WRITE;
/*!40000 ALTER TABLE `tb_teacher_competition` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_teacher_competition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_teacher_info`
--

DROP TABLE IF EXISTS `tb_teacher_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_teacher_info` (
  `teacher_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `teacher_code` varchar(255) DEFAULT NULL COMMENT '工号',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(255) DEFAULT NULL COMMENT '性别',
  `birth_day` timestamp NULL DEFAULT NULL COMMENT '出生日期',
  `entry_time` timestamp NULL DEFAULT NULL COMMENT '入职时间',
  `img_path` varchar(255) DEFAULT NULL COMMENT '个人照片',
  `high_edu` varchar(255) DEFAULT NULL COMMENT '最高学历',
  `first_edu` varchar(255) DEFAULT NULL COMMENT '第一学历',
  `technical_post` varchar(255) DEFAULT NULL COMMENT '专业技术职称',
  `administer_post` varchar(255) DEFAULT NULL COMMENT '行政职务',
  `resume` varchar(2000) DEFAULT NULL COMMENT '简历',
  `other` varchar(2000) DEFAULT NULL COMMENT '主要从事工作及研究方向',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`teacher_id`),
  UNIQUE KEY `teacher_code` (`teacher_code`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COMMENT='教师信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_teacher_info`
--

LOCK TABLES `tb_teacher_info` WRITE;
/*!40000 ALTER TABLE `tb_teacher_info` DISABLE KEYS */;
INSERT INTO `tb_teacher_info` VALUES (1,11,'18074','龚红梅','女','2019-01-14 16:00:00','2019-01-14 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(2,12,'17005','吴燕端','女','2019-01-15 16:00:00','2019-01-15 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(3,13,'30008','刘树先','男','2019-01-16 16:00:00','2019-01-16 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(4,14,'17702','邵晋芳','女','2019-01-17 16:00:00','2019-01-17 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(5,15,'17703','蔡敏仪','女','2019-01-18 16:00:00','2019-01-18 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(6,16,'17704','曾雨珊','女','2019-01-19 16:00:00','2019-01-19 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(7,17,'17049','汪梅','女','2019-01-20 16:00:00','2019-01-20 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(8,18,'17020','李健','男','2019-01-21 16:00:00','2019-01-21 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(9,19,'17011','陈佳','女','2019-01-22 16:00:00','2019-01-22 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(10,20,'17022','黄洁明','男','2019-01-23 16:00:00','2019-01-23 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(11,21,'27013','梁朝湘','女','2019-01-24 16:00:00','2019-01-24 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(12,22,'17064','吴芳','女','2019-01-25 16:00:00','2019-01-25 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(13,23,'17018','代丽娴','女','2019-01-26 16:00:00','2019-01-26 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(14,24,'07003','黄宏本','男','2019-01-27 16:00:00','2019-01-27 16:00:00',NULL,'暂无','暂无','暂无','大数据与软件工程学院院长','暂无','暂无','2020-06-12 16:00:00','2020-09-25 05:19:09'),(15,25,'17037','梁菁','女','2019-01-28 16:00:00','2019-01-28 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(16,26,'31047','李宗妮','女','2019-01-29 16:00:00','2019-01-29 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(17,27,'17202','农健','男','2019-01-30 16:00:00','2019-01-30 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(18,28,'17132','卿海军','男','2019-01-31 16:00:00','2019-01-31 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(19,29,'17039','陈聪','男','2019-02-01 16:00:00','2019-02-01 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(20,30,'17038','卢雪燕','女','2019-02-02 16:00:00','2019-02-02 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(21,31,'17133','黄寄洪','男','2019-02-03 16:00:00','2019-02-03 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(22,32,'17213','郑明','女','2019-02-04 16:00:00','2019-02-04 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(23,33,'31046','何希','男','2019-02-05 16:00:00','2019-02-05 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(24,34,'17041','莫智懿','男','1981-01-08 16:00:00','2004-06-30 16:00:00','/home/gxuwz/projectFile/teacher-business/17041-莫智懿/portrait/17041.jpg','硕士','本科','高级工程师','大数据与软件工程学院副院长','暂无','暂无','2020-06-12 16:00:00','2020-09-25 06:16:50'),(25,35,'17025','吴炎桃','女','2019-02-07 16:00:00','2019-02-07 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(26,36,'17221','陈悦','男','2019-02-08 16:00:00','2019-02-08 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(27,37,'17134','冀肖榆','男','2019-02-09 16:00:00','2019-02-09 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(28,38,'27105','许传本','男','2019-02-10 16:00:00','2019-02-10 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(29,39,'17224','朱肖颖','女','2019-02-11 16:00:00','2019-02-11 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(30,40,'17074','庞光垚','男','2019-02-12 16:00:00','2019-02-12 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(31,41,'17701','蒲保兴','男','2019-02-13 16:00:00','2019-02-13 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(32,42,'21016','覃学文','男','2019-02-14 16:00:00','2019-02-14 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(33,43,'30011','赵春茹','女','2019-02-15 16:00:00','2019-02-15 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(34,44,'21022','覃桂茳','女','2019-02-16 16:00:00','2019-02-16 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(35,45,'21011','刘敏捷','女','2019-02-17 16:00:00','2019-02-17 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(36,46,'21054','杨甲山','男','2019-02-18 16:00:00','2019-02-18 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(37,47,'21012','黄劲','男','2019-02-19 16:00:00','2019-02-19 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(38,48,'28009','胡庆席','女','2019-02-20 16:00:00','2019-02-20 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(39,49,'21005','李连芬','女','2019-02-21 16:00:00','2019-02-21 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(40,50,'21014','石向东','男','2019-02-22 16:00:00','2019-02-22 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(41,51,'30013','涂井先','男','2019-02-23 16:00:00','2019-02-23 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(42,52,'06003','陈红','女','2019-02-24 16:00:00','2019-02-24 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(43,53,'21018','李志军','男','2019-02-25 16:00:00','2019-02-25 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(44,54,'21017','许成章','男','2019-02-26 16:00:00','2019-02-26 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(45,55,'23098','吴飞燕','女','2019-02-27 16:00:00','2019-02-27 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(46,56,'17010','贺杰','男','2019-02-28 16:00:00','2019-02-28 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(47,57,'17044','宫海晓','男','2019-03-01 16:00:00','2019-03-01 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(48,58,'17062','邸臻炜','女','2019-03-02 16:00:00','2019-03-02 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(49,59,'17031','李翊','男','2019-03-03 16:00:00','2019-03-03 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(50,60,'17033','黄筱佟','女','2019-03-04 16:00:00','2019-03-04 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(51,61,'17214','杨秋慧','女','2019-03-05 16:00:00','2019-03-05 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(52,62,'17046','何高明','男','2019-03-06 16:00:00','2019-03-06 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(53,63,'17027','蒋琳琼','女','2019-03-07 16:00:00','2019-03-07 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(54,64,'17019','李海英','女','2019-03-08 16:00:00','2019-03-08 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(55,65,'17021','李军','男','2019-03-09 16:00:00','2019-03-09 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(56,66,'21010','苏芳','女','2019-03-10 16:00:00','2019-03-10 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(57,67,'21019','赵贤','女','2019-03-11 16:00:00','2019-03-11 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(58,68,'17001','甘金明','男','2019-03-12 16:00:00','2019-03-12 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(59,69,'17042','彭今虎','男','2019-03-13 16:00:00','2019-03-13 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(60,70,'17003','龚平','女','2019-03-14 16:00:00','2019-03-14 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(61,71,'17004','陆科达','男','2019-03-15 16:00:00','2019-03-15 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(62,72,'17007','冀占江','男','2019-03-16 16:00:00','2019-03-16 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00'),(63,73,'17006','卢在盛','男','2019-03-17 16:00:00','2019-03-17 16:00:00',NULL,'暂无','暂无','暂无','暂无','暂无','暂无','2020-06-12 16:00:00','2020-06-12 16:00:00');
/*!40000 ALTER TABLE `tb_teacher_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_teacher_of_ap`
--

DROP TABLE IF EXISTS `tb_teacher_of_ap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_teacher_of_ap` (
  `toap_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tea_id` bigint(20) DEFAULT NULL COMMENT '教师id',
  `ap_id` bigint(20) DEFAULT NULL COMMENT '论文id',
  `type` int(11) DEFAULT NULL COMMENT '作者类型:1-主持人 2-成员',
  PRIMARY KEY (`toap_id`)
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=utf8mb4 COMMENT='教师-of-论文表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_teacher_of_ap`
--

LOCK TABLES `tb_teacher_of_ap` WRITE;
/*!40000 ALTER TABLE `tb_teacher_of_ap` DISABLE KEYS */;
INSERT INTO `tb_teacher_of_ap` VALUES (101,24,1,1),(102,6,1,2),(103,7,1,2),(104,8,1,2),(105,9,1,2),(106,24,2,1),(107,22,2,2),(108,24,3,1),(109,9,3,2),(110,24,4,1),(111,9,4,2),(112,24,5,1),(113,28,5,2);
/*!40000 ALTER TABLE `tb_teacher_of_ap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_teacher_of_dept`
--

DROP TABLE IF EXISTS `tb_teacher_of_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_teacher_of_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tea_id` bigint(20) DEFAULT NULL COMMENT '教师ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8mb4 COMMENT='教师(部门成员)与部门对应关系';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_teacher_of_dept`
--

LOCK TABLES `tb_teacher_of_dept` WRITE;
/*!40000 ALTER TABLE `tb_teacher_of_dept` DISABLE KEYS */;
INSERT INTO `tb_teacher_of_dept` VALUES (1,1,1),(2,2,1),(3,3,1),(4,4,1),(5,5,1),(6,6,1),(7,7,1),(8,8,1),(9,9,1),(10,10,1),(11,11,1),(12,12,1),(13,13,1),(14,14,1),(15,15,1),(16,16,1),(17,17,1),(18,18,1),(19,19,1),(20,20,1),(21,21,1),(22,22,1),(23,23,1),(24,24,1),(25,25,1),(26,26,1),(27,27,1),(28,28,1),(29,29,1),(30,30,1),(31,31,1),(32,32,1),(33,33,1),(34,34,1),(35,35,1),(36,36,1),(37,37,1),(38,38,1),(39,39,1),(40,40,1),(41,41,1),(42,42,1),(43,43,1),(44,44,1),(45,45,1),(46,46,1),(47,47,1),(48,48,1),(49,49,1),(50,50,1),(51,51,1),(52,52,1),(53,53,1),(54,54,1),(55,55,1),(56,56,1),(57,57,1),(58,58,1),(59,59,1),(60,60,1),(61,61,1),(62,62,1),(63,63,1),(85,17,12),(86,27,12),(87,30,14),(88,28,14),(89,24,14),(90,23,14),(91,26,14),(92,29,14),(93,31,14),(94,25,14),(95,27,15),(96,39,15),(97,47,16),(98,48,16),(99,45,16),(100,50,16),(101,46,16),(102,49,16);
/*!40000 ALTER TABLE `tb_teacher_of_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_teacher_of_ip`
--

DROP TABLE IF EXISTS `tb_teacher_of_ip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_teacher_of_ip` (
  `toip_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tea_id` bigint(20) DEFAULT NULL COMMENT '教师id',
  `ip_id` bigint(20) DEFAULT NULL COMMENT '大创项目id',
  `type` int(11) DEFAULT NULL COMMENT '教师类型:1-第一指导老师 2-第二指导老师',
  PRIMARY KEY (`toip_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='教师-of-大创项目表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_teacher_of_ip`
--

LOCK TABLES `tb_teacher_of_ip` WRITE;
/*!40000 ALTER TABLE `tb_teacher_of_ip` DISABLE KEYS */;
INSERT INTO `tb_teacher_of_ip` VALUES (1,24,1,1),(2,24,2,1),(3,24,3,1),(4,24,4,1),(5,24,5,1);
/*!40000 ALTER TABLE `tb_teacher_of_ip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_teacher_of_proc`
--

DROP TABLE IF EXISTS `tb_teacher_of_proc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_teacher_of_proc` (
  `topc_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tea_id` bigint(20) DEFAULT NULL COMMENT '教师id',
  `pc_id` bigint(20) DEFAULT NULL COMMENT '项目立项id',
  `type` int(11) DEFAULT NULL COMMENT '教师类型:1-主持人 2-成员',
  PRIMARY KEY (`topc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=144 DEFAULT CHARSET=utf8mb4 COMMENT='教师-of-项目立项表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_teacher_of_proc`
--

LOCK TABLES `tb_teacher_of_proc` WRITE;
/*!40000 ALTER TABLE `tb_teacher_of_proc` DISABLE KEYS */;
INSERT INTO `tb_teacher_of_proc` VALUES (17,24,1,1),(18,24,1,2),(19,25,1,2),(20,26,1,2),(21,27,1,2),(22,28,1,2),(23,29,1,2),(24,30,1,2),(25,52,1,2),(26,46,1,2),(27,17,1,2),(28,24,2,1),(29,17,2,2),(30,24,2,2),(31,25,2,2),(32,26,2,2),(33,27,2,2),(34,28,2,2),(35,29,2,2),(36,30,2,2),(37,46,2,2),(38,52,2,2),(39,61,2,2),(40,24,3,1),(41,24,3,2),(42,28,3,2),(43,29,3,2),(44,30,3,2),(45,46,3,2),(46,52,3,2),(47,17,3,2),(48,25,3,2),(49,26,3,2),(50,27,3,2),(51,58,3,2),(52,61,3,2),(61,24,6,1),(62,24,6,2),(63,28,6,2),(64,24,7,1),(65,24,7,2),(66,25,7,2),(67,26,7,2),(68,27,7,2),(69,28,7,2),(70,29,7,2),(71,30,7,2),(72,46,7,2),(73,58,7,2),(74,61,7,2),(75,24,8,1),(76,24,8,2),(77,28,8,2),(78,24,9,1),(79,24,9,2),(80,28,9,2),(81,24,10,1),(82,24,10,2),(83,28,10,2),(84,24,11,1),(85,19,11,2),(86,24,11,2),(87,28,11,2),(88,30,11,2),(89,61,11,2),(90,24,12,1),(91,24,12,2),(92,28,12,2),(93,30,12,2),(94,61,12,2),(106,24,15,1),(107,24,15,2),(108,28,15,2),(109,30,15,2),(110,61,15,2),(119,24,5,1),(120,17,5,2),(121,24,5,2),(122,28,5,2),(130,24,13,1),(131,28,13,2),(132,24,17,1),(133,31,17,2),(134,27,17,2),(135,28,17,2),(136,29,17,2),(137,24,14,1),(138,25,14,2),(139,26,14,2),(140,27,14,2),(141,28,14,2),(142,29,14,2),(143,52,14,2);
/*!40000 ALTER TABLE `tb_teacher_of_proc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_teacher_of_rg`
--

DROP TABLE IF EXISTS `tb_teacher_of_rg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_teacher_of_rg` (
  `torg_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tea_id` bigint(20) DEFAULT NULL COMMENT '教师id',
  `rg_id` bigint(20) DEFAULT NULL COMMENT '科研成果id',
  `type` int(11) DEFAULT NULL COMMENT '教师类型:1-主持人 2-成员',
  PRIMARY KEY (`torg_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='教师-of-科研成果表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_teacher_of_rg`
--

LOCK TABLES `tb_teacher_of_rg` WRITE;
/*!40000 ALTER TABLE `tb_teacher_of_rg` DISABLE KEYS */;
INSERT INTO `tb_teacher_of_rg` VALUES (1,24,1,1),(2,24,2,1),(3,24,3,1),(4,24,4,1),(5,28,4,2);
/*!40000 ALTER TABLE `tb_teacher_of_rg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_teacher_of_stuc`
--

DROP TABLE IF EXISTS `tb_teacher_of_stuc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_teacher_of_stuc` (
  `tosc_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tea_id` bigint(20) DEFAULT NULL COMMENT '教师id',
  `sc_id` bigint(20) DEFAULT NULL COMMENT '学生竞赛id',
  `match_id` bigint(20) DEFAULT NULL COMMENT '赛事id',
  `type` int(11) DEFAULT NULL COMMENT '教师类型:1-第一指导老师 2-其它指导老师',
  PRIMARY KEY (`tosc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8 COMMENT='指导老师-of-学生竞赛表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_teacher_of_stuc`
--

LOCK TABLES `tb_teacher_of_stuc` WRITE;
/*!40000 ALTER TABLE `tb_teacher_of_stuc` DISABLE KEYS */;
INSERT INTO `tb_teacher_of_stuc` VALUES (32,24,3,1,1),(33,24,1,1,1),(34,24,2,1,1),(35,24,4,1,1),(36,24,5,1,1),(37,24,6,1,1),(38,24,7,1,1),(39,24,8,1,1),(40,24,9,1,1),(41,24,10,1,1),(42,24,11,7,1),(43,24,12,8,1),(44,24,13,8,1),(45,24,14,15,1),(46,24,15,15,1),(50,24,17,15,1),(51,24,16,15,1);
/*!40000 ALTER TABLE `tb_teacher_of_stuc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_teacher_of_ta`
--

DROP TABLE IF EXISTS `tb_teacher_of_ta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_teacher_of_ta` (
  `tota_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tea_id` bigint(20) DEFAULT NULL COMMENT '教师id',
  `ta_id` bigint(20) DEFAULT NULL COMMENT '教学成果id',
  `type` int(11) DEFAULT NULL COMMENT '教师类型:1-主持人 2-成员',
  PRIMARY KEY (`tota_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='教师-of-教学成果表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_teacher_of_ta`
--

LOCK TABLES `tb_teacher_of_ta` WRITE;
/*!40000 ALTER TABLE `tb_teacher_of_ta` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_teacher_of_ta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_teacher_of_teac`
--

DROP TABLE IF EXISTS `tb_teacher_of_teac`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_teacher_of_teac` (
  `totc_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tea_id` bigint(20) DEFAULT NULL COMMENT '教师id',
  `tc_id` bigint(20) DEFAULT NULL COMMENT '教师竞赛id',
  `match_id` bigint(20) DEFAULT NULL COMMENT '赛事id',
  `type` int(11) DEFAULT NULL COMMENT '教师类型:1-队长 2-成员',
  PRIMARY KEY (`totc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='指导老师-of-教师竞赛表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_teacher_of_teac`
--

LOCK TABLES `tb_teacher_of_teac` WRITE;
/*!40000 ALTER TABLE `tb_teacher_of_teac` DISABLE KEYS */;
INSERT INTO `tb_teacher_of_teac` VALUES (6,24,1,2,1),(7,54,1,2,2),(8,55,1,2,2);
/*!40000 ALTER TABLE `tb_teacher_of_teac` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_teacher_of_th`
--

DROP TABLE IF EXISTS `tb_teacher_of_th`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_teacher_of_th` (
  `tota_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tea_id` bigint(20) DEFAULT NULL COMMENT '教师id',
  `th_id` bigint(20) DEFAULT NULL COMMENT '教学荣誉id',
  `type` int(11) DEFAULT NULL COMMENT '教师类型:1-主持人 2-成员',
  PRIMARY KEY (`tota_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='教师-of-教材表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_teacher_of_th`
--

LOCK TABLES `tb_teacher_of_th` WRITE;
/*!40000 ALTER TABLE `tb_teacher_of_th` DISABLE KEYS */;
INSERT INTO `tb_teacher_of_th` VALUES (2,24,2,1),(4,24,3,1),(5,24,1,1),(6,1,1,2),(7,2,1,2),(8,24,4,1),(9,24,5,1);
/*!40000 ALTER TABLE `tb_teacher_of_th` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_teacher_of_tm`
--

DROP TABLE IF EXISTS `tb_teacher_of_tm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_teacher_of_tm` (
  `totm_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tea_id` bigint(20) DEFAULT NULL COMMENT '教师id',
  `tm_id` bigint(20) DEFAULT NULL COMMENT '教材id',
  `type` int(11) DEFAULT NULL COMMENT '教师类型:1-主编 2-成员',
  PRIMARY KEY (`totm_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='教师-of-教材表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_teacher_of_tm`
--

LOCK TABLES `tb_teacher_of_tm` WRITE;
/*!40000 ALTER TABLE `tb_teacher_of_tm` DISABLE KEYS */;
INSERT INTO `tb_teacher_of_tm` VALUES (1,24,1,1),(2,1,1,2),(3,2,1,2);
/*!40000 ALTER TABLE `tb_teacher_of_tm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_teaching_achievements`
--

DROP TABLE IF EXISTS `tb_teaching_achievements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_teaching_achievements` (
  `ta_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `award_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '获奖时间',
  `grade` int(11) DEFAULT NULL COMMENT '获奖级别 国家级等',
  `rank` int(11) DEFAULT NULL COMMENT '获奖等次 一等奖等',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`ta_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教学成果表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_teaching_achievements`
--

LOCK TABLES `tb_teaching_achievements` WRITE;
/*!40000 ALTER TABLE `tb_teaching_achievements` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_teaching_achievements` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_teaching_honor`
--

DROP TABLE IF EXISTS `tb_teaching_honor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_teaching_honor` (
  `th_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '荣誉称号',
  `grant_unit` varchar(255) DEFAULT NULL COMMENT '授予单位',
  `honor_time` varchar(255) DEFAULT NULL COMMENT '获得时间',
  `grade` int(11) DEFAULT NULL COMMENT '授予等级 国家级等',
  `rank` int(11) DEFAULT NULL COMMENT '授予等次 一等奖等',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`th_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='教学荣誉表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_teaching_honor`
--

LOCK TABLES `tb_teaching_honor` WRITE;
/*!40000 ALTER TABLE `tb_teaching_honor` DISABLE KEYS */;
INSERT INTO `tb_teaching_honor` VALUES (4,'2011-2012科研先进工作者','梧州学院','2013-08-01 00:00:00',3,5,'2020-09-25 05:07:12','2020-09-25 05:07:12'),(5,'2013-2014科研先进工作者','梧州学院','2015-09-01 00:00:00',3,5,'2020-09-25 05:07:50','2020-09-25 05:07:50');
/*!40000 ALTER TABLE `tb_teaching_honor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_teaching_material`
--

DROP TABLE IF EXISTS `tb_teaching_material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_teaching_material` (
  `material_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '教材名称',
  `press` varchar(255) DEFAULT NULL COMMENT '出版社',
  `publish_time` varchar(255) DEFAULT NULL COMMENT '出版时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`material_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='教材表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_teaching_material`
--

LOCK TABLES `tb_teaching_material` WRITE;
/*!40000 ALTER TABLE `tb_teaching_material` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_teaching_material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user`
--

DROP TABLE IF EXISTS `tb_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `mobile` varchar(20) NOT NULL COMMENT '手机号',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user`
--

LOCK TABLES `tb_user` WRITE;
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` VALUES (1,'mark','13612345678','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918','2017-03-23 22:37:41');
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-30 19:08:28
