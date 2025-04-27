-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: faculty_management
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `medical`
--

DROP TABLE IF EXISTS `medical`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medical` (
  `Medical_id` char(3) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `Description` varchar(40) DEFAULT NULL,
  `Sub_date` date DEFAULT NULL,
  `State` varchar(30) DEFAULT NULL,
  `c_code` varchar(20) NOT NULL,
  `c_type` char(1) NOT NULL,
  `cut_lec_hour` int DEFAULT NULL,
  PRIMARY KEY (`Medical_id`,`c_code`,`c_type`),
  KEY `username` (`username`),
  KEY `c_code` (`c_code`),
  CONSTRAINT `medical_ibfk_1` FOREIGN KEY (`username`) REFERENCES `allusers` (`username`),
  CONSTRAINT `medical_ibfk_2` FOREIGN KEY (`c_code`) REFERENCES `courses` (`course_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medical`
--

LOCK TABLES `medical` WRITE;
/*!40000 ALTER TABLE `medical` DISABLE KEYS */;
INSERT INTO `medical` VALUES ('M01','TG1340','Matara hospital','2025-04-04','approval','ICT2152','T',4),('M02','TG1341','Ruhunugama hospital','2025-03-27','approval','ICT2122','T',4),('M02','TG1341','Ruhunugama hospital','2025-03-27','approval','ICT2142','P',4),('M03','TG1341','Ruhunugama hospital','2025-04-10','not approval','ICT2122','T',4),('M03','TG1341','Ruhunugama hospital','2025-04-10','not approval','ICT2142','P',4),('M04','TG1342','Kamburupitiya hospital','2025-03-13','approval','ICT2142','P',4),('M05','TG1342','Kamburupitiya hospital','2025-02-18','approval','ICT2133','P',2),('M06','TG1347','Dickoya hospital','2025-01-30','approval','ICT2142','P',4),('M07','TG1347','Dickoya hospital','2025-03-20','approval','ICT2122','T',4),('M08','TG1347','Karapitiya hospital','2025-04-04','approval','ICT2152','T',4),('M09','TG1353','Matara hospital','2025-03-28','not approval','ICT2152','T',4),('M10','TG1353','Matara hospital','2025-04-25','approval','ICT2152','T',4),('M11','TG1356','Ruhunugama hospital','2025-01-30','approval','ICT2122','T',4),('M12','TG1356','Ruhunugama hospital','2025-03-20','not approval','ICT2122','T',4),('M13','TG1360','Matara hospital','2025-03-20','approval','ICT2122','T',4),('M13','TG1360','Matara hospital','2025-03-20','approval','ICT2142','P',4),('M14','TG1362','Karapitiya hospital','2025-03-10','approval','ICT2113','P',4),('M14','TG1362','Karapitiya hospital','2025-03-10','approval','ICT2113','T',2),('M15','TG1363','Ruhunugama hospital','2025-03-10','not approval','ICT2113','T',4),('M16','TG1363','Matara hospital','2025-02-11','approval','ICT2133','T',4),('M17','TG1363','Matara hospital','2025-02-14','approval','ICT2152','T',4);
/*!40000 ALTER TABLE `medical` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-27 21:08:52
