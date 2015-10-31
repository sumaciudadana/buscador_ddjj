CREATE DATABASE  IF NOT EXISTS `sumaciudadana` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `sumaciudadana`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: sumaciudadana
-- ------------------------------------------------------
-- Server version	5.0.96-community-nt

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
-- Not dumping tablespaces as no INFORMATION_SCHEMA.FILES table on this server
--

--
-- Table structure for table `affidavit`
--

DROP TABLE IF EXISTS `affidavit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `affidavit` (
  `idaffidavit` int(11) NOT NULL auto_increment,
  `idpservant` int(11) NOT NULL,
  `idposition` int(11) default NULL,
  `affi_presentation` char(1) default NULL,
  `affi_year` int(11) default NULL,
  `affi_public_income` decimal(15,2) default NULL,
  `affi_private_income` decimal(15,2) default NULL,
  `affi_total_month_wealth` decimal(15,2) default NULL,
  `affi_other_public_income` decimal(15,2) default NULL,
  `affi_other_private_income` decimal(15,2) default NULL,
  `affi_total_other_income` decimal(15,2) default NULL,
  `affi_total_belong` decimal(15,2) default NULL,
  `affi_total_wealth` decimal(15,2) default NULL,
  `affi_type` varchar(45) default NULL,
  `affi_source_url` varchar(200) default NULL,
  `affi_valid` int(1) default NULL,
  `affi_date` datetime default NULL,
  `user_create` varchar(45) default NULL,
  `date_create` datetime default NULL,
  `user_modify` varchar(45) default NULL,
  `date_modify` datetime default NULL,
  PRIMARY KEY  (`idaffidavit`),
  KEY `fk_affidavit_pservant1` (`idpservant`),
  KEY `fk_affidavit_position_idx` (`idposition`),
  CONSTRAINT `fk_affidavit_position` FOREIGN KEY (`idposition`) REFERENCES `position` (`idposition`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_affidavit_pservant1` FOREIGN KEY (`idpservant`) REFERENCES `pservant` (`idpservant`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=515 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `belonging`
--

DROP TABLE IF EXISTS `belonging`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `belonging` (
  `idbelonging` int(11) NOT NULL auto_increment,
  `idbelonging_types` int(11) default NULL,
  `category_belonging` char(1) default NULL,
  `des_belonging` varchar(150) default NULL,
  `detail_belonging` varchar(100) default NULL,
  `value_belonging` decimal(10,0) default NULL,
  `belonging_source` varchar(50) default NULL,
  `idaffidavit` int(11) NOT NULL,
  `user_create` varchar(45) default NULL,
  `date_create` datetime default NULL,
  `user_modify` varchar(45) default NULL,
  `date_modify` datetime default NULL,
  PRIMARY KEY  (`idbelonging`),
  KEY `fk_belonging_belonging_types` (`idbelonging_types`),
  KEY `fk_belonging_affidavit1` (`idaffidavit`),
  CONSTRAINT `fk_belonging_affidavit1` FOREIGN KEY (`idaffidavit`) REFERENCES `affidavit` (`idaffidavit`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_belonging_belonging_types` FOREIGN KEY (`idbelonging_types`) REFERENCES `belonging_type` (`idbelonging_types`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=725 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `belonging_type`
--

DROP TABLE IF EXISTS `belonging_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `belonging_type` (
  `idbelonging_types` int(11) NOT NULL auto_increment,
  `des_belonging_type` varchar(45) default NULL,
  PRIMARY KEY  (`idbelonging_types`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `complaint`
--

DROP TABLE IF EXISTS `complaint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `complaint` (
  `idcomplaint` int(11) NOT NULL auto_increment,
  `idpservant` int(11) NOT NULL,
  `com_description` varchar(200) default NULL,
  `com_file` blob,
  PRIMARY KEY  (`idcomplaint`),
  KEY `fk_complaint_pservant1` (`idpservant`),
  CONSTRAINT `fk_complaint_pservant1` FOREIGN KEY (`idpservant`) REFERENCES `pservant` (`idpservant`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jurisdiction`
--

DROP TABLE IF EXISTS `jurisdiction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jurisdiction` (
  `idjurisdiction` int(11) NOT NULL auto_increment,
  `idorganization` int(11) default NULL,
  `jur_name` varchar(100) NOT NULL,
  PRIMARY KEY  (`idjurisdiction`),
  KEY `fk_jurisdiction_entity_idx` (`idorganization`),
  KEY `fk_jurisdiction_organization_idx` (`idorganization`),
  CONSTRAINT `fk_jurisdiction_organization` FOREIGN KEY (`idorganization`) REFERENCES `organization` (`idorganization`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `organization`
--

DROP TABLE IF EXISTS `organization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `organization` (
  `idorganization` int(11) NOT NULL auto_increment,
  `org_name` varchar(100) NOT NULL,
  PRIMARY KEY  (`idorganization`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `position`
--

DROP TABLE IF EXISTS `position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `position` (
  `idposition` int(11) NOT NULL auto_increment,
  `idjurisdiction` int(11) default NULL,
  `pos_name` varchar(100) NOT NULL,
  PRIMARY KEY  (`idposition`),
  KEY `fk_position_jurisdiction_idx` (`idjurisdiction`),
  CONSTRAINT `fk_position_jurisdiction` FOREIGN KEY (`idjurisdiction`) REFERENCES `jurisdiction` (`idjurisdiction`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pservant`
--

DROP TABLE IF EXISTS `pservant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pservant` (
  `idpservant` int(11) NOT NULL auto_increment,
  `ser_name` varchar(45) default NULL,
  `ser_firstsurname` varchar(45) default NULL,
  `ser_secondsurname` varchar(45) default NULL,
  `ser_dni` varchar(8) default NULL,
  `ser_address` varchar(100) default NULL,
  `ser_image` blob,
  `user_create` varchar(45) default NULL,
  `date_create` datetime default NULL,
  `user_modify` varchar(45) default NULL,
  `date_modify` datetime default NULL,
  PRIMARY KEY  (`idpservant`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `iduser` int(11) NOT NULL auto_increment,
  `username` varchar(45) default NULL,
  `password` varchar(45) default NULL,
  `u_firstname` varchar(45) default NULL,
  `u_firstsurname` varchar(45) default NULL,
  `u_secondsurname` varchar(45) default NULL,
  `u_telephone` varchar(45) default NULL,
  `u_address` varchar(45) default NULL,
  `u_email` varchar(45) default NULL,
  `u_status` char(1) default NULL,
  `user_create` varchar(45) default NULL,
  `date_create` datetime default NULL,
  `user_modify` varchar(45) default NULL,
  `date_modify` datetime default NULL,
  PRIMARY KEY  (`iduser`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-31 10:55:25
