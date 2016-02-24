CREATE DATABASE users;

DROP TABLE IF EXISTS users.`users`;

CREATE TABLE users.`users` (
  `userid` int(10) PRIMARY KEY NOT NULL,
  `username` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `inventoryflag` varchar(5) DEFAULT NULL,
  `orderflag` varchar(5) DEFAULT NULL,
  `shippingflag` varchar(5) DEFAULT NULL  
);

INSERT INTO users.`users` values (1, 'inventoryuser', 'inventorypass', 'true', 'false', 'false');
INSERT INTO users.`users` values (2, 'orderuser', 'orderpass', 'false', 'true', 'false');
INSERT INTO users.`users` values (3, 'shippinguser', 'shippingpass', 'false', 'false', 'true');

DROP TABLE IF EXISTS users.`loginactivity`;

CREATE TABLE users.`loginactivity` (
  `id` int(10) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `userid` int(10) NOT NULL,
  `timein` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `timeout` TIMESTAMP DEFAULT CURRENT_TIMESTAMP  
);