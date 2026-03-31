CREATE DATABASE  IF NOT EXISTS `book30_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `book30_db`;
-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: localhost    Database: book30_db
-- ------------------------------------------------------
-- Server version	8.0.45

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `book3`
--

DROP TABLE IF EXISTS `book3`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book3` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `author` varchar(255) DEFAULT NULL,
  `borrow_date` varchar(255) DEFAULT NULL,
  `category` enum('ANATOMY','BIOGRAPHY','BIOLOGY','CHEMISTRY','FICTION','HISTORY','NON_FICTION','SCIENCE','UNKNOWN') DEFAULT NULL,
  `is_available` bit(1) DEFAULT NULL,
  `return_date` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book3`
--

LOCK TABLES `book3` WRITE;
/*!40000 ALTER TABLE `book3` DISABLE KEYS */;
INSERT INTO `book3` VALUES (1,'KLI',NULL,'FICTION',_binary '\0',NULL,'KIL'),(2,'JHK',NULL,'FICTION',_binary '\0',NULL,'HGJ'),(3,'UIO','GER','FICTION',_binary '\0','FER','GHJ'),(4,'RET','WERT','FICTION',_binary '','WERT','RET'),(5,'YU','RY','FICTION',_binary '\0','RY','YU');
/*!40000 ALTER TABLE `book3` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borrowed`
--

DROP TABLE IF EXISTS `borrowed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `borrowed` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `memberid` bigint DEFAULT NULL,
  `book1id` bigint DEFAULT NULL,
  `book2id` bigint DEFAULT NULL,
  `book3id` bigint DEFAULT NULL,
  `borrow_date` varchar(255) DEFAULT NULL,
  `return_date` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrowed`
--

LOCK TABLES `borrowed` WRITE;
/*!40000 ALTER TABLE `borrowed` DISABLE KEYS */;
/*!40000 ALTER TABLE `borrowed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member3`
--

DROP TABLE IF EXISTS `member3`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member3` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `book1id` bigint DEFAULT NULL,
  `book2id` bigint DEFAULT NULL,
  `book3id` bigint DEFAULT NULL,
  `borrow_date` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `return_date` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member3`
--

LOCK TABLES `member3` WRITE;
/*!40000 ALTER TABLE `member3` DISABLE KEYS */;
INSERT INTO `member3` VALUES (1,2,3,5,'RY','HOP','RY','JOB ');
/*!40000 ALTER TABLE `member3` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-03-31 13:44:33
