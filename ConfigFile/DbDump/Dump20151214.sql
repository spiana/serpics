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
-- Table structure for table `abstractproducts`
--

DROP TABLE IF EXISTS `abstractproducts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `abstractproducts` (
  `buyable` int(11) NOT NULL,
  `downlodable` int(11) NOT NULL,
  `manufacturer_sku` varchar(255) DEFAULT NULL,
  `meta_description` varchar(255) DEFAULT NULL,
  `meta_keyword` varchar(255) DEFAULT NULL,
  `product_type` int(11) NOT NULL,
  `published` int(11) NOT NULL,
  `unit_meas` varchar(255) DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `weight_meas` varchar(255) DEFAULT NULL,
  `ctentry_id` bigint(20) NOT NULL,
  `brand_id` bigint(20) DEFAULT NULL,
  `catalog_id` bigint(20) NOT NULL,
  `featuremodel_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ctentry_id`),
  KEY `FKAF76E3A665B636B8` (`brand_id`),
  KEY `FKAF76E3A61A37F5F8` (`catalog_id`),
  KEY `FKAF76E3A650ECCC9C` (`featuremodel_id`),
  KEY `FKAF76E3A6E24388F8` (`ctentry_id`),
  CONSTRAINT `FKAF76E3A61A37F5F8` FOREIGN KEY (`catalog_id`) REFERENCES `catalog` (`catalog_id`),
  CONSTRAINT `FKAF76E3A650ECCC9C` FOREIGN KEY (`featuremodel_id`) REFERENCES `featuremodel` (`specification_id`),
  CONSTRAINT `FKAF76E3A665B636B8` FOREIGN KEY (`brand_id`) REFERENCES `brands` (`brand_id`),
  CONSTRAINT `FKAF76E3A6E24388F8` FOREIGN KEY (`ctentry_id`) REFERENCES `ctentry` (`ctentry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `abstractproducts`
--

LOCK TABLES `abstractproducts` WRITE;
/*!40000 ALTER TABLE `abstractproducts` DISABLE KEYS */;
INSERT INTO `abstractproducts` VALUES (1,0,NULL,NULL,NULL,2,0,NULL,NULL,NULL,5,10,1,NULL),(1,0,NULL,NULL,NULL,2,0,NULL,NULL,NULL,6,10,1,NULL),(1,0,NULL,NULL,NULL,2,0,NULL,NULL,NULL,7,10,1,NULL),(1,0,NULL,NULL,NULL,2,0,NULL,NULL,NULL,8,7,1,NULL),(1,0,NULL,NULL,NULL,2,0,NULL,NULL,NULL,772,7,1,NULL),(1,0,NULL,NULL,NULL,2,0,NULL,NULL,NULL,773,7,1,NULL),(1,0,NULL,NULL,NULL,2,0,NULL,NULL,NULL,774,7,1,NULL),(1,0,NULL,NULL,NULL,2,0,NULL,NULL,NULL,775,7,1,NULL),(1,0,NULL,NULL,NULL,2,0,NULL,NULL,NULL,776,6,1,NULL),(1,0,NULL,NULL,NULL,2,0,NULL,NULL,NULL,777,6,1,NULL),(1,0,NULL,NULL,NULL,2,0,NULL,NULL,NULL,778,6,1,NULL),(1,0,NULL,NULL,NULL,2,0,NULL,NULL,NULL,779,7,1,NULL),(1,0,NULL,NULL,NULL,2,0,NULL,NULL,NULL,781,NULL,1,NULL);
/*!40000 ALTER TABLE `abstractproducts` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `addresses`
--

LOCK TABLES `addresses` WRITE;
/*!40000 ALTER TABLE `addresses` DISABLE KEYS */;
INSERT INTO `addresses` VALUES ('PRIMARY',1,'2015-11-16 17:00:20','2015-11-16 17:00:20','624f4c78-4e8a-436e-a60e-35c10a636d79',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4),('PRIMARY',2,'2015-12-10 15:17:27','2015-12-10 15:17:27','d00f6922-03d2-49e1-8dea-705d5a813fff',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,5),('PRIMARY',3,'2015-12-10 15:19:07','2015-12-10 15:19:07','86d64d2c-76c6-44fd-9d83-929458112f89',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,6),('PRIMARY',4,'2015-12-10 15:22:42','2015-12-10 15:22:42','65843b22-3071-4208-bdea-c5b47ffe95d0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,7),('PRIMARY',5,'2015-12-10 15:23:59','2015-12-10 15:23:59','bdd20f73-f80a-4c0a-b4f5-183b45c8f0ce',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,8),('PRIMARY',6,'2015-12-10 15:46:32','2015-12-10 15:46:32','be179868-47fc-45a8-b75b-68b3c7affa14',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,9),('PRIMARY',7,'2015-12-10 16:17:43','2015-12-10 16:17:43','89b4ed81-15f8-4e72-8e87-38174db15af0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,10),('PRIMARY',8,'2015-12-11 11:03:42','2015-12-11 11:03:42','f12f39d1-8399-45f1-85c1-b50b84ec3419',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,11),('PRIMARY',9,'2015-12-14 11:37:16','2015-12-14 11:37:16','f93d541b-06a4-4d0e-974c-c234d7e8344d',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,12),('PRIMARY',10,'2015-12-14 17:18:56','2015-12-14 17:18:56','25b9acd7-5499-4639-8702-137e366c32a9',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,13);
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
  `brand_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `logo_src` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `catalog_id` bigint(20) NOT NULL,
  `published` int(11) DEFAULT NULL,
  PRIMARY KEY (`brand_id`),
  KEY `FKADAF25CC1A37F5F8` (`catalog_id`),
  CONSTRAINT `FKADAF25CC1A37F5F8` FOREIGN KEY (`catalog_id`) REFERENCES `catalog` (`catalog_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brands`
--

LOCK TABLES `brands` WRITE;
/*!40000 ALTER TABLE `brands` DISABLE KEYS */;
INSERT INTO `brands` VALUES (1,'2015-11-16 17:13:41','2015-11-16 17:13:41','3b2e4404-6ab2-43cb-97e0-148074abb9a5','Logo','PHILIPHS',1,1),(2,'2015-11-16 17:14:02','2015-11-16 17:14:02','bde83c7b-5503-4f4c-8c63-8b56540db517','Logos','MICROMICRO',1,1),(3,'2015-11-16 17:14:12','2015-11-16 17:14:12','bf05b880-5645-4821-9877-5148b6d808cc','Logos','MULTITOUCH',1,1),(4,'2015-11-16 17:01:58','2015-11-16 17:01:58','668a270b-d951-45ee-8d4e-9b4c8f716f0a','Logo','SAMSUNG',1,1),(5,'2015-11-16 17:01:58','2015-11-16 17:01:58','668a270b-d951-45ee-8d4e-9b4c8f716f0a','Logo','APPLE',1,1),(6,'2015-11-16 17:01:58','2015-11-16 17:01:58','668a270b-d951-45ee-8d4e-9b4c8f716f0a','Logo','NOKIA',1,1),(7,'2015-11-16 17:01:58','2015-11-16 17:01:58','668a270b-d951-45ee-8d4e-9b4c8f716f0a','Logo','TOSHIBA',1,1),(8,'2015-11-16 17:01:58','2015-11-16 17:01:58','668a270b-d951-45ee-8d4e-9b4c8f716f0a','Logo','LG',1,1),(9,'2015-11-16 17:01:58','2015-11-16 17:01:58','668a270b-d951-45ee-8d4e-9b4c8f716f0a','Logo','ASUS',1,1),(10,'2015-11-16 17:01:58','2015-11-16 17:01:58','668a270b-d951-45ee-8d4e-9b4c8f716f0a','Logo','LOGITECH',1,1);
/*!40000 ALTER TABLE `brands` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bundle`
--

DROP TABLE IF EXISTS `bundle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bundle` (
  `ctentry_id` bigint(20) NOT NULL,
  PRIMARY KEY (`ctentry_id`),
  KEY `FK77441CA241AE1AC4` (`ctentry_id`),
  CONSTRAINT `FK77441CA241AE1AC4` FOREIGN KEY (`ctentry_id`) REFERENCES `abstractproducts` (`ctentry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bundle`
--

LOCK TABLES `bundle` WRITE;
/*!40000 ALTER TABLE `bundle` DISABLE KEYS */;
/*!40000 ALTER TABLE `bundle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `catalog`
--

DROP TABLE IF EXISTS `catalog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `catalog` (
  `published` smallint(6) NOT NULL,
  `catalog_id` bigint(20) NOT NULL,
  PRIMARY KEY (`catalog_id`),
  KEY `FK211F601939D6B080` (`catalog_id`),
  CONSTRAINT `FK211F601939D6B080` FOREIGN KEY (`catalog_id`) REFERENCES `ctentry` (`ctentry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalog`
--

LOCK TABLES `catalog` WRITE;
/*!40000 ALTER TABLE `catalog` DISABLE KEYS */;
INSERT INTO `catalog` VALUES (1,1);
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
  `ctentry_id` bigint(20) NOT NULL,
  `catalog_id` bigint(20) NOT NULL,
  `published` int(11) DEFAULT NULL,
  PRIMARY KEY (`ctentry_id`),
  KEY `FK302BCFE1A37F5F8` (`catalog_id`),
  KEY `FK302BCFEE24388F8` (`ctentry_id`),
  CONSTRAINT `FK302BCFE1A37F5F8` FOREIGN KEY (`catalog_id`) REFERENCES `catalog` (`catalog_id`),
  CONSTRAINT `FK302BCFEE24388F8` FOREIGN KEY (`ctentry_id`) REFERENCES `ctentry` (`ctentry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (NULL,NULL,2,1,1),(NULL,NULL,3,1,1),(NULL,NULL,4,1,1),(NULL,NULL,9,1,1),(NULL,NULL,10,1,1),(NULL,NULL,11,1,1),(NULL,NULL,12,1,1),(NULL,NULL,13,1,1),(NULL,NULL,14,1,1),(NULL,NULL,15,1,1),(NULL,NULL,16,1,1),(NULL,NULL,17,1,1),(NULL,NULL,18,1,1),(NULL,NULL,19,1,1),(NULL,NULL,20,1,1),(NULL,NULL,21,1,1),(NULL,NULL,22,1,1),(NULL,NULL,23,1,1),(NULL,NULL,24,1,1),(NULL,NULL,25,1,1),(NULL,NULL,26,1,1),(NULL,NULL,780,1,0);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoryproductrelation`
--

DROP TABLE IF EXISTS `categoryproductrelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoryproductrelation` (
  `ctentry_id_child` bigint(20) NOT NULL,
  `ctentry_id_parent` bigint(20) NOT NULL,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `field1` varchar(255) DEFAULT NULL,
  `fiedl2` decimal(19,2) DEFAULT NULL,
  `relation_type` int(11) NOT NULL,
  `sequence` double NOT NULL,
  PRIMARY KEY (`ctentry_id_child`,`ctentry_id_parent`),
  KEY `FKAC7B430DD62F3421` (`ctentry_id_child`),
  KEY `FKAC7B430DA39D8610` (`ctentry_id_parent`),
  CONSTRAINT `FKAC7B430DA39D8610` FOREIGN KEY (`ctentry_id_parent`) REFERENCES `category` (`ctentry_id`),
  CONSTRAINT `FKAC7B430DD62F3421` FOREIGN KEY (`ctentry_id_child`) REFERENCES `abstractproducts` (`ctentry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoryproductrelation`
--

LOCK TABLES `categoryproductrelation` WRITE;
/*!40000 ALTER TABLE `categoryproductrelation` DISABLE KEYS */;
INSERT INTO `categoryproductrelation` VALUES (5,19,'2015-11-20 11:40:48','2015-11-20 11:40:48','b4ddf8b0-9938-41f2-a080-a3dd91c901ee',NULL,NULL,1,0),(6,19,'2015-11-20 11:40:25','2015-11-20 11:40:25','00f3c979-a1d5-45c8-a34a-fa124d17a4a2',NULL,NULL,1,0),(7,780,'2015-11-20 11:39:52','2015-11-20 11:39:52','361cc914-9398-4b1e-b806-d99225c4abdf',NULL,NULL,1,0),(8,2,'2015-11-16 17:19:48','2015-11-16 17:19:48','b22712da-6a45-4838-9f5e-88bf5340918e',NULL,NULL,1,0),(8,19,'2015-11-20 11:32:42','2015-11-20 11:32:42','2d3f6065-8fd8-4610-a84d-2a430f79ab96',NULL,NULL,1,0),(772,19,'2015-11-19 11:36:35','2015-11-19 11:36:35','82bbb5c2-c99f-48b9-be2c-65c3de567cbd',NULL,NULL,1,0),(773,19,'2015-11-19 11:37:42','2015-11-19 11:37:42','01f50cc0-c2f9-432f-a59f-a825992388fd',NULL,NULL,1,0),(774,19,'2015-11-19 11:38:40','2015-11-19 11:38:40','b74d9ccd-da5a-4dc3-b0ec-c4283e1ec377',NULL,NULL,1,0),(774,780,'2015-11-20 11:31:37','2015-11-20 11:31:37','2a337841-b08e-461d-a09e-cde811db53f7',NULL,NULL,1,0),(775,19,'2015-11-19 11:39:08','2015-11-19 11:39:08','2e6c41ca-3a86-4e34-a7f6-eea94ad573b7',NULL,NULL,1,0),(776,19,'2015-11-19 11:39:20','2015-11-19 11:39:20','3e848dab-412a-45ed-b68c-157a6df191d9',NULL,NULL,1,0),(777,19,'2015-11-19 11:39:42','2015-11-19 11:39:42','a8589ef0-05b9-4123-a6a1-e165e8f200dc',NULL,NULL,1,0),(777,780,'2015-11-20 11:26:58','2015-11-20 11:26:58','932d5c97-5d87-44de-8ad2-7ed644d95b6a',NULL,NULL,1,0),(778,19,'2015-11-19 11:40:02','2015-11-19 11:40:02','b8ca5aca-02b7-4b23-92e9-9cde232cc197',NULL,NULL,1,0),(779,19,'2015-11-19 11:40:57','2015-11-19 11:40:57','9c68eb63-452d-40f6-ae21-75bb450f06a8',NULL,NULL,1,0);
/*!40000 ALTER TABLE `categoryproductrelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoryrelation`
--

DROP TABLE IF EXISTS `categoryrelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoryrelation` (
  `ctentry_id_child` bigint(20) NOT NULL,
  `ctentry_id_parent` bigint(20) NOT NULL,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `field1` varchar(255) DEFAULT NULL,
  `fiedl2` decimal(19,2) DEFAULT NULL,
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
-- Dumping data for table `categoryrelation`
--

LOCK TABLES `categoryrelation` WRITE;
/*!40000 ALTER TABLE `categoryrelation` DISABLE KEYS */;
INSERT INTO `categoryrelation` VALUES (2,3,'2015-11-16 17:37:23','2015-11-16 17:37:23','554c41e4-1332-4d37-9b8e-73fa02f7ccca',NULL,NULL,1,1),(2,19,'2015-11-16 16:11:58','2015-11-16 16:11:58','668a270b-d951-45ee-8d4e-9b4c8f716f0a',NULL,NULL,1,1),(3,16,'2015-11-16 18:16:03','2015-11-16 18:16:03','3d6cff4d-543a-492a-a9fa-74cf73906253',NULL,NULL,1,1),(9,16,'2015-11-16 18:16:03','2015-11-16 18:16:03','3d6cff4d-543a-492a-a9fa-74cf73906253',NULL,NULL,1,1),(10,16,'2015-11-27 09:56:54','2015-11-27 09:56:54','a0583c00-9d77-4ec1-8e82-f6193478847d',NULL,NULL,0,0),(11,19,'2015-11-16 18:16:03','2015-11-16 18:16:03','3d6cff4d-543a-492a-a9fa-74cf73906253',NULL,NULL,1,1),(12,19,'2015-11-16 18:16:03','2015-11-16 18:16:03','3d6cff4d-543a-492a-a9fa-74cf73906253',NULL,NULL,1,1),(13,17,'2015-11-16 18:16:03','2015-11-16 18:16:03','3d6cff4d-543a-492a-a9fa-74cf73906253',NULL,NULL,1,1),(14,17,'2015-11-16 18:16:03','2015-11-16 18:16:03','3d6cff4d-543a-492a-a9fa-74cf73906253',NULL,NULL,1,1),(15,17,'2015-11-16 18:16:03','2015-11-16 18:16:03','3d6cff4d-543a-492a-a9fa-74cf73906253',NULL,NULL,1,1),(16,17,'2015-11-16 18:16:03','2015-11-16 18:16:03','3d6cff4d-543a-492a-a9fa-74cf73906253',NULL,NULL,1,1),(21,20,'2015-11-16 18:16:03','2015-11-16 18:16:03','3d6cff4d-543a-492a-a9fa-74cf73906253',NULL,NULL,1,1),(25,19,'2015-11-16 18:16:03','2015-11-16 18:16:03','3d6cff4d-543a-492a-a9fa-74cf73906253',NULL,NULL,1,1),(26,19,'2015-11-16 18:16:03','2015-11-16 18:16:03','3d6cff4d-543a-492a-a9fa-74cf73906253',NULL,NULL,1,1);
/*!40000 ALTER TABLE `categoryrelation` ENABLE KEYS */;
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
  `description_string_id` bigint(20) DEFAULT NULL,
  `name_string_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ctentry_id`),
  KEY `FK40BE1AA1F30827C6` (`description_string_id`),
  KEY `FK40BE1AA1714A7795` (`name_string_id`),
  KEY `FK40BE1AA1F9EB4CA2` (`description_string_id`),
  CONSTRAINT `FK40BE1AA1714A7795` FOREIGN KEY (`name_string_id`) REFERENCES `multilingual_string` (`Id`),
  CONSTRAINT `FK40BE1AA1F30827C6` FOREIGN KEY (`description_string_id`) REFERENCES `multilingual_string` (`Id`),
  CONSTRAINT `FK40BE1AA1F9EB4CA2` FOREIGN KEY (`description_string_id`) REFERENCES `multilingual_text` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=782 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ctentry`
--

LOCK TABLES `ctentry` WRITE;
/*!40000 ALTER TABLE `ctentry` DISABLE KEYS */;
INSERT INTO `ctentry` VALUES (1,'2015-11-16 17:01:06','2015-11-16 17:01:06','160d8650-f8bd-4b81-a7d1-76234415c528','default-catalog',0,NULL,NULL,NULL,'/default-catalog',NULL,NULL),(2,'2015-11-16 17:11:58','2015-11-16 17:11:58','183e775c-9675-43ce-a1d8-cf5a83228c0e','CAVO HDMI',3,NULL,NULL,NULL,'/default-catalog/provacategoria1',NULL,NULL),(3,'2015-11-16 17:12:09','2015-11-16 17:12:09','efef6ed1-065a-45e3-8679-ff2812efac75','PUMA',1,NULL,NULL,NULL,'/default-catalog/provacategoria2',NULL,NULL),(4,'2015-11-16 17:12:17','2015-11-16 17:12:17','c4d5a364-4a07-4e9a-aa68-4fe5cd31c538','GUESS',1,NULL,NULL,NULL,'/default-catalog/provacategoria3',NULL,1),(5,'2015-11-16 17:12:45','2015-11-20 11:46:59','a0441cae-08ae-43b3-96a3-e7ce10cf13a5','TASTIERA',3,NULL,NULL,NULL,'/default-catalog/product/Tastiera',NULL,2),(6,'2015-11-16 17:12:59','2015-11-18 15:31:07','80513282-94b5-4708-988d-8ae5c1919d3d','MOUSE',3,NULL,NULL,NULL,'/default-catalog/product/Mouse',NULL,3),(7,'2015-11-16 17:13:23','2015-11-20 11:48:15','e09064d6-91b1-4a54-99e1-f4dcb6a3495d','PHONE',3,NULL,NULL,NULL,'/default-catalog/product/Phone',NULL,4),(8,'2015-11-16 17:18:02','2015-11-18 15:19:11','70ff79f3-a52b-4312-8fb7-ef7acf4cc948','MONITOR',3,NULL,NULL,NULL,'/default-catalog/product/Monitor',NULL,5),(9,'2015-11-16 17:46:46','2015-11-16 17:46:46','ebc9d22f-4a73-429f-90b4-95de3b566308','NIKE',1,NULL,NULL,NULL,'/default-catalog/NIKE',NULL,NULL),(10,'2015-11-16 17:50:12','2015-11-16 17:50:12','1132d208-34a3-4269-a960-c0d73b6ea829','ADIDAS',1,NULL,NULL,NULL,'/default-catalog/ADIDAS',NULL,NULL),(11,'2015-11-16 17:51:54','2015-11-16 17:51:54','5c664a14-5d5f-410c-bfc5-f3acf711e81b','TASTIERA',1,NULL,NULL,NULL,'/default-catalog/TASTIERA',NULL,NULL),(12,'2015-11-16 17:52:01','2015-11-16 17:52:01','791f1da9-c109-4032-884e-4cf99b6010ee','MONITOR',1,NULL,NULL,NULL,'/default-catalog/MONITOR',NULL,NULL),(13,'2015-11-16 17:52:13','2015-11-16 17:52:13','7a2f5cc1-5013-45ea-9f52-d9d315d8dd48','T-SHIRT NIKE',1,NULL,NULL,NULL,'/default-catalog/T-SHIRT NIKE',NULL,NULL),(14,'2015-11-16 17:52:21','2015-11-16 17:52:21','9fe2374d-d5bc-441c-be2c-a4cc9d4da06d','T-SHIRT ADIDAS',1,NULL,NULL,NULL,'/default-catalog/T-SHIRT ADIDAS',NULL,NULL),(15,'2015-11-16 17:52:31','2015-11-16 17:52:31','27736d94-9260-45b4-85c4-a91f9b875eec','T-SHIRT ASICS',1,NULL,NULL,NULL,'/default-catalog/T-SHIRT ASICS',NULL,NULL),(16,'2015-11-16 18:00:49','2015-11-16 18:00:49','a5871601-d74d-4525-a132-2cf9f2624d87','SPORTSWEAR',1,NULL,NULL,NULL,'/default-catalog/SPORTSWEAR',NULL,NULL),(17,'2015-11-16 18:01:25','2015-11-16 18:01:25','96ea6e5a-8c10-4e29-9f02-e7e74c887d15','MENS',1,NULL,NULL,NULL,'/default-catalog/MENS',NULL,NULL),(18,'2015-11-16 18:01:36','2015-11-16 18:01:36','12358ab9-0549-4d66-9f9f-fe5ba309aef3','WOMENS',1,NULL,NULL,NULL,'/default-catalog/WOMENS',NULL,NULL),(19,'2015-11-16 18:01:46','2015-11-16 18:01:46','3fd6597a-edac-4825-bed0-5245b9d81e42','COMPUTER',1,NULL,NULL,NULL,'/default-catalog/COMPUTER',NULL,NULL),(20,'2015-11-16 18:01:58','2015-11-16 18:01:58','86d6f1a7-e22c-4994-9916-124639bb31e1','PARFUM',1,NULL,NULL,NULL,'/default-catalog/PARFUM',NULL,NULL),(21,'2015-11-16 18:02:09','2015-11-16 18:02:09','182be3ad-b638-45f9-b66b-5418cb1dd4b7','LANCOME',1,NULL,NULL,NULL,'/default-catalog/LANCOME',NULL,NULL),(22,'2015-11-16 18:02:19','2015-11-16 18:02:19','9fefe1cf-3cbb-4565-a294-02d8ad25779c','LEGEA',1,NULL,NULL,NULL,'/default-catalog/LEGEA',NULL,NULL),(23,'2015-11-16 18:02:30','2015-11-16 18:02:30','5451fd28-4787-4d81-815e-972bbc76ab60','KIDS',1,NULL,NULL,NULL,'/default-catalog/KIDS',NULL,NULL),(24,'2015-11-16 18:02:47','2015-11-16 18:02:47','d15d64ea-e0dd-412c-9e36-17359886ce47','SURF',1,NULL,NULL,NULL,'/default-catalog/SURF',NULL,NULL),(25,'2015-11-16 18:04:40','2015-11-16 18:04:40','3a416060-6923-4401-a8aa-ed3501f4ff47','USB',3,NULL,NULL,NULL,'/default-catalog/USB',NULL,NULL),(26,'2015-11-16 18:04:55','2015-11-16 18:04:55','602a1789-87d7-4b5b-85f4-b05bff14eba9','HARD DISK',3,NULL,NULL,NULL,'/default-catalog/HARD DISK',NULL,NULL),(772,'2015-11-19 11:36:35','2015-11-19 11:36:35','0fccafd7-dd6a-4fce-971c-28fad55b2e6c','APPLE MAC PRO RETINA',3,NULL,NULL,NULL,'/default-catalog/product/APLLE MAC PRO RETINA',NULL,486),(773,'2015-11-19 11:37:42','2015-11-19 11:37:42','901fa3e1-37e6-4790-9944-1095448eaa94','NOTEBOOK',3,NULL,NULL,NULL,'/default-catalog/product/NOTEBOOK',NULL,487),(774,'2015-11-19 11:38:40','2015-11-19 11:38:40','24eb8df8-92f0-4a19-94f9-0c556cd24350','SAMSUNG NOTE IV',3,NULL,NULL,NULL,'/default-catalog/product/SAMSUNG NOTE IV',NULL,488),(775,'2015-11-19 11:39:07','2015-11-19 11:39:08','f4539f4b-6674-4150-98c0-e11f368d0d5f','CAVOD HDMI',3,NULL,NULL,NULL,'/default-catalog/product/CAVOD HDMI',NULL,489),(776,'2015-11-19 11:39:20','2015-11-19 11:39:20','a5348dd5-4b4e-43e2-8764-ec4bad25736d','USB HARD DISK',3,NULL,NULL,NULL,'/default-catalog/product/USB HARD DISK',NULL,490),(777,'2015-11-19 11:39:42','2015-11-19 11:39:42','ef3a86a1-63a9-40ab-a043-9fe907f44d19','NOKIA 662',3,NULL,NULL,NULL,'/default-catalog/product/NOKIA 662',NULL,491),(778,'2015-11-19 11:40:02','2015-11-19 11:40:02','11f8d1e7-1b37-4fda-b02f-05ad0dd57e6b','AURICOLARI NOKIA',3,NULL,NULL,NULL,'/default-catalog/product/AURICOLARI NOKIA',NULL,492),(779,'2015-11-19 11:40:57','2015-11-19 11:40:57','66d3545d-114e-4428-aab3-58345649f1df','TOSHIBA NOTEBOOK SATELLITE XJC.255',3,NULL,NULL,NULL,'/default-catalog/product/TOSHIBA NOTEBOOK SATELLITE XJC.255',NULL,493),(780,'2015-11-20 11:19:32','2015-11-20 11:19:32','554b81a5-7f05-484d-b245-d9e536861c98','MOBILE',1,NULL,NULL,NULL,'/default-catalog/MOBILE',NULL,NULL),(781,'2015-12-03 17:21:18','2015-12-03 17:21:18','d29cdcd0-a2a0-479e-84d8-dc32868ca432','provaprodotto1',3,NULL,NULL,NULL,'/default-catalog/product/provaprodotto1',NULL,494);
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
INSERT INTO `currency` VALUES (1,'2015-11-16 17:00:16','2015-11-16 17:00:16','acca9391-0149-4eb7-bdf3-fd39e14e6959','Euro','EUR');
/*!40000 ALTER TABLE `currency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fearuremodelrelation`
--

DROP TABLE IF EXISTS `fearuremodelrelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fearuremodelrelation` (
  `ctentry_id_child` bigint(20) NOT NULL,
  `ctentry_id_parent` bigint(20) NOT NULL,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `field1` varchar(255) DEFAULT NULL,
  `fiedl2` decimal(19,2) DEFAULT NULL,
  `relation_type` int(11) NOT NULL,
  `sequence` double NOT NULL,
  PRIMARY KEY (`ctentry_id_child`,`ctentry_id_parent`),
  KEY `FK26B14D0D1AA6CE8B` (`ctentry_id_child`),
  KEY `FK26B14D0D962481E5` (`ctentry_id_parent`),
  CONSTRAINT `FK26B14D0D1AA6CE8B` FOREIGN KEY (`ctentry_id_child`) REFERENCES `featuremodel` (`specification_id`),
  CONSTRAINT `FK26B14D0D962481E5` FOREIGN KEY (`ctentry_id_parent`) REFERENCES `featuremodel` (`specification_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fearuremodelrelation`
--

LOCK TABLES `fearuremodelrelation` WRITE;
/*!40000 ALTER TABLE `fearuremodelrelation` DISABLE KEYS */;
/*!40000 ALTER TABLE `fearuremodelrelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feature`
--

DROP TABLE IF EXISTS `feature`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feature` (
  `feature_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sequence` tinyint(1) DEFAULT NULL,
  `type` smallint(6) NOT NULL,
  `description_stringid` bigint(20) DEFAULT NULL,
  `featureGroup_id` bigint(20) DEFAULT NULL,
  `featureModel_id` bigint(20) NOT NULL,
  PRIMARY KEY (`feature_id`),
  KEY `FK28DB12D6D37DBE0F` (`description_stringid`),
  KEY `FK28DB12D693AE04DC` (`featureGroup_id`),
  KEY `FK28DB12D650ECCC9C` (`featureModel_id`),
  CONSTRAINT `FK28DB12D650ECCC9C` FOREIGN KEY (`featureModel_id`) REFERENCES `featuremodel` (`specification_id`),
  CONSTRAINT `FK28DB12D693AE04DC` FOREIGN KEY (`featureGroup_id`) REFERENCES `featuregroup` (`id`),
  CONSTRAINT `FK28DB12D6D37DBE0F` FOREIGN KEY (`description_stringid`) REFERENCES `multilingual_string` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feature`
--

LOCK TABLES `feature` WRITE;
/*!40000 ALTER TABLE `feature` DISABLE KEYS */;
/*!40000 ALTER TABLE `feature` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `featuregroup`
--

DROP TABLE IF EXISTS `featuregroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `featuregroup` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sequence` double NOT NULL,
  `description_string_id` bigint(20) DEFAULT NULL,
  `model_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK508B4949F30827C6` (`description_string_id`),
  KEY `FK508B494936A1A7A6` (`model_id`),
  CONSTRAINT `FK508B494936A1A7A6` FOREIGN KEY (`model_id`) REFERENCES `featuremodel` (`specification_id`),
  CONSTRAINT `FK508B4949F30827C6` FOREIGN KEY (`description_string_id`) REFERENCES `multilingual_string` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `featuregroup`
--

LOCK TABLES `featuregroup` WRITE;
/*!40000 ALTER TABLE `featuregroup` DISABLE KEYS */;
/*!40000 ALTER TABLE `featuregroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `featuremodel`
--

DROP TABLE IF EXISTS `featuremodel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `featuremodel` (
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
-- Dumping data for table `featuremodel`
--

LOCK TABLES `featuremodel` WRITE;
/*!40000 ALTER TABLE `featuremodel` DISABLE KEYS */;
/*!40000 ALTER TABLE `featuremodel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `featurevalues`
--

DROP TABLE IF EXISTS `featurevalues`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `featurevalues` (
  `featurevalue_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `dateValue` date DEFAULT NULL,
  `decimalValue` double DEFAULT NULL,
  `longValue` bigint(20) DEFAULT NULL,
  `feature_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `value_string_id` bigint(20) DEFAULT NULL,
  `text_string_id` bigint(20) DEFAULT NULL,
  `attributeType` int(11) DEFAULT NULL,
  `doubleValue` double DEFAULT NULL,
  `integerValue` int(11) DEFAULT NULL,
  `stringValue` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`featurevalue_id`),
  KEY `FKD985A398338E2CD8` (`feature_id`),
  KEY `FKD985A3987F8C9DB6` (`product_id`),
  KEY `FKD985A398E158A6FB` (`value_string_id`),
  KEY `FKD985A3988491DFB7` (`text_string_id`),
  CONSTRAINT `FKD985A398338E2CD8` FOREIGN KEY (`feature_id`) REFERENCES `feature` (`feature_id`),
  CONSTRAINT `FKD985A3987F8C9DB6` FOREIGN KEY (`product_id`) REFERENCES `abstractproducts` (`ctentry_id`),
  CONSTRAINT `FKD985A3988491DFB7` FOREIGN KEY (`text_string_id`) REFERENCES `multilingual_string` (`Id`),
  CONSTRAINT `FKD985A398E158A6FB` FOREIGN KEY (`value_string_id`) REFERENCES `multilingual_string` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `featurevalues`
--

LOCK TABLES `featurevalues` WRITE;
/*!40000 ALTER TABLE `featurevalues` DISABLE KEYS */;
/*!40000 ALTER TABLE `featurevalues` ENABLE KEYS */;
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
INSERT INTO `locale` VALUES (1,'2015-11-16 17:00:17','2015-11-16 17:00:17','875d522a-aaf9-4fdb-894a-f0556029e457','IT','it','Italiano'),(2,'2015-11-16 17:00:18','2015-11-16 17:00:18','43a08295-55e3-4fd8-8a2f-7b3f84b0a5dd','US','en','English');
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
  `text` varchar(200) DEFAULT NULL,
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
INSERT INTO `locale_string_map` VALUES (1,'en','public list','en'),(1,'it','listino al publico','it'),(2,'it','Tastiera','it'),(3,'it','Mouse','it'),(4,'it','Phone','it'),(5,'it','Monitor','it'),(486,'it','','it'),(487,'it','NOTEBOOK','it'),(488,'it','SAMSUNG NOTE IV','it'),(489,'it','CAVO HDMI','it'),(490,'it','CAVO HDMI','it'),(491,'it','NOKIA 662','it'),(492,'it','AURICOLARI NOKIA','it'),(493,'it','TOSHIBA NOTEBOOK SATELLITE XJC.255','it'),(494,'it','prodotto1','it');
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
  `text` varchar(200) DEFAULT NULL,
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
  `media_type` smallint(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sequence` double NOT NULL,
  `src` varchar(255) DEFAULT NULL,
  `ctentry_id` bigint(20) DEFAULT NULL,
  `description_string_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`media_id`),
  KEY `FK62F6FE4E24388F8` (`ctentry_id`),
  KEY `FK62F6FE4F30827C6` (`description_string_id`),
  CONSTRAINT `FK62F6FE4E24388F8` FOREIGN KEY (`ctentry_id`) REFERENCES `ctentry` (`ctentry_id`),
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
  KEY `FKAF088EEFCBF07F7` (`store_id`),
  CONSTRAINT `FKAF088EEFCBF07F7` FOREIGN KEY (`store_id`) REFERENCES `stores` (`member_id`)
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
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members`
--

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;
INSERT INTO `members` VALUES (1,'2015-11-16 17:00:18','2015-11-16 17:00:18','822a705e-3617-4e21-9630-000901547593',NULL,NULL,NULL,'STORE'),(2,'2015-11-16 17:00:18','2015-11-16 17:00:18','d600e984-313e-426a-8be3-5642222cecb9',NULL,NULL,NULL,'STORE'),(3,'2015-11-16 17:00:18','2015-11-16 17:00:18','b79f1be5-7551-4c33-b5ee-34ad32d8ad70',NULL,NULL,NULL,'USER'),(4,'2015-11-16 17:00:19','2015-12-14 17:51:38','66515dea-9eaa-4f78-9aa3-af4cfb83f8f1',NULL,NULL,NULL,'USER'),(5,'2015-12-10 15:17:27','2015-12-10 15:17:27','92651f31-cb21-4937-96bb-6be1f767989a',NULL,NULL,NULL,'USER'),(6,'2015-12-10 15:19:07','2015-12-10 15:19:07','eded1f11-f5d3-49e5-9942-3c6f9fa77513',NULL,NULL,NULL,'USER'),(7,'2015-12-10 15:22:42','2015-12-10 15:22:42','29daa015-ccf2-4934-9f04-78d97f86bb9a',NULL,NULL,NULL,'USER'),(8,'2015-12-10 15:23:59','2015-12-10 15:23:59','1521e698-9bef-4729-afa3-011a724eff6b',NULL,NULL,NULL,'USER'),(9,'2015-12-10 15:46:31','2015-12-10 15:58:41','e06c23dd-da9d-41ae-a14f-5f57910357b3',NULL,NULL,NULL,'USER'),(10,'2015-12-10 16:17:43','2015-12-10 16:28:41','5d4fb2ee-e550-4b53-b50c-ee71692524f9',NULL,NULL,NULL,'USER'),(11,'2015-12-11 11:03:42','2015-12-11 11:04:13','c4ddf487-aa9f-448d-9e65-e88201bb27ab',NULL,NULL,NULL,'USER'),(12,'2015-12-14 11:37:16','2015-12-14 18:05:21','26cd5baf-c631-41bb-9c76-7aa33a6e3219',NULL,NULL,NULL,'USER'),(13,'2015-12-14 17:18:56','2015-12-14 17:40:39','7f2f5eca-0229-4b64-91b8-c612fc8c788f',NULL,NULL,NULL,'USER');
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
  KEY `FK1E6037DCFCBF07F7` (`store_id`),
  CONSTRAINT `FK1E6037DC5F2D187D` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
  CONSTRAINT `FK1E6037DC7B32A13D` FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`),
  CONSTRAINT `FK1E6037DCFCBF07F7` FOREIGN KEY (`store_id`) REFERENCES `stores` (`member_id`)
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
) ENGINE=InnoDB AUTO_INCREMENT=495 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `multilingual_string`
--

LOCK TABLES `multilingual_string` WRITE;
/*!40000 ALTER TABLE `multilingual_string` DISABLE KEYS */;
INSERT INTO `multilingual_string` VALUES (1,'2015-11-16 17:01:06','2015-11-16 17:01:06','77a9dbcb-8fcc-4617-95f5-79acefa45f62'),(2,'2015-11-16 17:12:45','2015-11-16 17:12:45','c3aeaffa-c50d-4247-a32c-5511a6bdda8b'),(3,'2015-11-16 17:12:59','2015-11-16 17:12:59','46967765-0879-4112-92f9-d735d683b4d7'),(4,'2015-11-16 17:13:23','2015-11-16 17:13:23','c152e427-1ba2-460e-96c2-3aaafabef8b7'),(5,'2015-11-16 17:18:02','2015-11-16 17:18:02','176bfdde-951a-4e1c-87de-4d28a1a656d2'),(486,'2015-11-19 11:36:35','2015-11-19 11:36:35','265a6a6d-ad2e-478e-8d13-0ec3afbc7184'),(487,'2015-11-19 11:37:42','2015-11-19 11:37:42','c7590f5e-a7ff-4217-a670-26a32f92f5ed'),(488,'2015-11-19 11:38:40','2015-11-19 11:38:40','ebad119f-2e0d-4197-a658-5f6b3f345c0b'),(489,'2015-11-19 11:39:07','2015-11-19 11:39:07','5b7274c2-d580-42ed-ac5d-cbfe1bef9a5a'),(490,'2015-11-19 11:39:20','2015-11-19 11:39:20','5454e5cd-84fb-4949-94cf-31573f99f918'),(491,'2015-11-19 11:39:42','2015-11-19 11:39:42','6f3bec5d-3e84-4e49-b8d4-01794469b2b9'),(492,'2015-11-19 11:40:02','2015-11-19 11:40:02','d23222d5-6e79-4a56-ac93-0ca2218199a0'),(493,'2015-11-19 11:40:57','2015-11-19 11:40:57','b14e6350-1d79-4cb1-8f6b-d177f07d3043'),(494,'2015-12-03 17:21:18','2015-12-03 17:21:18','e6a445b3-c2a2-429c-b7d8-f26f78e1110f');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `multilingual_text`
--

LOCK TABLES `multilingual_text` WRITE;
/*!40000 ALTER TABLE `multilingual_text` DISABLE KEYS */;
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
  KEY `FK2DA2C1327F8C9DB6` (`product_id`),
  KEY `FK2DA2C13246710562` (`shipmode_id`),
  KEY `FK2DA2C1322C02378B` (`suborders_id`),
  CONSTRAINT `FK2DA2C1322C02378B` FOREIGN KEY (`suborders_id`) REFERENCES `suborders` (`suborders_id`),
  CONSTRAINT `FK2DA2C13246710562` FOREIGN KEY (`shipmode_id`) REFERENCES `shipmode` (`shipmode_id`),
  CONSTRAINT `FK2DA2C1327F8C9DB6` FOREIGN KEY (`product_id`) REFERENCES `abstractproducts` (`ctentry_id`),
  CONSTRAINT `FK2DA2C132E21E5CF0` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderitems`
--

LOCK TABLES `orderitems` WRITE;
/*!40000 ALTER TABLE `orderitems` DISABLE KEYS */;
INSERT INTO `orderitems` VALUES (1,91,'2015-12-14 12:37:12','2015-12-14 12:37:12','f2661d43-307c-4be8-97e0-8047bc1806ea',0,0,0,0,1,NULL,0,'TASTIERA',0,NULL,30.99,30.99,1052,5,NULL,NULL),(1,93,'2015-12-14 17:33:52','2015-12-14 17:33:52','24a260d8-546c-4b42-b5f0-fd016468d73d',0,0,0,0,1,NULL,0,'MOUSE',0,NULL,22.5,22.5,1060,6,NULL,NULL),(1,99,'2015-12-14 17:53:29','2015-12-14 17:53:29','39eccd8d-84c6-422b-894d-00450f735f34',0,0,0,0,1,NULL,0,'TASTIERA',0,NULL,30.99,30.99,1062,5,NULL,NULL),(1,101,'2015-12-14 17:55:28','2015-12-14 17:55:28','28dd6d4d-e833-481f-b2fa-17578f644dff',0,0,0,0,2,NULL,0,'MOUSE',0,NULL,22.5,22.5,1061,6,NULL,NULL),(1,104,'2015-12-14 17:57:47','2015-12-14 17:57:47','a5b34236-17c6-4790-98eb-9827f737065a',0,0,0,0,2,NULL,0,'MONITOR',0,NULL,1500,1500,1061,8,NULL,NULL),(1,106,'2015-12-14 18:05:04','2015-12-14 18:05:04','7ffd7f22-25e1-4c2c-b903-40bddc9dcf6e',0,0,0,0,2,NULL,0,'TASTIERA',0,NULL,30.99,30.99,1063,5,NULL,NULL);
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
  PRIMARY KEY (`order_id`),
  KEY `FKC3DF62E5EF40301B` (`billing_address_id`),
  KEY `FKC3DF62E56C1E9E18` (`currency_id`),
  KEY `FKC3DF62E5C1927579` (`customer_id`),
  KEY `FKC3DF62E546710562` (`shipmode_id`),
  KEY `FKC3DF62E5DBEA8708` (`shipping_address_id`),
  KEY `FKC3DF62E5FCBF07F7` (`store_id`),
  KEY `FKC3DF62E5457DC5D` (`user_id`),
  CONSTRAINT `FKC3DF62E5457DC5D` FOREIGN KEY (`user_id`) REFERENCES `users` (`member_id`),
  CONSTRAINT `FKC3DF62E546710562` FOREIGN KEY (`shipmode_id`) REFERENCES `shipmode` (`shipmode_id`),
  CONSTRAINT `FKC3DF62E56C1E9E18` FOREIGN KEY (`currency_id`) REFERENCES `currency` (`currency_id`),
  CONSTRAINT `FKC3DF62E5C1927579` FOREIGN KEY (`customer_id`) REFERENCES `members` (`member_id`),
  CONSTRAINT `FKC3DF62E5DBEA8708` FOREIGN KEY (`shipping_address_id`) REFERENCES `addresses` (`address_id`),
  CONSTRAINT `FKC3DF62E5EF40301B` FOREIGN KEY (`billing_address_id`) REFERENCES `addresses` (`address_id`),
  CONSTRAINT `FKC3DF62E5FCBF07F7` FOREIGN KEY (`store_id`) REFERENCES `stores` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1064 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,1052,'2015-12-14 12:37:07','2015-12-14 12:37:13','060de929-37b1-4609-ac64-10a52f487111','ZGVmYXVsdC1zdG9yZQ==-f2617c6c-51ab-4da7-8b05-2f8534779d29',NULL,0,30.99,'P',0,30.99,30.99,NULL,0,0,NULL,NULL,NULL,1,4,NULL,NULL,1,4),(1,1057,'2015-12-14 16:25:05','2015-12-14 16:25:05','6d208c3f-a246-4623-9be5-edcc54d4dd7e','ZGVmYXVsdC1zdG9yZQ==-701744d2-27e9-4154-b502-c87618b770e5',NULL,0,0,'P',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,NULL,NULL,1,1),(1,1060,'2015-12-14 17:33:52','2015-12-14 17:33:52','fca8efa4-3184-44dd-ad1e-964978c94f12','ZGVmYXVsdC1zdG9yZQ==-6ee1c48a-3605-4f07-8bd2-9887ecd6c38b',NULL,0,22.5,'P',0,22.5,22.5,NULL,0,0,NULL,NULL,NULL,1,1,NULL,NULL,1,1),(1,1061,'2015-12-14 17:51:14','2015-12-14 17:57:47','36d8071f-fd0c-4e8b-aef9-c0ab0386f320','ZGVmYXVsdC1zdG9yZQ==-08928627-0bc4-4eae-87b5-8a1187f63e7f',NULL,0,3045,'P',0,3045,3045,NULL,0,0,NULL,NULL,NULL,1,4,NULL,NULL,1,4),(1,1062,'2015-12-14 17:53:29','2015-12-14 17:53:30','b4b9a24f-6675-4b53-84ce-9e5cded1b253','ZGVmYXVsdC1zdG9yZQ==-2b8e4e3a-f71c-457d-a44e-8bdaa1521b82',NULL,0,30.99,'P',0,30.99,30.99,NULL,0,0,NULL,NULL,NULL,1,1,NULL,NULL,1,1),(1,1063,'2015-12-14 17:59:25','2015-12-14 18:05:04','42e8571b-5f5b-4801-8463-9a9b3ea2dda5','ZGVmYXVsdC1zdG9yZQ==-deea903a-b8fa-44c8-9247-70e2636af30f',NULL,0,61.98,'P',0,61.98,61.98,NULL,0,0,NULL,NULL,NULL,1,1,NULL,NULL,1,1);
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
  KEY `FK6158243FCBF07F7` (`store_id`),
  CONSTRAINT `FK61582436BDD0612` FOREIGN KEY (`paymethod_id`) REFERENCES `paymethod` (`paymethod_id`),
  CONSTRAINT `FK6158243FCBF07F7` FOREIGN KEY (`store_id`) REFERENCES `stores` (`member_id`)
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
INSERT INTO `pricelist` VALUES (1,'2015-11-16 17:01:06','2015-11-16 17:01:06','065ca444-5df8-489b-955f-3daf23586be4',1,'default-list',1,1);
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
  `currency_id` bigint(20) NOT NULL,
  `pricelist_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) NOT NULL,
  PRIMARY KEY (`prices_id`),
  KEY `FKC596784A6C1E9E18` (`currency_id`),
  KEY `FKC596784A15E50BB8` (`pricelist_id`),
  KEY `FKC596784A7F8C9DB6` (`product_id`),
  CONSTRAINT `FKC596784A15E50BB8` FOREIGN KEY (`pricelist_id`) REFERENCES `pricelist` (`pricelist_id`),
  CONSTRAINT `FKC596784A6C1E9E18` FOREIGN KEY (`currency_id`) REFERENCES `currency` (`currency_id`),
  CONSTRAINT `FKC596784A7F8C9DB6` FOREIGN KEY (`product_id`) REFERENCES `abstractproducts` (`ctentry_id`)
) ENGINE=InnoDB AUTO_INCREMENT=218 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prices`
--

LOCK TABLES `prices` WRITE;
/*!40000 ALTER TABLE `prices` DISABLE KEYS */;
INSERT INTO `prices` VALUES (201,'2015-11-20 13:03:30','2015-11-20 13:03:30','311518c3-f91f-4a46-b2d6-9f40a9104d81',22.5,1,0,NULL,NULL,NULL,NULL,1,1,6),(205,'2015-11-20 13:04:52','2015-11-20 13:04:52','0b8ea502-df51-4c20-8189-f4b1e4940743',2290.99,1,0,NULL,NULL,NULL,NULL,1,1,772),(206,'2015-11-20 13:05:36','2015-11-20 13:05:36','13f3be26-4c2d-4891-b8e2-a71ace4dc304',1500,1,0,NULL,NULL,NULL,NULL,1,1,8),(207,'2015-11-20 13:05:59','2015-11-20 13:05:59','ab62d20d-6213-463a-981a-27db91fe04c1',1298.99,1,0,NULL,NULL,NULL,NULL,1,1,773),(208,'2015-11-20 13:06:17','2015-11-20 13:06:17','11c75b92-5d77-487b-9857-835be7a4d037',800.99,1,0,NULL,NULL,NULL,NULL,1,1,774),(209,'2015-11-20 13:07:17','2015-11-20 13:07:17','a1d84861-3d5c-4ade-86b6-eb65eaa68a6d',800.99,5,0,NULL,NULL,NULL,NULL,1,1,774),(212,'2015-11-20 13:07:41','2015-11-20 13:07:41','332161b6-0d93-4134-be7c-d9028fe3b69c',155.99,9,0,NULL,NULL,NULL,NULL,1,1,7),(214,'2015-11-20 14:21:22','2015-11-20 14:21:22','d3dc881a-0150-4c48-b34d-48f64918428f',30.99,2,0,NULL,NULL,NULL,NULL,1,1,5),(215,'2015-11-20 14:21:22','2015-11-20 14:21:22','',15.99,1,NULL,NULL,NULL,NULL,NULL,1,1,775),(216,'2015-11-20 14:21:22','2015-11-20 14:21:22','',256.67,1,NULL,NULL,NULL,NULL,NULL,1,1,777),(217,'2015-11-20 14:21:22','2015-11-20 14:21:22','',89.99,1,NULL,NULL,NULL,NULL,NULL,1,1,776);
/*!40000 ALTER TABLE `prices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `ctentry_id` bigint(20) NOT NULL,
  PRIMARY KEY (`ctentry_id`),
  KEY `FK50C664CF41AE1AC4` (`ctentry_id`),
  CONSTRAINT `FK50C664CF41AE1AC4` FOREIGN KEY (`ctentry_id`) REFERENCES `abstractproducts` (`ctentry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (5),(6),(7),(8),(772),(773),(774),(775),(776),(777),(778),(779),(781);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
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
  KEY `FKA71FA7D67F8C9DB6` (`product_id`),
  CONSTRAINT `FKA71FA7D615E50BB8` FOREIGN KEY (`pricelist_id`) REFERENCES `pricelist` (`pricelist_id`),
  CONSTRAINT `FKA71FA7D67F8C9DB6` FOREIGN KEY (`product_id`) REFERENCES `abstractproducts` (`ctentry_id`)
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
-- Table structure for table `promotions`
--

DROP TABLE IF EXISTS `promotions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promotions` (
  `Type` varchar(31) NOT NULL,
  `promotion_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `code` varchar(255) NOT NULL,
  `discountAmount` double DEFAULT NULL,
  `discountPercentAmount` double DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL,
  `endDate` datetime NOT NULL,
  `forceApply` tinyint(1) DEFAULT NULL,
  `priority` int(11) NOT NULL,
  `startDate` datetime NOT NULL,
  `maxAmountToApply` double DEFAULT NULL,
  `minThreshold` double DEFAULT NULL,
  `description_string_id` bigint(20) DEFAULT NULL,
  `store_id` bigint(20) NOT NULL,
  `title_string_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`promotion_id`),
  KEY `FK3B429830F30827C6` (`description_string_id`),
  KEY `FK3B429830FCBF07F7` (`store_id`),
  KEY `FK3B4298306B698322` (`title_string_id`),
  CONSTRAINT `FK3B4298306B698322` FOREIGN KEY (`title_string_id`) REFERENCES `multilingual_string` (`Id`),
  CONSTRAINT `FK3B429830F30827C6` FOREIGN KEY (`description_string_id`) REFERENCES `multilingual_string` (`Id`),
  CONSTRAINT `FK3B429830FCBF07F7` FOREIGN KEY (`store_id`) REFERENCES `stores` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotions`
--

LOCK TABLES `promotions` WRITE;
/*!40000 ALTER TABLE `promotions` DISABLE KEYS */;
/*!40000 ALTER TABLE `promotions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promotionsapplied`
--

DROP TABLE IF EXISTS `promotionsapplied`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promotionsapplied` (
  `promotionApplied_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `amountApplied` double DEFAULT NULL,
  `amountPercentApplied` double DEFAULT NULL,
  `amountTotalApplied` double DEFAULT NULL,
  `code` varchar(255) NOT NULL,
  `sequence` int(11) DEFAULT NULL,
  PRIMARY KEY (`promotionApplied_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotionsapplied`
--

LOCK TABLES `promotionsapplied` WRITE;
/*!40000 ALTER TABLE `promotionsapplied` DISABLE KEYS */;
/*!40000 ALTER TABLE `promotionsapplied` ENABLE KEYS */;
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
INSERT INTO `roles` VALUES (1,'2015-11-16 17:00:19','2015-11-16 17:00:19','33d6d5c8-301c-49d1-a5cb-3600a3ec9bac',NULL,'employee'),(2,'2015-11-16 17:00:19','2015-11-16 17:00:19','e93d06a1-b9d4-41b4-9b1a-5bdbd98111ea',NULL,'administrator');
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
  PRIMARY KEY (`shipmode_id`)
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
  KEY `FK261F1C19FCBF07F7` (`store_id`),
  CONSTRAINT `FK261F1C193781983C` FOREIGN KEY (`country_id`) REFERENCES `countries` (`countries_id`),
  CONSTRAINT `FK261F1C1941A7A73C` FOREIGN KEY (`geocode_id`) REFERENCES `geocode` (`geocode_id`),
  CONSTRAINT `FK261F1C1946710562` FOREIGN KEY (`shipmode_id`) REFERENCES `shipmode` (`shipmode_id`),
  CONSTRAINT `FK261F1C19641D45CD` FOREIGN KEY (`regions_id`) REFERENCES `regions` (`regions_id`),
  CONSTRAINT `FK261F1C19FCBF07F7` FOREIGN KEY (`store_id`) REFERENCES `stores` (`member_id`)
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
  `name` varchar(250) NOT NULL,
  `member_id` bigint(20) NOT NULL,
  `currency_id` bigint(20) NOT NULL,
  PRIMARY KEY (`member_id`),
  KEY `FKCAD423B26C1E9E18` (`currency_id`),
  KEY `FKCAD423B27B32A13D` (`member_id`),
  CONSTRAINT `FKCAD423B26C1E9E18` FOREIGN KEY (`currency_id`) REFERENCES `currency` (`currency_id`),
  CONSTRAINT `FKCAD423B27B32A13D` FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stores`
--

LOCK TABLES `stores` WRITE;
/*!40000 ALTER TABLE `stores` DISABLE KEYS */;
INSERT INTO `stores` VALUES ('default-store',1,1),('test-store',2,1);
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
-- Table structure for table `user2storerel`
--

DROP TABLE IF EXISTS `user2storerel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user2storerel` (
  `user_id` bigint(20) NOT NULL,
  `store_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`store_id`),
  KEY `FK5955DEDFFCBF07F7` (`store_id`),
  KEY `FK5955DEDF457DC5D` (`user_id`),
  CONSTRAINT `FK5955DEDF457DC5D` FOREIGN KEY (`user_id`) REFERENCES `users` (`member_id`),
  CONSTRAINT `FK5955DEDFFCBF07F7` FOREIGN KEY (`store_id`) REFERENCES `stores` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user2storerel`
--

LOCK TABLES `user2storerel` WRITE;
/*!40000 ALTER TABLE `user2storerel` DISABLE KEYS */;
INSERT INTO `user2storerel` VALUES (5,1),(6,1),(7,1),(8,1),(9,1),(10,1),(11,1),(12,1),(13,1);
/*!40000 ALTER TABLE `user2storerel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user2userrel`
--

DROP TABLE IF EXISTS `user2userrel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user2userrel` (
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
-- Dumping data for table `user2userrel`
--

LOCK TABLES `user2userrel` WRITE;
/*!40000 ALTER TABLE `user2userrel` DISABLE KEYS */;
/*!40000 ALTER TABLE `user2userrel` ENABLE KEYS */;
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
INSERT INTO `users` VALUES ('test@test.it',5,'pippo',NULL,'pluto',NULL,'ANONYMOUS',1),(NULL,NULL,NULL,NULL,'Anonymous',NULL,'ANONYMOUS',3),(NULL,NULL,NULL,'2015-12-14 17:51:38','Superuser',NULL,'SUPERSUSER',4),(NULL,NULL,NULL,NULL,NULL,NULL,'REGISTERED',5),(NULL,NULL,NULL,NULL,NULL,NULL,'REGISTERED',6),(NULL,NULL,NULL,NULL,NULL,NULL,'REGISTERED',7),(NULL,NULL,NULL,NULL,NULL,NULL,'REGISTERED',8),('cchiama@stepfour.it',NULL,'Christian','2015-12-10 15:58:41','Chiama',NULL,'REGISTERED',9),('cchiama@stepfour.it',NULL,'Christian','2015-12-10 16:28:41','Chiama',NULL,'REGISTERED',10),('oggoogogo@ttt.it',NULL,'pippoppp','2015-12-11 11:04:13','ogogogogou',NULL,'REGISTERED',11),('fpicinelli@stepfour.it',NULL,'Fede','2015-12-14 18:05:21','Pic',NULL,'REGISTERED',12),('cchiama@stepfour.it',NULL,'christian','2015-12-14 17:40:39','chiama',NULL,'REGISTERED',13);
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
INSERT INTO `users_reg` VALUES (NULL,NULL,NULL,NULL,'2015-12-14 17:51:38','superuser','admin',NULL,'ACTIVE',4,NULL),(NULL,NULL,NULL,NULL,'2015-12-10 15:58:41','chris77','250977cc',NULL,'ACTIVE',9,NULL),(NULL,NULL,NULL,NULL,'2015-12-10 16:28:41','pippo','250977',NULL,'ACTIVE',10,NULL),(NULL,NULL,NULL,NULL,'2015-12-11 11:04:13','rrrrrrrr','rrrrrr',NULL,'ACTIVE',11,NULL),(NULL,NULL,NULL,NULL,'2015-12-14 18:05:21','fede','123456',NULL,'ACTIVE',12,NULL),(NULL,NULL,NULL,NULL,'2015-12-14 17:40:39','christian','250977',NULL,'ACTIVE',13,NULL);
/*!40000 ALTER TABLE `users_reg` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-14 18:16:11
