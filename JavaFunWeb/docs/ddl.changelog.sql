
-- ***************************************************************
-- [ozkansari, 20110409] : Committing Initial database creation
-- ***************************************************************

CREATE TABLE `project` (
  `id_project` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `project_name` varchar(128) NOT NULL,
  `description` varchar(1024) NOT NULL,
  PRIMARY KEY (`id_project`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Table `javafun`.`constant_type`
CREATE TABLE `constant_type` (
  `id_constant_type` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `type_name` varchar(45) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id_constant_type`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

INSERT INTO `javafun`.`constant_type`(`id_constant_type`,`type_name`) VALUES (1,'Country');
INSERT INTO `javafun`.`constant_type`(`id_constant_type`,`type_name`) VALUES (2,'City');

-- Table `javafun`.`constant_value`
CREATE TABLE `constant_value` (
  `id_constant_value` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `id_type_fk` int(10) unsigned NOT NULL,
  `valueInt` mediumint(8) unsigned NOT NULL,
  `valueText` varchar(45) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id_constant_value`) USING BTREE,
  KEY `CONSTANTVALUE_CONSTANTTYPE_FK` (`id_type_fk`),
  CONSTRAINT `CONSTANTVALUE_CONSTANTTYPE_FK` FOREIGN KEY (`id_type_fk`) REFERENCES `constant_type` (`id_constant_type`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=791 DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (16,2,1,'Adana');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (17,2,2,'Adıyaman');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (18,2,3,'Afyonkarahisar');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (19,2,4,'Ağrı');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (20,2,5,'Amasya');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (21,2,6,'Ankara');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (22,2,7,'Antalya');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (23,2,8,'Artvin');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (24,2,9,'Aydın');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (25,2,10,'Balıkesir');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (26,2,11,'Bilecik');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (27,2,12,'Bingöl');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (28,2,13,'Bitlis');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (29,2,14,'Bolu');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (30,2,15,'Burdur');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (31,2,16,'Bursa');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (32,2,17,'Çanakkale');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (33,2,18,'Çankırı');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (34,2,19,'Çorum');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (35,2,20,'Denizli');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (36,2,21,'Diyarbakır');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (37,2,22,'Edirne');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (38,2,23,'Elazığ');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (39,2,24,'Erzincan');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (40,2,25,'Erzurum');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (41,2,26,'Eskişehir');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (42,2,27,'Gaziantep');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (43,2,28,'Giresun');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (44,2,29,'Gümüşhane');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (45,2,30,'Hakkari');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (46,2,31,'Hatay');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (47,2,32,'Isparta');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (48,2,33,'Mersin');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (49,2,34,'İstanbul');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (50,2,35,'İzmir');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (51,2,36,'Kars');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (52,2,37,'Kastamonu');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (53,2,38,'Kayseri');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (54,2,39,'Kırklareli');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (55,2,40,'Kırşehir');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (56,2,41,'Kocaeli');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (57,2,42,'Konya');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (58,2,43,'Kütahya');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (59,2,44,'Malatya');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (60,2,45,'Manisa');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (61,2,46,'Kahramanmaraş');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (62,2,47,'Mardin');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (63,2,48,'Muğla');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (64,2,49,'Muş');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (65,2,50,'Nevşehir');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (66,2,51,'Niğde');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (67,2,52,'Ordu');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (68,2,53,'Rize');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (69,2,54,'Sakarya');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (70,2,55,'Samsun');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (71,2,56,'Siirt');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (72,2,57,'Sinop');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (73,2,58,'Sivas');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (74,2,59,'Tekirdağ');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (75,2,60,'Tokat');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (76,2,61,'Trabzon');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (77,2,62,'Tunceli');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (78,2,63,'Şanlıurfa');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (79,2,64,'Uşak');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (80,2,65,'Van');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (81,2,66,'Yozgat');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (82,2,67,'Zonguldak');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (83,2,68,'Aksaray');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (84,2,69,'Bayburt');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (85,2,70,'Karaman');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (86,2,71,'Kırıkkale');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (87,2,72,'Batman');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (88,2,73,'Şırnak');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (89,2,74,'Bartın');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (90,2,75,'Ardahan');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (91,2,76,'Iğdır');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (92,2,77,'Yalova');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (93,2,78,'Karabük');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (94,2,79,'Kilis');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (95,2,80,'Osmaniye');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (96,2,81,'Düzce');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (560,1,1,'Fransa');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (561,1,2,'Belçika');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (562,1,3,'Hollanda');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (563,1,4,'Almanya');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (564,1,5,'İtalya');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (565,1,6,'Birleşik Krallık');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (566,1,7,'İrlanda');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (567,1,8,'Danimarka');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (568,1,9,'Yunanistan');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (569,1,10,'Portekiz');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (570,1,11,'İspanya');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (571,1,12,'Lüksemburg');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (572,1,21,'Kanarya Adaları');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (573,1,22,'Ceuta Ve Melilla');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (574,1,24,'İzlanda');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (575,1,25,'Faroe Adaları');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (576,1,28,'Norveç');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (577,1,30,'İsveç');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (578,1,32,'Finlandiya');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (579,1,36,'İsviçre');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (580,1,38,'Avusturya');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (581,1,43,'Andorra');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (582,1,44,'Cebeli Tarık');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (583,1,45,'Vatikan');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (584,1,46,'Malta');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (585,1,48,'Yugoslavya');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (586,1,52,'Türkiye');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (587,1,53,'Estonya');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (588,1,54,'Letonya');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (589,1,55,'Litvanya');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (590,1,60,'Polonya');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (591,1,62,'Çek Cumhuriyeti');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (592,1,63,'Slovakya');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (593,1,64,'Macaristan');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (594,1,66,'Romanya');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (595,1,68,'Bulgaristan');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (596,1,70,'Arnavutluk');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (597,1,72,'Ukrayna');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (598,1,73,'Beyaz Rusya');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (599,1,74,'Moldavya');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (600,1,75,'Rusya Federasyonu');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (601,1,76,'Gürcistan');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (602,1,77,'Ermenistan');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (603,1,78,'Azerbeycan-Nahçıvan');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (604,1,79,'Kazakistan');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (605,1,80,'Türkmenistan');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (606,1,81,'Özbekistan');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (607,1,82,'Tacikistan');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (608,1,83,'Kırgızistan');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (609,1,84,'Çeçen Cumhuriyeti');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (610,1,85,'Dağıstan Cumhuriyeti');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (611,1,86,'Tataristan');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (612,1,87,'Yakutistan');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (613,1,91,'Slovenya');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (614,1,92,'Hırvatistan');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (615,1,93,'Bosna-Hersek');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (616,1,94,'Sırbistan');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (617,1,96,'Makedonya');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (618,1,182,'Cook Adaları');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (619,1,204,'Fas');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (620,1,208,'Cezayir');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (621,1,212,'Tunus');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (622,1,216,'Libya');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (623,1,220,'Mısır');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (624,1,224,'Sudan');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (625,1,228,'Moritanya');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (626,1,232,'Mali');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (627,1,236,'Burkina Faso');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (628,1,240,'Nijer');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (629,1,244,'Çad');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (630,1,247,'Cape Verde');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (631,1,248,'Senegal');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (632,1,252,'Gambiya');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (633,1,257,'Gine-Bissau');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (634,1,260,'Gine');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (635,1,264,'Sıerra Leone');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (636,1,268,'Liberya');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (637,1,272,'Fildişi Sahili');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (638,1,276,'Gana');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (639,1,280,'Togo');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (640,1,284,'Benin');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (641,1,288,'Nijerya');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (642,1,302,'Kamerun');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (643,1,306,'Merkezi Afrika Cumhuriyeti');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (644,1,310,'Ekvator Ginesi');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (645,1,311,'Sao Tome And Prıncıpe');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (646,1,314,'Gabon');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (647,1,318,'Kongo');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (648,1,322,'Zaire');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (649,1,324,'Ruanda');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (650,1,328,'Burundi');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (651,1,329,'St. Helena Ve Bağlantıları');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (652,1,330,'Angola');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (653,1,334,'Etiyopya');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (654,1,338,'Cibuti');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (655,1,342,'Somali');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (656,1,346,'Kenya');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (657,1,350,'Uganda');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (658,1,352,'Tanzanya');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (659,1,355,'Seyşel Adaları Ve Bağlantıları');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (660,1,357,'İngiliz Hint Oky.Toprakları');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (661,1,366,'Mozambik');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (662,1,370,'Madagaskar');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (663,1,372,'Reunion');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (664,1,373,'Mauritius');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (665,1,375,'Komoro Adaları');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (666,1,377,'Mayotte');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (667,1,378,'Zambia');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (668,1,382,'Zimbabve');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (669,1,386,'Malavi');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (670,1,389,'Namibya');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (671,1,390,'Güney Afrika Cumhuriyeti');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (672,1,391,'Bostvana');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (673,1,393,'Svaziland');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (674,1,395,'Lesotho');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (675,1,400,'Amerika Birleşik Devletleri');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (676,1,404,'Kanada');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (677,1,406,'Grönland');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (678,1,408,'St. Pierre Ve Miquelon');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (679,1,412,'Meksika');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (680,1,413,'Bermuda');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (681,1,416,'Guatemala');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (682,1,421,'Belize');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (683,1,424,'Honduras');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (684,1,428,'El Salvador');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (685,1,432,'Nikaragua');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (686,1,436,'Kosta Rika');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (687,1,442,'Panama');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (688,1,446,'Anguılla');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (689,1,448,'Küba');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (690,1,449,'St. Christopher Ve Nevis');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (691,1,452,'Haiti');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (692,1,453,'Bahama');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (693,1,454,'Turks Ve Caicos Adası');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (694,1,456,'Dominik Cumhuriyeti');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (695,1,457,'Abd Virjin Adaları');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (696,1,458,'Guadelup');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (697,1,459,'Antıgua Ve Bermuda');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (698,1,460,'Dominika');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (699,1,461,'İngiliz Virjin Adaları');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (700,1,462,'Martinik');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (701,1,463,'Cayman Adaları');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (702,1,464,'Jamaika');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (703,1,465,'St. Lucia');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (704,1,467,'St. Vincent');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (705,1,469,'Barbados');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (706,1,472,'Trinidad Ve Tobago');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (707,1,473,'Grenada');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (708,1,474,'Aruba');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (709,1,478,'Hollanda Antilleri');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (710,1,480,'Kolombiya');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (711,1,484,'Venezuella');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (712,1,488,'Guyana');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (713,1,492,'Surinam');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (714,1,493,'Monaco');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (715,1,496,'Fransız Guyanası');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (716,1,500,'Ekvator');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (717,1,504,'Peru');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (718,1,508,'Brezilya');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (719,1,512,'Şili');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (720,1,516,'Bolivya');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (721,1,520,'Paraguay');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (722,1,524,'Uruguay');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (723,1,528,'Arjantin');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (724,1,529,'Falkland Adaları');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (725,1,600,'Kıbrıs Rum Kesimi');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (726,1,601,'Kuzey Kıbrıs Türk Cumhuriyeti');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (727,1,604,'Lübnan');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (728,1,608,'Suriye');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (729,1,612,'Irak');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (730,1,616,'İran');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (731,1,624,'İsrail');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (732,1,625,'Gazze');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (733,1,628,'Ürdün');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (734,1,632,'Suudi Arabistan');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (735,1,636,'Kuveyt');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (736,1,640,'Bahreyn');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (737,1,644,'Katar');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (738,1,647,'Birleşik Arap Emirlikleri');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (739,1,648,'Dubai');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (740,1,649,'Umman');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (741,1,652,'Kuzey Yemen');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (742,1,656,'Güney Yemen');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (743,1,660,'Afganistan');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (744,1,662,'Pakistan');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (745,1,664,'Hindistan');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (746,1,666,'Bangladeş');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (747,1,667,'Maldiv Adaları');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (748,1,669,'Sri Lanka');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (749,1,672,'Nepal');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (750,1,675,'Bhutan');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (751,1,676,'Burma');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (752,1,680,'Tayland');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (753,1,684,'Laos');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (754,1,690,'Vietnam');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (755,1,696,'Kamboçya');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (756,1,700,'Endonezya');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (757,1,701,'Malezya');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (758,1,703,'Brunei');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (759,1,706,'Singapur');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (760,1,708,'Filipinler');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (761,1,716,'Moğolistan');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (762,1,720,'Çin Halk Cumhuriyeti');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (763,1,724,'Kuzey Kore Demokratik Halk Cum.');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (764,1,728,'Güney Kore Cumhuriyeti');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (765,1,732,'Japonya');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (766,1,736,'Tayvan');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (767,1,740,'Hong Kong');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (768,1,743,'Makao');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (769,1,800,'Avustralya');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (770,1,801,'Papua Yeni Gine');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (771,1,802,'Avustralya Okyanusu');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (772,1,803,'Nauru');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (773,1,804,'Yeni Zelanda');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (774,1,806,'Solomon Adaları');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (775,1,807,'Tuvalu');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (776,1,808,'Amerikan Okyanusyası');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (777,1,809,'Yeni Kalodenya Ve Bağlantıları');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (778,1,811,'Wallis Ve Futuna Adaları');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (779,1,812,'Kiribati');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (780,1,813,'Pitcairn');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (781,1,814,'Yeni Zelanda Okyanusu');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (782,1,815,'Fiji');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (783,1,816,'Vanuatu');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (784,1,817,'Tonga');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (785,1,819,'Batı Samao');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (786,1,822,'Fransız Polinezyası');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (787,1,890,'Kutup Bölgeleri');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (788,1,901,'Bismark Archipelago');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (789,1,958,'Bilinemeyen Ülke Ve Topraklar');
INSERT INTO `javafun`.`constant_value`(`id_constant_value`,`id_type_fk`,`valueInt`,`valueText`) VALUES (790,1,960,'Serbest Bölgeler');

-- Table `javafun`.`user_info`
CREATE TABLE `user_info` (
  `user_id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `username` varchar(64) CHARACTER SET utf8 NOT NULL,
  `pwd` varchar(64) CHARACTER SET utf8 NOT NULL,
  `email` varchar(45) CHARACTER SET utf8 NOT NULL,
  `id_user_detail_fk` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  KEY `USER_USERDETAIL_FK` (`id_user_detail_fk`),
  CONSTRAINT `USER_USERDETAIL_FK` FOREIGN KEY (`id_user_detail_fk`) REFERENCES `user_detail` (`id_user_detail`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

-- Table `javafun`.`user_detail`
CREATE TABLE `user_detail` (
  `id_user_detail` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `email_personal` varchar(45) NOT NULL,
  `email_work` varchar(45) NOT NULL,
  `id_city_fk` int(10) unsigned NOT NULL,
  `id_country_fk` int(10) unsigned NOT NULL,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `last_login` datetime NOT NULL,
  `gender` char(1) NOT NULL,
  PRIMARY KEY (`id_user_detail`),
  KEY `USERDETAIL_CITY_FK` (`id_city_fk`),
  KEY `USERDETAIL_COUNTRY_FK` (`id_country_fk`),
  CONSTRAINT `USERDETAIL_CITY_FK` FOREIGN KEY (`id_city_fk`) REFERENCES `constant_value` (`id_constant_value`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `USERDETAIL_COUNTRY_FK` FOREIGN KEY (`id_country_fk`) REFERENCES `constant_value` (`id_constant_value`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;


-- Table `javafun`.`blog`
CREATE TABLE `blog` (
  `blog_id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `title` varchar(128) CHARACTER SET utf8 NOT NULL,
  `description` varchar(1024) CHARACTER SET utf8 NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  `created` datetime NOT NULL,
  PRIMARY KEY (`blog_id`) USING BTREE,
  KEY `BLOG_USER_FK` (`user_id`),
  CONSTRAINT `BLOG_USER_FK` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

-- Table `javafun`.`article`
CREATE TABLE `article` (
  `article_id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `title` varchar(256) CHARACTER SET utf8 NOT NULL,
  `body` varchar(10240) CHARACTER SET utf8 NOT NULL,
  `blog_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`article_id`) USING BTREE,
  KEY `ARTICLE_BLOG_FK` (`blog_id`),
  CONSTRAINT `ARTICLE_BLOG_FK` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`blog_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

CREATE TABLE `link` (
  `link_id` INTEGER UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(256) NOT NULL,
  `link_url` VARCHAR256) NOT NULL,
  `id_user_fk` INTEGER UNSIGNED ZEROFILL NOT NULL,
  PRIMARY KEY (`link_id`) USING BTREE,
  KEY `LINK_USERINFO_FK` (`id_user_fk`)
  CONSTRAINT `LINK_USERINFO_FK` FOREIGN KEY (`id_user_fk`) REFERENCES `user_info` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;
