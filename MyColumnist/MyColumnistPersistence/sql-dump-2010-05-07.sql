-- phpMyAdmin SQL Dump
-- version 2.11.4
-- http://www.phpmyadmin.net
--
-- Host: s43.eatj.com:3307
-- Generation Time: May 07, 2010 at 11:44 AM
-- Server version: 5.0.77
-- PHP Version: 5.2.6

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Database: `ozkansari2`
--

-- --------------------------------------------------------

--
-- Table structure for table `columnist_table`
--

CREATE TABLE IF NOT EXISTS `columnist_table` (
  `columnist_id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(128) collate utf8_turkish_ci NOT NULL,
  `name_tag` varchar(128) collate utf8_turkish_ci NOT NULL,
  `image_src` varchar(256) collate utf8_turkish_ci default NULL,
  `newspaper` varchar(64) collate utf8_turkish_ci NOT NULL,
  `category` varchar(64) collate utf8_turkish_ci default NULL,
  `latest_column_id` int(10) unsigned default NULL,
  `newspaper_id` int(10) unsigned default NULL,
  PRIMARY KEY  (`columnist_id`),
  UNIQUE KEY `latest_column_id` (`latest_column_id`),
  KEY `COLUMNIST_NEWSPAPER_ID_FK` (`newspaper_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci ROW_FORMAT=COMPACT COMMENT='Columnist' AUTO_INCREMENT=1 ;

--
-- Dumping data for table `columnist_table`
--


-- --------------------------------------------------------

--
-- Table structure for table `column_table`
--

CREATE TABLE IF NOT EXISTS `column_table` (
  `column_id` int(10) unsigned NOT NULL auto_increment,
  `columnist_id` int(10) unsigned NOT NULL,
  `date` date NOT NULL,
  `sequence` tinyint(2) NOT NULL,
  `title` varchar(256) collate utf8_turkish_ci NOT NULL,
  `summary` varchar(1024) collate utf8_turkish_ci default NULL,
  `hyperlink` varchar(256) collate utf8_turkish_ci NOT NULL,
  PRIMARY KEY  (`column_id`),
  KEY `columnist_id` (`columnist_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci ROW_FORMAT=COMPACT COMMENT='Column \\ Article' AUTO_INCREMENT=1 ;

--
-- Dumping data for table `column_table`
--


-- --------------------------------------------------------

--
-- Table structure for table `newspaper_table`
--

CREATE TABLE IF NOT EXISTS `newspaper_table` (
  `newspaper_id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(45) collate utf8_turkish_ci NOT NULL,
  `read_count` int(10) unsigned default '0',
  `fav_count` int(10) unsigned default '0',
  PRIMARY KEY  (`newspaper_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=11 ;

--
-- Dumping data for table `newspaper_table`
--

INSERT INTO `newspaper_table` (`newspaper_id`, `name`, `read_count`, `fav_count`) VALUES
(1, 'Sabah', 0, 0),
(2, 'Habertürk', 0, 0),
(3, 'Zaman', 0, 0),
(4, 'Takvim', 0, 0),
(5, 'Fanatik', 0, 0),
(6, 'Posta', 0, 0),
(7, 'Vatan', 0, 0),
(8, 'Radikal', 0, 0),
(9, 'Milliyet', 0, 0),
(10, 'Hürriyet', 0, 0);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `columnist_table`
--
ALTER TABLE `columnist_table`
  ADD CONSTRAINT `COLUMNIST_NEWSPAPER_ID_FK` FOREIGN KEY (`newspaper_id`) REFERENCES `newspaper_table` (`newspaper_id`),
  ADD CONSTRAINT `COLUMNIST_COLUMN_ID_FK` FOREIGN KEY (`latest_column_id`) REFERENCES `column_table` (`column_id`);

--
-- Constraints for table `column_table`
--
ALTER TABLE `column_table`
  ADD CONSTRAINT `COLUMN_COLUMNIST_ID_FK` FOREIGN KEY (`columnist_id`) REFERENCES `columnist_table` (`columnist_id`);
