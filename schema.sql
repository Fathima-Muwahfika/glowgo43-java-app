-- Glow & Go 43 — Database Schema
-- Beauty Shop Inventory Management System
-- MySQL / MariaDB

CREATE DATABASE IF NOT EXISTS glowgo43;
USE glowgo43;

-- --------------------------------------------------------
-- Table: categories
-- --------------------------------------------------------
CREATE TABLE `categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------
-- Table: products
-- --------------------------------------------------------
CREATE TABLE `products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `category_id` int(11) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `qty` int(11) NOT NULL,
  `reorder_level` int(11) DEFAULT 10,
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `products_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------
-- Table: users
-- --------------------------------------------------------
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password_hash` varchar(100) NOT NULL,
  `role` enum('assistant','manager') NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------
-- Sample category data (safe to include — no sensitive info)
-- --------------------------------------------------------
INSERT INTO `categories` (`name`) VALUES
('Skincare'),
('Makeup'),
('Haircare'),
('Fragrance'),
('Wellness'),
('Beauty Tool'),
('Body care');

-- --------------------------------------------------------
-- Sample product data (safe to include — no sensitive info)
-- --------------------------------------------------------
INSERT INTO `products` (`name`, `category_id`, `price`, `qty`, `reorder_level`) VALUES
('Vitamin C Serum', 1, 3500.00, 20, 10),
('Lipstick Rose', 2, 1800.00, 9, 10),
('Hair Oil', 3, 2200.00, 17, 10),
('Body Lotion', 1, 2000.00, 13, 10),
('Shampoo', 3, 900.00, 25, 10),
('Perfume', 4, 1600.00, 30, 10),
('Face Roller', 6, 800.00, 15, 10),
('Lotion', 7, 1500.00, 5, 10);

-- --------------------------------------------------------
-- NOTE: User accounts are intentionally NOT included in this
-- schema file. Create your own test users after setup, e.g.:
--
-- INSERT INTO `users` (`username`, `password_hash`, `role`)
-- VALUES ('manager1', 'your_hashed_password', 'manager');
-- --------------------------------------------------------
