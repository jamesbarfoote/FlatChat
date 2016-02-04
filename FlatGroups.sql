
-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 19, 2015 at 04:25 AM
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
-- Table structure for table `FlatGroups`
--

CREATE TABLE IF NOT EXISTS `FlatGroups` (
  `GROUP_ID` int(80) NOT NULL AUTO_INCREMENT,
  `GROUP_NAME` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `PASSWORD` varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  `USERS` varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  `SHOPPINGLIST` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CALENDAR` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MONEY` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TODO` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `OWNER_ID` int(80) NOT NULL,
  PRIMARY KEY (`GROUP_ID`),
  UNIQUE KEY `GROUP_NAME` (`GROUP_NAME`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
