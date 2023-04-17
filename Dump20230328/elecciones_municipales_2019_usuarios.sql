-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: elecciones_municipales_2019
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
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `LOGIN` char(16) NOT NULL,
  `passw` char(41) NOT NULL,
  `descripcion` char(50) NOT NULL,
  `perfil` int NOT NULL DEFAULT '0',
  `activo` int NOT NULL DEFAULT '0',
  `input_upd_principal` int NOT NULL DEFAULT '0',
  `input_upd_historicos` int NOT NULL DEFAULT '0',
  `input_upd_sondeo` int NOT NULL DEFAULT '0',
  `input_upd_muestreo` int NOT NULL DEFAULT '0',
  `input_upd_avance1` int NOT NULL DEFAULT '0',
  `input_upd_avance2` int NOT NULL DEFAULT '0',
  `input_upd_oficiales` int NOT NULL DEFAULT '0',
  `input_upd_medios` int NOT NULL DEFAULT '0',
  `input_web_captura` int NOT NULL DEFAULT '0',
  `input_maquina` char(50) DEFAULT NULL,
  `input_version` char(50) DEFAULT NULL,
  `input_fecha` char(14) DEFAULT NULL,
  `input_fecha_fin` char(14) DEFAULT NULL,
  `input_mensaje` text,
  `input_upd_avance3` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`LOGIN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES ('andalucia','7a5aa869fdeb24ca5ec7b5a7e0ec124fcf2446d6','Usuario Andalucía',1,1,1,1,1,1,1,1,1,0,0,'IPF702','22.0.5.0','20190526211254','20190526210713','refresh;',0),('aragon','27486579536f90a8b04c0143747cd9a57d0aefa4','Usuario Aragón',1,1,1,1,1,1,1,1,1,0,0,NULL,NULL,NULL,NULL,'refresh;',0),('asturias','3a70122aa1d0a5629fccb7896e6612e2f2ec1182','Usuario Asturias',1,1,1,1,1,1,1,1,1,0,0,NULL,NULL,NULL,'20190527033155','refresh;',0),('automatizacion','c68b97224ad1b10009fce1ccde61eb3a4817000b','Usuario administrador del sistema',2,1,0,0,0,0,0,0,0,0,0,NULL,NULL,NULL,'20190526190902','refresh;',0),('baleares','8a3d3b7fb2aa6a5528d85cd41a9e964a02a4893e','Usuario Baleares',1,1,1,1,1,1,1,1,1,0,0,'IPF720','22.0.5.0','20190526204138','20190526202858','refresh;',0),('canarias','a05430faad1b21ec9bb6bf56a298fa7d2671c524','Usuario Canarias',1,1,1,1,1,1,1,1,1,0,0,'IPF721','22.0.5.0','20190526193858','20190526180205','refresh;',0),('cantabria','477e8c8955de4a49fd8bfc4ae1204a6e4abb8358','Usuario Cantabria',1,1,1,1,1,1,1,1,1,0,0,'IPF736','22.0.5.0','20190526203954','20190527024340','refresh;',0),('catalunya','66bd8c569ce43ba94f065646e12f2e5bd63d1fde','Usuario Cataluña',1,1,1,1,1,1,1,1,1,0,0,'IPF152_E338238','22.0.5.0','20010103081519','20190527012605','refresh;',0),('ceuta','cca2e719040e5efc3c9ac980af465cf341f8fe6e','Usuario Ceuta',1,1,1,1,1,1,1,1,1,0,0,NULL,NULL,NULL,NULL,'refresh;',0),('euskadi','2dbd8a90e31ffaf84cc74d0b86e1741381e6a8b4','Usuario País Vasco',1,1,1,1,1,1,1,1,1,0,0,'IPF704','22.0.5.0','20190526211331','20190526211025','refresh;',0),('extremadura','6db0626a2b39495334d10446f35f612578142909','Usuario Extremadura',1,1,1,1,1,1,1,1,1,0,0,'IPF129','22.0.5.0','20190526201235','20190526190812','refresh;',0),('galicia','e98c71171b56873522206fef2de6d1ddd67eada6','Usuario Galicia',1,1,1,1,1,1,1,1,1,0,0,'IPF_155','22.0.5.0','20190526233829','20190526200648','refresh;',0),('informativos1','da39a3ee5e6b4b0d3255bfef95601890afd80709','Usuario entrada manual informativos',1,1,1,1,1,1,1,1,1,0,0,'E420585','22.0.5.0','20190526175406','20190527013005','refresh;',0),('informativos2','da39a3ee5e6b4b0d3255bfef95601890afd80709','Usuario entrada manual informativos',1,1,1,1,1,1,1,1,1,0,0,'E420583','22.0.5.0','20190526210840',NULL,'refresh;',0),('invitado','0c0438a2d770051789cbafdd47fe25a9d7f74587','Usuario invitado',0,1,0,0,0,0,0,0,0,0,0,'E325608','22.0.5.0','20190526195828','20190527021730','refresh;',0),('leon','9cfbf3d556f429961395795d2e4c6a995eb2491d','Usuario Castilla y León',1,1,1,1,1,1,1,1,1,0,0,NULL,NULL,NULL,NULL,'refresh;',0),('madrid','9f96d80666ebc05e1ab60d1627da811d6652f2d5','Usuario Madrid',1,1,1,1,1,1,1,1,1,0,0,NULL,NULL,NULL,NULL,'refresh;',0),('mancha','6bd1fbb8b0c17b614585b8419dc173cb5ad48f2b','Usuario Castilla - La Mancha',1,1,1,1,1,1,1,1,1,0,0,NULL,NULL,NULL,NULL,'refresh;',0),('manual','da39a3ee5e6b4b0d3255bfef95601890afd80709','Usuario captura manual',1,1,1,1,1,1,1,1,1,1,0,NULL,NULL,NULL,'20190527024655','refresh;',0),('melilla','6c62ce8eb264ab69925f3b30d279bccefee732d3','Usuario Melilla',1,1,1,1,1,1,1,1,1,0,0,NULL,NULL,NULL,NULL,'refresh;',0),('murcia','4bbd344169e2a98595fcd0dd1f73f4ba118ca06d','Usuario Murcia',1,1,1,1,1,1,1,1,1,0,0,'IPF705','22.0.5.0','20190526213202','20190526212931','refresh;',0),('navarra','4d6abd48841b2894cfb97af21e7ff3686e35f2e2','Usuario Navarra',1,1,1,1,1,1,1,1,1,0,0,NULL,NULL,NULL,NULL,'refresh;',0),('rioja','5ff32a6f471a47add882d3f4d0308e2a83c0d5dc','Usuario La Rioja',1,1,1,1,1,1,1,1,1,0,0,'IPF143','22.0.5.0','20190526213818','20190526213630','refresh;',0),('valencia','518b84042929476c177354bb2a478280157c06a1','Usuario Valencia',1,1,1,1,1,1,1,1,1,0,0,'IPF727','22.0.5.0','20190526205037','20190526210931','refresh;',0);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-28  8:02:43
