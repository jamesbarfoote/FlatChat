
-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 19, 2015 at 04:26 AM
-- Server version: 10.0.20-MariaDB
-- PHP Version: 5.2.17

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `u205845314_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `Users`
--

CREATE TABLE IF NOT EXISTS `Users` (
  `User_ID` int(80) NOT NULL AUTO_INCREMENT,
  `Email` varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  `Password` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `Pic` varchar(80) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FlatGroup_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`User_ID`),
  UNIQUE KEY `ID` (`User_ID`),
  UNIQUE KEY `Email_2` (`Email`),
  KEY `Email` (`Email`,`Password`,`Pic`),
  KEY `Group` (`FlatGroup_name`),
  KEY `Pic` (`Pic`),
  KEY `Group_2` (`FlatGroup_name`),
  FULLTEXT KEY `Group_3` (`FlatGroup_name`),
  FULLTEXT KEY `Group_4` (`FlatGroup_name`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=34 ;

--
-- Dumping data for table `Users`
--

INSERT INTO `Users` (`User_ID`, `Email`, `Password`, `Pic`, `FlatGroup_name`) VALUES
(1, 'jimmy2174@gmail.com', 'password23', 'item1, item2, item3, item4', 'admin'),
(12, 'jimmy2174@gmail', 'pass', NULL, NULL),
(7, 'time', 'machine', NULL, NULL),
(32, 'a', 'b', NULL, NULL),
(33, 'ab', 'b', NULL, NULL);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
