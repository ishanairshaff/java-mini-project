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
-- Table structure for table `final_practical`
--

DROP TABLE IF EXISTS `final_practical`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `final_practical` (
  `final_practical_id` int NOT NULL AUTO_INCREMENT,
  `course_id` int NOT NULL,
  `student_id` int NOT NULL,
  `marks` decimal(5,2) NOT NULL,
  PRIMARY KEY (`final_practical_id`),
  KEY `course_id` (`course_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `final_practical_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`),
  CONSTRAINT `final_practical_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `final_practical`
--

LOCK TABLES `final_practical` WRITE;
/*!40000 ALTER TABLE `final_practical` DISABLE KEYS */;
INSERT INTO `final_practical` VALUES (1,1,4,87.00),(2,1,5,79.00),(3,1,6,92.00),(4,1,9,82.50),(5,1,10,86.00),(6,1,14,76.00),(7,1,15,84.50),(8,1,19,83.00),(9,1,20,80.00),(10,1,24,90.00),(11,1,25,87.00),(12,1,26,82.00),(13,1,27,81.00),(14,1,28,88.50),(15,1,29,84.00),(16,1,30,82.00),(17,1,31,77.50),(18,1,32,85.00),(19,1,33,80.50),(20,1,34,89.00),(21,1,35,73.50),(22,1,36,76.00),(23,1,37,75.50),(24,1,38,88.50),(25,2,4,84.50),(26,2,5,78.00),(27,2,6,90.50),(28,2,9,77.00),(29,2,10,88.00),(30,2,14,73.50),(31,2,15,81.00),(32,2,19,80.00),(33,2,20,78.50),(34,2,24,89.00),(35,2,25,83.00),(36,2,26,80.50),(37,2,27,79.00),(38,2,28,85.00),(39,2,29,90.50),(40,2,30,84.00),(41,2,31,76.50),(42,2,32,81.50),(43,2,33,78.00),(44,2,34,87.00),(45,2,35,76.00),(46,2,36,79.50),(47,2,37,75.50),(48,2,38,88.00),(49,3,4,83.00),(50,3,5,75.50),(51,3,6,88.00),(52,3,9,72.00),(53,3,10,84.50),(54,3,14,71.00),(55,3,15,77.50),(56,3,19,74.00),(57,3,20,78.00),(58,3,24,80.50),(59,3,25,75.00),(60,3,26,79.50),(61,3,27,77.00),(62,3,28,82.00),(63,3,29,80.00),(64,3,30,83.00),(65,3,31,71.50),(66,3,32,78.50),(67,3,33,74.50),(68,3,34,85.00),(69,3,35,70.50),(70,3,36,71.00),(71,3,37,70.00),(72,3,38,86.00),(73,4,4,88.50),(74,4,5,82.00),(75,4,6,87.50),(76,4,9,80.00),(77,4,10,84.00),(78,4,14,78.50),(79,4,15,84.00),(80,4,19,79.00),(81,4,20,81.50),(82,4,24,91.00),(83,4,25,79.00),(84,4,26,85.00),(85,4,27,81.00),(86,4,28,86.00),(87,4,29,84.50),(88,4,30,88.00),(89,4,31,75.00),(90,4,32,80.00),(91,4,33,78.00),(92,4,34,89.50),(93,4,35,74.00),(94,4,36,78.00),(95,4,37,75.50),(96,4,38,87.50),(97,5,4,85.00),(98,5,5,79.00),(99,5,6,84.00),(100,5,9,73.00),(101,5,10,81.50),(102,5,14,75.50),(103,5,15,79.00),(104,5,19,77.00),(105,5,20,80.00),(106,5,24,85.50),(107,5,25,80.00),(108,5,26,78.00),(109,5,27,74.50),(110,5,28,82.50),(111,5,29,84.00),(112,5,30,86.00),(113,5,31,74.50),(114,5,32,80.00),(115,5,33,76.50),(116,5,34,86.50),(117,5,35,77.00),(118,5,36,78.00),(119,5,37,75.00),(120,5,38,88.00);
/*!40000 ALTER TABLE `final_practical` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-27 21:08:49
