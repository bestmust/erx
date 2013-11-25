-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 25, 2013 at 03:53 PM
-- Server version: 5.5.32
-- PHP Version: 5.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `1541215_android`
--
CREATE DATABASE IF NOT EXISTS `1541215_android` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `1541215_android`;

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
CREATE TABLE IF NOT EXISTS `address` (
  `address_id` int(15) NOT NULL AUTO_INCREMENT,
  `house_no` varchar(50) COLLATE latin1_general_ci NOT NULL,
  `address_line1` varchar(100) COLLATE latin1_general_ci DEFAULT NULL,
  `address_line2` varchar(100) COLLATE latin1_general_ci DEFAULT NULL,
  `city` varchar(20) COLLATE latin1_general_ci DEFAULT NULL,
  `state` varchar(20) COLLATE latin1_general_ci DEFAULT NULL,
  `pin` int(10) DEFAULT NULL,
  `country` varchar(20) COLLATE latin1_general_ci DEFAULT NULL,
  `person_id` int(11) NOT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=10 ;

--
-- Dumping data for table `address`
--

INSERT INTO `address` (`address_id`, `house_no`, `address_line1`, `address_line2`, `city`, `state`, `pin`, `country`, `person_id`) VALUES
(2, 'test1', NULL, NULL, NULL, NULL, NULL, NULL, 0),
(3, 'test2', NULL, NULL, NULL, NULL, NULL, NULL, 0),
(4, 'test3', NULL, NULL, NULL, NULL, NULL, NULL, 0),
(5, 'test4', NULL, NULL, NULL, NULL, NULL, NULL, 9),
(6, 'test5', NULL, NULL, NULL, NULL, NULL, NULL, 10),
(7, 'test6', NULL, NULL, NULL, NULL, NULL, NULL, 11),
(8, 'test7', NULL, NULL, NULL, NULL, NULL, NULL, 12),
(9, 'test8', NULL, NULL, NULL, NULL, NULL, NULL, 13);

-- --------------------------------------------------------

--
-- Table structure for table `disease`
--

DROP TABLE IF EXISTS `disease`;
CREATE TABLE IF NOT EXISTS `disease` (
  `disease_id` int(11) NOT NULL AUTO_INCREMENT,
  `history_id` int(11) NOT NULL,
  `disease` varchar(100) COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`disease_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `doc_data`
--

DROP TABLE IF EXISTS `doc_data`;
CREATE TABLE IF NOT EXISTS `doc_data` (
  `doc_data_id` int(11) NOT NULL AUTO_INCREMENT,
  `doc_id` int(11) NOT NULL,
  `person_id` int(11) NOT NULL,
  `specialization_id` varchar(45) COLLATE latin1_general_ci DEFAULT NULL,
  PRIMARY KEY (`doc_data_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `medicine`
--

DROP TABLE IF EXISTS `medicine`;
CREATE TABLE IF NOT EXISTS `medicine` (
  `medicine_data_id` int(11) NOT NULL,
  `medicines_id` int(11) NOT NULL,
  `prescription_id` int(11) NOT NULL,
  `history_id` int(11) NOT NULL,
  `morning` set('N','Y') COLLATE latin1_general_ci NOT NULL DEFAULT 'N',
  `afternoon` set('N','Y') COLLATE latin1_general_ci NOT NULL DEFAULT 'N',
  `evening` set('N','Y') COLLATE latin1_general_ci NOT NULL DEFAULT 'N',
  `night` set('N','Y') COLLATE latin1_general_ci NOT NULL DEFAULT 'N'
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `medicines_all`
--

DROP TABLE IF EXISTS `medicines_all`;
CREATE TABLE IF NOT EXISTS `medicines_all` (
  `medicines_id` int(11) NOT NULL AUTO_INCREMENT,
  `medicine` varchar(100) COLLATE latin1_general_ci NOT NULL,
  `type` varchar(45) COLLATE latin1_general_ci DEFAULT NULL,
  `dose` varchar(45) COLLATE latin1_general_ci DEFAULT NULL,
  `manufacturer` varchar(45) COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`medicines_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `notes`
--

DROP TABLE IF EXISTS `notes`;
CREATE TABLE IF NOT EXISTS `notes` (
  `notes_id` int(11) NOT NULL AUTO_INCREMENT,
  `history_id` int(11) NOT NULL,
  `note` varchar(500) COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`notes_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `parameters`
--

DROP TABLE IF EXISTS `parameters`;
CREATE TABLE IF NOT EXISTS `parameters` (
  `parameters_id` int(11) NOT NULL,
  `history_id` int(11) NOT NULL,
  `parameter_type` varchar(45) COLLATE latin1_general_ci NOT NULL,
  `value` varchar(45) COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`parameters_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
CREATE TABLE IF NOT EXISTS `patient` (
  `patient_id` int(11) NOT NULL AUTO_INCREMENT,
  `person_id` int(11) NOT NULL,
  `height` varchar(15) COLLATE latin1_general_ci DEFAULT NULL,
  `weight` varchar(15) COLLATE latin1_general_ci DEFAULT NULL,
  PRIMARY KEY (`patient_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=14 ;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`patient_id`, `person_id`, `height`, `weight`) VALUES
(2, 0, NULL, NULL),
(3, 0, NULL, NULL),
(4, 0, NULL, NULL),
(9, 0, NULL, NULL),
(10, 0, NULL, NULL),
(11, 11, NULL, NULL),
(12, 12, NULL, NULL),
(13, 13, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `patient_history`
--

DROP TABLE IF EXISTS `patient_history`;
CREATE TABLE IF NOT EXISTS `patient_history` (
  `history_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `patient_id` int(11) unsigned NOT NULL,
  `person_id` int(11) unsigned NOT NULL,
  `symptoms_id` int(11) unsigned DEFAULT NULL,
  `parameters_id` int(11) unsigned DEFAULT NULL,
  `disease_id` int(11) unsigned DEFAULT NULL,
  `medicines_id` int(11) unsigned DEFAULT NULL,
  `tests_id` int(11) unsigned DEFAULT NULL,
  `notes_id` int(11) unsigned DEFAULT NULL,
  `doc_id` int(10) unsigned NOT NULL,
  `saved` varchar(45) COLLATE latin1_general_ci NOT NULL DEFAULT 'N',
  `date_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `date_created` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`history_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `per_all_people_f`
--

DROP TABLE IF EXISTS `per_all_people_f`;
CREATE TABLE IF NOT EXISTS `per_all_people_f` (
  `person_id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE latin1_general_ci NOT NULL,
  `email` varchar(45) COLLATE latin1_general_ci DEFAULT NULL,
  `person_type` varchar(20) COLLATE latin1_general_ci NOT NULL,
  `address_id` int(15) DEFAULT NULL,
  `telephone` int(15) NOT NULL,
  `patient_id` int(15) DEFAULT NULL,
  `doc_id` int(15) DEFAULT NULL,
  `person_no` int(15) DEFAULT NULL,
  PRIMARY KEY (`person_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=14 ;

--
-- Dumping data for table `per_all_people_f`
--

INSERT INTO `per_all_people_f` (`person_id`, `name`, `email`, `person_type`, `address_id`, `telephone`, `patient_id`, `doc_id`, `person_no`) VALUES
(9, 'test4', 'test4', 'P', NULL, 4, NULL, NULL, NULL),
(8, 'test3', 'test3', 'P', NULL, 3, NULL, NULL, NULL),
(7, 'test2', 'test2', 'P', NULL, 2, NULL, NULL, NULL),
(6, 'test1', 'test1', 'P', NULL, 12345, NULL, NULL, NULL),
(10, 'test5', 'test5', 'P', NULL, 5, NULL, NULL, NULL),
(11, 'test6', 'test6', 'P', NULL, 6, NULL, NULL, NULL),
(12, 'test7', 'test7', 'P', NULL, 7, NULL, NULL, NULL),
(13, 'test8', 'test8', 'P', NULL, 8, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `prescription`
--

DROP TABLE IF EXISTS `prescription`;
CREATE TABLE IF NOT EXISTS `prescription` (
  `prescription_id` int(11) NOT NULL AUTO_INCREMENT,
  `history_id` int(11) NOT NULL,
  `prescription_date` date NOT NULL,
  PRIMARY KEY (`prescription_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `symptoms`
--

DROP TABLE IF EXISTS `symptoms`;
CREATE TABLE IF NOT EXISTS `symptoms` (
  `symptoms_id` int(11) NOT NULL AUTO_INCREMENT,
  `history_id` int(11) NOT NULL,
  `symptom` varchar(100) COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`symptoms_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `tests`
--

DROP TABLE IF EXISTS `tests`;
CREATE TABLE IF NOT EXISTS `tests` (
  `tests_id` int(11) NOT NULL AUTO_INCREMENT,
  `history_id` int(11) NOT NULL,
  `tests` varchar(100) COLLATE latin1_general_ci DEFAULT NULL,
  PRIMARY KEY (`tests_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `unique_id` varchar(23) COLLATE latin1_general_ci NOT NULL,
  `name` varchar(50) COLLATE latin1_general_ci NOT NULL,
  `email` varchar(100) COLLATE latin1_general_ci NOT NULL,
  `person_id` varchar(45) COLLATE latin1_general_ci NOT NULL,
  `encrypted_password` varchar(80) COLLATE latin1_general_ci NOT NULL,
  `salt` varchar(10) COLLATE latin1_general_ci NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `unique_id` (`unique_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=18 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`uid`, `unique_id`, `name`, `email`, `person_id`, `encrypted_password`, `salt`, `created_at`, `updated_at`) VALUES
(15, '526ca6ee3c9d34.19720793', 'test4', 'test4', '4', '9QsZROZAfwl0DQTR32hca1lvKo2E3N2JhOWNkOTE1', '77ba9cd915', '2013-10-27 01:38:54', NULL),
(14, '526ca6815441d3.30016355', 'test3', 'test3', '5', '0KONQRz6xV38tFPmI/bd9aHOrNczNzQ4MWMyZmIw', '37481c2fb0', '2013-10-27 01:37:05', NULL),
(13, '526ca5f2158875.62948780', 'test2', 'test2', '6', 'd+x9luhi1fRQz8rCiQgRfw24+RdlNDdiMDViMzg5', 'e47b05b389', '2013-10-27 01:34:42', NULL),
(11, '526ca09d4b4371.37267302', 'a', 'a', '1', 'KW4Xd0TFJaD9ktSFLbh93TZ6MXFmMzNmN2FlODlj', 'f33f7ae89c', '2013-10-27 01:11:57', NULL),
(12, '526ca50e86bce5.72888464', 'test1', 'test1', '2', 'c5RwPKyQLpV85NymyvDZxXarK142YmY3ZDJjOWFh', '6bf7d2c9aa', '2013-10-27 01:30:54', NULL),
(16, '526ca7621f5e92.01271850', 'test5', 'test5', '3', '10aHl1wsBNPNA6zXY/R4FHpKjmc0xmMjU0ZWU4MTBm', 'f254ee810f', '2013-10-27 01:40:50', NULL),
(17, '526ca90708dc21.31149067', 'test8', 'test8', '13', 'BSCq7BbkoBcjb81icJ+QJOCn/soxOTczYzE4NWQw', '1973c185d0', '2013-10-27 01:47:51', NULL);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
