CREATE DATABASE  IF NOT EXISTS `serpics` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `serpics`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: serpics
-- ------------------------------------------------------
-- Server version	5.7.9-log

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
-- Table structure for table `Bundle`
--

DROP TABLE IF EXISTS `Bundle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Bundle` (
  `ctentry_id` bigint(20) NOT NULL,
  PRIMARY KEY (`ctentry_id`),
  KEY `FK77441CA241AE1AC4` (`ctentry_id`),
  CONSTRAINT `FK77441CA241AE1AC4` FOREIGN KEY (`ctentry_id`) REFERENCES `abstractProducts` (`ctentry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Bundle`
--

LOCK TABLES `Bundle` WRITE;
/*!40000 ALTER TABLE `Bundle` DISABLE KEYS */;
/*!40000 ALTER TABLE `Bundle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Catalog2StoreRelation`
--

DROP TABLE IF EXISTS `Catalog2StoreRelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Catalog2StoreRelation` (
  `catalog_id` bigint(20) NOT NULL,
  `store_id` bigint(20) NOT NULL,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `selected` tinyint(1) NOT NULL,
  PRIMARY KEY (`catalog_id`,`store_id`),
  KEY `FK61FD87241A37F5F8` (`catalog_id`),
  KEY `FK61FD87245CF8B8DC` (`store_id`),
  CONSTRAINT `FK61FD87241A37F5F8` FOREIGN KEY (`catalog_id`) REFERENCES `catalog` (`catalog_id`),
  CONSTRAINT `FK61FD87245CF8B8DC` FOREIGN KEY (`store_id`) REFERENCES `stores` (`store_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Catalog2StoreRelation`
--

LOCK TABLES `Catalog2StoreRelation` WRITE;
/*!40000 ALTER TABLE `Catalog2StoreRelation` DISABLE KEYS */;
INSERT INTO `Catalog2StoreRelation` (`catalog_id`, `store_id`, `created`, `updated`, `uuid`, `selected`) VALUES (1,1,'2016-01-12 12:32:52','2016-01-12 12:32:52','14fcf5bf-3eaf-4959-8252-1f7024a99054',1);
/*!40000 ALTER TABLE `Catalog2StoreRelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CategoryProductRelation`
--

DROP TABLE IF EXISTS `CategoryProductRelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CategoryProductRelation` (
  `ctentry_id_child` bigint(20) NOT NULL,
  `ctentry_id_parent` bigint(20) NOT NULL,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `field1` varchar(255) DEFAULT NULL,
  `fiedl2` double DEFAULT NULL,
  `relation_type` int(11) NOT NULL,
  `sequence` double NOT NULL,
  PRIMARY KEY (`ctentry_id_child`,`ctentry_id_parent`),
  KEY `FKAC7B430D239454A3` (`ctentry_id_child`),
  KEY `FKAC7B430DA39D8610` (`ctentry_id_parent`),
  CONSTRAINT `FKAC7B430D239454A3` FOREIGN KEY (`ctentry_id_child`) REFERENCES `abstractProducts` (`ctentry_id`),
  CONSTRAINT `FKAC7B430DA39D8610` FOREIGN KEY (`ctentry_id_parent`) REFERENCES `category` (`ctentry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CategoryProductRelation`
--

LOCK TABLES `CategoryProductRelation` WRITE;
/*!40000 ALTER TABLE `CategoryProductRelation` DISABLE KEYS */;
INSERT INTO `CategoryProductRelation` (`ctentry_id_child`, `ctentry_id_parent`, `created`, `updated`, `uuid`, `field1`, `fiedl2`, `relation_type`, `sequence`) VALUES (3,2,'2016-01-12 15:16:53','2016-01-12 15:16:53','12c99633-816c-4f23-b1c7-0b91cf14573e',NULL,NULL,1,0);
/*!40000 ALTER TABLE `CategoryProductRelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CategoryRelation`
--

DROP TABLE IF EXISTS `CategoryRelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CategoryRelation` (
  `ctentry_id_child` bigint(20) NOT NULL,
  `ctentry_id_parent` bigint(20) NOT NULL,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `field1` varchar(255) DEFAULT NULL,
  `fiedl2` double DEFAULT NULL,
  `relation_type` int(11) NOT NULL,
  `sequence` double NOT NULL,
  PRIMARY KEY (`ctentry_id_child`,`ctentry_id_parent`),
  KEY `FKEEFF813A281FD2B6` (`ctentry_id_child`),
  KEY `FKEEFF813AA39D8610` (`ctentry_id_parent`),
  CONSTRAINT `FKEEFF813A281FD2B6` FOREIGN KEY (`ctentry_id_child`) REFERENCES `category` (`ctentry_id`),
  CONSTRAINT `FKEEFF813AA39D8610` FOREIGN KEY (`ctentry_id_parent`) REFERENCES `category` (`ctentry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CategoryRelation`
--

LOCK TABLES `CategoryRelation` WRITE;
/*!40000 ALTER TABLE `CategoryRelation` DISABLE KEYS */;
INSERT INTO `CategoryRelation` (`ctentry_id_child`, `ctentry_id_parent`, `created`, `updated`, `uuid`, `field1`, `fiedl2`, `relation_type`, `sequence`) VALUES (2,1,'2016-01-12 15:14:50','2016-01-12 15:14:50','f10ebfcf-0321-4600-b7cb-200a03684477',NULL,NULL,0,0);
/*!40000 ALTER TABLE `CategoryRelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FearureModelRelation`
--

DROP TABLE IF EXISTS `FearureModelRelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FearureModelRelation` (
  `ctentry_id_child` bigint(20) NOT NULL,
  `ctentry_id_parent` bigint(20) NOT NULL,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `field1` varchar(255) DEFAULT NULL,
  `fiedl2` double DEFAULT NULL,
  `relation_type` int(11) NOT NULL,
  `sequence` double NOT NULL,
  PRIMARY KEY (`ctentry_id_child`,`ctentry_id_parent`),
  KEY `FK26B14D0D1AA6CE8B` (`ctentry_id_child`),
  KEY `FK26B14D0D962481E5` (`ctentry_id_parent`),
  CONSTRAINT `FK26B14D0D1AA6CE8B` FOREIGN KEY (`ctentry_id_child`) REFERENCES `FeatureModel` (`specification_id`),
  CONSTRAINT `FK26B14D0D962481E5` FOREIGN KEY (`ctentry_id_parent`) REFERENCES `FeatureModel` (`specification_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FearureModelRelation`
--

LOCK TABLES `FearureModelRelation` WRITE;
/*!40000 ALTER TABLE `FearureModelRelation` DISABLE KEYS */;
/*!40000 ALTER TABLE `FearureModelRelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Feature`
--

DROP TABLE IF EXISTS `Feature`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Feature` (
  `feature_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sequence` double DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `catalog_id` bigint(20) NOT NULL,
  `description_stringid` bigint(20) DEFAULT NULL,
  `featureGroup_id` bigint(20) NOT NULL,
  `featureModel_id` bigint(20) NOT NULL,
  PRIMARY KEY (`feature_id`),
  KEY `FK28DB12D61A37F5F8` (`catalog_id`),
  KEY `FK28DB12D6D37DBE0F` (`description_stringid`),
  KEY `FK28DB12D693AE04DC` (`featureGroup_id`),
  KEY `FK28DB12D650ECCC9C` (`featureModel_id`),
  CONSTRAINT `FK28DB12D61A37F5F8` FOREIGN KEY (`catalog_id`) REFERENCES `catalog` (`catalog_id`),
  CONSTRAINT `FK28DB12D650ECCC9C` FOREIGN KEY (`featureModel_id`) REFERENCES `FeatureModel` (`specification_id`),
  CONSTRAINT `FK28DB12D693AE04DC` FOREIGN KEY (`featureGroup_id`) REFERENCES `FeatureGroup` (`id`),
  CONSTRAINT `FK28DB12D6D37DBE0F` FOREIGN KEY (`description_stringid`) REFERENCES `multilingual_string` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Feature`
--

LOCK TABLES `Feature` WRITE;
/*!40000 ALTER TABLE `Feature` DISABLE KEYS */;
/*!40000 ALTER TABLE `Feature` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FeatureGroup`
--

DROP TABLE IF EXISTS `FeatureGroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FeatureGroup` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sequence` double NOT NULL,
  `catalog_id` bigint(20) NOT NULL,
  `description_string_id` bigint(20) DEFAULT NULL,
  `model_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK508B49491A37F5F8` (`catalog_id`),
  KEY `FK508B4949F30827C6` (`description_string_id`),
  KEY `FK508B494936A1A7A6` (`model_id`),
  CONSTRAINT `FK508B49491A37F5F8` FOREIGN KEY (`catalog_id`) REFERENCES `catalog` (`catalog_id`),
  CONSTRAINT `FK508B494936A1A7A6` FOREIGN KEY (`model_id`) REFERENCES `FeatureModel` (`specification_id`),
  CONSTRAINT `FK508B4949F30827C6` FOREIGN KEY (`description_string_id`) REFERENCES `multilingual_string` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FeatureGroup`
--

LOCK TABLES `FeatureGroup` WRITE;
/*!40000 ALTER TABLE `FeatureGroup` DISABLE KEYS */;
/*!40000 ALTER TABLE `FeatureGroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FeatureModel`
--

DROP TABLE IF EXISTS `FeatureModel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FeatureModel` (
  `specification_id` bigint(20) NOT NULL,
  `catalog_id` bigint(20) NOT NULL,
  PRIMARY KEY (`specification_id`),
  KEY `FK50DE4DF31A37F5F8` (`catalog_id`),
  KEY `FK50DE4DF3C70A2F56` (`specification_id`),
  CONSTRAINT `FK50DE4DF31A37F5F8` FOREIGN KEY (`catalog_id`) REFERENCES `catalog` (`catalog_id`),
  CONSTRAINT `FK50DE4DF3C70A2F56` FOREIGN KEY (`specification_id`) REFERENCES `ctentry` (`ctentry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FeatureModel`
--

LOCK TABLES `FeatureModel` WRITE;
/*!40000 ALTER TABLE `FeatureModel` DISABLE KEYS */;
/*!40000 ALTER TABLE `FeatureModel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FeatureValues`
--

DROP TABLE IF EXISTS `FeatureValues`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FeatureValues` (
  `featurevalue_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `attributeType` int(11) DEFAULT NULL,
  `dateValue` date DEFAULT NULL,
  `doubleValue` double DEFAULT NULL,
  `integerValue` int(11) DEFAULT NULL,
  `stringValue` varchar(255) DEFAULT NULL,
  `catalog_id` bigint(20) NOT NULL,
  `feature_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  PRIMARY KEY (`featurevalue_id`),
  KEY `FKD985A3981A37F5F8` (`catalog_id`),
  KEY `FKD985A398338E2CD8` (`feature_id`),
  KEY `FKD985A398CCF1BE38` (`product_id`),
  CONSTRAINT `FKD985A3981A37F5F8` FOREIGN KEY (`catalog_id`) REFERENCES `catalog` (`catalog_id`),
  CONSTRAINT `FKD985A398338E2CD8` FOREIGN KEY (`feature_id`) REFERENCES `Feature` (`feature_id`),
  CONSTRAINT `FKD985A398CCF1BE38` FOREIGN KEY (`product_id`) REFERENCES `abstractProducts` (`ctentry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FeatureValues`
--

LOCK TABLES `FeatureValues` WRITE;
/*!40000 ALTER TABLE `FeatureValues` DISABLE KEYS */;
/*!40000 ALTER TABLE `FeatureValues` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MediaSupport`
--

DROP TABLE IF EXISTS `MediaSupport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MediaSupport` (
  `mediasupport_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `format` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `src` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `media_id` bigint(20) NOT NULL,
  PRIMARY KEY (`mediasupport_id`),
  UNIQUE KEY `UK_3206c2c871f34d86adddd54caba` (`media_id`,`format`),
  UNIQUE KEY `UK_68efcb821321429c950be503ab0` (`media_id`,`format`),
  UNIQUE KEY `UK_91164ef47c6b4fd3b2e78bd03f3` (`media_id`,`format`),
  UNIQUE KEY `UK_c5270a4c2d9348dbb327bac0746` (`media_id`,`format`),
  UNIQUE KEY `UK_e58b1d3ae5ac40a881f699050b2` (`media_id`,`format`),
  UNIQUE KEY `UK_279c2954c66d4974babbb4c1286` (`media_id`,`format`),
  UNIQUE KEY `UK_3c46b3782b624811846a9419e96` (`media_id`,`format`),
  UNIQUE KEY `UK_205de92f4d754aacb6369bb6e90` (`media_id`,`format`),
  UNIQUE KEY `UK_6ecef13731b5488ba2dae927ade` (`media_id`,`format`),
  UNIQUE KEY `UK_76a36888eb64451eaff1ab82d5e` (`media_id`,`format`),
  UNIQUE KEY `UK_29544a17be72446b85666c834c1` (`media_id`,`format`),
  UNIQUE KEY `UK_87e15c3233be4f25bf1ba8ca9f8` (`media_id`,`format`),
  UNIQUE KEY `UK_5d574461270f4f8d871ad74e44e` (`media_id`,`format`),
  UNIQUE KEY `UK_7e161ad7cde64611814eb266534` (`media_id`,`format`),
  UNIQUE KEY `UK_3b8baf74e3af4b7794afbdbcfb3` (`media_id`,`format`),
  UNIQUE KEY `UK_823f9f01f0524796aea9d92ab19` (`media_id`,`format`),
  UNIQUE KEY `UK_46d7c87ba6284d9b87eb1cdfb36` (`media_id`,`format`),
  UNIQUE KEY `UK_039ab6b07d3d4d30af3ef41aee1` (`media_id`,`format`),
  UNIQUE KEY `UK_18a0022150ff46538c9e2e24e73` (`media_id`,`format`),
  UNIQUE KEY `UK_9c8686ee0a0344c29716f7b2ce4` (`media_id`,`format`),
  UNIQUE KEY `UK_733e2fc67c9d47168e24edb48e5` (`media_id`,`format`),
  UNIQUE KEY `UK_650f3c54f0d147fa92a7a4ba5a9` (`media_id`,`format`),
  UNIQUE KEY `UK_1a662f5bb90f4687aecaf521daa` (`media_id`,`format`),
  UNIQUE KEY `UK_67eed80b95324c498c45ba2ff73` (`media_id`,`format`),
  UNIQUE KEY `UK_ac5f437911e74b948266600648d` (`media_id`,`format`),
  UNIQUE KEY `UK_912083e6b71b409884b07443e84` (`media_id`,`format`),
  UNIQUE KEY `UK_83494e7090754be59525347119c` (`media_id`,`format`),
  UNIQUE KEY `UK_6f13235acc514ce68b163018fc6` (`media_id`,`format`),
  UNIQUE KEY `UK_d89875f63a9c4dc1a52aadf1a16` (`media_id`,`format`),
  UNIQUE KEY `UK_5c0acc99e7d14693b53bb3938a2` (`media_id`,`format`),
  UNIQUE KEY `UK_01b9dce41c1f43c8b504783c112` (`media_id`,`format`),
  UNIQUE KEY `UK_a78cd12baf3f41eb9cbe5141bfe` (`media_id`,`format`),
  UNIQUE KEY `UK_b199dba3d7e94ffbbff6387770b` (`media_id`,`format`),
  UNIQUE KEY `UK_fccdc3b3ae68409fb104eff6244` (`media_id`,`format`),
  UNIQUE KEY `UK_3e96ffbf5d0042e485d0edaab35` (`media_id`,`format`),
  UNIQUE KEY `UK_7fb6983538444d81adc4edbb1d1` (`media_id`,`format`),
  UNIQUE KEY `UK_2dd6afd2d70d4e81ac4e2482f8a` (`media_id`,`format`),
  UNIQUE KEY `UK_33d18cca7cf240a0816f1857a9b` (`media_id`,`format`),
  UNIQUE KEY `UK_18fa53d8943d4c1dbbc9188c33a` (`media_id`,`format`),
  UNIQUE KEY `UK_d132988082124f76b7ff595c3b4` (`media_id`,`format`),
  UNIQUE KEY `UK_4ec505d665714777a6197277c04` (`media_id`,`format`),
  UNIQUE KEY `UK_bdd632a272904f558b4989f39d7` (`media_id`,`format`),
  UNIQUE KEY `UK_9dac2aede0f14c7eaecc516d13c` (`media_id`,`format`),
  UNIQUE KEY `UK_609ceb0d03584ff18468ccaf523` (`media_id`,`format`),
  UNIQUE KEY `UK_3518f7226db4407eaaa450b54f3` (`media_id`,`format`),
  UNIQUE KEY `UK_28cd870ff4d5431492487f28cad` (`media_id`,`format`),
  UNIQUE KEY `UK_f8c0b3a5cb3f4e3dadf58c63daa` (`media_id`,`format`),
  UNIQUE KEY `UK_bb6dd29cb2324b468d6febf6215` (`media_id`,`format`),
  UNIQUE KEY `UK_bb60bc7c492f40e8947ba3a6d5d` (`media_id`,`format`),
  UNIQUE KEY `UK_33afa50f217c4bd8a4cc1c0cdd5` (`media_id`,`format`),
  UNIQUE KEY `UK_b337cebeec3b416a906596ee14d` (`media_id`,`format`),
  UNIQUE KEY `UK_bd0a6cc217b44a578f8a8b7750d` (`media_id`,`format`),
  UNIQUE KEY `UK_c0c0777dfa7c4ecf8357480af19` (`media_id`,`format`),
  UNIQUE KEY `UK_781728c9ab5442c6bbd55dc4d90` (`media_id`,`format`),
  UNIQUE KEY `UK_8cc0afdc99d44458b29d53ae202` (`media_id`,`format`),
  UNIQUE KEY `UK_f5e7e17164684b10b932a6bbdc1` (`media_id`,`format`),
  UNIQUE KEY `UK_518f93465add4c6283b5829ff82` (`media_id`,`format`),
  UNIQUE KEY `UK_fc4c4fdb103b4e7e93fcc111f8e` (`media_id`,`format`),
  UNIQUE KEY `UK_6bca81a345694f0eadb0dddffe4` (`media_id`,`format`),
  UNIQUE KEY `UK_ad209998217148599ccc4cbc93a` (`media_id`,`format`),
  UNIQUE KEY `UK_4a9f114008134186906666f2c18` (`media_id`,`format`),
  UNIQUE KEY `UK_e1052a7049094da382a5a4c0cc5` (`media_id`,`format`),
  KEY `FK9C68136BC092B5FC` (`media_id`),
  CONSTRAINT `FK9C68136BC092B5FC` FOREIGN KEY (`media_id`) REFERENCES `media` (`media_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MediaSupport`
--

LOCK TABLES `MediaSupport` WRITE;
/*!40000 ALTER TABLE `MediaSupport` DISABLE KEYS */;
/*!40000 ALTER TABLE `MediaSupport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Product`
--

DROP TABLE IF EXISTS `Product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Product` (
  `ctentry_id` bigint(20) NOT NULL,
  PRIMARY KEY (`ctentry_id`),
  KEY `FK50C664CF41AE1AC4` (`ctentry_id`),
  CONSTRAINT `FK50C664CF41AE1AC4` FOREIGN KEY (`ctentry_id`) REFERENCES `abstractProducts` (`ctentry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Product`
--

LOCK TABLES `Product` WRITE;
/*!40000 ALTER TABLE `Product` DISABLE KEYS */;
INSERT INTO `Product` (`ctentry_id`) VALUES (5),(6),(7),(8),(772),(773),(774),(775),(776),(777),(778),(779),(781);
/*!40000 ALTER TABLE `Product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `abstractProducts`
--

DROP TABLE IF EXISTS `abstractProducts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `abstractProducts` (
  `buyable` tinyint(1) NOT NULL,
  `downlodable` tinyint(1) NOT NULL,
  `manufacturer_sku` varchar(255) DEFAULT NULL,
  `meta_description` varchar(255) DEFAULT NULL,
  `meta_keyword` varchar(255) DEFAULT NULL,
  `product_type` int(11) NOT NULL,
  `published` tinyint(1) NOT NULL,
  `unit_meas` varchar(255) DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `weight_meas` varchar(255) DEFAULT NULL,
  `ctentry_id` bigint(20) NOT NULL,
  `brand_id` bigint(20) DEFAULT NULL,
  `featuremodel_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ctentry_id`),
  KEY `FKAF76E3A665B636B8` (`brand_id`),
  KEY `FKAF76E3A650ECCC9C` (`featuremodel_id`),
  KEY `FKAF76E3A6E24388F8` (`ctentry_id`),
  CONSTRAINT `FKAF76E3A650ECCC9C` FOREIGN KEY (`featuremodel_id`) REFERENCES `FeatureModel` (`specification_id`),
  CONSTRAINT `FKAF76E3A665B636B8` FOREIGN KEY (`brand_id`) REFERENCES `brands` (`ctentry_id`),
  CONSTRAINT `FKAF76E3A6E24388F8` FOREIGN KEY (`ctentry_id`) REFERENCES `ctentry` (`ctentry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `abstractProducts`
--

LOCK TABLES `abstractProducts` WRITE;
/*!40000 ALTER TABLE `abstractProducts` DISABLE KEYS */;
INSERT INTO `abstractProducts` (`buyable`, `downlodable`, `manufacturer_sku`, `meta_description`, `meta_keyword`, `product_type`, `published`, `unit_meas`, `weight`, `weight_meas`, `ctentry_id`, `brand_id`, `featuremodel_id`) VALUES (1,0,NULL,NULL,NULL,2,1,NULL,NULL,NULL,3,35,NULL),(1,0,NULL,NULL,NULL,2,1,NULL,NULL,NULL,4,NULL,NULL),(1,0,NULL,NULL,NULL,2,1,NULL,NULL,NULL,5,NULL,NULL),(1,0,NULL,NULL,NULL,2,1,NULL,NULL,NULL,6,NULL,NULL),(1,0,NULL,NULL,NULL,2,1,NULL,NULL,NULL,7,NULL,NULL),(1,0,NULL,NULL,NULL,2,1,NULL,NULL,NULL,8,NULL,NULL),(1,0,NULL,NULL,NULL,2,1,NULL,NULL,NULL,9,NULL,NULL),(1,0,NULL,NULL,NULL,2,1,NULL,NULL,NULL,10,NULL,NULL),(1,0,NULL,NULL,NULL,2,1,NULL,NULL,NULL,11,NULL,NULL),(1,0,NULL,NULL,NULL,2,1,NULL,NULL,NULL,12,NULL,NULL),(1,0,NULL,NULL,NULL,2,1,NULL,NULL,NULL,13,NULL,NULL),(1,0,NULL,NULL,NULL,2,1,NULL,NULL,NULL,14,NULL,NULL),(1,0,NULL,NULL,NULL,2,1,NULL,NULL,NULL,15,NULL,NULL),(1,0,NULL,NULL,NULL,2,1,NULL,NULL,NULL,16,NULL,NULL),(1,0,NULL,NULL,NULL,2,1,NULL,NULL,NULL,17,NULL,NULL),(1,0,NULL,NULL,NULL,2,1,NULL,NULL,NULL,18,NULL,NULL),(1,0,NULL,NULL,NULL,2,1,NULL,NULL,NULL,19,NULL,NULL),(1,0,NULL,NULL,NULL,2,1,NULL,NULL,NULL,20,NULL,NULL),(1,0,NULL,NULL,NULL,2,1,NULL,NULL,NULL,21,NULL,NULL),(1,0,NULL,NULL,NULL,2,1,NULL,NULL,NULL,22,NULL,NULL),(1,0,NULL,NULL,NULL,2,1,NULL,NULL,NULL,23,NULL,NULL),(1,0,NULL,NULL,NULL,2,1,NULL,NULL,NULL,24,NULL,NULL),(1,0,NULL,NULL,NULL,2,1,NULL,NULL,NULL,25,NULL,NULL),(1,0,NULL,NULL,NULL,2,1,NULL,NULL,NULL,26,NULL,NULL),(1,0,NULL,NULL,NULL,2,1,NULL,NULL,NULL,27,NULL,NULL),(1,0,NULL,NULL,NULL,2,1,NULL,NULL,NULL,28,NULL,NULL),(1,0,NULL,NULL,NULL,2,1,NULL,NULL,NULL,29,NULL,NULL),(1,0,NULL,NULL,NULL,2,1,NULL,NULL,NULL,30,NULL,NULL),(1,0,NULL,NULL,NULL,2,1,NULL,NULL,NULL,31,NULL,NULL),(1,0,NULL,NULL,NULL,2,1,NULL,NULL,NULL,32,NULL,NULL),(1,0,NULL,NULL,NULL,2,1,NULL,NULL,NULL,33,NULL,NULL),(1,0,NULL,NULL,NULL,2,1,NULL,NULL,NULL,34,NULL,NULL);
/*!40000 ALTER TABLE `abstractProducts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `addresses`
--

DROP TABLE IF EXISTS `addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `addresses` (
  `flag` varchar(31) NOT NULL,
  `address_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `address1` longtext,
  `address2` longtext,
  `address3` longtext,
  `city` varchar(200) DEFAULT NULL,
  `company` varchar(80) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  `fax` varchar(25) DEFAULT NULL,
  `field1` varchar(250) DEFAULT NULL,
  `field2` varchar(254) DEFAULT NULL,
  `field3` float DEFAULT NULL,
  `field4` bigint(20) DEFAULT NULL,
  `firstname` varchar(50) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `mobile` varchar(25) DEFAULT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  `phone` varchar(25) DEFAULT NULL,
  `streetNumber` varchar(10) DEFAULT NULL,
  `vatcode` varchar(30) DEFAULT NULL,
  `zipcode` varchar(20) DEFAULT NULL,
  `country_id` bigint(20) DEFAULT NULL,
  `region_id` bigint(20) DEFAULT NULL,
  `member_id` bigint(20) NOT NULL,
  PRIMARY KEY (`address_id`),
  KEY `FK34207BA23781983C` (`country_id`),
  KEY `FK34207BA2DF2276B8` (`region_id`),
  KEY `FK34207BA27B32A13D` (`member_id`),
  CONSTRAINT `FK34207BA23781983C` FOREIGN KEY (`country_id`) REFERENCES `countries` (`countries_id`),
  CONSTRAINT `FK34207BA27B32A13D` FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`),
  CONSTRAINT `FK34207BA2DF2276B8` FOREIGN KEY (`region_id`) REFERENCES `regions` (`regions_id`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `addresses`
--

LOCK TABLES `addresses` WRITE;
/*!40000 ALTER TABLE `addresses` DISABLE KEYS */;
INSERT INTO `addresses` (`flag`, `address_id`, `created`, `updated`, `uuid`, `address1`, `address2`, `address3`, `city`, `company`, `email`, `fax`, `field1`, `field2`, `field3`, `field4`, `firstname`, `lastname`, `mobile`, `nickname`, `phone`, `streetNumber`, `vatcode`, `zipcode`, `country_id`, `region_id`, `member_id`) VALUES ('PRIMARY',1,'2016-01-12 12:32:51','2016-01-12 12:32:51','d9ded291-df65-4387-bce4-fa853032cec1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2),('TEMPORARY',2,'2016-01-14 17:22:59','2016-01-14 17:22:59','214da7b9-d11d-4799-9cb2-63ee570d721c','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','6',NULL,'26025',NULL,NULL,1),('TEMPORARY',3,'2016-01-14 17:23:00','2016-01-14 17:23:00','6a3587b7-e4f2-4e7c-a431-b0fe442ee118','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','6',NULL,'26025',NULL,NULL,1),('TEMPORARY',4,'2016-01-15 10:11:03','2016-01-15 10:11:03','1cd1e3c4-125b-4ab5-8a1f-00de6e5b4d6e','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','6',NULL,'26025',NULL,NULL,1),('TEMPORARY',5,'2016-01-15 10:11:04','2016-01-15 10:11:04','39bfcfa1-329d-4591-ab8d-30a7dd93eb89','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','6',NULL,'26025',NULL,NULL,1),('TEMPORARY',6,'2016-01-15 11:22:11','2016-01-15 11:22:11','b82770ab-b4f9-452e-ae90-f56cb1055784','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','6',NULL,'26025',NULL,NULL,1),('TEMPORARY',7,'2016-01-15 11:22:11','2016-01-15 11:22:11','213aff9b-8565-485e-9039-9608c407f165','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','6',NULL,'26025',NULL,NULL,1),('TEMPORARY',8,'2016-01-15 11:26:18','2016-01-15 11:26:18','b9c6338c-d35d-4b6d-90ec-e285cb753fb2','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','10',NULL,'26025',NULL,NULL,1),('TEMPORARY',9,'2016-01-15 11:26:18','2016-01-15 11:26:18','1f61392c-fa0b-4b52-8d0c-6c1f6849ea2f','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','11',NULL,'26025',NULL,NULL,1),('TEMPORARY',10,'2016-01-15 11:26:37','2016-01-15 11:26:37','d5b200f8-6854-4c64-875c-dc35bfee3c49','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','10',NULL,'26025',NULL,NULL,1),('TEMPORARY',11,'2016-01-15 11:26:37','2016-01-15 11:26:37','7c25eb37-ad23-4c76-8144-37a325a769ef','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','11',NULL,'26025',NULL,NULL,1),('TEMPORARY',12,'2016-01-15 11:28:26','2016-01-15 11:28:26','f7eecd4e-6624-4ca2-8b42-84a62e666c5b','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','10',NULL,'26025',NULL,NULL,1),('TEMPORARY',13,'2016-01-15 11:28:27','2016-01-15 11:28:27','8db7355b-2dd4-4cec-8b01-81456cf4386f','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','11',NULL,'26025',NULL,NULL,1),('TEMPORARY',14,'2016-01-15 11:28:34','2016-01-15 11:28:34','3fdbc175-b910-45c1-b559-7b93ed29561c','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','10',NULL,'26025',NULL,NULL,1),('TEMPORARY',15,'2016-01-15 11:28:34','2016-01-15 11:28:34','1f05a153-e7dd-4454-b9bf-fda126850277','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','11',NULL,'26025',NULL,NULL,1),('TEMPORARY',16,'2016-01-15 11:28:47','2016-01-15 11:28:47','1401c709-b3ce-4755-918d-230545911a0f','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','10',NULL,'26025',NULL,NULL,1),('TEMPORARY',17,'2016-01-15 11:28:47','2016-01-15 11:28:47','4324c1e7-2dca-4ad3-a83a-7f66388a35f9','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','11',NULL,'26025',NULL,NULL,1),('TEMPORARY',18,'2016-01-15 11:29:00','2016-01-15 11:29:00','fa618a02-93a6-467c-bca2-1f2bcaf2e6ec','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','10',NULL,'26025',NULL,NULL,1),('TEMPORARY',19,'2016-01-15 11:29:00','2016-01-15 11:29:00','3d345a7c-0233-4264-a7f3-b969b0be4f2c','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','11',NULL,'26025',NULL,NULL,1),('TEMPORARY',20,'2016-01-15 11:29:19','2016-01-15 11:29:19','f38cb5e5-51d5-46c4-8222-e70f6df40004','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','10',NULL,'26025',NULL,NULL,1),('TEMPORARY',21,'2016-01-15 11:29:19','2016-01-15 11:29:19','3583b692-6dd5-497f-aded-abfd7012e3dc','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','11',NULL,'26025',NULL,NULL,1),('TEMPORARY',22,'2016-01-15 11:29:41','2016-01-15 11:29:41','bb986723-14e0-4c1f-9900-66f5973dac6b','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','10',NULL,'26025',NULL,NULL,1),('TEMPORARY',23,'2016-01-15 11:29:41','2016-01-15 11:29:41','0db723fd-520d-4519-ad53-24e339595f24','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','11',NULL,'26025',NULL,NULL,1),('TEMPORARY',24,'2016-01-15 11:29:57','2016-01-15 11:29:57','7ec2ec35-152f-4c09-a610-5bf67eabd97a','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','10',NULL,'26025',NULL,NULL,1),('TEMPORARY',25,'2016-01-15 11:29:57','2016-01-15 11:29:57','671646b3-f7c4-4ffb-adcc-a1025f983a66','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','11',NULL,'26025',NULL,NULL,1),('TEMPORARY',26,'2016-01-15 11:39:53','2016-01-15 11:39:53','b23f3639-b6bb-41a4-b8bd-b403fb502499','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','10',NULL,'26025',NULL,NULL,1),('TEMPORARY',27,'2016-01-15 11:39:53','2016-01-15 11:39:53','f8b55a97-d0d1-47f2-950d-4b3dbb9dcb5d','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','11',NULL,'26025',NULL,NULL,1),('TEMPORARY',28,'2016-01-15 11:40:43','2016-01-15 11:40:43','aa4c938c-8dcf-4c39-976a-5208ab12c156','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','10',NULL,'26025',NULL,NULL,1),('TEMPORARY',29,'2016-01-15 11:40:43','2016-01-15 11:40:43','9fb405c4-c669-420b-8650-efb2cd4ef60d','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','11',NULL,'26025',NULL,NULL,1),('TEMPORARY',30,'2016-01-15 11:49:56','2016-01-15 11:49:56','cb25b9f4-fe98-461f-943f-44f985e1a209','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','10',NULL,'26025',NULL,NULL,1),('TEMPORARY',31,'2016-01-15 11:49:56','2016-01-15 11:49:56','c635ed82-1cfa-4788-a684-33f7cb205517','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','11',NULL,'26025',NULL,NULL,1),('TEMPORARY',32,'2016-01-15 11:50:22','2016-01-15 11:50:22','64f11516-14a8-41cc-9f2d-9583844cf387','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','10',NULL,'26025',NULL,NULL,1),('TEMPORARY',33,'2016-01-15 11:50:22','2016-01-15 11:50:22','0242dce6-3265-436b-a759-be89efdfcfc1','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','11',NULL,'26025',NULL,NULL,1),('TEMPORARY',34,'2016-01-15 11:52:04','2016-01-15 11:52:04','57b78150-5147-4dea-bdde-6f244eaf5042','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','10',NULL,'26025',NULL,NULL,1),('TEMPORARY',35,'2016-01-15 11:52:04','2016-01-15 11:52:04','de245eb5-815c-401c-9a7b-03ec3bd94a45','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','11',NULL,'26025',NULL,NULL,1),('TEMPORARY',36,'2016-01-15 11:53:21','2016-01-15 11:53:21','69c2ed0c-4323-444f-a00b-b4bb384c7223','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','10',NULL,'26025',NULL,NULL,1),('TEMPORARY',37,'2016-01-15 11:53:21','2016-01-15 11:53:21','03af71fb-eaec-4b09-a5c6-035de09d3efd','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','11',NULL,'26025',NULL,NULL,1),('TEMPORARY',38,'2016-01-15 11:53:55','2016-01-15 11:53:55','33f2e96b-457f-4c2e-855a-dd3ee844aaa7','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','10',NULL,'26025',NULL,NULL,1),('TEMPORARY',39,'2016-01-15 11:53:55','2016-01-15 11:53:55','2576ba9e-38b5-408a-acc9-e19da971fe31','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','11',NULL,'26025',NULL,NULL,1),('TEMPORARY',40,'2016-01-15 12:12:08','2016-01-15 12:12:08','c125b1b1-9930-4668-af39-174358679609','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','10',NULL,'26025',NULL,NULL,1),('TEMPORARY',41,'2016-01-15 12:12:08','2016-01-15 12:12:08','50d3f9ff-3821-40ff-8f9c-3061d297cb5c','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','11',NULL,'26025',NULL,NULL,1),('TEMPORARY',42,'2016-01-15 12:13:31','2016-01-15 12:13:31','022b3e41-83f1-488a-8c7d-d618ff263606','VIA LUIGI PIRANDELLO','1',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','1',NULL,'26025',NULL,NULL,1),('TEMPORARY',43,'2016-01-15 12:13:32','2016-01-15 12:13:32','5b2ed657-c5ca-41d9-b449-3b7a491c2e22','VIA LUIGI PIRANDELLO','1',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','1',NULL,'26025',NULL,NULL,1),('TEMPORARY',44,'2016-01-15 12:18:13','2016-01-15 12:18:13','ab4e49e4-f0fa-4fcf-a87a-c4d7ac151407','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','10',NULL,'26025',NULL,NULL,1),('TEMPORARY',45,'2016-01-15 12:18:13','2016-01-15 12:18:13','7e6720f4-498d-449c-a530-3510486c5209','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','11',NULL,'26025',NULL,NULL,1),('TEMPORARY',46,'2016-01-15 12:24:56','2016-01-15 12:24:56','4b8da84a-ff53-4d8d-83b0-395efeb7764b','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','10',NULL,'26025',NULL,NULL,1),('TEMPORARY',47,'2016-01-15 12:24:56','2016-01-15 12:24:56','e1e85181-940c-4ef7-8d7b-384997eda087','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','11',NULL,'26025',NULL,NULL,1),('PRIMARY',51,'2016-01-15 14:05:09','2016-01-15 14:05:09','d8dec146-1ca4-471e-869e-62f2938f2501',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3),('BILLING',52,'2016-01-15 14:05:29','2016-01-15 14:05:53','53317f66-3593-4dc5-8394-d2d0eb913a3d','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic',NULL,NULL,NULL,'6',NULL,'26025',NULL,NULL,3),('PERMANENT',53,'2016-01-15 14:06:12','2016-01-15 14:06:12','e8dc72da-c895-4e04-aa6a-c768f3037f74','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333',NULL,NULL,'26025',NULL,NULL,3),('TEMPORARY',60,'2016-01-15 15:02:12','2016-01-15 15:02:12','e07cfa54-64fa-4ccf-9658-d29692a894f1','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','10',NULL,'26025',NULL,NULL,1),('TEMPORARY',61,'2016-01-15 15:02:35','2016-01-15 15:02:35','766756e6-16ca-43c8-b0ed-e37c27a3f4bf','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','11',NULL,'26025',NULL,NULL,1),('TEMPORARY',66,'2016-01-15 15:53:03','2016-01-15 15:53:03','eb754b3b-5d5d-4159-8eb4-69da87669ad7','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','10',NULL,'26025',NULL,NULL,1),('TEMPORARY',67,'2016-01-15 15:53:09','2016-01-15 15:53:09','f8ca5f45-6a58-4d1a-b46b-fdef18199e49','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','11',NULL,'26025',NULL,NULL,1),('TEMPORARY',68,'2016-01-15 15:56:49','2016-01-15 15:56:49','c5d8be52-2393-4c63-844a-ab47126bffdf','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','10',NULL,'26025',NULL,NULL,1),('TEMPORARY',69,'2016-01-15 15:57:10','2016-01-15 15:57:10','db3e954b-9ceb-44e0-9507-f9bf6faad77a','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','11',NULL,'26025',NULL,NULL,1),('TEMPORARY',70,'2016-01-15 16:45:18','2016-01-15 16:45:18','dc2298cb-6f58-40df-8191-4d84ec387a29','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','10',NULL,'26025',NULL,NULL,1),('TEMPORARY',71,'2016-01-15 16:45:20','2016-01-15 16:45:20','9de8a515-71b3-4419-a044-761dcf5219a7','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','11',NULL,'26025',NULL,NULL,1),('PERMANENT',72,'2016-01-18 10:48:07','2016-01-18 10:48:07','ca118ed4-3139-4198-a859-2bbe25c44015','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','7',NULL,'26025',NULL,NULL,3),('PERMANENT',73,'2016-01-18 10:48:22','2016-01-18 10:48:22','dcc45078-4f2e-499b-8e54-2468dc9f9f83',NULL,NULL,NULL,'PANDINO',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Fede','Pic',NULL,NULL,NULL,NULL,NULL,'26025',NULL,NULL,3),('PERMANENT',74,'2016-01-18 10:48:28','2016-01-18 10:48:28','18485f4e-e8ab-4ea0-a18a-6ddb58c29dac','VIA LUIGI PIRANDELLO',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Fede',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3),('PERMANENT',75,'2016-01-18 10:48:31','2016-01-18 10:48:31','5c1c04b4-c07b-41c7-be2f-ff7a7fc2c41e',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Pic',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3),('TEMPORARY',76,'2016-01-18 10:48:49','2016-01-18 10:48:49','fa28e461-86b5-4422-8536-8806c790c169','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic',NULL,NULL,NULL,'6',NULL,'26025',NULL,NULL,3),('TEMPORARY',78,'2016-01-20 16:20:46','2016-01-20 16:20:46','201a88e8-3a46-437f-bb4f-748062689e23','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic','333333333',NULL,'333333333','6',NULL,'26025',NULL,NULL,1),('TEMPORARY',79,'2016-01-26 11:32:32','2016-01-26 11:32:32','375ce323-6369-49f9-8dbc-e4a192b85008','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic',NULL,NULL,NULL,'6',NULL,'26025',NULL,NULL,3),('TEMPORARY',80,'2016-01-26 11:32:33','2016-01-26 11:32:33','3e7d3126-53aa-4019-9e10-c6904707a3ab','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic',NULL,NULL,NULL,'6',NULL,'26025',NULL,NULL,3),('TEMPORARY',81,'2016-01-26 11:38:33','2016-01-26 11:38:33','04298967-8e8c-4090-a5e6-24505280a55e','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic',NULL,NULL,NULL,'6',NULL,'26025',NULL,NULL,3),('TEMPORARY',82,'2016-01-26 11:38:33','2016-01-26 11:38:33','f5505960-2e79-446b-93ec-1dffa5994649','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic',NULL,NULL,NULL,'6',NULL,'26025',NULL,NULL,3),('TEMPORARY',83,'2016-01-26 11:39:28','2016-01-26 11:39:28','622593cb-a4cb-4eca-9444-54bf224118e4','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic',NULL,NULL,NULL,'6',NULL,'26025',NULL,NULL,3),('TEMPORARY',84,'2016-01-26 11:39:29','2016-01-26 11:39:29','92814550-1247-4829-859b-dd65ace1072d','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic',NULL,NULL,NULL,'6',NULL,'26025',NULL,NULL,3),('PRIMARY',85,'2016-01-26 16:06:45','2016-01-26 16:06:45','150b668a-4448-4263-8c24-95daf6ef8667',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4),('TEMPORARY',86,'2016-01-26 17:39:29','2016-01-26 17:39:29','64d92609-c243-4834-ac0c-d82bf22479fb','VIA LUIGI PIRANDELLO','6',NULL,NULL,NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),('TEMPORARY',87,'2016-01-26 17:39:30','2016-01-26 17:39:30','7346c5fd-bd47-4a38-bfef-faf3b9fb6a8a','VIA LUIGI PIRANDELLO','6',NULL,NULL,NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),('TEMPORARY',88,'2016-01-27 10:25:58','2016-01-27 10:25:58','5d0b9298-81a5-42f4-8b2d-730d6a76dc33','VIA LUIGI PIRANDELLO','6',NULL,NULL,NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),('TEMPORARY',89,'2016-01-27 10:25:58','2016-01-27 10:25:58','92f2e169-b919-4d2f-a44a-31eeac763fd5','VIA LUIGI PIRANDELLO','6',NULL,NULL,NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),('TEMPORARY',90,'2016-01-27 10:26:44','2016-01-27 10:26:44','007528e1-7f4b-4031-b37f-2bf593995da0','VIA LUIGI PIRANDELLO','6',NULL,NULL,NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),('TEMPORARY',91,'2016-01-27 10:26:45','2016-01-27 10:26:45','e7ba0f58-ca13-4a55-baca-ea21c2370bff','VIA LUIGI PIRANDELLO','6',NULL,NULL,NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede','Pic',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `addresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attribute_lookup`
--

DROP TABLE IF EXISTS `attribute_lookup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attribute_lookup` (
  `base_attributes_id` bigint(20) NOT NULL,
  `store_id` bigint(20) NOT NULL,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `sequence` double NOT NULL,
  PRIMARY KEY (`base_attributes_id`,`store_id`),
  KEY `FK1668147D76C3C7A2` (`base_attributes_id`),
  CONSTRAINT `FK1668147D76C3C7A2` FOREIGN KEY (`base_attributes_id`) REFERENCES `base_attributes` (`base_attributes_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attribute_lookup`
--

LOCK TABLES `attribute_lookup` WRITE;
/*!40000 ALTER TABLE `attribute_lookup` DISABLE KEYS */;
/*!40000 ALTER TABLE `attribute_lookup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `base_attributes`
--

DROP TABLE IF EXISTS `base_attributes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `base_attributes` (
  `base_attributes_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `attribute_type` int(11) NOT NULL,
  `availablefor` varchar(255) NOT NULL,
  `name` varchar(100) NOT NULL,
  `store_id` bigint(20) NOT NULL,
  `description_stringid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`base_attributes_id`),
  KEY `FK82063B85D37DBE0F` (`description_stringid`),
  CONSTRAINT `FK82063B85D37DBE0F` FOREIGN KEY (`description_stringid`) REFERENCES `multilingual_string` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `base_attributes`
--

LOCK TABLES `base_attributes` WRITE;
/*!40000 ALTER TABLE `base_attributes` DISABLE KEYS */;
/*!40000 ALTER TABLE `base_attributes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brands`
--

DROP TABLE IF EXISTS `brands`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `brands` (
  `logo_src` varchar(255) DEFAULT NULL,
  `published` tinyint(1) NOT NULL,
  `ctentry_id` bigint(20) NOT NULL,
  PRIMARY KEY (`ctentry_id`),
  KEY `FKADAF25CCE24388F8` (`ctentry_id`),
  CONSTRAINT `FKADAF25CCE24388F8` FOREIGN KEY (`ctentry_id`) REFERENCES `ctentry` (`ctentry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brands`
--

LOCK TABLES `brands` WRITE;
/*!40000 ALTER TABLE `brands` DISABLE KEYS */;
INSERT INTO `brands` (`logo_src`, `published`, `ctentry_id`) VALUES (NULL,1,35),('Logos4',1,68),('Logos4',0,72);
/*!40000 ALTER TABLE `brands` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `catalog`
--

DROP TABLE IF EXISTS `catalog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `catalog` (
  `catalog_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `published` tinyint(1) NOT NULL,
  `name_string_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`catalog_id`),
  KEY `FK211F6019714A7795` (`name_string_id`),
  CONSTRAINT `FK211F6019714A7795` FOREIGN KEY (`name_string_id`) REFERENCES `multilingual_string` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalog`
--

LOCK TABLES `catalog` WRITE;
/*!40000 ALTER TABLE `catalog` DISABLE KEYS */;
INSERT INTO `catalog` (`catalog_id`, `created`, `updated`, `uuid`, `code`, `published`, `name_string_id`) VALUES (1,'2016-01-12 12:32:52','2016-01-12 12:32:52','3565f860-546b-46aa-bcef-77da88229ec5','default-catalog',1,NULL);
/*!40000 ALTER TABLE `catalog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `meta_description` varchar(255) DEFAULT NULL,
  `meta_keyword` varchar(255) DEFAULT NULL,
  `published` tinyint(1) NOT NULL,
  `ctentry_id` bigint(20) NOT NULL,
  PRIMARY KEY (`ctentry_id`),
  KEY `FK302BCFEE24388F8` (`ctentry_id`),
  CONSTRAINT `FK302BCFEE24388F8` FOREIGN KEY (`ctentry_id`) REFERENCES `ctentry` (`ctentry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` (`meta_description`, `meta_keyword`, `published`, `ctentry_id`) VALUES (NULL,NULL,1,1),(NULL,NULL,1,2),(NULL,NULL,1,36),(NULL,NULL,1,37),(NULL,NULL,1,38),(NULL,NULL,1,39),(NULL,NULL,1,40),(NULL,NULL,1,41),(NULL,NULL,1,42),(NULL,NULL,1,43),(NULL,NULL,1,44),(NULL,NULL,1,45),(NULL,NULL,1,46),(NULL,NULL,1,47),(NULL,NULL,1,48),(NULL,NULL,1,49),(NULL,NULL,1,50),(NULL,NULL,1,51),(NULL,NULL,1,52),(NULL,NULL,1,53),(NULL,NULL,1,54),(NULL,NULL,1,55),(NULL,NULL,1,56),(NULL,NULL,1,57),(NULL,NULL,1,58),(NULL,NULL,1,59),(NULL,NULL,1,60),(NULL,NULL,1,61),(NULL,NULL,1,62),(NULL,NULL,1,63),(NULL,NULL,1,64),(NULL,NULL,1,65),(NULL,NULL,1,66),(NULL,NULL,1,67);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `countries`
--

DROP TABLE IF EXISTS `countries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `countries` (
  `countries_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `iso2_code` varchar(2) NOT NULL,
  `iso3_code` varchar(3) NOT NULL,
  `iso_num_code` int(11) DEFAULT NULL,
  `description_stringid` bigint(20) DEFAULT NULL,
  `geocode_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`countries_id`),
  KEY `FK509F9AB4D37DBE0F` (`description_stringid`),
  KEY `FK509F9AB441A7A73C` (`geocode_id`),
  CONSTRAINT `FK509F9AB441A7A73C` FOREIGN KEY (`geocode_id`) REFERENCES `geocode` (`geocode_id`),
  CONSTRAINT `FK509F9AB4D37DBE0F` FOREIGN KEY (`description_stringid`) REFERENCES `multilingual_string` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `countries`
--

LOCK TABLES `countries` WRITE;
/*!40000 ALTER TABLE `countries` DISABLE KEYS */;
/*!40000 ALTER TABLE `countries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ctentry`
--

DROP TABLE IF EXISTS `ctentry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ctentry` (
  `ctentry_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `code` varchar(250) NOT NULL,
  `ctentry_type` int(11) NOT NULL,
  `field1` varchar(255) DEFAULT NULL,
  `field2` bigint(20) DEFAULT NULL,
  `field3` double DEFAULT NULL,
  `url` varchar(255) NOT NULL,
  `catalog_id` bigint(20) NOT NULL,
  `description_string_id` bigint(20) DEFAULT NULL,
  `name_string_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ctentry_id`),
  UNIQUE KEY `UK_5d72dff077454105971e1a48aad` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_40a3e399fdc24336af67906ac91` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_a5222c72775547f3ad40fb1a098` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_873791b91f05408aa4089527b2a` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_ecadc8c68d4d4db9bc2f1af7cf6` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_d3b6b739921d4aafa34b771f1b7` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_45158bf63b4642a09627ebb7100` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_6d5f24d540a44595b708f5b308f` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_35003ea3a153455fbd375e8b8bf` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_c431b24f25cb47e5bae587254df` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_cbaf246199ee4502af6d7640745` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_595bc25e80284a7e8a93950b695` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_d44d55f4430541f786cb9ab2c79` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_33532294e6b3484a82eb72b9b69` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_026d7597e6f64c2da6823472d44` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_0c63038ed94440ba9178e3209b4` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_3d87d69ad6f54698b74665e05b1` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_3cce9ef4b1e441538b0497f8a55` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_eeded3aeb61045969e24d14eaf8` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_4bbb3ad841734901b6d98cbdcef` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_ee30cf119c6342d99c26643eba3` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_fbbb3c4dda31450383e77da6f08` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_bff3a0ef369d40dcbf98ebee38a` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_e2ce79dd70dc40efa9934dd9c2f` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_9bf0f373dcc149559e0a50034ff` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_e9f0a3d38e934668815ece1664b` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_fdf3f8f9181a4f789d746e3b6b5` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_c6e3e51e26ab48c982d6d582116` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_b5dde97e052940dea91d6391ba9` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_bcac7d9ee4994a33b7097d5c966` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_dfdb030c06cf432383a86ecbbff` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_4c9c6f18c4db4b0a9a90958fa56` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_f09b1f5047c14ea19556dbff608` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_ab7765ad1e6a449e96392ac16cd` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_5bffd44d65e044978988a7954f0` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_c3ac89dd761c4d56891122e9f33` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_3d1631698e5242ee8a6c8f68831` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_58892e45ffe846a68dc84721da4` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_ddc8a0d56ca34955b09c94038ed` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_58ed67fa5f1a498a86c10f05f7d` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_6bd8672bd5184b09b6748d552e1` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_6719905e8a304cb0a633f217dc5` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_729ea1b1036d4fb2b4d7803d720` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_2046b22a7d124a65b7c93f48c84` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_c776488846504c16bebcd0dd2fe` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_0c04802da24b412c8cfa118baba` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_3a3320e4a5f940bda5078c6f44a` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_2499aa7c11e540f3b091f07d793` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_5ea3d530ae554cfe90672ff52d9` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_b366164cc80048ab933014f4050` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_83ec20aee0544da4bbe6a39ff74` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_4cba8673e1694b0e8640d036b2f` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_80785fda10d146b3a455d4e786c` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_26679c1c952946e3bcd7b0c0a48` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_162897de722347469258c72683a` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_f07e057b1e1a4bce93e19a50061` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_57dac26091a4485c836e7524de6` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_08207833e5b24891af936550720` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_ea9db962be2d40958d731e1624d` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_5fcd57a7c93a4ae6a52557399ee` (`catalog_id`,`ctentry_type`,`code`),
  KEY `FK40BE1AA11A37F5F8` (`catalog_id`),
  KEY `FK40BE1AA1F9EB4CA2` (`description_string_id`),
  KEY `FK40BE1AA1714A7795` (`name_string_id`),
  CONSTRAINT `FK40BE1AA11A37F5F8` FOREIGN KEY (`catalog_id`) REFERENCES `catalog` (`catalog_id`),
  CONSTRAINT `FK40BE1AA1714A7795` FOREIGN KEY (`name_string_id`) REFERENCES `multilingual_string` (`Id`),
  CONSTRAINT `FK40BE1AA1F9EB4CA2` FOREIGN KEY (`description_string_id`) REFERENCES `multilingual_text` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ctentry`
--

LOCK TABLES `ctentry` WRITE;
/*!40000 ALTER TABLE `ctentry` DISABLE KEYS */;
INSERT INTO `ctentry` (`ctentry_id`, `created`, `updated`, `uuid`, `code`, `ctentry_type`, `field1`, `field2`, `field3`, `url`, `catalog_id`, `description_string_id`, `name_string_id`) VALUES (1,'2016-01-12 15:13:50','2016-01-12 15:13:50','a5070029-f45f-4e90-b3c9-66bb51956a60','Computer',1,NULL,NULL,NULL,'/default-catalog/c/Computer',1,1,2),(2,'2016-01-12 15:14:30','2016-01-12 15:14:30','22c5b3bc-94e4-4e9a-a1ec-87b0032d977b','Monitor',1,NULL,NULL,NULL,'/default-catalog/c/Monitor',1,2,3),(3,'2016-01-12 15:16:11','2016-01-18 11:53:56','68b70ec8-d60b-4851-9b43-efb47168a5fe','Philips32',3,NULL,NULL,NULL,'/default-catalog/p/Philips32',1,3,4),(4,'2016-01-12 16:11:18','2016-01-12 16:11:18','97d396bc-e074-4e9c-8535-6e2fd1127c98','66666',3,NULL,NULL,NULL,'/default-catalog/p/66666',1,4,5),(5,'2016-01-12 16:11:33','2016-01-12 16:11:33','63a2a8b0-6b57-43ef-a47f-27f4b01fafc7','P10',3,NULL,NULL,NULL,'/default-catalog/p/P10',1,NULL,6),(6,'2016-01-12 16:11:33','2016-01-12 16:11:33','259809c9-3412-48cb-944b-bf6be74c9f1e','P11',3,NULL,NULL,NULL,'/default-catalog/p/P11',1,NULL,7),(7,'2016-01-12 16:11:33','2016-01-12 16:11:33','1a67f37a-b94e-454e-ba17-7026930092af','P12',3,NULL,NULL,NULL,'/default-catalog/p/P12',1,NULL,8),(8,'2016-01-12 16:11:33','2016-01-12 16:11:33','dab34fe7-f083-4f9f-9f15-fa63c42226d3','P13',3,NULL,NULL,NULL,'/default-catalog/p/P13',1,NULL,9),(9,'2016-01-12 16:11:33','2016-01-12 16:11:33','d629cf63-7db6-4e69-8ba3-6a4fd143e047','P14',3,NULL,NULL,NULL,'/default-catalog/p/P14',1,NULL,10),(10,'2016-01-12 16:11:34','2016-01-12 16:11:34','e5db4db4-fdf0-4c46-8eeb-842afc562f79','P15',3,NULL,NULL,NULL,'/default-catalog/p/P15',1,NULL,11),(11,'2016-01-12 16:11:34','2016-01-12 16:11:34','8bae2657-ee50-476b-9834-6180abfbd044','P16',3,NULL,NULL,NULL,'/default-catalog/p/P16',1,NULL,12),(12,'2016-01-12 16:11:34','2016-01-12 16:11:34','212dacf8-17e6-435c-8f98-7f889278b68d','P17',3,NULL,NULL,NULL,'/default-catalog/p/P17',1,NULL,13),(13,'2016-01-12 16:11:34','2016-01-12 16:11:34','08d0ab61-d07f-4da5-aee6-f05a5a62be2e','P18',3,NULL,NULL,NULL,'/default-catalog/p/P18',1,NULL,14),(14,'2016-01-12 16:11:34','2016-01-12 16:11:34','ffa4ee17-e068-4db1-a812-c90b7a9bd04d','P19',3,NULL,NULL,NULL,'/default-catalog/p/P19',1,NULL,15),(15,'2016-01-12 16:11:34','2016-01-12 16:11:34','cdb6468b-dfd8-4a5c-8796-94ab0eaf9a77','P20',3,NULL,NULL,NULL,'/default-catalog/p/P20',1,NULL,16),(16,'2016-01-12 16:11:34','2016-01-12 16:11:34','9016b391-dc11-4eca-9f57-996a1f61e286','P21',3,NULL,NULL,NULL,'/default-catalog/p/P21',1,NULL,17),(17,'2016-01-12 16:11:34','2016-01-12 16:11:34','26554283-84b3-4a0b-a081-7473c7e7f143','P22',3,NULL,NULL,NULL,'/default-catalog/p/P22',1,NULL,18),(18,'2016-01-12 16:11:35','2016-01-12 16:11:35','a18947e0-3601-4778-a893-90875fb3b52c','P23',3,NULL,NULL,NULL,'/default-catalog/p/P23',1,NULL,19),(19,'2016-01-12 16:11:35','2016-01-12 16:11:35','9f146963-d2a8-48d0-a55b-304bcb3be464','P24',3,NULL,NULL,NULL,'/default-catalog/p/P24',1,NULL,20),(20,'2016-01-12 16:11:35','2016-01-12 16:11:35','d4da9a55-17cc-4d39-b945-d57962d921eb','P25',3,NULL,NULL,NULL,'/default-catalog/p/P25',1,NULL,21),(21,'2016-01-12 16:11:35','2016-01-12 16:11:35','4eee4615-2463-469d-8689-0b77dc53da4b','P26',3,NULL,NULL,NULL,'/default-catalog/p/P26',1,NULL,22),(22,'2016-01-12 16:11:35','2016-01-12 16:11:35','ef39b422-dc8f-4aa9-ab58-872f45c74407','P27',3,NULL,NULL,NULL,'/default-catalog/p/P27',1,NULL,23),(23,'2016-01-12 16:11:35','2016-01-12 16:11:35','7e44f3e2-2013-4fc3-a891-9127c6aec5b1','P28',3,NULL,NULL,NULL,'/default-catalog/p/P28',1,NULL,24),(24,'2016-01-12 16:11:35','2016-01-12 16:11:35','c7b40c79-355c-4488-876c-717c665285ad','P29',3,NULL,NULL,NULL,'/default-catalog/p/P29',1,NULL,25),(25,'2016-01-12 16:11:35','2016-01-12 16:11:35','fe3a6e1d-752b-4424-902f-2f272d6ac698','P30',3,NULL,NULL,NULL,'/default-catalog/p/P30',1,NULL,26),(26,'2016-01-12 16:11:36','2016-01-12 16:11:36','1f94bb83-7267-4c4a-9a3f-b6768f787155','P31',3,NULL,NULL,NULL,'/default-catalog/p/P31',1,NULL,27),(27,'2016-01-12 16:11:36','2016-01-12 16:11:36','4f4b8068-b637-486b-a81b-1f9759a1744f','P32',3,NULL,NULL,NULL,'/default-catalog/p/P32',1,NULL,28),(28,'2016-01-12 16:11:36','2016-01-12 16:11:36','93487dbf-5702-462e-8099-c1d2ecafbb55','P33',3,NULL,NULL,NULL,'/default-catalog/p/P33',1,NULL,29),(29,'2016-01-12 16:11:36','2016-01-12 16:11:36','fcf366a0-2458-4a82-a468-ded16d122eb8','P34',3,NULL,NULL,NULL,'/default-catalog/p/P34',1,NULL,30),(30,'2016-01-12 16:11:36','2016-01-12 16:11:36','a0df951b-4054-460f-b6a9-1600b6890cf3','P35',3,NULL,NULL,NULL,'/default-catalog/p/P35',1,NULL,31),(31,'2016-01-12 16:11:36','2016-01-12 16:11:36','4658cd42-a157-41af-81d8-199fa4d2e67c','P36',3,NULL,NULL,NULL,'/default-catalog/p/P36',1,NULL,32),(32,'2016-01-12 16:11:36','2016-01-12 16:11:36','f44e2b6d-97cc-4bcf-85bc-3842e5109162','P37',3,NULL,NULL,NULL,'/default-catalog/p/P37',1,NULL,33),(33,'2016-01-12 16:11:36','2016-01-12 16:11:36','60ad6b75-ddd4-4d7a-bd50-b2417e3f9f6d','P38',3,NULL,NULL,NULL,'/default-catalog/p/P38',1,NULL,34),(34,'2016-01-12 16:11:37','2016-01-12 16:11:37','00f36419-7222-440b-a273-d0f9d266ee02','P39',3,NULL,NULL,NULL,'/default-catalog/p/P39',1,NULL,35),(35,'2016-01-18 11:51:17','2016-01-18 11:51:17','993eadcd-1d84-46a4-8435-2770c146336f','PHILIPS',0,NULL,NULL,NULL,'/default-catalog/b/PHILIPS',1,NULL,NULL),(36,'2016-01-19 15:52:45','2016-01-19 15:52:45','dda2800d-ca80-4963-906f-059667c30330','C1',1,NULL,NULL,NULL,'/default-catalog/c/C1',1,NULL,36),(37,'2016-01-19 15:52:45','2016-01-19 15:52:45','25e394d9-0a50-4910-8780-27319196e757','C2',1,NULL,NULL,NULL,'/default-catalog/c/C2',1,NULL,37),(38,'2016-01-19 15:52:45','2016-01-19 15:52:45','dc4aa7c8-ef0c-44d3-9b38-bee14f1381c2','C10',1,NULL,NULL,NULL,'/default-catalog/c/C10',1,NULL,38),(39,'2016-01-19 15:52:45','2016-01-19 15:52:45','dda4711c-0a2b-4a08-b92c-5a91933e0e6c','C11',1,NULL,NULL,NULL,'/default-catalog/c/C11',1,NULL,39),(40,'2016-01-19 15:52:45','2016-01-19 15:52:45','28fe856e-60bf-446e-8be4-a44eaba8045d','C12',1,NULL,NULL,NULL,'/default-catalog/c/C12',1,NULL,40),(41,'2016-01-19 15:52:46','2016-01-19 15:52:46','11f8c304-d8bf-4017-88fc-4cef9dcb3018','C13',1,NULL,NULL,NULL,'/default-catalog/c/C13',1,NULL,41),(42,'2016-01-19 15:52:46','2016-01-19 15:52:46','6ded75b6-0217-4d84-b0d6-0259f6383e7c','C14',1,NULL,NULL,NULL,'/default-catalog/c/C14',1,NULL,42),(43,'2016-01-19 15:52:46','2016-01-19 15:52:46','d3ae755d-4715-4388-9ba3-104cdcd1343b','C15',1,NULL,NULL,NULL,'/default-catalog/c/C15',1,NULL,43),(44,'2016-01-19 15:52:46','2016-01-19 15:52:46','8a655984-2870-46cf-8077-13559d0c86b0','C16',1,NULL,NULL,NULL,'/default-catalog/c/C16',1,NULL,44),(45,'2016-01-19 15:52:46','2016-01-19 15:52:46','b6b5f00d-0fff-4d77-b51e-16ee14fac797','C17',1,NULL,NULL,NULL,'/default-catalog/c/C17',1,NULL,45),(46,'2016-01-19 15:52:46','2016-01-19 15:52:46','030ba5ee-1d00-4de8-ae4d-b64b63b29ee3','C18',1,NULL,NULL,NULL,'/default-catalog/c/C18',1,NULL,46),(47,'2016-01-19 15:52:46','2016-01-19 15:52:46','269b995d-5e1f-44c9-b083-78fc217c7e5e','C19',1,NULL,NULL,NULL,'/default-catalog/c/C19',1,NULL,47),(48,'2016-01-19 15:52:47','2016-01-19 15:52:47','9fe9f463-24fb-4d24-9b2a-bccb38f1052c','C20',1,NULL,NULL,NULL,'/default-catalog/c/C20',1,NULL,48),(49,'2016-01-19 15:52:47','2016-01-19 15:52:47','898df5d4-939c-4aa4-926b-d6e4f98c3f57','C21',1,NULL,NULL,NULL,'/default-catalog/c/C21',1,NULL,49),(50,'2016-01-19 15:52:47','2016-01-19 15:52:47','56310f44-a3c2-4f34-8246-8c3b5ea85582','C22',1,NULL,NULL,NULL,'/default-catalog/c/C22',1,NULL,50),(51,'2016-01-19 15:52:47','2016-01-19 15:52:47','2ecba46b-5b8a-4b63-b636-20e8a5b87560','C23',1,NULL,NULL,NULL,'/default-catalog/c/C23',1,NULL,51),(52,'2016-01-19 15:52:47','2016-01-19 15:52:47','fa90570b-f73d-4c90-b6e4-c6fc5fddcfff','C24',1,NULL,NULL,NULL,'/default-catalog/c/C24',1,NULL,52),(53,'2016-01-19 15:52:47','2016-01-19 15:52:47','69533a02-030a-459f-9382-3c317e7889bd','C25',1,NULL,NULL,NULL,'/default-catalog/c/C25',1,NULL,53),(54,'2016-01-19 15:52:47','2016-01-19 15:52:47','14117ca5-a780-4eac-b8c8-4c2f9dede9df','C26',1,NULL,NULL,NULL,'/default-catalog/c/C26',1,NULL,54),(55,'2016-01-19 15:52:47','2016-01-19 15:52:47','0bcd3341-3344-45f3-a7f7-c288c32ab967','C27',1,NULL,NULL,NULL,'/default-catalog/c/C27',1,NULL,55),(56,'2016-01-19 15:52:47','2016-01-19 15:52:47','a6460b72-afa5-492a-bb66-d65a193d7a47','C28',1,NULL,NULL,NULL,'/default-catalog/c/C28',1,NULL,56),(57,'2016-01-19 15:52:48','2016-01-19 15:52:48','34a3bc44-a4e5-4018-88d3-9a4831cfc199','C29',1,NULL,NULL,NULL,'/default-catalog/c/C29',1,NULL,57),(58,'2016-01-19 15:52:48','2016-01-19 15:52:48','bff172a3-d62a-45df-85a7-0ff5a079c7a0','C30',1,NULL,NULL,NULL,'/default-catalog/c/C30',1,NULL,58),(59,'2016-01-19 15:52:48','2016-01-19 15:52:48','d45e79a0-31e5-4ec7-8ae4-ea27a92f6c77','C31',1,NULL,NULL,NULL,'/default-catalog/c/C31',1,NULL,59),(60,'2016-01-19 15:52:48','2016-01-19 15:52:48','b8189335-eec1-4644-ab65-0661d8a89b7d','C32',1,NULL,NULL,NULL,'/default-catalog/c/C32',1,NULL,60),(61,'2016-01-19 15:52:48','2016-01-19 15:52:48','f1e53202-1b64-4199-82bf-d784f37894d3','C33',1,NULL,NULL,NULL,'/default-catalog/c/C33',1,NULL,61),(62,'2016-01-19 15:52:48','2016-01-19 15:52:48','031c21f3-7b37-4223-9cf2-d7e527c552b5','C34',1,NULL,NULL,NULL,'/default-catalog/c/C34',1,NULL,62),(63,'2016-01-19 15:52:48','2016-01-19 15:52:48','0d1c9dc2-8cc2-420c-92bf-beaf1c14dbef','C35',1,NULL,NULL,NULL,'/default-catalog/c/C35',1,NULL,63),(64,'2016-01-19 15:52:48','2016-01-19 15:52:48','f2385ffc-ac93-46a4-90ec-55d77272a1ef','C36',1,NULL,NULL,NULL,'/default-catalog/c/C36',1,NULL,64),(65,'2016-01-19 15:52:49','2016-01-19 15:52:49','dbe7b3cc-5f67-43ec-8c74-37a451228730','C37',1,NULL,NULL,NULL,'/default-catalog/c/C37',1,NULL,65),(66,'2016-01-19 15:52:49','2016-01-19 15:52:49','0fe78701-df0d-46b7-9d59-0b1c8a1bc11e','C38',1,NULL,NULL,NULL,'/default-catalog/c/C38',1,NULL,66),(67,'2016-01-19 15:52:49','2016-01-19 15:52:49','a609199f-ac97-4f35-9d8d-93f8f40fb1c4','C39',1,NULL,NULL,NULL,'/default-catalog/c/C39',1,NULL,67),(68,'2016-01-21 11:19:15','2016-01-21 11:19:15','2f08a118-52ad-460b-97e0-a59911934624','MultiTouch4',0,NULL,NULL,NULL,'/default-catalog/b/MultiTouch4',1,NULL,NULL),(72,'2016-01-26 11:12:57','2016-01-26 11:12:57','d9c9edff-6270-4ee3-9adc-7cf13ab25d99','MultiTouch5',0,NULL,NULL,NULL,'/default-catalog/b/MultiTouch5',1,NULL,NULL);
/*!40000 ALTER TABLE `ctentry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ctentry_attributes`
--

DROP TABLE IF EXISTS `ctentry_attributes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ctentry_attributes` (
  `attribute_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `sequence` double NOT NULL,
  `attributeType` int(11) DEFAULT NULL,
  `dateValue` date DEFAULT NULL,
  `doubleValue` double DEFAULT NULL,
  `integerValue` int(11) DEFAULT NULL,
  `stringValue` varchar(255) DEFAULT NULL,
  `base_attribute_id` bigint(20) DEFAULT NULL,
  `ctentry_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`attribute_id`),
  KEY `FKB712A5B57CDF2BD9` (`base_attribute_id`),
  KEY `FKB712A5B5E24388F8` (`ctentry_id`),
  CONSTRAINT `FKB712A5B57CDF2BD9` FOREIGN KEY (`base_attribute_id`) REFERENCES `base_attributes` (`base_attributes_id`),
  CONSTRAINT `FKB712A5B5E24388F8` FOREIGN KEY (`ctentry_id`) REFERENCES `ctentry` (`ctentry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ctentry_attributes`
--

LOCK TABLES `ctentry_attributes` WRITE;
/*!40000 ALTER TABLE `ctentry_attributes` DISABLE KEYS */;
/*!40000 ALTER TABLE `ctentry_attributes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ctentry_media`
--

DROP TABLE IF EXISTS `ctentry_media`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ctentry_media` (
  `media_id` bigint(20) NOT NULL,
  `ctentry_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`media_id`),
  KEY `FK8959B0C6E24388F8` (`ctentry_id`),
  KEY `FK8959B0C6C092B5FC` (`media_id`),
  CONSTRAINT `FK8959B0C6C092B5FC` FOREIGN KEY (`media_id`) REFERENCES `media` (`media_id`),
  CONSTRAINT `FK8959B0C6E24388F8` FOREIGN KEY (`ctentry_id`) REFERENCES `ctentry` (`ctentry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ctentry_media`
--

LOCK TABLES `ctentry_media` WRITE;
/*!40000 ALTER TABLE `ctentry_media` DISABLE KEYS */;
/*!40000 ALTER TABLE `ctentry_media` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `currency`
--

DROP TABLE IF EXISTS `currency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `currency` (
  `currency_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `descriprion` longtext,
  `iso_code` varchar(3) NOT NULL,
  PRIMARY KEY (`currency_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currency`
--

LOCK TABLES `currency` WRITE;
/*!40000 ALTER TABLE `currency` DISABLE KEYS */;
INSERT INTO `currency` (`currency_id`, `created`, `updated`, `uuid`, `descriprion`, `iso_code`) VALUES (1,'2016-01-12 12:32:51','2016-01-12 12:32:51','a76d2d4c-0c57-4ff0-8735-0de1a46ebc78','Euro','EUR');
/*!40000 ALTER TABLE `currency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `geocode`
--

DROP TABLE IF EXISTS `geocode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `geocode` (
  `geocode_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description_stringid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`geocode_id`),
  KEY `FKFB462CBED37DBE0F` (`description_stringid`),
  CONSTRAINT `FKFB462CBED37DBE0F` FOREIGN KEY (`description_stringid`) REFERENCES `multilingual_string` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `geocode`
--

LOCK TABLES `geocode` WRITE;
/*!40000 ALTER TABLE `geocode` DISABLE KEYS */;
/*!40000 ALTER TABLE `geocode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inventory` (
  `inventory_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `available` double DEFAULT NULL,
  `reserved` double DEFAULT NULL,
  `product_id` bigint(20) NOT NULL,
  `store_id` bigint(20) NOT NULL,
  `warehouse_id` bigint(20) NOT NULL,
  PRIMARY KEY (`inventory_id`),
  KEY `FK8790195CCCF1BE38` (`product_id`),
  KEY `FK8790195C5CF8B8DC` (`store_id`),
  KEY `FK8790195C949F754E` (`warehouse_id`),
  CONSTRAINT `FK8790195C5CF8B8DC` FOREIGN KEY (`store_id`) REFERENCES `stores` (`store_id`),
  CONSTRAINT `FK8790195C949F754E` FOREIGN KEY (`warehouse_id`) REFERENCES `warehouse` (`warehouse_id`),
  CONSTRAINT `FK8790195CCCF1BE38` FOREIGN KEY (`product_id`) REFERENCES `abstractProducts` (`ctentry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `locale`
--

DROP TABLE IF EXISTS `locale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `locale` (
  `locale_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `country` varchar(2) NOT NULL,
  `language` varchar(2) NOT NULL,
  `name` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`locale_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locale`
--

LOCK TABLES `locale` WRITE;
/*!40000 ALTER TABLE `locale` DISABLE KEYS */;
INSERT INTO `locale` (`locale_id`, `created`, `updated`, `uuid`, `country`, `language`, `name`) VALUES (1,'2016-01-12 12:32:51','2016-01-12 12:32:51','d0aaac18-5858-418a-85c9-e2c8d49d3761','IT','it','Italiano'),(2,'2016-01-12 12:32:51','2016-01-12 12:32:51','54271c27-3976-4018-b8db-09c19ec32d4f','US','en','English');
/*!40000 ALTER TABLE `locale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `locale_string_map`
--

DROP TABLE IF EXISTS `locale_string_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `locale_string_map` (
  `string_id` bigint(20) NOT NULL,
  `language` varchar(255) DEFAULT NULL,
  `text` longtext,
  `locale` varchar(255) NOT NULL,
  PRIMARY KEY (`string_id`,`locale`),
  KEY `FKB6496F3358397449` (`string_id`),
  CONSTRAINT `FKB6496F3358397449` FOREIGN KEY (`string_id`) REFERENCES `multilingual_string` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locale_string_map`
--

LOCK TABLES `locale_string_map` WRITE;
/*!40000 ALTER TABLE `locale_string_map` DISABLE KEYS */;
INSERT INTO `locale_string_map` (`string_id`, `language`, `text`, `locale`) VALUES (1,'en','public list','en'),(1,'it','listino al publico','it'),(2,'it','Computer','it'),(3,'it','Monitor','it'),(4,'it','Philips 32 pollici','it'),(5,'it','','it'),(6,'it','prodotto test5','it'),(7,'it','prodotto giorgio','it'),(8,'it','prodotto 12','it'),(9,'it','prodotto 13','it'),(10,'it','prodotto 14','it'),(11,'it','prodotto 15','it'),(12,'it','prodotto 16','it'),(13,'it','prodotto 17','it'),(14,'it','prodotto 18','it'),(15,'it','prodotto 19','it'),(16,'it','prodotto 20','it'),(17,'it','prodotto 21','it'),(18,'it','prodotto 22','it'),(19,'it','prodotto 23','it'),(20,'it','prodotto 24','it'),(21,'it','prodotto 25','it'),(22,'it','prodotto 26','it'),(23,'it','prodotto 27','it'),(24,'it','prodotto 28','it'),(25,'it','prodotto 29','it'),(26,'it','prodotto 30','it'),(27,'it','prodotto 31','it'),(28,'it','prodotto 32','it'),(29,'it','prodotto 33','it'),(30,'it','prodotto 34','it'),(31,'it','prodotto 35','it'),(32,'it','prodotto 36','it'),(33,'it','prodotto 37','it'),(34,'it','prodotto 38','it'),(35,'it','prodotto 39','it'),(36,'en','category 1','en'),(36,'it','categoria 1','it'),(37,'en','category 2','en'),(37,'it','categoria 2','it'),(38,'en','category 10','en'),(38,'it','categoria 10','it'),(39,'en','category 11','en'),(39,'it','categoria 11','it'),(40,'en','category 12','en'),(40,'it','categoria 12','it'),(41,'en','category 13','en'),(41,'it','categoria 13','it'),(42,'en','category 14','en'),(42,'it','categoria 14','it'),(43,'en','category 15','en'),(43,'it','categoria 15','it'),(44,'en','category 16','en'),(44,'it','categoria 16','it'),(45,'en','category 17','en'),(45,'it','categoria 17','it'),(46,'en','category 18','en'),(46,'it','categoria 18','it'),(47,'en','category 19','en'),(47,'it','categoria 19','it'),(48,'en','category 20','en'),(48,'it','categoria 20','it'),(49,'en','category 21','en'),(49,'it','categoria 21','it'),(50,'en','category 22','en'),(50,'it','categoria 22','it'),(51,'en','category 23','en'),(51,'it','categoria 23','it'),(52,'en','category 24','en'),(52,'it','categoria 24','it'),(53,'en','category 25','en'),(53,'it','categoria 25','it'),(54,'en','category 26','en'),(54,'it','categoria 26','it'),(55,'en','category 27','en'),(55,'it','categoria 27','it'),(56,'en','category 28','en'),(56,'it','categoria 28','it'),(57,'en','category 29','en'),(57,'it','categoria 29','it'),(58,'en','category 30','en'),(58,'it','categoria 30','it'),(59,'en','category 31','en'),(59,'it','categoria 31','it'),(60,'en','category 32','en'),(60,'it','categoria 32','it'),(61,'en','category 33','en'),(61,'it','categoria 33','it'),(62,'en','category 34','en'),(62,'it','categoria 34','it'),(63,'en','category 35','en'),(63,'it','categoria 35','it'),(64,'en','category 36','en'),(64,'it','categoria 36','it'),(65,'en','category 37','en'),(65,'it','categoria 37','it'),(66,'en','category 38','en'),(66,'it','categoria 38','it'),(67,'en','category 39','en'),(67,'it','categoria 39','it');
/*!40000 ALTER TABLE `locale_string_map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `locale_text_map`
--

DROP TABLE IF EXISTS `locale_text_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `locale_text_map` (
  `string_id` bigint(20) NOT NULL,
  `language` varchar(255) DEFAULT NULL,
  `text` longtext,
  `locale` varchar(255) NOT NULL,
  PRIMARY KEY (`string_id`,`locale`),
  KEY `FKF9C3638F5F1C9925` (`string_id`),
  CONSTRAINT `FKF9C3638F5F1C9925` FOREIGN KEY (`string_id`) REFERENCES `multilingual_text` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locale_text_map`
--

LOCK TABLES `locale_text_map` WRITE;
/*!40000 ALTER TABLE `locale_text_map` DISABLE KEYS */;
INSERT INTO `locale_text_map` (`string_id`, `language`, `text`, `locale`) VALUES (1,'it','','it'),(2,'it','','it'),(3,'it','TV Philips 32 pollici','it'),(4,'it','','it');
/*!40000 ALTER TABLE `locale_text_map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `media`
--

DROP TABLE IF EXISTS `media`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `media` (
  `media_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `content_type` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sequence` double NOT NULL,
  `source` varchar(255) DEFAULT NULL,
  `store_id` bigint(20) NOT NULL,
  `type` int(11) DEFAULT NULL,
  `description_string_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`media_id`),
  KEY `FK62F6FE4F30827C6` (`description_string_id`),
  CONSTRAINT `FK62F6FE4F30827C6` FOREIGN KEY (`description_string_id`) REFERENCES `multilingual_string` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `media`
--

LOCK TABLES `media` WRITE;
/*!40000 ALTER TABLE `media` DISABLE KEYS */;
/*!40000 ALTER TABLE `media` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_attribute`
--

DROP TABLE IF EXISTS `member_attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_attribute` (
  `attribute_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `sequence` double NOT NULL,
  `attributeType` int(11) DEFAULT NULL,
  `dateValue` date DEFAULT NULL,
  `doubleValue` double DEFAULT NULL,
  `integerValue` int(11) DEFAULT NULL,
  `stringValue` varchar(255) DEFAULT NULL,
  `base_attributes_id` bigint(20) NOT NULL,
  `member_id` bigint(20) NOT NULL,
  PRIMARY KEY (`attribute_id`),
  KEY `FKF73A373776C3C7A2` (`base_attributes_id`),
  KEY `FKF73A37377B32A13D` (`member_id`),
  CONSTRAINT `FKF73A373776C3C7A2` FOREIGN KEY (`base_attributes_id`) REFERENCES `base_attributes` (`base_attributes_id`),
  CONSTRAINT `FKF73A37377B32A13D` FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_attribute`
--

LOCK TABLES `member_attribute` WRITE;
/*!40000 ALTER TABLE `member_attribute` DISABLE KEYS */;
/*!40000 ALTER TABLE `member_attribute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `membergroups`
--

DROP TABLE IF EXISTS `membergroups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `membergroups` (
  `membergroups_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `description` varchar(254) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `store_id` bigint(20) NOT NULL,
  PRIMARY KEY (`membergroups_id`),
  KEY `FKAF088EE5CF8B8DC` (`store_id`),
  CONSTRAINT `FKAF088EE5CF8B8DC` FOREIGN KEY (`store_id`) REFERENCES `stores` (`store_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `membergroups`
--

LOCK TABLES `membergroups` WRITE;
/*!40000 ALTER TABLE `membergroups` DISABLE KEYS */;
/*!40000 ALTER TABLE `membergroups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `members`
--

DROP TABLE IF EXISTS `members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `members` (
  `member_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `field1` longtext,
  `field2` varchar(254) DEFAULT NULL,
  `field3` double DEFAULT NULL,
  `member_type` varchar(255) NOT NULL,
  PRIMARY KEY (`member_id`),
  UNIQUE KEY `UK_484a9018541f434dbd578d2cb4a` (`uuid`),
  UNIQUE KEY `UK_383d2690c8cc46f387b7a2a9ca2` (`uuid`),
  UNIQUE KEY `UK_1f02c76dc8c44f2fa181f2a8c8c` (`uuid`),
  UNIQUE KEY `UK_e61ba90c84bd45e2be80336865b` (`uuid`),
  UNIQUE KEY `UK_2eb81caedbee40ffa8b05b3c89b` (`uuid`),
  UNIQUE KEY `UK_b553fce96d1449f2b0ff78895c6` (`uuid`),
  UNIQUE KEY `UK_8c8754f9f3934dd8ab0bb797f82` (`uuid`),
  UNIQUE KEY `UK_2730c2cd236a44b3b89d5b4f4ae` (`uuid`),
  UNIQUE KEY `UK_28d0c7321e074abea9011621e71` (`uuid`),
  UNIQUE KEY `UK_aee4c9b9780c43d680a81984381` (`uuid`),
  UNIQUE KEY `UK_610d7bae946b46749269b0251e5` (`uuid`),
  UNIQUE KEY `UK_fc0821d6983d4ce2b86a56ef215` (`uuid`),
  UNIQUE KEY `UK_e537354f96924135a64492cbc1c` (`uuid`),
  UNIQUE KEY `UK_7b959f6a3d504271b86ef7f4c2c` (`uuid`),
  UNIQUE KEY `UK_42c74feb99f44ca0aa97a64e375` (`uuid`),
  UNIQUE KEY `UK_f3ca2f33c1aa4a129562c3ae1e4` (`uuid`),
  UNIQUE KEY `UK_130d9e2ed88a47aabd81c7e3556` (`uuid`),
  UNIQUE KEY `UK_55bb3f5531494789bef8df0dfe0` (`uuid`),
  UNIQUE KEY `UK_94dbe182f70245e3914a8c4c2d9` (`uuid`),
  UNIQUE KEY `UK_31bc47433b1b4102b796c3569b5` (`uuid`),
  UNIQUE KEY `UK_3958208c4c4d480b81b130f1239` (`uuid`),
  UNIQUE KEY `UK_cf809af1c1494e85bad94ba4d3a` (`uuid`),
  UNIQUE KEY `UK_cbd5e205e21843aa96697deb66e` (`uuid`),
  UNIQUE KEY `UK_a8cf6c914c36403e9d8eca86788` (`uuid`),
  UNIQUE KEY `UK_af4198f4c4e245c499dcd610dbb` (`uuid`),
  UNIQUE KEY `UK_4cadf069f41c49fa89129a0750b` (`uuid`),
  UNIQUE KEY `UK_200ed953df3f469082e0775608f` (`uuid`),
  UNIQUE KEY `UK_b37b490f6dc7400e8df20311162` (`uuid`),
  UNIQUE KEY `UK_8b76b32475724263b9e838d8373` (`uuid`),
  UNIQUE KEY `UK_1df3072766354b12ad6b73eafe5` (`uuid`),
  UNIQUE KEY `UK_a96d0fc2b358459799d6eb97d46` (`uuid`),
  UNIQUE KEY `UK_d808715ebb36456a85e50378c5d` (`uuid`),
  UNIQUE KEY `UK_9ffa4313bdff45c091b8769c089` (`uuid`),
  UNIQUE KEY `UK_03ee9283119541d3898e5bd2573` (`uuid`),
  UNIQUE KEY `UK_f19ce523cdf041d195c9e784a39` (`uuid`),
  UNIQUE KEY `UK_cb09017a9d8d41359fec464a163` (`uuid`),
  UNIQUE KEY `UK_5d16f3bee2534c3e8d80007e8d2` (`uuid`),
  UNIQUE KEY `UK_8aacf548ed02487aa376cd27270` (`uuid`),
  UNIQUE KEY `UK_dc06139de3c142e994c46a278e0` (`uuid`),
  UNIQUE KEY `UK_574bb66610d047a8b6bed5b1ea6` (`uuid`),
  UNIQUE KEY `UK_5b3c0cb198be4e8bbfe8265294a` (`uuid`),
  UNIQUE KEY `UK_addcb7eb62244dbfb09f4a335e9` (`uuid`),
  UNIQUE KEY `UK_4f5e3a0782ec473e870e1409408` (`uuid`),
  UNIQUE KEY `UK_3d181cfb62f9478a8ad87a724cc` (`uuid`),
  UNIQUE KEY `UK_1b11a112aa9545a48af7c17bad8` (`uuid`),
  UNIQUE KEY `UK_db862bf30822477096bf7507f2c` (`uuid`),
  UNIQUE KEY `UK_b3c666542dfb4f8fa0b4a0eb11d` (`uuid`),
  UNIQUE KEY `UK_1371bf2d0c88431590219c2ca54` (`uuid`),
  UNIQUE KEY `UK_7d8d9b992ded43d6954f90f0956` (`uuid`),
  UNIQUE KEY `UK_674abb43496f4c089334e84f6b6` (`uuid`),
  UNIQUE KEY `UK_4aff31473c0a44e7b57112fe4e6` (`uuid`),
  UNIQUE KEY `UK_40751878fd1445f79fd78a3c6c6` (`uuid`),
  UNIQUE KEY `UK_24a02653614d484d81d23ebe842` (`uuid`),
  UNIQUE KEY `UK_e44f57d2236544eb9d004509b75` (`uuid`),
  UNIQUE KEY `UK_1de14d3d19394602a312ab14400` (`uuid`),
  UNIQUE KEY `UK_e263bcb51a8e4035bceb6c26371` (`uuid`),
  UNIQUE KEY `UK_6e010937c70b4501adc651355ff` (`uuid`),
  UNIQUE KEY `UK_614668502d2c4da9bf8b0a9b19b` (`uuid`),
  UNIQUE KEY `UK_2f3d38629bca49d59af34b2a87b` (`uuid`),
  UNIQUE KEY `UK_2fffcb4d7e8e4cdbb7fc116a7e0` (`uuid`),
  UNIQUE KEY `UK_bb16da0b8601419590cba7eefcb` (`uuid`),
  UNIQUE KEY `UK_55834cb3a11f4f7c82513bda138` (`uuid`),
  UNIQUE KEY `UK_b6622db03ec34ca9b6138d472ab` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members`
--

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;
INSERT INTO `members` (`member_id`, `created`, `updated`, `uuid`, `field1`, `field2`, `field3`, `member_type`) VALUES (1,'2016-01-12 12:32:51','2016-01-12 12:32:51','33e7b0dd-e57a-4333-8a2f-064b65da8f9b',NULL,NULL,NULL,'USER'),(2,'2016-01-12 12:32:51','2016-01-20 17:05:10','16084847-e1b7-4783-b03a-34c90d92f604',NULL,NULL,NULL,'USER'),(3,'2016-01-15 14:05:09','2016-01-26 16:06:02','4e2e2b09-a69b-49eb-b78a-b505b901c3ba',NULL,NULL,NULL,'USER'),(4,'2016-01-26 16:06:45','2016-01-26 16:07:42','e9749473-68e6-4475-9f4b-525d25b8094e',NULL,NULL,NULL,'USER');
/*!40000 ALTER TABLE `members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `members_role`
--

DROP TABLE IF EXISTS `members_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `members_role` (
  `member_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `store_id` bigint(20) NOT NULL,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  PRIMARY KEY (`member_id`,`role_id`,`store_id`),
  KEY `FK1E6037DC7B32A13D` (`member_id`),
  KEY `FK1E6037DC5F2D187D` (`role_id`),
  KEY `FK1E6037DC5CF8B8DC` (`store_id`),
  CONSTRAINT `FK1E6037DC5CF8B8DC` FOREIGN KEY (`store_id`) REFERENCES `stores` (`store_id`),
  CONSTRAINT `FK1E6037DC5F2D187D` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
  CONSTRAINT `FK1E6037DC7B32A13D` FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members_role`
--

LOCK TABLES `members_role` WRITE;
/*!40000 ALTER TABLE `members_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `members_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `membgrouprel`
--

DROP TABLE IF EXISTS `membgrouprel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `membgrouprel` (
  `member_id` bigint(20) NOT NULL,
  `membergroups_id` bigint(20) NOT NULL,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `status` varchar(50) NOT NULL,
  `valid_from` date NOT NULL,
  `valid_to` date NOT NULL,
  PRIMARY KEY (`member_id`,`membergroups_id`),
  KEY `FKE88455E77B32A13D` (`member_id`),
  KEY `FKE88455E7F2CCA4E` (`membergroups_id`),
  CONSTRAINT `FKE88455E77B32A13D` FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`),
  CONSTRAINT `FKE88455E7F2CCA4E` FOREIGN KEY (`membergroups_id`) REFERENCES `membergroups` (`membergroups_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `membgrouprel`
--

LOCK TABLES `membgrouprel` WRITE;
/*!40000 ALTER TABLE `membgrouprel` DISABLE KEYS */;
/*!40000 ALTER TABLE `membgrouprel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `multilingual_string`
--

DROP TABLE IF EXISTS `multilingual_string`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `multilingual_string` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `multilingual_string`
--

LOCK TABLES `multilingual_string` WRITE;
/*!40000 ALTER TABLE `multilingual_string` DISABLE KEYS */;
INSERT INTO `multilingual_string` (`Id`, `created`, `updated`, `uuid`) VALUES (1,'2016-01-12 12:32:52','2016-01-12 12:32:52','7867b8c3-65b3-4966-b219-b45064277122'),(2,'2016-01-12 15:13:50','2016-01-12 15:13:50','a250895b-4a38-4a83-b277-24a54f349c61'),(3,'2016-01-12 15:14:30','2016-01-12 15:14:30','317fefc1-3eea-485c-83bd-52c492934a67'),(4,'2016-01-12 15:16:11','2016-01-12 15:16:11','307ae6ba-c9c0-4d4e-b156-fadb63c7b03f'),(5,'2016-01-12 16:11:18','2016-01-12 16:11:18','4271fe4b-0877-4099-b87d-02008d85ff25'),(6,'2016-01-12 16:11:33','2016-01-12 16:11:33','78fce9bc-3dd3-47a2-965b-7e7b4d43e8c1'),(7,'2016-01-12 16:11:33','2016-01-12 16:11:33','6a1d0c20-bb61-4571-8d3f-d94201946e3f'),(8,'2016-01-12 16:11:33','2016-01-12 16:11:33','983fd59e-8eac-42aa-973c-a8bb6e867920'),(9,'2016-01-12 16:11:33','2016-01-12 16:11:33','2007bc36-5096-4f6d-bf0e-7a12ee505c41'),(10,'2016-01-12 16:11:33','2016-01-12 16:11:33','5eb0f93f-5fb5-47fa-ae4c-410b7ac32861'),(11,'2016-01-12 16:11:34','2016-01-12 16:11:34','3621dc50-c016-48d1-b5bf-b79de759cd40'),(12,'2016-01-12 16:11:34','2016-01-12 16:11:34','4a130c88-f470-4303-86ad-f4f5b193ca22'),(13,'2016-01-12 16:11:34','2016-01-12 16:11:34','ea2fd28e-fb32-448b-9ec9-b90ad5d8435a'),(14,'2016-01-12 16:11:34','2016-01-12 16:11:34','89217836-c5cd-43dc-9665-3a0bc3114f46'),(15,'2016-01-12 16:11:34','2016-01-12 16:11:34','68b3cd0d-9b4b-4045-ac71-8babb3bc7f1a'),(16,'2016-01-12 16:11:34','2016-01-12 16:11:34','d07dec4b-c046-4e86-8c93-394e99f8e00c'),(17,'2016-01-12 16:11:34','2016-01-12 16:11:34','39702c8f-4bef-4122-8de3-f9d74a6e4960'),(18,'2016-01-12 16:11:34','2016-01-12 16:11:34','7d1df649-49c7-41e8-a955-8fdb0a3029dd'),(19,'2016-01-12 16:11:35','2016-01-12 16:11:35','649d1be5-929c-446a-a404-488338fe0c1a'),(20,'2016-01-12 16:11:35','2016-01-12 16:11:35','43f367c0-4700-4a77-885b-4dc560b34597'),(21,'2016-01-12 16:11:35','2016-01-12 16:11:35','4aeb58e8-2bf9-4f5e-b782-61c32af76cb7'),(22,'2016-01-12 16:11:35','2016-01-12 16:11:35','b4a440c1-3059-42ea-82d3-adb9383ea488'),(23,'2016-01-12 16:11:35','2016-01-12 16:11:35','4cb4f122-a06c-4c54-9e08-23b93cd614a4'),(24,'2016-01-12 16:11:35','2016-01-12 16:11:35','fa69de95-d2c5-4717-80a6-0e0f7c7d37d2'),(25,'2016-01-12 16:11:35','2016-01-12 16:11:35','4da005d7-fee9-4985-9987-ec42d9a5b503'),(26,'2016-01-12 16:11:35','2016-01-12 16:11:35','0b1fced9-5444-42e7-8c26-287d83e79e47'),(27,'2016-01-12 16:11:36','2016-01-12 16:11:36','fc89cc0f-6b6b-4c18-9b7f-7ce3d7b2c04e'),(28,'2016-01-12 16:11:36','2016-01-12 16:11:36','c1c3cad8-719f-4873-b6d5-e409abccdc60'),(29,'2016-01-12 16:11:36','2016-01-12 16:11:36','96e628f9-d98b-4ca8-bddd-4c413d8ce46f'),(30,'2016-01-12 16:11:36','2016-01-12 16:11:36','dba4c874-1d15-4697-8b84-61180fb2fdbb'),(31,'2016-01-12 16:11:36','2016-01-12 16:11:36','d7b368ea-0e41-4fee-ad44-73b46568196f'),(32,'2016-01-12 16:11:36','2016-01-12 16:11:36','d6897876-fe47-4d10-aab8-b7768f4afdfe'),(33,'2016-01-12 16:11:36','2016-01-12 16:11:36','cafc0eee-8717-46ef-b4b4-32fbf04980a2'),(34,'2016-01-12 16:11:36','2016-01-12 16:11:36','f9cbfb83-6156-470e-892f-8e7f27f82a5b'),(35,'2016-01-12 16:11:37','2016-01-12 16:11:37','68396e40-21c9-44b3-939c-3cb38b4e6b9a'),(36,'2016-01-19 15:52:45','2016-01-19 15:52:45','46a7d385-078f-4df6-8f24-0a089757bf84'),(37,'2016-01-19 15:52:45','2016-01-19 15:52:45','dfbb0763-c203-4f85-bb6e-981e2e1a76dd'),(38,'2016-01-19 15:52:45','2016-01-19 15:52:45','c0f828a2-8bc7-4681-a16d-9836fa6d9b88'),(39,'2016-01-19 15:52:45','2016-01-19 15:52:45','b03dc9b9-0f31-4a29-90ee-82768b310caf'),(40,'2016-01-19 15:52:45','2016-01-19 15:52:45','5eec36e5-fbe5-4e2d-8be4-641e5d5d157a'),(41,'2016-01-19 15:52:46','2016-01-19 15:52:46','5e511fec-c688-432e-8640-ea09693a993a'),(42,'2016-01-19 15:52:46','2016-01-19 15:52:46','a7fe92c6-ecd0-4ada-a620-fcfe02a4cea1'),(43,'2016-01-19 15:52:46','2016-01-19 15:52:46','59e11f40-78bb-4336-8a93-e14970cde38a'),(44,'2016-01-19 15:52:46','2016-01-19 15:52:46','c4adbf57-6cad-4bd7-a92a-9d7972d94a68'),(45,'2016-01-19 15:52:46','2016-01-19 15:52:46','dd0d20ba-0125-46de-a65c-780b8b9429a2'),(46,'2016-01-19 15:52:46','2016-01-19 15:52:46','b3f3da7e-3843-431e-a8a2-5134787ec96f'),(47,'2016-01-19 15:52:46','2016-01-19 15:52:46','7d6684ee-7b0a-4630-81bd-34740747c07c'),(48,'2016-01-19 15:52:47','2016-01-19 15:52:47','f095fa05-f028-49f0-9e0e-466c58cf4c20'),(49,'2016-01-19 15:52:47','2016-01-19 15:52:47','2a45795f-f9cb-4ed6-afb3-f7bce8888c8d'),(50,'2016-01-19 15:52:47','2016-01-19 15:52:47','cb0cd063-084c-46a6-ae54-eef90b8d832b'),(51,'2016-01-19 15:52:47','2016-01-19 15:52:47','e1778eaf-7c0f-41ee-8693-a8e7c001b331'),(52,'2016-01-19 15:52:47','2016-01-19 15:52:47','5e6c0c68-b30f-46be-8378-7eea710328bd'),(53,'2016-01-19 15:52:47','2016-01-19 15:52:47','1443cc47-c9e5-42d7-a944-442539715a50'),(54,'2016-01-19 15:52:47','2016-01-19 15:52:47','2066a95f-2734-4c46-869a-2f418b3dde1e'),(55,'2016-01-19 15:52:47','2016-01-19 15:52:47','e8f29457-85cf-477e-afc8-ab8b09f306a1'),(56,'2016-01-19 15:52:47','2016-01-19 15:52:47','971ffcc8-99a3-4dc5-8415-4e6f4601bf33'),(57,'2016-01-19 15:52:48','2016-01-19 15:52:48','9c7e5448-8be5-4025-b8f7-fc695c86ec6a'),(58,'2016-01-19 15:52:48','2016-01-19 15:52:48','193a7113-6bd2-431e-b979-97c86b9e3beb'),(59,'2016-01-19 15:52:48','2016-01-19 15:52:48','84eb32ce-aafb-4a2e-9f1c-5c1dbeae6cf8'),(60,'2016-01-19 15:52:48','2016-01-19 15:52:48','4cd0ffe3-9f53-40d1-b1c8-7b0177aea02f'),(61,'2016-01-19 15:52:48','2016-01-19 15:52:48','267d4989-ebb0-4e3d-b4dd-3804ca717f9d'),(62,'2016-01-19 15:52:48','2016-01-19 15:52:48','e489692a-1388-4a06-88c3-9fe135d58b91'),(63,'2016-01-19 15:52:48','2016-01-19 15:52:48','6557b696-ae50-4497-93bd-e746a21008d4'),(64,'2016-01-19 15:52:48','2016-01-19 15:52:48','f82262a4-3fbf-4d31-984e-4a7e6d1860b6'),(65,'2016-01-19 15:52:49','2016-01-19 15:52:49','a3ff74fa-a97f-4248-b2b6-d4909a6bad13'),(66,'2016-01-19 15:52:49','2016-01-19 15:52:49','3926a329-304d-4c9d-8017-d63858103d75'),(67,'2016-01-19 15:52:49','2016-01-19 15:52:49','28336b7d-027d-48c0-b67b-11e7056b451c');
/*!40000 ALTER TABLE `multilingual_string` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `multilingual_text`
--

DROP TABLE IF EXISTS `multilingual_text`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `multilingual_text` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `multilingual_text`
--

LOCK TABLES `multilingual_text` WRITE;
/*!40000 ALTER TABLE `multilingual_text` DISABLE KEYS */;
INSERT INTO `multilingual_text` (`Id`, `created`, `updated`, `uuid`) VALUES (1,'2016-01-12 15:13:50','2016-01-12 15:13:50','15cc3032-d0ff-425e-9324-c2dfa56a92ad'),(2,'2016-01-12 15:14:30','2016-01-12 15:14:30','38a2dcee-b7de-4205-8091-138b54882195'),(3,'2016-01-12 15:16:11','2016-01-12 15:16:11','2a33218e-21d3-430d-a626-997d5f4ed6e2'),(4,'2016-01-12 16:11:18','2016-01-12 16:11:18','4a1007a0-f176-485a-b196-310420829c0b');
/*!40000 ALTER TABLE `multilingual_text` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderitems`
--

DROP TABLE IF EXISTS `orderitems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderitems` (
  `pending` int(11) NOT NULL,
  `orderitem_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `discount_amount` double DEFAULT NULL,
  `discount_perc` double DEFAULT NULL,
  `discount_perc1` double DEFAULT NULL,
  `discount_perc2` double DEFAULT NULL,
  `quantity` double NOT NULL,
  `shipping_address_id` bigint(20) DEFAULT NULL,
  `shipping_cost` double NOT NULL,
  `sku` varchar(250) NOT NULL,
  `sku_cost` double DEFAULT NULL,
  `sku_description` longtext,
  `sku_net_price` double NOT NULL,
  `sku_price` double NOT NULL,
  `order_id` bigint(20) NOT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `shipmode_id` bigint(20) DEFAULT NULL,
  `suborders_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`orderitem_id`),
  KEY `FK2DA2C132E21E5CF0` (`order_id`),
  KEY `FK2DA2C132CCF1BE38` (`product_id`),
  KEY `FK2DA2C13246710562` (`shipmode_id`),
  KEY `FK2DA2C1322C02378B` (`suborders_id`),
  CONSTRAINT `FK2DA2C1322C02378B` FOREIGN KEY (`suborders_id`) REFERENCES `suborders` (`suborders_id`),
  CONSTRAINT `FK2DA2C13246710562` FOREIGN KEY (`shipmode_id`) REFERENCES `shipmode` (`shipmode_id`),
  CONSTRAINT `FK2DA2C132CCF1BE38` FOREIGN KEY (`product_id`) REFERENCES `abstractProducts` (`ctentry_id`),
  CONSTRAINT `FK2DA2C132E21E5CF0` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderitems`
--

LOCK TABLES `orderitems` WRITE;
/*!40000 ALTER TABLE `orderitems` DISABLE KEYS */;
INSERT INTO `orderitems` (`pending`, `orderitem_id`, `created`, `updated`, `uuid`, `discount_amount`, `discount_perc`, `discount_perc1`, `discount_perc2`, `quantity`, `shipping_address_id`, `shipping_cost`, `sku`, `sku_cost`, `sku_description`, `sku_net_price`, `sku_price`, `order_id`, `product_id`, `shipmode_id`, `suborders_id`) VALUES (0,1,'2016-01-14 17:22:37','2016-01-14 17:22:37','7cdb3eff-06d2-47a6-84f4-544f922f25af',0,0,0,0,1,NULL,0,'Philips32',12,NULL,12,12,2,3,NULL,NULL),(0,2,'2016-01-15 10:10:24','2016-01-15 10:10:24','7b3f6730-7ccc-4228-a7d8-5423a56c3420',0,0,0,0,1,NULL,0,'Philips32',12,NULL,12,12,4,3,NULL,NULL),(0,3,'2016-01-15 11:22:11','2016-01-15 11:22:11','b7ec4a1a-fe4d-4ebf-acb5-40b83069a6e9',0,0,0,0,1,NULL,0,'Philips32',12,NULL,12,12,8,3,NULL,NULL),(0,4,'2016-01-15 11:26:19','2016-01-15 11:26:19','5a34d08c-5113-41be-b622-41c7bfdf0fc9',0,0,0,0,1,NULL,0,'Philips32',12,NULL,12,12,9,3,NULL,NULL),(0,5,'2016-01-15 11:26:37','2016-01-15 11:26:37','3b37d0d8-678d-4859-803e-3d41c21bd810',0,0,0,0,1,NULL,0,'Philips32',12,NULL,12,12,10,3,NULL,NULL),(0,6,'2016-01-15 11:28:27','2016-01-15 11:28:27','7e34ac22-bbaa-4962-9d26-c6407009e2d2',0,0,0,0,1,NULL,0,'Philips32',12,NULL,12,12,11,3,NULL,NULL),(0,7,'2016-01-15 11:28:34','2016-01-15 11:28:34','5f91df19-41fd-4957-ab0d-495228f3f20e',0,0,0,0,1,NULL,0,'Philips32',12,NULL,12,12,12,3,NULL,NULL),(0,8,'2016-01-15 11:28:47','2016-01-15 11:28:47','80328967-37ea-471b-9422-96879725687f',0,0,0,0,1,NULL,0,'Philips32',12,NULL,12,12,13,3,NULL,NULL),(0,9,'2016-01-15 11:29:00','2016-01-15 11:29:00','b83c6448-2564-46c6-8f24-8146a19830d4',0,0,0,0,1,NULL,0,'Philips32',12,NULL,12,12,14,3,NULL,NULL),(0,10,'2016-01-15 11:29:19','2016-01-15 11:29:19','365f826d-f1d9-4993-8c78-9e693007e654',0,0,0,0,1,NULL,0,'Philips32',12,NULL,12,12,15,3,NULL,NULL),(0,11,'2016-01-15 11:29:41','2016-01-15 11:29:41','567a208d-4015-48b7-b17b-9146f5bfc567',0,0,0,0,1,NULL,0,'Philips32',12,NULL,12,12,16,3,NULL,NULL),(0,12,'2016-01-15 11:29:57','2016-01-15 11:29:57','45b67077-c612-4dfa-ac76-7fde2adbb7e3',0,0,0,0,3,NULL,0,'Philips32',12,NULL,12,12,17,3,NULL,NULL),(0,13,'2016-01-15 11:39:53','2016-01-15 11:39:53','db1935ee-6b22-4ca1-b9f8-29a63558e201',0,0,0,0,3,NULL,0,'Philips32',12,NULL,12,12,18,3,NULL,NULL),(0,14,'2016-01-15 11:40:43','2016-01-15 11:40:43','71a09f38-5780-4ccc-820d-15f693883138',0,0,0,0,3,NULL,0,'Philips32',12,NULL,12,12,19,3,NULL,NULL),(0,15,'2016-01-15 11:49:57','2016-01-15 11:49:57','c03416c2-1f4e-4f83-8e6f-156bd12bd139',0,0,0,0,3,NULL,0,'Philips32',12,NULL,12,12,20,3,NULL,NULL),(0,16,'2016-01-15 11:50:22','2016-01-15 11:50:22','7aa33614-b516-4c8c-aaf4-015f12102c79',0,0,0,0,3,NULL,0,'Philips32',12,NULL,12,12,21,3,NULL,NULL),(0,17,'2016-01-15 11:52:05','2016-01-15 11:52:05','1e0ee04b-eb9b-4159-916b-ca2486515a35',0,0,0,0,3,NULL,0,'Philips32',12,NULL,12,12,22,3,NULL,NULL),(0,18,'2016-01-15 11:53:21','2016-01-15 11:53:21','3a1f3aff-1d1b-4521-a4ac-6e380aa8bb99',0,0,0,0,3,NULL,0,'Philips32',12,NULL,12,12,23,3,NULL,NULL),(0,19,'2016-01-15 11:53:55','2016-01-15 11:53:55','275340cb-f2c2-4e64-b686-7bcce6065c09',0,0,0,0,3,NULL,0,'Philips32',12,NULL,12,12,24,3,NULL,NULL),(0,20,'2016-01-15 12:12:09','2016-01-15 12:12:09','084c3c2c-2b84-40a6-aaef-9e7b6aa24f31',0,0,0,0,3,NULL,0,'Philips32',12,NULL,12,12,25,3,NULL,NULL),(0,21,'2016-01-15 12:13:12','2016-01-15 12:13:12','c3222f91-1815-42b8-8a23-b5c96803fcba',0,0,0,0,1,NULL,0,'Philips32',12,NULL,12,12,26,3,NULL,NULL),(0,22,'2016-01-15 12:18:13','2016-01-15 12:18:13','064d5eb8-3323-44b1-84f5-39315e131850',0,0,0,0,3,NULL,0,'Philips32',12,NULL,12,12,27,3,NULL,NULL),(0,23,'2016-01-15 12:24:56','2016-01-15 12:24:56','8f31981c-8d25-4b97-a836-3230a71f14f1',0,0,0,0,3,NULL,0,'Philips32',12,NULL,12,12,28,3,NULL,NULL),(0,24,'2016-01-15 15:02:40','2016-01-15 15:02:40','58765397-44f5-4913-a50e-4c4e5511dab8',0,0,0,0,3,NULL,0,'Philips32',12,NULL,12,12,40,3,NULL,NULL),(0,25,'2016-01-15 15:53:11','2016-01-15 15:53:11','95425ad2-54b6-4e35-852b-9812d450bc0d',0,0,0,0,3,NULL,0,'Philips32',12,NULL,12,12,45,3,NULL,NULL),(0,26,'2016-01-15 15:57:21','2016-01-15 15:57:21','d79cce31-7ede-4e95-aba6-1d488e8e16d8',0,0,0,0,3,NULL,0,'Philips32',12,NULL,12,12,46,3,NULL,NULL),(0,27,'2016-01-15 16:45:21','2016-01-15 16:45:21','578f5f94-697c-47f7-8025-068181440456',0,0,0,0,3,NULL,0,'Philips32',12,NULL,12,12,47,3,NULL,NULL),(1,32,'2016-01-19 15:16:14','2016-01-19 15:16:14','4bf4f6d8-49db-4564-9b03-8e3b54c2e96f',0,0,0,0,2,NULL,0,'Philips32',12,NULL,12,12,57,3,NULL,NULL),(0,33,'2016-01-26 11:32:09','2016-01-26 11:32:09','0c5ebd59-6a99-4412-951c-53eb222b1123',0,0,0,0,1,NULL,0,'Philips32',12,NULL,12,12,87,3,NULL,NULL),(0,34,'2016-01-26 11:38:17','2016-01-26 11:38:17','db3fc42a-93f9-4b30-a40a-3966f027f3e4',0,0,0,0,1,NULL,0,'Philips32',12,NULL,12,12,88,3,NULL,NULL),(0,35,'2016-01-26 11:39:22','2016-01-26 11:39:22','d5b47e19-fff6-4c34-85ad-9bfb548bf5b6',0,0,0,0,1,NULL,0,'Philips32',12,NULL,12,12,89,3,NULL,NULL);
/*!40000 ALTER TABLE `orderitems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderitems_attribute`
--

DROP TABLE IF EXISTS `orderitems_attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderitems_attribute` (
  `attribute_id` bigint(20) NOT NULL,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `base_attributes_id` decimal(19,2) NOT NULL,
  `sequence` double NOT NULL,
  `attributeType` int(11) DEFAULT NULL,
  `dateValue` date DEFAULT NULL,
  `doubleValue` double DEFAULT NULL,
  `integerValue` int(11) DEFAULT NULL,
  `stringValue` varchar(255) DEFAULT NULL,
  `orderitems_id` bigint(20) NOT NULL,
  PRIMARY KEY (`attribute_id`),
  KEY `FK32990FCF43F7619F` (`orderitems_id`),
  CONSTRAINT `FK32990FCF43F7619F` FOREIGN KEY (`orderitems_id`) REFERENCES `orderitems` (`orderitem_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderitems_attribute`
--

LOCK TABLES `orderitems_attribute` WRITE;
/*!40000 ALTER TABLE `orderitems_attribute` DISABLE KEYS */;
/*!40000 ALTER TABLE `orderitems_attribute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderpayment`
--

DROP TABLE IF EXISTS `orderpayment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderpayment` (
  `orderpayment_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `amount` double DEFAULT NULL,
  `orders_id` bigint(20) NOT NULL,
  `paymethod_id` bigint(20) NOT NULL,
  PRIMARY KEY (`orderpayment_id`),
  KEY `FKA2F076F8C931D8FB` (`orders_id`),
  KEY `FKA2F076F86BDD0612` (`paymethod_id`),
  CONSTRAINT `FKA2F076F86BDD0612` FOREIGN KEY (`paymethod_id`) REFERENCES `paymethod` (`paymethod_id`),
  CONSTRAINT `FKA2F076F8C931D8FB` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderpayment`
--

LOCK TABLES `orderpayment` WRITE;
/*!40000 ALTER TABLE `orderpayment` DISABLE KEYS */;
/*!40000 ALTER TABLE `orderpayment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `pending` int(11) NOT NULL,
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `cookie` varchar(250) DEFAULT NULL,
  `discount_amount` double DEFAULT NULL,
  `discount_perc` double DEFAULT NULL,
  `order_amount` double DEFAULT NULL,
  `status` varchar(2) NOT NULL,
  `total_cost` double DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `total_product` double DEFAULT NULL,
  `total_service` double DEFAULT NULL,
  `total_shipping` double DEFAULT NULL,
  `total_tax` double DEFAULT NULL,
  `orderNumber` varchar(100) DEFAULT NULL,
  `pay_amount` double DEFAULT NULL,
  `billing_address_id` bigint(20) DEFAULT NULL,
  `currency_id` bigint(20) NOT NULL,
  `customer_id` bigint(20) NOT NULL,
  `shipmode_id` bigint(20) DEFAULT NULL,
  `shipping_address_id` bigint(20) DEFAULT NULL,
  `store_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `taxType` int(11) DEFAULT NULL,
  `paymethod_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `FKC3DF62E5EF40301B` (`billing_address_id`),
  KEY `FKC3DF62E56C1E9E18` (`currency_id`),
  KEY `FKC3DF62E5C1927579` (`customer_id`),
  KEY `FKC3DF62E546710562` (`shipmode_id`),
  KEY `FKC3DF62E5DBEA8708` (`shipping_address_id`),
  KEY `FKC3DF62E55CF8B8DC` (`store_id`),
  KEY `FKC3DF62E5457DC5D` (`user_id`),
  KEY `FK_f6a73a4b13774de5bd64d2a5ede` (`paymethod_id`),
  CONSTRAINT `FKC3DF62E5457DC5D` FOREIGN KEY (`user_id`) REFERENCES `users` (`member_id`),
  CONSTRAINT `FKC3DF62E546710562` FOREIGN KEY (`shipmode_id`) REFERENCES `shipmode` (`shipmode_id`),
  CONSTRAINT `FKC3DF62E55CF8B8DC` FOREIGN KEY (`store_id`) REFERENCES `stores` (`store_id`),
  CONSTRAINT `FKC3DF62E56C1E9E18` FOREIGN KEY (`currency_id`) REFERENCES `currency` (`currency_id`),
  CONSTRAINT `FKC3DF62E5C1927579` FOREIGN KEY (`customer_id`) REFERENCES `members` (`member_id`),
  CONSTRAINT `FKC3DF62E5DBEA8708` FOREIGN KEY (`shipping_address_id`) REFERENCES `addresses` (`address_id`),
  CONSTRAINT `FKC3DF62E5EF40301B` FOREIGN KEY (`billing_address_id`) REFERENCES `addresses` (`address_id`),
  CONSTRAINT `FK_f6a73a4b13774de5bd64d2a5ede` FOREIGN KEY (`paymethod_id`) REFERENCES `paymethod` (`paymethod_id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` (`pending`, `order_id`, `created`, `updated`, `uuid`, `cookie`, `discount_amount`, `discount_perc`, `order_amount`, `status`, `total_cost`, `total_price`, `total_product`, `total_service`, `total_shipping`, `total_tax`, `orderNumber`, `pay_amount`, `billing_address_id`, `currency_id`, `customer_id`, `shipmode_id`, `shipping_address_id`, `store_id`, `user_id`, `taxType`, `paymethod_id`) VALUES (1,1,'2016-01-14 12:09:35','2016-01-14 12:09:35','e2a99755-d69b-4840-a988-1f81730e0347','ZGVmYXVsdC1zdG9yZQ==-008edd17-fe6a-4752-be94-126d13fdc9b3',NULL,0,0,'P',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,NULL,NULL,1,1,NULL,NULL),(0,2,'2016-01-14 17:22:28','2016-01-14 17:23:00','68be5e00-7d4d-41b6-86cb-82fc0db5b9b3','ZGVmYXVsdC1zdG9yZQ==-78e98e81-b8fe-485c-be8a-dfd7072c9902',NULL,0,12,'P',12,12,12,NULL,0,0,NULL,NULL,2,1,1,NULL,3,1,1,NULL,NULL),(0,4,'2016-01-15 10:10:24','2016-01-15 10:11:04','2d3595f0-923d-42a7-a702-103ecb0e0669','ZGVmYXVsdC1zdG9yZQ==-4bafe408-0097-45a5-9e0d-adeafdc45a10',NULL,0,12,'P',12,12,12,NULL,0,0,NULL,NULL,4,1,1,NULL,5,1,1,NULL,NULL),(0,8,'2016-01-15 11:22:11','2016-01-15 11:22:11','4aa225ad-4b50-4890-89ef-7081c7c278c3','ZGVmYXVsdC1zdG9yZQ==-00e298d9-347d-43e8-bbe9-6cc0b923b793',NULL,0,0,'P',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,7,1,1,NULL,NULL,1,1,NULL,NULL),(0,9,'2016-01-15 11:26:18','2016-01-15 11:26:18','47673ee3-fa71-4675-aeb2-0d8b05b9a6e3','ZGVmYXVsdC1zdG9yZQ==-00e298d9-347d-43e8-bbe9-6cc0b923b793',NULL,0,0,'P',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,9,1,1,NULL,NULL,1,1,NULL,NULL),(0,10,'2016-01-15 11:26:37','2016-01-15 11:26:37','810f3577-9ea4-4fd5-8e7b-2ec8989b2a23','ZGVmYXVsdC1zdG9yZQ==-00e298d9-347d-43e8-bbe9-6cc0b923b793',NULL,0,0,'P',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,11,1,1,NULL,NULL,1,1,NULL,NULL),(0,11,'2016-01-15 11:28:26','2016-01-15 11:28:27','be36e826-e0c3-4ec4-8e6e-9604673637ad','ZGVmYXVsdC1zdG9yZQ==-00e298d9-347d-43e8-bbe9-6cc0b923b793',NULL,0,0,'P',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,13,1,1,NULL,NULL,1,1,NULL,NULL),(0,12,'2016-01-15 11:28:33','2016-01-15 11:28:34','67d35415-8741-4343-9b82-92799fb4f20d','ZGVmYXVsdC1zdG9yZQ==-00e298d9-347d-43e8-bbe9-6cc0b923b793',NULL,0,0,'P',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,15,1,1,NULL,NULL,1,1,NULL,NULL),(0,13,'2016-01-15 11:28:47','2016-01-15 11:28:47','85b8f2bb-1d90-47e1-8c83-c0ddc5313995','ZGVmYXVsdC1zdG9yZQ==-00e298d9-347d-43e8-bbe9-6cc0b923b793',NULL,0,0,'P',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,17,1,1,NULL,NULL,1,1,NULL,NULL),(0,14,'2016-01-15 11:28:59','2016-01-15 11:29:00','16ad8453-c375-4324-af8e-b8c48b0c77bb','ZGVmYXVsdC1zdG9yZQ==-00e298d9-347d-43e8-bbe9-6cc0b923b793',NULL,0,0,'P',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,19,1,1,NULL,NULL,1,1,NULL,NULL),(0,15,'2016-01-15 11:29:19','2016-01-15 11:29:19','352f9109-63b4-4df8-81ba-6719246287f0','ZGVmYXVsdC1zdG9yZQ==-00e298d9-347d-43e8-bbe9-6cc0b923b793',NULL,0,0,'P',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,21,1,1,NULL,NULL,1,1,NULL,NULL),(0,16,'2016-01-15 11:29:41','2016-01-15 11:29:41','2ea81b20-c1bc-4cac-aa2e-e213b4c824ad','ZGVmYXVsdC1zdG9yZQ==-00e298d9-347d-43e8-bbe9-6cc0b923b793',NULL,0,0,'P',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,23,1,1,NULL,NULL,1,1,NULL,NULL),(0,17,'2016-01-15 11:29:57','2016-01-15 11:29:57','25573872-e312-46de-a921-ea2dfc98344f','ZGVmYXVsdC1zdG9yZQ==-00e298d9-347d-43e8-bbe9-6cc0b923b793',NULL,0,0,'P',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,25,1,1,NULL,NULL,1,1,NULL,NULL),(0,18,'2016-01-15 11:39:53','2016-01-15 11:39:53','9391c1c6-4f3a-44f4-bcc2-124b977225d0','ZGVmYXVsdC1zdG9yZQ==-db46e66d-db56-4d05-823d-afd026cfbec9',NULL,0,0,'P',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,26,1,1,NULL,27,1,1,NULL,NULL),(0,19,'2016-01-15 11:40:42','2016-01-15 11:40:43','0d844e78-c4ca-4f8f-b022-ed0af292aa94','ZGVmYXVsdC1zdG9yZQ==-db46e66d-db56-4d05-823d-afd026cfbec9',NULL,0,0,'P',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,28,1,1,NULL,29,1,1,NULL,NULL),(0,20,'2016-01-15 11:49:56','2016-01-15 11:49:56','70058127-5e97-4ccc-98d0-dae6e432ef13','ZGVmYXVsdC1zdG9yZQ==-043c3a14-1b49-4ce2-b5ac-ba169508c9a7',NULL,0,0,'P',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,30,1,1,NULL,31,1,1,NULL,NULL),(0,21,'2016-01-15 11:50:22','2016-01-15 11:50:22','4693d343-b977-49bb-bdce-5be5dbb0bcd6','ZGVmYXVsdC1zdG9yZQ==-043c3a14-1b49-4ce2-b5ac-ba169508c9a7',NULL,0,0,'P',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,32,1,1,NULL,33,1,1,NULL,NULL),(0,22,'2016-01-15 11:52:04','2016-01-15 11:52:05','d6ca82c1-d55d-4f48-8f17-260b0c21fe60','ZGVmYXVsdC1zdG9yZQ==-8fe81ca9-64dd-4b77-88fb-d9eca9698f4b',NULL,0,0,'P',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,34,1,1,NULL,35,1,1,NULL,NULL),(0,23,'2016-01-15 11:53:21','2016-01-15 11:53:21','304f12a1-a362-49d7-ac9f-4cf9bf0cccb5','ZGVmYXVsdC1zdG9yZQ==-8fe81ca9-64dd-4b77-88fb-d9eca9698f4b',NULL,0,0,'P',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,36,1,1,NULL,37,1,1,NULL,NULL),(0,24,'2016-01-15 11:53:54','2016-01-15 11:53:55','3dfd40c1-1463-4e37-ab61-5f61f6485dd2','ZGVmYXVsdC1zdG9yZQ==-8fe81ca9-64dd-4b77-88fb-d9eca9698f4b',NULL,0,0,'P',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,38,1,1,NULL,39,1,1,NULL,NULL),(0,25,'2016-01-15 12:12:08','2016-01-15 12:12:08','6a993ac1-a78e-469b-9bde-810244bf9a17','ZGVmYXVsdC1zdG9yZQ==-02e7efb9-5cce-4164-a1f8-f1d8d738ea16',NULL,0,0,'P',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,40,1,1,NULL,41,1,1,NULL,NULL),(0,26,'2016-01-15 12:13:11','2016-01-15 12:13:32','b50b0e33-9492-4d45-b51a-35584ee61d7b','ZGVmYXVsdC1zdG9yZQ==-97f90cfc-fd51-4453-a9bb-f05ccd8e70e6',NULL,0,12,'P',12,12,12,NULL,0,0,NULL,NULL,42,1,1,NULL,43,1,1,NULL,NULL),(0,27,'2016-01-15 12:18:12','2016-01-15 12:18:13','025fc026-f0c8-4409-acab-93b18ed83b45','ZGVmYXVsdC1zdG9yZQ==-019373dd-57f4-45d6-9051-74394d8a32e7',NULL,0,0,'P',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,44,1,1,NULL,45,1,1,NULL,NULL),(0,28,'2016-01-15 12:24:56','2016-01-15 12:24:56','d67e3048-2226-45e4-b5c4-d1862fbb9f99','ZGVmYXVsdC1zdG9yZQ==-6281a4bb-9f48-426b-b915-884ad15cdd94',NULL,0,36,'P',12,36,36,NULL,0,0,NULL,NULL,46,1,1,NULL,47,1,1,NULL,NULL),(0,40,'2016-01-15 15:02:12','2016-01-15 15:02:40','a2c13185-ac9f-4755-8166-8f20e0683dd5','ZGVmYXVsdC1zdG9yZQ==-6fe20bd2-da3b-433a-8e65-1a2bdcade89a',NULL,0,36,'P',12,36,36,NULL,0,0,NULL,NULL,60,1,1,NULL,61,1,1,NULL,NULL),(0,45,'2016-01-15 15:53:03','2016-01-15 15:53:11','1d552c01-aa54-4505-941a-808e9921bc1d','ZGVmYXVsdC1zdG9yZQ==-97c9aa87-6d72-4043-9211-cb0502e9a175',NULL,0,36,'P',12,36,36,NULL,0,0,NULL,NULL,66,1,1,NULL,67,1,1,NULL,NULL),(0,46,'2016-01-15 15:56:49','2016-01-15 15:57:21','3c037871-9512-469d-ba95-66db40aacbcc','ZGVmYXVsdC1zdG9yZQ==-615dc76e-b348-4287-95a9-44c4aa0409d1',NULL,0,36,'P',12,36,36,NULL,0,0,NULL,NULL,68,1,1,NULL,69,1,1,NULL,NULL),(0,47,'2016-01-15 16:45:18','2016-01-15 16:45:21','ac6d3267-37d6-48a2-9409-fa260d5dfba4','ZGVmYXVsdC1zdG9yZQ==-025d70dc-b877-4918-b34f-4186212ff0b6',NULL,0,36,'P',12,36,36,NULL,0,0,NULL,NULL,70,1,1,NULL,71,1,1,NULL,NULL),(1,50,'2016-01-18 14:42:28','2016-01-18 14:42:28','f8ee8e9a-e7db-4132-a996-ed58ec67044a','ZGVmYXVsdC1zdG9yZQ==-83cc4cd4-65d8-48d3-972f-0921097d1e02',NULL,0,0,'P',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,NULL,NULL,1,1,NULL,NULL),(1,51,'2016-01-18 14:53:12','2016-01-18 14:53:12','bcc90ea6-53bf-491a-991e-b4ef8b541edd','ZGVmYXVsdC1zdG9yZQ==-6086ae4c-f91e-46ab-9f61-f1ed9869e2fb',NULL,0,0,'P',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,NULL,NULL,1,1,NULL,NULL),(1,52,'2016-01-18 15:48:58','2016-01-18 15:48:58','2b606352-0b9e-4bf0-b3ed-60d62422cb93','ZGVmYXVsdC1zdG9yZQ==-0cbc4776-88e8-4ba1-bcb3-bf56b15482bc',NULL,0,0,'P',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,NULL,NULL,1,1,NULL,NULL),(1,53,'2016-01-18 16:00:05','2016-01-18 16:00:05','44345dc4-312d-4f31-aa88-97da40670268','ZGVmYXVsdC1zdG9yZQ==-cfc1d236-e86c-4e0e-bc17-b3042dc2a10a',NULL,0,0,'P',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,NULL,NULL,1,1,NULL,NULL),(1,57,'2016-01-19 15:15:00','2016-01-19 15:16:17','afb944c8-77e6-4622-94a4-98cd4679ae4e','ZGVmYXVsdC1zdG9yZQ==-c6104051-ecec-4c9b-91f5-6dbd7b30d005',NULL,0,24,'P',24,24,24,NULL,0,0,NULL,NULL,NULL,1,1,NULL,NULL,1,1,NULL,NULL),(1,58,'2016-01-20 16:17:06','2016-01-20 16:20:46','cc6e50b1-09ef-471a-8a82-1c7dbbd59bf4','ZGVmYXVsdC1zdG9yZQ==-1ae62b58-9b33-477f-8802-de12a633b44b',NULL,0,0,'P',0,0,0,NULL,0,0,NULL,NULL,78,1,1,NULL,NULL,1,1,NULL,NULL),(1,59,'2016-01-20 16:28:14','2016-01-20 16:28:14','6768f852-3ef9-45ee-a76f-8faeb72742f8','ZGVmYXVsdC1zdG9yZQ==-e30ca8ae-6212-4f68-a0f8-76c2bd51f429',NULL,0,0,'P',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,NULL,NULL,1,1,NULL,NULL),(1,60,'2016-01-21 17:24:40','2016-01-21 17:24:40','957947ce-8958-44f2-a1c7-eab5f2f6e415','ZGVmYXVsdC1zdG9yZQ==-ca7d15d4-b03b-4f8e-8063-d0fda8835be7',NULL,0,0,'P',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,NULL,NULL,1,1,NULL,NULL),(1,78,'2016-01-22 17:33:42','2016-01-22 17:33:42','0e3a30f6-67ba-42e2-820f-aacfeaa05472','ZGVmYXVsdC1zdG9yZQ==-b4738eab-6e24-41e2-a083-3a3666431b98',NULL,0,0,'P',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,NULL,NULL,1,1,NULL,NULL),(1,82,'2016-01-25 17:07:39','2016-01-25 17:07:39','a732fd48-427b-4279-8652-fc7b1b6dafe9','ZGVmYXVsdC1zdG9yZQ==-92835741-8c05-408f-a959-6be3cbdcee8e',NULL,0,0,'P',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,NULL,NULL,1,1,NULL,NULL),(1,83,'2016-01-25 17:44:47','2016-01-25 17:44:47','7b5fe1bf-e22f-46fe-8cd2-b370720540d5','ZGVmYXVsdC1zdG9yZQ==-d83c442c-f241-4968-8417-5feb40efa4aa',NULL,0,0,'P',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,NULL,NULL,1,1,NULL,NULL),(1,84,'2016-01-26 09:30:49','2016-01-26 09:30:49','023a5df0-ad16-4477-adbe-5ebfbcc7f431','ZGVmYXVsdC1zdG9yZQ==-8e542dfe-7396-42a8-a911-af6c08438a1d',NULL,0,0,'P',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,NULL,NULL,1,1,NULL,NULL),(1,85,'2016-01-26 10:03:32','2016-01-26 10:03:32','587d4a1e-0866-422f-bcfe-afb899e69321','ZGVmYXVsdC1zdG9yZQ==-66bf38a5-70b3-443d-a8fa-1ef344cdfe37',NULL,0,0,'P',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,NULL,NULL,1,1,NULL,NULL),(1,86,'2016-01-26 10:31:38','2016-01-26 10:31:38','286ca20c-3c10-433c-99cb-2d36fadc969c','ZGVmYXVsdC1zdG9yZQ==-0663cd9e-3ec1-43bd-9d1c-205615e29645',NULL,0,0,'P',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,NULL,NULL,1,1,NULL,NULL),(0,87,'2016-01-26 11:32:04','2016-01-26 11:32:33','6e9fa6ee-226d-4bb9-98cf-4f9099eb3784','ZGVmYXVsdC1zdG9yZQ==-35513a44-cc74-4269-b750-758867417456',NULL,0,12,'P',12,12,12,NULL,0,0,NULL,NULL,79,1,3,NULL,80,1,3,NULL,NULL),(0,88,'2016-01-26 11:38:14','2016-01-26 11:38:34','e3a9b705-21ed-46c2-a264-9e71ea1bf13e','ZGVmYXVsdC1zdG9yZQ==-35513a44-cc74-4269-b750-758867417456',NULL,0,12,'P',12,12,12,NULL,0,0,NULL,NULL,81,1,3,NULL,82,1,3,NULL,NULL),(0,89,'2016-01-26 11:39:19','2016-01-26 11:39:29','7677cc35-7b53-4c93-becb-cf28340e9e58','ZGVmYXVsdC1zdG9yZQ==-35513a44-cc74-4269-b750-758867417456',NULL,0,12,'P',12,12,12,NULL,0,0,NULL,NULL,83,1,3,NULL,84,1,3,NULL,NULL),(1,92,'2016-01-26 12:48:24','2016-01-26 12:48:24','97488fd7-210b-470f-b375-eaa71466928e','ZGVmYXVsdC1zdG9yZQ==-8fe1b32a-4620-4488-85a5-f70745ecc9df',NULL,0,0,'P',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,NULL,NULL,1,1,NULL,NULL),(1,93,'2016-01-26 14:17:47','2016-01-26 14:17:47','c610614b-dc3b-45a5-9656-41bc2e4f694d','ZGVmYXVsdC1zdG9yZQ==-509135a5-760e-4884-bbc8-2946ccecef64',NULL,0,0,'P',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,NULL,NULL,1,1,NULL,NULL),(1,96,'2016-01-26 15:20:22','2016-01-26 15:20:22','b4d7a93a-0153-462b-bf1b-2032d8cefd56','ZGVmYXVsdC1zdG9yZQ==-5e0331b9-5f4d-4786-a276-fe133e2c2b43',NULL,0,0,'P',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,NULL,NULL,1,1,NULL,NULL),(1,97,'2016-01-26 16:05:29','2016-01-26 16:05:29','5f705130-e30c-4e78-9c03-a72f75d4e86e','ZGVmYXVsdC1zdG9yZQ==-914b53b5-343f-480f-bc80-84a9c74645a5',NULL,0,0,'P',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,NULL,NULL,1,1,NULL,NULL),(1,98,'2016-01-26 16:06:03','2016-01-26 16:07:42','41248480-6307-426a-ae43-3fffbabe78eb','ZGVmYXVsdC1zdG9yZQ==-4ae11b41-c21f-488d-979d-7f4749ea9523',NULL,0,0,'P',0,0,0,NULL,0,0,NULL,NULL,NULL,1,4,NULL,NULL,1,4,NULL,NULL),(1,99,'2016-01-26 16:34:57','2016-01-26 16:34:57','20ae986c-195c-48c4-b1d4-8ef8540599ec','ZGVmYXVsdC1zdG9yZQ==-eba9e531-b066-4f6b-b0ec-ad2968e51019',NULL,0,0,'P',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,NULL,NULL,1,1,NULL,NULL),(1,100,'2016-01-26 17:39:19','2016-01-26 17:39:30','c40d3db8-8b13-4c24-bbee-8a4c2ad92fc3','ZGVmYXVsdC1zdG9yZQ==-4633eebc-482f-4573-a1de-c7ecc93bd8d3',NULL,0,0,'P',0,0,0,NULL,0,0,NULL,NULL,86,1,1,NULL,87,1,1,NULL,NULL),(1,101,'2016-01-27 10:25:51','2016-01-27 10:26:45','2da43730-be74-432a-9915-cff25489d103','ZGVmYXVsdC1zdG9yZQ==-9c7b181c-2b21-4c01-b257-1deba1dfc97e',NULL,0,0,'P',0,0,0,NULL,0,0,NULL,NULL,90,1,1,NULL,91,1,1,NULL,NULL);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders_attribute`
--

DROP TABLE IF EXISTS `orders_attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders_attribute` (
  `attribute_id` bigint(20) NOT NULL,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `base_attributes_id` decimal(19,2) NOT NULL,
  `sequence` double NOT NULL,
  `attributeType` int(11) DEFAULT NULL,
  `dateValue` date DEFAULT NULL,
  `doubleValue` double DEFAULT NULL,
  `integerValue` int(11) DEFAULT NULL,
  `stringValue` varchar(255) DEFAULT NULL,
  `orders_id` bigint(20) NOT NULL,
  PRIMARY KEY (`attribute_id`),
  KEY `FK1E296DC2814345F9` (`orders_id`),
  CONSTRAINT `FK1E296DC2814345F9` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders_attribute`
--

LOCK TABLES `orders_attribute` WRITE;
/*!40000 ALTER TABLE `orders_attribute` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders_attribute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paymethod`
--

DROP TABLE IF EXISTS `paymethod`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paymethod` (
  `paymethod_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description_stringid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`paymethod_id`),
  KEY `FKFADA6389D37DBE0F` (`description_stringid`),
  CONSTRAINT `FKFADA6389D37DBE0F` FOREIGN KEY (`description_stringid`) REFERENCES `multilingual_string` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paymethod`
--

LOCK TABLES `paymethod` WRITE;
/*!40000 ALTER TABLE `paymethod` DISABLE KEYS */;
/*!40000 ALTER TABLE `paymethod` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paymethodlookup`
--

DROP TABLE IF EXISTS `paymethodlookup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paymethodlookup` (
  `paymethod_id` bigint(20) NOT NULL,
  `store_id` bigint(20) NOT NULL,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`paymethod_id`,`store_id`),
  KEY `FK61582436BDD0612` (`paymethod_id`),
  KEY `FK61582435CF8B8DC` (`store_id`),
  CONSTRAINT `FK61582435CF8B8DC` FOREIGN KEY (`store_id`) REFERENCES `stores` (`store_id`),
  CONSTRAINT `FK61582436BDD0612` FOREIGN KEY (`paymethod_id`) REFERENCES `paymethod` (`paymethod_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paymethodlookup`
--

LOCK TABLES `paymethodlookup` WRITE;
/*!40000 ALTER TABLE `paymethodlookup` DISABLE KEYS */;
/*!40000 ALTER TABLE `paymethodlookup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pricelist`
--

DROP TABLE IF EXISTS `pricelist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pricelist` (
  `pricelist_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `defaultList` tinyint(1) NOT NULL,
  `name` varchar(100) NOT NULL,
  `catalog_id` bigint(20) NOT NULL,
  `description_string_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`pricelist_id`),
  KEY `FK815695671A37F5F8` (`catalog_id`),
  KEY `FK81569567F30827C6` (`description_string_id`),
  CONSTRAINT `FK815695671A37F5F8` FOREIGN KEY (`catalog_id`) REFERENCES `catalog` (`catalog_id`),
  CONSTRAINT `FK81569567F30827C6` FOREIGN KEY (`description_string_id`) REFERENCES `multilingual_string` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pricelist`
--

LOCK TABLES `pricelist` WRITE;
/*!40000 ALTER TABLE `pricelist` DISABLE KEYS */;
INSERT INTO `pricelist` (`pricelist_id`, `created`, `updated`, `uuid`, `defaultList`, `name`, `catalog_id`, `description_string_id`) VALUES (1,'2016-01-12 12:32:52','2016-01-12 12:32:52','6224cd79-acf1-460c-946c-e729d891ec13',1,'default-list',1,1);
/*!40000 ALTER TABLE `pricelist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prices`
--

DROP TABLE IF EXISTS `prices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prices` (
  `prices_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `current_price` double DEFAULT NULL,
  `min_qty` double DEFAULT NULL,
  `precedence` double DEFAULT NULL,
  `product_cost` double DEFAULT NULL,
  `product_price` double DEFAULT NULL,
  `valid_from` datetime DEFAULT NULL,
  `valid_to` datetime DEFAULT NULL,
  `catalog_id` bigint(20) NOT NULL,
  `currency_id` bigint(20) NOT NULL,
  `pricelist_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) NOT NULL,
  PRIMARY KEY (`prices_id`),
  KEY `FKC596784A1A37F5F8` (`catalog_id`),
  KEY `FKC596784A6C1E9E18` (`currency_id`),
  KEY `FKC596784A15E50BB8` (`pricelist_id`),
  KEY `FKC596784ACCF1BE38` (`product_id`),
  CONSTRAINT `FKC596784A15E50BB8` FOREIGN KEY (`pricelist_id`) REFERENCES `pricelist` (`pricelist_id`),
  CONSTRAINT `FKC596784A1A37F5F8` FOREIGN KEY (`catalog_id`) REFERENCES `catalog` (`catalog_id`),
  CONSTRAINT `FKC596784A6C1E9E18` FOREIGN KEY (`currency_id`) REFERENCES `currency` (`currency_id`),
  CONSTRAINT `FKC596784ACCF1BE38` FOREIGN KEY (`product_id`) REFERENCES `abstractProducts` (`ctentry_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prices`
--

LOCK TABLES `prices` WRITE;
/*!40000 ALTER TABLE `prices` DISABLE KEYS */;
INSERT INTO `prices` (`prices_id`, `created`, `updated`, `uuid`, `current_price`, `min_qty`, `precedence`, `product_cost`, `product_price`, `valid_from`, `valid_to`, `catalog_id`, `currency_id`, `pricelist_id`, `product_id`) VALUES (1,'2016-01-12 15:17:11','2016-01-12 15:17:35','61ec54e4-41dd-476d-ba17-134a10144ef2',12,NULL,0,12,12,NULL,NULL,1,1,1,3);
/*!40000 ALTER TABLE `prices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productffmt`
--

DROP TABLE IF EXISTS `productffmt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productffmt` (
  `pricelist_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `available_date` date DEFAULT NULL,
  `available_in_order` double DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`pricelist_id`,`product_id`),
  KEY `FKA71FA7D615E50BB8` (`pricelist_id`),
  KEY `FKA71FA7D6CCF1BE38` (`product_id`),
  CONSTRAINT `FKA71FA7D615E50BB8` FOREIGN KEY (`pricelist_id`) REFERENCES `pricelist` (`pricelist_id`),
  CONSTRAINT `FKA71FA7D6CCF1BE38` FOREIGN KEY (`product_id`) REFERENCES `abstractProducts` (`ctentry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productffmt`
--

LOCK TABLES `productffmt` WRITE;
/*!40000 ALTER TABLE `productffmt` DISABLE KEYS */;
/*!40000 ALTER TABLE `productffmt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `regions`
--

DROP TABLE IF EXISTS `regions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `regions` (
  `regions_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `countries_id` bigint(20) NOT NULL,
  `description_stringid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`regions_id`),
  KEY `FK40BCB7FFB17C185E` (`countries_id`),
  KEY `FK40BCB7FFD37DBE0F` (`description_stringid`),
  CONSTRAINT `FK40BCB7FFB17C185E` FOREIGN KEY (`countries_id`) REFERENCES `countries` (`countries_id`),
  CONSTRAINT `FK40BCB7FFD37DBE0F` FOREIGN KEY (`description_stringid`) REFERENCES `multilingual_string` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regions`
--

LOCK TABLES `regions` WRITE;
/*!40000 ALTER TABLE `regions` DISABLE KEYS */;
/*!40000 ALTER TABLE `regions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `description` varchar(254) DEFAULT NULL,
  `name` varchar(80) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`role_id`, `created`, `updated`, `uuid`, `description`, `name`) VALUES (1,'2016-01-12 12:32:51','2016-01-12 12:32:51','d2484a78-1ba3-4623-9b55-5db588f5d483',NULL,'employee'),(2,'2016-01-12 12:32:51','2016-01-12 12:32:51','a221332d-7675-4ace-8223-510a93f88f93',NULL,'administrator');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipmode`
--

DROP TABLE IF EXISTS `shipmode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shipmode` (
  `shipmode_id` bigint(20) NOT NULL,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `description` longtext,
  `name` varchar(100) DEFAULT NULL,
  `store_id` bigint(20) NOT NULL,
  `name_string_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`shipmode_id`),
  KEY `FK_99708ae6c2e448a8a154df50244` (`store_id`),
  KEY `FK_1b557fd5099b4610a8aae461493` (`name_string_id`),
  CONSTRAINT `FK_1b557fd5099b4610a8aae461493` FOREIGN KEY (`name_string_id`) REFERENCES `multilingual_string` (`Id`),
  CONSTRAINT `FK_99708ae6c2e448a8a154df50244` FOREIGN KEY (`store_id`) REFERENCES `stores` (`store_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipmode`
--

LOCK TABLES `shipmode` WRITE;
/*!40000 ALTER TABLE `shipmode` DISABLE KEYS */;
/*!40000 ALTER TABLE `shipmode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipmodelookup`
--

DROP TABLE IF EXISTS `shipmodelookup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shipmodelookup` (
  `shipmodelookup_id` bigint(20) NOT NULL,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `zipcode` varchar(30) DEFAULT NULL,
  `country_id` bigint(20) DEFAULT NULL,
  `geocode_id` bigint(20) DEFAULT NULL,
  `regions_id` bigint(20) DEFAULT NULL,
  `shipmode_id` bigint(20) NOT NULL,
  `store_id` bigint(20) NOT NULL,
  PRIMARY KEY (`shipmodelookup_id`),
  KEY `FK261F1C193781983C` (`country_id`),
  KEY `FK261F1C1941A7A73C` (`geocode_id`),
  KEY `FK261F1C19641D45CD` (`regions_id`),
  KEY `FK261F1C1946710562` (`shipmode_id`),
  KEY `FK261F1C195CF8B8DC` (`store_id`),
  CONSTRAINT `FK261F1C193781983C` FOREIGN KEY (`country_id`) REFERENCES `countries` (`countries_id`),
  CONSTRAINT `FK261F1C1941A7A73C` FOREIGN KEY (`geocode_id`) REFERENCES `geocode` (`geocode_id`),
  CONSTRAINT `FK261F1C1946710562` FOREIGN KEY (`shipmode_id`) REFERENCES `shipmode` (`shipmode_id`),
  CONSTRAINT `FK261F1C195CF8B8DC` FOREIGN KEY (`store_id`) REFERENCES `stores` (`store_id`),
  CONSTRAINT `FK261F1C19641D45CD` FOREIGN KEY (`regions_id`) REFERENCES `regions` (`regions_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipmodelookup`
--

LOCK TABLES `shipmodelookup` WRITE;
/*!40000 ALTER TABLE `shipmodelookup` DISABLE KEYS */;
/*!40000 ALTER TABLE `shipmodelookup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipping`
--

DROP TABLE IF EXISTS `shipping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shipping` (
  `shipping_id` bigint(20) NOT NULL,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `rangestart` double DEFAULT NULL,
  `value` double DEFAULT NULL,
  `currency_id` bigint(20) NOT NULL,
  `shipmode_id` bigint(20) NOT NULL,
  PRIMARY KEY (`shipping_id`),
  KEY `FKE13ADDAE6C1E9E18` (`currency_id`),
  KEY `FKE13ADDAE46710562` (`shipmode_id`),
  CONSTRAINT `FKE13ADDAE46710562` FOREIGN KEY (`shipmode_id`) REFERENCES `shipmode` (`shipmode_id`),
  CONSTRAINT `FKE13ADDAE6C1E9E18` FOREIGN KEY (`currency_id`) REFERENCES `currency` (`currency_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipping`
--

LOCK TABLES `shipping` WRITE;
/*!40000 ALTER TABLE `shipping` DISABLE KEYS */;
/*!40000 ALTER TABLE `shipping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stores`
--

DROP TABLE IF EXISTS `stores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stores` (
  `store_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `name` varchar(250) NOT NULL,
  `currency_id` bigint(20) NOT NULL,
  `taxType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`store_id`),
  KEY `FKCAD423B26C1E9E18` (`currency_id`),
  CONSTRAINT `FKCAD423B26C1E9E18` FOREIGN KEY (`currency_id`) REFERENCES `currency` (`currency_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stores`
--

LOCK TABLES `stores` WRITE;
/*!40000 ALTER TABLE `stores` DISABLE KEYS */;
INSERT INTO `stores` (`store_id`, `created`, `updated`, `uuid`, `name`, `currency_id`, `taxType`) VALUES (1,'2016-01-12 12:32:51','2016-01-12 12:32:51','0c20310d-685f-47f6-b304-f664aa0278e4','default-store',1,NULL),(2,'2016-01-12 12:32:51','2016-01-12 12:32:51','e10c232a-391f-4e42-a77d-1057c47bddfd','test-store',1,NULL);
/*!40000 ALTER TABLE `stores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suborders`
--

DROP TABLE IF EXISTS `suborders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `suborders` (
  `suborders_id` bigint(20) NOT NULL,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `customer_id` bigint(20) NOT NULL,
  `discount_amount` decimal(10,4) DEFAULT NULL,
  `discount_perc` decimal(10,4) DEFAULT NULL,
  `shipping_address_id` decimal(19,2) NOT NULL,
  `status` varchar(2) NOT NULL,
  `subtotal_amount` decimal(10,4) NOT NULL,
  `subtotal_product` decimal(10,4) NOT NULL,
  `subtotal_shipping` decimal(10,4) DEFAULT NULL,
  `subtotal_tax` decimal(10,4) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  `orders_id` bigint(20) DEFAULT NULL,
  `shipmode_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`suborders_id`),
  KEY `FK18A97125814345F9` (`orders_id`),
  KEY `FK18A9712546710562` (`shipmode_id`),
  CONSTRAINT `FK18A9712546710562` FOREIGN KEY (`shipmode_id`) REFERENCES `shipmode` (`shipmode_id`),
  CONSTRAINT `FK18A97125814345F9` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suborders`
--

LOCK TABLES `suborders` WRITE;
/*!40000 ALTER TABLE `suborders` DISABLE KEYS */;
/*!40000 ALTER TABLE `suborders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taxes`
--

DROP TABLE IF EXISTS `taxes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `taxes` (
  `taxes_id` bigint(20) NOT NULL,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `rate` double DEFAULT NULL,
  PRIMARY KEY (`taxes_id`),
  UNIQUE KEY `UK_79054d6d92444353b3eed26b47c` (`uuid`),
  UNIQUE KEY `UK_32d7654936ee4e0cb0ef625a05a` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taxes`
--

LOCK TABLES `taxes` WRITE;
/*!40000 ALTER TABLE `taxes` DISABLE KEYS */;
/*!40000 ALTER TABLE `taxes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taxeslookup`
--

DROP TABLE IF EXISTS `taxeslookup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `taxeslookup` (
  `paymethod_id` bigint(20) NOT NULL,
  `store_id` bigint(20) NOT NULL,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `active` tinyint(1) NOT NULL,
  `taxes_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`paymethod_id`,`store_id`),
  UNIQUE KEY `UK_5d7cb6a0457a497b968e5a9b754` (`uuid`),
  KEY `FK_b2f86e50e2b24d889d06aeb59ec` (`store_id`),
  KEY `FK_b5f6e22a5389481fb7483d3b03b` (`taxes_id`),
  CONSTRAINT `FK_b2f86e50e2b24d889d06aeb59ec` FOREIGN KEY (`store_id`) REFERENCES `stores` (`store_id`),
  CONSTRAINT `FK_b5f6e22a5389481fb7483d3b03b` FOREIGN KEY (`taxes_id`) REFERENCES `taxes` (`taxes_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taxeslookup`
--

LOCK TABLES `taxeslookup` WRITE;
/*!40000 ALTER TABLE `taxeslookup` DISABLE KEYS */;
/*!40000 ALTER TABLE `taxeslookup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user2storerel`
--

DROP TABLE IF EXISTS `user2storerel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user2storerel` (
  `user_id` bigint(20) NOT NULL,
  `store_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`store_id`),
  KEY `FK5955DEDF5CF8B8DC` (`store_id`),
  KEY `FK5955DEDF457DC5D` (`user_id`),
  CONSTRAINT `FK5955DEDF457DC5D` FOREIGN KEY (`user_id`) REFERENCES `users` (`member_id`),
  CONSTRAINT `FK5955DEDF5CF8B8DC` FOREIGN KEY (`store_id`) REFERENCES `stores` (`store_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user2storerel`
--

LOCK TABLES `user2storerel` WRITE;
/*!40000 ALTER TABLE `user2storerel` DISABLE KEYS */;
INSERT INTO `user2storerel` (`user_id`, `store_id`) VALUES (3,1),(4,1);
/*!40000 ALTER TABLE `user2storerel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user2userRel`
--

DROP TABLE IF EXISTS `user2userRel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user2userRel` (
  `child_member_id` bigint(20) NOT NULL,
  `parent_member_id` bigint(20) NOT NULL,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `field1` varchar(250) DEFAULT NULL,
  `field2` varchar(200) DEFAULT NULL,
  `field3` decimal(10,4) DEFAULT NULL,
  `precedence` double NOT NULL,
  `relation_type` int(11) NOT NULL,
  PRIMARY KEY (`child_member_id`,`parent_member_id`),
  KEY `FKF957127C257D3CB` (`child_member_id`),
  KEY `FKF95712734BE4DD9` (`parent_member_id`),
  CONSTRAINT `FKF95712734BE4DD9` FOREIGN KEY (`parent_member_id`) REFERENCES `users` (`member_id`),
  CONSTRAINT `FKF957127C257D3CB` FOREIGN KEY (`child_member_id`) REFERENCES `users` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user2userRel`
--

LOCK TABLES `user2userRel` WRITE;
/*!40000 ALTER TABLE `user2userRel` DISABLE KEYS */;
/*!40000 ALTER TABLE `user2userRel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `email` varchar(254) DEFAULT NULL,
  `field4` int(11) DEFAULT NULL,
  `firstname` varchar(200) DEFAULT NULL,
  `last_visit` datetime DEFAULT NULL,
  `lastname` varchar(200) DEFAULT NULL,
  `phone` varchar(25) DEFAULT NULL,
  `user_type` varchar(255) NOT NULL,
  `member_id` bigint(20) NOT NULL,
  PRIMARY KEY (`member_id`),
  KEY `FK6A68E087B32A13D` (`member_id`),
  CONSTRAINT `FK6A68E087B32A13D` FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`email`, `field4`, `firstname`, `last_visit`, `lastname`, `phone`, `user_type`, `member_id`) VALUES (NULL,NULL,NULL,NULL,'Anonymous',NULL,'ANONYMOUS',1),(NULL,NULL,NULL,'2016-01-20 17:05:10','Superuser',NULL,'SUPERSUSER',2),('fpicinelli@stepfour.it',NULL,'Fede','2016-01-26 16:06:02','Pic',NULL,'REGISTERED',3),('gabri@gabri.it',NULL,'gabri','2016-01-26 16:07:42','gabri',NULL,'REGISTERED',4);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_reg`
--

DROP TABLE IF EXISTS `users_reg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_reg` (
  `alternate_email` varchar(100) DEFAULT NULL,
  `changeanswer` varchar(254) DEFAULT NULL,
  `changequestion` varchar(254) DEFAULT NULL,
  `dn` varchar(254) DEFAULT NULL,
  `last_login` datetime DEFAULT NULL,
  `logonid` varchar(100) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `password_change` datetime DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `member_id` bigint(20) NOT NULL,
  `locale_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`member_id`),
  KEY `FK154D31DD893374F8` (`locale_id`),
  KEY `FK154D31DD5D1D972E` (`member_id`),
  CONSTRAINT `FK154D31DD5D1D972E` FOREIGN KEY (`member_id`) REFERENCES `users` (`member_id`),
  CONSTRAINT `FK154D31DD893374F8` FOREIGN KEY (`locale_id`) REFERENCES `locale` (`locale_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_reg`
--

LOCK TABLES `users_reg` WRITE;
/*!40000 ALTER TABLE `users_reg` DISABLE KEYS */;
INSERT INTO `users_reg` (`alternate_email`, `changeanswer`, `changequestion`, `dn`, `last_login`, `logonid`, `password`, `password_change`, `status`, `member_id`, `locale_id`) VALUES (NULL,NULL,NULL,NULL,'2016-01-20 17:05:10','superuser','admin',NULL,'ACTIVE',2,NULL),(NULL,NULL,NULL,NULL,'2016-01-26 16:06:02','fedepic','123456',NULL,'ACTIVE',3,NULL),(NULL,NULL,NULL,NULL,'2016-01-26 16:07:42','gabri','gabriele',NULL,'ACTIVE',4,NULL);
/*!40000 ALTER TABLE `users_reg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warehouse`
--

DROP TABLE IF EXISTS `warehouse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `warehouse` (
  `warehouse_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `inventoryThreshold` double DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `precedence` double NOT NULL,
  `description_string_id` bigint(20) DEFAULT NULL,
  `store_id` bigint(20) DEFAULT NULL,
  `forceInStock` tinyint(1) NOT NULL,
  PRIMARY KEY (`warehouse_id`),
  KEY `FK88EF3AC3F30827C6` (`description_string_id`),
  KEY `FK88EF3AC35CF8B8DC` (`store_id`),
  CONSTRAINT `FK88EF3AC35CF8B8DC` FOREIGN KEY (`store_id`) REFERENCES `stores` (`store_id`),
  CONSTRAINT `FK88EF3AC3F30827C6` FOREIGN KEY (`description_string_id`) REFERENCES `multilingual_string` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warehouse`
--

LOCK TABLES `warehouse` WRITE;
/*!40000 ALTER TABLE `warehouse` DISABLE KEYS */;
/*!40000 ALTER TABLE `warehouse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warehouse_config`
--

DROP TABLE IF EXISTS `warehouse_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `warehouse_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `alwaysInstock` tinyint(1) NOT NULL,
  `storeThreshold` double DEFAULT NULL,
  `store_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKD190343E5CF8B8DC` (`store_id`),
  CONSTRAINT `FKD190343E5CF8B8DC` FOREIGN KEY (`store_id`) REFERENCES `stores` (`store_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warehouse_config`
--

LOCK TABLES `warehouse_config` WRITE;
/*!40000 ALTER TABLE `warehouse_config` DISABLE KEYS */;
/*!40000 ALTER TABLE `warehouse_config` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-01-27 17:38:57
