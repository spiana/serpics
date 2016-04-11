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
-- Table structure for table `Address`
--

DROP TABLE IF EXISTS `Address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Address` (
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
  `flag` varchar(255) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `mobile` varchar(25) DEFAULT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  `phone` varchar(25) DEFAULT NULL,
  `streetNumber` varchar(10) DEFAULT NULL,
  `vatcode` varchar(30) DEFAULT NULL,
  `zipcode` varchar(20) DEFAULT NULL,
  `country_id` bigint(20) DEFAULT NULL,
  `district_id` bigint(20) DEFAULT NULL,
  `region_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`address_id`),
  UNIQUE KEY `UK_3esur66d4fw7rpsajiftsnubm` (`uuid`),
  KEY `FK_b9wby4b3jwmvb3t6u4m0jlygg` (`country_id`),
  KEY `FK_89wohkcficnbc4wgox74tfvrp` (`district_id`),
  KEY `FK_gos6jh1q4p5gx3nsqn2yyhloi` (`region_id`),
  CONSTRAINT `FK_89wohkcficnbc4wgox74tfvrp` FOREIGN KEY (`district_id`) REFERENCES `district` (`district_id`),
  CONSTRAINT `FK_b9wby4b3jwmvb3t6u4m0jlygg` FOREIGN KEY (`country_id`) REFERENCES `countries` (`countries_id`),
  CONSTRAINT `FK_gos6jh1q4p5gx3nsqn2yyhloi` FOREIGN KEY (`region_id`) REFERENCES `regions` (`regions_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Address`
--

LOCK TABLES `Address` WRITE;
/*!40000 ALTER TABLE `Address` DISABLE KEYS */;
INSERT INTO `Address` (`address_id`, `created`, `updated`, `uuid`, `address1`, `address2`, `address3`, `city`, `company`, `email`, `fax`, `field1`, `field2`, `field3`, `field4`, `firstname`, `flag`, `lastname`, `mobile`, `nickname`, `phone`, `streetNumber`, `vatcode`, `zipcode`, `country_id`, `district_id`, `region_id`) VALUES (1,'2016-03-07 17:54:00','2016-03-07 17:54:00','66fae153-47f4-4f3e-8190-7a5bbf013fa1','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede',NULL,'Pic','333333333',NULL,'333333333','1',NULL,'26025',118,NULL,NULL),(2,'2016-03-07 17:54:00','2016-03-07 17:54:00','ee10ff10-4f85-42ad-86b6-6484f774e9e7','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede',NULL,'Pic','333333333',NULL,'333333333','1',NULL,'26025',118,NULL,NULL),(3,'2016-03-14 10:53:27','2016-03-14 10:53:27','7cb85b2d-1abd-441b-a9d6-a735046f458b','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede',NULL,'Pic','333333333',NULL,'333333333','1',NULL,'26025',NULL,NULL,NULL),(4,'2016-03-14 10:53:27','2016-03-14 10:53:27','9c0b75b4-7dc7-41c9-9708-bcc8cbbf75d6','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede',NULL,'Pic','333333333',NULL,'333333333','1',NULL,'26025',NULL,NULL,NULL),(5,'2016-03-14 10:53:48','2016-03-14 10:53:48','3756ed75-7b52-495f-9e0b-80dda2def6c5','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede',NULL,'Pic','333333333',NULL,'333333333','1',NULL,'26025',NULL,NULL,NULL),(6,'2016-03-14 10:53:48','2016-03-14 10:53:48','aab0d82f-e8fb-4d36-91ae-d824808fd491','VIA LUIGI PIRANDELLO','6',NULL,'PANDINO',NULL,'fpicinelli@stepfour.it',NULL,NULL,NULL,NULL,NULL,'Fede',NULL,'Pic','333333333',NULL,'333333333','1',NULL,'26025',NULL,NULL,NULL),(7,'2016-03-18 11:00:57','2016-03-18 11:00:57','3c0aaa79-035d-4add-a853-da85a774dbba','ga','ga',NULL,'ga',NULL,'ga','ga',NULL,NULL,NULL,NULL,'ga',NULL,'ga','ga',NULL,'ga','ga',NULL,'ga',118,10,NULL),(8,'2016-03-18 11:00:59','2016-03-18 11:00:59','113c0705-5e97-4de1-b952-3d859faddfdf','ga','ga',NULL,'ga',NULL,'ga','ga',NULL,NULL,NULL,NULL,'ga',NULL,'ga','ga',NULL,'ga','ga',NULL,'ga',118,10,NULL);
/*!40000 ALTER TABLE `Address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BillingAddress`
--

DROP TABLE IF EXISTS `BillingAddress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BillingAddress` (
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
  `flag` varchar(255) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `mobile` varchar(25) DEFAULT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  `phone` varchar(25) DEFAULT NULL,
  `streetNumber` varchar(10) DEFAULT NULL,
  `vatcode` varchar(30) DEFAULT NULL,
  `zipcode` varchar(20) DEFAULT NULL,
  `country_id` bigint(20) DEFAULT NULL,
  `district_id` bigint(20) DEFAULT NULL,
  `region_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`address_id`),
  UNIQUE KEY `UK_72tmb4sxo08diha47y4hgloxw` (`uuid`),
  KEY `FK_h8kgbxit57qqqm6feqi8gv1ba` (`country_id`),
  KEY `FK_o200hu19u2v8rg4sc7q3t06u0` (`district_id`),
  KEY `FK_nb4ujxivpa9ad40lh5f9bg7ax` (`region_id`),
  CONSTRAINT `FK_h8kgbxit57qqqm6feqi8gv1ba` FOREIGN KEY (`country_id`) REFERENCES `countries` (`countries_id`),
  CONSTRAINT `FK_nb4ujxivpa9ad40lh5f9bg7ax` FOREIGN KEY (`region_id`) REFERENCES `regions` (`regions_id`),
  CONSTRAINT `FK_o200hu19u2v8rg4sc7q3t06u0` FOREIGN KEY (`district_id`) REFERENCES `district` (`district_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BillingAddress`
--

LOCK TABLES `BillingAddress` WRITE;
/*!40000 ALTER TABLE `BillingAddress` DISABLE KEYS */;
INSERT INTO `BillingAddress` (`address_id`, `created`, `updated`, `uuid`, `address1`, `address2`, `address3`, `city`, `company`, `email`, `fax`, `field1`, `field2`, `field3`, `field4`, `firstname`, `flag`, `lastname`, `mobile`, `nickname`, `phone`, `streetNumber`, `vatcode`, `zipcode`, `country_id`, `district_id`, `region_id`) VALUES (1,'2016-03-18 11:00:57','2016-03-18 11:00:57','c4cdea2d-77b3-43fb-99f6-4cae4937c805','ga','ga',NULL,'ga',NULL,'ga','ga',NULL,NULL,NULL,NULL,'ga',NULL,'ga','ga',NULL,'ga','ga',NULL,'ga',118,10,NULL);
/*!40000 ALTER TABLE `BillingAddress` ENABLE KEYS */;
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
  `selected` bit(1) NOT NULL,
  PRIMARY KEY (`catalog_id`,`store_id`),
  UNIQUE KEY `UK_laf9h90thx5ppufcbebfndjb` (`uuid`),
  KEY `FK_66dbimrl7h94d5wgwsbspmyh2` (`store_id`),
  CONSTRAINT `FK_66dbimrl7h94d5wgwsbspmyh2` FOREIGN KEY (`store_id`) REFERENCES `stores` (`store_id`),
  CONSTRAINT `FK_mpt2o6ydp4l4p1sq6x3qywptt` FOREIGN KEY (`catalog_id`) REFERENCES `catalog` (`catalog_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Catalog2StoreRelation`
--

LOCK TABLES `Catalog2StoreRelation` WRITE;
/*!40000 ALTER TABLE `Catalog2StoreRelation` DISABLE KEYS */;
INSERT INTO `Catalog2StoreRelation` (`catalog_id`, `store_id`, `created`, `updated`, `uuid`, `selected`) VALUES (1,1,'2016-03-07 14:42:25','2016-03-07 14:42:25','f5b16d28-3572-47f8-ac05-f0099abb76a9','');
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
  UNIQUE KEY `UK_9msh1ktopjm7xtuo4fnr5f1cj` (`uuid`),
  KEY `FK_rkp2crn21651s7jpvpqfyko34` (`ctentry_id_parent`),
  CONSTRAINT `FK_97lud8rd035s87ap7jh4rifmg` FOREIGN KEY (`ctentry_id_child`) REFERENCES `abstractProducts` (`ctentry_id`),
  CONSTRAINT `FK_rkp2crn21651s7jpvpqfyko34` FOREIGN KEY (`ctentry_id_parent`) REFERENCES `category` (`ctentry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CategoryProductRelation`
--

LOCK TABLES `CategoryProductRelation` WRITE;
/*!40000 ALTER TABLE `CategoryProductRelation` DISABLE KEYS */;
INSERT INTO `CategoryProductRelation` (`ctentry_id_child`, `ctentry_id_parent`, `created`, `updated`, `uuid`, `field1`, `fiedl2`, `relation_type`, `sequence`) VALUES (26,6,'2016-03-07 14:52:13','2016-03-07 14:52:13','510a993f-592a-4fb5-b04c-7fddedb74894',NULL,NULL,1,0),(27,6,'2016-03-07 14:52:13','2016-03-07 14:52:13','821d913b-ccbb-4674-8155-971de590e924',NULL,NULL,1,0),(28,8,'2016-03-07 14:52:13','2016-03-07 14:52:13','17eebfde-5746-457d-bd9d-8ba41e6cb4f5',NULL,NULL,1,0),(29,8,'2016-03-07 14:52:13','2016-03-07 14:52:13','4499c489-592c-4abc-b6f0-85bf3436128f',NULL,NULL,1,0),(30,7,'2016-03-07 14:52:13','2016-03-07 14:52:13','d39acc5f-ab1d-4892-86b1-0da834f94af9',NULL,NULL,1,0),(30,10,'2016-03-07 14:52:14','2016-03-07 14:52:14','90f070c3-f5be-4028-a9e8-6b95cd8811bc',NULL,NULL,1,0),(31,10,'2016-03-07 14:52:14','2016-03-07 14:52:14','d4e4fd59-0f13-4226-a16e-87f1b2163e2f',NULL,NULL,1,0),(33,7,'2016-03-07 14:52:13','2016-03-07 14:52:13','2dc23548-3fda-4b1d-be85-2bf2579fe95c',NULL,NULL,1,0),(34,9,'2016-03-07 14:52:14','2016-03-07 14:52:14','1fa1b522-0cfd-4889-9b6b-5ab11c35d9af',NULL,NULL,1,0),(35,9,'2016-03-07 14:52:14','2016-03-07 14:52:14','a7a71197-2373-4b14-9dcb-f351cfc30c43',NULL,NULL,1,0),(35,11,'2016-03-07 14:52:14','2016-03-07 14:52:14','e363b9bd-4379-4d12-96d8-f50ed86fe39b',NULL,NULL,1,0),(37,11,'2016-03-07 14:52:14','2016-03-07 14:52:14','23a3fc36-cb29-463d-97d2-ec4fb7784dcb',NULL,NULL,1,0),(38,16,'2016-03-07 14:52:14','2016-03-07 14:52:14','233ef065-c248-4604-a8bc-91444c63d503',NULL,NULL,1,0),(39,16,'2016-03-07 14:52:14','2016-03-07 14:52:14','6718e1fc-649c-46d4-8a41-48d9e0501da6',NULL,NULL,1,0),(40,17,'2016-03-07 14:52:14','2016-03-07 14:52:14','3e0bb393-0d04-4111-b402-fd9bd75a7246',NULL,NULL,1,0),(41,17,'2016-03-07 14:52:14','2016-03-07 14:52:14','300d4da3-9d7e-4faf-8b20-55bdf5026870',NULL,NULL,1,0);
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
  UNIQUE KEY `UK_ddmlmubckrfr3ass7lon0qdwr` (`uuid`),
  KEY `FK_93fngr9enslngkbmy5oc1ln46` (`ctentry_id_parent`),
  CONSTRAINT `FK_93fngr9enslngkbmy5oc1ln46` FOREIGN KEY (`ctentry_id_parent`) REFERENCES `category` (`ctentry_id`),
  CONSTRAINT `FK_qnkqm93rqn7746emtaxvyo03t` FOREIGN KEY (`ctentry_id_child`) REFERENCES `category` (`ctentry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CategoryRelation`
--

LOCK TABLES `CategoryRelation` WRITE;
/*!40000 ALTER TABLE `CategoryRelation` DISABLE KEYS */;
INSERT INTO `CategoryRelation` (`ctentry_id_child`, `ctentry_id_parent`, `created`, `updated`, `uuid`, `field1`, `fiedl2`, `relation_type`, `sequence`) VALUES (4,1,'2016-03-07 14:51:13','2016-03-07 14:51:13','da1b9f03-62a4-456e-b9f3-9886abb42254',NULL,NULL,0,1),(5,2,'2016-03-07 14:51:14','2016-03-07 14:51:14','af3647d3-17e0-41ca-941d-2790168a5f88',NULL,NULL,0,1),(6,4,'2016-03-07 14:51:13','2016-03-07 14:51:13','d66bd236-53cf-4fd4-9f40-0abf1b81a593',NULL,NULL,0,1),(7,4,'2016-03-07 14:51:14','2016-03-07 14:51:14','0aef7555-a23f-44ec-a378-318560d89f71',NULL,NULL,0,2),(8,1,'2016-03-07 14:51:14','2016-03-07 14:51:14','4d24966d-2f33-443b-8ea3-eaf93f18ce41',NULL,NULL,0,2),(9,5,'2016-03-07 14:51:14','2016-03-07 14:51:14','87d66ed8-59ff-4dfb-ad4e-0a3caf9f95f9',NULL,NULL,0,1),(10,5,'2016-03-07 14:51:14','2016-03-07 14:51:14','6732fcbf-e0e9-4289-9cbb-0f9bac37bddb',NULL,NULL,0,2),(11,2,'2016-03-07 14:51:14','2016-03-07 14:51:14','a4150d99-3f3b-4eb1-b04a-748843106864',NULL,NULL,0,2),(12,3,'2016-03-07 14:51:14','2016-03-07 14:51:14','d935beaa-7287-45c0-bd62-5884d78bdbaa',NULL,NULL,0,1),(13,3,'2016-03-07 14:51:14','2016-03-07 14:51:14','6a09544c-3a52-4308-a473-8baf1a174097',NULL,NULL,0,2),(14,12,'2016-03-07 14:51:14','2016-03-07 14:51:14','01939cc6-74eb-46b0-9f73-6144e828b935',NULL,NULL,0,1),(15,13,'2016-03-07 14:51:15','2016-03-07 14:51:15','44f3f9d5-ce40-4880-beec-18871cbd56df',NULL,NULL,0,1),(16,14,'2016-03-07 14:51:15','2016-03-07 14:51:15','9eb90c2e-28a2-47a7-a843-d6bfd97ca2c8',NULL,NULL,0,1),(17,15,'2016-03-07 14:51:15','2016-03-07 14:51:15','25d74534-ab0e-4611-ab7c-0da809e7dbee',NULL,NULL,0,1);
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
  UNIQUE KEY `UK_e560iadocorv8t09wvq3sme9x` (`uuid`),
  KEY `FK_s70v00guucyy4fvywkq88xs9j` (`ctentry_id_parent`),
  CONSTRAINT `FK_9lpl3vikq6whuomjhj9y21hpk` FOREIGN KEY (`ctentry_id_child`) REFERENCES `FeatureModel` (`specification_id`),
  CONSTRAINT `FK_s70v00guucyy4fvywkq88xs9j` FOREIGN KEY (`ctentry_id_parent`) REFERENCES `FeatureModel` (`specification_id`)
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
  UNIQUE KEY `UK_290x92413m4pk1smtob91enyv` (`uuid`),
  KEY `FK_pmb82iiph3mqp7ih3c83qiya2` (`catalog_id`),
  KEY `FK_fd37ify1dkevy1bv9qb7o5d6i` (`description_stringid`),
  KEY `FK_mmbv88smvvtc4hc8la7npm1av` (`featureGroup_id`),
  KEY `FK_6wsb5oym5vpqjp582kwo5whk5` (`featureModel_id`),
  CONSTRAINT `FK_6wsb5oym5vpqjp582kwo5whk5` FOREIGN KEY (`featureModel_id`) REFERENCES `FeatureModel` (`specification_id`),
  CONSTRAINT `FK_fd37ify1dkevy1bv9qb7o5d6i` FOREIGN KEY (`description_stringid`) REFERENCES `multilingual_string` (`Id`),
  CONSTRAINT `FK_mmbv88smvvtc4hc8la7npm1av` FOREIGN KEY (`featureGroup_id`) REFERENCES `FeatureGroup` (`id`),
  CONSTRAINT `FK_pmb82iiph3mqp7ih3c83qiya2` FOREIGN KEY (`catalog_id`) REFERENCES `catalog` (`catalog_id`)
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
  UNIQUE KEY `UK_al74xbjhmh5k9hccixc0hjdgf` (`uuid`),
  KEY `FK_bjw7qonqj1cjwcodlgyvhjep5` (`catalog_id`),
  KEY `FK_2ovjh8lh2fyqwaabqf97479nc` (`description_string_id`),
  KEY `FK_k9oexurejj6mpmrisc8e1ldo9` (`model_id`),
  CONSTRAINT `FK_2ovjh8lh2fyqwaabqf97479nc` FOREIGN KEY (`description_string_id`) REFERENCES `multilingual_string` (`Id`),
  CONSTRAINT `FK_bjw7qonqj1cjwcodlgyvhjep5` FOREIGN KEY (`catalog_id`) REFERENCES `catalog` (`catalog_id`),
  CONSTRAINT `FK_k9oexurejj6mpmrisc8e1ldo9` FOREIGN KEY (`model_id`) REFERENCES `FeatureModel` (`specification_id`)
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
  KEY `FK_t2y2t31kabeu4gf9do4dg87n2` (`catalog_id`),
  CONSTRAINT `FK_554eu4qup12518hc23r8wgw6v` FOREIGN KEY (`specification_id`) REFERENCES `ctentry` (`ctentry_id`),
  CONSTRAINT `FK_t2y2t31kabeu4gf9do4dg87n2` FOREIGN KEY (`catalog_id`) REFERENCES `catalog` (`catalog_id`)
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
  UNIQUE KEY `UK_8vvsemes81x1y771a66edprjj` (`uuid`),
  KEY `FK_j90aioqt035c1o58v3j23qjpj` (`catalog_id`),
  KEY `FK_q0318if1we5qd5w7nfgcjibei` (`feature_id`),
  KEY `FK_8ctolgjmcnrw45t2fmudehop7` (`product_id`),
  CONSTRAINT `FK_8ctolgjmcnrw45t2fmudehop7` FOREIGN KEY (`product_id`) REFERENCES `abstractProducts` (`ctentry_id`),
  CONSTRAINT `FK_j90aioqt035c1o58v3j23qjpj` FOREIGN KEY (`catalog_id`) REFERENCES `catalog` (`catalog_id`),
  CONSTRAINT `FK_q0318if1we5qd5w7nfgcjibei` FOREIGN KEY (`feature_id`) REFERENCES `Feature` (`feature_id`)
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
  UNIQUE KEY `UK_33bypji7rf9928ec5vaiecdeu` (`media_id`,`format`),
  UNIQUE KEY `UK_6kuvmo1g9ou8q8f5jxf909lh4` (`uuid`),
  CONSTRAINT `FK_6xgw0vp1m7rdalf04oe87rdau` FOREIGN KEY (`media_id`) REFERENCES `media` (`media_id`)
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
-- Table structure for table `Payment`
--

DROP TABLE IF EXISTS `Payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Payment` (
  `payment_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `PayerId` varchar(255) DEFAULT NULL,
  `amount` double NOT NULL,
  `authorizedAmount` double DEFAULT NULL,
  `authorizedURL` varchar(255) DEFAULT NULL,
  `captureAmount` double DEFAULT NULL,
  `intent` varchar(255) NOT NULL,
  `paymentIdentifier` varchar(255) NOT NULL,
  `payment_method` varchar(255) DEFAULT NULL,
  `refoundAmount` double DEFAULT NULL,
  `state` varchar(255) NOT NULL,
  `currence_id` bigint(20) NOT NULL,
  `order_id` bigint(20) NOT NULL,
  `paymethod_id` bigint(20) NOT NULL,
  PRIMARY KEY (`payment_id`),
  UNIQUE KEY `UK_mg7qk458c0pphect872ivd23w` (`uuid`),
  KEY `FK_pi6cr6ao9io6xmppnrwbnddyp` (`currence_id`),
  KEY `FK_ib81ge3yc3c20yqjivacha3yc` (`order_id`),
  KEY `FK_ss2uyxc3xe8m79xxc5c7hm4q8` (`paymethod_id`),
  CONSTRAINT `FK_ib81ge3yc3c20yqjivacha3yc` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  CONSTRAINT `FK_pi6cr6ao9io6xmppnrwbnddyp` FOREIGN KEY (`currence_id`) REFERENCES `currency` (`currency_id`),
  CONSTRAINT `FK_ss2uyxc3xe8m79xxc5c7hm4q8` FOREIGN KEY (`paymethod_id`) REFERENCES `paymethod` (`paymethod_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Payment`
--

LOCK TABLES `Payment` WRITE;
/*!40000 ALTER TABLE `Payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `Payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PermanentAddress`
--

DROP TABLE IF EXISTS `PermanentAddress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PermanentAddress` (
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
  `flag` varchar(255) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `mobile` varchar(25) DEFAULT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  `phone` varchar(25) DEFAULT NULL,
  `streetNumber` varchar(10) DEFAULT NULL,
  `vatcode` varchar(30) DEFAULT NULL,
  `zipcode` varchar(20) DEFAULT NULL,
  `country_id` bigint(20) DEFAULT NULL,
  `district_id` bigint(20) DEFAULT NULL,
  `region_id` bigint(20) DEFAULT NULL,
  `member_id` bigint(20) NOT NULL,
  PRIMARY KEY (`address_id`),
  UNIQUE KEY `UK_eiv0ys3u77r55xixlv0rlvx2v` (`uuid`),
  KEY `FK_7ykwllqy3d0i47wn7bb9yp1oh` (`country_id`),
  KEY `FK_qe58rh2erco6mv3vql3e4rcs6` (`district_id`),
  KEY `FK_7n2xewgjsw4h9n4qtb6xd74hf` (`region_id`),
  KEY `FK_hnv5e8q6byp2yfjvkeo3p8n9q` (`member_id`),
  CONSTRAINT `FK_7n2xewgjsw4h9n4qtb6xd74hf` FOREIGN KEY (`region_id`) REFERENCES `regions` (`regions_id`),
  CONSTRAINT `FK_7ykwllqy3d0i47wn7bb9yp1oh` FOREIGN KEY (`country_id`) REFERENCES `countries` (`countries_id`),
  CONSTRAINT `FK_hnv5e8q6byp2yfjvkeo3p8n9q` FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`),
  CONSTRAINT `FK_qe58rh2erco6mv3vql3e4rcs6` FOREIGN KEY (`district_id`) REFERENCES `district` (`district_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PermanentAddress`
--

LOCK TABLES `PermanentAddress` WRITE;
/*!40000 ALTER TABLE `PermanentAddress` DISABLE KEYS */;
INSERT INTO `PermanentAddress` (`address_id`, `created`, `updated`, `uuid`, `address1`, `address2`, `address3`, `city`, `company`, `email`, `fax`, `field1`, `field2`, `field3`, `field4`, `firstname`, `flag`, `lastname`, `mobile`, `nickname`, `phone`, `streetNumber`, `vatcode`, `zipcode`, `country_id`, `district_id`, `region_id`, `member_id`) VALUES (1,'2016-03-18 11:00:59','2016-03-18 11:00:59','24a162f8-0582-42ef-b7a9-85152adc3d08','ga','ga',NULL,'ga',NULL,'ga','ga',NULL,NULL,NULL,NULL,'ga',NULL,'ga','ga',NULL,'ga','ga',NULL,'ga',118,10,NULL,3);
/*!40000 ALTER TABLE `PermanentAddress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PrimaryAddress`
--

DROP TABLE IF EXISTS `PrimaryAddress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PrimaryAddress` (
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
  `flag` varchar(255) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `mobile` varchar(25) DEFAULT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  `phone` varchar(25) DEFAULT NULL,
  `streetNumber` varchar(10) DEFAULT NULL,
  `vatcode` varchar(30) DEFAULT NULL,
  `zipcode` varchar(20) DEFAULT NULL,
  `country_id` bigint(20) DEFAULT NULL,
  `district_id` bigint(20) DEFAULT NULL,
  `region_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`address_id`),
  UNIQUE KEY `UK_6xmq491cbpg7abee13bacdlim` (`uuid`),
  KEY `FK_nawphvgr01la9tyw783ru8yla` (`country_id`),
  KEY `FK_mmg7m7yy6x8g2r7c7wxc1eaud` (`district_id`),
  KEY `FK_n1mi6cqx03mpodxmkne0vq894` (`region_id`),
  CONSTRAINT `FK_mmg7m7yy6x8g2r7c7wxc1eaud` FOREIGN KEY (`district_id`) REFERENCES `district` (`district_id`),
  CONSTRAINT `FK_n1mi6cqx03mpodxmkne0vq894` FOREIGN KEY (`region_id`) REFERENCES `regions` (`regions_id`),
  CONSTRAINT `FK_nawphvgr01la9tyw783ru8yla` FOREIGN KEY (`country_id`) REFERENCES `countries` (`countries_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PrimaryAddress`
--

LOCK TABLES `PrimaryAddress` WRITE;
/*!40000 ALTER TABLE `PrimaryAddress` DISABLE KEYS */;
INSERT INTO `PrimaryAddress` (`address_id`, `created`, `updated`, `uuid`, `address1`, `address2`, `address3`, `city`, `company`, `email`, `fax`, `field1`, `field2`, `field3`, `field4`, `firstname`, `flag`, `lastname`, `mobile`, `nickname`, `phone`, `streetNumber`, `vatcode`, `zipcode`, `country_id`, `district_id`, `region_id`) VALUES (1,'2016-03-07 14:42:24','2016-03-07 14:42:24','4760bfee-9fda-431c-972c-623346ed66e0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,'2016-03-08 12:05:06','2016-03-08 12:05:06','bccc8c91-09e4-4a2f-b65d-3995c1b4126b',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `PrimaryAddress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `WarehouseAddress`
--

DROP TABLE IF EXISTS `WarehouseAddress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WarehouseAddress` (
  `address_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address1` longtext,
  `address2` longtext,
  `address3` longtext,
  `city` varchar(200) DEFAULT NULL,
  `fax` varchar(25) DEFAULT NULL,
  `mobile` varchar(25) DEFAULT NULL,
  `phone` varchar(25) DEFAULT NULL,
  `streetNumber` varchar(10) DEFAULT NULL,
  `zipcode` varchar(20) DEFAULT NULL,
  `country_id` bigint(20) DEFAULT NULL,
  `district_id` bigint(20) DEFAULT NULL,
  `region_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`address_id`),
  KEY `FK_e5p0urtps419escftdnpv2m16` (`country_id`),
  KEY `FK_4jrigcsx524srmhmsxoc9j5v9` (`district_id`),
  KEY `FK_5h8hcbf9uydhkyajkg33rmekf` (`region_id`),
  CONSTRAINT `FK_4jrigcsx524srmhmsxoc9j5v9` FOREIGN KEY (`district_id`) REFERENCES `district` (`district_id`),
  CONSTRAINT `FK_5h8hcbf9uydhkyajkg33rmekf` FOREIGN KEY (`region_id`) REFERENCES `regions` (`regions_id`),
  CONSTRAINT `FK_e5p0urtps419escftdnpv2m16` FOREIGN KEY (`country_id`) REFERENCES `countries` (`countries_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `WarehouseAddress`
--

LOCK TABLES `WarehouseAddress` WRITE;
/*!40000 ALTER TABLE `WarehouseAddress` DISABLE KEYS */;
/*!40000 ALTER TABLE `WarehouseAddress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `abstractProducts`
--

DROP TABLE IF EXISTS `abstractProducts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `abstractProducts` (
  `buyable` bit(1) NOT NULL,
  `downlodable` bit(1) NOT NULL,
  `manufacturer_sku` varchar(255) DEFAULT NULL,
  `product_type` int(11) NOT NULL,
  `published` bit(1) NOT NULL,
  `unit_meas` varchar(255) DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `weight_meas` varchar(255) DEFAULT NULL,
  `ctentry_id` bigint(20) NOT NULL,
  `brand_id` bigint(20) DEFAULT NULL,
  `featuremodel_id` bigint(20) DEFAULT NULL,
  `taxcategory_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ctentry_id`),
  KEY `FK_a1wpkv424nnphxho50ufjdqpk` (`brand_id`),
  KEY `FK_9ica98ddno37po8qt7hnqyrjf` (`featuremodel_id`),
  KEY `FK_ryj3ybr1ow582x6yj05tkc9mw` (`taxcategory_id`),
  CONSTRAINT `FK_9ica98ddno37po8qt7hnqyrjf` FOREIGN KEY (`featuremodel_id`) REFERENCES `FeatureModel` (`specification_id`),
  CONSTRAINT `FK_a1wpkv424nnphxho50ufjdqpk` FOREIGN KEY (`brand_id`) REFERENCES `brands` (`ctentry_id`),
  CONSTRAINT `FK_qoq9gvihdmypul9urunjd8jd6` FOREIGN KEY (`ctentry_id`) REFERENCES `ctentry` (`ctentry_id`),
  CONSTRAINT `FK_ryj3ybr1ow582x6yj05tkc9mw` FOREIGN KEY (`taxcategory_id`) REFERENCES `taxes` (`taxes_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `abstractProducts`
--

LOCK TABLES `abstractProducts` WRITE;
/*!40000 ALTER TABLE `abstractProducts` DISABLE KEYS */;
INSERT INTO `abstractProducts` (`buyable`, `downlodable`, `manufacturer_sku`, `product_type`, `published`, `unit_meas`, `weight`, `weight_meas`, `ctentry_id`, `brand_id`, `featuremodel_id`, `taxcategory_id`) VALUES ('','\0',NULL,2,'',NULL,NULL,NULL,26,NULL,NULL,NULL),('','\0',NULL,2,'',NULL,NULL,NULL,27,NULL,NULL,NULL),('','\0',NULL,2,'',NULL,NULL,NULL,28,NULL,NULL,NULL),('','\0',NULL,2,'',NULL,NULL,NULL,29,NULL,NULL,NULL),('','\0',NULL,2,'',NULL,NULL,NULL,30,NULL,NULL,NULL),('','\0',NULL,2,'',NULL,NULL,NULL,31,NULL,NULL,NULL),('','\0',NULL,2,'',NULL,NULL,NULL,32,NULL,NULL,NULL),('','\0',NULL,2,'',NULL,NULL,NULL,33,NULL,NULL,NULL),('','\0',NULL,2,'',NULL,NULL,NULL,34,NULL,NULL,NULL),('','\0',NULL,2,'',NULL,NULL,NULL,35,NULL,NULL,NULL),('','\0',NULL,2,'',NULL,NULL,NULL,36,NULL,NULL,NULL),('','\0',NULL,2,'',NULL,NULL,NULL,37,NULL,NULL,NULL),('','\0',NULL,2,'',NULL,NULL,NULL,38,NULL,NULL,NULL),('','\0',NULL,2,'',NULL,NULL,NULL,39,NULL,NULL,NULL),('','\0',NULL,2,'',NULL,NULL,NULL,40,NULL,NULL,NULL),('','\0',NULL,2,'',NULL,NULL,NULL,41,NULL,NULL,NULL);
/*!40000 ALTER TABLE `abstractProducts` ENABLE KEYS */;
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
  UNIQUE KEY `UK_in4icgdheslo1nkh0a3quxd8` (`base_attributes_id`),
  UNIQUE KEY `UK_6jc58umpvkg33hh668o1jwe2h` (`store_id`),
  UNIQUE KEY `UK_rvkp5o3un1p2mxkmfy1oct8ks` (`uuid`),
  CONSTRAINT `FK_in4icgdheslo1nkh0a3quxd8` FOREIGN KEY (`base_attributes_id`) REFERENCES `base_attributes` (`base_attributes_id`)
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
  UNIQUE KEY `UK_konlpfc7mtdq27hmvkn8067vk` (`uuid`),
  KEY `FK_hua9x1fc4km07ge39uy0q0qed` (`description_stringid`),
  CONSTRAINT `FK_hua9x1fc4km07ge39uy0q0qed` FOREIGN KEY (`description_stringid`) REFERENCES `multilingual_string` (`Id`)
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
  `published` bit(1) NOT NULL,
  `ctentry_id` bigint(20) NOT NULL,
  PRIMARY KEY (`ctentry_id`),
  CONSTRAINT `FK_gdnv8qhckd8v7j2n7xiyg9fuq` FOREIGN KEY (`ctentry_id`) REFERENCES `ctentry` (`ctentry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brands`
--

LOCK TABLES `brands` WRITE;
/*!40000 ALTER TABLE `brands` DISABLE KEYS */;
INSERT INTO `brands` (`logo_src`, `published`, `ctentry_id`) VALUES (NULL,'',18),(NULL,'',19),(NULL,'',20),(NULL,'',21),(NULL,'',22),(NULL,'',23),(NULL,'',24),(NULL,'',25);
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
  `published` bit(1) NOT NULL,
  `name_string_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`catalog_id`),
  UNIQUE KEY `UK_po9uve1lmp31xamrsvg47oiim` (`uuid`),
  KEY `FK_adll41cnjdmseeuvp41y01c9n` (`name_string_id`),
  CONSTRAINT `FK_adll41cnjdmseeuvp41y01c9n` FOREIGN KEY (`name_string_id`) REFERENCES `multilingual_string` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalog`
--

LOCK TABLES `catalog` WRITE;
/*!40000 ALTER TABLE `catalog` DISABLE KEYS */;
INSERT INTO `catalog` (`catalog_id`, `created`, `updated`, `uuid`, `code`, `published`, `name_string_id`) VALUES (1,'2016-03-07 14:42:25','2016-03-07 14:42:25','baa13f9f-5420-4b45-b668-669d3fcb7758','default-catalog','',NULL);
/*!40000 ALTER TABLE `catalog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `published` bit(1) NOT NULL,
  `ctentry_id` bigint(20) NOT NULL,
  PRIMARY KEY (`ctentry_id`),
  CONSTRAINT `FK_nvwqioqkwksu7gr23ivhv898q` FOREIGN KEY (`ctentry_id`) REFERENCES `ctentry` (`ctentry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` (`published`, `ctentry_id`) VALUES ('',1),('',2),('',3),('',4),('',5),('',6),('',7),('',8),('',9),('',10),('',11),('',12),('',13),('',14),('',15),('',16),('',17);
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
  UNIQUE KEY `UK_4bnmhh89pd04phhcvbox9m6us` (`uuid`),
  KEY `FK_8r6rhjm7wpjiyqoj8wth0qfat` (`description_stringid`),
  KEY `FK_b6ttcldnxmwrk7eb7ly4y7t6i` (`geocode_id`),
  CONSTRAINT `FK_8r6rhjm7wpjiyqoj8wth0qfat` FOREIGN KEY (`description_stringid`) REFERENCES `multilingual_string` (`Id`),
  CONSTRAINT `FK_b6ttcldnxmwrk7eb7ly4y7t6i` FOREIGN KEY (`geocode_id`) REFERENCES `geocode` (`geocode_id`)
) ENGINE=InnoDB AUTO_INCREMENT=250 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `countries`
--

LOCK TABLES `countries` WRITE;
/*!40000 ALTER TABLE `countries` DISABLE KEYS */;
INSERT INTO `countries` (`countries_id`, `created`, `updated`, `uuid`, `iso2_code`, `iso3_code`, `iso_num_code`, `description_stringid`, `geocode_id`) VALUES (1,'2016-03-07 16:33:55','2016-03-07 16:33:55','3665ab99-2833-434d-9bfe-1f55e96625e0','AF','AFG',4,122,NULL),(2,'2016-03-07 16:33:55','2016-03-07 16:33:55','b55d2900-41aa-43b7-9a75-fa89f53847a4','AL','ALB',8,123,NULL),(3,'2016-03-07 16:33:55','2016-03-07 16:33:55','19bc9b95-738c-4468-a2d1-68c7cd35f377','DZ','DZA',12,124,NULL),(4,'2016-03-07 16:33:55','2016-03-07 16:33:55','1e131bbc-7ca7-4d01-bdbf-785b498153bf','AD','AND',20,125,NULL),(5,'2016-03-07 16:33:55','2016-03-07 16:33:55','1aea01bb-30f6-460f-99cc-315ac8d4516b','AO','AGO',24,126,NULL),(6,'2016-03-07 16:33:56','2016-03-07 16:33:56','0249ac29-395e-40cb-9584-d9e76b911e00','AI','AIA',660,127,NULL),(7,'2016-03-07 16:33:56','2016-03-07 16:33:56','89b0b4d3-611e-483c-95f2-662bdceb9f65','AQ','ATA',10,128,NULL),(8,'2016-03-07 16:33:56','2016-03-07 16:33:56','fbc5945b-b3c3-4312-9a49-81b3f28ea442','AG','ATG',28,129,NULL),(9,'2016-03-07 16:33:56','2016-03-07 16:33:56','298327d3-c409-45dc-b0cb-8bd5ff95039f','SA','SAU',682,130,NULL),(10,'2016-03-07 16:33:56','2016-03-07 16:33:56','16d855c3-d6db-4442-8907-01c6ead02c8e','AR','ARG',32,131,NULL),(11,'2016-03-07 16:33:56','2016-03-07 16:33:56','dbb65b97-67f4-4e58-bbc3-7f5e28f28041','AM','ARM',51,132,NULL),(12,'2016-03-07 16:33:56','2016-03-07 16:33:56','e3e60c38-7081-4764-9c40-63fcedaa96b4','AW','ABW',533,133,NULL),(13,'2016-03-07 16:33:56','2016-03-07 16:33:56','2605401e-d79e-44d8-91f3-7551f1d05e3a','AU','AUS',36,134,NULL),(14,'2016-03-07 16:33:56','2016-03-07 16:33:56','a247621d-62a4-493a-b2f4-297d63d6a24a','AT','AUT',40,135,NULL),(15,'2016-03-07 16:33:57','2016-03-07 16:33:57','6701da8d-5270-47ca-a3dd-c8f8b24e6f96','AZ','AZE',31,136,NULL),(16,'2016-03-07 16:33:57','2016-03-07 16:33:57','e10308ca-00e0-4272-bc98-51d478d252c0','BS','BHS',44,137,NULL),(17,'2016-03-07 16:33:57','2016-03-07 16:33:57','b631d057-4a49-4f9a-96a6-fa311308be85','BH','BHR',48,138,NULL),(18,'2016-03-07 16:33:57','2016-03-07 16:33:57','8f06ce94-4daf-461a-ba19-211827cf625f','BD','BGD',50,139,NULL),(19,'2016-03-07 16:33:57','2016-03-07 16:33:57','2e952f0e-1ec9-4560-9941-e16ff4ccbb54','BB','BRB',52,140,NULL),(20,'2016-03-07 16:33:57','2016-03-07 16:33:57','6ca0efbc-517a-40e2-99d2-644c53c7dcd7','BE','BEL',56,141,NULL),(21,'2016-03-07 16:33:57','2016-03-07 16:33:57','cf418972-c685-4dcc-86b8-de53092d8d3f','BZ','BLZ',84,142,NULL),(22,'2016-03-07 16:33:57','2016-03-07 16:33:57','fb9543b2-84a8-4ba7-9763-41f81d835cac','BJ','BEN',204,143,NULL),(23,'2016-03-07 16:33:58','2016-03-07 16:33:58','e81c9583-a439-4dd7-94a7-3b4324a8106d','BM','BMU',60,144,NULL),(24,'2016-03-07 16:33:58','2016-03-07 16:33:58','87b1d8e3-795b-47b8-a3fe-3a420d9c8257','BT','BTN',64,145,NULL),(25,'2016-03-07 16:33:58','2016-03-07 16:33:58','ba6c1df6-f0ff-449a-98ae-66750d80d49d','BY','BLR',112,146,NULL),(26,'2016-03-07 16:33:58','2016-03-07 16:33:58','3db31081-ab90-4a75-8412-6dfc1131255e','MM','MMR',104,147,NULL),(27,'2016-03-07 16:33:58','2016-03-07 16:33:58','ed044ecf-64ed-41a4-8e26-62eb3976e79b','BO','BOL',68,148,NULL),(28,'2016-03-07 16:33:58','2016-03-07 16:33:58','6acd7735-c6ad-4df2-bb15-e7d2f64e2a98','BA','BIH',70,149,NULL),(29,'2016-03-07 16:33:58','2016-03-07 16:33:58','28a1aa32-8a82-45d6-9b6e-e2d87fd8b57b','BW','BWA',72,150,NULL),(30,'2016-03-07 16:33:58','2016-03-07 16:33:58','89d584b5-931e-40e4-b52c-d5f42e3209e4','BR','BRA',76,151,NULL),(31,'2016-03-07 16:33:58','2016-03-07 16:33:58','39e93d6a-fbce-4d04-b57b-ad7fa04abe1b','BN','BRN',96,152,NULL),(32,'2016-03-07 16:33:58','2016-03-07 16:33:58','0ef8163d-44af-4ea8-b033-5b89edb3c29c','BG','BGR',100,153,NULL),(33,'2016-03-07 16:33:59','2016-03-07 16:33:59','fba335a7-ae54-4687-b425-81b1eb756fdb','BF','BFA',854,154,NULL),(34,'2016-03-07 16:33:59','2016-03-07 16:33:59','8896050e-8296-4864-b5fb-c74dace2784b','BI','BDI',108,155,NULL),(35,'2016-03-07 16:33:59','2016-03-07 16:33:59','25387c0e-8b41-4835-9d26-bc6ca4d4adc5','KH','KHM',116,156,NULL),(36,'2016-03-07 16:33:59','2016-03-07 16:33:59','1a5b593d-1f4c-4afe-9575-11f0f431be95','CM','CMR',120,157,NULL),(37,'2016-03-07 16:33:59','2016-03-07 16:33:59','d6839cf9-3a5e-4578-a267-13c51426878b','CA','CAN',124,158,NULL),(38,'2016-03-07 16:33:59','2016-03-07 16:33:59','f3ef3843-6276-4bac-9249-e8633167bc0d','CV','CPV',132,159,NULL),(39,'2016-03-07 16:33:59','2016-03-07 16:33:59','3398a472-dbd7-4925-adb5-0eadfab94362','TD','TCD',148,160,NULL),(40,'2016-03-07 16:33:59','2016-03-07 16:33:59','c071a191-fe86-4b5a-869d-45346b151811','CL','CHL',152,161,NULL),(41,'2016-03-07 16:34:00','2016-03-07 16:34:00','8cd74bb1-4a84-446b-955c-d9c0840143f5','CN','CHN',156,162,NULL),(42,'2016-03-07 16:34:00','2016-03-07 16:34:00','f6254d48-7101-46de-af80-aae6950dde48','CY','CYP',196,163,NULL),(43,'2016-03-07 16:34:00','2016-03-07 16:34:00','81f69eba-4129-4b1c-9543-94148cf7ab16','VA','VAT',336,164,NULL),(44,'2016-03-07 16:34:00','2016-03-07 16:34:00','b1279aab-b376-4509-8c83-fa7826a18270','CO','COL',170,165,NULL),(45,'2016-03-07 16:34:00','2016-03-07 16:34:00','419eabd8-4a2d-4b0e-868c-4299b9571130','KM','COM',174,166,NULL),(46,'2016-03-07 16:34:00','2016-03-07 16:34:00','1b3636fd-93fa-4782-b7a3-f8439ca2c652','KP','PRK',408,167,NULL),(47,'2016-03-07 16:34:00','2016-03-07 16:34:00','2dbfcee0-2c69-45e6-ac83-f7768561659e','KR','KOR',410,168,NULL),(48,'2016-03-07 16:34:00','2016-03-07 16:34:00','d5f4f0f0-e152-4f61-a119-4c99a739a6d0','CI','CIV',384,169,NULL),(49,'2016-03-07 16:34:00','2016-03-07 16:34:00','845dfe5e-671e-4971-aede-72bf60f4a20a','CR','CRI',188,170,NULL),(50,'2016-03-07 16:34:01','2016-03-07 16:34:01','3cada423-7fba-494b-ad52-7e7fbdc0a8f9','HR','HRV',191,171,NULL),(51,'2016-03-07 16:34:01','2016-03-07 16:34:01','d548d19f-febd-4162-b7af-99863953c5ac','CU','CUB',192,172,NULL),(52,'2016-03-07 16:34:01','2016-03-07 16:34:01','7f09d2a6-3444-4474-bad8-b90f2504a66e','CW','CUW',531,173,NULL),(53,'2016-03-07 16:34:01','2016-03-07 16:34:01','50ee8646-ae12-480b-b771-44ae030647f3','DK','DNK',208,174,NULL),(54,'2016-03-07 16:34:01','2016-03-07 16:34:01','3ed893a9-b06b-4f7c-ba31-c67197b779a4','DM','DMA',212,175,NULL),(55,'2016-03-07 16:34:01','2016-03-07 16:34:01','35ed7359-4395-44b7-9bc1-b3763bff7064','EC','ECU',218,176,NULL),(56,'2016-03-07 16:34:01','2016-03-07 16:34:01','f26dadf3-f87e-48cc-ac3c-f85a067e5f61','EG','EGY',818,177,NULL),(57,'2016-03-07 16:34:01','2016-03-07 16:34:01','62f3662a-ee19-4acc-9963-eb026a744a2a','SV','SLV',222,178,NULL),(58,'2016-03-07 16:34:01','2016-03-07 16:34:01','bafae9d8-579b-44e8-a77a-bf85254ee9a6','AE','ARE',784,179,NULL),(59,'2016-03-07 16:34:02','2016-03-07 16:34:02','82b75bec-3a58-4bda-8efc-55c35ec3e042','ER','ERI',232,180,NULL),(60,'2016-03-07 16:34:02','2016-03-07 16:34:02','f9e4a61c-f5ef-477e-abf1-1bce1eadc5d3','EE','EST',233,181,NULL),(61,'2016-03-07 16:34:02','2016-03-07 16:34:02','e763a408-dc08-4186-80c2-f434e799d93a','ET','ETH',231,182,NULL),(62,'2016-03-07 16:34:02','2016-03-07 16:34:02','f5753dad-5a49-466e-ae7f-caf56b6ba1cf','FJ','FJI',242,183,NULL),(63,'2016-03-07 16:34:02','2016-03-07 16:34:02','8303739b-33e6-45d3-ad25-ca38b2db814d','PH','PHL',608,184,NULL),(64,'2016-03-07 16:34:02','2016-03-07 16:34:02','947797d1-f683-475a-8f1a-4ab7d26a1c32','FI','FIN',246,185,NULL),(65,'2016-03-07 16:34:02','2016-03-07 16:34:02','7c02761b-0c4f-48f1-aba2-977df37609c9','FR','FRA',250,186,NULL),(66,'2016-03-07 16:34:02','2016-03-07 16:34:02','4f741021-ab56-42a2-b9b5-904e5c51be3f','GA','GAB',266,187,NULL),(67,'2016-03-07 16:34:02','2016-03-07 16:34:02','999c45e4-433e-4009-93a8-422fdf5e1513','GM','GMB',270,188,NULL),(68,'2016-03-07 16:34:03','2016-03-07 16:34:03','e1428bee-7636-4ff6-bd35-b3e4d02d0025','GE','GEO',268,189,NULL),(69,'2016-03-07 16:34:03','2016-03-07 16:34:03','6460191a-41a1-49b8-9fd9-7081e74a4be3','GS','SGS',239,190,NULL),(70,'2016-03-07 16:34:03','2016-03-07 16:34:03','0c068372-5a39-44de-a2c7-8deef1efbf25','DE','DEU',276,191,NULL),(71,'2016-03-07 16:34:03','2016-03-07 16:34:03','4c63dbc7-8dad-4892-957e-4ed0b321183b','GH','GHA',288,192,NULL),(72,'2016-03-07 16:34:03','2016-03-07 16:34:03','b75f1af0-026c-4e5e-81e4-3bb9c8d7beff','JM','JAM',388,193,NULL),(73,'2016-03-07 16:34:03','2016-03-07 16:34:03','18c5e0e2-61c9-457d-89f6-c51755599b31','JP','JPN',392,194,NULL),(74,'2016-03-07 16:34:03','2016-03-07 16:34:03','34ff9cf7-68e1-48bd-93f9-24db2cba3212','GI','GIB',292,195,NULL),(75,'2016-03-07 16:34:03','2016-03-07 16:34:03','a26b18db-7193-4708-b848-65cc287b3697','DJ','DJI',262,196,NULL),(76,'2016-03-07 16:34:03','2016-03-07 16:34:03','b57053c5-5993-4cfe-b899-2f1d738b6807','JO','JOR',400,197,NULL),(77,'2016-03-07 16:34:03','2016-03-07 16:34:03','7f03203a-30dd-4c25-9f08-3eb4de7e88a6','GR','GRC',300,198,NULL),(78,'2016-03-07 16:34:04','2016-03-07 16:34:04','dec19046-42a3-47c5-bc88-e657270c5303','GD','GRD',308,199,NULL),(79,'2016-03-07 16:34:04','2016-03-07 16:34:04','d5b38450-42d8-4aad-a642-00e4d5bafcbb','GL','GRL',304,200,NULL),(80,'2016-03-07 16:34:04','2016-03-07 16:34:04','7bfaf445-4c24-4f04-ae68-5cc088767c58','GP','GLP',312,201,NULL),(81,'2016-03-07 16:34:04','2016-03-07 16:34:04','c823aab8-d16e-4a29-b95c-458d2d61b9a6','GU','GUM',316,202,NULL),(82,'2016-03-07 16:34:04','2016-03-07 16:34:04','571ee6ce-640c-4bb1-a5d8-65c3043249e6','GT','GTM',320,203,NULL),(83,'2016-03-07 16:34:04','2016-03-07 16:34:04','67c488e2-409a-4eef-bc89-761e7fceca47','GG','GGY',831,204,NULL),(84,'2016-03-07 16:34:04','2016-03-07 16:34:04','2ea66b22-5dd3-4800-ae5b-4011efc04013','GN','GIN',324,205,NULL),(85,'2016-03-07 16:34:04','2016-03-07 16:34:04','dd023449-3524-44dd-8214-d777a54410ec','GW','GNB',624,206,NULL),(86,'2016-03-07 16:34:05','2016-03-07 16:34:05','d00ea814-1f45-44a4-a860-b7f69efff20f','GQ','GNQ',226,207,NULL),(87,'2016-03-07 16:34:05','2016-03-07 16:34:05','ceeafc20-b856-44ee-ae38-f1bfeb69ef53','GY','GUY',328,208,NULL),(88,'2016-03-07 16:34:05','2016-03-07 16:34:05','b5eb21f0-4de9-4d7c-ba6b-cc7e21da83e2','GF','GUF',254,209,NULL),(89,'2016-03-07 16:34:05','2016-03-07 16:34:05','2937a077-3a3d-4a56-bc0d-b13b5a5b29ee','HT','HTI',332,210,NULL),(90,'2016-03-07 16:34:05','2016-03-07 16:34:05','374a116d-474f-49a1-8d7e-858a3450236f','HN','HND',340,211,NULL),(91,'2016-03-07 16:34:05','2016-03-07 16:34:05','23cf7892-f0c2-404b-a3e3-7b0da9c57f9e','HK','HKG',344,212,NULL),(92,'2016-03-07 16:34:05','2016-03-07 16:34:05','241fa0cf-10dc-49fb-b63b-d030045df85a','IN','IND',356,213,NULL),(93,'2016-03-07 16:34:05','2016-03-07 16:34:05','fc5b52f4-82d8-4595-a7df-475030381716','ID','IDN',360,214,NULL),(94,'2016-03-07 16:34:05','2016-03-07 16:34:05','79a6ab88-a0af-4718-8aab-dc45a2acde77','IR','IRN',364,215,NULL),(95,'2016-03-07 16:34:06','2016-03-07 16:34:06','1686b7fd-6830-4e8b-bd21-3a4d976d39c8','IQ','IRQ',368,216,NULL),(96,'2016-03-07 16:34:06','2016-03-07 16:34:06','5d73a18f-4387-46de-95cb-69fb8d38f4ca','IE','IRL',372,217,NULL),(97,'2016-03-07 16:34:06','2016-03-07 16:34:06','490b87d6-3241-48d0-945f-37e5eeb94ff8','IS','ISL',352,218,NULL),(98,'2016-03-07 16:34:06','2016-03-07 16:34:06','ab4411f4-a374-41c5-9b1b-674b87f38030','BV','BVT',74,219,NULL),(99,'2016-03-07 16:34:06','2016-03-07 16:34:06','62555158-34b0-4897-a1e4-1fca169f79b2','IM','IMN',833,220,NULL),(100,'2016-03-07 16:34:06','2016-03-07 16:34:06','365a257c-a36d-4660-ac71-340e7bdbebdb','CX','CXR',162,221,NULL),(101,'2016-03-07 16:34:06','2016-03-07 16:34:06','227643a3-d915-4978-9d23-7bb06faf47b1','NF','NFK',574,222,NULL),(102,'2016-03-07 16:34:06','2016-03-07 16:34:06','4ba47958-29fe-498f-9548-595de5628d56','AX','ALA',248,223,NULL),(103,'2016-03-07 16:34:06','2016-03-07 16:34:06','6fe38e26-0844-443f-9dfe-5ffee99ff95d','BQ','BES',535,224,NULL),(104,'2016-03-07 16:34:07','2016-03-07 16:34:07','9cfb75d5-c501-4cf0-841a-01d88ea33b2b','KY','CYM',136,225,NULL),(105,'2016-03-07 16:34:07','2016-03-07 16:34:07','90f25f38-29d4-4dc0-97e3-c7082b605a96','CC','CCK',166,226,NULL),(106,'2016-03-07 16:34:07','2016-03-07 16:34:07','3115031a-f328-4ae4-816e-d041f391ef35','CK','COK',184,227,NULL),(107,'2016-03-07 16:34:07','2016-03-07 16:34:07','1a7763b2-7986-44f0-b8be-78b2764c6819','FO','FRO',234,228,NULL),(108,'2016-03-07 16:34:07','2016-03-07 16:34:07','c1a1e88b-fec7-417e-9e40-0132a3f6d608','FK','FLK',238,229,NULL),(109,'2016-03-07 16:34:07','2016-03-07 16:34:07','6b936a52-c5a8-4332-b0a0-c5d1d65255f5','HM','HMD',334,230,NULL),(110,'2016-03-07 16:34:07','2016-03-07 16:34:07','888386a9-85c6-4c9c-bfae-96c1128f30c7','MP','MNP',580,231,NULL),(111,'2016-03-07 16:34:07','2016-03-07 16:34:07','377eb177-3bce-4a0b-8b25-8a3e1dc53768','MH','MHL',584,232,NULL),(112,'2016-03-07 16:34:07','2016-03-07 16:34:07','ad60da59-febe-4b37-bb10-26f4aaaebf33','UM','UMI',581,233,NULL),(113,'2016-03-07 16:34:08','2016-03-07 16:34:08','be9ae698-e87b-4c68-bd3a-8fbac539a1e5','PN','PCN',612,234,NULL),(114,'2016-03-07 16:34:08','2016-03-07 16:34:08','d580fac7-33e8-48c0-ae90-9f9aa0373e06','SB','SLB',90,235,NULL),(115,'2016-03-07 16:34:08','2016-03-07 16:34:08','906fb5ba-5c15-41c0-a36b-75aa94044f56','VG','VGB',92,236,NULL),(116,'2016-03-07 16:34:08','2016-03-07 16:34:08','a0ad6f8e-a813-47a6-a54f-8ac4fe3915b0','VI','VIR',850,237,NULL),(117,'2016-03-07 16:34:08','2016-03-07 16:34:08','11c5e073-a009-473c-b7a4-2bba1d6b3376','IL','ISR',376,238,NULL),(118,'2016-03-07 16:34:08','2016-03-07 16:34:08','45f30428-531b-42ec-bfcd-6d472db2ef52','IT','ITA',380,239,NULL),(119,'2016-03-07 16:34:08','2016-03-07 16:34:08','bae8c81c-71b4-44c5-8c18-d21616c5735b','JE','JEY',832,240,NULL),(120,'2016-03-07 16:34:08','2016-03-07 16:34:08','fb505da0-37c2-4c28-b03e-c53510c2fdba','KZ','KAZ',398,241,NULL),(121,'2016-03-07 16:34:09','2016-03-07 16:34:09','b25bcbd1-a6b9-41e2-85f7-b7dfeed0f5cd','KE','KEN',404,242,NULL),(122,'2016-03-07 16:34:09','2016-03-07 16:34:09','9dca480e-58dd-451f-bfa2-c04da08906bd','KG','KGZ',417,243,NULL),(123,'2016-03-07 16:34:09','2016-03-07 16:34:09','be5995c7-411e-4a60-990f-da752d2c970f','KI','KIR',296,244,NULL),(124,'2016-03-07 16:34:09','2016-03-07 16:34:09','4ff7dc13-eb26-4cef-964c-c888235ac50a','KW','KWT',414,245,NULL),(125,'2016-03-07 16:34:09','2016-03-07 16:34:09','9eeb530e-b414-4299-b637-931a22b9877f','LA','LAO',418,246,NULL),(126,'2016-03-07 16:34:09','2016-03-07 16:34:09','f3f5f479-42df-4640-b1d7-7ff0c34c713b','LS','LSO',426,247,NULL),(127,'2016-03-07 16:34:09','2016-03-07 16:34:09','20de42fb-2f27-42e3-a1c8-0c7527c37d45','LV','LVA',428,248,NULL),(128,'2016-03-07 16:34:09','2016-03-07 16:34:09','e2b08b7f-6e75-4b09-89f6-4b1cc3040e4e','LB','LBN',422,249,NULL),(129,'2016-03-07 16:34:09','2016-03-07 16:34:09','10008f3d-47bc-4dc6-8754-2514bea303a2','LR','LBR',430,250,NULL),(130,'2016-03-07 16:34:10','2016-03-07 16:34:10','2f4c2db7-fdc1-4be5-ba7c-2b5dd6db7b84','LY','LBY',434,251,NULL),(131,'2016-03-07 16:34:10','2016-03-07 16:34:10','72d45a6d-0090-48fd-ad1e-dd1473ed7eb1','LI','LIE',438,252,NULL),(132,'2016-03-07 16:34:10','2016-03-07 16:34:10','2150eb5d-03ce-409f-81b7-3be3b5a8a6a0','LT','LTU',440,253,NULL),(133,'2016-03-07 16:34:10','2016-03-07 16:34:10','ed571e26-68d0-478d-9b09-78fcfb160ab5','LU','LUX',442,254,NULL),(134,'2016-03-07 16:34:10','2016-03-07 16:34:10','d87a61a9-61b2-4368-a039-2ca7a176f1de','MO','MAC',446,255,NULL),(135,'2016-03-07 16:34:10','2016-03-07 16:34:10','8c20c69e-33e3-4274-b588-44395d5d8ee5','MK','MKD',807,256,NULL),(136,'2016-03-07 16:34:10','2016-03-07 16:34:10','27692a51-9987-49bd-b14b-09e01db02547','MG','MDG',450,257,NULL),(137,'2016-03-07 16:34:10','2016-03-07 16:34:10','835ffd3b-5d47-4624-a395-fbb40b0fd068','MW','MWI',454,258,NULL),(138,'2016-03-07 16:34:10','2016-03-07 16:34:10','154c6bc8-4a60-4e0e-9fe6-f440851f1b84','MY','MYS',458,259,NULL),(139,'2016-03-07 16:34:11','2016-03-07 16:34:11','67ab86f7-2590-44a1-a253-dd41852dbded','MV','MDV',462,260,NULL),(140,'2016-03-07 16:34:11','2016-03-07 16:34:11','199d330c-b32e-44bc-ab59-d394eeaeccbe','ML','MLI',466,261,NULL),(141,'2016-03-07 16:34:11','2016-03-07 16:34:11','e7d12955-c404-45f5-8ecb-ffcfd0e0c17a','MT','MLT',470,262,NULL),(142,'2016-03-07 16:34:11','2016-03-07 16:34:11','c542473b-99e3-40e1-ab61-7d8bfd84223f','MA','MAR',504,263,NULL),(143,'2016-03-07 16:34:11','2016-03-07 16:34:11','bd8ab53f-bf18-4f13-8198-5cdab5a313be','MQ','MTQ',474,264,NULL),(144,'2016-03-07 16:34:11','2016-03-07 16:34:11','bcbe6859-069b-40e4-a94e-ba11837efd66','MR','MRT',478,265,NULL),(145,'2016-03-07 16:34:11','2016-03-07 16:34:11','14174711-85f3-4616-b68d-dab1650a1fec','MU','MUS',480,266,NULL),(146,'2016-03-07 16:34:11','2016-03-07 16:34:11','d633cf1f-5ac3-46aa-a131-25f403038494','YT','MYT',175,267,NULL),(147,'2016-03-07 16:34:12','2016-03-07 16:34:12','02d57a97-c7db-4145-ad4b-fb7f192dc487','MX','MEX',484,268,NULL),(148,'2016-03-07 16:34:12','2016-03-07 16:34:12','6f16e809-a0ca-4c0e-bf66-02b6c8a6ee2c','FM','FSM',583,269,NULL),(149,'2016-03-07 16:34:12','2016-03-07 16:34:12','4a879ed9-0a8c-4057-ba05-f32a10f18930','MD','MDA',498,270,NULL),(150,'2016-03-07 16:34:12','2016-03-07 16:34:12','658e62af-f3b1-4694-aed4-4417d07dd788','MN','MNG',496,271,NULL),(151,'2016-03-07 16:34:12','2016-03-07 16:34:12','1f893199-5c4b-4162-81ed-38988d535a51','ME','MNE',499,272,NULL),(152,'2016-03-07 16:34:12','2016-03-07 16:34:12','f953e94f-1ab9-4965-9f67-13b08d57bd5a','MS','MSR',500,273,NULL),(153,'2016-03-07 16:34:12','2016-03-07 16:34:12','851c38f6-6ad4-4c82-ae56-056d62e1cdf1','MZ','MOZ',508,274,NULL),(154,'2016-03-07 16:34:12','2016-03-07 16:34:12','16e81f9b-da60-44d8-a857-53e63e50321a','NA','NAM',516,275,NULL),(155,'2016-03-07 16:34:12','2016-03-07 16:34:12','ad906ed6-a8cc-40d9-910f-f73ed27b9a57','NR','NRU',520,276,NULL),(156,'2016-03-07 16:34:13','2016-03-07 16:34:13','f8f8f420-48c5-4546-abb1-2a8f4bfd2d17','NP','NPL',524,277,NULL),(157,'2016-03-07 16:34:13','2016-03-07 16:34:13','95f4b7ac-6bae-4ed6-861f-233729888465','NI','NIC',558,278,NULL),(158,'2016-03-07 16:34:13','2016-03-07 16:34:13','508a9f3b-f698-4d81-a5c6-853106c34ab4','NE','NER',562,279,NULL),(159,'2016-03-07 16:34:13','2016-03-07 16:34:13','9157ac2a-cd36-4fc6-8f03-0a0bc02baef5','NG','NGA',566,280,NULL),(160,'2016-03-07 16:34:13','2016-03-07 16:34:13','1c2fba85-dbef-4772-a1c5-6626ae2fd791','NU','NIU',570,281,NULL),(161,'2016-03-07 16:34:13','2016-03-07 16:34:13','642ef3cc-8278-495d-9e78-d027ecbf2eed','NO','NOR',578,282,NULL),(162,'2016-03-07 16:34:13','2016-03-07 16:34:13','fa461f52-c993-49f8-a573-24f86a5795f8','NC','NCL',540,283,NULL),(163,'2016-03-07 16:34:13','2016-03-07 16:34:13','ae5e0526-d066-4337-bdcc-61edf7f92d2d','NZ','NZL',554,284,NULL),(164,'2016-03-07 16:34:14','2016-03-07 16:34:14','cccf971c-a7ff-49df-8397-8ab77c72d0b7','OM','OMN',512,285,NULL),(165,'2016-03-07 16:34:14','2016-03-07 16:34:14','b19d21fa-4f34-421e-a50e-f5db99b60d18','NL','NLD',528,286,NULL),(166,'2016-03-07 16:34:14','2016-03-07 16:34:14','534a8467-5ac5-4371-bfdf-4682b6dcccd6','PK','PAK',586,287,NULL),(167,'2016-03-07 16:34:14','2016-03-07 16:34:14','96f47ef1-ed8d-495c-ab20-ce8428bca000','PW','PLW',585,288,NULL),(168,'2016-03-07 16:34:14','2016-03-07 16:34:14','cf5e0b4d-7f68-4113-8512-e9b26d1cb4ee','PS','PSE',275,289,NULL),(169,'2016-03-07 16:34:14','2016-03-07 16:34:14','914cb673-9834-453e-88ef-604ea9055b85','PA','PAN',591,290,NULL),(170,'2016-03-07 16:34:14','2016-03-07 16:34:14','4bf53dcc-2928-4d02-992d-ceeda2170469','PG','PNG',598,291,NULL),(171,'2016-03-07 16:34:15','2016-03-07 16:34:15','ae134702-ae15-4e59-a82c-44abc7cf3228','PY','PRY',600,292,NULL),(172,'2016-03-07 16:34:15','2016-03-07 16:34:15','91eb36f8-ae7d-4e12-b801-37d7b916e14e','PE','PER',604,293,NULL),(173,'2016-03-07 16:34:15','2016-03-07 16:34:15','f4305073-51fe-4cf5-836d-b77d4a56d88a','PF','PYF',258,294,NULL),(174,'2016-03-07 16:34:15','2016-03-07 16:34:15','02b99b63-fe58-4864-9ec8-f49f9124090e','PL','POL',616,295,NULL),(175,'2016-03-07 16:34:15','2016-03-07 16:34:15','be921d52-7d46-4225-8d7f-c3a1f89cb8aa','PR','PRI',630,296,NULL),(176,'2016-03-07 16:34:15','2016-03-07 16:34:15','182ec448-31f6-46ce-a87f-186b4aa283da','PT','PRT',620,297,NULL),(177,'2016-03-07 16:34:15','2016-03-07 16:34:15','aa6d4e48-4fa3-4516-9735-d343af2c21c1','MC','MCO',492,298,NULL),(178,'2016-03-07 16:34:15','2016-03-07 16:34:15','852657be-d099-4f95-95c0-96d251539222','QA','QAT',634,299,NULL),(179,'2016-03-07 16:34:16','2016-03-07 16:34:16','279cf466-633c-43af-a51d-1ccfb1ea8cc4','GB','GBR',826,300,NULL),(180,'2016-03-07 16:34:16','2016-03-07 16:34:16','07ce3c81-ed4a-4b67-8d95-3165009038ad','CD','COD',180,301,NULL),(181,'2016-03-07 16:34:16','2016-03-07 16:34:16','d2d63fd7-1766-4c4a-8bb3-ec004ddc9665','CZ','CZE',203,302,NULL),(182,'2016-03-07 16:34:16','2016-03-07 16:34:16','74dbeb1a-df20-43e5-902b-1e7a4371a775','CF','CAF',140,303,NULL),(183,'2016-03-07 16:34:16','2016-03-07 16:34:16','0bba9275-8151-4559-999c-65c568fbfa01','CG','COG',178,304,NULL),(184,'2016-03-07 16:34:16','2016-03-07 16:34:16','2cafd297-c166-4365-9a1a-f33e6f61628f','DO','DOM',214,305,NULL),(185,'2016-03-07 16:34:16','2016-03-07 16:34:16','63adabbe-8695-4e96-88af-a08c7f22d79c','RE','REU',638,306,NULL),(186,'2016-03-07 16:34:16','2016-03-07 16:34:16','6a26722b-80be-4757-ac20-f5a80108feee','RO','ROU',642,307,NULL),(187,'2016-03-07 16:34:17','2016-03-07 16:34:17','107c64f7-e3e9-4f45-a526-ab215d8d2872','RW','RWA',646,308,NULL),(188,'2016-03-07 16:34:17','2016-03-07 16:34:17','fd6810cb-bc10-4fb1-b7ef-a6a50dfb90e2','RU','RUS',643,309,NULL),(189,'2016-03-07 16:34:17','2016-03-07 16:34:17','598128b3-f17b-4fcd-af7d-5ab2af9a0382','EH','ESH',732,310,NULL),(190,'2016-03-07 16:34:17','2016-03-07 16:34:17','404e5555-fa81-44f8-8475-199c57eb0823','KN','KNA',659,311,NULL),(191,'2016-03-07 16:34:17','2016-03-07 16:34:17','7b9115f9-9e51-4785-b3e5-6211b7077552','LC','LCA',662,312,NULL),(192,'2016-03-07 16:34:17','2016-03-07 16:34:17','e74f7d80-49ab-4854-b748-330fac8647d8','SH','SHN',654,313,NULL),(193,'2016-03-07 16:34:17','2016-03-07 16:34:17','dccb489c-788b-43fe-b4f4-424ca5fb3ac9','VC','VCT',670,314,NULL),(194,'2016-03-07 16:34:17','2016-03-07 16:34:17','f739f774-e6f6-48c8-914b-89e99d404357','BL','BLM',652,315,NULL),(195,'2016-03-07 16:34:18','2016-03-07 16:34:18','2a559c77-4b51-4357-b38c-c2a0d16b2b50','MF','MAF',663,316,NULL),(196,'2016-03-07 16:34:18','2016-03-07 16:34:18','8f6f2e2e-0d87-4558-9ad6-1fc6c3a4babd','PM','SPM',666,317,NULL),(197,'2016-03-07 16:34:18','2016-03-07 16:34:18','84e852ae-d696-4c5f-96fc-f90de4ed71ee','WS','WSM',882,318,NULL),(198,'2016-03-07 16:34:18','2016-03-07 16:34:18','bba96166-e62a-4464-ae86-8e46e8198a83','AS','ASM',16,319,NULL),(199,'2016-03-07 16:34:18','2016-03-07 16:34:18','836ce358-a008-4f45-b1a6-d27b6575c901','SM','SMR',674,320,NULL),(200,'2016-03-07 16:34:18','2016-03-07 16:34:18','5a77bd9e-1019-4efa-9abc-4086ecfcdbe9','ST','STP',678,321,NULL),(201,'2016-03-07 16:34:19','2016-03-07 16:34:19','67c09f82-f620-4e84-b236-4328b0d305b0','SN','SEN',686,322,NULL),(202,'2016-03-07 16:34:19','2016-03-07 16:34:19','0a57e912-e03c-4989-861e-9cf9d1d3108d','RS','SRB',688,323,NULL),(203,'2016-03-07 16:34:19','2016-03-07 16:34:19','10df4d7c-4a68-4f24-9002-eee802bdc0d4','SC','SYC',690,324,NULL),(204,'2016-03-07 16:34:19','2016-03-07 16:34:19','51ea8475-e139-4f1b-ab5a-f0508cde6a11','SL','SLE',694,325,NULL),(205,'2016-03-07 16:34:19','2016-03-07 16:34:19','ad0a6b63-fe98-4d44-bd79-89330418c534','SG','SGP',702,326,NULL),(206,'2016-03-07 16:34:19','2016-03-07 16:34:19','a2fc2adc-1988-4f67-86c6-75c5de04baca','SX','SXM',534,327,NULL),(207,'2016-03-07 16:34:19','2016-03-07 16:34:19','7546c05e-8701-4210-8f5c-df49c29c3e06','SY','SYR',760,328,NULL),(208,'2016-03-07 16:34:20','2016-03-07 16:34:20','6e02d3a0-9e75-42bd-b298-e71df07047c5','SK','SVK',703,329,NULL),(209,'2016-03-07 16:34:20','2016-03-07 16:34:20','47ca3d86-6cf9-47dc-a78e-ff34ae81512c','SI','SVN',705,330,NULL),(210,'2016-03-07 16:34:20','2016-03-07 16:34:20','ab698823-c743-4860-b9fb-5022e3e916bd','SO','SOM',706,331,NULL),(211,'2016-03-07 16:34:20','2016-03-07 16:34:20','4e530167-d490-4d39-ab14-123e26489b97','ES','ESP',724,332,NULL),(212,'2016-03-07 16:34:20','2016-03-07 16:34:20','834cbb2b-791a-4ca6-97b4-f8a2a4df7d57','LK','LKA',144,333,NULL),(213,'2016-03-07 16:34:20','2016-03-07 16:34:20','9fdba27d-3c9a-4b8b-8667-5531a6543c61','US','USA',840,334,NULL),(214,'2016-03-07 16:34:20','2016-03-07 16:34:20','c848176e-993a-4b81-a45f-8685bdd1367b','ZA','ZAF',710,335,NULL),(215,'2016-03-07 16:34:20','2016-03-07 16:34:20','8fc0f705-ed82-480e-99b3-838e7332206e','SD','SDN',729,336,NULL),(216,'2016-03-07 16:34:21','2016-03-07 16:34:21','a0c61fc9-0705-430e-bd49-cf1c8b03b2d0','SS','SSD',728,337,NULL),(217,'2016-03-07 16:34:21','2016-03-07 16:34:21','3f7961cf-e66f-4f98-ac1c-153544ad194d','SR','SUR',740,338,NULL),(218,'2016-03-07 16:34:21','2016-03-07 16:34:21','6331310f-3650-435b-bb3f-d1915849a4b1','SJ','SJM',744,339,NULL),(219,'2016-03-07 16:34:21','2016-03-07 16:34:21','4b7185c6-3842-4510-b5c4-742b1ee3fa26','SE','SWE',752,340,NULL),(220,'2016-03-07 16:34:21','2016-03-07 16:34:21','426f9ccb-eff1-44a2-996d-2e93498a8c65','CH','CHE',756,341,NULL),(221,'2016-03-07 16:34:21','2016-03-07 16:34:21','7bda34a5-b576-4555-81c9-3662a0cee02e','SZ','SWZ',748,342,NULL),(222,'2016-03-07 16:34:21','2016-03-07 16:34:21','875d43d9-c5cc-41b7-874d-b1406a0318b4','TW','TWN',158,343,NULL),(223,'2016-03-07 16:34:21','2016-03-07 16:34:21','3a6c5db7-1983-4d3d-972f-0f5fb7c62ad6','TJ','TJK',762,344,NULL),(224,'2016-03-07 16:34:22','2016-03-07 16:34:22','d5241a98-b50c-4a09-b3fc-4f2384ac2572','TZ','TZA',834,345,NULL),(225,'2016-03-07 16:34:22','2016-03-07 16:34:22','52998a23-6d6c-40e1-9b7a-d74d7440a041','TF','ATF',260,346,NULL),(226,'2016-03-07 16:34:22','2016-03-07 16:34:22','ee1b83dd-5938-43f7-871e-8b490b2ac503','IO','IOT',86,347,NULL),(227,'2016-03-07 16:34:22','2016-03-07 16:34:22','40029c19-367d-45cc-a774-7fd8093ed847','TH','THA',764,348,NULL),(228,'2016-03-07 16:34:22','2016-03-07 16:34:22','2b09169e-8829-474c-bbeb-103179edc21c','TL','TLS',626,349,NULL),(229,'2016-03-07 16:34:22','2016-03-07 16:34:22','b536df46-e264-40c5-a8f6-26e803cc4da9','TG','TGO',768,350,NULL),(230,'2016-03-07 16:34:22','2016-03-07 16:34:22','70b76612-e3e1-4ef9-aef0-c14ea1f0ce5e','TK','TKL',772,351,NULL),(231,'2016-03-07 16:34:23','2016-03-07 16:34:23','43ce19c8-f498-4f9f-a232-d9f2a36f051d','TO','TON',776,352,NULL),(232,'2016-03-07 16:34:23','2016-03-07 16:34:23','70729980-a013-4b8d-8c53-21163a3a1210','TT','TTO',780,353,NULL),(233,'2016-03-07 16:34:24','2016-03-07 16:34:24','837a57c2-cd31-45be-a965-3ef808dd8668','TN','TUN',788,354,NULL),(234,'2016-03-07 16:34:25','2016-03-07 16:34:25','f08bd179-b3e4-47bf-bbd0-3d094a35d0f8','TR','TUR',792,355,NULL),(235,'2016-03-07 16:34:26','2016-03-07 16:34:26','43da13f2-7cf7-4d46-8a90-7f92792f6fc9','TM','TKM',795,356,NULL),(236,'2016-03-07 16:34:26','2016-03-07 16:34:26','cdca2889-9fb4-47c9-9f7f-d5f6c739f045','TC','TCA',796,357,NULL),(237,'2016-03-07 16:34:26','2016-03-07 16:34:26','20f38b29-6c25-4f95-bfa8-3910a967c4ee','TV','TUV',798,358,NULL),(238,'2016-03-07 16:34:26','2016-03-07 16:34:26','fb1ab90e-e0e8-43c6-9390-83834fb35b39','UA','UKR',804,359,NULL),(239,'2016-03-07 16:34:26','2016-03-07 16:34:26','15d0ebe2-a3ff-4deb-9155-b45f98716e5f','UG','UGA',800,360,NULL),(240,'2016-03-07 16:34:26','2016-03-07 16:34:26','367063ca-844a-444d-a217-c92055d420c5','HU','HUN',348,361,NULL),(241,'2016-03-07 16:34:26','2016-03-07 16:34:26','25874990-5971-4780-ba22-2db051aa2edb','UY','URY',858,362,NULL),(242,'2016-03-07 16:34:26','2016-03-07 16:34:26','0a7b5a22-c121-4133-a254-3ea98876e3a6','UZ','UZB',860,363,NULL),(243,'2016-03-07 16:34:27','2016-03-07 16:34:27','88a6c37e-1eca-4259-8bd8-37a26230eb34','VU','VUT',548,364,NULL),(244,'2016-03-07 16:34:27','2016-03-07 16:34:27','d04dc234-2859-498d-933e-1ad985e3174f','VE','VEN',862,365,NULL),(245,'2016-03-07 16:34:27','2016-03-07 16:34:27','406af562-618f-41c8-b022-9d354356c05c','VN','VNM',704,366,NULL),(246,'2016-03-07 16:34:27','2016-03-07 16:34:27','7b5398ae-7de6-4e4c-8388-5ae6737c53a6','WF','WLF',876,367,NULL),(247,'2016-03-07 16:34:27','2016-03-07 16:34:27','73dfe42e-ebda-4d22-be37-7b87ad8b2ca3','YE','YEM',887,368,NULL),(248,'2016-03-07 16:34:27','2016-03-07 16:34:27','95be509d-1950-4b72-9fee-1e915eabdb32','ZM','ZMB',894,369,NULL),(249,'2016-03-07 16:34:27','2016-03-07 16:34:27','b8477094-ec14-4371-b12a-993dddf1c545','ZW','ZWE',716,370,NULL);
/*!40000 ALTER TABLE `countries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ctentry`
--

DROP TABLE IF EXISTS `ctentry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ctentry` (
  `ctenytry_type` int(11) NOT NULL,
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
  `meta_description` bigint(20) DEFAULT NULL,
  `meta_keyword` bigint(20) DEFAULT NULL,
  `name_string_id` bigint(20) DEFAULT NULL,
  `primaryImage_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ctentry_id`),
  UNIQUE KEY `UK_ieekrq5rjhlo3jl5lbh03wsao` (`catalog_id`,`ctentry_type`,`code`),
  UNIQUE KEY `UK_ntqmjnbu61jq75mk5go3gre5u` (`uuid`),
  UNIQUE KEY `UK_nv8lo8hampi7vreepe2lkovuv` (`url`),
  KEY `FK_frb56du8afjon87o9552elevd` (`description_string_id`),
  KEY `FK_2xj78mhlmobcqiep6apknh5j2` (`meta_description`),
  KEY `FK_lurveq4wne9e10dll55ueax02` (`meta_keyword`),
  KEY `FK_gl1ojgd1gy1sh1hcbgfet8j2p` (`name_string_id`),
  KEY `FK_d9vjtyovrhckbtn10vmrge0jg` (`primaryImage_id`),
  CONSTRAINT `FK_2xj78mhlmobcqiep6apknh5j2` FOREIGN KEY (`meta_description`) REFERENCES `multilingual_string` (`Id`),
  CONSTRAINT `FK_av62j95yej3fyohknxqylrcr5` FOREIGN KEY (`catalog_id`) REFERENCES `catalog` (`catalog_id`),
  CONSTRAINT `FK_d9vjtyovrhckbtn10vmrge0jg` FOREIGN KEY (`primaryImage_id`) REFERENCES `ctentry_media` (`media_id`),
  CONSTRAINT `FK_frb56du8afjon87o9552elevd` FOREIGN KEY (`description_string_id`) REFERENCES `multilingual_text` (`Id`),
  CONSTRAINT `FK_gl1ojgd1gy1sh1hcbgfet8j2p` FOREIGN KEY (`name_string_id`) REFERENCES `multilingual_string` (`Id`),
  CONSTRAINT `FK_lurveq4wne9e10dll55ueax02` FOREIGN KEY (`meta_keyword`) REFERENCES `multilingual_string` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ctentry`
--

LOCK TABLES `ctentry` WRITE;
/*!40000 ALTER TABLE `ctentry` DISABLE KEYS */;
INSERT INTO `ctentry` (`ctenytry_type`, `ctentry_id`, `created`, `updated`, `uuid`, `code`, `ctentry_type`, `field1`, `field2`, `field3`, `url`, `catalog_id`, `description_string_id`, `meta_description`, `meta_keyword`, `name_string_id`, `primaryImage_id`) VALUES (1,1,'2016-03-07 14:50:58','2016-03-07 14:50:58','2cb42ecf-baf7-4ff5-81bf-5afd3b20bf76','WOM',1,NULL,NULL,NULL,'/default-catalog/c/WOM',1,1,NULL,NULL,80,1),(1,2,'2016-03-07 14:50:59','2016-03-07 14:50:59','595a4cae-8b76-4369-ab1e-305361fd9594','MAN',1,NULL,NULL,NULL,'/default-catalog/c/MAN',1,2,NULL,NULL,81,2),(1,3,'2016-03-07 14:50:59','2016-03-07 14:50:59','b398349d-d13b-4ac8-a7b4-76caf01f3464','KIDS',1,NULL,NULL,NULL,'/default-catalog/c/KIDS',1,3,NULL,NULL,82,3),(1,4,'2016-03-07 14:50:59','2016-03-07 14:50:59','bed204fc-44bb-4d4e-8ac0-3bb99adcefaa','WCLH',1,NULL,NULL,NULL,'/default-catalog/c/WCLH',1,4,NULL,NULL,83,4),(1,5,'2016-03-07 14:50:59','2016-03-07 14:50:59','4d5d1714-b63e-4d7f-926a-ccb937d4d870','MCLH',1,NULL,NULL,NULL,'/default-catalog/c/MCLH',1,5,NULL,NULL,84,5),(1,6,'2016-03-07 14:51:00','2016-03-07 14:51:00','ef41f55b-f380-4daa-9dda-34d37c5a264b','WDRESS',1,NULL,NULL,NULL,'/default-catalog/c/WDRESS',1,6,NULL,NULL,85,6),(1,7,'2016-03-07 14:51:00','2016-03-07 14:51:00','97b94be0-36cd-4fcd-9a57-68d15f2d302f','WTOP',1,NULL,NULL,NULL,'/default-catalog/c/WTOP',1,7,NULL,NULL,86,7),(1,8,'2016-03-07 14:51:00','2016-03-07 14:51:00','dc24ad1d-bf1d-4944-9089-cea8edba2798','WBAGS',1,NULL,NULL,NULL,'/default-catalog/c/WBAGS',1,8,NULL,NULL,87,8),(1,9,'2016-03-07 14:51:00','2016-03-07 14:51:00','4f1100c2-d57e-4e23-98ed-608168c7f011','MJACK',1,NULL,NULL,NULL,'/default-catalog/c/MJACK',1,9,NULL,NULL,88,9),(1,10,'2016-03-07 14:51:00','2016-03-07 14:51:08','4e2247d1-5834-4de1-b8fb-d73fd49cbd49','MJEANS',1,NULL,NULL,NULL,'/default-catalog/c/MJEANS',1,10,NULL,NULL,89,9),(1,11,'2016-03-07 14:51:00','2016-03-07 14:51:00','0a4dc9c7-d31a-468c-8060-df56501a3d47','MACC',1,NULL,NULL,NULL,'/default-catalog/c/MACC',1,11,NULL,NULL,90,10),(1,12,'2016-03-07 14:51:00','2016-03-07 14:51:00','8e9f1daf-faa6-469b-96ea-74f6e772cf60','KGIRL',1,NULL,NULL,NULL,'/default-catalog/c/KGIRL',1,12,NULL,NULL,91,NULL),(1,13,'2016-03-07 14:51:01','2016-03-07 14:51:01','aac36c40-17fe-4ef0-a8bb-90c883935d66','KBOY',1,NULL,NULL,NULL,'/default-catalog/c/KBOY',1,13,NULL,NULL,92,NULL),(1,14,'2016-03-07 14:51:01','2016-03-07 14:51:01','a43b6fc7-34dd-4473-a84a-90ff9b5b8277','GCLH',1,NULL,NULL,NULL,'/default-catalog/c/GCLH',1,14,NULL,NULL,93,12),(1,15,'2016-03-07 14:51:01','2016-03-07 14:51:01','80792f69-1d26-4e22-8d77-f17cdd323644','BCLH',1,NULL,NULL,NULL,'/default-catalog/c/BCLH',1,15,NULL,NULL,94,13),(1,16,'2016-03-07 14:51:01','2016-03-07 14:51:09','019b4d72-2918-4198-ad09-cab987123d9d','KG610',1,NULL,NULL,NULL,'/default-catalog/c/KG610',1,16,NULL,NULL,95,15),(1,17,'2016-03-07 14:51:01','2016-03-07 14:51:09','6f94c494-4901-409e-88f1-6170bbcfe9ec','KB610',1,NULL,NULL,NULL,'/default-catalog/c/KB610',1,17,NULL,NULL,96,15),(0,18,'2016-03-07 14:51:15','2016-03-07 14:51:15','0c93747d-d19d-47cc-a02d-f94faa0345a7','ADIDAS',0,NULL,NULL,NULL,'/default-catalog/b/ADIDAS',1,18,NULL,NULL,97,NULL),(0,19,'2016-03-07 14:51:16','2016-03-07 14:51:16','c88df2a3-202c-452e-9bbe-a87022e8ce89','MDUK',0,NULL,NULL,NULL,'/default-catalog/b/MDUK',1,19,NULL,NULL,98,NULL),(0,20,'2016-03-07 14:51:16','2016-03-07 14:51:16','6fe4e422-60bd-4f1b-9ed0-388fd4ac7795','LEVIS',0,NULL,NULL,NULL,'/default-catalog/b/LEVIS',1,20,NULL,NULL,99,NULL),(0,21,'2016-03-07 14:51:16','2016-03-07 14:51:16','30b4822f-e2f3-41c9-ab4c-09cdaf13c5f5','MELTP',0,NULL,NULL,NULL,'/default-catalog/b/MELTP',1,21,NULL,NULL,100,NULL),(0,22,'2016-03-07 14:51:16','2016-03-07 14:51:16','8004e595-20e1-42a5-9eb0-1d8b7f78086c','WRANG',0,NULL,NULL,NULL,'/default-catalog/b/WRANG',1,22,NULL,NULL,101,NULL),(0,23,'2016-03-07 14:51:16','2016-03-07 14:51:16','02334131-f325-4b09-a3f1-0744be1480fb','BELSTAFF',0,NULL,NULL,NULL,'/default-catalog/b/BELSTAFF',1,23,NULL,NULL,102,NULL),(0,24,'2016-03-07 14:51:17','2016-03-07 14:51:17','3f82bb9a-a94d-4513-832f-502a63ec880e','BRUMS',0,NULL,NULL,NULL,'/default-catalog/b/BRUMS',1,24,NULL,NULL,103,NULL),(0,25,'2016-03-07 14:51:17','2016-03-07 14:51:17','c363c1bc-a5b0-4862-bf50-e61d3c9c1e13','BNT',0,NULL,NULL,NULL,'/default-catalog/b/BNT',1,25,NULL,NULL,104,NULL),(3,26,'2016-03-07 14:51:23','2016-03-07 14:51:30','507719f5-da73-4c22-9bbc-4651042b69d2','Z001',3,NULL,NULL,NULL,'/default-catalog/p/Z001',1,26,NULL,NULL,105,17),(3,27,'2016-03-07 14:51:23','2016-03-07 14:51:30','9aa81ec4-0339-454a-af57-172a051d097e','Z002',3,NULL,NULL,NULL,'/default-catalog/p/Z002',1,27,NULL,NULL,106,21),(3,28,'2016-03-07 14:51:24','2016-03-07 14:51:30','01b0398a-519e-42eb-850f-a5de5c61b4b9','A001',3,NULL,NULL,NULL,'/default-catalog/p/A001',1,28,NULL,NULL,107,25),(3,29,'2016-03-07 14:51:24','2016-03-07 14:51:31','16812c85-c518-42c1-a637-6a2638351c04','M001',3,NULL,NULL,NULL,'/default-catalog/p/M001',1,29,NULL,NULL,108,31),(3,30,'2016-03-07 14:51:24','2016-03-07 14:51:24','5046ccb7-6453-41c5-855b-4c063508f4ee','L001',3,NULL,NULL,NULL,'/default-catalog/p/L001',1,30,NULL,NULL,109,33),(3,31,'2016-03-07 14:51:24','2016-03-07 14:51:24','b21f5358-d663-4f97-bb68-c9ed713f1e42','MP001',3,NULL,NULL,NULL,'/default-catalog/p/MP001',1,31,NULL,NULL,110,40),(3,32,'2016-03-07 14:51:24','2016-03-07 14:51:24','e0140ab0-0ca5-4263-9c03-0851cf778222','L002',3,NULL,NULL,NULL,'/default-catalog/p/L002',1,32,NULL,NULL,111,41),(3,33,'2016-03-07 14:51:24','2016-03-07 14:51:24','15fdb93b-2419-465d-8681-c6ea66025665','W7275DS12',3,NULL,NULL,NULL,'/default-catalog/p/W7275DS12',1,33,NULL,NULL,112,45),(3,34,'2016-03-07 14:51:25','2016-03-07 14:51:25','e2209ff9-1763-477e-a2a4-484fb6182ea3','W4611VPPP',3,NULL,NULL,NULL,'/default-catalog/p/W4611VPPP',1,34,NULL,NULL,113,49),(3,35,'2016-03-07 14:51:25','2016-03-07 14:51:25','8bfd339f-aabe-4eb8-8e5f-0e00584b3a83','BS001',3,NULL,NULL,NULL,'/default-catalog/p/BS001',1,35,NULL,NULL,114,53),(3,36,'2016-03-07 14:51:25','2016-03-07 14:51:25','40b96c80-af7b-4fa9-8a41-4667f55c980b','BS002',3,NULL,NULL,NULL,'/default-catalog/p/BS002',1,36,NULL,NULL,115,59),(3,37,'2016-03-07 14:51:25','2016-03-07 14:51:25','56fb62ee-8e74-4981-bf5e-996f38bc48dc','W0S04UF67',3,NULL,NULL,NULL,'/default-catalog/p/W0S04UF67',1,37,NULL,NULL,116,61),(3,38,'2016-03-07 14:51:25','2016-03-07 14:51:25','e3fd86e2-3acf-443e-9da2-7ebdb35c416c','BR001',3,NULL,NULL,NULL,'/default-catalog/p/BR001',1,38,NULL,NULL,117,64),(3,39,'2016-03-07 14:51:25','2016-03-07 14:51:25','0247bc03-82a5-4f17-a24d-6222f879d6ec','3TM4C13KP',3,NULL,NULL,NULL,'/default-catalog/p/3TM4C13KP',1,39,NULL,NULL,118,68),(3,40,'2016-03-07 14:51:26','2016-03-07 14:51:26','af9d8ef5-42d3-431b-8ad9-4075a7ccfe19','2ALC537E0',3,NULL,NULL,NULL,'/default-catalog/p/2ALC537E0',1,40,NULL,NULL,119,71),(3,41,'2016-03-07 14:51:26','2016-03-07 14:51:26','aea62176-334c-4422-b626-0244d704ae35','A002',3,NULL,NULL,NULL,'/default-catalog/p/A002',1,41,NULL,NULL,120,74);
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
  UNIQUE KEY `UK_c0lq6csq8oda7rsx688fum00e` (`uuid`),
  KEY `FK_c8obtgl8iht7she4484wocy4t` (`base_attribute_id`),
  KEY `FK_djbx3u0x84mt0gs7cspy0h0x7` (`ctentry_id`),
  CONSTRAINT `FK_c8obtgl8iht7she4484wocy4t` FOREIGN KEY (`base_attribute_id`) REFERENCES `base_attributes` (`base_attributes_id`),
  CONSTRAINT `FK_djbx3u0x84mt0gs7cspy0h0x7` FOREIGN KEY (`ctentry_id`) REFERENCES `ctentry` (`ctentry_id`)
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
  KEY `FK_6lqst9qmgoquifmuaycrd5iqg` (`ctentry_id`),
  CONSTRAINT `FK_6lqst9qmgoquifmuaycrd5iqg` FOREIGN KEY (`ctentry_id`) REFERENCES `ctentry` (`ctentry_id`),
  CONSTRAINT `FK_e743sel4olyq377fwt4r2k6yb` FOREIGN KEY (`media_id`) REFERENCES `media` (`media_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ctentry_media`
--

LOCK TABLES `ctentry_media` WRITE;
/*!40000 ALTER TABLE `ctentry_media` DISABLE KEYS */;
INSERT INTO `ctentry_media` (`media_id`, `ctentry_id`) VALUES (1,NULL),(2,NULL),(3,NULL),(4,NULL),(5,NULL),(6,NULL),(7,NULL),(8,NULL),(9,NULL),(10,NULL),(11,NULL),(12,NULL),(13,NULL),(14,NULL),(15,NULL),(79,NULL),(16,26),(17,26),(18,26),(19,26),(20,27),(21,27),(22,27),(23,27),(24,28),(25,28),(26,28),(27,28),(28,28),(29,28),(30,29),(31,29),(32,29),(33,30),(34,30),(35,30),(36,30),(37,30),(38,30),(39,31),(40,31),(41,32),(42,32),(43,32),(44,32),(45,33),(46,33),(47,33),(48,33),(49,34),(50,34),(51,34),(52,34),(53,35),(54,35),(55,35),(56,35),(57,35),(58,35),(59,36),(60,36),(61,37),(62,37),(63,37),(64,38),(65,38),(66,38),(67,38),(68,39),(69,39),(70,39),(71,40),(72,40),(73,40),(74,41),(75,41),(76,41),(77,41),(78,41);
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
  PRIMARY KEY (`currency_id`),
  UNIQUE KEY `UK_8joybdu0hdbcrigyk408trimp` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currency`
--

LOCK TABLES `currency` WRITE;
/*!40000 ALTER TABLE `currency` DISABLE KEYS */;
INSERT INTO `currency` (`currency_id`, `created`, `updated`, `uuid`, `descriprion`, `iso_code`) VALUES (1,'2016-03-07 14:42:23','2016-03-07 14:42:23','b6b9826f-ee61-4d49-985e-876a38c40bd2','Euro','EUR');
/*!40000 ALTER TABLE `currency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `district`
--

DROP TABLE IF EXISTS `district`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `district` (
  `district_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `isoCode` varchar(20) NOT NULL,
  `countries_id` bigint(20) NOT NULL,
  `description_stringid` bigint(20) DEFAULT NULL,
  `regions_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`district_id`),
  UNIQUE KEY `UK_ak6osygs4n1filjbae5s5dhgq` (`uuid`),
  UNIQUE KEY `UK_658j55nml7blyuayemsqrx83s` (`isoCode`),
  KEY `FK_mmd25th0mct55l71f99ek1ty3` (`countries_id`),
  KEY `FK_n7jwthjqg68kg5ovlb7ccbbsl` (`description_stringid`),
  KEY `FK_s2dmw70ae2nwivlscpbbjq8u3` (`regions_id`),
  CONSTRAINT `FK_mmd25th0mct55l71f99ek1ty3` FOREIGN KEY (`countries_id`) REFERENCES `countries` (`countries_id`),
  CONSTRAINT `FK_n7jwthjqg68kg5ovlb7ccbbsl` FOREIGN KEY (`description_stringid`) REFERENCES `multilingual_string` (`Id`),
  CONSTRAINT `FK_s2dmw70ae2nwivlscpbbjq8u3` FOREIGN KEY (`regions_id`) REFERENCES `regions` (`regions_id`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `district`
--

LOCK TABLES `district` WRITE;
/*!40000 ALTER TABLE `district` DISABLE KEYS */;
INSERT INTO `district` (`district_id`, `created`, `updated`, `uuid`, `isoCode`, `countries_id`, `description_stringid`, `regions_id`) VALUES (1,'2016-03-07 17:21:19','2016-03-07 17:21:19','3639f331-91c5-492f-83f2-c5a66f2e3acc','IT-AG',118,391,15),(2,'2016-03-07 17:21:19','2016-03-07 17:21:19','799818cf-74c0-4d1a-a173-fa8c17183c10','IT-AL',118,392,12),(3,'2016-03-07 17:21:19','2016-03-07 17:21:19','358fe234-d28f-467c-8c51-b35281232bd6','IT-AN',118,393,10),(4,'2016-03-07 17:21:19','2016-03-07 17:21:19','eca6c804-1cad-45af-8dd3-eee6fc153d3e','IT-AO',118,394,19),(5,'2016-03-07 17:21:20','2016-03-07 17:21:20','01380636-07df-4357-bb2a-98cb37d5c498','IT-AR',118,395,16),(6,'2016-03-07 17:21:20','2016-03-07 17:21:20','eeb98546-4ba9-4816-9e95-2bcba484cc39','IT-AP',118,396,10),(7,'2016-03-07 17:21:20','2016-03-07 17:21:20','22be0a3a-2776-4fca-9696-aa5dad21b563','IT-AT',118,397,12),(8,'2016-03-07 17:21:20','2016-03-07 17:21:20','681219a3-4195-42b7-85f8-abb6742c4e3a','IT-AV',118,398,4),(9,'2016-03-07 17:21:20','2016-03-07 17:21:20','c18717f8-3332-4d0b-90cf-e5a1a6b88a48','IT-BA',118,399,13),(10,'2016-03-07 17:21:20','2016-03-07 17:21:20','98d67ae3-853d-456c-b662-94395dd5e219','IT-BT',118,400,13),(11,'2016-03-07 17:21:20','2016-03-07 17:21:20','a0d06e95-6eab-4771-a30d-13299989f53e','IT-BL',118,401,20),(12,'2016-03-07 17:21:20','2016-03-07 17:21:20','30a20070-8d56-45c5-aa3f-d2d9d75a4a65','IT-BN',118,402,4),(13,'2016-03-07 17:21:20','2016-03-07 17:21:20','e110ac1f-5b61-4d47-b629-e1bbc4c2ff88','IT-BG',118,403,9),(14,'2016-03-07 17:21:20','2016-03-07 17:21:20','752c663a-f392-46fd-a6e0-6a0f0e2b13e7','IT-BI',118,404,12),(15,'2016-03-07 17:21:21','2016-03-07 17:21:21','18ef0911-5c17-436e-bc9d-90fa18d1c22e','IT-BO',118,405,5),(16,'2016-03-07 17:21:21','2016-03-07 17:21:21','e75194a3-d946-4c27-af19-99c966bf247e','IT-BZ',118,406,17),(17,'2016-03-07 17:21:21','2016-03-07 17:21:21','ee50575f-7be1-4920-851a-c310f2cf7a0f','IT-BS',118,407,9),(18,'2016-03-07 17:21:21','2016-03-07 17:21:21','0d2fe053-a57f-4d7b-835b-b8a0231ef787','IT-BR',118,408,13),(19,'2016-03-07 17:21:21','2016-03-07 17:21:21','6ef8065f-e82a-4bd4-99c3-14deb3e07e09','IT-CA',118,409,14),(20,'2016-03-07 17:21:21','2016-03-07 17:21:21','62a4f186-5f49-40be-8ac8-00677e993fd0','IT-CL',118,410,15),(21,'2016-03-07 17:21:21','2016-03-07 17:21:21','b213dd33-c813-4574-be5b-3d66cb7b7b3d','IT-CB',118,411,11),(22,'2016-03-07 17:21:21','2016-03-07 17:21:21','2c8f4df9-0eb9-4a35-8259-8bc31f324f73','IT-CI',118,412,14),(23,'2016-03-07 17:21:21','2016-03-07 17:21:21','f63e682f-82d0-4765-b645-1399f08ddea1','IT-CE',118,413,4),(24,'2016-03-07 17:21:22','2016-03-07 17:21:22','196c3a98-37af-4c82-bf41-ce0360e9a5fb','IT-CT',118,414,15),(25,'2016-03-07 17:21:22','2016-03-07 17:21:22','0911bf99-550b-48d4-8a73-c56c46b743b9','IT-CZ',118,415,3),(26,'2016-03-07 17:21:22','2016-03-07 17:21:22','0b493c0a-d778-4f11-a6aa-b9d6586e4265','IT-CH',118,416,1),(27,'2016-03-07 17:21:22','2016-03-07 17:21:22','e852d7b2-c2d7-4eea-a60f-75f00217150e','IT-CO',118,417,9),(28,'2016-03-07 17:21:22','2016-03-07 17:21:22','9ea9aded-d49c-43e5-8291-37795e9a3d94','IT-CS',118,418,3),(29,'2016-03-07 17:21:22','2016-03-07 17:21:22','1c362857-f49c-4848-88eb-cf64460891b6','IT-CR',118,419,9),(30,'2016-03-07 17:21:22','2016-03-07 17:21:22','453eb68a-bf2b-4c05-9c76-eacfdb199fd1','IT-KR',118,420,3),(31,'2016-03-07 17:21:22','2016-03-07 17:21:22','8dcacf32-7c21-487a-a447-24a8da6fd24c','IT-CN',118,421,12),(32,'2016-03-07 17:21:22','2016-03-07 17:21:22','896cfd2b-2376-4ffd-9726-8e77f6757bfa','IT-EN',118,422,15),(33,'2016-03-07 17:21:23','2016-03-07 17:21:23','7357c334-7471-464a-84e1-690d8e181c25','IT-FM',118,423,10),(34,'2016-03-07 17:21:23','2016-03-07 17:21:23','4e543049-97c6-4aab-8858-4c0a46a8db17','IT-FE',118,424,5),(35,'2016-03-07 17:21:23','2016-03-07 17:21:23','e94bfcae-b941-46cc-9d40-5f15fea9f7d3','IT-FI',118,425,16),(36,'2016-03-07 17:21:23','2016-03-07 17:21:23','10924552-64be-4bed-b20f-849166ec0fdf','IT-FG',118,426,13),(37,'2016-03-07 17:21:23','2016-03-07 17:21:23','55182985-9462-4119-8903-224b1bbea88f','IT-FC',118,427,5),(38,'2016-03-07 17:21:23','2016-03-07 17:21:23','a587150f-fede-4f22-af52-d4821bcdb0f3','IT-FR',118,428,7),(39,'2016-03-07 17:21:23','2016-03-07 17:21:23','e0f1cd33-89c7-4397-999d-3d66b5912ef6','IT-GE',118,429,8),(40,'2016-03-07 17:21:23','2016-03-07 17:21:23','dea094ac-5bdd-40b4-8fa9-b5a9e594b363','IT-GO',118,430,6),(41,'2016-03-07 17:21:23','2016-03-07 17:21:23','4562074d-4f3f-4cda-8fa5-89059d9e7cba','IT-GR',118,431,16),(42,'2016-03-07 17:21:24','2016-03-07 17:21:24','88063fcb-b339-43c0-99bb-251dbfcb5a71','IT-IM',118,432,8),(43,'2016-03-07 17:21:24','2016-03-07 17:21:24','5d3484cd-a01d-43d1-bb50-e17db7bd3385','IT-IS',118,433,11),(44,'2016-03-07 17:21:24','2016-03-07 17:21:24','68315fa3-1439-40a1-8025-4543315dfc18','IT-SP',118,434,8),(45,'2016-03-07 17:21:24','2016-03-07 17:21:24','c421075e-a528-42ad-a8d9-db328a703b34','IT-AQ',118,435,1),(46,'2016-03-07 17:21:24','2016-03-07 17:21:24','91a42a90-e506-41e2-9015-9b5585849434','IT-LT',118,436,7),(47,'2016-03-07 17:21:24','2016-03-07 17:21:24','38a9c07e-b30c-4b30-b47d-5b57735590aa','IT-LE',118,437,13),(48,'2016-03-07 17:21:24','2016-03-07 17:21:24','f152042e-e846-4145-9dda-37178360ca75','IT-LC',118,438,9),(49,'2016-03-07 17:21:24','2016-03-07 17:21:24','f3a65751-866b-480f-ab6b-271d56e778d3','IT-LI',118,439,16),(50,'2016-03-07 17:21:24','2016-03-07 17:21:24','290e896d-a6f7-4d83-b974-5088abe2ce14','IT-LO',118,440,9),(51,'2016-03-07 17:21:25','2016-03-07 17:21:25','ad124e7f-e981-48e0-81ff-cd7f989f1810','IT-LU',118,441,16),(52,'2016-03-07 17:21:25','2016-03-07 17:21:25','5189b398-e701-4693-8bd9-2c1f49a99995','IT-MC',118,442,10),(53,'2016-03-07 17:21:25','2016-03-07 17:21:25','a93ed231-8ecb-4f36-a717-2ee6b5a3141a','IT-MN',118,443,9),(54,'2016-03-07 17:21:25','2016-03-07 17:21:25','d17c7b17-882e-48e6-b6a0-6dc41b66dd56','IT-MS',118,444,16),(55,'2016-03-07 17:21:25','2016-03-07 17:21:25','c5c47f72-c87d-4c47-bd81-0d8387c45693','IT-MT',118,445,2),(56,'2016-03-07 17:21:25','2016-03-07 17:21:25','359f268d-eaf6-4ef9-a305-78e9f28e80a4','IT-VS',118,446,14),(57,'2016-03-07 17:21:25','2016-03-07 17:21:25','2c939d70-9479-4583-9fd2-06cdf9a4d511','IT-ME',118,447,15),(58,'2016-03-07 17:21:25','2016-03-07 17:21:25','a4c9e9cd-0d72-41da-89f7-6b1262ed0b95','IT-MB',118,448,9),(59,'2016-03-07 17:21:25','2016-03-07 17:21:25','03bb3240-6e3a-4c18-a557-9f30ef0d962e','IT-MI',118,449,9),(60,'2016-03-07 17:21:26','2016-03-07 17:21:26','51cc63b0-7816-438c-8d24-c2608515492d','IT-MO',118,450,5),(61,'2016-03-07 17:21:26','2016-03-07 17:21:26','30aa13ca-432a-460d-85e0-f8270a64fdad','IT-NA',118,451,4),(62,'2016-03-07 17:21:26','2016-03-07 17:21:26','52b764b8-70be-4d81-b3da-0dc1f782d591','IT-NO',118,452,12),(63,'2016-03-07 17:21:26','2016-03-07 17:21:26','a4ecf069-9a1a-4321-935a-f33097107e21','IT-NU',118,453,14),(64,'2016-03-07 17:21:26','2016-03-07 17:21:26','163db0fd-7511-49a5-ac81-a061be938562','IT-OG',118,454,14),(65,'2016-03-07 17:21:26','2016-03-07 17:21:26','a3857643-2fe2-499f-b5b8-ddff0f9f4346','IT-OT',118,455,14),(66,'2016-03-07 17:21:26','2016-03-07 17:21:26','389c7296-6b46-47cc-9336-1f0868911197','IT-OR',118,456,14),(67,'2016-03-07 17:21:26','2016-03-07 17:21:26','2f66d2a8-5e0c-410a-9208-f6fe5db92021','IT-PD',118,457,20),(68,'2016-03-07 17:21:27','2016-03-07 17:21:27','b556c6e4-7fc3-4b67-b4af-857fdb75f982','IT-PA',118,458,15),(69,'2016-03-07 17:21:27','2016-03-07 17:21:27','dde342e5-6c6f-4fb6-a142-674645a96b86','IT-PR',118,459,5),(70,'2016-03-07 17:21:27','2016-03-07 17:21:27','d5d0930c-0b81-4e19-b30b-3bf749e37dae','IT-PV',118,460,9),(71,'2016-03-07 17:21:27','2016-03-07 17:21:27','828dc78e-22eb-46f3-872f-a4a688483782','IT-PG',118,461,18),(72,'2016-03-07 17:21:27','2016-03-07 17:21:27','1fa0b335-de30-4e58-8b3b-2fc84835d421','IT-PU',118,462,10),(73,'2016-03-07 17:21:27','2016-03-07 17:21:27','86557018-9edd-4bb1-856f-a7598d1631c5','IT-PE',118,463,1),(74,'2016-03-07 17:21:27','2016-03-07 17:21:27','155636a0-0bb4-4dea-8699-bd34159d63c6','IT-PC',118,464,5),(75,'2016-03-07 17:21:27','2016-03-07 17:21:27','226d1858-cd5b-45da-b6b3-931a95d8dbc6','IT-PI',118,465,16),(76,'2016-03-07 17:21:28','2016-03-07 17:21:28','614f9156-e9fb-44fc-8f85-e780971105d2','IT-PT',118,466,16),(77,'2016-03-07 17:21:28','2016-03-07 17:21:28','fa4018fc-e741-4268-82cc-bd61c18ccdd7','IT-PN',118,467,6),(78,'2016-03-07 17:21:28','2016-03-07 17:21:28','d5efa12a-9e16-4e4a-a93e-a242c60a19e4','IT-PZ',118,468,2),(79,'2016-03-07 17:21:28','2016-03-07 17:21:28','dee40f6a-1a23-4360-a546-7a85e8ef799f','IT-PO',118,469,16),(80,'2016-03-07 17:21:28','2016-03-07 17:21:28','5b1fe447-6d38-4089-a4ee-9e99a6fe6211','IT-RG',118,470,15),(81,'2016-03-07 17:21:28','2016-03-07 17:21:28','5b2c3ae4-fb85-4c75-b289-c3e5310441e1','IT-RA',118,471,5),(82,'2016-03-07 17:21:28','2016-03-07 17:21:28','ec3ba8af-75ae-43c2-9824-a0b0d941d648','IT-RC',118,472,3),(83,'2016-03-07 17:21:28','2016-03-07 17:21:28','2adb988f-e36c-42a8-9476-0b208917a0a6','IT-RE',118,473,5),(84,'2016-03-07 17:21:29','2016-03-07 17:21:29','8893a200-c1ce-44c2-b07b-04f2f096f500','IT-RI',118,474,7),(85,'2016-03-07 17:21:29','2016-03-07 17:21:29','38cce6a8-bec6-49aa-9308-1d8b58aea5f3','IT-RN',118,475,5),(86,'2016-03-07 17:21:29','2016-03-07 17:21:29','06d8ca7f-f762-46e7-b39b-6ca254900dee','IT-RM',118,476,7),(87,'2016-03-07 17:21:29','2016-03-07 17:21:29','8561aab4-d4b6-4239-ba4e-af0184ddc0a6','IT-RO',118,477,20),(88,'2016-03-07 17:21:29','2016-03-07 17:21:29','66d8e39f-5b49-4534-b646-5e0b33eae016','IT-SA',118,478,4),(89,'2016-03-07 17:21:29','2016-03-07 17:21:29','92be2b30-76ff-42c7-827d-7f9c24d286aa','IT-SS',118,479,14),(90,'2016-03-07 17:21:29','2016-03-07 17:21:29','e8755f38-93cc-4940-ad43-892b5700c36f','IT-SV',118,480,8),(91,'2016-03-07 17:21:29','2016-03-07 17:21:29','665927fc-445a-45ed-b120-f5242c0b1a0a','IT-SI',118,481,16),(92,'2016-03-07 17:21:29','2016-03-07 17:21:29','282228a2-b688-4960-9fc1-f6fa7df48ad4','IT-SR',118,482,15),(93,'2016-03-07 17:21:30','2016-03-07 17:21:30','03c8bc56-459c-42a3-b58a-b8fa60ec72cb','IT-SO',118,483,9),(94,'2016-03-07 17:21:30','2016-03-07 17:21:30','44fa5d98-33ed-4f39-a123-7c307393a679','IT-TA',118,484,13),(95,'2016-03-07 17:21:30','2016-03-07 17:21:30','7903f714-c0cf-4835-9b73-14746decd123','IT-TE',118,485,1),(96,'2016-03-07 17:21:30','2016-03-07 17:21:30','2bd8b7bf-ff43-4a5f-a66d-65293b50a3da','IT-TR',118,486,18),(97,'2016-03-07 17:21:30','2016-03-07 17:21:30','233d2e68-5bfa-42a4-b72e-d0147f47343d','IT-TO',118,487,12),(98,'2016-03-07 17:21:30','2016-03-07 17:21:30','bc596250-8400-4ee2-8b16-3a2dcceeac4c','IT-TP',118,488,15),(99,'2016-03-07 17:21:30','2016-03-07 17:21:30','1659c847-6596-42ee-b3c5-d4783a07835f','IT-TN',118,489,17),(100,'2016-03-07 17:21:30','2016-03-07 17:21:30','8b13e04a-cbf2-4917-bd75-7d68d4b34ce6','IT-TV',118,490,20),(101,'2016-03-07 17:21:30','2016-03-07 17:21:30','d137132d-cf3d-4d16-9274-8c869a20ced8','IT-TS',118,491,6),(102,'2016-03-07 17:21:30','2016-03-07 17:21:30','b84f307d-b46b-41a1-907d-07047f285161','IT-UD',118,492,6),(103,'2016-03-07 17:21:31','2016-03-07 17:21:31','a3996644-d7a1-4f23-b3fc-598bd7ef956a','IT-VA',118,493,9),(104,'2016-03-07 17:21:31','2016-03-07 17:21:31','9759aa37-6483-4955-845e-8ae6d5d25e3e','IT-VE',118,494,20),(105,'2016-03-07 17:21:31','2016-03-07 17:21:31','b9e61985-a14e-43ea-b5a1-1f40773d98d4','IT-VB',118,495,12),(106,'2016-03-07 17:21:31','2016-03-07 17:21:31','3b1b1062-77d6-4360-bde2-7e0c39d6eee2','IT-VC',118,496,12),(107,'2016-03-07 17:21:31','2016-03-07 17:21:31','83ad82b3-3ea8-4fe5-83fa-654d897eae72','IT-VR',118,497,20),(108,'2016-03-07 17:21:31','2016-03-07 17:21:31','1388dfe3-86c9-4040-ab09-d4c703bfb907','IT-VV',118,498,3),(109,'2016-03-07 17:21:31','2016-03-07 17:21:31','77f70978-e151-45a4-ae8e-788eee9d16eb','IT-VI',118,499,20),(110,'2016-03-07 17:21:31','2016-03-07 17:21:31','b546cb8f-e630-4b82-b02a-9abe86b690dc','IT-VT',118,500,7);
/*!40000 ALTER TABLE `district` ENABLE KEYS */;
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
  UNIQUE KEY `UK_9dr671afth0rcmoxgtyh9wle1` (`uuid`),
  KEY `FK_q6ik2v092ls68r2evhki4s3m` (`description_stringid`),
  CONSTRAINT `FK_q6ik2v092ls68r2evhki4s3m` FOREIGN KEY (`description_stringid`) REFERENCES `multilingual_string` (`Id`)
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
  UNIQUE KEY `UK_6atnxcmc2cttoqqkhr960fmmb` (`uuid`),
  KEY `FK_ce3rbi3bfstbvvyne34c1dvyv` (`product_id`),
  KEY `FK_e9f2mbx9vt4aar5ht39hy8rhr` (`store_id`),
  KEY `FK_t4xjpic3v3ayluu40ty85imr6` (`warehouse_id`),
  CONSTRAINT `FK_ce3rbi3bfstbvvyne34c1dvyv` FOREIGN KEY (`product_id`) REFERENCES `abstractProducts` (`ctentry_id`),
  CONSTRAINT `FK_e9f2mbx9vt4aar5ht39hy8rhr` FOREIGN KEY (`store_id`) REFERENCES `stores` (`store_id`),
  CONSTRAINT `FK_t4xjpic3v3ayluu40ty85imr6` FOREIGN KEY (`warehouse_id`) REFERENCES `warehouse` (`warehouse_id`)
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
-- Table structure for table `job_details`
--

DROP TABLE IF EXISTS `job_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `job_details` (
  `jobDetail_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `job_param` varchar(255) DEFAULT NULL,
  `last_run_date` datetime DEFAULT NULL,
  `name_job` varchar(255) DEFAULT NULL,
  `name_class` varchar(255) DEFAULT NULL,
  `state_of_job` varchar(255) DEFAULT NULL,
  `stop_on_fail` bit(1) DEFAULT NULL,
  `store_id` bigint(20) NOT NULL,
  `catalog_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`jobDetail_id`),
  UNIQUE KEY `UK_jvxmtmpetvl8c64x8f2h8ysgr` (`uuid`),
  KEY `FK_6jqmk3b3m5kdati9e9yo8n8i6` (`store_id`),
  KEY `FK_n9uwtv21lfkq2eyf7vdgwql73` (`catalog_id`),
  CONSTRAINT `FK_6jqmk3b3m5kdati9e9yo8n8i6` FOREIGN KEY (`store_id`) REFERENCES `stores` (`store_id`),
  CONSTRAINT `FK_n9uwtv21lfkq2eyf7vdgwql73` FOREIGN KEY (`catalog_id`) REFERENCES `catalog` (`catalog_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_details`
--

LOCK TABLES `job_details` WRITE;
/*!40000 ALTER TABLE `job_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `job_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_scheduler_log`
--

DROP TABLE IF EXISTS `job_scheduler_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `job_scheduler_log` (
  `joblog_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `date_end` datetime DEFAULT NULL,
  `date_start` datetime DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `job_id` bigint(20) NOT NULL,
  `scheduler_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`joblog_id`),
  UNIQUE KEY `UK_aj0xoqwcf5m9281th97l99nf3` (`uuid`),
  KEY `FK_ix7m907xho9v1r8jiwx9i8dbn` (`job_id`),
  KEY `FK_swc4am3hp3tbqagbo4wl6wfao` (`scheduler_id`),
  CONSTRAINT `FK_ix7m907xho9v1r8jiwx9i8dbn` FOREIGN KEY (`job_id`) REFERENCES `job_details` (`jobDetail_id`),
  CONSTRAINT `FK_swc4am3hp3tbqagbo4wl6wfao` FOREIGN KEY (`scheduler_id`) REFERENCES `scheduler_job` (`jobscheduler_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_scheduler_log`
--

LOCK TABLES `job_scheduler_log` WRITE;
/*!40000 ALTER TABLE `job_scheduler_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `job_scheduler_log` ENABLE KEYS */;
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
  PRIMARY KEY (`locale_id`),
  UNIQUE KEY `UK_jfx0ppqn44vrjhx1ktfq558ba` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locale`
--

LOCK TABLES `locale` WRITE;
/*!40000 ALTER TABLE `locale` DISABLE KEYS */;
INSERT INTO `locale` (`locale_id`, `created`, `updated`, `uuid`, `country`, `language`, `name`) VALUES (1,'2016-03-07 14:42:23','2016-03-07 14:42:23','d5292b4c-f3fc-41de-b0fa-76d19f1c85c4','IT','it','Italiano'),(2,'2016-03-07 14:42:24','2016-03-07 14:42:24','8ba4c63e-cf73-4a11-b941-504d12262639','US','en','English');
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
  CONSTRAINT `FK_jcwbfs8il9mrhkqyq1nhyqwsu` FOREIGN KEY (`string_id`) REFERENCES `multilingual_string` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locale_string_map`
--

LOCK TABLES `locale_string_map` WRITE;
/*!40000 ALTER TABLE `locale_string_map` DISABLE KEYS */;
INSERT INTO `locale_string_map` (`string_id`, `language`, `text`, `locale`) VALUES (1,'en','public list','en'),(1,'it','listino al publico','it'),(2,'en','Category woman donna','en'),(2,'it','Categoria donna','it'),(3,'en','Category man','en'),(3,'it','Categoria uomo','it'),(4,'en','Category kids','en'),(4,'it','Categoria bambini','it'),(5,'en','Clothing','en'),(5,'it','Abbigliamento donna','it'),(6,'en','Clothing','en'),(6,'it','Abbigliamento Uomo','it'),(7,'en','Dresses','en'),(7,'it','Abiti','it'),(8,'en','Top','en'),(8,'it','Top','it'),(9,'en','Bags','en'),(9,'it','Borse','it'),(10,'en','Jackets','en'),(10,'it','Giacche','it'),(11,'en','Accessories','en'),(11,'it','Accessori','it'),(12,'en','Jeans','en'),(12,'it','Jeans','it'),(13,'en','Clothing','en'),(13,'it','Abbigliamento','it'),(14,'en','Clothing','en'),(14,'it','Abbigliamento','it'),(15,'en','Girls 6 - 10 years','en'),(15,'it','Bambine 6 - 10 anni','it'),(16,'en','Boy 6 - 10 anni','en'),(16,'it','Bambino 6 - 10 anni','it'),(17,'en','Round Neck Tunic','en'),(17,'it','Tunica','it'),(18,'en','Round Neck Tunic','en'),(18,'it','Tunica','it'),(19,'en','Round Neck Tunic','en'),(19,'it','Tunica','it'),(20,'en','Round Neck Tunic','en'),(20,'it','Tunica','it'),(21,'en','Colour block bress','en'),(21,'it','Vestito color block','it'),(22,'en','Colour block bress','en'),(22,'it','Vestito color block','it'),(23,'en','Colour block bress','en'),(23,'it','Vestito color block','it'),(24,'en','Colour block bress','en'),(24,'it','Vestito color block','it'),(25,'it','Borsa daily','it'),(26,'it','Borsa daily','it'),(27,'it','Borsa daily','it'),(28,'it','Borsa daily','it'),(29,'it','Borsa daily','it'),(30,'it','Borsa daily','it'),(31,'en','Mellon Leather Shoulder Bag','en'),(31,'it','Mellon Leather Tracolla','it'),(32,'en','Mellon Leather Shoulder Bag','en'),(32,'it','Mellon Leather Tracolla','it'),(33,'en','Mellon Leather Shoulder Bag','en'),(33,'it','Mellon Leather Tracolla','it'),(34,'en','511 Slim Fit Jeans','en'),(34,'it','511 Slim Fit Jeans','it'),(35,'en','511 Slim Fit Jeans','en'),(35,'it','511 Slim Fit Jeans','it'),(36,'en','511 Slim Fit Jeans','en'),(36,'it','511 Slim Fit Jeans','it'),(37,'en','511 Slim Fit Jeans','en'),(37,'it','511 Slim Fit Jeans','it'),(38,'en','511 Slim Fit Jeans','en'),(38,'it','511 Slim Fit Jeans','it'),(39,'en','511 Slim Fit Jeans','en'),(39,'it','511 Slim Fit Jeans','it'),(40,'en','Markus','en'),(40,'it','Markus','it'),(41,'en','Markus','en'),(41,'it','Markus','it'),(42,'en','Relaxed Lace Tee','en'),(42,'it','Relaxed Lace Tee','it'),(43,'en','Relaxed Lace Tee','en'),(43,'it','Relaxed Lace Tee','it'),(44,'en','Relaxed Lace Tee','en'),(44,'it','Relaxed Lace Tee','it'),(45,'en','Relaxed Lace Tee','en'),(45,'it','Relaxed Lace Tee','it'),(46,'en','Cap Sleeve Tee','en'),(46,'it','Cap Sleeve Tee','it'),(47,'en','Cap Sleeve Tee','en'),(47,'it','Cap Sleeve Tee','it'),(48,'en','Cap Sleeve Tee','en'),(48,'it','Cap Sleeve Tee','it'),(49,'en','Cap Sleeve Tee','en'),(49,'it','Cap Sleeve Tee','it'),(50,'en','THE CLASSIC BOMBER','en'),(50,'it','THE CLASSIC BOMBER','it'),(51,'en','THE CLASSIC BOMBER','en'),(51,'it','THE CLASSIC BOMBER','it'),(52,'en','THE CLASSIC BOMBER','en'),(52,'it','THE CLASSIC BOMBER','it'),(53,'en','THE CLASSIC BOMBER','en'),(53,'it','THE CLASSIC BOMBER','it'),(54,'en','Sedgefield Coat','en'),(54,'it','Sedgefield Coat','it'),(55,'en','Sedgefield Coat','en'),(55,'it','Sedgefield Coat','it'),(56,'en','Sedgefield Coat','en'),(56,'it','Sedgefield Coat','it'),(57,'en','Sedgefield Coat','en'),(57,'it','Sedgefield Coat','it'),(58,'en','Sedgefield Coat','en'),(58,'it','Sedgefield Coat','it'),(59,'en','Sedgefield Coat','en'),(59,'it','Sedgefield Coat','it'),(60,'en','Aldergrove Hat','en'),(60,'it','Aldergrove Hat','it'),(61,'en','Aldergrove Hat','en'),(61,'it','Aldergrove Hat','it'),(62,'en','CAMOUFLAGE SCARF','en'),(62,'it','CAMOUFLAGE SCARF','it'),(63,'en','CAMOUFLAGE SCARF','en'),(63,'it','CAMOUFLAGE SCARF','it'),(64,'en','CAMOUFLAGE SCARF','en'),(64,'it','CAMOUFLAGE SCARF','it'),(65,'en','Dress sweatshirt with fake t shirt','en'),(65,'it','Abito in felpa con finta t shirt','it'),(66,'en','Dress sweatshirt with fake t shirt','en'),(66,'it','Abito in felpa con finta t shirt','it'),(67,'en','Dress sweatshirt with fake t shirt','en'),(67,'it','Abito in felpa con finta t shirt','it'),(68,'en','Dress sweatshirt with fake t shirt','en'),(68,'it','Abito in felpa con finta t shirt','it'),(69,'en','T-SHIRT WITH TULLE FRILLS','en'),(69,'it','T-SHIRT CON BALZE IN TULLE','it'),(70,'en','T-SHIRT WITH TULLE FRILLS','en'),(70,'it','T-SHIRT CON BALZE IN TULLE','it'),(71,'en','T-SHIRT WITH TULLE FRILLS','en'),(71,'it','T-SHIRT CON BALZE IN TULLE','it'),(72,'en','JACKET WITH HOOD','en'),(72,'it','GIUBBOTTO CON CAPPUCCIO','it'),(73,'en','JACKET WITH HOOD','en'),(73,'it','GIUBBOTTO CON CAPPUCCIO','it'),(74,'en','JACKET WITH HOOD','en'),(74,'it','GIUBBOTTO CON CAPPUCCIO','it'),(75,'en','Juventus Home Replica Jersey','en'),(75,'it','Maglia Home Replica Juventus','it'),(76,'en','Juventus Home Replica Jersey','en'),(76,'it','Maglia Home Replica Juventus','it'),(77,'en','Juventus Home Replica Jersey','en'),(77,'it','Maglia Home Replica Juventus','it'),(78,'en','Juventus Home Replica Jersey','en'),(78,'it','Maglia Home Replica Juventus','it'),(79,'en','Juventus Home Replica Jersey','en'),(79,'it','Maglia Home Replica Juventus','it'),(80,'en','Woman','en'),(80,'it','Donna','it'),(81,'en','Man','en'),(81,'it','Uomo','it'),(82,'en','Kids','en'),(82,'it','Bambini','it'),(83,'en','Clothing','en'),(83,'it','Abbigliamento','it'),(84,'en','Clothing','en'),(84,'it','Abbigliamento','it'),(85,'en','Dresses','en'),(85,'it','Abiti','it'),(86,'en','Top','en'),(86,'it','Top','it'),(87,'en','Bags','en'),(87,'it','Borse','it'),(88,'en','Jackets','en'),(88,'it','Giacche','it'),(89,'en','Jeans','en'),(89,'it','Jeans','it'),(90,'en','Accessories','en'),(90,'it','Accessori','it'),(91,'en','Girl','en'),(91,'it','Bambine','it'),(92,'en','Boy','en'),(92,'it','Bambino','it'),(93,'en','Clothing','en'),(93,'it','Abbigliamento','it'),(94,'en','Clothing','en'),(94,'it','Abbigliamento','it'),(95,'en','6-10 Years','en'),(95,'it','6-10 Anni','it'),(96,'en','6-10 Years','en'),(96,'it','6-10 Anni','it'),(97,'en','Adidas','en'),(97,'it','Adidas','it'),(98,'en','Mandarina Duck','en'),(98,'it','Mandarina Duck','it'),(99,'en','Levi\'s','en'),(99,'it','Levi\'s','it'),(100,'en','Meltin\'Pot','en'),(100,'it','Meltin\'Pot','it'),(101,'en','Wrangler','en'),(101,'it','Wrangler','it'),(102,'en','Belstaff','en'),(102,'it','Belstaff','it'),(103,'en','BRUMS','en'),(103,'it','BRUMS','it'),(104,'en','BENETTON','en'),(104,'it','BENETTON','it'),(105,'en','Round neck tunic','en'),(105,'it','Tunica girocollo','it'),(106,'en','Colour block bress','en'),(106,'it','Vestito color block','it'),(107,'en','Daily tote','en'),(107,'it','Borsa daily','it'),(108,'en','Mellon Leather Shoulder Bag','en'),(108,'it','Mellon Leather Tracolla','it'),(109,'en','511 Slim Fit Jeans','en'),(109,'it','511 Slim Fit Jeans','it'),(110,'en','Markus D1577-UD121','en'),(110,'it','Markus D1577-UD121','it'),(111,'en','Relaxed Lace Tee','en'),(111,'it','Relaxed Lace Tee','it'),(112,'en','Cap Slleve Tee','en'),(112,'it','Cap Slleve Tee','it'),(113,'en','THE CLASSIC BOMBER','en'),(113,'it','THE CLASSIC BOMBER','it'),(114,'en','Sedgefield Coat','en'),(114,'it','Sedgefield Coat','it'),(115,'en','Aldergrove Hat<BR/>In Bright Navy Wool','en'),(115,'it','Aldergrove Hat','it'),(116,'en','CAMOUFLAGE SCARF<BR/>Kentucky Blue','en'),(116,'it','CAMOUFLAGE SCARF<BR/>Kentucky Blue','it'),(117,'en','Dress sweatshirt with fake t shirt','en'),(117,'it','Abito in felpa con finta t shirt','it'),(118,'en','T-SHIRT WITH TULLE FRILLS','en'),(118,'it','T-SHIRT CON BALZE IN TULLE','it'),(119,'en','JACKET WITH HOOD','en'),(119,'it','GIUBBOTTO CON CAPPUCCIO','it'),(120,'en','Juventus Home Replica Jersey','en'),(120,'it','Maglia Home Replica Juventus','it'),(121,'it','Shipmode test description','it'),(122,'en','Afghanistan','en'),(122,'it','Afghanistan','it'),(123,'en','Albania','en'),(123,'it','Albania','it'),(124,'en','Algeria','en'),(124,'it','Algeria','it'),(125,'en','Andorra','en'),(125,'it','Andorra','it'),(126,'en','Angola','en'),(126,'it','Angola','it'),(127,'en','Anguilla','en'),(127,'it','Anguilla','it'),(128,'en','Antarctica','en'),(128,'it','Antartide','it'),(129,'en','Antigua and Barbuda','en'),(129,'it','Antigua e Barbuda','it'),(130,'en','Saudi Arabia','en'),(130,'it','Arabia Saudita','it'),(131,'en','Argentina','en'),(131,'it','Argentina','it'),(132,'en','Armenia','en'),(132,'it','Armenia','it'),(133,'en','Aruba','en'),(133,'it','Aruba','it'),(134,'en','Australia','en'),(134,'it','Australia','it'),(135,'en','Austria','en'),(135,'it','Austria','it'),(136,'en','Azerbaijan','en'),(136,'it','Azerbaigian','it'),(137,'en','Bahamas','en'),(137,'it','Bahamas','it'),(138,'en','Bahrain','en'),(138,'it','Bahrein','it'),(139,'en','Bangladesh','en'),(139,'it','Bangladesh','it'),(140,'en','Barbados','en'),(140,'it','Barbados','it'),(141,'en','Belgium','en'),(141,'it','Belgio','it'),(142,'en','Belize','en'),(142,'it','Belize','it'),(143,'en','Benin','en'),(143,'it','Benin','it'),(144,'en','Bermuda','en'),(144,'it','Bermuda','it'),(145,'en','Bhutan','en'),(145,'it','Bhutan','it'),(146,'en','Belarus','en'),(146,'it','Bielorussia','it'),(147,'en','Myanmar','en'),(147,'it','Birmania','it'),(148,'en','Bolivia (Plurinational State of)','en'),(148,'it','Bolivia','it'),(149,'en','Bosnia and Herzegovina','en'),(149,'it','Bosnia ed Erzegovina','it'),(150,'en','Botswana','en'),(150,'it','Botswana','it'),(151,'en','Brazil','en'),(151,'it','Brasile','it'),(152,'en','Brunei Darussalam','en'),(152,'it','Brunei','it'),(153,'en','Bulgaria','en'),(153,'it','Bulgaria','it'),(154,'en','Burkina Faso','en'),(154,'it','Burkina Faso','it'),(155,'en','Burundi','en'),(155,'it','Burundi','it'),(156,'en','Cambodia','en'),(156,'it','Cambogia','it'),(157,'en','Cameroon','en'),(157,'it','Camerun','it'),(158,'en','Canada','en'),(158,'it','Canada','it'),(159,'en','Cabo Verde','en'),(159,'it','Capo Verde','it'),(160,'en','Chad','en'),(160,'it','Ciad','it'),(161,'en','Chile','en'),(161,'it','Cile','it'),(162,'en','China','en'),(162,'it','Cina','it'),(163,'en','Cyprus','en'),(163,'it','Cipro','it'),(164,'en','Holy See','en'),(164,'it','Città del Vaticano','it'),(165,'en','Colombia','en'),(165,'it','Colombia','it'),(166,'en','Comoros','en'),(166,'it','Comore','it'),(167,'en','Korea (Democratic People\'s Republic of)','en'),(167,'it','Corea del Nord','it'),(168,'en','Korea (Republic of)','en'),(168,'it','Corea del Sud','it'),(169,'en','Côte d\'Ivoire','en'),(169,'it','Costa d\'Avorio','it'),(170,'en','Costa Rica','en'),(170,'it','Costa Rica','it'),(171,'en','Croatia','en'),(171,'it','Croazia','it'),(172,'en','Cuba','en'),(172,'it','Cuba','it'),(173,'en','Curaçao','en'),(173,'it','Curaçao','it'),(174,'en','Denmark','en'),(174,'it','Danimarca','it'),(175,'en','Dominica','en'),(175,'it','Dominica','it'),(176,'en','Ecuador','en'),(176,'it','Ecuador','it'),(177,'en','Egypt','en'),(177,'it','Egitto','it'),(178,'en','El Salvador','en'),(178,'it','El Salvador','it'),(179,'en','United Arab Emirates','en'),(179,'it','Emirati Arabi Uniti','it'),(180,'en','Eritrea','en'),(180,'it','Eritrea','it'),(181,'en','Estonia','en'),(181,'it','Estonia','it'),(182,'en','Ethiopia','en'),(182,'it','Etiopia','it'),(183,'en','Fiji','en'),(183,'it','Figi','it'),(184,'en','Philippines','en'),(184,'it','Filippine','it'),(185,'en','Finland','en'),(185,'it','Finlandia','it'),(186,'en','France','en'),(186,'it','Francia','it'),(187,'en','Gabon','en'),(187,'it','Gabon','it'),(188,'en','Gambia','en'),(188,'it','Gambia','it'),(189,'en','Georgia','en'),(189,'it','Georgia','it'),(190,'en','South Georgia and the South Sandwich Islands','en'),(190,'it','Georgia del Sud e isole Sandwich meridionali','it'),(191,'en','Germany','en'),(191,'it','Germania','it'),(192,'en','Ghana','en'),(192,'it','Ghana','it'),(193,'en','Jamaica','en'),(193,'it','Giamaica','it'),(194,'en','Japan','en'),(194,'it','Giappone','it'),(195,'en','Gibraltar','en'),(195,'it','Gibilterra','it'),(196,'en','Djibouti','en'),(196,'it','Gibuti','it'),(197,'en','Jordan','en'),(197,'it','Giordania','it'),(198,'en','Greece','en'),(198,'it','Grecia','it'),(199,'en','Grenada','en'),(199,'it','Grenada','it'),(200,'en','Greenland','en'),(200,'it','Groenlandia','it'),(201,'en','Guadeloupe','en'),(201,'it','Guadalupa','it'),(202,'en','Guam','en'),(202,'it','Guam','it'),(203,'en','Guatemala','en'),(203,'it','Guatemala','it'),(204,'en','Guernsey','en'),(204,'it','Guernsey','it'),(205,'en','Guinea','en'),(205,'it','Guinea','it'),(206,'en','Guinea-Bissau','en'),(206,'it','Guinea-Bissau','it'),(207,'en','Equatorial Guinea','en'),(207,'it','Guinea Equatoriale','it'),(208,'en','Guyana','en'),(208,'it','Guyana','it'),(209,'en','French Guiana','en'),(209,'it','Guyana francese','it'),(210,'en','Haiti','en'),(210,'it','Haiti','it'),(211,'en','Honduras','en'),(211,'it','Honduras','it'),(212,'en','Hong Kong','en'),(212,'it','Hong Kong','it'),(213,'en','India','en'),(213,'it','India','it'),(214,'en','Indonesia','en'),(214,'it','Indonesia','it'),(215,'en','Iran (Islamic Republic of)','en'),(215,'it','Iran','it'),(216,'en','Iraq','en'),(216,'it','Iraq','it'),(217,'en','Ireland','en'),(217,'it','Irlanda','it'),(218,'en','Iceland','en'),(218,'it','Islanda','it'),(219,'en','Bouvet Island','en'),(219,'it','Isola Bouvet','it'),(220,'en','Isle of Man','en'),(220,'it','Isola di Man','it'),(221,'en','Christmas Island','en'),(221,'it','Isola di Natale','it'),(222,'en','Norfolk Island','en'),(222,'it','Isola Norfolk','it'),(223,'en','Åland Islands','en'),(223,'it','Isole Åland','it'),(224,'en','Bonaire; Sint Eustatius and Saba','en'),(224,'it','Isole BES','it'),(225,'en','Cayman Islands','en'),(225,'it','Isole Cayman','it'),(226,'en','Cocos (Keeling) Islands','en'),(226,'it','Isole Cocos (Keeling)','it'),(227,'en','Cook Islands','en'),(227,'it','Isole Cook','it'),(228,'en','Faroe Islands','en'),(228,'it','Fær Øer','it'),(229,'en','Falkland Islands (Malvinas)','en'),(229,'it','Isole Falkland','it'),(230,'en','Heard Island and McDonald Islands','en'),(230,'it','Isole Heard e McDonald','it'),(231,'en','Northern Mariana Islands','en'),(231,'it','Isole Marianne Settentrionali','it'),(232,'en','Marshall Islands','en'),(232,'it','Isole Marshall','it'),(233,'en','United States Minor Outlying Islands','en'),(233,'it','Isole minori esterne degli Stati Uniti','it'),(234,'en','Pitcairn','en'),(234,'it','Isole Pitcairn','it'),(235,'en','Solomon Islands','en'),(235,'it','Isole Salomone','it'),(236,'en','Virgin Islands (British)','en'),(236,'it','Isole Vergini britanniche','it'),(237,'en','Virgin Islands (U.S.)','en'),(237,'it','Isole Vergini americane','it'),(238,'en','Israel','en'),(238,'it','Israele','it'),(239,'en','Italy','en'),(239,'it','Italia','it'),(240,'en','Jersey','en'),(240,'it','Jersey','it'),(241,'en','Kazakhstan','en'),(241,'it','Kazakistan','it'),(242,'en','Kenya','en'),(242,'it','Kenya','it'),(243,'en','Kyrgyzstan','en'),(243,'it','Kirghizistan','it'),(244,'en','Kiribati','en'),(244,'it','Kiribati','it'),(245,'en','Kuwait','en'),(245,'it','Kuwait','it'),(246,'en','Lao People\'s Democratic Republic','en'),(246,'it','Laos','it'),(247,'en','Lesotho','en'),(247,'it','Lesotho','it'),(248,'en','Latvia','en'),(248,'it','Lettonia','it'),(249,'en','Lebanon','en'),(249,'it','Libano','it'),(250,'en','Liberia','en'),(250,'it','Liberia','it'),(251,'en','Libya','en'),(251,'it','Libia','it'),(252,'en','Liechtenstein','en'),(252,'it','Liechtenstein','it'),(253,'en','Lithuania','en'),(253,'it','Lituania','it'),(254,'en','Luxembourg','en'),(254,'it','Lussemburgo','it'),(255,'en','Macao','en'),(255,'it','Macao','it'),(256,'en','Macedonia (the former Yugoslav Republic of)','en'),(256,'it','Macedonia','it'),(257,'en','Madagascar','en'),(257,'it','Madagascar','it'),(258,'en','Malawi','en'),(258,'it','Malawi','it'),(259,'en','Malaysia','en'),(259,'it','Malesia','it'),(260,'en','Maldives','en'),(260,'it','Maldive','it'),(261,'en','Mali','en'),(261,'it','Mali','it'),(262,'en','Malta','en'),(262,'it','Malta','it'),(263,'en','Morocco','en'),(263,'it','Marocco','it'),(264,'en','Martinique','en'),(264,'it','Martinica','it'),(265,'en','Mauritania','en'),(265,'it','Mauritania','it'),(266,'en','Mauritius','en'),(266,'it','Mauritius','it'),(267,'en','Mayotte','en'),(267,'it','Mayotte','it'),(268,'en','Mexico','en'),(268,'it','Messico','it'),(269,'en','Micronesia (Federated States of)','en'),(269,'it','Micronesia','it'),(270,'en','Moldova (Republic of)','en'),(270,'it','Moldavia','it'),(271,'en','Mongolia','en'),(271,'it','Mongolia','it'),(272,'en','Montenegro','en'),(272,'it','Montenegro','it'),(273,'en','Montserrat','en'),(273,'it','Montserrat','it'),(274,'en','Mozambique','en'),(274,'it','Mozambico','it'),(275,'en','Namibia','en'),(275,'it','Namibia','it'),(276,'en','Nauru','en'),(276,'it','Nauru','it'),(277,'en','Nepal','en'),(277,'it','Nepal','it'),(278,'en','Nicaragua','en'),(278,'it','Nicaragua','it'),(279,'en','Niger','en'),(279,'it','Niger','it'),(280,'en','Nigeria','en'),(280,'it','Nigeria','it'),(281,'en','Niue','en'),(281,'it','Niue','it'),(282,'en','Norway','en'),(282,'it','Norvegia','it'),(283,'en','New Caledonia','en'),(283,'it','Nuova Caledonia','it'),(284,'en','New Zealand','en'),(284,'it','Nuova Zelanda','it'),(285,'en','Oman','en'),(285,'it','Oman','it'),(286,'en','Netherlands','en'),(286,'it','Paesi Bassi','it'),(287,'en','Pakistan','en'),(287,'it','Pakistan','it'),(288,'en','Palau','en'),(288,'it','Palau','it'),(289,'en','Palestine; State of','en'),(289,'it','Palestina','it'),(290,'en','Panama','en'),(290,'it','Panamá','it'),(291,'en','Papua New Guinea','en'),(291,'it','Papua Nuova Guinea','it'),(292,'en','Paraguay','en'),(292,'it','Paraguay','it'),(293,'en','Peru','en'),(293,'it','Perù','it'),(294,'en','French Polynesia','en'),(294,'it','Polinesia Francese','it'),(295,'en','Poland','en'),(295,'it','Polonia','it'),(296,'en','Puerto Rico','en'),(296,'it','Porto Rico','it'),(297,'en','Portugal','en'),(297,'it','Portogallo','it'),(298,'en','Monaco','en'),(298,'it','Monaco','it'),(299,'en','Qatar','en'),(299,'it','Qatar','it'),(300,'en','United Kingdom of Great Britain and Northern Ireland','en'),(300,'it','Regno Unito','it'),(301,'en','Congo (Democratic Republic of the)','en'),(301,'it','RD del Congo','it'),(302,'en','Czech Republic','en'),(302,'it','Rep. Ceca','it'),(303,'en','Central African Republic','en'),(303,'it','Rep. Centrafricana','it'),(304,'en','Congo','en'),(304,'it','Rep. del Congo','it'),(305,'en','Dominican Republic','en'),(305,'it','Rep. Dominicana','it'),(306,'en','Réunion','en'),(306,'it','Riunione','it'),(307,'en','Romania','en'),(307,'it','Romania','it'),(308,'en','Rwanda','en'),(308,'it','Ruanda','it'),(309,'en','Russian Federation','en'),(309,'it','Russia','it'),(310,'en','Western Sahara','en'),(310,'it','Sahara Occidentale','it'),(311,'en','Saint Kitts and Nevis','en'),(311,'it','Saint Kitts e Nevis','it'),(312,'en','Saint Lucia','en'),(312,'it','Santa Lucia','it'),(313,'en','Saint Helena; Ascension and Tristan da Cunha','en'),(313,'it','Sant\'Elena; Ascensione e Tristan da Cunha','it'),(314,'en','Saint Vincent and the Grenadines','en'),(314,'it','Saint Vincent e Grenadine','it'),(315,'en','Saint Barthélemy','en'),(315,'it','Saint-Barthélemy','it'),(316,'en','Saint Martin (French part)','en'),(316,'it','Saint-Martin','it'),(317,'en','Saint Pierre and Miquelon','en'),(317,'it','Saint-Pierre e Miquelon','it'),(318,'en','Samoa','en'),(318,'it','Samoa','it'),(319,'en','American Samoa','en'),(319,'it','Samoa Americane','it'),(320,'en','San Marino','en'),(320,'it','San Marino','it'),(321,'en','Sao Tome and Principe','en'),(321,'it','São Tomé e Príncipe','it'),(322,'en','Senegal','en'),(322,'it','Senegal','it'),(323,'en','Serbia','en'),(323,'it','Serbia','it'),(324,'en','Seychelles','en'),(324,'it','Seychelles','it'),(325,'en','Sierra Leone','en'),(325,'it','Sierra Leone','it'),(326,'en','Singapore','en'),(326,'it','Singapore','it'),(327,'en','Sint Maarten (Dutch part)','en'),(327,'it','Sint Maarten','it'),(328,'en','Syrian Arab Republic','en'),(328,'it','Siria','it'),(329,'en','Slovakia','en'),(329,'it','Slovacchia','it'),(330,'en','Slovenia','en'),(330,'it','Slovenia','it'),(331,'en','Somalia','en'),(331,'it','Somalia','it'),(332,'en','Spain','en'),(332,'it','Spagna','it'),(333,'en','Sri Lanka','en'),(333,'it','Sri Lanka','it'),(334,'en','United States of America','en'),(334,'it','Stati Uniti','it'),(335,'en','South Africa','en'),(335,'it','Sudafrica','it'),(336,'en','Sudan','en'),(336,'it','Sudan','it'),(337,'en','South Sudan','en'),(337,'it','Sudan del Sud','it'),(338,'en','Suriname','en'),(338,'it','Suriname','it'),(339,'en','Svalbard and Jan Mayen','en'),(339,'it','Svalbard e Jan Mayen','it'),(340,'en','Sweden','en'),(340,'it','Svezia','it'),(341,'en','Switzerland','en'),(341,'it','Svizzera','it'),(342,'en','Swaziland','en'),(342,'it','Swaziland','it'),(343,'en','Taiwan; Province of China[a]','en'),(343,'it','Taiwan','it'),(344,'en','Tajikistan','en'),(344,'it','Tagikistan','it'),(345,'en','Tanzania; United Republic of','en'),(345,'it','Tanzania','it'),(346,'en','French Southern Territories','en'),(346,'it','Terre australi e antartiche francesi','it'),(347,'en','British Indian Ocean Territory','en'),(347,'it','Territorio britannico dell\'oceano Indiano','it'),(348,'en','Thailand','en'),(348,'it','Thailandia','it'),(349,'en','Timor-Leste','en'),(349,'it','Timor Est','it'),(350,'en','Togo','en'),(350,'it','Togo','it'),(351,'en','Tokelau','en'),(351,'it','Tokelau','it'),(352,'en','Tonga','en'),(352,'it','Tonga','it'),(353,'en','Trinidad and Tobago','en'),(353,'it','Trinidad e Tobago','it'),(354,'en','Tunisia','en'),(354,'it','Tunisia','it'),(355,'en','Turkey','en'),(355,'it','Turchia','it'),(356,'en','Turkmenistan','en'),(356,'it','Turkmenistan','it'),(357,'en','Turks and Caicos Islands','en'),(357,'it','Turks e Caicos','it'),(358,'en','Tuvalu','en'),(358,'it','Tuvalu','it'),(359,'en','Ukraine','en'),(359,'it','Ucraina','it'),(360,'en','Uganda','en'),(360,'it','Uganda','it'),(361,'en','Hungary','en'),(361,'it','Ungheria','it'),(362,'en','Uruguay','en'),(362,'it','Uruguay','it'),(363,'en','Uzbekistan','en'),(363,'it','Uzbekistan','it'),(364,'en','Vanuatu','en'),(364,'it','Vanuatu','it'),(365,'en','Venezuela (Bolivarian Republic of)','en'),(365,'it','Venezuela','it'),(366,'en','Viet Nam','en'),(366,'it','Vietnam','it'),(367,'en','Wallis and Futuna','en'),(367,'it','Wallis e Futuna','it'),(368,'en','Yemen','en'),(368,'it','Yemen','it'),(369,'en','Zambia','en'),(369,'it','Zambia','it'),(370,'en','Zimbabwe','en'),(370,'it','Zimbabwe','it'),(371,'en','Abruzzo','en'),(371,'it','Abruzzo','it'),(372,'en','Basilicata','en'),(372,'it','Basilicata','it'),(373,'en','Calabria','en'),(373,'it','Calabria','it'),(374,'en','Campania','en'),(374,'it','Campania','it'),(375,'en','Emilia-Romagna','en'),(375,'it','Emilia-Romagna','it'),(376,'en','Friuli-Venezia Giulia','en'),(376,'it','Friuli-Venezia Giulia','it'),(377,'en','Lazio','en'),(377,'it','Lazio','it'),(378,'en','Liguria','en'),(378,'it','Liguria','it'),(379,'en','Lombardia','en'),(379,'it','Lombardia','it'),(380,'en','Marche','en'),(380,'it','Marche','it'),(381,'en','Molise','en'),(381,'it','Molise','it'),(382,'en','Piemonte','en'),(382,'it','Piemonte','it'),(383,'en','Puglia','en'),(383,'it','Puglia','it'),(384,'en','Sardegna','en'),(384,'it','Sardegna','it'),(385,'en','Sicilia','en'),(385,'it','Sicilia','it'),(386,'en','Toscana','en'),(386,'it','Toscana','it'),(387,'en','Trentino-Alto Adige','en'),(387,'it','Trentino-Alto Adige','it'),(388,'en','Umbria','en'),(388,'it','Umbria','it'),(389,'en','Valle d\'Aosta','en'),(389,'it','Valle d\'Aosta','it'),(390,'en','Veneto','en'),(390,'it','Veneto','it'),(391,'en','Agrigento','en'),(391,'it','Agrigento','it'),(392,'en','Alessandria','en'),(392,'it','Alessandria','it'),(393,'en','Ancona','en'),(393,'it','Ancona','it'),(394,'en','Valle d\'Aosta','en'),(394,'it','Valle d\'Aosta','it'),(395,'en','Arezzo','en'),(395,'it','Arezzo','it'),(396,'en','Ascoli Piceno','en'),(396,'it','Ascoli Piceno','it'),(397,'en','Asti','en'),(397,'it','Asti','it'),(398,'en','Avellino','en'),(398,'it','Avellino','it'),(399,'en','Bari','en'),(399,'it','Bari','it'),(400,'en','Barletta-Andria-Trani','en'),(400,'it','Barletta-Andria-Trani','it'),(401,'en','Belluno','en'),(401,'it','Belluno','it'),(402,'en','Benevento','en'),(402,'it','Benevento','it'),(403,'en','Bergamo','en'),(403,'it','Bergamo','it'),(404,'en','Biella','en'),(404,'it','Biella','it'),(405,'en','Bologna','en'),(405,'it','Bologna','it'),(406,'en','Bolzano','en'),(406,'it','Bolzano','it'),(407,'en','Brescia','en'),(407,'it','Brescia','it'),(408,'en','Brindisi','en'),(408,'it','Brindisi','it'),(409,'en','Cagliari','en'),(409,'it','Cagliari','it'),(410,'en','Caltanissetta','en'),(410,'it','Caltanissetta','it'),(411,'en','Campobasso','en'),(411,'it','Campobasso','it'),(412,'en','Carbonia-Iglesias','en'),(412,'it','Carbonia-Iglesias','it'),(413,'en','Caserta','en'),(413,'it','Caserta','it'),(414,'en','Catania','en'),(414,'it','Catania','it'),(415,'en','Catanzaro','en'),(415,'it','Catanzaro','it'),(416,'en','Chieti','en'),(416,'it','Chieti','it'),(417,'en','Como','en'),(417,'it','Como','it'),(418,'en','Cosenza','en'),(418,'it','Cosenza','it'),(419,'en','Cremona','en'),(419,'it','Cremona','it'),(420,'en','Crotone','en'),(420,'it','Crotone','it'),(421,'en','Cuneo','en'),(421,'it','Cuneo','it'),(422,'en','Enna','en'),(422,'it','Enna','it'),(423,'en','Fermo','en'),(423,'it','Fermo','it'),(424,'en','Ferrara','en'),(424,'it','Ferrara','it'),(425,'en','Firenze','en'),(425,'it','Firenze','it'),(426,'en','Foggia','en'),(426,'it','Foggia','it'),(427,'en','Forlì-Cesena','en'),(427,'it','Forlì-Cesena','it'),(428,'en','Frosinone','en'),(428,'it','Frosinone','it'),(429,'en','Genova','en'),(429,'it','Genova','it'),(430,'en','Gorizia','en'),(430,'it','Gorizia','it'),(431,'en','Grosseto','en'),(431,'it','Grosseto','it'),(432,'en','Imperia','en'),(432,'it','Imperia','it'),(433,'en','Isernia','en'),(433,'it','Isernia','it'),(434,'en','La Spezia','en'),(434,'it','La Spezia','it'),(435,'en','L\'Aquila','en'),(435,'it','L\'Aquila','it'),(436,'en','Latina','en'),(436,'it','Latina','it'),(437,'en','Lecce','en'),(437,'it','Lecce','it'),(438,'en','Lecco','en'),(438,'it','Lecco','it'),(439,'en','Livorno','en'),(439,'it','Livorno','it'),(440,'en','Lodi','en'),(440,'it','Lodi','it'),(441,'en','Lucca','en'),(441,'it','Lucca','it'),(442,'en','Macerata','en'),(442,'it','Macerata','it'),(443,'en','Mantova','en'),(443,'it','Mantova','it'),(444,'en','Massa e Carrara','en'),(444,'it','Massa e Carrara','it'),(445,'en','Matera','en'),(445,'it','Matera','it'),(446,'en','Medio Campidano','en'),(446,'it','Medio Campidano','it'),(447,'en','Messina','en'),(447,'it','Messina','it'),(448,'en','Monza e Brianza','en'),(448,'it','Monza e Brianza','it'),(449,'en','Milano','en'),(449,'it','Milano','it'),(450,'en','Modena','en'),(450,'it','Modena','it'),(451,'en','Napoli','en'),(451,'it','Napoli','it'),(452,'en','Novara','en'),(452,'it','Novara','it'),(453,'en','Nuoro','en'),(453,'it','Nuoro','it'),(454,'en','Ogliastra','en'),(454,'it','Ogliastra','it'),(455,'en','Olbia-Tempio','en'),(455,'it','Olbia-Tempio','it'),(456,'en','Oristano','en'),(456,'it','Oristano','it'),(457,'en','Padova','en'),(457,'it','Padova','it'),(458,'en','Palermo','en'),(458,'it','Palermo','it'),(459,'en','Parma','en'),(459,'it','Parma','it'),(460,'en','Pavia','en'),(460,'it','Pavia','it'),(461,'en','Perugia','en'),(461,'it','Perugia','it'),(462,'en','Pesaro e Urbino','en'),(462,'it','Pesaro e Urbino','it'),(463,'en','Pescara','en'),(463,'it','Pescara','it'),(464,'en','Piacenza','en'),(464,'it','Piacenza','it'),(465,'en','Pisa','en'),(465,'it','Pisa','it'),(466,'en','Pistoia','en'),(466,'it','Pistoia','it'),(467,'en','Pordenone','en'),(467,'it','Pordenone','it'),(468,'en','Potenza','en'),(468,'it','Potenza','it'),(469,'en','Prato','en'),(469,'it','Prato','it'),(470,'en','Ragusa','en'),(470,'it','Ragusa','it'),(471,'en','Ravenna','en'),(471,'it','Ravenna','it'),(472,'en','Reggio Calabria','en'),(472,'it','Reggio Calabria','it'),(473,'en','Reggio Emilia','en'),(473,'it','Reggio Emilia','it'),(474,'en','Rieti','en'),(474,'it','Rieti','it'),(475,'en','Rimini','en'),(475,'it','Rimini','it'),(476,'en','Roma','en'),(476,'it','Roma','it'),(477,'en','Rovigo','en'),(477,'it','Rovigo','it'),(478,'en','Salerno','en'),(478,'it','Salerno','it'),(479,'en','Sassari','en'),(479,'it','Sassari','it'),(480,'en','Savona','en'),(480,'it','Savona','it'),(481,'en','Siena','en'),(481,'it','Siena','it'),(482,'en','Siracusa','en'),(482,'it','Siracusa','it'),(483,'en','Sondrio','en'),(483,'it','Sondrio','it'),(484,'en','Taranto','en'),(484,'it','Taranto','it'),(485,'en','Teramo','en'),(485,'it','Teramo','it'),(486,'en','Terni','en'),(486,'it','Terni','it'),(487,'en','Torino','en'),(487,'it','Torino','it'),(488,'en','Trapani','en'),(488,'it','Trapani','it'),(489,'en','Trento','en'),(489,'it','Trento','it'),(490,'en','Treviso','en'),(490,'it','Treviso','it'),(491,'en','Trieste','en'),(491,'it','Trieste','it'),(492,'en','Udine','en'),(492,'it','Udine','it'),(493,'en','Varese','en'),(493,'it','Varese','it'),(494,'en','Venezia','en'),(494,'it','Venezia','it'),(495,'en','Verbano-Cusio-Ossola','en'),(495,'it','Verbano-Cusio-Ossola','it'),(496,'en','Vercelli','en'),(496,'it','Vercelli','it'),(497,'en','Verona','en'),(497,'it','Verona','it'),(498,'en','Vibo Valentia','en'),(498,'it','Vibo Valentia','it'),(499,'en','Vicenza','en'),(499,'it','Vicenza','it'),(500,'en','Viterbo','en'),(500,'it','Viterbo','it');
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
  CONSTRAINT `FK_25926i589b2pbgwkunjua4m9k` FOREIGN KEY (`string_id`) REFERENCES `multilingual_text` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locale_text_map`
--

LOCK TABLES `locale_text_map` WRITE;
/*!40000 ALTER TABLE `locale_text_map` DISABLE KEYS */;
INSERT INTO `locale_text_map` (`string_id`, `language`, `text`, `locale`) VALUES (1,'en','Woman','en'),(1,'it','Donna','it'),(2,'en','Man','en'),(2,'it','Uomo','it'),(3,'en','Kids','en'),(3,'it','Bambini','it'),(4,'en','Clothing','en'),(4,'it','Abbligliamento','it'),(5,'en','Clothing','en'),(5,'it','Abbligliamento','it'),(6,'en','Dresses','en'),(6,'it','Abiti','it'),(7,'en','Top','en'),(7,'it','Top','it'),(8,'en','Bags','en'),(8,'it','Borse','it'),(9,'en','Jackets','en'),(9,'it','Giacche','it'),(10,'en','Jeans','en'),(10,'it','Jeans','it'),(11,'en','Accessories','en'),(11,'it','Accessori','it'),(12,'en','Girl','en'),(12,'it','Bambine','it'),(13,'en','Boy','en'),(13,'it','Bambino','it'),(14,'en','Clothing','en'),(14,'it','Abbligliamento','it'),(15,'en','Clothing','en'),(15,'it','Abbligliamento','it'),(16,'en','6-10 Years','en'),(16,'it','6-10 Anni','it'),(17,'en','6-10 Years','en'),(17,'it','6-10 Anni','it'),(18,'en','Adidas','en'),(18,'it','Adidas','it'),(19,'en','Mandarina Duck','en'),(19,'it','Mandarina Duck','it'),(20,'en','Levi\'s','en'),(20,'it','Levi\'s','it'),(21,'en','Meltin\'Pot','en'),(21,'it','Meltin\'Pot','it'),(22,'en','Wrangler','en'),(22,'it','Wrangler','it'),(23,'en','Belstaff','en'),(23,'it','Belstaff','it'),(24,'en','BRUMS','en'),(24,'it','BRUMS','it'),(25,'en','BENETTON','en'),(25,'it','BENETTON','it'),(26,'en','Round neck tunic<br/>Round neck tunic. Long sleeves. Slits.','en'),(26,'it','Tunica color grigio<br>Tunica girocollo.<br>Maniche lunghe.<br>Aperture','it'),(27,'en','Colour block bress<br/>Long and flowing dress. Round neck. Short sleeves','en'),(27,'it','Vestito color block<br>Abito lungo e morbido. <b>Collo</b> rotondo. Maniche corte.','it'),(28,'en','Totally fresh. This girls tote bag comes with an allover marble print and side-turned adidas neo logo on one side.','en'),(28,'it','Fresca e originale  proprio come te. Questa borsa sfoggia su un lato una stampa allover marmorizzata e un logo adidas neo verticale. La zip integrale è perfetta per custodire al meglio tutte le tue cose.','it'),(29,'en','Textured leather shoulder bag. External zipped pocket. Inner pocket and mobile phone holder. Logo plate. Removable shoulder strap.','en'),(29,'it','Tracolla in pelle texturizzata. Tasca esterna con zip. Tasca interna e vano porta cellulare. Placchetta logata. Tracolla rimovibile.','it'),(30,'en','A modern slim fit with room to move  the 511™ Slim Fit Jeans are a classic from right now. These jeans sit below the waist with a slim leg from the hip to the ankle.','en'),(30,'it','Dal moderno taglio aderente ma spazioso  i jeans 511™ Slim Fit sono un classico a partire da ora. Al di sotto della vita naturale con gamba aderente dai fianchi alle caviglie.','it'),(31,'en','Markus è uno slim fit pant with a narrow leg  very low waist and zip fly.','en'),(31,'it','Markus è uno slim fit con gamba stretta vita molto bassa e chiusura zip.','it'),(32,'en','Our T-shirts and denim were made for each other. This soft  versatile tee features a longer drape and a chest pocket.','en'),(32,'it','I nostri jeans e le nostre T-shirt sono fatti gli uni per le altre. Questa morbida e versatile T-shirt presenta un drappeggio più lungo e una tasca sul petto.','it'),(33,'en','Our tropical photo print t-shirt is style in full bloom.','en'),(33,'it','La nostra maglietta con stampa tropicale sprizza stile da tutti i pori.','it'),(34,'en','A tough service  water-repellent bomber jacket with hood','en'),(34,'it','Affronta la pioggia col nostro bomber grigio idrorepellente','it'),(35,'en','This versatile navy coat boasts multiple pockets  internal and external  and a removable nylon liner   so it can be worn through every season','en'),(35,'it','Questo cappotto versatile vanta numerose tasche interne ed esterne e una fodera in nylon rimovibile in modo che possa essere indossato in ogni stagione.','it'),(36,'en','A classic knitted beanie hat is essential for the winter months','en'),(36,'it','Un cappello classico beanie maglia è essenziale per i mesi invernali','it'),(37,'en','Our camouflage scarf for embracing your surroundings','en'),(37,'it','La nostra sciarpa mimetica per abbracciare l’ambiente circostante.','it'),(38,'en','Forest animals and flowers hued watercolor come to life in comfortable garments jersey melange stretch sweatshirts and colored denim in cranberry and pomegranate tones in a fashion vision.','en'),(38,'it','Animali del bosco e fiori dalle tinte acquarello prendono vita in comodi capi di jersey melange felpe stretch e denim colorati nei toni mirtillo e melograno in una visione fashion.','it'),(39,'en','100% cotton short sleeve t-shirt  with round neck. Straight cut  slightly longer at back  with two tulle frills affixed to the bottom','en'),(39,'it','T-shirt a maniche corte in 100% cotone con scollo rotondo. Linea dritta leggermente svasata e più lunga dietro con dettaglio di due balzette di tulle applicate sul fondo. Davanti è inoltre presente una luccicante stampa glitterata','it'),(40,'en','Padded jacket with stitching and non-removable hood. Centered zip closure and clashing color side slit pockets.','en'),(40,'it','Giubbotto imbottito con impunture e cappuccio non removibile. Apertura centrale a zip e tasche a taglio laterali in contrasto colore','it'),(41,'en','This junior boys\' football jersey is a version of the one Juve wear when they\'re setting the pace and putting pressure on their opponents. Styled after their home shirt  it features breathable climacool® ventilation and the team badge on the left chest.','en'),(41,'it','Questa maglia da calcio junior è simile a quella indossata dalla Juve quando mostra il suo gioco rapido e mette sotto pressione gli avversari nelle partite in casa. È dotata di tecnologia traspirante climacool® e sfoggia lo stemma della squadra sul petto.','it');
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
  `sourcePath` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `store_id` bigint(20) NOT NULL,
  `description_string_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`media_id`),
  UNIQUE KEY `UK_gm3cav9xv4lt6mhadod23c8x2` (`uuid`),
  KEY `FK_myja7ol8qlxt6ra7oojf0gj5f` (`store_id`),
  KEY `FK_8t7bw9lgyayxsbc0fxvq1brei` (`description_string_id`),
  CONSTRAINT `FK_8t7bw9lgyayxsbc0fxvq1brei` FOREIGN KEY (`description_string_id`) REFERENCES `multilingual_string` (`Id`),
  CONSTRAINT `FK_myja7ol8qlxt6ra7oojf0gj5f` FOREIGN KEY (`store_id`) REFERENCES `stores` (`store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `media`
--

LOCK TABLES `media` WRITE;
/*!40000 ALTER TABLE `media` DISABLE KEYS */;
INSERT INTO `media` (`media_id`, `created`, `updated`, `uuid`, `content_type`, `name`, `sequence`, `source`, `sourcePath`, `type`, `store_id`, `description_string_id`) VALUES (1,'2016-03-07 14:50:26','2016-03-07 14:50:26','683bcfe7-2118-4f03-9bd0-36d06bfaacce','image/jpg','WCAT',1,'http://www.castelnuovoimmagina.it/Immagini_Varie/abbigliamento-calzature.jpg',NULL,1,1,2),(2,'2016-03-07 14:50:26','2016-03-07 14:50:26','9f66467e-ec1c-448a-a52e-b66dcd0fec8f','image/jpg','MCAT',1,'https://www.themall.it/dati/marchi_immagine_interna_14.jpg',NULL,1,1,3),(3,'2016-03-07 14:50:26','2016-03-07 14:50:26','f9dc1cdb-5b15-4dc6-9dfd-afd1b74bd9af','image/jpg','KCAT',1,'http://www.benebenemarket.it/polopoly_fs/1.1754711.1447249673!/httpImage/img.jpeg_gen/derivatives/landscape_600/img.jpeg',NULL,1,1,4),(4,'2016-03-07 14:50:27','2016-03-07 14:50:27','0742754a-4410-47ed-b09b-ead9d1065c5b','image/jpg','WCLH',1,'http://www.castelnuovoimmagina.it/Immagini_Varie/abbigliamento-calzature.jpg',NULL,1,1,5),(5,'2016-03-07 14:50:27','2016-03-07 14:50:27','e7f7e115-9960-421c-867d-0bb44f6ecb55','image/jpg','MCLH',1,'http://thumbs.ebaystatic.com/images/m/mwbgO8rOO--5ZkKHL27QCXA/s-l225.jpg',NULL,1,1,6),(6,'2016-03-07 14:50:27','2016-03-07 14:50:27','f4f39332-a47c-4c75-a68e-5573239bee3a','image/jpg','WDRESS',1,'http://raylenne.info/images/vestiti-donna-estivi/vestiti-donna-estivi-27-4.jpg',NULL,1,1,7),(7,'2016-03-07 14:50:27','2016-03-07 14:50:27','d1d85720-cbc3-4b42-9bc0-7d3bbbf562a4','image/jpg','WTOP',1,'http://thumbs1.ebaystatic.com/d/l225/m/mc9rVN68oXoKeahAcIkj6bw.jpg',NULL,1,1,8),(8,'2016-03-07 14:50:27','2016-03-07 14:50:27','48ad4e7e-eccf-40c9-b84a-7553fd8c64f6','image/jpg','WBAGS',1,'http://www.keys.it/sites/default/files/13E_OK335.jpg',NULL,1,1,9),(9,'2016-03-07 14:50:27','2016-03-07 14:50:27','e69ff76d-3b8f-4861-8f70-b1a56a735868','image/jpg','MJACK',1,'http://www.punto-shopping.it/c/20-category_default/giacche-uomo.jpg',NULL,1,1,10),(10,'2016-03-07 14:50:27','2016-03-07 14:50:27','b70d2ae7-5eb3-4a52-8c2c-a28a2f2e1785','image/jpg','MACC',1,'http://www.cazzolashop.it/wp-content/uploads/2015/01/accessori-abbigliamento-uomo-bologna.jpg',NULL,1,1,11),(11,'2016-03-07 14:50:27','2016-03-07 14:50:27','903ac3a9-29f8-4b77-9f75-3b6f7ed038ff','image/jpg','MJEANS',1,'http://www.grafiksmania.com/images/autum/Collezione_jeans_uomo_OVS_autunno_inverno_2013_2014.jpg',NULL,1,1,12),(12,'2016-03-07 14:50:28','2016-03-07 14:50:28','22da265c-2bec-4694-b110-ef072e2cd9d3','image/jpg','GCLH',1,'http://www.sportway.it/wordpress/wp-content/uploads/2014/07/300X300-IL-GUFO.jpg',NULL,1,1,13),(13,'2016-03-07 14:50:28','2016-03-07 14:50:28','4becc57a-8880-4713-9e7c-78de98c904f6','image/jpg','BCLH',1,'http://www.altramoda.net/data/cat/big/abbigliamento-bambino_60.jpg',NULL,1,1,14),(14,'2016-03-07 14:50:28','2016-03-07 14:50:28','ac514798-6f6c-447b-af06-b1ab0c2c5a5f','image/jpg','KG610',1,'http://natale.gnius.it/wp-content/uploads/2015/09/il-gufo-abbigliamento-bambini-e1442393834603.jpg',NULL,1,1,15),(15,'2016-03-07 14:50:28','2016-03-07 14:50:28','c7f0bafe-7fd4-4147-90fb-bc0084a78a0c','image/jpg','KB610',1,'http://www.blogmamma.it/wp-content/uploads/2011/05/abbigliamento-bambini-cerimonia-brums-primavera-estate-20111.jpg',NULL,1,1,16),(16,'2016-03-07 14:50:28','2016-03-07 14:51:54','a2754ac4-4f4a-44ef-af37-e86efc9d852e','image/jpg','Z001A',1,'http://static.zara.net/photos//2016/V/0/1/p/1509/001/809/2/w/400/1509001809_1_1_1.jpg?timestamp=1455126996169',NULL,1,1,17),(17,'2016-03-07 14:50:28','2016-03-07 14:51:54','27a22c2c-0dd1-44c8-a5f0-473b8ac98f6a','image/jpg','Z001B',2,'http://static.zara.net/photos///2016/V/0/1/p/1509/001/809/2/w/1024/1509001809_2_1_1.jpg?ts=1455126953253',NULL,1,1,18),(18,'2016-03-07 14:50:28','2016-03-07 14:51:54','991f495f-8518-4c7d-b7bb-faf2a1a51b2c','image/jpg','Z001C',3,'http://static.zara.net/photos///2016/V/0/1/p/1509/001/809/2/w/1024/1509001809_2_2_1.jpg?ts=1455130925287',NULL,1,1,19),(19,'2016-03-07 14:50:28','2016-03-07 14:51:55','f1886520-b64c-40bf-8a62-f861489f78f4','image/jpg','Z001D',4,'http://static.zara.net/photos///2016/V/0/1/p/1509/001/809/2/w/1024/1509001809_2_3_1.jpg?ts=1455126965285',NULL,1,1,20),(20,'2016-03-07 14:50:29','2016-03-07 14:51:55','10c13466-379e-4e94-b49f-73a98748b550','image/jpg','Z002A',1,'http://static.zara.net/photos///2016/V/0/1/p/1165/076/605/2/w/560/1165076605_1_1_1.jpg?ts=1453906368545',NULL,1,1,21),(21,'2016-03-07 14:50:29','2016-03-07 14:51:55','f24b56ff-d8b8-4a04-b5b7-ba23b0698736','image/jpg','Z002B',2,'http://static.zara.net/photos///2016/V/0/1/p/1165/076/605/2/w/560/1165076605_2_1_1.jpg?ts=1453906316093',NULL,1,1,22),(22,'2016-03-07 14:50:29','2016-03-07 14:51:55','6e109ed1-2ac9-4d2d-9009-e7ae47f636cb','image/jpg','Z002C',3,'http://static.zara.net/photos///2016/V/0/1/p/1165/076/605/2/w/560/1165076605_2_2_1.jpg?ts=1453906322606',NULL,1,1,23),(23,'2016-03-07 14:50:29','2016-03-07 14:51:56','841c2c38-9a0b-4173-ac45-62ac992e8492','image/jpg','Z002D',4,'http://static.zara.net/photos///2016/V/0/1/p/1165/076/605/2/w/560/1165076605_2_3_1.jpg?ts=1453906329181',NULL,1,1,24),(24,'2016-03-07 14:50:29','2016-03-07 14:51:56','c45904ec-be63-45b3-b7f5-e4aa2e4dd46e','image/jpg','A001A',1,'http://demandware.edgesuite.net/sits_pod14-adidas/dw/image/v2/aagl_prd/on/demandware.static/Sites-adidas-IT-Site/Sites-adidas-products/it_IT/v1456726800281/zoom/AK2355_01_standard.jpg?sw=500&sfrm=jpg',NULL,1,1,25),(25,'2016-03-07 14:50:29','2016-03-07 14:51:56','1b562aeb-315a-45cf-9f16-64cc1fa1c1f8','image/jpg','A001B',2,'http://demandware.edgesuite.net/sits_pod14-adidas/dw/image/v2/aagl_prd/on/demandware.static/Sites-adidas-IT-Site/Sites-adidas-products/it_IT/v1456726800281/zoom/AK2355_02_standard.jpg?sw=500&sfrm=jpg',NULL,1,1,26),(26,'2016-03-07 14:50:29','2016-03-07 14:51:56','c450fbe6-4e99-4227-afa0-53cc7c2b9152','image/jpg','A001C',3,'http://demandware.edgesuite.net/sits_pod14-adidas/dw/image/v2/aagl_prd/on/demandware.static/Sites-adidas-IT-Site/Sites-adidas-products/it_IT/v1456726800281/zoom/AK2355_03_standard.jpg?sw=500&sfrm=jpg',NULL,1,1,27),(27,'2016-03-07 14:50:30','2016-03-07 14:51:57','35356577-b80b-409d-9943-c40a0c5e5915','image/jpg','A001D',4,'http://demandware.edgesuite.net/sits_pod14-adidas/dw/image/v2/aagl_prd/on/demandware.static/Sites-adidas-IT-Site/Sites-adidas-products/it_IT/v1456726800281/zoom/AK2355_04_standard.jpg?sw=500&sfrm=jpg',NULL,1,1,28),(28,'2016-03-07 14:50:30','2016-03-07 14:51:57','ec3875f7-f4d4-41d9-a57b-6e96f6e7a281','image/jpg','A001E',5,'http://demandware.edgesuite.net/sits_pod14-adidas/dw/image/v2/aagl_prd/on/demandware.static/Sites-adidas-IT-Site/Sites-adidas-products/it_IT/v1456726800281/zoom/AK2355_05_hover_standard.jpg?sw=500&sfrm=jpg',NULL,1,1,29),(29,'2016-03-07 14:50:30','2016-03-07 14:51:57','d009c7a5-8ed7-4d1b-8999-0cea808154a2','image/jpg','A001F',6,'http://demandware.edgesuite.net/sits_pod14-adidas/dw/image/v2/aagl_prd/on/demandware.static/Sites-adidas-IT-Site/Sites-adidas-products/it_IT/v1456726800281/zoom/AK2355_25_outfit.jpg?sw=500&sfrm=jpg',NULL,1,1,30),(30,'2016-03-07 14:50:30','2016-03-07 14:51:57','d479dee9-c469-41a7-aea0-0a4f9003484b','image/jpg','MD001A',1,'http://d3j3gwnzh9gv18.cloudfront.net/Mandarina/Images/Catalog/FZT62-651-02-PRDT.JPG?v=DBkOIoCgtc6BBcIE3KT/fg==',NULL,1,1,31),(31,'2016-03-07 14:50:30','2016-03-07 14:51:57','39bd8997-af86-4ce5-b787-e4e2c7f6035d','image/jpg','MD001B',2,'http://d3j3gwnzh9gv18.cloudfront.net/Mandarina/Images/Catalog/FZT62-651-03-PRDT.JPG?v=DBkOIoCgtc6BBcIE3KT/fg==',NULL,1,1,32),(32,'2016-03-07 14:50:30','2016-03-07 14:51:58','ccd50836-a38f-4ff7-aaf8-d89b9421135f','image/jpg','MD001C',3,'http://d3j3gwnzh9gv18.cloudfront.net/Mandarina/Images/Catalog/FZT62-651-04-PRDT.JPG?v=DBkOIoCgtc6BBcIE3KT/fg==',NULL,1,1,33),(33,'2016-03-07 14:50:30','2016-03-07 14:51:58','a39e3f97-0cff-41d8-b2fa-287484e65d72','image/jpg','L001A',1,'http://lsco.scene7.com/is/image/lsco/Levi/clothing/045111659-2015-spring-front-pdp.jpg?$1330x800main$',NULL,1,1,34),(34,'2016-03-07 14:50:30','2016-03-07 14:51:58','f41b6855-7cba-42fd-9199-bd963b131b3a','image/jpg','L001B',2,'http://lsco.scene7.com/is/image/lsco/Levi/clothing/045111659-2015-spring-side-pdp.jpg?$1330x800main$',NULL,1,1,35),(35,'2016-03-07 14:50:31','2016-03-07 14:51:58','912ffe35-64d0-479e-b41d-e9d640a5bcc9','image/jpg','L001C',3,'http://lsco.scene7.com/is/image/lsco/Levi/clothing/045111659-2015-spring-back-pdp.jpg?$1330x800main$',NULL,1,1,36),(36,'2016-03-07 14:50:31','2016-03-07 14:51:58','62f0096d-39f2-4cce-9daa-fd40df39becd','image/jpg','L001D',4,'http://lsco.scene7.com/is/image/lsco/Levi/clothing/045111659-2015-spring-detail1-pdp.jpg?$1330x800det$',NULL,1,1,37),(37,'2016-03-07 14:50:31','2016-03-07 14:51:59','3edf0c9f-a106-4519-b00c-6bacdbc5410d','image/jpg','L001E',5,'http://lsco.scene7.com/is/image/lsco/Levi/clothing/045111659-2015-spring-detail2-pdp.jpg?$1330x800det$',NULL,1,1,38),(38,'2016-03-07 14:50:31','2016-03-07 14:51:59','4e52ae92-fd57-4307-9fcf-850f46c8c7ed','image/jpg','L001F',6,'http://lsco.scene7.com/is/image/lsco/Levi/clothing/045111659-2015-spring-detail3-pdp.jpg?$1330x800det$',NULL,1,1,39),(39,'2016-03-07 14:50:31','2016-03-07 14:51:59','93807bc3-8f8c-4c96-ad1b-81a5d29a8e6a','image/jpg','MP001A',1,'http://store.meltinpot.com/images/VARIANT/large/MARKUS_D1577-UD121_BS15_1.jpg',NULL,1,1,40),(40,'2016-03-07 14:50:31','2016-03-07 14:51:59','584bed14-193b-4904-9545-31ac9a596e1c','image/jpg','MP001B',2,'http://store.meltinpot.com/images/variant/large/MARKUS_D1577-UD121_BS15_2.jpg',NULL,1,1,41),(41,'2016-03-07 14:50:31','2016-03-07 14:52:00','527d0190-1b9a-4c1a-81df-9ef56c781588','image/jpg','L002A',1,'http://lsco.scene7.com/is/image/lsco/Levi/clothing/228070001-front-pdp.jpg?$1330x800main$',NULL,1,1,42),(42,'2016-03-07 14:50:31','2016-03-07 14:52:00','b37e48b7-171c-4f08-aeca-7e7124069710','image/jpg','L002B',2,'http://lsco.scene7.com/is/image/lsco/Levi/clothing/228070001-back-pdp.jpg?$1330x800main$',NULL,1,1,43),(43,'2016-03-07 14:50:32','2016-03-07 14:52:00','f8f63e65-2a93-47c8-b194-589d01b6c8c1','image/jpg','L002C',3,'http://lsco.scene7.com/is/image/lsco/Levi/clothing/228070001-detail1-pdp.jpg?$1330x800det$',NULL,1,1,44),(44,'2016-03-07 14:50:32','2016-03-07 14:52:00','c6c4faf5-32a6-4b7d-8192-8c6ae86056e0','image/jpg','L002D',4,'http://lsco.scene7.com/is/image/lsco/Levi/clothing/228070001-detail2-pdp.jpg?$1330x800det$',NULL,1,1,45),(45,'2016-03-07 14:50:32','2016-03-07 14:52:01','48b939c5-bf94-49b8-980f-e223a093d883','image/jpg','W001A',1,'http://images-eu.wrangler.it/product/W7275DS12/1_zoom/cap-sleeve-tee.jpg',NULL,1,1,46),(46,'2016-03-07 14:50:32','2016-03-07 14:52:01','56e817aa-1f46-4892-b74f-94480aac0a2e','image/jpg','W001B',2,'http://images-eu.wrangler.it/product/W7275DS12/2_zoom/cap-sleeve-tee.jpg',NULL,1,1,47),(47,'2016-03-07 14:50:32','2016-03-07 14:52:01','3153505a-a1fe-42a4-ae0a-bed6ebd34689','image/jpg','W001C',3,'http://images-eu.wrangler.it/product/W7275DS12/3_zoom/cap-sleeve-tee.jpg',NULL,1,1,48),(48,'2016-03-07 14:50:32','2016-03-07 14:52:01','987b9b96-42fe-4d3b-bed9-248d5a5a1e47','image/jpg','W001D',4,'http://images-eu.wrangler.it/product/W7275DS12/4_zoom/cap-sleeve-tee.jpg',NULL,1,1,49),(49,'2016-03-07 14:50:32','2016-03-07 14:52:01','a14342bb-7b53-4ec6-8aae-648dc5390bcc','image/jpg','W002A',1,'http://images-eu.wrangler.it/product/W4611VPPP/1_zoom/the-classic-bomber.jpg',NULL,1,1,50),(50,'2016-03-07 14:50:32','2016-03-07 14:52:02','569ae7b8-c041-4554-b771-b7ae3b287562','image/jpg','W002B',2,'http://images-eu.wrangler.it/product/W4611VPPP/2_zoom/the-classic-bomber.jpg',NULL,1,1,51),(51,'2016-03-07 14:50:33','2016-03-07 14:52:02','7f9b4e24-7bbd-4004-aae3-d857d3add53d','image/jpg','W002C',3,'http://images-eu.wrangler.it/product/W4611VPPP/3_zoom/the-classic-bomber.jpg',NULL,1,1,52),(52,'2016-03-07 14:50:33','2016-03-07 14:52:02','30eefe10-1d27-4ed5-a6ec-b101267b1072','image/jpg','W002D',4,'http://images-eu.wrangler.it/product/W4611VPPP/4_zoom/the-classic-bomber.jpg',NULL,1,1,53),(53,'2016-03-07 14:50:33','2016-03-07 14:52:02','9d345e4b-5d81-43c1-ae51-fe728ed7fb04','image/jpg','BS001A',1,'http://demandware.edgesuite.net/sits_pod28/dw/image/v2/AAWE_PRD/on/demandware.static/-/Sites-belstaff_master_catalog/default/dw95740fb1/products/sedgefield-coat-dark-navy-71010095C50N032380010_T.jpg?sw=158&sh=158&sm=fit',NULL,1,1,54),(54,'2016-03-07 14:50:33','2016-03-07 14:52:03','7e9823e9-04ec-4cd8-a4c8-ac84fdf6b274','image/jpg','BS001G',2,'http://demandware.edgesuite.net/sits_pod28/dw/image/v2/AAWE_PRD/on/demandware.static/-/Sites-belstaff_master_catalog/default/dw1a99f39a/products/sedgefield-coat-dark-navy-71010095C50N032380010_LK_ALT.jpg?sw=711&sh=907&sm=fit',NULL,1,1,55),(55,'2016-03-07 14:50:33','2016-03-07 14:52:03','13e01993-2911-420e-b1ad-c6326dd7bf27','image/jpg','BS001C',3,'http://demandware.edgesuite.net/sits_pod28/dw/image/v2/AAWE_PRD/on/demandware.static/-/Sites-belstaff_master_catalog/default/dw3c813e6f/products/sedgefield-coat-dark-navy-71010095C50N032380010_ALT1.jpg?sw=711&sh=907&sm=fit',NULL,1,1,56),(56,'2016-03-07 14:50:33','2016-03-07 14:52:03','8acb7288-b074-4770-9f40-c72c0e998b6d','image/jpg','BS001D',4,'http://demandware.edgesuite.net/sits_pod28/dw/image/v2/AAWE_PRD/on/demandware.static/-/Sites-belstaff_master_catalog/default/dwaea18a5a/products/sedgefield-coat-dark-navy-71010095C50N032380010_ALT2.jpg?sw=711&sh=907&sm=fit',NULL,1,1,57),(57,'2016-03-07 14:50:33','2016-03-07 14:52:03','f7efb6ce-1489-4be9-846a-c76651540d20','image/jpg','BS001E',5,'http://demandware.edgesuite.net/sits_pod28/dw/image/v2/AAWE_PRD/on/demandware.static/-/Sites-belstaff_master_catalog/default/dw02f6070e/products/sedgefield-coat-dark-navy-71010095C50N032380010_ALT3.jpg?sw=711&sh=907&sm=fit',NULL,1,1,58),(58,'2016-03-07 14:50:34','2016-03-07 14:52:04','2903794e-466f-46e6-b58e-f0c0c00fa1cc','image/jpg','BS001F',6,'http://demandware.edgesuite.net/sits_pod28/dw/image/v2/AAWE_PRD/on/demandware.static/-/Sites-belstaff_master_catalog/default/dw95740fb1/products/sedgefield-coat-dark-navy-71010095C50N032380010_T.jpg?sw=711&sh=907&sm=fit',NULL,1,1,59),(59,'2016-03-07 14:50:34','2016-03-07 14:52:04','00efbe69-a1f4-4e74-b84d-0732f42e9c92','image/jpg','BS002A',1,'http://demandware.edgesuite.net/sits_pod28/dw/image/v2/AAWE_PRD/on/demandware.static/-/Sites-belstaff_master_catalog/default/dw992fdfc4/products/aldergrove-hat-blue-75630043K77A003680062.jpg?sw=711&sh=907&sm=fit',NULL,1,1,60),(60,'2016-03-07 14:50:34','2016-03-07 14:52:05','2dea7fec-168d-4581-bda8-07b85b7ab71d','image/jpg','BS002B',2,'http://demandware.edgesuite.net/sits_pod28/dw/image/v2/AAWE_PRD/on/demandware.static/-/Sites-belstaff_master_catalog/default/dw1ebc8460/products/aldergrove-hat-blue-75630043K77A003680062_ALT1.jpg?sw=711&sh=907&sm=fit',NULL,1,1,61),(61,'2016-03-07 14:50:34','2016-03-07 14:52:05','f883f43e-11c1-4cdb-b7c6-b0a8ac778373','image/jpg','W003A',1,'http://images-eu.wrangler.it/product/W0S04UF67/1_zoom/camouflage-scarf.jpg',NULL,1,1,62),(62,'2016-03-07 14:50:34','2016-03-07 14:52:05','8cfa07bb-f50f-4b3f-aef0-2119d15dca97','image/jpg','W003B',2,'http://images-eu.wrangler.it/product/W0S04UF67/2_zoom/camouflage-scarf.jpg',NULL,1,1,63),(63,'2016-03-07 14:50:34','2016-03-07 14:52:05','75481baa-6ad1-4894-8ec7-fde1b453c81c','image/jpg','W003C',3,'http://images-eu.wrangler.it/product/W0S04UF67/3_zoom/camouflage-scarf.jpg',NULL,1,1,64),(64,'2016-03-07 14:50:34','2016-03-07 14:52:06','44565519-ae74-4ab3-b6ec-bcd5a006fc93','image/jpg','BR001A',1,'http://www.brums.com/media/catalog/product/cache/1/image/495x/c36e9dcf88d1d100f38ed5b59bdae37e/1/5/153bgim013-078_1.jpg',NULL,1,1,65),(65,'2016-03-07 14:50:35','2016-03-07 14:52:06','d68a2e9e-3c92-4660-8ea3-166e4c3f6440','image/jpg','BR001B',2,'http://www.brums.com/media/catalog/product/cache/1/thumbnail/9df78eab33525d08d6e5fb8d27136e95/1/5/153bgim013-078-p1_1.jpg',NULL,1,1,66),(66,'2016-03-07 14:50:35','2016-03-07 14:52:06','ed90d777-a458-4fd7-ac63-5419ec6ba588','image/jpg','BR001C',3,'http://www.brums.com/media/catalog/product/cache/1/thumbnail/9df78eab33525d08d6e5fb8d27136e95/1/5/153bgim013-078-p2_1.jpg',NULL,1,1,67),(67,'2016-03-07 14:50:35','2016-03-07 14:52:06','7bf0fbc1-62d5-4f7e-b063-241dea7739b5','image/jpg','BR001D',4,'http://www.brums.com/media/catalog/product/cache/1/thumbnail/9df78eab33525d08d6e5fb8d27136e95/1/5/153bgim013-078-p3_1.jpg',NULL,1,1,68),(68,'2016-03-07 14:50:35','2016-03-07 14:52:06','74b91749-5fc3-482e-8400-b4449970de94','image/jpg','BN001A',1,'http://shopben-dunebuggysrl.netdna-ssl.com/media/catalog/product/cache/0/image/441x557/5e06319eda06f020e43594a9c230972d/0/2/02-16P_3TM4C13KP_23W-01_1.jpg',NULL,1,1,69),(69,'2016-03-07 14:50:35','2016-03-07 14:52:07','e338326b-fedf-4acf-8883-b8219e2eb3c8','image/jpg','BN001B',2,'http://shopben-dunebuggysrl.netdna-ssl.com/media/catalog/product/cache/0/image/1000x1264/5e06319eda06f020e43594a9c230972d/0/2/02-16P_3TM4C13KP_23W-03_1.jpg',NULL,1,1,70),(70,'2016-03-07 14:50:35','2016-03-07 14:52:07','6056d4fb-491f-40a7-87d8-d372bb0cf406','image/jpg','BN001C',3,'http://shopben-dunebuggysrl.netdna-ssl.com/media/catalog/product/cache/0/image/1000x1264/5e06319eda06f020e43594a9c230972d/0/2/02-16P_3TM4C13KP_23W-03.jpg',NULL,1,1,71),(71,'2016-03-07 14:50:35','2016-03-07 14:52:07','4ff8b336-d2dd-4971-8f1f-4657e1114950','image/jpg','BN002A',1,'http://shopben-dunebuggysrl.netdna-ssl.com/media/catalog/product/cache/0/image/1000x1264/5e06319eda06f020e43594a9c230972d/0/2/02-16P_2ALC537E0_100-02.jpg',NULL,1,1,72),(72,'2016-03-07 14:50:35','2016-03-07 14:52:07','488d0936-2aaa-4611-92e3-1e789c544c0d','image/jpg','BN002B',2,'http://shopben-dunebuggysrl.netdna-ssl.com/media/catalog/product/cache/0/image/1000x1264/5e06319eda06f020e43594a9c230972d/0/2/02-16P_2ALC537E0_100-02.jpg',NULL,1,1,73),(73,'2016-03-07 14:50:36','2016-03-07 14:52:08','de1f39c3-b834-4743-9a0b-076f9e212834','image/jpg','BN002C',3,'http://shopben-dunebuggysrl.netdna-ssl.com/media/catalog/product/cache/0/image/1000x1264/5e06319eda06f020e43594a9c230972d/0/2/02-16P_2ALC537E0_100-03.jpg',NULL,1,1,74),(74,'2016-03-07 14:50:36','2016-03-07 14:52:08','967e0384-f2d0-425e-b476-73c8fa622b69','image/jpg','A002A',1,'http://demandware.edgesuite.net/sits_pod14-adidas/dw/image/v2/aagl_prd/on/demandware.static/Sites-adidas-IT-Site/Sites-adidas-products/it_IT/v1456983515166/zoom/S12867_01_laydown.jpg?sw=500&sfrm=jpg',NULL,1,1,75),(75,'2016-03-07 14:50:36','2016-03-07 14:52:08','31bc955e-ed57-4ca1-9454-9cda0dc8d38c','image/jpg','A002B',2,'http://demandware.edgesuite.net/sits_pod14-adidas/dw/image/v2/aagl_prd/on/demandware.static/Sites-adidas-IT-Site/Sites-adidas-products/it_IT/v1456983515166/zoom/S12867_02_laydown.jpg?sw=500&sfrm=jpg',NULL,1,1,76),(76,'2016-03-07 14:50:36','2016-03-07 14:52:08','4225ed37-6554-444b-a99d-0627e4f00b67','image/jpg','A002C',3,'http://demandware.edgesuite.net/sits_pod14-adidas/dw/image/v2/aagl_prd/on/demandware.static/Sites-adidas-IT-Site/Sites-adidas-products/it_IT/v1456983515166/zoom/S12867_41_detail.jpg?sw=500&sfrm=jpg',NULL,1,1,77),(77,'2016-03-07 14:50:36','2016-03-07 14:52:08','961b8794-d0d0-4258-85a7-bb926e07c659','image/jpg','A002D',4,'http://demandware.edgesuite.net/sits_pod14-adidas/dw/image/v2/aagl_prd/on/demandware.static/Sites-adidas-IT-Site/Sites-adidas-products/it_IT/v1456983515166/zoom/S12867_42_detail.jpg?sw=500&sfrm=jpg',NULL,1,1,78),(78,'2016-03-07 14:50:36','2016-03-07 14:52:09','37440b9a-9109-4c89-9866-8bfc0fff8c67','image/jpg','A002E',5,'http://demandware.edgesuite.net/sits_pod14-adidas/dw/image/v2/aagl_prd/on/demandware.static/Sites-adidas-IT-Site/Sites-adidas-products/it_IT/v1456983515166/zoom/S12867_43_detail.jpg?sw=500&sfrm=jpg',NULL,1,1,79),(79,'2016-03-17 12:44:31','2016-03-17 12:44:31','9bc1b487-50b2-4013-8af6-cc40226e1b48','image/jpeg','prova',0,'/1/57F/E91/8fb16a16ea5b380a0a1a7ab55be6338d_marcus46.jpg',NULL,0,1,506);
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
  UNIQUE KEY `UK_4g9gkxu06l109lssbdv35req8` (`uuid`),
  KEY `FK_fwh5s5953u5jjqoaeahptgjuw` (`base_attributes_id`),
  KEY `FK_2e2flctpliriep0fhoyt897sc` (`member_id`),
  CONSTRAINT `FK_2e2flctpliriep0fhoyt897sc` FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`),
  CONSTRAINT `FK_fwh5s5953u5jjqoaeahptgjuw` FOREIGN KEY (`base_attributes_id`) REFERENCES `base_attributes` (`base_attributes_id`)
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
  UNIQUE KEY `UK_9a9k36j62nrr6hmx70dp37l6b` (`uuid`),
  KEY `FK_rhl57wp33s12cc519opedvy47` (`store_id`),
  CONSTRAINT `FK_rhl57wp33s12cc519opedvy47` FOREIGN KEY (`store_id`) REFERENCES `stores` (`store_id`)
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
  `primary_address_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`member_id`),
  UNIQUE KEY `UK_rw06128d52kj31dgfo4duoapr` (`uuid`),
  KEY `FK_m2e7t1gpsi13yg4ef62gbsw2x` (`primary_address_id`),
  CONSTRAINT `FK_m2e7t1gpsi13yg4ef62gbsw2x` FOREIGN KEY (`primary_address_id`) REFERENCES `PrimaryAddress` (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members`
--

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;
INSERT INTO `members` (`member_id`, `created`, `updated`, `uuid`, `field1`, `field2`, `field3`, `member_type`, `primary_address_id`) VALUES (1,'2016-03-07 14:42:24','2016-03-07 14:42:24','aa9dc32f-a5c8-4830-9e41-5dfc875c69a2',NULL,NULL,NULL,'USER',NULL),(2,'2016-03-07 14:42:24','2016-03-18 11:02:28','f33cac7a-51a2-4c56-be46-a8bb40cd5547',NULL,NULL,NULL,'USER',1),(3,'2016-03-08 12:05:06','2016-03-18 11:00:58','cdf4306b-9419-4bc0-88ef-b6de28ccffcd',NULL,NULL,NULL,'USER',2),(4,'2016-03-08 12:07:28','2016-03-14 12:10:51','6a372e76-0394-4ef2-9975-de4aa20557ed',NULL,NULL,NULL,'USER',NULL);
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
  UNIQUE KEY `UK_sx8a9f2i1xmqfc6a44vxuilf9` (`uuid`),
  KEY `FK_d7hidhfcvspjt4grh7gcgt418` (`role_id`),
  KEY `FK_osspcsh6h6d5l7mxj4yklwcbm` (`store_id`),
  CONSTRAINT `FK_b1c2ievupes5otqscteomj30q` FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`),
  CONSTRAINT `FK_d7hidhfcvspjt4grh7gcgt418` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
  CONSTRAINT `FK_osspcsh6h6d5l7mxj4yklwcbm` FOREIGN KEY (`store_id`) REFERENCES `stores` (`store_id`)
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
  UNIQUE KEY `UK_ni32bkuksqnt7qknwnd35caq8` (`uuid`),
  KEY `FK_9it61dc3tny9dmhwrn4cweekp` (`membergroups_id`),
  CONSTRAINT `FK_9it61dc3tny9dmhwrn4cweekp` FOREIGN KEY (`membergroups_id`) REFERENCES `membergroups` (`membergroups_id`),
  CONSTRAINT `FK_9uosvohbagosovehhdvk6enve` FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`)
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
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_nxu97ljimcd062dgkxo2lcbdq` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=507 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `multilingual_string`
--

LOCK TABLES `multilingual_string` WRITE;
/*!40000 ALTER TABLE `multilingual_string` DISABLE KEYS */;
INSERT INTO `multilingual_string` (`Id`, `created`, `updated`, `uuid`) VALUES (1,'2016-03-07 14:42:25','2016-03-07 14:42:25','e7845dfc-24e8-4d38-bcbf-a576e8a7437e'),(2,'2016-03-07 14:50:26','2016-03-07 14:50:26','2ae15dd8-2bc2-444e-9a40-c1b7d1b4d344'),(3,'2016-03-07 14:50:26','2016-03-07 14:50:26','f1d8fa6d-e4f3-4749-872b-5c47d967c27d'),(4,'2016-03-07 14:50:26','2016-03-07 14:50:26','9a679ee9-f4b4-40d7-a154-b613d13919d6'),(5,'2016-03-07 14:50:27','2016-03-07 14:50:27','f3ecc7b6-e3f2-46c0-8e28-e56bff5863dc'),(6,'2016-03-07 14:50:27','2016-03-07 14:50:27','b28c1d97-97a9-4653-b7fa-fa82872dc36f'),(7,'2016-03-07 14:50:27','2016-03-07 14:50:27','1bc7f79f-6f74-4213-b970-707799453ea4'),(8,'2016-03-07 14:50:27','2016-03-07 14:50:27','cba7c000-f82a-48cc-8950-4bde80f7d127'),(9,'2016-03-07 14:50:27','2016-03-07 14:50:27','7b5ffa68-2a02-4195-ad63-4f98e94c5bd0'),(10,'2016-03-07 14:50:27','2016-03-07 14:50:27','54f40414-6de4-4ac4-b4a0-dd5b9410d558'),(11,'2016-03-07 14:50:27','2016-03-07 14:50:27','b0d251c8-4bd2-494e-a8d8-87c03fd127fb'),(12,'2016-03-07 14:50:27','2016-03-07 14:50:27','861315b0-5abd-45fd-990a-2fd6a6eb6c83'),(13,'2016-03-07 14:50:28','2016-03-07 14:50:28','8c29edab-b840-4acf-bad7-9062c4cddb6e'),(14,'2016-03-07 14:50:28','2016-03-07 14:50:28','a0c91a5f-a7f7-4081-9a27-14dbff8ba914'),(15,'2016-03-07 14:50:28','2016-03-07 14:50:28','6206b813-956d-4426-9b34-6f0581f229ca'),(16,'2016-03-07 14:50:28','2016-03-07 14:50:28','63f2dc44-cc4f-454e-8dd9-27044f9642b7'),(17,'2016-03-07 14:50:28','2016-03-07 14:50:28','f619c69e-07d8-41c3-8869-95cf8a9965e9'),(18,'2016-03-07 14:50:28','2016-03-07 14:50:28','c727c1ac-fd65-47d8-a20d-7ac369fe4551'),(19,'2016-03-07 14:50:28','2016-03-07 14:50:28','24173f6d-4dd0-4721-b6e8-0cfd96ad144f'),(20,'2016-03-07 14:50:28','2016-03-07 14:50:28','5faac1ce-9886-40fe-ab56-8760f04b1561'),(21,'2016-03-07 14:50:29','2016-03-07 14:50:29','d19182c2-2e8a-4bc8-a182-2a3e0d301c8b'),(22,'2016-03-07 14:50:29','2016-03-07 14:50:29','b03a9351-690f-4fac-b8de-c70daf33d3c6'),(23,'2016-03-07 14:50:29','2016-03-07 14:50:29','fc1fcf29-5549-4c54-9481-874e97765256'),(24,'2016-03-07 14:50:29','2016-03-07 14:50:29','2e21c350-6a9d-4178-af32-b9da78732376'),(25,'2016-03-07 14:50:29','2016-03-07 14:50:29','2f78941d-479e-475c-aa51-5f690e3cd597'),(26,'2016-03-07 14:50:29','2016-03-07 14:50:29','00695b6f-18e1-4a63-b62a-13ed0ae3ed86'),(27,'2016-03-07 14:50:29','2016-03-07 14:50:29','45608a1e-6638-41ab-b6b1-2cefc7840a2a'),(28,'2016-03-07 14:50:30','2016-03-07 14:50:30','be64f893-4c6b-4b25-be81-9fc44d61cbb5'),(29,'2016-03-07 14:50:30','2016-03-07 14:50:30','0c595a1b-b582-495e-a1c7-0f801cd2a72d'),(30,'2016-03-07 14:50:30','2016-03-07 14:50:30','e2c53de5-df45-4e30-9a49-84873d7eeecb'),(31,'2016-03-07 14:50:30','2016-03-07 14:50:30','e659926f-dcc4-4fce-ae8a-ec0dcc43f3f6'),(32,'2016-03-07 14:50:30','2016-03-07 14:50:30','37d32074-cf22-48a6-938e-026d7a7b7cee'),(33,'2016-03-07 14:50:30','2016-03-07 14:50:30','6e83638a-e202-47e1-9195-7556f8448acd'),(34,'2016-03-07 14:50:30','2016-03-07 14:50:30','6b7d3ef0-c323-404e-8752-2c1fd9b59c78'),(35,'2016-03-07 14:50:30','2016-03-07 14:50:30','6b3c0f73-9b4c-4b37-acb5-d5bfc00f23bc'),(36,'2016-03-07 14:50:31','2016-03-07 14:50:31','bf156335-d04b-4cb1-815a-91c4335093b9'),(37,'2016-03-07 14:50:31','2016-03-07 14:50:31','33588286-6598-473f-b2f7-46c10945b66d'),(38,'2016-03-07 14:50:31','2016-03-07 14:50:31','8d63b4e4-572d-4b67-ae2a-01dc5c3860c6'),(39,'2016-03-07 14:50:31','2016-03-07 14:50:31','df520753-4019-4ed9-9ac3-16a6e7633a2a'),(40,'2016-03-07 14:50:31','2016-03-07 14:50:31','da3e66fe-ed15-45f2-8706-38e575586899'),(41,'2016-03-07 14:50:31','2016-03-07 14:50:31','c8b2f961-447e-4117-94ad-acd3812212fa'),(42,'2016-03-07 14:50:31','2016-03-07 14:50:31','dc763344-9692-4969-bce1-0561f3fff2d6'),(43,'2016-03-07 14:50:31','2016-03-07 14:50:31','83fba484-bffe-41b7-9581-b8a33b93b96e'),(44,'2016-03-07 14:50:32','2016-03-07 14:50:32','0d059331-eac8-4b27-8f2b-8946d7638645'),(45,'2016-03-07 14:50:32','2016-03-07 14:50:32','127d3fce-11c6-47bb-aca4-036b0040d95d'),(46,'2016-03-07 14:50:32','2016-03-07 14:50:32','a329fc8a-d0b4-4bac-8686-3d9d79fc9a48'),(47,'2016-03-07 14:50:32','2016-03-07 14:50:32','601a705c-df1c-4812-8f22-3ac92d732328'),(48,'2016-03-07 14:50:32','2016-03-07 14:50:32','5882bae1-c493-4c54-be88-3207487fd764'),(49,'2016-03-07 14:50:32','2016-03-07 14:50:32','9b492489-bf34-4ed5-a221-69c47fdd612c'),(50,'2016-03-07 14:50:32','2016-03-07 14:50:32','a33c1e19-fa17-4b23-a292-385b4de831de'),(51,'2016-03-07 14:50:32','2016-03-07 14:50:32','50a805fd-6dd2-426d-bd28-342fc0fbca4a'),(52,'2016-03-07 14:50:33','2016-03-07 14:50:33','ef4c6677-c413-455b-ae0a-241835b9e207'),(53,'2016-03-07 14:50:33','2016-03-07 14:50:33','cf093795-7805-48f0-9816-b9e38324563e'),(54,'2016-03-07 14:50:33','2016-03-07 14:50:33','f70bd55b-81d5-4f4b-b4cc-42c91f92afcb'),(55,'2016-03-07 14:50:33','2016-03-07 14:50:33','ffddb361-e13b-4133-8357-f95862a148f9'),(56,'2016-03-07 14:50:33','2016-03-07 14:50:33','2da8736a-5e99-46c2-9579-2009086770bd'),(57,'2016-03-07 14:50:33','2016-03-07 14:50:33','b61dbbcf-a530-44c1-be85-4d5fb5ee38d7'),(58,'2016-03-07 14:50:33','2016-03-07 14:50:33','942cd2b6-d89b-4a29-ad70-ceef29ad29e4'),(59,'2016-03-07 14:50:34','2016-03-07 14:50:34','94e09216-dba2-47dc-a674-3aae7d470d2d'),(60,'2016-03-07 14:50:34','2016-03-07 14:50:34','6b803921-b4dd-4d27-bf87-261deb125771'),(61,'2016-03-07 14:50:34','2016-03-07 14:50:34','be8a0e1e-3bad-49c2-83c7-f913ad6ca61d'),(62,'2016-03-07 14:50:34','2016-03-07 14:50:34','02fd6ac0-d7cc-45ae-b102-222306fa3750'),(63,'2016-03-07 14:50:34','2016-03-07 14:50:34','8a6702c5-0fd2-4f97-8322-34f758ebaf42'),(64,'2016-03-07 14:50:34','2016-03-07 14:50:34','0a0b0410-4069-4a75-9b24-ba9ff09dfcf2'),(65,'2016-03-07 14:50:34','2016-03-07 14:50:34','eef74757-82fb-4195-9645-4b80b6828f28'),(66,'2016-03-07 14:50:35','2016-03-07 14:50:35','d1960eb1-123f-482e-82b6-ac81cc7868e6'),(67,'2016-03-07 14:50:35','2016-03-07 14:50:35','e3d426c1-5483-452c-b727-96298957da8d'),(68,'2016-03-07 14:50:35','2016-03-07 14:50:35','9b98d984-0e94-4329-a99d-36cf1d235c40'),(69,'2016-03-07 14:50:35','2016-03-07 14:50:35','f2b40ae4-c5b5-4096-bc0b-95aa7977611b'),(70,'2016-03-07 14:50:35','2016-03-07 14:50:35','dc9a5524-bea5-4671-a55f-7c83ccabd852'),(71,'2016-03-07 14:50:35','2016-03-07 14:50:35','7de5a0e5-7d24-43de-9db1-feb2e3b8386f'),(72,'2016-03-07 14:50:35','2016-03-07 14:50:35','7633fde5-9d85-48f3-ab9c-0e33b4858227'),(73,'2016-03-07 14:50:35','2016-03-07 14:50:35','7c166b50-7ce7-41c7-8815-c0ac870c25f6'),(74,'2016-03-07 14:50:36','2016-03-07 14:50:36','0e3e6490-ff42-476c-86e7-52f92d8e745e'),(75,'2016-03-07 14:50:36','2016-03-07 14:50:36','5cb7b512-3f77-40c9-8102-38b1b9594a6b'),(76,'2016-03-07 14:50:36','2016-03-07 14:50:36','e67d7ada-d8b5-4190-bf2e-1d207ef5bba9'),(77,'2016-03-07 14:50:36','2016-03-07 14:50:36','cc3e1f29-88e2-421a-b1c1-76282fae710f'),(78,'2016-03-07 14:50:36','2016-03-07 14:50:36','b78b408d-aa7b-40ef-a4fc-e4937a0fab1c'),(79,'2016-03-07 14:50:36','2016-03-07 14:50:36','0cc2bb9e-655d-4040-8b4e-f9e7ed4231de'),(80,'2016-03-07 14:50:58','2016-03-07 14:50:58','bcba8fbe-2232-46a3-84c3-8ec8d004eead'),(81,'2016-03-07 14:50:59','2016-03-07 14:50:59','c40432df-c42e-4779-af79-fc334ef0b219'),(82,'2016-03-07 14:50:59','2016-03-07 14:50:59','e938fc8a-d078-44cc-84b9-6edd759f85e6'),(83,'2016-03-07 14:50:59','2016-03-07 14:50:59','56d02b43-d2b6-4297-b504-75404664be3e'),(84,'2016-03-07 14:50:59','2016-03-07 14:50:59','e618df9e-01fa-4356-9774-6ca02f9cb4da'),(85,'2016-03-07 14:51:00','2016-03-07 14:51:00','0c23eeb1-3fda-4fbe-80e7-a7f039696495'),(86,'2016-03-07 14:51:00','2016-03-07 14:51:00','7d7f2e9f-d3d0-4ce0-93dd-808c0e6a82f2'),(87,'2016-03-07 14:51:00','2016-03-07 14:51:00','cb9033d9-689c-4070-a523-a9f444cb9ff4'),(88,'2016-03-07 14:51:00','2016-03-07 14:51:00','0a79a0b2-3aa3-4206-8e12-e9ede024c339'),(89,'2016-03-07 14:51:00','2016-03-07 14:51:00','b289a07b-dbc8-4a0c-b8d6-e36ba5a601a0'),(90,'2016-03-07 14:51:00','2016-03-07 14:51:00','8e5ac846-9390-4897-9f71-018d0270a55d'),(91,'2016-03-07 14:51:00','2016-03-07 14:51:00','56386a14-f92d-4d8c-9f50-5530518b1ac1'),(92,'2016-03-07 14:51:01','2016-03-07 14:51:01','c725864d-a469-4a0f-a67c-5e14eed6367c'),(93,'2016-03-07 14:51:01','2016-03-07 14:51:01','ecfaec7a-15cd-4729-bf85-06a06d485594'),(94,'2016-03-07 14:51:01','2016-03-07 14:51:01','94914823-f308-4a5f-a005-1c34e3e355c5'),(95,'2016-03-07 14:51:01','2016-03-07 14:51:01','3b29a685-dee8-4038-8a3d-4bf26be2ef5a'),(96,'2016-03-07 14:51:01','2016-03-07 14:51:01','e849d319-a4f2-4fc6-ba11-15ab681fefa6'),(97,'2016-03-07 14:51:16','2016-03-07 14:51:16','493a03a4-081e-4c3f-9262-f1e7ff87df35'),(98,'2016-03-07 14:51:16','2016-03-07 14:51:16','cf37e6b2-3b50-4e3a-a5c3-8cf69eee0241'),(99,'2016-03-07 14:51:16','2016-03-07 14:51:16','01951df9-7983-4bbf-b4db-8dc90b2e5f6e'),(100,'2016-03-07 14:51:16','2016-03-07 14:51:16','440320f2-9639-48aa-8eb8-ff1b488fe24a'),(101,'2016-03-07 14:51:16','2016-03-07 14:51:16','e56dce15-2606-48fb-9e75-9a9d0a351af5'),(102,'2016-03-07 14:51:16','2016-03-07 14:51:16','d53f2966-b14b-4339-9db7-a360315fe84b'),(103,'2016-03-07 14:51:17','2016-03-07 14:51:17','6074b1ff-4b0a-42ee-a94d-87fa40c268ff'),(104,'2016-03-07 14:51:17','2016-03-07 14:51:17','681811b0-1998-44bb-b930-36f5386c7d0c'),(105,'2016-03-07 14:51:23','2016-03-07 14:51:23','20c0fee2-a49e-49e4-8f08-caa3aeb41ce2'),(106,'2016-03-07 14:51:23','2016-03-07 14:51:23','5bea125a-be2b-4b99-bb8a-43a96fd934b7'),(107,'2016-03-07 14:51:24','2016-03-07 14:51:24','b738a267-e222-4a24-afc4-67506e96507c'),(108,'2016-03-07 14:51:24','2016-03-07 14:51:24','23457775-6157-4804-bc44-54a40e17d116'),(109,'2016-03-07 14:51:24','2016-03-07 14:51:24','6456e418-1721-4099-b2ce-6b779c12ccab'),(110,'2016-03-07 14:51:24','2016-03-07 14:51:24','4e7c197b-1c61-45ee-afa8-67b3aacbdc05'),(111,'2016-03-07 14:51:24','2016-03-07 14:51:24','34e25f13-f91c-4bf5-a823-ea8b59865be8'),(112,'2016-03-07 14:51:24','2016-03-07 14:51:24','f6c33dc0-f3e4-4e85-82af-ee328e373580'),(113,'2016-03-07 14:51:25','2016-03-07 14:51:25','dc50164c-8493-4297-9dfb-61a312588947'),(114,'2016-03-07 14:51:25','2016-03-07 14:51:25','72786b44-dac3-4d20-b886-b5cb39f7e85a'),(115,'2016-03-07 14:51:25','2016-03-07 14:51:25','621c5332-691d-4145-8be3-1e6169164316'),(116,'2016-03-07 14:51:25','2016-03-07 14:51:25','e2dd2220-fbbf-4511-a260-afc22b5d859c'),(117,'2016-03-07 14:51:25','2016-03-07 14:51:25','402127e7-2639-4996-b115-fdc77ae03c6d'),(118,'2016-03-07 14:51:25','2016-03-07 14:51:25','7a84cd68-394a-49d6-a30c-8cd30b5af8b5'),(119,'2016-03-07 14:51:26','2016-03-07 14:51:26','544f88e1-d5a7-4d50-acda-f5cc202cefa1'),(120,'2016-03-07 14:51:26','2016-03-07 14:51:26','b82bd22e-3ac0-4ab7-95d4-0925a3e4065e'),(121,'2016-03-07 16:23:31','2016-03-07 16:23:31','d15cc8cd-6950-48be-af42-0c844460b18d'),(122,'2016-03-07 16:33:55','2016-03-07 16:33:55','3bec6c64-0f19-463f-be4a-5bdfe9295af5'),(123,'2016-03-07 16:33:55','2016-03-07 16:33:55','998ff3fb-e1d3-4ee9-b8ff-b8e27eae3ee8'),(124,'2016-03-07 16:33:55','2016-03-07 16:33:55','3b0c305a-8dca-401b-baa3-dc97f0eee84e'),(125,'2016-03-07 16:33:55','2016-03-07 16:33:55','9c9c51a2-3d62-4c0d-ac9c-d3a1ee822239'),(126,'2016-03-07 16:33:55','2016-03-07 16:33:55','1b647f17-9608-47c2-b3f7-f305ded94679'),(127,'2016-03-07 16:33:56','2016-03-07 16:33:56','12c73d4a-4b03-4352-a92e-c173705f9a54'),(128,'2016-03-07 16:33:56','2016-03-07 16:33:56','ad9e5d7e-0b17-4dbf-95bf-6300977b716c'),(129,'2016-03-07 16:33:56','2016-03-07 16:33:56','f7dbb77a-5a9c-400d-ac36-77d057755ead'),(130,'2016-03-07 16:33:56','2016-03-07 16:33:56','d49d7517-c28f-4acf-add3-047489e0c26f'),(131,'2016-03-07 16:33:56','2016-03-07 16:33:56','1d7021b8-f9d4-4ad8-b3b5-b6c77fda5df7'),(132,'2016-03-07 16:33:56','2016-03-07 16:33:56','dd52ee3f-b06c-4465-aac6-e3b1d496f546'),(133,'2016-03-07 16:33:56','2016-03-07 16:33:56','44c0dd78-095f-4c62-a272-a8e1a15bdfb7'),(134,'2016-03-07 16:33:56','2016-03-07 16:33:56','44d2ed67-d8f0-41f1-b54c-eb48c82c0e61'),(135,'2016-03-07 16:33:56','2016-03-07 16:33:56','4e041064-5f9a-4095-a87f-72f78ce12f92'),(136,'2016-03-07 16:33:57','2016-03-07 16:33:57','7b0b3626-8613-4181-8b6c-aac6858b41f5'),(137,'2016-03-07 16:33:57','2016-03-07 16:33:57','0c09a5ab-46a6-494d-9a12-637b047fb6d9'),(138,'2016-03-07 16:33:57','2016-03-07 16:33:57','e51a9480-ed26-4ada-85ee-15b70d9be29c'),(139,'2016-03-07 16:33:57','2016-03-07 16:33:57','5eebf20b-e97d-4d90-9ddb-42cfa6bc2471'),(140,'2016-03-07 16:33:57','2016-03-07 16:33:57','c352847d-07b8-4a0c-9666-3b2c165850a3'),(141,'2016-03-07 16:33:57','2016-03-07 16:33:57','1e739623-992e-42b4-8572-510b1e1b0033'),(142,'2016-03-07 16:33:57','2016-03-07 16:33:57','7e076883-a649-4af7-9eda-e22cea823c4a'),(143,'2016-03-07 16:33:57','2016-03-07 16:33:57','fd110d25-f2d7-4261-85c0-6f1aa593f515'),(144,'2016-03-07 16:33:58','2016-03-07 16:33:58','c9d24e21-8366-4d4f-a9e6-199c5868eb79'),(145,'2016-03-07 16:33:58','2016-03-07 16:33:58','9866525e-7eb8-4fe8-8b73-0a778a2ad044'),(146,'2016-03-07 16:33:58','2016-03-07 16:33:58','b7e3230d-29b6-4791-8a81-0ff85de4678a'),(147,'2016-03-07 16:33:58','2016-03-07 16:33:58','35934744-57f1-4eb7-b5f2-e2d032f32aa7'),(148,'2016-03-07 16:33:58','2016-03-07 16:33:58','8a1b54ea-745b-46c8-ad91-0174373b44bb'),(149,'2016-03-07 16:33:58','2016-03-07 16:33:58','72b0aa24-e464-428a-9e12-ba3bc6c6f944'),(150,'2016-03-07 16:33:58','2016-03-07 16:33:58','6cf24cce-bb40-4af6-b3cc-8cc465a4e867'),(151,'2016-03-07 16:33:58','2016-03-07 16:33:58','94b815d8-7044-4159-a57c-4cfde3b15c6f'),(152,'2016-03-07 16:33:58','2016-03-07 16:33:58','236829dd-a998-4e59-8410-62e2f3b33839'),(153,'2016-03-07 16:33:58','2016-03-07 16:33:58','bdefbb80-998d-4782-9bae-b8872d637cb6'),(154,'2016-03-07 16:33:59','2016-03-07 16:33:59','71ee475f-60c0-4a54-921d-3ed35d15a199'),(155,'2016-03-07 16:33:59','2016-03-07 16:33:59','a1ddf6ce-4e1a-4eeb-9db3-30f2a59ae412'),(156,'2016-03-07 16:33:59','2016-03-07 16:33:59','9c2cdd6d-2981-46e2-b226-fb52b82ad25a'),(157,'2016-03-07 16:33:59','2016-03-07 16:33:59','35bf113e-2d1e-4d20-a658-95443e57b87c'),(158,'2016-03-07 16:33:59','2016-03-07 16:33:59','ab1223cb-a2bd-4596-ac04-289f206b8d82'),(159,'2016-03-07 16:33:59','2016-03-07 16:33:59','c11ad380-d3f9-45da-8d5b-eaee58429f93'),(160,'2016-03-07 16:33:59','2016-03-07 16:33:59','63b8804e-f13f-45fc-bec8-6addd2bce433'),(161,'2016-03-07 16:33:59','2016-03-07 16:33:59','56454ed5-fef4-406f-8ae7-4f27c39a9822'),(162,'2016-03-07 16:34:00','2016-03-07 16:34:00','37966f20-5f8c-40ac-ac18-9f707420a97e'),(163,'2016-03-07 16:34:00','2016-03-07 16:34:00','dff41f1f-a89f-4e5d-8e39-74cc10bca67f'),(164,'2016-03-07 16:34:00','2016-03-07 16:34:00','b4b5fe36-c13f-4afd-b002-e47ba8cd54e7'),(165,'2016-03-07 16:34:00','2016-03-07 16:34:00','cfabfdc0-031a-43a8-8500-710c02827e4d'),(166,'2016-03-07 16:34:00','2016-03-07 16:34:00','4b9e6e47-d73e-4544-a4a4-5d5956bd7e51'),(167,'2016-03-07 16:34:00','2016-03-07 16:34:00','9455bb4b-48c6-444b-ac8d-b753a22cbb34'),(168,'2016-03-07 16:34:00','2016-03-07 16:34:00','006d762d-5f62-4d72-9b91-7f5dae57266d'),(169,'2016-03-07 16:34:00','2016-03-07 16:34:00','cadd1417-a26b-4187-87d3-41de64f574d2'),(170,'2016-03-07 16:34:00','2016-03-07 16:34:00','2cac2e07-4e20-437c-9902-7d974642f1de'),(171,'2016-03-07 16:34:01','2016-03-07 16:34:01','828b55d3-199e-4a21-89a7-7644665d69a1'),(172,'2016-03-07 16:34:01','2016-03-07 16:34:01','60a62dc4-fda6-402c-be3c-1c1e747fae90'),(173,'2016-03-07 16:34:01','2016-03-07 16:34:01','a859f1f8-4d4c-4163-aed9-83f1256ae31e'),(174,'2016-03-07 16:34:01','2016-03-07 16:34:01','a4cda672-255e-4ff4-9213-78f3b95a2ed6'),(175,'2016-03-07 16:34:01','2016-03-07 16:34:01','2a825903-81b6-450c-bb76-40da20326557'),(176,'2016-03-07 16:34:01','2016-03-07 16:34:01','202d0dd1-c147-493a-b70f-338c05530c7b'),(177,'2016-03-07 16:34:01','2016-03-07 16:34:01','99efadaf-a2e1-4d10-b266-8f4a7c8a29d2'),(178,'2016-03-07 16:34:01','2016-03-07 16:34:01','0cf73962-ad9e-4c44-a2df-8cbc314bb86a'),(179,'2016-03-07 16:34:01','2016-03-07 16:34:01','954b67f8-7e0c-4b9c-bbcb-b7fa51ec59e4'),(180,'2016-03-07 16:34:02','2016-03-07 16:34:02','428f6d41-bc0d-4208-b730-a1c006e922b4'),(181,'2016-03-07 16:34:02','2016-03-07 16:34:02','7f83f15d-7ce7-4e5f-9e8c-eebbc2927c62'),(182,'2016-03-07 16:34:02','2016-03-07 16:34:02','bf6e89e3-e1ad-4d74-9e42-28e85103a596'),(183,'2016-03-07 16:34:02','2016-03-07 16:34:02','0786c7ac-1e46-4ba8-ab77-5625861c2f4f'),(184,'2016-03-07 16:34:02','2016-03-07 16:34:02','5bf8d238-17ac-4723-8a9c-7bbf4752efc5'),(185,'2016-03-07 16:34:02','2016-03-07 16:34:02','04b43d82-4999-4080-aa38-bf9604da4714'),(186,'2016-03-07 16:34:02','2016-03-07 16:34:02','2feb0364-9919-4b84-8bf8-1c82ea2dd3e1'),(187,'2016-03-07 16:34:02','2016-03-07 16:34:02','3e2b0506-ff08-4b7d-ab84-9ba2c343513f'),(188,'2016-03-07 16:34:02','2016-03-07 16:34:02','06127c74-8be9-48d7-ad65-e5c2e442f3ca'),(189,'2016-03-07 16:34:03','2016-03-07 16:34:03','67810385-24f7-4282-8ef0-52780861aef6'),(190,'2016-03-07 16:34:03','2016-03-07 16:34:03','dd404e85-934b-464e-8dbd-0a715c7df8d7'),(191,'2016-03-07 16:34:03','2016-03-07 16:34:03','8fe36a55-6f4e-4b50-8667-debbafd6accb'),(192,'2016-03-07 16:34:03','2016-03-07 16:34:03','7100b41c-2ed2-4300-a9ad-47c469296a5d'),(193,'2016-03-07 16:34:03','2016-03-07 16:34:03','dd09a358-d082-46b1-9261-e53d6e957ea5'),(194,'2016-03-07 16:34:03','2016-03-07 16:34:03','113f1853-f352-4629-8360-7816d5a33c06'),(195,'2016-03-07 16:34:03','2016-03-07 16:34:03','1dec11b1-244b-4561-acc7-96f2d51377e9'),(196,'2016-03-07 16:34:03','2016-03-07 16:34:03','9164de3c-e106-4fba-9afc-147cb973534e'),(197,'2016-03-07 16:34:03','2016-03-07 16:34:03','e5f327e5-0791-463a-b02c-2699599c7277'),(198,'2016-03-07 16:34:03','2016-03-07 16:34:03','ee0034bc-ea89-4db2-8050-31ac58cc9f66'),(199,'2016-03-07 16:34:04','2016-03-07 16:34:04','8a8d6148-7240-4290-acf0-d3c87dd6b99b'),(200,'2016-03-07 16:34:04','2016-03-07 16:34:04','c7081e34-5ebb-4cc6-8d1a-098188a981d5'),(201,'2016-03-07 16:34:04','2016-03-07 16:34:04','112a356c-0a70-47e0-8b6d-2388995a3f95'),(202,'2016-03-07 16:34:04','2016-03-07 16:34:04','0734780a-74e3-47a0-b393-4e9f432c78ed'),(203,'2016-03-07 16:34:04','2016-03-07 16:34:04','870aa233-25aa-4ba0-92f0-3245c48bee7d'),(204,'2016-03-07 16:34:04','2016-03-07 16:34:04','64ad921f-8456-433b-bd7d-e61fc505e38b'),(205,'2016-03-07 16:34:04','2016-03-07 16:34:04','36a01fb9-3a6e-4087-ba1d-ca79dff0ed7a'),(206,'2016-03-07 16:34:04','2016-03-07 16:34:04','10dc6995-5fb2-40b8-9ea0-5d2d493b0e5e'),(207,'2016-03-07 16:34:05','2016-03-07 16:34:05','f022b654-c70c-40a8-a6a7-04906c4b708a'),(208,'2016-03-07 16:34:05','2016-03-07 16:34:05','fa6769c4-744f-4d37-9e4a-d895d49b8005'),(209,'2016-03-07 16:34:05','2016-03-07 16:34:05','934600d2-1cba-4b2f-a099-be414077678b'),(210,'2016-03-07 16:34:05','2016-03-07 16:34:05','4004a6f5-25d6-4083-900c-5327a3af1748'),(211,'2016-03-07 16:34:05','2016-03-07 16:34:05','0b5a0cf1-5d14-4d60-9f35-7db1db850e2b'),(212,'2016-03-07 16:34:05','2016-03-07 16:34:05','d4c8d589-a81c-4a5a-b733-b81846e662d8'),(213,'2016-03-07 16:34:05','2016-03-07 16:34:05','8d25c57f-64a4-4d35-8811-44ca195b7f6c'),(214,'2016-03-07 16:34:05','2016-03-07 16:34:05','38836267-13d7-4553-a427-bf548c21336f'),(215,'2016-03-07 16:34:05','2016-03-07 16:34:05','493a6426-b1fd-495f-9f05-eafb9cc739a5'),(216,'2016-03-07 16:34:06','2016-03-07 16:34:06','9666dd30-1ec9-401c-bc2c-85d62159210e'),(217,'2016-03-07 16:34:06','2016-03-07 16:34:06','ea2da662-108e-44db-8927-a68ba4a0e2fc'),(218,'2016-03-07 16:34:06','2016-03-07 16:34:06','0e2ab094-aff9-435f-8ce8-1c4638c8578b'),(219,'2016-03-07 16:34:06','2016-03-07 16:34:06','ba97c83f-132d-47e0-8fd6-7f35b7c68634'),(220,'2016-03-07 16:34:06','2016-03-07 16:34:06','d290c04c-baa6-432c-81d7-e3514ca98a7d'),(221,'2016-03-07 16:34:06','2016-03-07 16:34:06','34f40af8-7687-4943-bbb7-1d611586cd3a'),(222,'2016-03-07 16:34:06','2016-03-07 16:34:06','deeea38c-c583-4830-a778-e3294cd34600'),(223,'2016-03-07 16:34:06','2016-03-07 16:34:06','679d8cd7-9088-4f38-afa7-57bcebd83105'),(224,'2016-03-07 16:34:06','2016-03-07 16:34:06','c42148d3-642c-4913-8a2b-ed40aa36bb33'),(225,'2016-03-07 16:34:07','2016-03-07 16:34:07','62fed5d0-f0d6-47de-a320-2d34dfc54670'),(226,'2016-03-07 16:34:07','2016-03-07 16:34:07','80c6cfbe-89dc-4749-b74d-b1f5091fafe6'),(227,'2016-03-07 16:34:07','2016-03-07 16:34:07','63ef1488-5bca-4b3e-849b-e85f0492f702'),(228,'2016-03-07 16:34:07','2016-03-07 16:34:07','96357401-8ad7-4134-98d6-ce82f2eae082'),(229,'2016-03-07 16:34:07','2016-03-07 16:34:07','19146650-629d-49bf-8535-fa2e946d823a'),(230,'2016-03-07 16:34:07','2016-03-07 16:34:07','53b2b62c-dd2d-4177-ae8f-02aeb830a09d'),(231,'2016-03-07 16:34:07','2016-03-07 16:34:07','a443e5d0-56dc-426d-b67c-8e15365f19bb'),(232,'2016-03-07 16:34:07','2016-03-07 16:34:07','a669961a-e962-4e43-b86c-3aaa4ba333e3'),(233,'2016-03-07 16:34:07','2016-03-07 16:34:07','b70ccc32-6cb0-45da-a6d8-0a303567bf50'),(234,'2016-03-07 16:34:08','2016-03-07 16:34:08','16b60fcc-ed20-452d-b60f-7114e03eb236'),(235,'2016-03-07 16:34:08','2016-03-07 16:34:08','7fe94932-e834-483b-be06-05ff7ff61d4a'),(236,'2016-03-07 16:34:08','2016-03-07 16:34:08','f254f825-a8b7-40e8-aba8-93484a1f621a'),(237,'2016-03-07 16:34:08','2016-03-07 16:34:08','6aacd89c-ba17-4166-931b-a3cb4d08f531'),(238,'2016-03-07 16:34:08','2016-03-07 16:34:08','a6ad7144-b2c6-4203-96ea-98aacd728923'),(239,'2016-03-07 16:34:08','2016-03-07 16:34:08','519a674c-fe28-44dc-b4ff-9c257911a150'),(240,'2016-03-07 16:34:08','2016-03-07 16:34:08','02407282-5437-4047-9d0b-097dc2ff258b'),(241,'2016-03-07 16:34:08','2016-03-07 16:34:08','f4db523d-626f-4455-a491-484cc5aabcd0'),(242,'2016-03-07 16:34:09','2016-03-07 16:34:09','daa1e8bc-b225-4508-9e17-52829fcbe979'),(243,'2016-03-07 16:34:09','2016-03-07 16:34:09','b262db56-8acd-461b-a8f2-fa4da36c3858'),(244,'2016-03-07 16:34:09','2016-03-07 16:34:09','a569336b-7ce3-4ff5-a713-44fdec644ecf'),(245,'2016-03-07 16:34:09','2016-03-07 16:34:09','8bcc9bc0-6835-41c5-97c2-a3c498ad94bc'),(246,'2016-03-07 16:34:09','2016-03-07 16:34:09','27f2af58-2b94-42ed-b615-21515f1b2bc6'),(247,'2016-03-07 16:34:09','2016-03-07 16:34:09','ed4bd623-4f88-4bb1-921e-6803e40357b4'),(248,'2016-03-07 16:34:09','2016-03-07 16:34:09','15a4b0fd-6027-4a2b-a939-a751c309234c'),(249,'2016-03-07 16:34:09','2016-03-07 16:34:09','49e46c04-2a12-4290-a0e2-1c461cb8c12b'),(250,'2016-03-07 16:34:09','2016-03-07 16:34:09','82d3cfd4-404d-4278-80f4-d82174cc6ecf'),(251,'2016-03-07 16:34:10','2016-03-07 16:34:10','1597e444-f41b-4142-b7c4-df912346b22d'),(252,'2016-03-07 16:34:10','2016-03-07 16:34:10','4133b300-1285-4093-bc97-68292e559f89'),(253,'2016-03-07 16:34:10','2016-03-07 16:34:10','4de47b38-73c3-4fc5-b43f-72c4e68c362c'),(254,'2016-03-07 16:34:10','2016-03-07 16:34:10','0dd71b21-9788-4b1d-99e5-710d41a4a254'),(255,'2016-03-07 16:34:10','2016-03-07 16:34:10','fba7f75b-84e2-4c11-a63c-dce8481af50d'),(256,'2016-03-07 16:34:10','2016-03-07 16:34:10','cd060817-f354-40ae-b45a-48785056e08b'),(257,'2016-03-07 16:34:10','2016-03-07 16:34:10','1a4c788f-d8d1-454c-bba6-64fb01c5a3c4'),(258,'2016-03-07 16:34:10','2016-03-07 16:34:10','31d11453-bcfe-4625-a527-70c9ecb8a2d7'),(259,'2016-03-07 16:34:10','2016-03-07 16:34:10','60914d4a-8f08-4fd5-8857-92121fd77403'),(260,'2016-03-07 16:34:11','2016-03-07 16:34:11','9d6e007c-fc1e-44f7-bedd-d3d3e1829fd0'),(261,'2016-03-07 16:34:11','2016-03-07 16:34:11','a027b793-2d33-488f-83fe-169ca430cf17'),(262,'2016-03-07 16:34:11','2016-03-07 16:34:11','a1c35a8b-b57c-44a1-ab0c-bcaef3566aad'),(263,'2016-03-07 16:34:11','2016-03-07 16:34:11','eea1df5c-1e04-4599-9eb5-49a52755b35d'),(264,'2016-03-07 16:34:11','2016-03-07 16:34:11','0a6ad3db-f21c-44e2-a13f-c7aa463ea891'),(265,'2016-03-07 16:34:11','2016-03-07 16:34:11','e79ad5b8-4f6e-4272-9d02-503e00190e50'),(266,'2016-03-07 16:34:11','2016-03-07 16:34:11','704eeeb3-ce11-4f98-b74b-c3e2a32b7280'),(267,'2016-03-07 16:34:11','2016-03-07 16:34:11','90edd3d4-85a5-4406-83ce-218a8eec60d5'),(268,'2016-03-07 16:34:12','2016-03-07 16:34:12','33a1a15d-701b-4fce-b66f-720ccb78e0a0'),(269,'2016-03-07 16:34:12','2016-03-07 16:34:12','fbde7ba6-be5a-4b76-aac5-6de0939d22b4'),(270,'2016-03-07 16:34:12','2016-03-07 16:34:12','4b405b49-7fe3-4f4e-9e7f-ad456bc8b202'),(271,'2016-03-07 16:34:12','2016-03-07 16:34:12','b4e831c6-94ba-4f7f-8272-aa9200be1915'),(272,'2016-03-07 16:34:12','2016-03-07 16:34:12','17fa2cd5-c044-4363-9f02-a4e5d744ccc5'),(273,'2016-03-07 16:34:12','2016-03-07 16:34:12','52fc315c-a884-4ef4-978d-d319c8edb4dd'),(274,'2016-03-07 16:34:12','2016-03-07 16:34:12','8dde6cfb-da23-419c-ae44-6d7954c3bf5c'),(275,'2016-03-07 16:34:12','2016-03-07 16:34:12','44919f09-e199-4506-b8d2-49cd0ecedb20'),(276,'2016-03-07 16:34:12','2016-03-07 16:34:12','6b08904c-c484-45fd-91f5-14716aac834c'),(277,'2016-03-07 16:34:13','2016-03-07 16:34:13','3bd1e303-d114-4d06-9da6-4f21a5ed32db'),(278,'2016-03-07 16:34:13','2016-03-07 16:34:13','140228ce-19a6-4f29-87bf-e14c795b6c0a'),(279,'2016-03-07 16:34:13','2016-03-07 16:34:13','e088ee54-50ab-455d-8507-7377017137ca'),(280,'2016-03-07 16:34:13','2016-03-07 16:34:13','9a41dd38-c266-477d-90dc-cf30f7aea163'),(281,'2016-03-07 16:34:13','2016-03-07 16:34:13','2162d080-ae5f-4e08-95f9-61f9639466ba'),(282,'2016-03-07 16:34:13','2016-03-07 16:34:13','3ec8f5c1-4b9c-471b-83b6-ed2abf492138'),(283,'2016-03-07 16:34:13','2016-03-07 16:34:13','1bc1d4b8-7856-4303-97f8-b0282416366b'),(284,'2016-03-07 16:34:13','2016-03-07 16:34:13','207c2be4-e64b-4f49-94c1-5134812faf6f'),(285,'2016-03-07 16:34:14','2016-03-07 16:34:14','111e8e7e-2007-4121-b4ea-6574b912a06a'),(286,'2016-03-07 16:34:14','2016-03-07 16:34:14','59643525-6e0a-4da5-8db2-0b3d5b8a64ce'),(287,'2016-03-07 16:34:14','2016-03-07 16:34:14','4ed659f1-26b5-4226-8bca-7a13214a60a8'),(288,'2016-03-07 16:34:14','2016-03-07 16:34:14','70de6292-9173-4c7e-8f01-da0c83956871'),(289,'2016-03-07 16:34:14','2016-03-07 16:34:14','da10caea-a690-48e1-9f19-d5bed12f04e4'),(290,'2016-03-07 16:34:14','2016-03-07 16:34:14','c1da143c-0b7d-4f4d-bd8b-c4e646eb7802'),(291,'2016-03-07 16:34:14','2016-03-07 16:34:14','8ad70af8-16a9-4a6a-8ce5-834baff644a7'),(292,'2016-03-07 16:34:15','2016-03-07 16:34:15','c435339b-d291-48b4-a3b3-511453dbfd59'),(293,'2016-03-07 16:34:15','2016-03-07 16:34:15','c58673e8-be88-4dfa-a952-c302d1630a65'),(294,'2016-03-07 16:34:15','2016-03-07 16:34:15','31f8693e-ecb7-44c2-aef9-2546f78e6d3a'),(295,'2016-03-07 16:34:15','2016-03-07 16:34:15','8ee25597-c1df-4b8b-8f4c-b5bf7134f0e8'),(296,'2016-03-07 16:34:15','2016-03-07 16:34:15','82ac7d16-20b5-46a7-8094-b2d9694fa0e4'),(297,'2016-03-07 16:34:15','2016-03-07 16:34:15','c8570aff-1d9f-43d0-9202-74fde811227d'),(298,'2016-03-07 16:34:15','2016-03-07 16:34:15','40b8dcb0-640c-4d11-98ee-4fda687890dd'),(299,'2016-03-07 16:34:15','2016-03-07 16:34:15','4db02afc-4a22-4551-9d4f-f3a7a6ec8dd9'),(300,'2016-03-07 16:34:16','2016-03-07 16:34:16','5ec84c9d-2ea5-44a7-8d0c-a189eca47522'),(301,'2016-03-07 16:34:16','2016-03-07 16:34:16','3e381b65-f18b-4166-965b-146515feb6a1'),(302,'2016-03-07 16:34:16','2016-03-07 16:34:16','48820f60-aa92-4d57-8529-bc2592469210'),(303,'2016-03-07 16:34:16','2016-03-07 16:34:16','7e26acb4-73a8-44be-b282-3ccc4e58b8c5'),(304,'2016-03-07 16:34:16','2016-03-07 16:34:16','48cfb974-f745-4a1b-bb73-12b07d45f1d7'),(305,'2016-03-07 16:34:16','2016-03-07 16:34:16','9e239a73-7377-4700-940f-07616f386327'),(306,'2016-03-07 16:34:16','2016-03-07 16:34:16','70ad81a6-5779-4ccd-9624-486cb7af114c'),(307,'2016-03-07 16:34:16','2016-03-07 16:34:16','5e37158f-c971-400a-a4ad-c21946f84103'),(308,'2016-03-07 16:34:17','2016-03-07 16:34:17','1a44b496-35c2-4400-a876-ef1b58eb7a76'),(309,'2016-03-07 16:34:17','2016-03-07 16:34:17','6e8a41a9-6ae1-4980-aefc-dcb094757ecb'),(310,'2016-03-07 16:34:17','2016-03-07 16:34:17','5ae1813a-277f-46f4-8cda-5d3ebf2cd7eb'),(311,'2016-03-07 16:34:17','2016-03-07 16:34:17','4504984b-e3ca-4cdd-8b4a-623f5401e0de'),(312,'2016-03-07 16:34:17','2016-03-07 16:34:17','38f3e204-8987-4453-b176-320e33240064'),(313,'2016-03-07 16:34:17','2016-03-07 16:34:17','e52536e4-2fff-4452-a52e-1b731daee542'),(314,'2016-03-07 16:34:17','2016-03-07 16:34:17','96af1e35-4e55-487f-9aed-5c0034a062af'),(315,'2016-03-07 16:34:17','2016-03-07 16:34:17','0cbe7522-87a9-422a-9926-5e4cffc53357'),(316,'2016-03-07 16:34:18','2016-03-07 16:34:18','d1b4d64d-1b4a-4fba-a491-46f6b8b691d2'),(317,'2016-03-07 16:34:18','2016-03-07 16:34:18','83fd6440-ed56-4b09-aacc-d1faf439e79e'),(318,'2016-03-07 16:34:18','2016-03-07 16:34:18','3d9a389c-69d0-409c-a672-4c41ac9a3676'),(319,'2016-03-07 16:34:18','2016-03-07 16:34:18','c5a2385f-208f-4a75-aa96-fe780fdd58df'),(320,'2016-03-07 16:34:18','2016-03-07 16:34:18','f3586df5-bd1d-4254-8f48-99b7ce1152ad'),(321,'2016-03-07 16:34:18','2016-03-07 16:34:18','0264c178-0d58-443b-8ed3-52808a0662c5'),(322,'2016-03-07 16:34:19','2016-03-07 16:34:19','1f8a5238-e7a2-4a62-baf1-4e9153c79b4b'),(323,'2016-03-07 16:34:19','2016-03-07 16:34:19','4facd86a-6e04-470c-87cc-023a54d0ddfd'),(324,'2016-03-07 16:34:19','2016-03-07 16:34:19','c5c4b4c9-a3a6-4bcb-8f9b-d7d059c20a00'),(325,'2016-03-07 16:34:19','2016-03-07 16:34:19','99ada306-b305-47c6-8a1d-669bf0536128'),(326,'2016-03-07 16:34:19','2016-03-07 16:34:19','49a5561f-a251-4866-8586-4785a2c91340'),(327,'2016-03-07 16:34:19','2016-03-07 16:34:19','27fda38c-9d1d-450a-8a80-81bd9f4a11c3'),(328,'2016-03-07 16:34:19','2016-03-07 16:34:19','a4ec9ca8-3586-4cf7-9ae2-a919d6b1071a'),(329,'2016-03-07 16:34:20','2016-03-07 16:34:20','8b3d1d2c-a68a-4770-9809-45c3aaa0c5cb'),(330,'2016-03-07 16:34:20','2016-03-07 16:34:20','ac153eb2-9ec1-4db7-94d8-ac24a4a8f1be'),(331,'2016-03-07 16:34:20','2016-03-07 16:34:20','44f73481-d1da-4f64-b58d-46e62d2a9632'),(332,'2016-03-07 16:34:20','2016-03-07 16:34:20','5e46ff4e-2418-426b-95e7-91df527f06b0'),(333,'2016-03-07 16:34:20','2016-03-07 16:34:20','22d06624-c417-4f8f-99dc-12c87b5752a0'),(334,'2016-03-07 16:34:20','2016-03-07 16:34:20','f9a8fdf3-532e-447d-b61c-df85829de21c'),(335,'2016-03-07 16:34:20','2016-03-07 16:34:20','188f59eb-ce30-4d45-a8cf-4e721fb4f6d6'),(336,'2016-03-07 16:34:20','2016-03-07 16:34:20','a2c25cd3-d714-42e6-b8a3-a8bc0954a0d7'),(337,'2016-03-07 16:34:21','2016-03-07 16:34:21','ecad9073-f8c0-4b61-abd5-f98044248dce'),(338,'2016-03-07 16:34:21','2016-03-07 16:34:21','5debd6ea-c93f-4bfb-92de-b6b7196859c2'),(339,'2016-03-07 16:34:21','2016-03-07 16:34:21','abe568ea-1534-4f84-84c9-0f1af91c3cff'),(340,'2016-03-07 16:34:21','2016-03-07 16:34:21','4149fe14-aa91-46e6-ac03-92bec2740d87'),(341,'2016-03-07 16:34:21','2016-03-07 16:34:21','6577fd93-e662-4af4-97fa-7b2dd15a19b1'),(342,'2016-03-07 16:34:21','2016-03-07 16:34:21','b21c3261-d6bb-4b6b-aead-986c543f2074'),(343,'2016-03-07 16:34:21','2016-03-07 16:34:21','47149161-6f9a-4f63-a4cc-0e4263ddad51'),(344,'2016-03-07 16:34:21','2016-03-07 16:34:21','3502cf6e-544e-41ab-b682-acc73369c470'),(345,'2016-03-07 16:34:22','2016-03-07 16:34:22','c4ea5819-b41c-4d4c-861f-c7a9a795cbd7'),(346,'2016-03-07 16:34:22','2016-03-07 16:34:22','f9c20cf9-bfd4-4cf1-a321-783364281b44'),(347,'2016-03-07 16:34:22','2016-03-07 16:34:22','21098ba8-fea5-45c1-aaf2-d81a701bdcc4'),(348,'2016-03-07 16:34:22','2016-03-07 16:34:22','e0121129-c0e0-430a-811c-1c1974a390a7'),(349,'2016-03-07 16:34:22','2016-03-07 16:34:22','dcb93d7d-20a8-4949-a6a2-5269fabbf114'),(350,'2016-03-07 16:34:22','2016-03-07 16:34:22','7acba33d-f844-4dc7-a751-94fc904b60b6'),(351,'2016-03-07 16:34:22','2016-03-07 16:34:22','de9acd6e-5c7b-41c5-bd97-a72d085464f8'),(352,'2016-03-07 16:34:23','2016-03-07 16:34:23','cbadd732-e585-40d1-a28d-449801f2a93c'),(353,'2016-03-07 16:34:23','2016-03-07 16:34:23','99fe4c97-4019-4f9d-a320-428985a2bc32'),(354,'2016-03-07 16:34:24','2016-03-07 16:34:24','e1d8f8cc-3104-4cbf-a07e-48303bdc8850'),(355,'2016-03-07 16:34:25','2016-03-07 16:34:25','65ec967b-e3aa-45ed-8b04-dd5c8ea1759c'),(356,'2016-03-07 16:34:26','2016-03-07 16:34:26','57a1c614-4d40-41f0-b791-12c89f521a81'),(357,'2016-03-07 16:34:26','2016-03-07 16:34:26','92d0d043-ad22-43ef-823d-4caf01f1cc4c'),(358,'2016-03-07 16:34:26','2016-03-07 16:34:26','9cb6751a-5065-4fed-93cf-fe36f5df17f1'),(359,'2016-03-07 16:34:26','2016-03-07 16:34:26','0ebcf35d-b666-4959-bead-88e43da00100'),(360,'2016-03-07 16:34:26','2016-03-07 16:34:26','1ebdf6dd-0f92-4e8b-8b6a-53094b669b46'),(361,'2016-03-07 16:34:26','2016-03-07 16:34:26','5e0204d3-04e6-46d3-b706-4c099506274c'),(362,'2016-03-07 16:34:26','2016-03-07 16:34:26','4ec45673-51b6-4387-bb26-e92c413dbe0b'),(363,'2016-03-07 16:34:26','2016-03-07 16:34:26','9393d9d3-9c51-42aa-b1fe-938130358909'),(364,'2016-03-07 16:34:27','2016-03-07 16:34:27','192587c4-85c9-4767-9cc6-682f969ea9f0'),(365,'2016-03-07 16:34:27','2016-03-07 16:34:27','4c142e69-61da-4efc-903a-6532bb23ac31'),(366,'2016-03-07 16:34:27','2016-03-07 16:34:27','e17c3f4d-3b1a-440c-b8ee-2f5a083ac585'),(367,'2016-03-07 16:34:27','2016-03-07 16:34:27','0478b397-d083-412f-b565-cfd1b12e893a'),(368,'2016-03-07 16:34:27','2016-03-07 16:34:27','6ffdfb03-cc85-4945-a9d5-919b6185b532'),(369,'2016-03-07 16:34:27','2016-03-07 16:34:27','7e9352c1-a266-457d-ac00-8786adec84e7'),(370,'2016-03-07 16:34:27','2016-03-07 16:34:27','75a683e3-511c-4764-b7b7-0ad62a4adbf5'),(371,'2016-03-07 17:20:46','2016-03-07 17:20:46','4a42a284-2b4f-4913-9b9a-b3e9cfac3fe8'),(372,'2016-03-07 17:20:46','2016-03-07 17:20:46','8f99d281-b434-40a6-862e-0adf2af4050d'),(373,'2016-03-07 17:20:47','2016-03-07 17:20:47','9ed9ec34-cc64-4b1d-8767-44f37e76eb70'),(374,'2016-03-07 17:20:47','2016-03-07 17:20:47','2a535446-1573-43f5-8d6e-692168a061b1'),(375,'2016-03-07 17:20:47','2016-03-07 17:20:47','ca3e5a5d-3ed1-46e9-9c15-f6ad1e574214'),(376,'2016-03-07 17:20:47','2016-03-07 17:20:47','0ad57c45-d5a5-4226-a13d-f2a4db022f31'),(377,'2016-03-07 17:20:47','2016-03-07 17:20:47','917fd772-35fa-481d-bc47-216a6cf49931'),(378,'2016-03-07 17:20:47','2016-03-07 17:20:47','d5e8b056-da70-4777-ab19-923cb931b7bc'),(379,'2016-03-07 17:20:47','2016-03-07 17:20:47','cc0172e9-e8b7-4e4f-9da5-e7dc29a03ca0'),(380,'2016-03-07 17:20:47','2016-03-07 17:20:47','df554df9-085b-4da9-ae27-9902141481e3'),(381,'2016-03-07 17:20:47','2016-03-07 17:20:47','60434c88-3d81-49e6-9780-3890f69df81c'),(382,'2016-03-07 17:20:48','2016-03-07 17:20:48','cce57560-e16f-4850-b0bd-889ec766aa4d'),(383,'2016-03-07 17:20:48','2016-03-07 17:20:48','e2c6fa22-3bd3-486a-8c44-735e9efc4d2c'),(384,'2016-03-07 17:20:48','2016-03-07 17:20:48','d0b8f9fc-a421-4942-ba12-706e2788bb71'),(385,'2016-03-07 17:20:48','2016-03-07 17:20:48','de0bfbd1-51fd-4f6a-ad2d-df1cd8030b79'),(386,'2016-03-07 17:20:48','2016-03-07 17:20:48','c6189791-1bd6-4c9f-912a-3ca9b0625727'),(387,'2016-03-07 17:20:48','2016-03-07 17:20:48','23db8d30-c22c-464c-9b8f-50a69804e65f'),(388,'2016-03-07 17:20:48','2016-03-07 17:20:48','d74baa14-b877-4aff-b483-92aae34b260a'),(389,'2016-03-07 17:20:48','2016-03-07 17:20:48','593e013c-0e21-4463-8603-1f67428ece58'),(390,'2016-03-07 17:20:48','2016-03-07 17:20:48','06322f81-0a51-4eba-950c-90e891ece947'),(391,'2016-03-07 17:21:19','2016-03-07 17:21:19','afa84387-3fa3-4f19-9fea-5e2b8412f89c'),(392,'2016-03-07 17:21:19','2016-03-07 17:21:19','c673f974-b642-4247-b43f-df039a70bd01'),(393,'2016-03-07 17:21:19','2016-03-07 17:21:19','7127003a-c9d6-46e5-a0d9-a6334760b192'),(394,'2016-03-07 17:21:19','2016-03-07 17:21:19','5b5c3b73-24d8-45aa-9e56-2ba2e4287b45'),(395,'2016-03-07 17:21:20','2016-03-07 17:21:20','427eb354-64bf-43a0-8bcd-55ab4613e381'),(396,'2016-03-07 17:21:20','2016-03-07 17:21:20','fe8aabc6-6706-483a-be89-788d9d12e521'),(397,'2016-03-07 17:21:20','2016-03-07 17:21:20','5f850cf1-e89e-48ea-a524-3087e522443a'),(398,'2016-03-07 17:21:20','2016-03-07 17:21:20','22db34f3-a688-4904-9f15-918333aa0142'),(399,'2016-03-07 17:21:20','2016-03-07 17:21:20','6691cc63-c472-45d0-a703-41429c793a1b'),(400,'2016-03-07 17:21:20','2016-03-07 17:21:20','3bad8b2d-c7e9-46ff-ac2c-83825f881efc'),(401,'2016-03-07 17:21:20','2016-03-07 17:21:20','7e8e69af-140e-4b3e-aa29-7de31702c468'),(402,'2016-03-07 17:21:20','2016-03-07 17:21:20','0bd1dd54-bafc-4ee8-9b73-a7b72d3f4f09'),(403,'2016-03-07 17:21:20','2016-03-07 17:21:20','ff5cf2e1-1bd3-498f-8356-70f18a02a917'),(404,'2016-03-07 17:21:20','2016-03-07 17:21:20','0bd750da-6efb-4eba-b412-e032c5068d5d'),(405,'2016-03-07 17:21:21','2016-03-07 17:21:21','443fea79-e505-4956-a9bc-44c0b8d4046e'),(406,'2016-03-07 17:21:21','2016-03-07 17:21:21','4ad3de13-6937-4708-883c-e486d61905a1'),(407,'2016-03-07 17:21:21','2016-03-07 17:21:21','776205fb-987b-4f89-9e1d-b78070945f4f'),(408,'2016-03-07 17:21:21','2016-03-07 17:21:21','da05dfd4-a934-4f93-9c81-83d9516d1c5e'),(409,'2016-03-07 17:21:21','2016-03-07 17:21:21','8d068eca-6050-4d52-9bbd-6f8e7555c0d7'),(410,'2016-03-07 17:21:21','2016-03-07 17:21:21','bf332d13-0280-43ed-808d-9132aececaf9'),(411,'2016-03-07 17:21:21','2016-03-07 17:21:21','05cd5bbe-bd55-42f6-821c-170b77a7116c'),(412,'2016-03-07 17:21:21','2016-03-07 17:21:21','c1e4e919-94ab-4203-bf4a-9e757462f4e0'),(413,'2016-03-07 17:21:21','2016-03-07 17:21:21','2ebf8c58-c6c6-43b7-9cab-2a1ea68a5dfb'),(414,'2016-03-07 17:21:22','2016-03-07 17:21:22','9a49326a-903f-4940-bb33-c7ecf9ac6c95'),(415,'2016-03-07 17:21:22','2016-03-07 17:21:22','0d73a129-066f-4dfc-8bea-efc12967d533'),(416,'2016-03-07 17:21:22','2016-03-07 17:21:22','ec45e807-996a-4d83-8116-af26a173dbaa'),(417,'2016-03-07 17:21:22','2016-03-07 17:21:22','644062e5-7d97-4960-b194-69cb738d4921'),(418,'2016-03-07 17:21:22','2016-03-07 17:21:22','b6212c2a-a973-4328-8d31-68e0dd892c51'),(419,'2016-03-07 17:21:22','2016-03-07 17:21:22','b33cbcce-5442-4880-b557-37e7e86445ba'),(420,'2016-03-07 17:21:22','2016-03-07 17:21:22','ed5d7e36-2752-430c-8fec-b71b13c03b8f'),(421,'2016-03-07 17:21:22','2016-03-07 17:21:22','ae3deff4-884b-4920-b0a1-6ed9671e128f'),(422,'2016-03-07 17:21:22','2016-03-07 17:21:22','e4346522-2205-4d0f-862a-04ff36569a3f'),(423,'2016-03-07 17:21:23','2016-03-07 17:21:23','cc8c4076-9cfe-4cfe-8bd0-43e6c026b435'),(424,'2016-03-07 17:21:23','2016-03-07 17:21:23','28dd6188-746c-464d-bb3f-272e8918d449'),(425,'2016-03-07 17:21:23','2016-03-07 17:21:23','88a53873-df63-4c7d-a64d-e7ff65ce8914'),(426,'2016-03-07 17:21:23','2016-03-07 17:21:23','18f84dbc-09e3-4af1-b152-6629264946a3'),(427,'2016-03-07 17:21:23','2016-03-07 17:21:23','d2e64dd5-bfa5-47ea-bbfe-0d74399bc570'),(428,'2016-03-07 17:21:23','2016-03-07 17:21:23','ca4f5393-10b1-4893-abac-a20c984e776d'),(429,'2016-03-07 17:21:23','2016-03-07 17:21:23','e22a1af3-2d80-4c2d-8247-912bba424283'),(430,'2016-03-07 17:21:23','2016-03-07 17:21:23','5161143c-4a0a-40ea-92f3-a0eb4cd96bc6'),(431,'2016-03-07 17:21:23','2016-03-07 17:21:23','a01ed039-4683-4771-9269-cea3f0b6d6b3'),(432,'2016-03-07 17:21:24','2016-03-07 17:21:24','161bcdf1-6869-4f8d-ae09-6074568d395a'),(433,'2016-03-07 17:21:24','2016-03-07 17:21:24','aa61d807-a10e-4ddf-a3a6-5e251bcd028c'),(434,'2016-03-07 17:21:24','2016-03-07 17:21:24','eb731305-f48e-4190-a402-43e63e58b8f3'),(435,'2016-03-07 17:21:24','2016-03-07 17:21:24','2a923cc3-2f69-40d7-9041-293924cb9d48'),(436,'2016-03-07 17:21:24','2016-03-07 17:21:24','363979cd-5db8-4c38-853f-221aa732ed6d'),(437,'2016-03-07 17:21:24','2016-03-07 17:21:24','4eb43963-bc46-4bc8-9df6-5c6b39aac3a5'),(438,'2016-03-07 17:21:24','2016-03-07 17:21:24','455ba18b-eb8f-4d3d-8922-fec582372a19'),(439,'2016-03-07 17:21:24','2016-03-07 17:21:24','bb053bd8-1f74-4a82-a4c3-9e962f068abb'),(440,'2016-03-07 17:21:24','2016-03-07 17:21:24','48430a22-e949-48ea-91f9-c59769a401e3'),(441,'2016-03-07 17:21:25','2016-03-07 17:21:25','c47d9f81-1632-4dfe-bccf-4dc084cff063'),(442,'2016-03-07 17:21:25','2016-03-07 17:21:25','c9e75f41-66ce-40de-a507-327fbf93a39a'),(443,'2016-03-07 17:21:25','2016-03-07 17:21:25','918164b0-ace9-4169-a865-5cbfb49326d5'),(444,'2016-03-07 17:21:25','2016-03-07 17:21:25','549d637b-26fc-4c8d-9179-6529d1d6dfe8'),(445,'2016-03-07 17:21:25','2016-03-07 17:21:25','9cac8b5a-8884-465f-a0da-e7b9e8959af6'),(446,'2016-03-07 17:21:25','2016-03-07 17:21:25','80ae78f1-203d-4ba2-b13a-52cbe67dfbe8'),(447,'2016-03-07 17:21:25','2016-03-07 17:21:25','06a7dff7-567a-4e3e-9888-dfd75dff80dc'),(448,'2016-03-07 17:21:25','2016-03-07 17:21:25','d1a25d80-0d42-4ba4-b716-41072861472d'),(449,'2016-03-07 17:21:25','2016-03-07 17:21:25','15d498b0-05b3-43e5-8f0a-ca946b0538f8'),(450,'2016-03-07 17:21:26','2016-03-07 17:21:26','c03e9736-83ba-4cac-b3cd-917f07db8ea6'),(451,'2016-03-07 17:21:26','2016-03-07 17:21:26','9e1d1460-83a9-4a72-9401-ec2d0a142603'),(452,'2016-03-07 17:21:26','2016-03-07 17:21:26','9344804f-1b70-413d-adeb-a8851c8b03ff'),(453,'2016-03-07 17:21:26','2016-03-07 17:21:26','fc8ae088-16ea-4ef7-8a60-f7a26760865e'),(454,'2016-03-07 17:21:26','2016-03-07 17:21:26','1666eeaf-ab83-414a-beea-8889294e0694'),(455,'2016-03-07 17:21:26','2016-03-07 17:21:26','02c8e420-c66f-44c4-96c3-e616d7cf2785'),(456,'2016-03-07 17:21:26','2016-03-07 17:21:26','2b62506a-f030-4245-94ea-2126813a5cf6'),(457,'2016-03-07 17:21:26','2016-03-07 17:21:26','3368d141-e043-49cc-bf54-79508f16aec1'),(458,'2016-03-07 17:21:27','2016-03-07 17:21:27','c502fcbf-e2d1-4df0-9216-2443cecb2f5b'),(459,'2016-03-07 17:21:27','2016-03-07 17:21:27','0d5d3474-1806-4115-bba7-848ffcc5cd66'),(460,'2016-03-07 17:21:27','2016-03-07 17:21:27','1901f604-a18a-43eb-a1db-babb1ecf0e75'),(461,'2016-03-07 17:21:27','2016-03-07 17:21:27','4d716704-cecb-4297-8a49-fd769af8082e'),(462,'2016-03-07 17:21:27','2016-03-07 17:21:27','8ef3181b-b7ee-44f7-bae3-b9c95bd25f18'),(463,'2016-03-07 17:21:27','2016-03-07 17:21:27','ac417a92-260f-40aa-b999-f65d1625a121'),(464,'2016-03-07 17:21:27','2016-03-07 17:21:27','33261a08-7576-4c79-9cdf-085d537b0844'),(465,'2016-03-07 17:21:27','2016-03-07 17:21:27','6f3ba536-d5e4-4535-ac48-ebd30e694290'),(466,'2016-03-07 17:21:28','2016-03-07 17:21:28','aea0e19c-7065-49a5-98d9-bc6f93a838d3'),(467,'2016-03-07 17:21:28','2016-03-07 17:21:28','f4a1b17d-3611-41e5-b331-6fac779c1b1c'),(468,'2016-03-07 17:21:28','2016-03-07 17:21:28','7750d5a9-1cf5-4fae-aa3e-bbddddb89b7b'),(469,'2016-03-07 17:21:28','2016-03-07 17:21:28','28db3e30-06b5-4263-ad78-668f52ff75f0'),(470,'2016-03-07 17:21:28','2016-03-07 17:21:28','f584ce4b-92c3-4d0b-a4af-60378f83a2ab'),(471,'2016-03-07 17:21:28','2016-03-07 17:21:28','6dada78e-88e3-406c-b2b8-0650706eb1f3'),(472,'2016-03-07 17:21:28','2016-03-07 17:21:28','1a90070b-9b44-4457-9150-b1b56e414250'),(473,'2016-03-07 17:21:28','2016-03-07 17:21:28','74f172b4-8113-41e5-a708-be0b150ec50c'),(474,'2016-03-07 17:21:29','2016-03-07 17:21:29','bdb02cc1-4ea3-46fd-8c65-6692a262d31c'),(475,'2016-03-07 17:21:29','2016-03-07 17:21:29','e30575eb-4884-4ed6-9d72-def3a1224dc9'),(476,'2016-03-07 17:21:29','2016-03-07 17:21:29','6e22daf5-a2cb-4029-af05-a29d6d75d7c5'),(477,'2016-03-07 17:21:29','2016-03-07 17:21:29','32c20271-6058-4bba-a3cb-1ea1fdb9d28f'),(478,'2016-03-07 17:21:29','2016-03-07 17:21:29','13d85450-b533-4c28-b83a-e24d785f0995'),(479,'2016-03-07 17:21:29','2016-03-07 17:21:29','d965af00-add5-45de-b888-2ef40ba003d0'),(480,'2016-03-07 17:21:29','2016-03-07 17:21:29','41ff72e3-187d-4441-b0e5-84f29755867c'),(481,'2016-03-07 17:21:29','2016-03-07 17:21:29','8e9fb746-dac0-47ae-a78e-780e4b05c086'),(482,'2016-03-07 17:21:29','2016-03-07 17:21:29','7d7a891f-6775-457e-979b-1048a50c88c3'),(483,'2016-03-07 17:21:30','2016-03-07 17:21:30','52500488-8a83-4ab3-8da4-0ca20192e7cc'),(484,'2016-03-07 17:21:30','2016-03-07 17:21:30','3472c17a-2c4d-486d-9804-6a6639fa8d60'),(485,'2016-03-07 17:21:30','2016-03-07 17:21:30','068a4bbc-1981-4518-90a2-864ac930865c'),(486,'2016-03-07 17:21:30','2016-03-07 17:21:30','ffea8473-23e1-4792-89c8-cddc8c664c2f'),(487,'2016-03-07 17:21:30','2016-03-07 17:21:30','6fa0ae27-3b59-49c5-80fa-51afe3b62b64'),(488,'2016-03-07 17:21:30','2016-03-07 17:21:30','34531071-460b-475c-807e-3f08533bf8cf'),(489,'2016-03-07 17:21:30','2016-03-07 17:21:30','173009d8-5c0e-4a22-b93f-cf33529b36be'),(490,'2016-03-07 17:21:30','2016-03-07 17:21:30','c94de319-7cff-4569-90c8-7ec9be710b67'),(491,'2016-03-07 17:21:30','2016-03-07 17:21:30','eb7e370c-ca65-4b91-a5fc-776e5ef9d907'),(492,'2016-03-07 17:21:30','2016-03-07 17:21:30','25294663-af58-4da9-98e9-4249451aa519'),(493,'2016-03-07 17:21:31','2016-03-07 17:21:31','ce0b3335-1f48-4152-9b5e-84d9cf95f1d8'),(494,'2016-03-07 17:21:31','2016-03-07 17:21:31','dec267a8-6a78-4514-aa87-8bfed1d808ea'),(495,'2016-03-07 17:21:31','2016-03-07 17:21:31','08b610bf-15b4-40a8-8113-aeb63817cdff'),(496,'2016-03-07 17:21:31','2016-03-07 17:21:31','c8cfd4fe-c6ad-442a-b13e-e1496f8aa967'),(497,'2016-03-07 17:21:31','2016-03-07 17:21:31','c73428af-add4-4cdb-a055-2c57fafb481e'),(498,'2016-03-07 17:21:31','2016-03-07 17:21:31','b860dfe3-571e-4f45-ad6a-f2d01f618134'),(499,'2016-03-07 17:21:31','2016-03-07 17:21:31','0ba1e802-8094-41e2-865d-5c4246a5cb76'),(500,'2016-03-07 17:21:31','2016-03-07 17:21:31','3948eb73-394b-4b7b-94df-3fc3fdd2f620'),(506,'2016-03-17 12:44:31','2016-03-17 12:44:31','1d4b0f6d-cc0a-4fe5-a29c-314ace79780d');
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
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_rgwbveabdker6prhy3sxv4sq5` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `multilingual_text`
--

LOCK TABLES `multilingual_text` WRITE;
/*!40000 ALTER TABLE `multilingual_text` DISABLE KEYS */;
INSERT INTO `multilingual_text` (`Id`, `created`, `updated`, `uuid`) VALUES (1,'2016-03-07 14:50:58','2016-03-07 14:50:58','37161d36-9c77-45b8-b576-861f3017fb66'),(2,'2016-03-07 14:50:59','2016-03-07 14:50:59','a533a133-3e0b-45e9-99ea-6c4c804a12ef'),(3,'2016-03-07 14:50:59','2016-03-07 14:50:59','f36731da-3bda-4fff-8269-55bdb4527966'),(4,'2016-03-07 14:50:59','2016-03-07 14:50:59','8be829bf-d792-40a2-8c5a-ec777c6406cf'),(5,'2016-03-07 14:50:59','2016-03-07 14:50:59','414efa71-3942-4042-9613-0330e21e4429'),(6,'2016-03-07 14:51:00','2016-03-07 14:51:00','3229ef25-e04c-4239-a2f5-42941f1510ea'),(7,'2016-03-07 14:51:00','2016-03-07 14:51:00','cfaf3d8e-d4e8-4087-8025-f1a8606c1064'),(8,'2016-03-07 14:51:00','2016-03-07 14:51:00','ed7113f4-a57e-4290-9dcc-23ab721ad75f'),(9,'2016-03-07 14:51:00','2016-03-07 14:51:00','f27da2ba-b7a5-481c-8a5e-05a0ec8b73d0'),(10,'2016-03-07 14:51:00','2016-03-07 14:51:00','b7d4e22c-289d-4d17-ba6b-591ac65af13d'),(11,'2016-03-07 14:51:00','2016-03-07 14:51:00','b8bacec4-d860-45a7-872a-7eb9b3fd4e7f'),(12,'2016-03-07 14:51:00','2016-03-07 14:51:00','ba5a3fad-e93d-458b-a9fb-e99b343949c7'),(13,'2016-03-07 14:51:01','2016-03-07 14:51:01','0aa15ab9-d7c2-4a02-aac3-3a689e7cdf3c'),(14,'2016-03-07 14:51:01','2016-03-07 14:51:01','bcdc168b-6fce-4f4b-b35a-6528b96eba46'),(15,'2016-03-07 14:51:01','2016-03-07 14:51:01','be13b0ba-4ea3-44f8-947c-3a4879de7d88'),(16,'2016-03-07 14:51:01','2016-03-07 14:51:01','59986dd7-3311-4537-8178-62a317dca069'),(17,'2016-03-07 14:51:01','2016-03-07 14:51:01','9aad8b64-5a5b-4778-a66d-23537ddfe07d'),(18,'2016-03-07 14:51:15','2016-03-07 14:51:15','f71b2b9d-8e2e-49b6-bdaa-00f97aafc0a8'),(19,'2016-03-07 14:51:16','2016-03-07 14:51:16','e5acfc04-a63b-447b-a3e8-234902d5b4a6'),(20,'2016-03-07 14:51:16','2016-03-07 14:51:16','e1e6b790-1c41-44d8-979a-e5890b830e58'),(21,'2016-03-07 14:51:16','2016-03-07 14:51:16','db1f6d12-852a-49eb-aad7-923f3524ec63'),(22,'2016-03-07 14:51:16','2016-03-07 14:51:16','5b76799a-3a48-4c84-9d94-0f5610fc037c'),(23,'2016-03-07 14:51:16','2016-03-07 14:51:16','9ac050bd-fdac-41f0-8728-e15b43159e22'),(24,'2016-03-07 14:51:17','2016-03-07 14:51:17','b92dd20f-53e7-4e56-8537-a392ec01273d'),(25,'2016-03-07 14:51:17','2016-03-07 14:51:17','2852ac06-15ff-4d48-b6ae-374e9f0d9421'),(26,'2016-03-07 14:51:23','2016-03-07 14:51:23','b428bfda-1905-46fb-87a8-3fe5481b88e0'),(27,'2016-03-07 14:51:23','2016-03-07 14:51:23','0cd6917e-7cf0-43ef-bb76-881902d67769'),(28,'2016-03-07 14:51:24','2016-03-07 14:51:24','ccc20d86-a334-494b-a6be-118e499e4cfe'),(29,'2016-03-07 14:51:24','2016-03-07 14:51:24','92b7d671-e542-4d04-8d52-3151220ec7f1'),(30,'2016-03-07 14:51:24','2016-03-07 14:51:24','7c2bb216-db13-4a48-abea-e2cac31fdec1'),(31,'2016-03-07 14:51:24','2016-03-07 14:51:24','c25c890d-4095-4add-bee1-960651c42963'),(32,'2016-03-07 14:51:24','2016-03-07 14:51:24','81fc8b19-71dc-4dad-b580-2fdcc09f28ec'),(33,'2016-03-07 14:51:24','2016-03-07 14:51:24','9275d44a-d6fa-48a8-862f-5310fc23625d'),(34,'2016-03-07 14:51:25','2016-03-07 14:51:25','c3c63847-c82a-4d2c-9fd7-6394d88ae714'),(35,'2016-03-07 14:51:25','2016-03-07 14:51:25','87965a3e-be32-4ecc-a013-fa9e9d8b6e2c'),(36,'2016-03-07 14:51:25','2016-03-07 14:51:25','62481968-a434-4f67-b7a0-f5768b3d3340'),(37,'2016-03-07 14:51:25','2016-03-07 14:51:25','30497b44-8de9-45cd-918d-bdc2906108a0'),(38,'2016-03-07 14:51:25','2016-03-07 14:51:25','d8075cfa-9595-46e2-835d-2d9d0add5448'),(39,'2016-03-07 14:51:25','2016-03-07 14:51:25','ba5410dd-befa-4e55-86d4-a1da7ce8ee61'),(40,'2016-03-07 14:51:26','2016-03-07 14:51:26','00906042-e043-45c5-b7dc-3597375fb12e'),(41,'2016-03-07 14:51:26','2016-03-07 14:51:26','bb2d8d56-a79c-4dd8-8178-d8f6bc0d6f05');
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
  `product_id` bigint(20) DEFAULT NULL,
  `shipmode_id` bigint(20) DEFAULT NULL,
  `suborders_id` bigint(20) DEFAULT NULL,
  `taxcategory_id` bigint(20) DEFAULT NULL,
  `order_id` bigint(20) NOT NULL,
  PRIMARY KEY (`orderitem_id`),
  UNIQUE KEY `UK_dgxcpjijf357k9no0wy54rl3j` (`uuid`),
  KEY `FK_kpmo71ore4cb0xdvtqbd5c99q` (`product_id`),
  KEY `FK_evapc06i8lv4ddtl5h3lk1gqb` (`shipmode_id`),
  KEY `FK_sacxp6jb831or49uv042f9nt` (`suborders_id`),
  KEY `FK_g0hjksbbxek7ew2a5uuva4iew` (`taxcategory_id`),
  KEY `orderitems_fk` (`order_id`),
  CONSTRAINT `FK_evapc06i8lv4ddtl5h3lk1gqb` FOREIGN KEY (`shipmode_id`) REFERENCES `shipmode` (`shipmode_id`),
  CONSTRAINT `FK_g0hjksbbxek7ew2a5uuva4iew` FOREIGN KEY (`taxcategory_id`) REFERENCES `taxes` (`taxes_id`),
  CONSTRAINT `FK_kpmo71ore4cb0xdvtqbd5c99q` FOREIGN KEY (`product_id`) REFERENCES `abstractProducts` (`ctentry_id`),
  CONSTRAINT `FK_sacxp6jb831or49uv042f9nt` FOREIGN KEY (`suborders_id`) REFERENCES `suborders` (`suborders_id`),
  CONSTRAINT `cartitems_fk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  CONSTRAINT `orderitems_fk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderitems`
--

LOCK TABLES `orderitems` WRITE;
/*!40000 ALTER TABLE `orderitems` DISABLE KEYS */;
INSERT INTO `orderitems` (`pending`, `orderitem_id`, `created`, `updated`, `uuid`, `discount_amount`, `discount_perc`, `discount_perc1`, `discount_perc2`, `quantity`, `shipping_address_id`, `shipping_cost`, `sku`, `sku_cost`, `sku_description`, `sku_net_price`, `sku_price`, `product_id`, `shipmode_id`, `suborders_id`, `taxcategory_id`, `order_id`) VALUES (1,1,'2016-03-14 12:09:59','2016-03-14 12:09:59','bbba6b43-2cf5-4e3e-b33d-fab00d3f98c1',0,0,0,0,1,NULL,0,'Z001',0,NULL,29.95,29.95,26,NULL,NULL,NULL,12),(1,2,'2016-03-18 10:59:34','2016-03-18 10:59:34','bab45079-5d37-4361-aa9d-60d35028c05f',0,0,0,0,1,NULL,0,'Z002',0,NULL,29.95,29.95,27,NULL,NULL,NULL,14),(1,4,'2016-03-18 11:04:34','2016-03-18 11:04:34','ee5ff011-6058-4920-b9dc-762443b197cc',0,0,0,0,2,NULL,0,'Z001',0,NULL,29.95,29.95,26,NULL,NULL,NULL,15),(1,5,'2016-03-18 11:06:42','2016-03-18 11:06:42','a7bef609-7afb-4dca-9e71-21ea55065abb',0,0,0,0,1,NULL,0,'Z001',0,NULL,29.95,29.95,26,NULL,NULL,NULL,16);
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
  UNIQUE KEY `UK_iujnxluydvdqprswe31ueye7v` (`uuid`),
  KEY `FK_nbse78sgl0ysl2sw459hc24hm` (`orderitems_id`),
  CONSTRAINT `FK_nbse78sgl0ysl2sw459hc24hm` FOREIGN KEY (`orderitems_id`) REFERENCES `orderitems` (`orderitem_id`)
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
  UNIQUE KEY `UK_j4bo0gxgxp25h23xwtbv7od6o` (`uuid`),
  KEY `FK_15kssaaw7byti8axkhnnfhsx1` (`orders_id`),
  KEY `FK_g88yi5x2ba69rx3kaspt780kq` (`paymethod_id`),
  CONSTRAINT `FK_15kssaaw7byti8axkhnnfhsx1` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`order_id`),
  CONSTRAINT `FK_g88yi5x2ba69rx3kaspt780kq` FOREIGN KEY (`paymethod_id`) REFERENCES `paymethod` (`paymethod_id`)
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
  `status` varchar(255) DEFAULT NULL,
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
  `paymethod_id` bigint(20) DEFAULT NULL,
  `shipmode_id` bigint(20) DEFAULT NULL,
  `shipping_address_id` bigint(20) DEFAULT NULL,
  `store_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`order_id`),
  UNIQUE KEY `UK_8trmqe3eqy2ut1xr2i2atcaip` (`uuid`),
  UNIQUE KEY `UK_t5ee3vjmonruwsp9g423dhrek` (`orderNumber`),
  UNIQUE KEY `UK_i4xhef5x6drd02us28r33k430` (`billing_address_id`),
  UNIQUE KEY `UK_sdv8vvdhj9gxm0dfoeh2rqvkh` (`shipping_address_id`),
  KEY `FK_kxo1195dgqoedraraxtlef65s` (`currency_id`),
  KEY `FK_astys1dv61mdlp0n0wx0574r2` (`customer_id`),
  KEY `FK_la7i2mjchfxj6q4c6qc167gaq` (`paymethod_id`),
  KEY `FK_linpoeo095ocudghs1pjlypeu` (`shipmode_id`),
  KEY `FK_htsl1tgatejg560p0m3n3uhik` (`store_id`),
  KEY `FK_k8kupdtcdpqd57b6j4yq9uvdj` (`user_id`),
  CONSTRAINT `FK_astys1dv61mdlp0n0wx0574r2` FOREIGN KEY (`customer_id`) REFERENCES `members` (`member_id`),
  CONSTRAINT `FK_htsl1tgatejg560p0m3n3uhik` FOREIGN KEY (`store_id`) REFERENCES `stores` (`store_id`),
  CONSTRAINT `FK_i4xhef5x6drd02us28r33k430` FOREIGN KEY (`billing_address_id`) REFERENCES `Address` (`address_id`),
  CONSTRAINT `FK_k8kupdtcdpqd57b6j4yq9uvdj` FOREIGN KEY (`user_id`) REFERENCES `users` (`member_id`),
  CONSTRAINT `FK_kxo1195dgqoedraraxtlef65s` FOREIGN KEY (`currency_id`) REFERENCES `currency` (`currency_id`),
  CONSTRAINT `FK_la7i2mjchfxj6q4c6qc167gaq` FOREIGN KEY (`paymethod_id`) REFERENCES `paymethod` (`paymethod_id`),
  CONSTRAINT `FK_linpoeo095ocudghs1pjlypeu` FOREIGN KEY (`shipmode_id`) REFERENCES `shipmode` (`shipmode_id`),
  CONSTRAINT `FK_sdv8vvdhj9gxm0dfoeh2rqvkh` FOREIGN KEY (`shipping_address_id`) REFERENCES `Address` (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` (`pending`, `order_id`, `created`, `updated`, `uuid`, `cookie`, `discount_amount`, `discount_perc`, `order_amount`, `status`, `total_cost`, `total_price`, `total_product`, `total_service`, `total_shipping`, `total_tax`, `orderNumber`, `pay_amount`, `billing_address_id`, `currency_id`, `customer_id`, `paymethod_id`, `shipmode_id`, `shipping_address_id`, `store_id`, `user_id`) VALUES (1,1,'2016-03-07 14:56:38','2016-03-07 14:56:38','c5f4f3d8-ece9-493b-8f5b-7c932fa8efca','ZGVmYXVsdC1zdG9yZQ==-086ad97c-c349-4618-9844-60b55fc661c1',NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,NULL,NULL,NULL,1,1),(1,2,'2016-03-07 17:53:45','2016-03-07 17:54:03','d7be1c53-a081-46f3-96f3-4ec9de380919','ZGVmYXVsdC1zdG9yZQ==-4ca24980-c23e-46be-ae17-543daaa32329',NULL,0,10,NULL,0,0,0,NULL,10,0,NULL,NULL,1,1,1,NULL,1,2,1,1),(1,10,'2016-03-14 10:53:15','2016-03-14 10:53:27','3fb171d8-e888-41ff-b15d-d6dff2db80e7','ZGVmYXVsdC1zdG9yZQ==-314b458d-606c-41b9-ac0f-4d197932ada9',NULL,0,0,NULL,0,0,0,NULL,0,0,NULL,NULL,3,1,1,NULL,NULL,4,1,1),(1,11,'2016-03-14 10:53:43','2016-03-14 10:53:48','9967fa15-897c-43e3-a938-2258b06c99ca','ZGVmYXVsdC1zdG9yZQ==-c4d3f882-fbae-4de8-b839-a6d41d20a69f',NULL,0,0,NULL,0,0,0,NULL,0,0,NULL,NULL,5,1,1,NULL,NULL,6,1,1),(1,12,'2016-03-14 12:09:58','2016-03-14 12:10:51','e7e72ef7-2fef-4e17-9c80-6cc2b531841f','ZGVmYXVsdC1zdG9yZQ==-28e16c9d-5184-476b-aba6-9aa9fd27fb99',NULL,0,29.95,NULL,0,29.95,29.95,NULL,0,0,NULL,NULL,NULL,1,4,NULL,NULL,NULL,1,4),(1,14,'2016-03-18 10:59:34','2016-03-18 11:00:59','eb13c35b-973b-44df-ba7f-91758805c88d','ZGVmYXVsdC1zdG9yZQ==-6ff38e98-5bef-4421-b02f-7f63555cccfa',NULL,0,29.95,NULL,0,29.95,29.95,NULL,0,0,NULL,NULL,7,1,3,NULL,NULL,8,1,3),(1,15,'2016-03-18 11:01:04','2016-03-18 11:04:34','8d043eea-2372-4192-b0e0-e231ca4facc4','ZGVmYXVsdC1zdG9yZQ==-3c5b5906-486f-41ed-bdb3-074771a5bf0c',NULL,0,59.9,NULL,0,59.9,59.9,NULL,0,0,NULL,NULL,NULL,1,1,NULL,NULL,NULL,1,1),(1,16,'2016-03-18 11:06:41','2016-03-18 11:06:42','1d652022-829a-4d31-9a32-6a1bf85663b9','ZGVmYXVsdC1zdG9yZQ==-f6742dbd-0d94-4790-9d27-ccbce11fe1a0',NULL,0,29.95,NULL,0,29.95,29.95,NULL,0,0,NULL,NULL,NULL,1,1,NULL,NULL,NULL,1,1);
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
  UNIQUE KEY `UK_sjp9yc7sl1ypkl46oxw59ygu4` (`uuid`),
  KEY `FK_gtrq0e427pbknt7qu3err916v` (`orders_id`),
  CONSTRAINT `FK_gtrq0e427pbknt7qu3err916v` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`order_id`)
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
-- Table structure for table `paymentTransaction`
--

DROP TABLE IF EXISTS `paymentTransaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paymentTransaction` (
  `paytransaction_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `amount` double NOT NULL,
  `errorMessage` varchar(255) DEFAULT NULL,
  `reason_code` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `transaction_id` varchar(255) NOT NULL,
  `transactionType` varchar(255) DEFAULT NULL,
  `validUntil` date DEFAULT NULL,
  `payment_payment_id` bigint(20) DEFAULT NULL,
  `referred_transaction_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`paytransaction_id`),
  UNIQUE KEY `UK_4u2xxx3u01fg3du8y088nefq8` (`uuid`),
  KEY `FK_2y5qf8pic02ucemmd1cgch0pp` (`payment_payment_id`),
  KEY `FK_cnae6rsm5m9vd7qo68pap79rx` (`referred_transaction_id`),
  CONSTRAINT `FK_2y5qf8pic02ucemmd1cgch0pp` FOREIGN KEY (`payment_payment_id`) REFERENCES `Payment` (`payment_id`),
  CONSTRAINT `FK_cnae6rsm5m9vd7qo68pap79rx` FOREIGN KEY (`referred_transaction_id`) REFERENCES `paymentTransaction` (`paytransaction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paymentTransaction`
--

LOCK TABLES `paymentTransaction` WRITE;
/*!40000 ALTER TABLE `paymentTransaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `paymentTransaction` ENABLE KEYS */;
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
  `paymentStrategy` varchar(255) DEFAULT NULL,
  `description_stringid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`paymethod_id`),
  UNIQUE KEY `UK_bmjch4j6fd8gkelfnk1o4wu69` (`uuid`),
  UNIQUE KEY `UK_r4ogeetd11nghvh6k4b8y8pts` (`name`),
  KEY `FK_oqobaicpcg6wb2drcq80yv8nr` (`description_stringid`),
  CONSTRAINT `FK_oqobaicpcg6wb2drcq80yv8nr` FOREIGN KEY (`description_stringid`) REFERENCES `multilingual_string` (`Id`)
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
  `active` bit(1) NOT NULL,
  `cancelURL` varchar(255) DEFAULT NULL,
  `intent` varchar(255) DEFAULT NULL,
  `merchantKey` varchar(255) DEFAULT NULL,
  `merchantSecret` varchar(255) DEFAULT NULL,
  `returnURL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`paymethod_id`,`store_id`),
  UNIQUE KEY `UK_k67hcs955a6cideno3xgvtfgb` (`uuid`),
  KEY `FK_kqh3n5pl12a805iiwddutgyc4` (`store_id`),
  CONSTRAINT `FK_8qghydtnutj6oxh4puksolddc` FOREIGN KEY (`paymethod_id`) REFERENCES `paymethod` (`paymethod_id`),
  CONSTRAINT `FK_kqh3n5pl12a805iiwddutgyc4` FOREIGN KEY (`store_id`) REFERENCES `stores` (`store_id`)
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
  `defaultList` bit(1) NOT NULL,
  `name` varchar(100) NOT NULL,
  `store_id` bigint(20) NOT NULL,
  `description_string_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`pricelist_id`),
  UNIQUE KEY `UK_qwljxhitse93o7k4a24ahc2lw` (`uuid`),
  KEY `FK_g6t4lmxyent92lybek9xnqysp` (`store_id`),
  KEY `FK_8ncs1c0g9o99p618i2o023kpb` (`description_string_id`),
  CONSTRAINT `FK_8ncs1c0g9o99p618i2o023kpb` FOREIGN KEY (`description_string_id`) REFERENCES `multilingual_string` (`Id`),
  CONSTRAINT `FK_g6t4lmxyent92lybek9xnqysp` FOREIGN KEY (`store_id`) REFERENCES `stores` (`store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pricelist`
--

LOCK TABLES `pricelist` WRITE;
/*!40000 ALTER TABLE `pricelist` DISABLE KEYS */;
INSERT INTO `pricelist` (`pricelist_id`, `created`, `updated`, `uuid`, `defaultList`, `name`, `store_id`, `description_string_id`) VALUES (1,'2016-03-07 14:42:25','2016-03-07 14:42:25','562dc37c-9f58-4f4d-83bb-e6a0be6cd1ee','','default-list',1,1);
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
  `store_id` bigint(20) NOT NULL,
  `currency_id` bigint(20) NOT NULL,
  `pricelist_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) NOT NULL,
  PRIMARY KEY (`prices_id`),
  UNIQUE KEY `UK_c2u46kswvt9pw3xktt320npt9` (`uuid`),
  KEY `FK_w2kx9i8nwplrgtwpxunblt3m` (`store_id`),
  KEY `FK_9bv44p0kh8xfyvyahcmdb78cx` (`currency_id`),
  KEY `FK_hmm07ul56uq846upbdofqhs0k` (`pricelist_id`),
  KEY `FK_j66emjiiimluy2q5kacrmxws` (`product_id`),
  CONSTRAINT `FK_9bv44p0kh8xfyvyahcmdb78cx` FOREIGN KEY (`currency_id`) REFERENCES `currency` (`currency_id`),
  CONSTRAINT `FK_hmm07ul56uq846upbdofqhs0k` FOREIGN KEY (`pricelist_id`) REFERENCES `pricelist` (`pricelist_id`),
  CONSTRAINT `FK_j66emjiiimluy2q5kacrmxws` FOREIGN KEY (`product_id`) REFERENCES `abstractProducts` (`ctentry_id`),
  CONSTRAINT `FK_w2kx9i8nwplrgtwpxunblt3m` FOREIGN KEY (`store_id`) REFERENCES `stores` (`store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prices`
--

LOCK TABLES `prices` WRITE;
/*!40000 ALTER TABLE `prices` DISABLE KEYS */;
INSERT INTO `prices` (`prices_id`, `created`, `updated`, `uuid`, `current_price`, `min_qty`, `precedence`, `product_cost`, `product_price`, `valid_from`, `valid_to`, `store_id`, `currency_id`, `pricelist_id`, `product_id`) VALUES (49,'2016-03-08 10:30:26','2016-03-08 10:30:26','9e05c71b-3598-438c-a6b3-643e7a7cbf67',29.95,NULL,0,NULL,29.95,NULL,NULL,1,1,1,26),(50,'2016-03-08 10:30:26','2016-03-08 10:30:26','a8d88858-d719-4c2c-9be2-ade0035ed204',29.95,NULL,0,NULL,29.95,NULL,NULL,1,1,1,27),(51,'2016-03-08 10:30:26','2016-03-08 10:30:26','f9408481-4dde-4eeb-9294-6e5b6b6999b8',20,NULL,0,NULL,20,NULL,NULL,1,1,1,28),(52,'2016-03-08 10:30:26','2016-03-08 10:30:26','cb281c5d-28c5-4d35-b41e-3057ec1d4124',172,NULL,0,NULL,172,NULL,NULL,1,1,1,29),(53,'2016-03-08 10:30:26','2016-03-08 10:30:26','efb2de58-aa28-4d61-8dfa-7fc15f7ef90e',110,NULL,0,NULL,110,NULL,NULL,1,1,1,30),(54,'2016-03-08 10:30:26','2016-03-08 10:30:26','69a9921b-d695-43c6-be9c-1fc536a0afd0',79,NULL,0,NULL,79,NULL,NULL,1,1,1,31),(55,'2016-03-08 10:30:26','2016-03-08 10:30:26','a8856b1c-27c0-4183-b259-3340d6a6a5e2',60,NULL,0,NULL,60,NULL,NULL,1,1,1,30),(56,'2016-03-08 10:30:27','2016-03-08 10:30:27','aabbdb61-658e-4b78-a057-e1e6f6e1a123',29.95,NULL,0,NULL,29.95,NULL,NULL,1,1,1,33),(57,'2016-03-08 10:30:27','2016-03-08 10:30:27','93d18026-c626-471b-b87c-214d17a7ebfb',111.95,NULL,0,NULL,111.95,NULL,NULL,1,1,1,34),(58,'2016-03-08 10:30:27','2016-03-08 10:30:27','0e2e833e-9aa4-484e-a0cf-ce5f579c61d8',1095,NULL,0,NULL,1095,NULL,NULL,1,1,1,35),(59,'2016-03-08 10:30:27','2016-03-08 10:30:27','798753dc-55de-495d-a897-7bf6b5cdd043',95,NULL,0,NULL,95,NULL,NULL,1,1,1,36),(60,'2016-03-08 10:30:27','2016-03-08 10:30:27','f0cfba30-597d-40f0-a47f-4fa8b62cf9eb',34.95,NULL,0,NULL,34.95,NULL,NULL,1,1,1,37),(61,'2016-03-08 10:30:28','2016-03-08 10:30:28','d5341f0f-114b-45be-bf93-55d889ec94e2',25.95,NULL,0,NULL,25.95,NULL,NULL,1,1,1,38),(62,'2016-03-08 10:30:28','2016-03-08 10:30:28','7e6a36ab-9ae6-47a9-978d-0cc626e30201',12.95,NULL,0,NULL,12.95,NULL,NULL,1,1,1,39),(63,'2016-03-08 10:30:28','2016-03-08 10:30:28','8cfe15b6-808d-4c4e-a43d-a3d535731f9f',34.95,NULL,0,NULL,34.95,NULL,NULL,1,1,1,40),(64,'2016-03-08 10:30:28','2016-03-08 10:30:28','708299c6-e1f9-46a6-9a3f-1d7964acc2a4',60,NULL,0,NULL,60,NULL,NULL,1,1,1,41);
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
  KEY `FK_ksjduj00vpd1jksbre0i38cu5` (`product_id`),
  CONSTRAINT `FK_78g768uekswd36we8p83t3mg4` FOREIGN KEY (`pricelist_id`) REFERENCES `pricelist` (`pricelist_id`),
  CONSTRAINT `FK_ksjduj00vpd1jksbre0i38cu5` FOREIGN KEY (`product_id`) REFERENCES `abstractProducts` (`ctentry_id`)
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
  `isoCode` varchar(20) NOT NULL,
  `countries_id` bigint(20) NOT NULL,
  `description_stringid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`regions_id`),
  UNIQUE KEY `UK_po9lr0ewg38m4xhdaxo9t2hmt` (`uuid`),
  UNIQUE KEY `UK_qaqotvojqpd7pvychu6yffmky` (`isoCode`),
  KEY `FK_3cnkklhfvtofw5xkt90t3qp31` (`countries_id`),
  KEY `FK_7e5lk0dc5w0yvleuk2ocd1ph0` (`description_stringid`),
  CONSTRAINT `FK_3cnkklhfvtofw5xkt90t3qp31` FOREIGN KEY (`countries_id`) REFERENCES `countries` (`countries_id`),
  CONSTRAINT `FK_7e5lk0dc5w0yvleuk2ocd1ph0` FOREIGN KEY (`description_stringid`) REFERENCES `multilingual_string` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regions`
--

LOCK TABLES `regions` WRITE;
/*!40000 ALTER TABLE `regions` DISABLE KEYS */;
INSERT INTO `regions` (`regions_id`, `created`, `updated`, `uuid`, `isoCode`, `countries_id`, `description_stringid`) VALUES (1,'2016-03-07 17:20:46','2016-03-07 17:20:46','61536303-11db-4288-8143-f00653dccbb2','IT-65',118,371),(2,'2016-03-07 17:20:46','2016-03-07 17:20:46','1f2f270a-6490-490a-9ff4-49ba3d7fe34f','IT-77',118,372),(3,'2016-03-07 17:20:47','2016-03-07 17:20:47','9d97ffe2-877a-45da-92d2-64acc9f29db8','IT-78',118,373),(4,'2016-03-07 17:20:47','2016-03-07 17:20:47','7b7093da-837c-469b-905b-8931165372de','IT-72',118,374),(5,'2016-03-07 17:20:47','2016-03-07 17:20:47','2ca481aa-a83c-4f1d-83e6-74c70824b7c7','IT-45',118,375),(6,'2016-03-07 17:20:47','2016-03-07 17:20:47','536b758f-6879-4132-bf30-88fd9e66601b','IT-36',118,376),(7,'2016-03-07 17:20:47','2016-03-07 17:20:47','6701d68e-11cf-4152-8025-93518ec3c694','IT-62',118,377),(8,'2016-03-07 17:20:47','2016-03-07 17:20:47','10411002-c2d5-4b61-a442-0455481ade1b','IT-42',118,378),(9,'2016-03-07 17:20:47','2016-03-07 17:20:47','e928af14-6b78-43b5-a302-25e79fdc9dbd','IT-25',118,379),(10,'2016-03-07 17:20:47','2016-03-07 17:20:47','5353b5fa-6ed4-403a-8026-9c9938a6cb3e','IT-57',118,380),(11,'2016-03-07 17:20:47','2016-03-07 17:20:47','82eb07bb-6740-4ce7-897e-0ad5e9f35594','IT-67',118,381),(12,'2016-03-07 17:20:48','2016-03-07 17:20:48','5a095ace-a039-41f6-9f16-3958a714e0bf','IT-21',118,382),(13,'2016-03-07 17:20:48','2016-03-07 17:20:48','0556f9a0-25b2-43b3-98db-9739641b0ca9','IT-75',118,383),(14,'2016-03-07 17:20:48','2016-03-07 17:20:48','e2f0e5b9-408f-4cc1-8263-928a1c86ebb4','IT-88',118,384),(15,'2016-03-07 17:20:48','2016-03-07 17:20:48','6db64e98-1676-4ff7-b5c1-38ce8f45ca99','IT-82',118,385),(16,'2016-03-07 17:20:48','2016-03-07 17:20:48','aab54943-6293-431b-9465-7aa11b250fde','IT-52',118,386),(17,'2016-03-07 17:20:48','2016-03-07 17:20:48','cfc327f0-0738-47d7-9a32-c84aa1ae4117','IT-32',118,387),(18,'2016-03-07 17:20:48','2016-03-07 17:20:48','4bb9671c-0a69-4182-9b42-b6f5f2d996ea','IT-55',118,388),(19,'2016-03-07 17:20:48','2016-03-07 17:20:48','586279c1-eedc-49af-ab98-d60900d67483','IT-23',118,389),(20,'2016-03-07 17:20:48','2016-03-07 17:20:48','00ad08a3-4ffb-45ab-b6b6-0302185ee4eb','IT-34',118,390);
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
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `UK_bdys1vaxs0jqndxmixeragus8` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`role_id`, `created`, `updated`, `uuid`, `description`, `name`) VALUES (1,'2016-03-07 14:42:24','2016-03-07 14:42:24','7f6ad4e0-99e9-4f6d-8f3f-71cbadcecd3d',NULL,'employee'),(2,'2016-03-07 14:42:24','2016-03-07 14:42:24','e0e9600a-cf9c-45f6-9ebb-42ef77d766e3',NULL,'administrator');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scheduler_job`
--

DROP TABLE IF EXISTS `scheduler_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scheduler_job` (
  `type_of_scheduler` int(11) NOT NULL,
  `jobscheduler_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `last_run_date` datetime DEFAULT NULL,
  `next_run_date` datetime DEFAULT NULL,
  `cronexpression` varchar(255) DEFAULT NULL,
  `number_triggered` int(11) DEFAULT NULL,
  `RepeatCount` int(11) DEFAULT NULL,
  `seconds_interval` int(11) DEFAULT NULL,
  `end_to` datetime DEFAULT NULL,
  `start_at` datetime DEFAULT NULL,
  `jobdetail_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`jobscheduler_id`),
  UNIQUE KEY `UK_bmweqdf3c26osisjft7vfld0h` (`uuid`),
  KEY `FK_nr8whrbcjfbhnnjhvummn2gqe` (`jobdetail_id`),
  CONSTRAINT `FK_nr8whrbcjfbhnnjhvummn2gqe` FOREIGN KEY (`jobdetail_id`) REFERENCES `job_details` (`jobDetail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scheduler_job`
--

LOCK TABLES `scheduler_job` WRITE;
/*!40000 ALTER TABLE `scheduler_job` DISABLE KEYS */;
/*!40000 ALTER TABLE `scheduler_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipmode`
--

DROP TABLE IF EXISTS `shipmode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shipmode` (
  `shipmode_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `shipmodeStrategy` varchar(255) DEFAULT NULL,
  `name_string_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`shipmode_id`),
  UNIQUE KEY `UK_tiseihdu49fvcfn9ngxubxode` (`uuid`),
  UNIQUE KEY `UK_cpa9qxk7mmcepmuf6165sl6bf` (`name`),
  KEY `FK_rabm2541kwj7vyf9aulipfliy` (`name_string_id`),
  CONSTRAINT `FK_rabm2541kwj7vyf9aulipfliy` FOREIGN KEY (`name_string_id`) REFERENCES `multilingual_string` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipmode`
--

LOCK TABLES `shipmode` WRITE;
/*!40000 ALTER TABLE `shipmode` DISABLE KEYS */;
INSERT INTO `shipmode` (`shipmode_id`, `created`, `updated`, `uuid`, `name`, `shipmodeStrategy`, `name_string_id`) VALUES (1,'2016-03-07 16:23:31','2016-03-07 16:23:31','3b446a74-d710-4d42-9656-b57a3aff6574','ShipmodeTest','defaultShipmodeStrategy',121);
/*!40000 ALTER TABLE `shipmode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipmodelookup`
--

DROP TABLE IF EXISTS `shipmodelookup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shipmodelookup` (
  `shipmodelookup_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `zipcode` varchar(30) DEFAULT NULL,
  `country_id` bigint(20) DEFAULT NULL,
  `districts_id` bigint(20) DEFAULT NULL,
  `geocode_id` bigint(20) DEFAULT NULL,
  `regions_id` bigint(20) DEFAULT NULL,
  `shipmode_id` bigint(20) NOT NULL,
  `store_id` bigint(20) NOT NULL,
  PRIMARY KEY (`shipmodelookup_id`),
  UNIQUE KEY `UK_hee0nwr03f9rkxsql2v9hiwm4` (`uuid`),
  KEY `FK_jsxpv3nunke5j64u64r14j6er` (`country_id`),
  KEY `FK_gtx5qe9ckc99aah81omblsavb` (`districts_id`),
  KEY `FK_n7fipd70co4t3mdguppiruwi4` (`geocode_id`),
  KEY `FK_ouam5eudu42kurqmfrinil7bv` (`regions_id`),
  KEY `FK_qsuxpsgl06jjdb3pqm4cqp9g3` (`shipmode_id`),
  KEY `FK_dnqt1ny7j8gbqonm25ivg79nm` (`store_id`),
  CONSTRAINT `FK_dnqt1ny7j8gbqonm25ivg79nm` FOREIGN KEY (`store_id`) REFERENCES `stores` (`store_id`),
  CONSTRAINT `FK_gtx5qe9ckc99aah81omblsavb` FOREIGN KEY (`districts_id`) REFERENCES `district` (`district_id`),
  CONSTRAINT `FK_jsxpv3nunke5j64u64r14j6er` FOREIGN KEY (`country_id`) REFERENCES `countries` (`countries_id`),
  CONSTRAINT `FK_n7fipd70co4t3mdguppiruwi4` FOREIGN KEY (`geocode_id`) REFERENCES `geocode` (`geocode_id`),
  CONSTRAINT `FK_ouam5eudu42kurqmfrinil7bv` FOREIGN KEY (`regions_id`) REFERENCES `regions` (`regions_id`),
  CONSTRAINT `FK_qsuxpsgl06jjdb3pqm4cqp9g3` FOREIGN KEY (`shipmode_id`) REFERENCES `shipmode` (`shipmode_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipmodelookup`
--

LOCK TABLES `shipmodelookup` WRITE;
/*!40000 ALTER TABLE `shipmodelookup` DISABLE KEYS */;
INSERT INTO `shipmodelookup` (`shipmodelookup_id`, `created`, `updated`, `uuid`, `zipcode`, `country_id`, `districts_id`, `geocode_id`, `regions_id`, `shipmode_id`, `store_id`) VALUES (1,'2016-03-07 17:24:58','2016-03-07 17:24:58','257144af-9c86-4a2f-a472-333f2992c16c',NULL,118,NULL,NULL,NULL,1,1);
/*!40000 ALTER TABLE `shipmodelookup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipping`
--

DROP TABLE IF EXISTS `shipping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shipping` (
  `shipping_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `rangestart` double DEFAULT NULL,
  `value` double DEFAULT NULL,
  `currency_id` bigint(20) NOT NULL,
  `shipmodelookup_id` bigint(20) NOT NULL,
  PRIMARY KEY (`shipping_id`),
  UNIQUE KEY `UK_ip0me30iwhp4rky8q66xvq7dc` (`uuid`),
  KEY `FK_ny0xcchnejby85snryl69u8qh` (`currency_id`),
  KEY `FK_5eh1l1ilwe71792ts0k23udcc` (`shipmodelookup_id`),
  CONSTRAINT `FK_5eh1l1ilwe71792ts0k23udcc` FOREIGN KEY (`shipmodelookup_id`) REFERENCES `shipmodelookup` (`shipmodelookup_id`),
  CONSTRAINT `FK_ny0xcchnejby85snryl69u8qh` FOREIGN KEY (`currency_id`) REFERENCES `currency` (`currency_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipping`
--

LOCK TABLES `shipping` WRITE;
/*!40000 ALTER TABLE `shipping` DISABLE KEYS */;
INSERT INTO `shipping` (`shipping_id`, `created`, `updated`, `uuid`, `rangestart`, `value`, `currency_id`, `shipmodelookup_id`) VALUES (1,'2016-03-07 17:53:29','2016-03-07 17:53:29','a692fcdf-cc89-409b-a686-fc3c8d087316',0,10,1,1);
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
  `taxcategory_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`store_id`),
  UNIQUE KEY `UK_snojwpscgwbt9xj5njydd2iit` (`uuid`),
  KEY `FK_r7rickaq5g02yish2ywgibxjk` (`currency_id`),
  KEY `FK_foaxqu73sq6dipv52wa3i26it` (`taxcategory_id`),
  CONSTRAINT `FK_foaxqu73sq6dipv52wa3i26it` FOREIGN KEY (`taxcategory_id`) REFERENCES `taxes` (`taxes_id`),
  CONSTRAINT `FK_r7rickaq5g02yish2ywgibxjk` FOREIGN KEY (`currency_id`) REFERENCES `currency` (`currency_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stores`
--

LOCK TABLES `stores` WRITE;
/*!40000 ALTER TABLE `stores` DISABLE KEYS */;
INSERT INTO `stores` (`store_id`, `created`, `updated`, `uuid`, `name`, `currency_id`, `taxcategory_id`) VALUES (1,'2016-03-07 14:42:24','2016-03-07 14:42:24','d240ba15-ca93-4fa8-aad0-09e4778920e9','default-store',1,NULL),(2,'2016-03-07 14:42:24','2016-03-07 14:42:24','4320b97d-beca-4719-bd0a-9d300739fef2','test-store',1,NULL);
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
  UNIQUE KEY `UK_i8u1r0u9v5pp8gy6uindh2sn9` (`uuid`),
  KEY `FK_tixig2etrfd8gv5bpna8ajstj` (`orders_id`),
  KEY `FK_c11vbr0g781l87nk8duutvsuw` (`shipmode_id`),
  CONSTRAINT `FK_c11vbr0g781l87nk8duutvsuw` FOREIGN KEY (`shipmode_id`) REFERENCES `shipmode` (`shipmode_id`),
  CONSTRAINT `FK_tixig2etrfd8gv5bpna8ajstj` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`order_id`)
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
  `taxes_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `uuid` varchar(100) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `rate` double DEFAULT NULL,
  `store_id` bigint(20) NOT NULL,
  `name_string_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`taxes_id`),
  UNIQUE KEY `UK_fwxfveagu4lqi06i1c5r1x69b` (`uuid`),
  UNIQUE KEY `UK_bcn1ivd4hwx3ncxs34ivtsnac` (`store_id`,`name`),
  KEY `FK_pyhrgdmq34oocg4bk6xk7achy` (`name_string_id`),
  CONSTRAINT `FK_e8yq6baap7e3kjx131h6cjxmn` FOREIGN KEY (`store_id`) REFERENCES `stores` (`store_id`),
  CONSTRAINT `FK_pyhrgdmq34oocg4bk6xk7achy` FOREIGN KEY (`name_string_id`) REFERENCES `multilingual_string` (`Id`)
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
-- Table structure for table `user2storerel`
--

DROP TABLE IF EXISTS `user2storerel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user2storerel` (
  `user_id` bigint(20) NOT NULL,
  `store_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`store_id`),
  KEY `FK_d8gen24hnehwleb7e9svsks72` (`store_id`),
  CONSTRAINT `FK_d8gen24hnehwleb7e9svsks72` FOREIGN KEY (`store_id`) REFERENCES `stores` (`store_id`),
  CONSTRAINT `FK_pgw09lf0xaa6u260r8vlolcp8` FOREIGN KEY (`user_id`) REFERENCES `users` (`member_id`)
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
  UNIQUE KEY `UK_6y8vtyew844fp38oppwia7dn` (`uuid`),
  KEY `FK_3p5g1x0mtdydt5005hyd82t39` (`parent_member_id`),
  CONSTRAINT `FK_3p5g1x0mtdydt5005hyd82t39` FOREIGN KEY (`parent_member_id`) REFERENCES `users` (`member_id`),
  CONSTRAINT `FK_nf8qhbvp5dq55tw8hixtosbe7` FOREIGN KEY (`child_member_id`) REFERENCES `users` (`member_id`)
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
  `billing_address_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`member_id`),
  KEY `FK_loxlas3j0txs6d5ogihkwayol` (`billing_address_id`),
  CONSTRAINT `FK_4lxonc7i8h0rijko84flsbd4e` FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`),
  CONSTRAINT `FK_loxlas3j0txs6d5ogihkwayol` FOREIGN KEY (`billing_address_id`) REFERENCES `BillingAddress` (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`email`, `field4`, `firstname`, `last_visit`, `lastname`, `phone`, `user_type`, `member_id`, `billing_address_id`) VALUES (NULL,NULL,NULL,NULL,'Anonymous',NULL,'ANONYMOUS',1,NULL),(NULL,NULL,NULL,'2016-03-18 11:02:28','Superuser',NULL,'SUPERSUSER',2,NULL),('gabri@gabri.it',NULL,'gabri','2016-03-18 11:00:21','gabri',NULL,'REGISTERED',3,1),('fpicinelli@stepfour.it',NULL,'federico','2016-03-14 12:10:50','Picinelli',NULL,'REGISTERED',4,NULL);
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
  UNIQUE KEY `UK_rv8vbmdqpm35rr3pvetbyvo8i` (`logonid`),
  KEY `FK_nbobgwt3mchuhv77h3klxcxwl` (`locale_id`),
  CONSTRAINT `FK_nbobgwt3mchuhv77h3klxcxwl` FOREIGN KEY (`locale_id`) REFERENCES `locale` (`locale_id`),
  CONSTRAINT `FK_tkehv1ajnmxbmvexqsw01v1ki` FOREIGN KEY (`member_id`) REFERENCES `users` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_reg`
--

LOCK TABLES `users_reg` WRITE;
/*!40000 ALTER TABLE `users_reg` DISABLE KEYS */;
INSERT INTO `users_reg` (`alternate_email`, `changeanswer`, `changequestion`, `dn`, `last_login`, `logonid`, `password`, `password_change`, `status`, `member_id`, `locale_id`) VALUES (NULL,NULL,NULL,NULL,'2016-03-18 11:02:28','superuser','admin',NULL,'ACTIVE',2,NULL),(NULL,NULL,NULL,NULL,'2016-03-18 11:00:21','gabri','gabri',NULL,'ACTIVE',3,NULL),(NULL,NULL,NULL,NULL,'2016-03-14 12:10:50','fedepic','12345678',NULL,'ACTIVE',4,NULL);
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
  `forceInStock` bit(1) NOT NULL,
  `inventoryThreshold` double DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `precedence` double NOT NULL,
  `address_id` bigint(20) DEFAULT NULL,
  `description_string_id` bigint(20) DEFAULT NULL,
  `store_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`warehouse_id`),
  UNIQUE KEY `UK_cpsbp6ppspc1nwxb0l5nnd31y` (`uuid`),
  KEY `FK_5hyew1b3bewu839bc54o2jo05` (`address_id`),
  KEY `FK_l8jx0kjjaxjg1aisld7r65h68` (`description_string_id`),
  KEY `FK_l34otojjaqlqtmd4xdr2glndf` (`store_id`),
  CONSTRAINT `FK_5hyew1b3bewu839bc54o2jo05` FOREIGN KEY (`address_id`) REFERENCES `Address` (`address_id`),
  CONSTRAINT `FK_l34otojjaqlqtmd4xdr2glndf` FOREIGN KEY (`store_id`) REFERENCES `stores` (`store_id`),
  CONSTRAINT `FK_l8jx0kjjaxjg1aisld7r65h68` FOREIGN KEY (`description_string_id`) REFERENCES `multilingual_string` (`Id`)
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
  `alwaysInstock` bit(1) NOT NULL,
  `storeThreshold` double DEFAULT NULL,
  `store_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_k28jt66briun6gtv3u0fopbd5` (`uuid`),
  KEY `FK_4kyr05c8wijwjlqlt88g5q4j3` (`store_id`),
  CONSTRAINT `FK_4kyr05c8wijwjlqlt88g5q4j3` FOREIGN KEY (`store_id`) REFERENCES `stores` (`store_id`)
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

-- Dump completed on 2016-03-18 11:09:44
