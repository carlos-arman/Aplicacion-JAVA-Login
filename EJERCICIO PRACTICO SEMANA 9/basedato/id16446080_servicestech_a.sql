-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Oct 20, 2022 at 06:36 AM
-- Server version: 10.5.16-MariaDB
-- PHP Version: 7.3.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id16446080_servicestech_a`
--
CREATE DATABASE IF NOT EXISTS `id16446080_servicestech_a` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `id16446080_servicestech_a`;

-- --------------------------------------------------------

--
-- Table structure for table `tb_categorias`
--

CREATE TABLE `tb_categorias` (
  `id_categoria` int(11) NOT NULL,
  `nom_categoria` varchar(60) COLLATE utf8_spanish_ci NOT NULL,
  `estado_categoria` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `tb_categorias`
--

INSERT INTO `tb_categorias` (`id_categoria`, `nom_categoria`, `estado_categoria`) VALUES
(1, 'Com', 1),
(2, 'Computadora ', 1),
(3, 'Teléfonos ', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tb_productos`
--

CREATE TABLE `tb_productos` (
  `id_producto` int(11) NOT NULL,
  `nom_producto` varchar(60) COLLATE utf8_spanish_ci NOT NULL,
  `des_producto` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `stock` decimal(5,2) NOT NULL,
  `precio` decimal(5,2) NOT NULL,
  `unidad_medida` varchar(10) COLLATE utf8_spanish_ci NOT NULL,
  `estado_producto` int(11) NOT NULL,
  `categoria` int(11) NOT NULL,
  `fecha_entrada` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `tb_productos`
--

INSERT INTO `tb_productos` (`id_producto`, `nom_producto`, `des_producto`, `stock`, `precio`, `unidad_medida`, `estado_producto`, `categoria`, `fecha_entrada`) VALUES
(1, 'Samsung', 'Ultimo modelo', 1.00, 3.00, 'Por unidad', 1, 1, '2022-10-15 20:41:43'),
(2, 'Celular', 'Alcatel 10 gb', 4.00, 200.00, 'Por unidad', 1, 1, '2022-10-16 00:52:43'),
(6, 'Computadora', 'Lenovo', 2.00, 10.00, 'Libra ', 2, 1, '2022-10-15 23:05:47'),
(13, 'Samsung', 'Modelo Ultimate', 12.00, 3.00, 'Por unidad', 3, 1, '2022-10-15 20:42:03'),
(88, 'jjsjs', 'ksjsjs', 1.00, 10.00, 'linra', 1, 1, '2022-10-20 06:07:47');

-- --------------------------------------------------------

--
-- Table structure for table `tb_usuarios`
--

CREATE TABLE `tb_usuarios` (
  `id` int(11) NOT NULL,
  `nombre` varchar(60) COLLATE utf8_spanish_ci NOT NULL,
  `apellidos` varchar(35) COLLATE utf8_spanish_ci NOT NULL,
  `correo` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `usuario` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `clave` varchar(150) COLLATE utf8_spanish_ci NOT NULL,
  `tipo` tinyint(1) NOT NULL,
  `estado` tinyint(1) NOT NULL,
  `pregunta` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `respuesta` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `fecha_registro` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_categorias`
--
ALTER TABLE `tb_categorias`
  ADD PRIMARY KEY (`id_categoria`);

--
-- Indexes for table `tb_productos`
--
ALTER TABLE `tb_productos`
  ADD PRIMARY KEY (`id_producto`),
  ADD KEY `categoria` (`categoria`);

--
-- Indexes for table `tb_usuarios`
--
ALTER TABLE `tb_usuarios`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_productos`
--
ALTER TABLE `tb_productos`
  MODIFY `id_producto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=89;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tb_productos`
--
ALTER TABLE `tb_productos`
  ADD CONSTRAINT `tb_productos_ibfk_1` FOREIGN KEY (`categoria`) REFERENCES `tb_categorias` (`id_categoria`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
