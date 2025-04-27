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
-- Table structure for table `allusers`
--

DROP TABLE IF EXISTS `allusers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `allusers` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `profile_picture` varchar(255) DEFAULT NULL,
  `user_type` enum('admin','lecturer','technical_officer','student') NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `allusers`
--

LOCK TABLES `allusers` WRITE;
/*!40000 ALTER TABLE `allusers` DISABLE KEYS */;
INSERT INTO `allusers` VALUES (1,'AD1001','ad1001@123','Sadun','wickramasinghe','admin@ruh.ac.lk','0771234001',NULL,'admin'),(2,'LC1002','lc1002@123','Subash','jayasinghe','subash@ictec.ruh.ac.lk','0771234002',NULL,'lecturer'),(3,'LC1003','lc1003@123','Mr.P.H.P.Nuwan','Laksiri','phpnlaksiri@ictec.ruh.ac.lk','0771234003',NULL,'lecturer'),(4,'TG1340','tg1340@123','Wijewardhana','','vihangayasitha@gmail.com','0771234004',NULL,'student'),(5,'TG1341','tg1341@123','Chamika','','sahanchamika@gmail.com','0771234005',NULL,'student'),(6,'TG1342','tg1342@123','Harshana','','harshana@gmail.com','0771234006',NULL,'student'),(7,'LC1004','lc1004@123','Navoda','','navodanherath@mtec.ruh.ac.lk','0771234007',NULL,'lecturer'),(8,'TO1005','to1005@123','Sameera','','sameera@fot.ruh.ac.lk','0771234008',NULL,'technical_officer'),(9,'TG1343','tg1343@123','Dharshika','','tdharshika2002@gmail.com','0771234009',NULL,'student'),(10,'TG1344','tg1344@123','Shashini','','shashinikaushalya557@gmail.com','0771234010',NULL,'student'),(11,'LC1006','lc1006@123','Niranjan','','niranjan@btec.ruh.ac.lk','0771234011',NULL,'lecturer'),(12,'LC1007','lc1007@123','Sanjeewani','','sanjeewani@fot.ruh.ac.lk','0771234012',NULL,'lecturer'),(13,'TO1008','to1008@123','Umandi','','umandi@fot.ruh.ac.lk','0771234013',NULL,'technical_officer'),(14,'TG1345','tg1345@123','Jayarathna','','jayarathnahpj23@gmail.com','0771234014',NULL,'student'),(15,'TG1346','tg1346@123','Sankalpana','','gaveshi.sankalpana@gmail.com','0771234015',NULL,'student'),(16,'LC1009','lc1009@123','Ms.Chanduni','Gamage','chanduni@ictec.ruh.ac.lk','0771234016',NULL,'lecturer'),(17,'LC1010','lc1010@123','Naveen','','naveen@fot.ruh.ac.lk','0771234017',NULL,'lecturer'),(18,'TO1011','to1011@123','Kumara','','kumara@fot.ruh.ac.lk','0771234018',NULL,'technical_officer'),(19,'TG1347','tg1347@123','Randika','','randikadhyan@gmail.com','0771234019',NULL,'student'),(20,'TG1348','tg1348@123','Fernando','','piumikafernando07@gmail.com','0771234020',NULL,'student'),(21,'TO1012','to1012@123','Madushan','','madushan@fot.ruh.ac.lk','0771234021',NULL,'technical_officer'),(22,'TO1013','to1013@123','Sahani','','shahani@fot.ruh.ac.lk','0771234022',NULL,'technical_officer'),(23,'TO1014','to1014@123','Chamila','','chamila@fot.ruh.ac.lk','0771234023',NULL,'technical_officer'),(24,'TG1349','tg1349@123','Kusal','','kusal12@gmail.com','0771234024',NULL,'student'),(25,'TG1350','tg1350@123','Nuwani','','nuwani78@gmail.com','0771234025',NULL,'student'),(26,'TG1351','tg1351@123','Sandun','','sadun90@gmail.com','0771234026',NULL,'student'),(27,'TG1352','tg1352@123','Chathuranga','','chathuranga@gmail.com','0771234027',NULL,'student'),(28,'TG1353','tg1353@123','Rumeshika','','rumeshika@gmail.com','0771234028',NULL,'student'),(29,'TG1354','tg1354@123','Piyumi','','piyumi@gmail.com','0771234029',NULL,'student'),(30,'TG1355','tg1355@123','Minoka','','iduwaraminoka@gmail.com','0771234030',NULL,'student'),(31,'TG1356','tg1356@123','Nishadini','','chavindya12@gmail.com','0771234031',NULL,'student'),(32,'TG1357','tg1357@123','Sanuka','','sanuka123@gmail.com','0771234032',NULL,'student'),(33,'TG1358','tg1358@123','Gamage','','amali@gmail.com','0771234033',NULL,'student'),(34,'TG1359','tg1359@123','Sewwandi','','sewwandi1225@gmail.com','0771234034',NULL,'student'),(35,'TG1360','tg1360@123','Dishan','','dishans@gmail.com','0771234035',NULL,'student'),(36,'TG1361','tg1361@123','Aasha','','asha200109@gmail.com','0771234036',NULL,'student'),(37,'TG1362','tg1362@123','Sithusha','','sithusha123@gmail.com','0771234037',NULL,'student'),(38,'TG1363','tg1363@123','Umaya','','chalaniumaya@gmail.com','0771234038',NULL,'student'),(39,'LC1011','lc1011@123','Mr.Shashitha','Lakal','lakal@gmail.com','0771234039',NULL,'lecturer'),(40,'LC1012','lc1011@123','Ms.Akila','Brahmana','akila@gmail.com','0771234040',NULL,'lecturer'),(41,'LC1013','lc1011@123','Ridmi','','ridmi@gmail.com','0771234041',NULL,'lecturer');
/*!40000 ALTER TABLE `allusers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-27 21:08:50
