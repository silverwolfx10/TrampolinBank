CREATE DATABASE  IF NOT EXISTS `trampolin_bank` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `trampolin_bank`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: trampolin_bank
-- ------------------------------------------------------
-- Server version	5.6.16

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
-- Table structure for table `agenda`
--

DROP TABLE IF EXISTS `agenda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agenda` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `valor` float NOT NULL,
  `conta_id` int(11) DEFAULT NULL,
  `descricao` varchar(255) NOT NULL,
  `agendado` datetime NOT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agenda`
--

LOCK TABLES `agenda` WRITE;
/*!40000 ALTER TABLE `agenda` DISABLE KEYS */;
INSERT INTO `agenda` VALUES (1,100,1,'Transferência para5678 5678','2014-10-28 00:00:00','2014-10-28 13:00:30'),(2,100,1,'Pagamento do Boleto: 1234','2014-10-28 00:00:00','2014-10-28 14:08:18');
/*!40000 ALTER TABLE `agenda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conta`
--

DROP TABLE IF EXISTS `conta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) NOT NULL,
  `agencia` varchar(4) NOT NULL,
  `conta` varchar(5) NOT NULL,
  `tipo_conta` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `saldo_poupanca` float DEFAULT NULL,
  `saldo_corrente` float DEFAULT NULL,
  `status` tinyint(4) DEFAULT '1',
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_conta_2_idx` (`id_usuario`),
  KEY `fk_conta_1_idx` (`tipo_conta`),
  CONSTRAINT `fk_conta_1` FOREIGN KEY (`tipo_conta`) REFERENCES `tipo_conta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_conta_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conta`
--

LOCK TABLES `conta` WRITE;
/*!40000 ALTER TABLE `conta` DISABLE KEYS */;
INSERT INTO `conta` VALUES (1,2,'1234','1234',1,'1234',0,4833,1,'2014-10-26 00:00:00','2014-10-29 05:47:54'),(2,1,'5678','5678',1,'5678',10000,7100,1,'2014-10-26 00:00:00','2014-10-29 02:31:04'),(3,3,'11','11',2,'11',10967,0,1,'2014-10-26 00:00:00','2014-10-29 05:47:54'),(4,2,'1234','1234',2,'1234',2000,0,1,'2014-10-26 00:00:00',NULL);
/*!40000 ALTER TABLE `conta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favoritos`
--

DROP TABLE IF EXISTS `favoritos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favoritos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usuario_id` int(11) DEFAULT NULL,
  `apelido` varchar(255) DEFAULT NULL,
  `cpf` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `conta_id` int(11) DEFAULT NULL,
  `tipo_conta` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favoritos`
--

LOCK TABLES `favoritos` WRITE;
/*!40000 ALTER TABLE `favoritos` DISABLE KEYS */;
INSERT INTO `favoritos` VALUES (4,2,'Padre Marcelo','1234567890','2014-10-28 02:48:20',2,2),(5,3,'allan','1234','2014-10-29 05:47:41',1,1);
/*!40000 ALTER TABLE `favoritos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movimentacao`
--

DROP TABLE IF EXISTS `movimentacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movimentacao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `conta_origem_id` int(11) NOT NULL,
  `descricao` varchar(255) NOT NULL,
  `valor` float NOT NULL,
  `created_at` datetime NOT NULL,
  `saldo` float NOT NULL,
  `conta_destino_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_movimentacao_1_idx` (`conta_origem_id`),
  CONSTRAINT `fk_movimentacao_1` FOREIGN KEY (`conta_origem_id`) REFERENCES `conta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimentacao`
--

LOCK TABLES `movimentacao` WRITE;
/*!40000 ALTER TABLE `movimentacao` DISABLE KEYS */;
INSERT INTO `movimentacao` VALUES (1,1,'Transferência para 5789',1500,'2014-08-15 00:00:00',10000,2),(2,1,'Transferência para1234 1234',100,'2014-10-28 04:56:09',9500,2),(3,1,'Transferência para1234 1234',100,'2014-10-28 04:59:23',4700,2),(4,1,'Transferência para1234 1234',100,'2014-10-28 05:03:59',9400,2),(5,1,'Transferência para1234 1234',100,'2014-10-28 05:04:48',9400,2),(6,1,'Transferência para1234 1234',100,'2014-10-28 05:05:54',4500,2),(7,1,'Transferência para1234 1234',100,'2014-10-28 05:07:20',9300,2),(8,1,'Transferência para1234 1234',100,'2014-10-28 05:08:05',9300,2),(9,1,'Transferência para1234 1234',100,'2014-10-28 05:21:20',4300,2),(10,1,'Transferência para1234 1234',100,'2014-10-28 05:22:15',4300,2),(11,1,'Transferência para1234 1234',100,'2014-10-28 05:24:40',9100,2),(12,1,'Transferência para1234 1234',100,'2014-10-28 05:25:47',9100,2),(13,1,'Transferência para1234 1234',100,'2014-10-28 05:26:22',4100,2),(14,1,'Transferência para1234 1234',100,'2014-10-28 05:27:34',4100,2),(15,1,'Transferência para1234 1234',100,'2014-10-28 05:28:12',4100,2),(16,1,'Transferência para1234 1234',100,'2014-10-28 05:36:25',8800,2),(17,1,'Transferência para1234 1234',100,'2014-10-28 05:39:39',4000,2),(18,1,'Transferência para1234 1234',100,'2014-10-28 05:55:53',8700,2),(19,1,'Transferência para5678 5678',100,'2014-10-28 12:16:47',3900,2),(20,1,'Transferência para5678 5678',100,'2014-10-28 12:23:46',3900,2),(22,1,'Pagamento do Boleto: 12345678901234567890',100,'2014-10-28 13:58:08',7500,2),(23,1,'Pagamento do Boleto: 09876543210987654321',200,'2014-10-28 21:29:48',7100,2),(24,1,'Pagamento do Boleto: 17038463825638562389',300,'2014-10-29 02:19:41',6800,0),(25,1,'Transferência para5678 5678',500,'2014-10-29 02:31:11',4800,2),(26,3,'Transferência para1234 1234',33,'2014-10-29 05:47:54',10967,1),(27,1,'Recebido de 11 11',33,'2014-10-29 05:47:54',4833,3);
/*!40000 ALTER TABLE `movimentacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operacao`
--

DROP TABLE IF EXISTS `operacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operacao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(45) DEFAULT NULL,
  `taxa` float DEFAULT NULL,
  `prazo` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operacao`
--

LOCK TABLES `operacao` WRITE;
/*!40000 ALTER TABLE `operacao` DISABLE KEYS */;
INSERT INTO `operacao` VALUES (1,'DOC',1.5,2),(2,'TED',5,4);
/*!40000 ALTER TABLE `operacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_conta`
--

DROP TABLE IF EXISTS `tipo_conta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_conta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_conta`
--

LOCK TABLES `tipo_conta` WRITE;
/*!40000 ALTER TABLE `tipo_conta` DISABLE KEYS */;
INSERT INTO `tipo_conta` VALUES (1,'Corrente'),(2,'Poupanca');
/*!40000 ALTER TABLE `tipo_conta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome_completo` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `idade` int(11) DEFAULT NULL,
  `status` tinyint(4) DEFAULT '1',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'dfdf',NULL,56,1,'2014-10-15 19:15:49',NULL),(2,'ALLAN OLIVEIRA','ALLANCOSTA@PLUSOFT.COM.BR',24,1,'2014-10-26 19:15:49',NULL),(3,'joao','joao@gmail.com',18,1,'2014-10-26 19:15:49',NULL);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-10-29  6:14:26
