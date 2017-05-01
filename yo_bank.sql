-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Aug 16, 2014 at 11:17 AM
-- Server version: 5.6.11
-- PHP Version: 5.5.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `yo_bank`
--
CREATE DATABASE IF NOT EXISTS `yo_bank` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `yo_bank`;

-- --------------------------------------------------------

--
-- Table structure for table `accavailable`
--

CREATE TABLE IF NOT EXISTS `accavailable` (
  `accid` varchar(5) NOT NULL,
  `acctype` varchar(3) NOT NULL,
  `aod` date NOT NULL,
  `opbal` float NOT NULL,
  `totalbal` float NOT NULL,
  PRIMARY KEY (`accid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `accavailable`
--

INSERT INTO `accavailable` (`accid`, `acctype`, `aod`, `opbal`, `totalbal`) VALUES
('12138', 'CUR', '2014-08-13', 5000, 7000),
('12486', 'FXD', '2014-07-08', 15000, 15000),
('21653', 'SAV', '2014-07-24', 5500, 5500),
('2392', 'FXD', '2014-08-16', 25000, 25000),
('27321', 'YNG', '2014-07-26', 10000, 10000),
('27345', 'CUR', '2014-07-08', 20000, 20000),
('35498', 'SNR', '2014-08-16', 2500, 2500),
('46572', 'SAV', '2014-08-02', 5000, 5000),
('54231', 'CUR', '2014-07-24', 3000, 3000),
('55021', 'YNG', '2014-08-15', 500, 500),
('64769', 'CUR', '2014-08-13', 5000, 5000),
('65428', 'FXD', '2014-07-23', 12000, 12000),
('69289', 'FXD', '2014-08-13', 11000, 11000),
('74586', 'SAV', '2014-07-15', 6000, 6000),
('95321', 'SNR', '2014-07-18', 8000, 8000),
('9659', 'SAV', '2014-08-15', 12000, 12000);

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE IF NOT EXISTS `account` (
  `COUNTER` int(11) NOT NULL AUTO_INCREMENT,
  `accid` varchar(5) NOT NULL,
  `custid` varchar(10) NOT NULL,
  `accname` varchar(25) NOT NULL,
  PRIMARY KEY (`accid`),
  UNIQUE KEY `counter` (`COUNTER`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=22 ;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`COUNTER`, `accid`, `custid`, `accname`) VALUES
(14, '12138', 'C0008', 'CURRENT_ACCOUNT'),
(4, '12486', 'C0002', 'FIXED_ACCOUNT'),
(10, '21653', 'C0005', 'SAVING_ACCOUNT'),
(20, '2392', 'C0001', 'FIXED_ACCOUNT'),
(1, '27321', 'C0001', 'YOUNG_STAR_ACCOUNT'),
(2, '27345', 'C0001', 'CURRENT_ACCOUNT'),
(21, '35498', 'C0021', 'SENIOR_CITIZEN_ACCOUNT'),
(7, '46572', 'C0004', 'SAVING_ACCOUNT'),
(6, '54231', 'C0003', 'CURRENT_ACCOUNT'),
(19, '55021', 'C0014', 'YOUNG_STAR_ACCOUNT'),
(13, '64769', 'C0006', 'CURRENT_ACCOUNT'),
(8, '65428', 'C0004', 'FIXED_ACCOUNT'),
(17, '69289', 'C0010', 'FIXED_ACCOUNT'),
(5, '74586', 'C0003', 'SAVING_ACCOUNT'),
(9, '95321', 'C0005', 'SENIOR_CITIZEN_ACCOUNT'),
(18, '9659', 'C00013', 'SAVING_ACCOUNT');

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

CREATE TABLE IF NOT EXISTS `address` (
  `custid` varchar(10) NOT NULL,
  `flatno` varchar(10) NOT NULL COMMENT 'flat_no',
  `blockno` varchar(10) NOT NULL,
  `sector` varchar(10) DEFAULT NULL COMMENT 'sector',
  `pincode` varchar(10) NOT NULL,
  `city` varchar(30) NOT NULL COMMENT 'area',
  `district` varchar(30) NOT NULL,
  `state` varchar(30) NOT NULL,
  PRIMARY KEY (`custid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `address`
--

INSERT INTO `address` (`custid`, `flatno`, `blockno`, `sector`, `pincode`, `city`, `district`, `state`) VALUES
('C0001', 'G/6', 'D', '30', '110092', 'PATPARGANJ', 'EAST_DELHI', 'DELHI _NCR'),
('C0002', '1/29', 'H', '22', '201305', 'NOIDA', 'GAUTAM BUDH NAGAR', 'UTTAR PRADESH'),
('C0003', '392', 'K', '21', '110091', 'GANESH_NAGAR', 'EAST _DELHI', 'DELHI _NCR'),
('C0004', '26', 'B', '12', '201005', 'INDIRAPURAM', 'GHAZIABAD', 'UTTAR_PRADESH'),
('C0005', '26', 'B', '12', '201005', 'INDIRAPURAM', 'GHAZIABAD', 'UTTAR PRADESH'),
('C0006', '1', '2', '3', '201305', 'AGRA', 'ARGA', 'UTTAR_PRADESH'),
('C0007', '1', '2', '23', '203032', 'KOCHIN', 'KOCHIN', 'KERALA'),
('C0008', '9', 'C', '13', '201302', 'INDIRAPURAM', 'INDIRAPURAM', 'UTTAR_PRADESH'),
('C0009', '4', '4', '10', '201302', 'INDIRAPURAM', 'GHAZIABAD', 'UTTAR_PRADESH'),
('C0010', '6', 'D', '12', '110092', 'PATPARGANJ', 'EAST_DELHI', 'DELHI_NCR'),
('C0011', '2', 'D', '12', '203012', 'NOIDA', 'GAUTAM_BUDH_NAGAR', 'NOIDA'),
('C0013', '4', 'C', '2', '203012', 'DWARKA', 'DWARKA', 'DELHI_NCR'),
('C0014', '2', 'C', '12', '210230', 'LAXMI NAGAR', 'EAST DELHI', 'DELHI NCR'),
('C0021', '2', 'A', '5', '103021', 'PALAMPUR', 'PALAMPUR', 'HIMANCHAL PRADESH');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE IF NOT EXISTS `customer` (
  `name` varchar(40) NOT NULL,
  `age` int(11) NOT NULL,
  `sex` varchar(16) NOT NULL,
  `idtype` varchar(16) NOT NULL,
  `idproof` varchar(16) NOT NULL,
  `custid` varchar(10) NOT NULL,
  PRIMARY KEY (`custid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`name`, `age`, `sex`, `idtype`, `idproof`, `custid`) VALUES
('BHUSHAN', 17, 'MALE', 'PANCARD', 'BUICB215L', 'C0001'),
('VIVEK KUMAR', 22, 'MALE', 'PANCARD', 'VUIVK123L', 'C0002'),
('NIRAJ', 23, 'MALE', 'PANCARD', 'NUINB342L', 'C0003'),
('GOVIND', 22, 'MALE', 'PANCARD', 'GUIGD456L', 'C0004'),
('GAUTAM', 63, 'MALE', 'PANCARD', 'GUIGM175L', 'C0005'),
('MUMTAZ', 24, 'FEMALE', 'PASSPORT', 'PUIPB48557L', 'C0006'),
('SHALU', 25, 'FEMALE', 'PANCARD', 'SUIPB8567L', 'C0007'),
('ANKUSH', 23, 'MALE', 'PANCARD', 'AUIPB8567L', 'C0008'),
('AMBUJ', 24, 'MALE', 'PANCARD', 'JUIJB8567L', 'C0009'),
('PRINSU', 24, 'MALE', 'PANCARD', 'PUIPB8567L', 'C0010'),
('SHALINI', 30, 'FEMALE', 'PANCARD', 'SUISB8213L', 'C0011'),
('HARSHIT', 18, 'MALE', 'PANCARD', 'HUIHB213L', 'C0013'),
('AIYUSH', 17, 'MALE', 'UID', 'AUICH5124P', 'C0014'),
('SAUN PAUL', 62, 'MALE', 'VOTER ID', 'SCVIN5234V', 'C0021');

-- --------------------------------------------------------

--
-- Table structure for table `deposit`
--

CREATE TABLE IF NOT EXISTS `deposit` (
  `accid` varchar(5) NOT NULL,
  `damt` float NOT NULL,
  `ddate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `deposit`
--

INSERT INTO `deposit` (`accid`, `damt`, `ddate`) VALUES
('12138', 3000, '2014-08-16');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE IF NOT EXISTS `employee` (
  `empid` varchar(16) NOT NULL,
  `empidproof` varchar(16) NOT NULL,
  `pass` varchar(16) NOT NULL,
  PRIMARY KEY (`empid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`empid`, `empidproof`, `pass`) VALUES
('E0001', 'cklpk4235l', 'scrutiny'),
('E0002', 'sdakb5424l', 'cscrutiny');

-- --------------------------------------------------------

--
-- Table structure for table `withdraw`
--

CREATE TABLE IF NOT EXISTS `withdraw` (
  `accid` varchar(5) NOT NULL,
  `wamt` float NOT NULL,
  `wdate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `withdraw`
--

INSERT INTO `withdraw` (`accid`, `wamt`, `wdate`) VALUES
('12138', 1000, '2014-08-16');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
