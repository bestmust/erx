
-- phpMyAdmin SQL Dump
-- version 2.11.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 17, 2013 at 04:09 AM
-- Server version: 5.1.57
-- PHP Version: 5.2.17

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Database: `a8748905_android`
--

-- --------------------------------------------------------

--
-- Table structure for table `users`
--
-- Creation: Jun 04, 2013 at 05:29 AM
-- Last update: Jun 16, 2013 at 03:01 AM
-- Last check: Jun 07, 2013 at 02:22 AM
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `unique_id` varchar(23) COLLATE latin1_general_ci NOT NULL,
  `name` varchar(50) COLLATE latin1_general_ci NOT NULL,
  `email` varchar(100) COLLATE latin1_general_ci NOT NULL,
  `encrypted_password` varchar(80) COLLATE latin1_general_ci NOT NULL,
  `salt` varchar(10) COLLATE latin1_general_ci NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `unique_id` (`unique_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=4 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`uid`, `unique_id`, `name`, `email`, `encrypted_password`, `salt`, `created_at`, `updated_at`) VALUES
(1, '51aef18eca3e21.76381710', 'Mustaqim Moulavi', 'bestmust@gmail.com', '0CXzWXMZH+HND5NSCAEYCdch0L04NzY5ODNkNTc0', '876983d574', '2013-06-05 04:06:38', NULL),
(2, '51aef5de7c0217.63553951', 'a', 'a', 'v3mrt+HM+V3bDgvVZqkP77wfNtthNzYzNDhmNzdk', 'a76348f77d', '2013-06-05 04:25:02', NULL);
