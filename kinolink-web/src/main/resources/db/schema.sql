-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: kinolink
-- ------------------------------------------------------
-- Server version	8.0.16
SET FOREIGN_KEY_CHECKS=0 ;

 SET NAMES utf8 ;


DROP TABLE IF EXISTS `actor_movie`;

 SET character_set_client = utf8mb4 ;
CREATE TABLE `actor_movie` (
  `actor_id` int(11) NOT NULL,
  `movie_id` int(11) NOT NULL,
  PRIMARY KEY (`movie_id`,`actor_id`),
  KEY `FKjdb9dqa4sc0fbw0e6ligl2qkw` (`actor_id`),
  CONSTRAINT `FK10wqik6soqi350gph7sjdgpl4` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`),
  CONSTRAINT `FKjdb9dqa4sc0fbw0e6ligl2qkw` FOREIGN KEY (`actor_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `country`;

 SET character_set_client = utf8mb4 ;
CREATE TABLE `country` (
  `COUNTRY_ID` bigint(20) NOT NULL,
  `COUNTRY_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`COUNTRY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `country_movie`;

 SET character_set_client = utf8mb4 ;
CREATE TABLE `country_movie` (
  `movie_id` int(11) NOT NULL,
  `country_id` bigint(20) NOT NULL,
  PRIMARY KEY (`country_id`,`movie_id`),
  KEY `FKg2ne5f254d453kmegx6asncfq` (`movie_id`),
  CONSTRAINT `FKf69lyb83wd0y1hq0kpddhn4a2` FOREIGN KEY (`country_id`) REFERENCES `country` (`COUNTRY_ID`),
  CONSTRAINT `FKg2ne5f254d453kmegx6asncfq` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `director_movie`;

 SET character_set_client = utf8mb4 ;
CREATE TABLE `director_movie` (
  `director_id` int(11) NOT NULL,
  `movie_id` int(11) NOT NULL,
  PRIMARY KEY (`director_id`,`movie_id`),
  KEY `FKnwpull61vbniarqcas2t6mvtf` (`movie_id`),
  CONSTRAINT `FK8vncwakq8bavffmtcn7ull4pk` FOREIGN KEY (`director_id`) REFERENCES `person` (`id`),
  CONSTRAINT `FKnwpull61vbniarqcas2t6mvtf` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `genre`;

 SET character_set_client = utf8mb4 ;
CREATE TABLE `genre` (
  `ID` bigint(20) NOT NULL,
  `GENRE_TITLE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `genre_movie`;

 SET character_set_client = utf8mb4 ;
CREATE TABLE `genre_movie` (
  `genre_id` bigint(20) NOT NULL,
  `movie_id` int(11) NOT NULL,
  PRIMARY KEY (`movie_id`,`genre_id`),
  KEY `FKmink7rsh2tpclo43knndfhylq` (`genre_id`),
  CONSTRAINT `FK1i84wjq2rxoijqg6miscan9v1` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`),
  CONSTRAINT `FKmink7rsh2tpclo43knndfhylq` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `hibernate_sequence`;

 SET character_set_client = utf8mb4 ;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `movie`;

 SET character_set_client = utf8mb4 ;
CREATE TABLE `movie` (
  `id` int(11) NOT NULL,
  `ALTER_TITLE` varchar(255) DEFAULT NULL,
  `DESCRIPTION` longtext,
  `IMDB` float DEFAULT NULL,
  `IMDB_LINK` varchar(255) DEFAULT NULL,
  `PICTURE_PATH` varchar(255) DEFAULT NULL,
  `RELEASE_DATE` date DEFAULT NULL,
  `TITLE` varchar(255) NOT NULL,
  `TRAILER` longtext,
  `DUBLICATED_MOVIES` bit(1) DEFAULT NULL,
  `dublicatedMovies` tinyint(4) NOT NULL,
  `URL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `person`;

 SET character_set_client = utf8mb4 ;
CREATE TABLE `person` (
  `id` int(11) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `PICTURE_PATH` varchar(255) DEFAULT NULL,
  `RATING` int(11) DEFAULT NULL,
  `URL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


SET FOREIGN_KEY_CHECKS=1 ;


