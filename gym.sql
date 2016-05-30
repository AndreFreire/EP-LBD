-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Máquina: localhost
-- Data de Criação: 27-Maio-2016 às 13:08
-- Versão do servidor: 5.5.49-0ubuntu0.14.04.1
-- versão do PHP: 5.5.9-1ubuntu4.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de Dados: `gym`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `aparelho`
--

CREATE TABLE IF NOT EXISTS `aparelho` (
  `idAparelho` int(4) DEFAULT NULL,
  `idSalaFK` int(4) DEFAULT NULL,
  `descricao` varchar(100) DEFAULT NULL,
  `estado` varchar(100) DEFAULT NULL,
  KEY `idSalaFK` (`idSalaFK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `aparelho`
--

INSERT INTO `aparelho` (`idAparelho`, `idSalaFK`, `descricao`, `estado`) VALUES
(1, 1, 'Peitoral', 'Defeituoso'),
(2, 1, 'Perna', 'Bom estado'),
(350, 20, 'Abdomen', 'Defeituoso');

-- --------------------------------------------------------

--
-- Estrutura da tabela `aula`
--

CREATE TABLE IF NOT EXISTS `aula` (
  `idAula` int(4) NOT NULL AUTO_INCREMENT,
  `idSalaFK` int(4) DEFAULT NULL,
  `rgMonitorFK` int(4) DEFAULT NULL,
  `descricao` varchar(500) DEFAULT NULL,
  `dia_hora` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idAula`),
  KEY `idSalaFK` (`idSalaFK`),
  KEY `rgMonitorFK` (`rgMonitorFK`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Extraindo dados da tabela `aula`
--

INSERT INTO `aula` (`idAula`, `idSalaFK`, `rgMonitorFK`, `descricao`, `dia_hora`) VALUES
(1, 1, 2, 'Pilates', '2016-02-03 00:00:00'),
(2, 3, 1, 'HIT', '2016-04-10 13:00:00'),
(3, 5, 2, 'Natação', '2016-04-04 14:19:47'),
(4, 4, 1, 'Karate', '2016-03-22 21:00:00'),
(5, 1, 2, 'Aula super pupimpa', '2016-02-03 01:00:00');

-- --------------------------------------------------------

--
-- Estrutura da tabela `experienciaMonitor`
--

CREATE TABLE IF NOT EXISTS `experienciaMonitor` (
  `idExpProfissional` int(4) NOT NULL AUTO_INCREMENT,
  `rgMonitorFK` int(4) DEFAULT NULL,
  `descricao` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idExpProfissional`),
  KEY `rgMonitorFK` (`rgMonitorFK`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Extraindo dados da tabela `experienciaMonitor`
--

INSERT INTO `experienciaMonitor` (`idExpProfissional`, `rgMonitorFK`, `descricao`) VALUES
(1, 1, 'Professor na academia x'),
(2, 2, 'Professora na academia y'),
(3, 3, 'Cordenador na academia x'),
(4, 4, 'Secretária na academia k');

-- --------------------------------------------------------

--
-- Estrutura da tabela `matricula`
--

CREATE TABLE IF NOT EXISTS `matricula` (
  `idAulaFK` int(4) DEFAULT NULL,
  `idSocioFK` int(4) DEFAULT NULL,
  KEY `idAulaFK` (`idAulaFK`),
  KEY `idSocioFK` (`idSocioFK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `matricula`
--

INSERT INTO `matricula` (`idAulaFK`, `idSocioFK`) VALUES
(1, 1),
(1, 2),
(3, 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `monitor`
--

CREATE TABLE IF NOT EXISTS `monitor` (
  `rgMonitor` int(4) NOT NULL AUTO_INCREMENT,
  `nome` varchar(150) DEFAULT NULL,
  `telefone` varchar(15) DEFAULT NULL,
  `titulacao` int(1) DEFAULT NULL,
  PRIMARY KEY (`rgMonitor`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Extraindo dados da tabela `monitor`
--

INSERT INTO `monitor` (`rgMonitor`, `nome`, `telefone`, `titulacao`) VALUES
(1, 'Caio de Lima Granero', '(11)2422-4663', 1),
(2, 'Larissa Moreira', '(11)99999-9999', 0),
(3, 'Azeite de Oliva', '(11)88888-8888', 0),
(4, 'Farinha de Aveia', '(11)88888-8888', 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `pode_lecionar`
--

CREATE TABLE IF NOT EXISTS `pode_lecionar` (
  `rgMonitorFK` int(4) DEFAULT NULL,
  `idAulaFK` int(4) DEFAULT NULL,
  KEY `rgMonitorFK` (`rgMonitorFK`),
  KEY `idAulaFK` (`idAulaFK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `reserva`
--

CREATE TABLE IF NOT EXISTS `reserva` (
  `idSalaFK` int(4) DEFAULT NULL,
  `idSocioFK` int(4) DEFAULT NULL,
  `data` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `usada` tinyint(4) NOT NULL,
  KEY `idSalaFK` (`idSalaFK`),
  KEY `idSocioFK` (`idSocioFK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `reserva`
--

INSERT INTO `reserva` (`idSalaFK`, `idSocioFK`, `data`, `usada`) VALUES
(1, 1, '2016-01-02 12:00:00', 0),
(1, 2, '2016-01-22 20:00:00', 0),
(2, 1, '2016-03-04 09:00:00', 0),
(10, 2, '2016-01-17 12:00:00', 0),
(10, 2, '2016-01-22 20:00:00', 0),
(1, 1, '2016-02-03 01:00:00', 0),
(1, 1, '2016-02-03 01:00:00', 0),
(1, 1, '2016-02-03 01:00:00', 0),
(1, 1, '2016-02-03 01:00:00', 0),
(1, 1, '2016-02-03 01:00:00', 0),
(1, 1, '2016-02-03 01:00:00', 0),
(1, 1, '2016-02-03 01:00:00', 0),
(1, 1, '2016-02-03 01:00:00', 0),
(1, 1, '2016-02-03 01:00:00', 0),
(1, 1, '2016-02-03 01:00:00', 0),
(1, 1, '2016-02-03 01:00:00', 0),
(1, 1, '2016-02-03 01:00:00', 0),
(1, 2, '2016-01-02 12:00:00', 0),
(1, 2, '2016-01-02 12:00:00', 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `sala`
--

CREATE TABLE IF NOT EXISTS `sala` (
  `idSala` int(4) NOT NULL AUTO_INCREMENT,
  `area` int(5) DEFAULT NULL,
  `localizacao` varchar(100) DEFAULT NULL,
  `tipoSala` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idSala`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=21 ;

--
-- Extraindo dados da tabela `sala`
--

INSERT INTO `sala` (`idSala`, `area`, `localizacao`, `tipoSala`) VALUES
(1, 50, 'Primeiro andar', 'Cardio'),
(2, 20, 'Segundo andar', 'Geral'),
(3, 200, 'Primeiro andar', 'Musculação'),
(4, 500, 'Terceiro andar', 'Geral'),
(5, 300, 'Quinto andar', 'Musculação'),
(10, 200, 'Campo 6', 'Geral'),
(20, 200, 'Campo 6', 'Geral');

-- --------------------------------------------------------

--
-- Estrutura da tabela `salaSquash`
--

CREATE TABLE IF NOT EXISTS `salaSquash` (
  `idSalaFK` int(4) DEFAULT NULL,
  `estado` varchar(100) DEFAULT NULL,
  KEY `idSalaFK` (`idSalaFK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `salaSquash`
--

INSERT INTO `salaSquash` (`idSalaFK`, `estado`) VALUES
(10, 'bom estado');

-- --------------------------------------------------------

--
-- Estrutura da tabela `socio`
--

CREATE TABLE IF NOT EXISTS `socio` (
  `idSocio` int(4) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) DEFAULT NULL,
  `endereco` varchar(100) DEFAULT NULL,
  `telefone` varchar(15) DEFAULT NULL,
  `profissao` varchar(100) DEFAULT NULL,
  `dadosBancarios` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idSocio`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Extraindo dados da tabela `socio`
--

INSERT INTO `socio` (`idSocio`, `nome`, `endereco`, `telefone`, `profissao`, `dadosBancarios`) VALUES
(1, 'Copão', 'Rua do Copão', '(11)1234-5678', 'Desenvolvedor', '7891893821'),
(2, 'Potão', 'Rua do Potão', '(11)8765-4321', 'Dançarino', '283179273');

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `aparelho`
--
ALTER TABLE `aparelho`
  ADD CONSTRAINT `aparelho_ibfk_1` FOREIGN KEY (`idSalaFK`) REFERENCES `sala` (`idSala`);

--
-- Limitadores para a tabela `aula`
--
ALTER TABLE `aula`
  ADD CONSTRAINT `aula_ibfk_1` FOREIGN KEY (`idSalaFK`) REFERENCES `sala` (`idSala`),
  ADD CONSTRAINT `aula_ibfk_2` FOREIGN KEY (`rgMonitorFK`) REFERENCES `monitor` (`rgMonitor`);

--
-- Limitadores para a tabela `experienciaMonitor`
--
ALTER TABLE `experienciaMonitor`
  ADD CONSTRAINT `experienciaMonitor_ibfk_1` FOREIGN KEY (`rgMonitorFK`) REFERENCES `monitor` (`rgMonitor`);

--
-- Limitadores para a tabela `matricula`
--
ALTER TABLE `matricula`
  ADD CONSTRAINT `matricula_ibfk_1` FOREIGN KEY (`idAulaFK`) REFERENCES `aula` (`idAula`),
  ADD CONSTRAINT `matricula_ibfk_2` FOREIGN KEY (`idSocioFK`) REFERENCES `socio` (`idSocio`);

--
-- Limitadores para a tabela `pode_lecionar`
--
ALTER TABLE `pode_lecionar`
  ADD CONSTRAINT `pode_lecionar_ibfk_1` FOREIGN KEY (`rgMonitorFK`) REFERENCES `monitor` (`rgMonitor`),
  ADD CONSTRAINT `pode_lecionar_ibfk_2` FOREIGN KEY (`idAulaFK`) REFERENCES `aula` (`idAula`);

--
-- Limitadores para a tabela `reserva`
--
ALTER TABLE `reserva`
  ADD CONSTRAINT `reserva_ibfk_1` FOREIGN KEY (`idSalaFK`) REFERENCES `sala` (`idSala`),
  ADD CONSTRAINT `reserva_ibfk_2` FOREIGN KEY (`idSocioFK`) REFERENCES `socio` (`idSocio`);

--
-- Limitadores para a tabela `salaSquash`
--
ALTER TABLE `salaSquash`
  ADD CONSTRAINT `salaSquash_ibfk_1` FOREIGN KEY (`idSalaFK`) REFERENCES `sala` (`idSala`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
