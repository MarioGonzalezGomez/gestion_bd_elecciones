-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: elecciones_autonomicas_2019
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `usuario_comunidades`
--

DROP TABLE IF EXISTS `usuario_comunidades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_comunidades` (
  `COD_LOGIN` char(16) NOT NULL,
  `COD_COMUNIDAD` char(2) NOT NULL,
  PRIMARY KEY (`COD_LOGIN`,`COD_COMUNIDAD`),
  CONSTRAINT `FK_usuario_comunidades_usuarios` FOREIGN KEY (`COD_LOGIN`) REFERENCES `usuarios` (`LOGIN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_comunidades`
--

LOCK TABLES `usuario_comunidades` WRITE;
/*!40000 ALTER TABLE `usuario_comunidades` DISABLE KEYS */;
INSERT INTO `usuario_comunidades` VALUES ('andalucia','01'),('aragon','02'),('asturias','03'),('baleares','04'),('canarias','05'),('cantabria','06'),('catalunya','09'),('ceuta','18'),('euskadi','14'),('extremadura','10'),('galicia','11'),('informativos1','02'),('informativos1','03'),('informativos1','04'),('informativos1','05'),('informativos1','06'),('informativos1','07'),('informativos1','08'),('informativos1','10'),('informativos1','12'),('informativos1','13'),('informativos1','15'),('informativos1','16'),('informativos1','99'),('informativos2','02'),('informativos2','03'),('informativos2','04'),('informativos2','05'),('informativos2','06'),('informativos2','07'),('informativos2','08'),('informativos2','10'),('informativos2','12'),('informativos2','13'),('informativos2','15'),('informativos2','16'),('informativos2','99'),('invitado','02'),('invitado','03'),('invitado','04'),('invitado','05'),('invitado','06'),('invitado','07'),('invitado','08'),('invitado','10'),('invitado','12'),('invitado','13'),('invitado','15'),('invitado','16'),('invitado','99'),('leon','08'),('madrid','12'),('mancha','07'),('manual','02'),('manual','03'),('manual','04'),('manual','05'),('manual','06'),('manual','07'),('manual','08'),('manual','10'),('manual','12'),('manual','13'),('manual','15'),('manual','16'),('manual','99'),('melilla','19'),('murcia','15'),('navarra','13'),('rioja','16'),('valencia','17');
/*!40000 ALTER TABLE `usuario_comunidades` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-28  8:02:42
