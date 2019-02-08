-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 08, 2019 at 03:43 PM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kasoty`
--

-- --------------------------------------------------------

--
-- Table structure for table `scoreboard`
--

CREATE TABLE `scoreboard` (
  `TNO` int(11) NOT NULL,
  `TNAME` varchar(500) NOT NULL,
  `TSCORE` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `scoreboard`
--

INSERT INTO `scoreboard` (`TNO`, `TNAME`, `TSCORE`) VALUES
(1, 't1', NULL),
(2, 't2', NULL),
(3, 't3', NULL),
(4, 't4', NULL),
(5, 't5', NULL),
(1, 'asdads', NULL),
(2, 'asdasd', NULL),
(3, 'asdasd', NULL),
(4, 'asdasd', NULL),
(5, 'asdasd', NULL),
(1, 'asdasd', NULL),
(2, 'asdasd', NULL),
(3, 'asdasd', NULL),
(4, 'asdasd', NULL),
(5, 'asdasd', NULL);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
