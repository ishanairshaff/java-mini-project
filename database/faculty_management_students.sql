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
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students` (
  `student_id` int NOT NULL,
  `registration_number` varchar(20) NOT NULL,
  `batch` varchar(20) NOT NULL,
  `degree_program` varchar(100) NOT NULL,
  PRIMARY KEY (`student_id`),
  UNIQUE KEY `registration_number` (`registration_number`),
  CONSTRAINT `students_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `allusers` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (4,'TG1340','Batch A','BSc Computer Science'),(5,'TG1341','Batch A','BSc Computer Science'),(6,'TG1342','Batch A','BSc Computer Science'),(9,'TG1343','Batch A','BSc Computer Science'),(10,'TG1344','Batch A','BSc Computer Science'),(14,'TG1345','Batch A','BSc Computer Science'),(15,'TG1346','Batch A','BSc Computer Science'),(19,'TG1347','Batch A','BSc Computer Science'),(20,'TG1348','Batch A','BSc Computer Science'),(24,'TG1349','Batch A','BSc Computer Science'),(25,'TG1350','Batch A','BSc Computer Science'),(26,'TG1351','Batch A','BSc Computer Science'),(27,'TG1352','Batch A','BSc Computer Science'),(28,'TG1353','Batch A','BSc Computer Science'),(29,'TG1354','Batch A','BSc Computer Science'),(30,'TG1355','Batch A','BSc Computer Science'),(31,'TG1356','Batch A','BSc Computer Science'),(32,'TG1357','Batch A','BSc Computer Science'),(33,'TG1358','Batch A','BSc Computer Science'),(34,'TG1359','Batch A','BSc Computer Science'),(35,'TG1360','Batch A','BSc Computer Science'),(36,'TG1361','Batch A','BSc Computer Science'),(37,'TG1362','Batch A','BSc Computer Science'),(38,'TG1363','Batch A','BSc Computer Science');
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-27 21:08:53
