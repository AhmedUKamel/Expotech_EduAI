/*!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19-11.4.2-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: EDU_AI_DB
-- ------------------------------------------------------
-- Server version	8.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*M!100616 SET @OLD_NOTE_VERBOSITY=@@NOTE_VERBOSITY, NOTE_VERBOSITY=0 */;

--
-- Table structure for table `access_tokens`
--

DROP TABLE IF EXISTS `access_tokens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `access_tokens` (
  `token` varchar(512) NOT NULL,
  `expiration` datetime(6) NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`token`),
  KEY `FKjxi0wavfc9xw97x1mhuc8nphm` (`user_id`),
  CONSTRAINT `FKjxi0wavfc9xw97x1mhuc8nphm` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `attachments`
--

DROP TABLE IF EXISTS `attachments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attachments` (
  `id` binary(16) NOT NULL,
  `extension` varchar(255) NOT NULL,
  `filename` varchar(255) NOT NULL,
  `format` enum('JPG','JPEG','PNG','DOC','DOCX','TXT','PDF','OTHER') NOT NULL,
  `type` enum('NATIONAL_ID','STUDENT_DOCUMENT','PARENT_DOCUMENT','TEACHER_DOCUMENT','EMPLOYEE_DOCUMENT','OTHER_DOCUMENT') NOT NULL,
  `upload_date` datetime(6) NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrap79tymgdjf1c5x4dla3rekl` (`user_id`),
  CONSTRAINT `FKrap79tymgdjf1c5x4dla3rekl` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `attendances`
--

DROP TABLE IF EXISTS `attendances`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attendances` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `absence_reason` enum('PATIENT','EMERGENCY','NORMAL','OTHER') NOT NULL,
  `date` date NOT NULL,
  `status` enum('PRESENT','ABSENT') NOT NULL,
  `lesson_id` bigint NOT NULL,
  `section_id` int NOT NULL,
  `student_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ATTENDANCE_STUDENT_UNIQUE_CONSTRAINT` (`student_id`,`section_id`),
  KEY `FK4s1oy9mebh4q8nog69l2oko8t` (`lesson_id`),
  KEY `FK3jtnwj00nndvisyssy7tslmt0` (`section_id`),
  CONSTRAINT `FK3jtnwj00nndvisyssy7tslmt0` FOREIGN KEY (`section_id`) REFERENCES `sections` (`id`),
  CONSTRAINT `FK4s1oy9mebh4q8nog69l2oko8t` FOREIGN KEY (`lesson_id`) REFERENCES `lessons` (`id`),
  CONSTRAINT `FK7bm4q4wptspkenhrsjgatdmk0` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `quantity` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `book_chk_1` CHECK ((`quantity` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `buses`
--

DROP TABLE IF EXISTS `buses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `buses` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bus_capacity` int NOT NULL,
  `bus_number` varchar(255) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `driver_name` varchar(255) NOT NULL,
  `driver_number` varchar(255) NOT NULL,
  `route` varchar(255) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `school_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `BUS_NUMBER_UNIQUE_CONSTRAINT` (`bus_number`,`school_id`),
  KEY `FK5ya7vbtlc8ccpkcyromo7ta2h` (`school_id`),
  CONSTRAINT `FK5ya7vbtlc8ccpkcyromo7ta2h` FOREIGN KEY (`school_id`) REFERENCES `schools` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `chat`
--

DROP TABLE IF EXISTS `chat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat` (
  `id` int NOT NULL AUTO_INCREMENT,
  `chat_image` varchar(255) DEFAULT NULL,
  `chat_name` varchar(255) DEFAULT NULL,
  `created_by` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2ckldva7muxq7ivcify9hcu37` (`created_by`),
  CONSTRAINT `FK2ckldva7muxq7ivcify9hcu37` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `chat_messages`
--

DROP TABLE IF EXISTS `chat_messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat_messages` (
  `chat_id` int NOT NULL,
  `messages_id` int NOT NULL,
  UNIQUE KEY `UK_mrq0rmc439okhdws2rxsjjhdn` (`messages_id`),
  KEY `FKb27mi3082eolv7k6tavhgq3wc` (`chat_id`),
  CONSTRAINT `FKb27mi3082eolv7k6tavhgq3wc` FOREIGN KEY (`chat_id`) REFERENCES `chat` (`id`),
  CONSTRAINT `FKjtlh6un2reea4nsgktq7qtao0` FOREIGN KEY (`messages_id`) REFERENCES `message` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `chat_user_set`
--

DROP TABLE IF EXISTS `chat_user_set`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat_user_set` (
  `chat_id` int NOT NULL,
  `user_set_id` bigint NOT NULL,
  PRIMARY KEY (`chat_id`,`user_set_id`),
  KEY `FKt4l3rj4xu5tdcaajm1lur8ljl` (`user_set_id`),
  CONSTRAINT `FKcxxs93g2uqeujx27p6bpkkafq` FOREIGN KEY (`chat_id`) REFERENCES `chat` (`id`),
  CONSTRAINT `FKt4l3rj4xu5tdcaajm1lur8ljl` FOREIGN KEY (`user_set_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cities`
--

DROP TABLE IF EXISTS `cities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cities` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name_ar` varchar(255) NOT NULL,
  `name_en` varchar(255) NOT NULL,
  `name_fr` varchar(255) NOT NULL,
  `country_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `CITY_ENGLISH_NAME_UNIQUE_CONSTRAINT` (`name_en`),
  UNIQUE KEY `CITY_ARABIC_NAME_UNIQUE_CONSTRAINT` (`name_ar`),
  UNIQUE KEY `CITY_FRENCH_NAME_UNIQUE_CONSTRAINT` (`name_fr`),
  KEY `FK6gatmv9dwedve82icy8wrkdmk` (`country_id`),
  CONSTRAINT `FK6gatmv9dwedve82icy8wrkdmk` FOREIGN KEY (`country_id`) REFERENCES `countries` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `classrooms`
--

DROP TABLE IF EXISTS `classrooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `classrooms` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `level` enum('KG1','KG2','GRADE1','GRADE2','GRADE3','GRADE4','GRADE5','GRADE6','GRADE7','GRADE8','GRADE9','GRADE10','GRADE11','GRADE12') NOT NULL,
  `name` varchar(255) NOT NULL,
  `number` int NOT NULL,
  `stage` enum('KINDERGARTEN','PRIMARY','PREPARATORY','SECONDARY') NOT NULL,
  `room_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_da6j948rdjolqa7og6yt5dxdx` (`room_id`),
  CONSTRAINT `FK4msji5x9bfek4i65cmeqjpjgc` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `countries`
--

DROP TABLE IF EXISTS `countries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `countries` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name_ar` varchar(255) NOT NULL,
  `name_en` varchar(255) NOT NULL,
  `name_fr` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `COUNTRY_ENGLISH_NAME_UNIQUE_CONSTRAINT` (`name_en`),
  UNIQUE KEY `COUNTRY_ARABIC_NAME_UNIQUE_CONSTRAINT` (`name_ar`),
  UNIQUE KEY `COUNTRY_FRENCH_NAME_UNIQUE_CONSTRAINT` (`name_fr`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `courses` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `name` varchar(255) NOT NULL,
  `semester` enum('FAIL','SPRING','SUMMER') NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `grade_id` bigint DEFAULT NULL,
  `courses_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `COURSE_UNIQUE_CONSTRAINT` (`name`,`grade_id`),
  KEY `FKk3rrb0vwd0krvndou487f5286` (`grade_id`),
  KEY `FKdfme3s66awjsg50y8dlq6bl8p` (`courses_id`),
  CONSTRAINT `FKdfme3s66awjsg50y8dlq6bl8p` FOREIGN KEY (`courses_id`) REFERENCES `grades` (`id`),
  CONSTRAINT `FKk3rrb0vwd0krvndou487f5286` FOREIGN KEY (`grade_id`) REFERENCES `grades` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `department_details`
--

DROP TABLE IF EXISTS `department_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department_details` (
  `language` enum('ENGLISH','ARABIC','FRENCH') NOT NULL,
  `name` varchar(255) NOT NULL,
  `department_id` int NOT NULL,
  PRIMARY KEY (`department_id`,`language`),
  CONSTRAINT `FKi3smi5qrgpy35kho8qv5qxcyf` FOREIGN KEY (`department_id`) REFERENCES `departments` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `departments`
--

DROP TABLE IF EXISTS `departments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `departments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `head_id` bigint NOT NULL,
  `school_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rpbl99tstvgrcsdpfsx34iheb` (`head_id`),
  KEY `FK9fwvupr4xfhftlrl40k0ga8u5` (`school_id`),
  CONSTRAINT `FK9fwvupr4xfhftlrl40k0ga8u5` FOREIGN KEY (`school_id`) REFERENCES `schools` (`id`),
  CONSTRAINT `FKbl8pi22vx8dv15r86mf4m9gdp` FOREIGN KEY (`head_id`) REFERENCES `employees` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `employee_roles`
--

DROP TABLE IF EXISTS `employee_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee_roles` (
  `employee_id` bigint NOT NULL,
  `employee_roles` enum('ADMIN','SEMESTER_MANAGER','EMPLOYEE_MANAGER','TEACHER_MANAGER','STUDENT_MANAGER','PARENT_MANAGER','BUS_MANAGER','EVENT_MANAGER') DEFAULT NULL,
  KEY `FK3uwwaxeiucvfixgd45etkjgmg` (`employee_id`),
  CONSTRAINT `FK3uwwaxeiucvfixgd45etkjgmg` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employees` (
  `created_date` datetime(6) NOT NULL,
  `hire_date` date DEFAULT NULL,
  `code` int NOT NULL,
  `number` bigint NOT NULL,
  `salary` double DEFAULT NULL,
  `updated_date` datetime(6) NOT NULL,
  `id` bigint NOT NULL,
  `position_id` int DEFAULT NULL,
  `school_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `EMPLOYEE_PHONE_NUMBER_UNIQUE_CONSTRAINT` (`code`,`number`),
  KEY `FKngcpgx7fx5kednw3m7u0u8of3` (`position_id`),
  KEY `FKogp1ei47gy6bd19kx203rdowg` (`school_id`),
  CONSTRAINT `FKd6th9xowehhf1kmmq1dsseq28` FOREIGN KEY (`id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKngcpgx7fx5kednw3m7u0u8of3` FOREIGN KEY (`position_id`) REFERENCES `positions` (`id`),
  CONSTRAINT `FKogp1ei47gy6bd19kx203rdowg` FOREIGN KEY (`school_id`) REFERENCES `schools` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `event_attendees`
--

DROP TABLE IF EXISTS `event_attendees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event_attendees` (
  `event_id` bigint NOT NULL,
  `attendee_id` bigint NOT NULL,
  PRIMARY KEY (`event_id`,`attendee_id`),
  KEY `FKg2y8vx9p77dpinlj5gk389rgr` (`attendee_id`),
  CONSTRAINT `FKg0w14vgqmpawqmil4fceac4yl` FOREIGN KEY (`event_id`) REFERENCES `events` (`id`),
  CONSTRAINT `FKg2y8vx9p77dpinlj5gk389rgr` FOREIGN KEY (`attendee_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `event_details`
--

DROP TABLE IF EXISTS `event_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event_details` (
  `language` enum('ENGLISH','ARABIC','FRENCH') NOT NULL,
  `description` text NOT NULL,
  `title` varchar(255) NOT NULL,
  `event_id` bigint NOT NULL,
  PRIMARY KEY (`event_id`,`language`),
  CONSTRAINT `FK47othn79xry1mqxc3rvmvr3a` FOREIGN KEY (`event_id`) REFERENCES `events` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `events` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `event_end_time` datetime(6) NOT NULL,
  `event_start_time` datetime(6) NOT NULL,
  `file` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) NOT NULL,
  `creator_id` bigint NOT NULL,
  `school_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7ljm71n1057envlomdxcni5hs` (`creator_id`),
  KEY `FK4odm33jiqyf4hwqui3x3i0c0n` (`school_id`),
  CONSTRAINT `FK4odm33jiqyf4hwqui3x3i0c0n` FOREIGN KEY (`school_id`) REFERENCES `schools` (`id`),
  CONSTRAINT `FK7ljm71n1057envlomdxcni5hs` FOREIGN KEY (`creator_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `exam_results`
--

DROP TABLE IF EXISTS `exam_results`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exam_results` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `score` float NOT NULL,
  `score_date` datetime(6) NOT NULL,
  `status` enum('PASSED','FAILED') NOT NULL,
  `exam_id` bigint NOT NULL,
  `student_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtf85ht7yquiorwjx2xbdx3fxw` (`exam_id`),
  KEY `FKr7qgl670f47u65kkdm8ex5119` (`student_id`),
  CONSTRAINT `FKr7qgl670f47u65kkdm8ex5119` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`),
  CONSTRAINT `FKtf85ht7yquiorwjx2xbdx3fxw` FOREIGN KEY (`exam_id`) REFERENCES `exams` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `exams`
--

DROP TABLE IF EXISTS `exams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exams` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `end_at` datetime(6) NOT NULL,
  `name` varchar(255) NOT NULL,
  `notice_published` bit(1) NOT NULL,
  `result_published` bit(1) NOT NULL,
  `start_at` datetime(6) NOT NULL,
  `term` varchar(255) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `school_id` int NOT NULL,
  `semester_id` int NOT NULL,
  `user_id` bigint NOT NULL,
  `exams_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK58snu3x30ly9owm86j8bqbrd2` (`school_id`),
  KEY `FK6jjck0hpbc4bmg6kmr9q86in3` (`semester_id`),
  KEY `FKi63cpl1xkgy32iq68ru4ypjn4` (`user_id`),
  KEY `FKghvjeh2ndi6pcbtggjcuf9l4c` (`exams_id`),
  CONSTRAINT `FK58snu3x30ly9owm86j8bqbrd2` FOREIGN KEY (`school_id`) REFERENCES `schools` (`id`),
  CONSTRAINT `FK6jjck0hpbc4bmg6kmr9q86in3` FOREIGN KEY (`semester_id`) REFERENCES `semesters` (`id`),
  CONSTRAINT `FKghvjeh2ndi6pcbtggjcuf9l4c` FOREIGN KEY (`exams_id`) REFERENCES `courses` (`id`),
  CONSTRAINT `FKi63cpl1xkgy32iq68ru4ypjn4` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `grades`
--

DROP TABLE IF EXISTS `grades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grades` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `school_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKk9qgl9w0cqoytrphl4sp5io8b` (`school_id`),
  CONSTRAINT `FKk9qgl9w0cqoytrphl4sp5io8b` FOREIGN KEY (`school_id`) REFERENCES `schools` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `interactions`
--

DROP TABLE IF EXISTS `interactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `interactions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `creation_date` datetime(6) NOT NULL,
  `description` varchar(255) NOT NULL,
  `type` enum('PARENT_TO_TEACHER','STUDENT_TO_TEACHER','TEACHER_TO_STUDENT') NOT NULL,
  `parent_id` bigint DEFAULT NULL,
  `student_id` bigint DEFAULT NULL,
  `teacher_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK78qna6l6lg8klt6k1k8tno511` (`parent_id`),
  KEY `FKc8b3n09byo7kg4g1vo6gptht0` (`student_id`),
  KEY `FKe32svh7xdgyojahqkaa92fgp5` (`teacher_id`),
  CONSTRAINT `FK78qna6l6lg8klt6k1k8tno511` FOREIGN KEY (`parent_id`) REFERENCES `parents` (`id`),
  CONSTRAINT `FKc8b3n09byo7kg4g1vo6gptht0` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`),
  CONSTRAINT `FKe32svh7xdgyojahqkaa92fgp5` FOREIGN KEY (`teacher_id`) REFERENCES `teachers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `labs`
--

DROP TABLE IF EXISTS `labs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `labs` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `type` enum('CHEMISTRY','PHYSICS','BIOLOGY','COMPUTER','ENGINEERING','MEDICAL','RESEARCH','LANGUAGE','OTHERS') NOT NULL,
  `room_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_mv0u2oy6rphp4g5wdeh4364a7` (`room_id`),
  CONSTRAINT `FKc1o5cobqofad80oxfkm0ajy0i` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lessons`
--

DROP TABLE IF EXISTS `lessons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lessons` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `course_id` bigint NOT NULL,
  `lessons_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK17ucc7gjfjddsyi0gvstkqeat` (`course_id`),
  KEY `FKbmfyi48qu23125b33wkaxie4b` (`lessons_id`),
  CONSTRAINT `FK17ucc7gjfjddsyi0gvstkqeat` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`),
  CONSTRAINT `FKbmfyi48qu23125b33wkaxie4b` FOREIGN KEY (`lessons_id`) REFERENCES `courses` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `local_date_time` datetime(6) DEFAULT NULL,
  `chat_id` int DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmejd0ykokrbuekwwgd5a5xt8a` (`chat_id`),
  KEY `FKpdrb79dg3bgym7pydlf9k3p1n` (`user_id`),
  CONSTRAINT `FKmejd0ykokrbuekwwgd5a5xt8a` FOREIGN KEY (`chat_id`) REFERENCES `chat` (`id`),
  CONSTRAINT `FKpdrb79dg3bgym7pydlf9k3p1n` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `notice_details`
--

DROP TABLE IF EXISTS `notice_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notice_details` (
  `language` enum('ENGLISH','ARABIC','FRENCH') NOT NULL,
  `description` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `notice_id` bigint NOT NULL,
  PRIMARY KEY (`language`,`notice_id`),
  KEY `FKfxhxi107ddibvja7df3kou7t0` (`notice_id`),
  CONSTRAINT `FKfxhxi107ddibvja7df3kou7t0` FOREIGN KEY (`notice_id`) REFERENCES `notices` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `notices`
--

DROP TABLE IF EXISTS `notices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notices` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `creation_date` datetime(6) NOT NULL,
  `pdf` varchar(255) NOT NULL,
  `update_date` datetime(6) NOT NULL,
  `school_id` int NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK98pwy06fm1aicue0jqqloin89` (`school_id`),
  KEY `FKerqkxcii6jcl64muyrcxm10sp` (`user_id`),
  CONSTRAINT `FK98pwy06fm1aicue0jqqloin89` FOREIGN KEY (`school_id`) REFERENCES `schools` (`id`),
  CONSTRAINT `FKerqkxcii6jcl64muyrcxm10sp` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `notification_details`
--

DROP TABLE IF EXISTS `notification_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification_details` (
  `language` enum('ENGLISH','ARABIC','FRENCH') NOT NULL,
  `message` varchar(255) NOT NULL,
  `notification_id` bigint NOT NULL,
  PRIMARY KEY (`language`,`notification_id`),
  KEY `FKntl0rtff4d9f632d2hrvrj8e9` (`notification_id`),
  CONSTRAINT `FKntl0rtff4d9f632d2hrvrj8e9` FOREIGN KEY (`notification_id`) REFERENCES `notifications` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notifications` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `offices`
--

DROP TABLE IF EXISTS `offices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `offices` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `type` enum('PRINCIPAL','VICE_PRINCIPAL','STUDENT_AFFAIRS','FINANCE','ACCOUNTS','HUMAN_RESOURCES','IT','ACADEMIC_AFFAIRS','LIBRARY','HEALTH_SERVICES','COUNSELING','MAINTENANCE','SECURITY','OTHERS') NOT NULL,
  `room_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_j856lkc7t7tqe91dt4mq26yvj` (`room_id`),
  CONSTRAINT `FKxkxdkcyxdpkbjdnsymjuosx` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `parent_details`
--

DROP TABLE IF EXISTS `parent_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parent_details` (
  `language` enum('ENGLISH','ARABIC','FRENCH') NOT NULL,
  `occupation` varchar(255) NOT NULL,
  `parent_id` bigint NOT NULL,
  PRIMARY KEY (`language`,`parent_id`),
  KEY `FKtftvycvyrqwoi1pfsnexj5dv3` (`parent_id`),
  CONSTRAINT `FKtftvycvyrqwoi1pfsnexj5dv3` FOREIGN KEY (`parent_id`) REFERENCES `parents` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `parent_student_association`
--

DROP TABLE IF EXISTS `parent_student_association`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parent_student_association` (
  `created_at` datetime(6) NOT NULL,
  `type` enum('FATHER','MOTHER','GUARDIAN','STEPFATHER','STEPMOTHER','GRANDFATHER','GRANDMOTHER','UNCLE','AUNT','LEGAL_GUARDIAN','FOSTER_PARENT','OTHER') NOT NULL,
  `parent_id` bigint NOT NULL,
  `student_id` bigint NOT NULL,
  PRIMARY KEY (`parent_id`,`student_id`),
  KEY `FKesswpb0mf6ss2sbwrsmicmla5` (`student_id`),
  CONSTRAINT `FKadjl35opdpscm258owhjdgo1x` FOREIGN KEY (`parent_id`) REFERENCES `parents` (`id`),
  CONSTRAINT `FKesswpb0mf6ss2sbwrsmicmla5` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `parents`
--

DROP TABLE IF EXISTS `parents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parents` (
  `created_date` datetime(6) NOT NULL,
  `code` int NOT NULL,
  `number` bigint NOT NULL,
  `updated_date` datetime(6) NOT NULL,
  `id` bigint NOT NULL,
  `school_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `PARENT_PHONE_NUMBER_UNIQUE_CONSTRAINT` (`code`,`number`),
  KEY `FKatq0lg3m5wavlfe3p7kfkvwpj` (`school_id`),
  CONSTRAINT `FKatq0lg3m5wavlfe3p7kfkvwpj` FOREIGN KEY (`school_id`) REFERENCES `schools` (`id`),
  CONSTRAINT `FKbunaue5n1kwq8j49jeqvgu0f3` FOREIGN KEY (`id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `position_details`
--

DROP TABLE IF EXISTS `position_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `position_details` (
  `language` enum('ENGLISH','ARABIC','FRENCH') NOT NULL,
  `title` varchar(255) NOT NULL,
  `position_id` int NOT NULL,
  PRIMARY KEY (`language`,`position_id`),
  KEY `FK2kwy17bv01akupwjvq5yyq4un` (`position_id`),
  CONSTRAINT `FK2kwy17bv01akupwjvq5yyq4un` FOREIGN KEY (`position_id`) REFERENCES `positions` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `positions`
--

DROP TABLE IF EXISTS `positions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `positions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `department_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7lkgjivwf8cd9o6s4h0ule242` (`department_id`),
  CONSTRAINT `FK7lkgjivwf8cd9o6s4h0ule242` FOREIGN KEY (`department_id`) REFERENCES `departments` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `regions`
--

DROP TABLE IF EXISTS `regions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `regions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `name_ar` varchar(255) NOT NULL,
  `name_en` varchar(255) NOT NULL,
  `name_fr` varchar(255) NOT NULL,
  `zip_code` int NOT NULL,
  `city_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `REGION_ENGLISH_NAME_UNIQUE_CONSTRAINT` (`name_en`),
  UNIQUE KEY `REGION_ARABIC_NAME_UNIQUE_CONSTRAINT` (`name_ar`),
  UNIQUE KEY `REGION_FRENCH_NAME_UNIQUE_CONSTRAINT` (`name_fr`),
  UNIQUE KEY `REGION_LOCATION_UNIQUE_CONSTRAINT` (`latitude`,`longitude`),
  KEY `FKauwnrrif8787f1jaddkxe35go` (`city_id`),
  CONSTRAINT `FKauwnrrif8787f1jaddkxe35go` FOREIGN KEY (`city_id`) REFERENCES `cities` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rooms` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `building_number` varchar(255) NOT NULL,
  `category` enum('CLASSROOM','LAB','LIBRARY','COMPUTER_ROOM','TEACHER_ROOM','OFFICE','RECEPTION','CONFERENCE_ROOM') NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `floor_number` int NOT NULL,
  `max_capacity` int NOT NULL,
  `room_number` varchar(255) NOT NULL,
  `status` enum('AVAILABLE','UNAVAILABLE') NOT NULL,
  `type` enum('EDUCATIONAL','ADMINISTRATIVE','FACILITIES_SERVICES','SPECIALIZED') NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `school_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ROOM_NUMBER_UNIQUE_CONSTRAINT` (`room_number`,`school_id`),
  KEY `FK2y5tff2lifa7321sroti7vcrd` (`school_id`),
  CONSTRAINT `FK2y5tff2lifa7321sroti7vcrd` FOREIGN KEY (`school_id`) REFERENCES `schools` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `schools`
--

DROP TABLE IF EXISTS `schools`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schools` (
  `id` int NOT NULL AUTO_INCREMENT,
  `about` text NOT NULL,
  `code` varchar(255) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `established` date NOT NULL,
  `language` enum('ENGLISH','ARABIC','FRENCH') NOT NULL,
  `name` varchar(255) NOT NULL,
  `theme` varchar(255) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `SCHOOL_NAME_UNIQUE_CONSTRAINT` (`name`),
  UNIQUE KEY `SCHOOL_CODE_UNIQUE_CONSTRAINT` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sections`
--

DROP TABLE IF EXISTS `sections`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sections` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `name` varchar(255) NOT NULL,
  `number` varchar(255) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `classroom_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl5gjakxtykif06u2qyylwygve` (`classroom_id`),
  CONSTRAINT `FKl5gjakxtykif06u2qyylwygve` FOREIGN KEY (`classroom_id`) REFERENCES `classrooms` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `semesters`
--

DROP TABLE IF EXISTS `semesters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `semesters` (
  `id` int NOT NULL AUTO_INCREMENT,
  `creation_date` datetime(6) NOT NULL,
  `end_date` date NOT NULL,
  `name` enum('FAIL','SPRING','SUMMER') NOT NULL,
  `start_date` date NOT NULL,
  `update_date` datetime(6) NOT NULL,
  `school_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKj1laexoffj4xgt7oujpch25qt` (`school_id`),
  CONSTRAINT `FKj1laexoffj4xgt7oujpch25qt` FOREIGN KEY (`school_id`) REFERENCES `schools` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `student_activities`
--

DROP TABLE IF EXISTS `student_activities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_activities` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date_time` datetime(6) NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `school_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd9raae88v0av6otlh3nph16jr` (`school_id`),
  CONSTRAINT `FKd9raae88v0av6otlh3nph16jr` FOREIGN KEY (`school_id`) REFERENCES `schools` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `student_activity_details`
--

DROP TABLE IF EXISTS `student_activity_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_activity_details` (
  `language` enum('ENGLISH','ARABIC','FRENCH') NOT NULL,
  `description` text NOT NULL,
  `location` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `student_activity_id` bigint NOT NULL,
  PRIMARY KEY (`language`,`student_activity_id`),
  KEY `FKspfhtypn6tqpq2n6psn0mqbpd` (`student_activity_id`),
  CONSTRAINT `FKspfhtypn6tqpq2n6psn0mqbpd` FOREIGN KEY (`student_activity_id`) REFERENCES `student_activities` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `students` (
  `created_date` datetime(6) NOT NULL,
  `updated_date` datetime(6) NOT NULL,
  `id` bigint NOT NULL,
  `parent_id` bigint DEFAULT NULL,
  `school_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7bbpphkk8f0aoav3iiih3mh4e` (`parent_id`),
  KEY `FKdojmg8v3rw2ow4dev2b8q5oqq` (`school_id`),
  CONSTRAINT `FK7bbpphkk8f0aoav3iiih3mh4e` FOREIGN KEY (`parent_id`) REFERENCES `parents` (`id`),
  CONSTRAINT `FK7xqmtv7r2eb5axni3jm0a80su` FOREIGN KEY (`id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKdojmg8v3rw2ow4dev2b8q5oqq` FOREIGN KEY (`school_id`) REFERENCES `schools` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `teachers`
--

DROP TABLE IF EXISTS `teachers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teachers` (
  `created_date` datetime(6) NOT NULL,
  `code` int NOT NULL,
  `number` bigint NOT NULL,
  `qualification` enum('HIGH_SCHOOL_DIPLOMA','BACHELOR_DEGREE','MASTER_DEGREE','DOCTORATE_DEGREE') NOT NULL,
  `updated_date` datetime(6) NOT NULL,
  `id` bigint NOT NULL,
  `school_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `TEACHER_PHONE_NUMBER_UNIQUE_CONSTRAINT` (`code`,`number`),
  KEY `FK25tvrvw3ww2p7mbt62abrbwev` (`school_id`),
  CONSTRAINT `FK25tvrvw3ww2p7mbt62abrbwev` FOREIGN KEY (`school_id`) REFERENCES `schools` (`id`),
  CONSTRAINT `FKpavufmal5lbtc60csriy8sx3` FOREIGN KEY (`id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `transaction_book`
--

DROP TABLE IF EXISTS `transaction_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction_book` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `book_id` int DEFAULT NULL,
  `transaction_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhsxq37tgwi588bbkodhq21dhq` (`book_id`),
  KEY `FKtptoe3935cgggbcq4a3ubj6x4` (`transaction_id`),
  CONSTRAINT `FKhsxq37tgwi588bbkodhq21dhq` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  CONSTRAINT `FKtptoe3935cgggbcq4a3ubj6x4` FOREIGN KEY (`transaction_id`) REFERENCES `transaction_history` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `transaction_history`
--

DROP TABLE IF EXISTS `transaction_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `issued_data` datetime(6) DEFAULT NULL,
  `user_language` enum('ENGLISH','ARABIC','FRENCH') DEFAULT NULL,
  `user_user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKos3j93dg51skxonnetgp42etx` (`user_language`,`user_user_id`),
  CONSTRAINT `FKos3j93dg51skxonnetgp42etx` FOREIGN KEY (`user_language`, `user_user_id`) REFERENCES `user_details` (`language`, `user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_details`
--

DROP TABLE IF EXISTS `user_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_details` (
  `language` enum('ENGLISH','ARABIC','FRENCH') NOT NULL,
  `name` varchar(255) NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`language`,`user_id`),
  KEY `FKicouhgavvmiiohc28mgk0kuj5` (`user_id`),
  CONSTRAINT `FKicouhgavvmiiohc28mgk0kuj5` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_notifications`
--

DROP TABLE IF EXISTS `user_notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_notifications` (
  `is_read` bit(1) NOT NULL DEFAULT b'0',
  `notification_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`notification_id`,`user_id`),
  KEY `FK9f86wonnl11hos1cuf5fibutl` (`user_id`),
  CONSTRAINT `FK9f86wonnl11hos1cuf5fibutl` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKovvx0ab3h8s9lrm6fppuadn7d` FOREIGN KEY (`notification_id`) REFERENCES `notifications` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `about` varchar(255) NOT NULL,
  `account_non_locked` bit(1) NOT NULL DEFAULT b'1',
  `birth_date` date NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `email` varchar(255) NOT NULL,
  `enabled` bit(1) NOT NULL DEFAULT b'0',
  `gender` enum('MALE','FEMALE') NOT NULL,
  `nationality` enum('AFGHAN','ALBANIAN','ALGERIAN','AMERICAN','ANDORRAN','ANGOLAN','ANTIGUANS','ARGENTINEAN','ARMENIAN','AUSTRALIAN','AUSTRIAN','AZERBAIJANI','BAHAMIAN','BAHRAINI','BANGLADESHI','BARBADIAN','BARBUDANS','BATSWANA','BELARUSIAN','BELGIAN','BELIZEAN','BENINESE','BHUTANESE','BOLIVIAN','BOSNIAN','BRAZILIAN','BRITISH','BRUNEIAN','BULGARIAN','BURKINABE','BURMESE','BURUNDIAN','CAMBODIAN','CAMEROONIAN','CANADIAN','CAPE_VERDEAN','CENTRAL_AFRICAN','CHADIAN','CHILEAN','CHINESE','COLOMBIAN','COMORAN','CONGOLESE','COSTA_RICAN','CROATIAN','CUBAN','CYPRIOT','CZECH','DANISH','DJIBOUTI','DOMINICAN','DUTCH','DUTCHMAN','DUTCHWOMAN','EAST_TIMORESE','ECUADOREAN','EGYPTIAN','EMIRIAN','EQUATORIAL_GUINEAN','ERITREAN','ESTONIAN','ETHIOPIAN','FIJIAN','FILIPINO','FINNISH','FRENCH','GABONESE','GAMBIAN','GEORGIAN','GERMAN','GHANAIAN','GREEK','GRENADIAN','GUATEMALAN','GUINEA_BISSAUAN','GUINEAN','GUYANESE','HAITIAN','HERZEGOVINIAN','HONDURAN','HUNGARIAN','I_KIRIBATI','ICELANDER','INDIAN','INDONESIAN','IRANIAN','IRAQI','IRISH','ISRAELI','ITALIAN','IVORIAN','JAMAICAN','JAPANESE','JORDANIAN','KAZAKHSTANI','KENYAN','KITTIAN_AND_NEVISIAN','KUWAITI','KYRGYZ','LAOTIAN','LATVIAN','LEBANESE','LIBERIAN','LIBYAN','LIECHTENSTEINER','LITHUANIAN','LUXEMBOURGER','MACEDONIAN','MALAGASY','MALAWIAN','MALAYSIAN','MALDIVAN','MALIAN','MALTESE','MARSHALLESE','MAURITANIAN','MAURITIAN','MEXICAN','MICRONESIAN','MOLDOVAN','MONACAN','MONGOLIAN','MOROCCAN','MOSOTHO','MOTSWANA','MOZAMBICAN','NAMIBIAN','NAURUAN','NEPALESE','NETHERLANDER','NEW_ZEALANDER','NI_VANUATU','NICARAGUAN','NIGERIAN','NIGERIEN','NORTH_KOREAN','NORTHERN_IRISH','NORWEGIAN','OMANI','PAKISTANI','PALAUAN','PANAMANIAN','PAPUA_NEW_GUINEAN','PARAGUAYAN','PERUVIAN','POLISH','PORTUGUESE','QATARI','ROMANIAN','RUSSIAN','RWANDAN','SAINT_LUCIAN','SALVADORAN','SAMOAN','SAN_MARINESE','SAO_TOMEAN','SAUDI','SCOTTISH','SENEGALESE','SERBIAN','SEYCHELLOIS','SIERRA_LEONEAN','SINGAPOREAN','SLOVAKIAN','SLOVENIAN','SOLOMON_ISLANDER','SOMALI','SOUTH_AFRICAN','SOUTH_KOREAN','SPANISH','SRI_LANKAN','SUDANESE','SURINAMER','SWAZI','SWEDISH','SWISS','SYRIAN','TAIWANESE','TAJIK','TANZANIAN','THAI','TOGOLESE','TONGAN','TRINIDADIAN_OR_TOBAGONIAN','TUNISIAN','TURKISH','TUVALUAN','UGANDAN','UKRAINIAN','URUGUAYAN','UZBEKISTANI','VENEZUELAN','VIETNAMESE','WELSH','YEMENITE','ZAMBIAN','ZIMBABWEAN') NOT NULL,
  `nid` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `religion` enum('MUSLIM','CHRISTIAN','OTHERS') NOT NULL,
  `role` enum('SUPER_ADMIN','ADMIN','TEACHER','STUDENT','PARENT','EMPLOYEE') NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `username` varchar(255) NOT NULL,
  `region_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `USER_USERNAME_UNIQUE_CONSTRAINT` (`username`),
  UNIQUE KEY `USER_EMAIL_UNIQUE_CONSTRAINT` (`email`),
  UNIQUE KEY `USER_NATIONAL_ID_UNIQUE_CONSTRAINT` (`nid`),
  UNIQUE KEY `USER_PICTURE_UNIQUE_CONSTRAINT` (`picture`),
  KEY `FK4muym4ujsr1xfh4qc3wsmmrhe` (`region_id`),
  CONSTRAINT `FK4muym4ujsr1xfh4qc3wsmmrhe` FOREIGN KEY (`region_id`) REFERENCES `regions` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*M!100616 SET NOTE_VERBOSITY=@OLD_NOTE_VERBOSITY */;

-- Dump completed on 2024-07-02 19:16:40
