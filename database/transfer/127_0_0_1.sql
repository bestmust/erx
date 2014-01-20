-- phpMyAdmin SQL Dump
-- version 4.0.9
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 07, 2014 at 10:22 AM
-- Server version: 5.6.14
-- PHP Version: 5.5.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;


Database: `1541215_android`


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
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=11 ;

--
-- Dumping data for table `address`
--

INSERT INTO `address` (`address_id`, `house_no`, `address_line1`, `address_line2`, `city`, `state`, `pin`, `country`, `person_id`) VALUES
(1, 'a', NULL, NULL, NULL, NULL, NULL, NULL, 1),
(2, 'b', NULL, NULL, NULL, NULL, NULL, NULL, 2),
(3, 'c', NULL, NULL, NULL, NULL, NULL, NULL, 3),
(4, 'd', NULL, NULL, NULL, NULL, NULL, NULL, 4),
(5, 'e', NULL, NULL, NULL, NULL, NULL, NULL, 5),
(6, 'ff', NULL, NULL, NULL, NULL, NULL, NULL, 6),
(7, 'g', NULL, NULL, NULL, NULL, NULL, NULL, 7),
(8, 'h', NULL, NULL, NULL, NULL, NULL, NULL, 8),
(9, 'i', NULL, NULL, NULL, NULL, NULL, NULL, 9),
(10, 'j', NULL, NULL, NULL, NULL, NULL, NULL, 10);

-- --------------------------------------------------------

--
-- Table structure for table `disease`
--

CREATE TABLE IF NOT EXISTS `disease` (
  `disease_id` int(11) NOT NULL AUTO_INCREMENT,
  `history_id` int(11) NOT NULL,
  `disease` varchar(100) COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`disease_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

CREATE TABLE IF NOT EXISTS `doctor` (
  `doctor_id` int(11) NOT NULL AUTO_INCREMENT,
  `person_id` int(11) NOT NULL,
  `specialization_id` varchar(45) COLLATE latin1_general_ci DEFAULT NULL,
  PRIMARY KEY (`doctor_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=9 ;

--
-- Dumping data for table `doctor`
--

INSERT INTO `doctor` (`doctor_id`, `person_id`, `specialization_id`) VALUES
(1, 1, NULL),
(2, 3, NULL),
(3, 5, NULL),
(4, 6, NULL),
(5, 7, NULL),
(6, 8, NULL),
(7, 9, NULL),
(8, 10, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `medicine`
--

CREATE TABLE IF NOT EXISTS `medicine` (
  `medicine_data_id` int(11) NOT NULL,
  `medicines_id` int(11) NOT NULL,
  `prescription_id` int(11) NOT NULL,
  `history_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `medicines_all`
--

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

CREATE TABLE IF NOT EXISTS `patient` (
  `patient_id` int(11) NOT NULL AUTO_INCREMENT,
  `person_id` int(11) NOT NULL,
  `height` varchar(15) COLLATE latin1_general_ci DEFAULT NULL,
  `weight` varchar(15) COLLATE latin1_general_ci DEFAULT NULL,
  PRIMARY KEY (`patient_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=3 ;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`patient_id`, `person_id`, `height`, `weight`) VALUES
(1, 2, NULL, NULL),
(2, 4, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `patient_history`
--

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
  `doctor_id` int(10) unsigned NOT NULL,
  `saved` varchar(45) COLLATE latin1_general_ci NOT NULL DEFAULT 'N',
  `date_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `date_created` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`history_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=17 ;

--
-- Dumping data for table `patient_history`
--

INSERT INTO `patient_history` (`history_id`, `patient_id`, `person_id`, `symptoms_id`, `parameters_id`, `disease_id`, `medicines_id`, `tests_id`, `notes_id`, `doctor_id`, `saved`, `date_modified`, `date_created`) VALUES
(7, 1, 2, NULL, NULL, NULL, NULL, NULL, NULL, 1, 'Y', '2013-11-28 10:56:08', '0000-00-00 00:00:00'),
(2, 1, 2, NULL, NULL, NULL, NULL, NULL, NULL, 1, 'Y', '2013-11-28 08:27:33', '0000-00-00 00:00:00'),
(3, 1, 2, NULL, NULL, NULL, NULL, NULL, NULL, 1, 'Y', '2013-11-27 23:43:24', '0000-00-00 00:00:00'),
(8, 1, 2, NULL, NULL, NULL, NULL, NULL, NULL, 1, 'Y', '2013-11-28 11:00:29', '0000-00-00 00:00:00'),
(9, 1, 2, NULL, NULL, NULL, NULL, NULL, NULL, 1, 'Y', '2013-11-28 11:01:53', '0000-00-00 00:00:00'),
(10, 1, 2, NULL, NULL, NULL, NULL, NULL, NULL, 1, 'Y', '2013-11-28 11:02:22', '0000-00-00 00:00:00'),
(11, 1, 2, NULL, NULL, NULL, NULL, NULL, NULL, 1, 'N', '2013-11-28 11:03:46', '0000-00-00 00:00:00'),
(12, 1, 2, NULL, NULL, NULL, NULL, NULL, NULL, 2, 'N', '2013-11-28 11:07:33', '0000-00-00 00:00:00'),
(13, 2, 4, NULL, NULL, NULL, NULL, NULL, NULL, 2, 'Y', '2013-11-28 11:09:16', '0000-00-00 00:00:00'),
(14, 2, 4, NULL, NULL, NULL, NULL, NULL, NULL, 1, 'Y', '2013-11-28 11:10:17', '0000-00-00 00:00:00'),
(16, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, 'N', '2013-12-03 08:36:09', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `per_all_people_f`
--

CREATE TABLE IF NOT EXISTS `per_all_people_f` (
  `person_id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE latin1_general_ci NOT NULL,
  `email` varchar(45) COLLATE latin1_general_ci DEFAULT NULL,
  `person_type` varchar(20) COLLATE latin1_general_ci NOT NULL,
  `address_id` int(15) DEFAULT NULL,
  `telephone` int(15) NOT NULL,
  `patient_id` int(15) DEFAULT NULL,
  `doctor_id` int(15) DEFAULT NULL,
  `person_no` int(15) DEFAULT NULL,
  PRIMARY KEY (`person_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=11 ;

--
-- Dumping data for table `per_all_people_f`
--

INSERT INTO `per_all_people_f` (`person_id`, `name`, `email`, `person_type`, `address_id`, `telephone`, `patient_id`, `doctor_id`, `person_no`) VALUES
(1, 'a', 'a', 'D', NULL, 1, NULL, 1, NULL),
(2, 'b', 'b', 'P', NULL, 1, 1, NULL, NULL),
(3, 'c', 'c', 'D', NULL, 3, NULL, 2, NULL),
(4, 'd', 'd', 'P', NULL, 4, 2, NULL, NULL),
(5, 'e', 'e', 'D', NULL, 5, NULL, 3, NULL),
(6, 'f', 'f', 'D', NULL, 5, NULL, 4, NULL),
(7, 'g', 'g', 'D', NULL, 6, NULL, 5, NULL),
(8, 'h', 'h', 'D', NULL, 7, NULL, 6, NULL),
(9, 'i', 'i', 'D', NULL, 8, NULL, 7, NULL),
(10, 'j', 'j', 'D', NULL, 89, NULL, 8, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `prescription`
--

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

CREATE TABLE IF NOT EXISTS `symptoms` (
  `symptom_id` int(11) NOT NULL AUTO_INCREMENT,
  `history_id` int(11) NOT NULL,
  `symptom` varchar(100) COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`symptom_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=7 ;

--
-- Dumping data for table `symptoms`
--

INSERT INTO `symptoms` (`symptom_id`, `history_id`, `symptom`) VALUES
(1, 11, 'head ache'),
(3, 11, 'stomach ache'),
(4, 11, 'throat ache'),
(5, 11, 'leg ache'),
(6, 11, 'the fifth symptom');

-- --------------------------------------------------------

--
-- Table structure for table `tests`
--

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
  `last_login` datetime DEFAULT NULL,
  `login_number` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `unique_id` (`unique_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=11 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`uid`, `unique_id`, `name`, `email`, `person_id`, `encrypted_password`, `salt`, `created_at`, `updated_at`, `last_login`, `login_number`) VALUES
(1, '5293cf0a088344.17963477', 'a', 'a', '1', 'HqhNFQSwemKIuXXn40oh828z/Tw1ZDExM2QxMWU1', '5d113d11e5', '2013-11-25 17:28:26', NULL, '2014-01-07 14:43:34', 16),
(2, '5293cf211b12e0.15882339', 'b', 'b', '2', 'L+2E1OXNl8u+g4Ywm9bD9rqQxSI4OGFiMDUyZWNl', '88ab052ece', '2013-11-25 17:28:49', NULL, '2013-12-02 00:00:00', 2),
(3, '529723b58029d1.91968511', 'c', 'c', '3', 'FlscyPbS6hvsGEttHg0vjsRFejgzNjg2MDM3NWEy', '36860375a2', '2013-11-28 06:06:29', NULL, '2013-12-02 00:00:00', 2),
(4, '5297244d3760a8.82351140', 'd', 'd', '4', 'tZXFJInW08aQqIWqelNH0D60Ky1iNDY2YjViNWRk', 'b466b5b5dd', '2013-11-28 06:09:01', NULL, '2013-12-02 00:00:00', 2),
(5, '529724ef981599.54961990', 'e', 'e', '5', 'Tzq9DjziF0uh8Lowb3q8u5SHxxQ1MjU0ZDMzYzQ0', '5254d33c44', '2013-11-28 06:11:43', NULL, '2013-12-02 00:00:00', 2),
(6, '52972567362278.88388377', 'f', 'f', '6', 'OqzMnTk+U9En59cxkO69cUDBxrUyNmIwYjEyNjA5', '26b0b12609', '2013-11-28 06:13:43', NULL, '2013-12-02 00:00:00', 2),
(7, '529725e9e4e015.65959347', 'g', 'g', '7', 'r7/oEAqR5d0jlzUCEuOYOoHaVDQ4MTdhYWYyMDA3', '817aaf2007', '2013-11-28 06:15:53', NULL, '2013-12-02 00:00:00', 2),
(8, '52972653c9a596.40477000', 'h', 'h', '8', 'p3j7sie0aN885GMS2JoVgjQPTxo5NzhlMTFiZWIy', '978e11beb2', '2013-11-28 06:17:39', NULL, '2013-12-02 00:00:00', 2),
(9, '52972779e245e9.86745941', 'i', 'i', '9', '2gAfyelJjifZEsHjc4YKvvlgSeEyNzEzZmYyMzZk', '2713ff236d', '2013-11-28 06:22:33', NULL, '2013-12-02 00:00:00', 2),
(10, '5297285af39a67.38790572', 'j', 'j', '10', '/1kN0C1zXiMzRA1bY/UNI6l64Yo5M2JhMTYwOGZj', '93ba1608fc', '2013-11-28 06:26:18', NULL, '2013-12-02 00:00:00', 2);
--
-- Database: `cdcol`
--

-- --------------------------------------------------------

--
-- Table structure for table `cds`
--

CREATE TABLE IF NOT EXISTS `cds` (
  `titel` varchar(200) COLLATE latin1_general_ci DEFAULT NULL,
  `interpret` varchar(200) COLLATE latin1_general_ci DEFAULT NULL,
  `jahr` int(11) DEFAULT NULL,
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=7 ;

--
-- Dumping data for table `cds`
--

INSERT INTO `cds` (`titel`, `interpret`, `jahr`, `id`) VALUES
('Beauty', 'Ryuichi Sakamoto', 1990, 1),
('Goodbye Country (Hello Nightclub)', 'Groove Armada', 2001, 4),
('Glee', 'Bran Van 3000', 1997, 5);
--
-- Database: `phpmyadmin`
--

-- --------------------------------------------------------

--
-- Table structure for table `pma_bookmark`
--

CREATE TABLE IF NOT EXISTS `pma_bookmark` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dbase` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `user` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `label` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `query` text COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Bookmarks' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `pma_column_info`
--

CREATE TABLE IF NOT EXISTS `pma_column_info` (
  `id` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `column_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `comment` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `mimetype` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `transformation` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `transformation_options` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `db_name` (`db_name`,`table_name`,`column_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Column information for phpMyAdmin' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `pma_designer_coords`
--

CREATE TABLE IF NOT EXISTS `pma_designer_coords` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `x` int(11) DEFAULT NULL,
  `y` int(11) DEFAULT NULL,
  `v` tinyint(4) DEFAULT NULL,
  `h` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`db_name`,`table_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table coordinates for Designer';

-- --------------------------------------------------------

--
-- Table structure for table `pma_history`
--

CREATE TABLE IF NOT EXISTS `pma_history` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `db` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `timevalue` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `sqlquery` text COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  KEY `username` (`username`,`db`,`table`,`timevalue`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='SQL history for phpMyAdmin' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `pma_pdf_pages`
--

CREATE TABLE IF NOT EXISTS `pma_pdf_pages` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `page_nr` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `page_descr` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '',
  PRIMARY KEY (`page_nr`),
  KEY `db_name` (`db_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='PDF relation pages for phpMyAdmin' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `pma_recent`
--

CREATE TABLE IF NOT EXISTS `pma_recent` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `tables` text COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Recently accessed tables';

--
-- Dumping data for table `pma_recent`
--

INSERT INTO `pma_recent` (`username`, `tables`) VALUES
('root', '[{"db":"phpmyadmin","table":"pma_column_info"},{"db":"phpmyadmin","table":"pma_designer_coords"},{"db":"phpmyadmin","table":"pma_history"},{"db":"phpmyadmin","table":"pma_pdf_pages"},{"db":"phpmyadmin","table":"pma_recent"},{"db":"phpmyadmin","table":"pma_relation"},{"db":"cdcol","table":"cds"},{"db":"mysql","table":"user"},{"db":"mysql","table":"help_relation"},{"db":"mysql","table":"db"}]');

-- --------------------------------------------------------

--
-- Table structure for table `pma_relation`
--

CREATE TABLE IF NOT EXISTS `pma_relation` (
  `master_db` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `master_table` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `master_field` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `foreign_db` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `foreign_table` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `foreign_field` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`master_db`,`master_table`,`master_field`),
  KEY `foreign_field` (`foreign_db`,`foreign_table`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Relation table';

-- --------------------------------------------------------

--
-- Table structure for table `pma_table_coords`
--

CREATE TABLE IF NOT EXISTS `pma_table_coords` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `pdf_page_number` int(11) NOT NULL DEFAULT '0',
  `x` float unsigned NOT NULL DEFAULT '0',
  `y` float unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`db_name`,`table_name`,`pdf_page_number`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table coordinates for phpMyAdmin PDF output';

-- --------------------------------------------------------

--
-- Table structure for table `pma_table_info`
--

CREATE TABLE IF NOT EXISTS `pma_table_info` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `display_field` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`db_name`,`table_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table information for phpMyAdmin';

-- --------------------------------------------------------

--
-- Table structure for table `pma_table_uiprefs`
--

CREATE TABLE IF NOT EXISTS `pma_table_uiprefs` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `prefs` text COLLATE utf8_bin NOT NULL,
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`username`,`db_name`,`table_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Tables'' UI preferences';

-- --------------------------------------------------------

--
-- Table structure for table `pma_tracking`
--

CREATE TABLE IF NOT EXISTS `pma_tracking` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `version` int(10) unsigned NOT NULL,
  `date_created` datetime NOT NULL,
  `date_updated` datetime NOT NULL,
  `schema_snapshot` text COLLATE utf8_bin NOT NULL,
  `schema_sql` text COLLATE utf8_bin,
  `data_sql` longtext COLLATE utf8_bin,
  `tracking` set('UPDATE','REPLACE','INSERT','DELETE','TRUNCATE','CREATE DATABASE','ALTER DATABASE','DROP DATABASE','CREATE TABLE','ALTER TABLE','RENAME TABLE','DROP TABLE','CREATE INDEX','DROP INDEX','CREATE VIEW','ALTER VIEW','DROP VIEW') COLLATE utf8_bin DEFAULT NULL,
  `tracking_active` int(1) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`db_name`,`table_name`,`version`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='Database changes tracking for phpMyAdmin';

-- --------------------------------------------------------

--
-- Table structure for table `pma_userconfig`
--

CREATE TABLE IF NOT EXISTS `pma_userconfig` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `timevalue` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `config_data` text COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='User preferences storage for phpMyAdmin';
--
-- Database: `test`
--
--
-- Database: `webauth`
--

-- --------------------------------------------------------

--
-- Table structure for table `user_pwd`
--

CREATE TABLE IF NOT EXISTS `user_pwd` (
  `name` char(30) COLLATE latin1_general_ci NOT NULL DEFAULT '',
  `pass` char(32) COLLATE latin1_general_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `user_pwd`
--

INSERT INTO `user_pwd` (`name`, `pass`) VALUES
('xampp', 'wampp');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
