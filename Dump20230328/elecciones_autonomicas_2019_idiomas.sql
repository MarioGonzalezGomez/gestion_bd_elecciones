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
-- Table structure for table `idiomas`
--

DROP TABLE IF EXISTS `idiomas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `idiomas` (
  `ID` int NOT NULL,
  `castellano` text,
  `catalan` text,
  `vasco` text,
  `gallego` text,
  `valenciano` text,
  `mallorquin` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `idiomas`
--

LOCK TABLES `idiomas` WRITE;
/*!40000 ALTER TABLE `idiomas` DISABLE KEYS */;
INSERT INTO `idiomas` VALUES (0,'castellano','catalan','vasco','gallego','valenciano','mallorquin'),(1,'participación','participació','Partehartzea','Participación','Participació','Participació'),(2,'Mapa de Participación','Mapa de Participació','Partehartze Mapa','Mapa de Participación','Mapa de Participació','Mapa de Participació'),(3,'Otros','Altres','Besteak','Outros ','Altres','Altres'),(4,'primer avance de participación','primer avançament de participació','Lehen Aurrerapena','Primeiro Avance','Primer Avanç','Primer Avanç'),(5,'segundo avance de participación','segon avançament de participació','Bigarren Aurrerapena','Segundo Avance','Segon Avanç','Segon Avanç'),(6,'escaños','escons','Eserlekuak','Escanos ','Escons','Escons'),(7,'Votos','Vots','Botoak','Votos ','Vots','Vots'),(8,'Datos Oficiales','Dades oficials','EMAITZA OFIZIALAK','Datos Oficiais ','Dades Oficials','Dades oficials'),(9,'Arco Parlamentario','Arc Parlamentari','LEGEBILTZARRA','Arco Parlamentario ','Arc Parlamentari','Arc Parlamentari'),(10,'Mayoría Absoluta','Majoria Absoluta','GEHIENGO OSOA','Maioría Absoluta','Majoria Absoluta','Majoria Absoluta'),(11,'Mapa de Mayorías','Mapa de Majories','Gehiengoen Mapa','Mapa de Maiorías ','Mapa de Majories','Mapa de Majories'),(12,'Municipios','Municipis','UDALAK','Concellos','Municipis','Municipis'),(13,'Concejales','Regidors','ZINEGOTZIAK','Concelleiros ','Concejals','Regidors'),(14,'Autonómicas','Autonòmiques','AUTONOMIKOAK','Eleccións Autonómicas ','Eleccions Autonòmiques','Eleccions Autonòmiques'),(15,'Municipales','Municipals','UDAL HAUTESKUNDEAK','Eleccións Municipais ','Eleccions Municipals','Eleccions Municipals'),(16,'Sondeo','Sondeig','Inkesta','Sondaxe','Sondeig','Sondeig'),(17,'Ayuntamiento de','Ajuntament de','Udalak','Concello de','Ajuntament de','Ajuntament'),(18,'Otros Medios','Altres Mitjans','Beste Komunikabideak','Outros Medios','Altres Mitjans','Altres Mitjans'),(19,'Pactos de Gobierno','Pactes de Govern','GOBERNU AKORDIOAK','Pactos de Goberno ','Pactes de Govern','Pactes de Govern'),(20,'escrutado','escrutat','Zenbatutakoa','Escrutado','Escrutat','Escrutat'),(21,'Comunidad','Catalunya','Euskadi','Galicia','Comunitat Valenciana','Illes Balears'),(22,'Provincia 1','Barcelona','Araba','A Coruña','Alacant','Mallorca'),(23,'Provincia 2','Girona','Gipuzkoa','Lugo','Castelló','Menorca'),(24,'Provincia 3','Lleida','Bizkaia','Ourense','València','Eivissa'),(25,'Provincia 4','Tarragona','','Pontevedra','','Formentera');
/*!40000 ALTER TABLE `idiomas` ENABLE KEYS */;
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
