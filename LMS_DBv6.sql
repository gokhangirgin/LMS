CREATE DATABASE  IF NOT EXISTS `lms_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `lms_db`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: lms_db
-- ------------------------------------------------------
-- Server version	5.5.25a

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
-- Table structure for table `basket`
--

DROP TABLE IF EXISTS `basket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `basket` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `EntryTime` time NOT NULL,
  `FinishTime` datetime DEFAULT NULL,
  `Type` int(11) DEFAULT NULL COMMENT 'Basket tipi Community, Hospitality standartlarına göre yıkanabilmesi için',
  `Step` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `basket`
--

LOCK TABLES `basket` WRITE;
/*!40000 ALTER TABLE `basket` DISABLE KEYS */;
INSERT INTO `basket` VALUES (8,'18:11:40',NULL,0,NULL),(9,'18:42:09',NULL,0,10),(10,'18:43:44',NULL,0,6),(11,'19:01:40',NULL,0,6);
/*!40000 ALTER TABLE `basket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `basketitems`
--

DROP TABLE IF EXISTS `basketitems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `basketitems` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `ItemId` int(11) DEFAULT NULL,
  `BasketId` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `ItemId` (`ItemId`),
  KEY `basketitems_ibfk_2` (`BasketId`),
  CONSTRAINT `basketitems_ibfk_1` FOREIGN KEY (`ItemId`) REFERENCES `item` (`Id`),
  CONSTRAINT `basketitems_ibfk_2` FOREIGN KEY (`BasketId`) REFERENCES `basket` (`Id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `basketitems`
--

LOCK TABLES `basketitems` WRITE;
/*!40000 ALTER TABLE `basketitems` DISABLE KEYS */;
INSERT INTO `basketitems` VALUES (3,33,8),(4,34,8),(5,35,8),(6,36,8),(7,37,8),(8,38,9),(9,39,9),(10,40,9),(11,41,9),(12,42,9),(13,43,10),(14,44,10),(15,45,10),(16,46,10),(17,47,10),(18,48,11),(19,49,11),(20,50,11),(21,51,11),(22,52,11);
/*!40000 ALTER TABLE `basketitems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bleach`
--

DROP TABLE IF EXISTS `bleach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bleach` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Brand` varchar(20) NOT NULL,
  `AbbritionEffect` double NOT NULL,
  `UnitPrice` double NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bleach`
--

LOCK TABLES `bleach` WRITE;
/*!40000 ALTER TABLE `bleach` DISABLE KEYS */;
INSERT INTO `bleach` VALUES (1,'Bleach Name',12.3,7.09),(2,'Bleach client',1.7,3.56);
/*!40000 ALTER TABLE `bleach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(20) NOT NULL,
  `LastName` varchar(20) NOT NULL,
  `DateOfBirth` date NOT NULL,
  `Gender` bit(1) NOT NULL,
  `Address` varchar(40) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `Phone` varchar(10) NOT NULL,
  `Password` varchar(20) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Gökhan','Girgin','1988-12-11','','izmir','gokhan@girgin.com','5557039039','123456');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detergant`
--

DROP TABLE IF EXISTS `detergant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detergant` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(20) NOT NULL,
  `Odor` varchar(20) NOT NULL,
  `ForColored` bit(1) NOT NULL,
  `UnitPrice` double NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detergant`
--

LOCK TABLES `detergant` WRITE;
/*!40000 ALTER TABLE `detergant` DISABLE KEYS */;
INSERT INTO `detergant` VALUES (1,'Omo','odor?','\0',17.43);
/*!40000 ALTER TABLE `detergant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `drying`
--

DROP TABLE IF EXISTS `drying`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `drying` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Temperature` double NOT NULL,
  `OpenAir` bit(1) DEFAULT NULL,
  `StartTime` date DEFAULT NULL,
  `EndTime` date DEFAULT NULL,
  `BasketId` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `BasketId` (`BasketId`),
  CONSTRAINT `drying_ibfk_1` FOREIGN KEY (`BasketId`) REFERENCES `basket` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drying`
--

LOCK TABLES `drying` WRITE;
/*!40000 ALTER TABLE `drying` DISABLE KEYS */;
INSERT INTO `drying` VALUES (2,0,NULL,'2013-05-22',NULL,9);
/*!40000 ALTER TABLE `drying` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fabricsoftener`
--

DROP TABLE IF EXISTS `fabricsoftener`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fabricsoftener` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Brand` varchar(20) NOT NULL,
  `AbbritionEffect` varchar(20) NOT NULL,
  `UnitPrice` double NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fabricsoftener`
--

LOCK TABLES `fabricsoftener` WRITE;
/*!40000 ALTER TABLE `fabricsoftener` DISABLE KEYS */;
INSERT INTO `fabricsoftener` VALUES (1,'Fabric Softener Name','1.3',4.63);
/*!40000 ALTER TABLE `fabricsoftener` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ironing`
--

DROP TABLE IF EXISTS `ironing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ironing` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `BasketId` int(11) NOT NULL,
  `StartTime` datetime DEFAULT NULL,
  `EndTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_basket_ironing` (`BasketId`),
  CONSTRAINT `fk_basket_ironing` FOREIGN KEY (`BasketId`) REFERENCES `basket` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ironing`
--

LOCK TABLES `ironing` WRITE;
/*!40000 ALTER TABLE `ironing` DISABLE KEYS */;
INSERT INTO `ironing` VALUES (2,10,'2013-05-22 18:43:44',NULL);
/*!40000 ALTER TABLE `ironing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Type` int(11) NOT NULL,
  `Material` int(11) NOT NULL,
  `Color` bit(1) NOT NULL,
  `Weight` double NOT NULL,
  `Location` int(11) NOT NULL,
  `WashedCount` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,1,0,'\0',0.137,9,1),(2,0,0,'',0.187,9,2),(33,0,0,'\0',0.192,9,1),(34,0,2,'\0',0.198,9,3),(35,0,0,'\0',0.196,7,2),(36,0,1,'\0',0.195,6,2),(37,0,2,'\0',0.193,9,0),(38,0,0,'\0',0.192,2,1),(39,0,2,'\0',0.193,1,0),(40,0,0,'\0',0.196,1,2),(41,0,1,'\0',0.195,1,2),(42,0,2,'\0',0.198,1,3),(43,0,0,'\0',0.196,1,2),(44,0,1,'\0',0.195,1,2),(45,0,0,'\0',0.192,1,1),(46,0,2,'\0',0.193,1,0),(47,0,2,'\0',0.198,1,3),(48,0,2,'\0',0.193,1,0),(49,0,2,'\0',0.198,1,3),(50,0,1,'\0',0.195,1,2),(51,0,0,'\0',0.196,1,2),(52,0,0,'\0',0.192,1,1);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderitems`
--

DROP TABLE IF EXISTS `orderitems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderitems` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `ItemId` int(11) DEFAULT NULL,
  `OrderId` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `ItemId` (`ItemId`),
  KEY `OrderId` (`OrderId`),
  CONSTRAINT `orderitems_ibfk_1` FOREIGN KEY (`ItemId`) REFERENCES `item` (`Id`),
  CONSTRAINT `orderitems_ibfk_2` FOREIGN KEY (`OrderId`) REFERENCES `orders` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderitems`
--

LOCK TABLES `orderitems` WRITE;
/*!40000 ALTER TABLE `orderitems` DISABLE KEYS */;
INSERT INTO `orderitems` VALUES (1,1,1),(2,2,1),(3,33,1),(4,34,1),(5,35,1),(6,36,1),(7,37,1);
/*!40000 ALTER TABLE `orderitems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `CustomerId` int(11) NOT NULL,
  `TransactionDate` date NOT NULL,
  `Status` int(11) NOT NULL,
  `PersonnelId` int(11) NOT NULL,
  `EstimatedPickUpDate` date NOT NULL,
  `RealPickUpDate` date NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `CustomerId` (`CustomerId`),
  KEY `PersonnelId` (`PersonnelId`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`CustomerId`) REFERENCES `customer` (`Id`),
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`PersonnelId`) REFERENCES `personnel` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,1,'2013-03-31',2,1,'2013-03-31','2013-03-31');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `packaging`
--

DROP TABLE IF EXISTS `packaging`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `packaging` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `BasketId` int(11) NOT NULL,
  `StartTime` datetime DEFAULT NULL,
  `EndTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_basket_packaging` (`BasketId`),
  CONSTRAINT `fk_basket_packaging` FOREIGN KEY (`BasketId`) REFERENCES `basket` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `packaging`
--

LOCK TABLES `packaging` WRITE;
/*!40000 ALTER TABLE `packaging` DISABLE KEYS */;
INSERT INTO `packaging` VALUES (4,8,'2013-05-22 18:48:13',NULL),(5,11,'2013-05-22 19:01:40',NULL);
/*!40000 ALTER TABLE `packaging` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personnel`
--

DROP TABLE IF EXISTS `personnel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personnel` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(20) NOT NULL,
  `LastName` varchar(20) NOT NULL,
  `Address` varchar(40) NOT NULL,
  `DateOfBirth` date NOT NULL,
  `Phone` varchar(10) NOT NULL,
  `Gender` bit(1) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `Title` varchar(20) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personnel`
--

LOCK TABLES `personnel` WRITE;
/*!40000 ALTER TABLE `personnel` DISABLE KEYS */;
INSERT INTO `personnel` VALUES (1,'Gökhan','Girgin','izmir','1988-12-11','5557039039','','gokhan@girgin.com','Administrator');
/*!40000 ALTER TABLE `personnel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `washing`
--

DROP TABLE IF EXISTS `washing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `washing` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `WaterId` int(11) DEFAULT NULL,
  `MachineId` int(11) DEFAULT NULL,
  `StartTime` datetime DEFAULT NULL,
  `EndTime` datetime DEFAULT NULL,
  `BasketId` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Id_UNIQUE` (`Id`),
  KEY `fk_basket` (`BasketId`),
  CONSTRAINT `fk_basket` FOREIGN KEY (`BasketId`) REFERENCES `basket` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `washing`
--

LOCK TABLES `washing` WRITE;
/*!40000 ALTER TABLE `washing` DISABLE KEYS */;
INSERT INTO `washing` VALUES (5,NULL,1,'2013-05-22 18:11:40',NULL,8);
/*!40000 ALTER TABLE `washing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `water`
--

DROP TABLE IF EXISTS `water`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `water` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Temperature` double NOT NULL,
  `PH` double NOT NULL,
  `Hardness` double NOT NULL,
  `BleachAmount` double NOT NULL,
  `FabricSoftenerAmount` double NOT NULL,
  `DetergantAmount` double NOT NULL,
  `DetergantId` int(11) NOT NULL,
  `BleachId` int(11) NOT NULL,
  `FabricSoftenerId` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `DetergantId` (`DetergantId`),
  KEY `BleachId` (`BleachId`),
  KEY `FabricSoftenerId` (`FabricSoftenerId`),
  CONSTRAINT `water_ibfk_1` FOREIGN KEY (`DetergantId`) REFERENCES `detergant` (`Id`),
  CONSTRAINT `water_ibfk_2` FOREIGN KEY (`BleachId`) REFERENCES `bleach` (`Id`),
  CONSTRAINT `water_ibfk_3` FOREIGN KEY (`FabricSoftenerId`) REFERENCES `fabricsoftener` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `water`
--

LOCK TABLES `water` WRITE;
/*!40000 ALTER TABLE `water` DISABLE KEYS */;
INSERT INTO `water` VALUES (1,45.3,7.2,1.2,3.2,1.7,4.8,1,1,1);
/*!40000 ALTER TABLE `water` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-05-23  2:08:38
