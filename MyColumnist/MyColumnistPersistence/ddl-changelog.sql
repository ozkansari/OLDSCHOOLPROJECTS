INSERT INTO `newspaper_table` 
VALUES 
(1,'Sabah',0,0),
(2,'Habertürk',0,0),
(3,'Zaman',0,0),
(4,'Takvim',0,0),
(5,'Fanatik',0,0),
(6,'Posta',0,0),
(7,'Vatan',0,0),
(8,'Radikal',0,0),
(9,'Milliyet',0,0),
(10,'Hürriyet',0,0);

ALTER TABLE `columnist_table` ADD CONSTRAINT `COLUMNIST_NEWSPAPER_ID_FK` FOREIGN KEY `COLUMNIST_NEWSPAPER_ID_FK` (`newspaper_id`)
    REFERENCES `newspaper_table` (`newspaper_id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;

ALTER TABLE `columnist_table` ADD COLUMN `newspaper_id` INTEGER UNSIGNED AFTER `latest_column_id`;

ALTER TABLE `columnist_table` DROP FOREIGN KEY `COLUMNIST_COLUMN_ID`,
 ADD CONSTRAINT `COLUMNIST_COLUMN_ID_FK` FOREIGN KEY `COLUMNIST_COLUMN_ID_FK` (`latest_column_id`)
    REFERENCES `column_table` (`column_id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;
    
CREATE TABLE `newspaper_table` (
  `newspaper_id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `read_count` INTEGER UNSIGNED DEFAULT 0,
  `fav_count` INTEGER UNSIGNED DEFAULT 0,
  PRIMARY KEY (`newspaper_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci ;

ALTER TABLE `column_table` MODIFY COLUMN `summary` VARCHAR(1024) CHARACTER SET utf8 COLLATE utf8_turkish_ci DEFAULT NULL;

ALTER TABLE `columnist_table` MODIFY COLUMN `category` VARCHAR(64) CHARACTER SET utf8 COLLATE utf8_turkish_ci DEFAULT NULL;

ALTER TABLE `column_table` MODIFY COLUMN `column_id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT;

ALTER TABLE `columnist_table` MODIFY COLUMN `columnist_id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT;

ALTER TABLE  `columnist_table` ADD  `image_src` VARCHAR( 256 ) NULL AFTER  `name_tag`;

ALTER TABLE  `columnist_table` ADD  `name_tag` VARCHAR( 128 ) NOT NULL AFTER  `name`;

ALTER TABLE  `column_table` DROP INDEX  `columnist_id` ,
ADD INDEX  `columnist_id` (  `columnist_id` );

ALTER TABLE  `columnist_table` ROW_FORMAT = COMPACT;

ALTER TABLE  `column_table` ROW_FORMAT = COMPACT;

ALTER TABLE `column` RENAME TO `column_table`;

ALTER TABLE `columnist` RENAME TO `columnist_table`;

ALTER TABLE `column` CHANGE  `column_id`  `column_id` INT( 10 ) UNSIGNED NOT NULL AUTO_INCREMENT;

ALTER TABLE  columnist CHANGE  `columnist_id`  `columnist_id` INT( 10 ) UNSIGNED NOT NULL AUTO_INCREMENT;

ALTER TABLE columnist
 ADD CONSTRAINT COLUMNIST_COLUMN_ID FOREIGN KEY (latest_column_id)
 REFERENCES `column` (column_id) ON UPDATE RESTRICT ON DELETE RESTRICT;
 
ALTER TABLE `column`
 ADD CONSTRAINT COLUMN_COLUMNIST_ID_FK FOREIGN KEY (columnist_id)
 REFERENCES columnist (columnist_id) ON UPDATE RESTRICT ON DELETE RESTRICT;
 
CREATE TABLE `column` (
  `column_id` int(10) unsigned NOT NULL,
  `columnist_id` int(10) unsigned NOT NULL,
  `date` date NOT NULL,
  `sequence` tinyint(2) NOT NULL,
  `title` varchar(256) COLLATE utf8_turkish_ci NOT NULL,
  `summary` varchar(1024) COLLATE utf8_turkish_ci NOT NULL,
  `hyperlink` varchar(256) COLLATE utf8_turkish_ci NOT NULL,
  PRIMARY KEY (`column_id`),
  UNIQUE KEY `columnist_id` (`columnist_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci COMMENT='Column \\ Article';

CREATE TABLE `columnist` (
  `columnist_id` int(10) unsigned NOT NULL,
  `name` varchar(128) COLLATE utf8_turkish_ci NOT NULL,
  `newspaper` varchar(64) COLLATE utf8_turkish_ci NOT NULL,
  `category` varchar(64) COLLATE utf8_turkish_ci NOT NULL,
  `latest_column_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`columnist_id`),
  UNIQUE KEY `latest_column_id` (`latest_column_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci COMMENT='Columnist';

CREATE DATABASE `my_columnist` /*!40100 DEFAULT CHARACTER SET utf8 */;


