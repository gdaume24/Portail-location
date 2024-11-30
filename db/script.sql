DROP DATABASE IF EXISTS my_database;
CREATE DATABASE my_database;  
USE my_database;

CREATE TABLE `USERS` (
  `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
  `email` varchar(255), 
  `name` varchar(255),
  `password` varchar(255)
);

CREATE TABLE `RENTALS` (
  `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `surface` numeric,
  `price` numeric,
  `picture` varchar(255),
  `description` varchar(2000),
  `owner_id` BIGINT NOT NULL,
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE TABLE `MESSAGES` (
  `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
  `rental_id` BIGINT,
  `user_id` BIGINT,
  `message` varchar(2000),
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE UNIQUE INDEX `USERS_index` ON `USERS` (`email`);

ALTER TABLE `RENTALS` ADD FOREIGN KEY (`owner_id`) REFERENCES `USERS` (`id`);

ALTER TABLE `MESSAGES` ADD FOREIGN KEY (`user_id`) REFERENCES `USERS` (`id`);

ALTER TABLE `MESSAGES` ADD FOREIGN KEY (`rental_id`) REFERENCES `RENTALS` (`id`);

INSERT INTO USERS (email, password, name) VALUES ('initial@initial.com', 'Initial@1234', 'USe 1');




-- ALTER TABLE `USERS` ADD FOREIGN KEY (`id`) REFERENCES `RENTALS` (`owner_id`);

-- ALTER TABLE `USERS` ADD FOREIGN KEY (`id`) REFERENCES `MESSAGES` (`user_id`);

-- ALTER TABLE `RENTALS` ADD FOREIGN KEY (`id`) REFERENCES `MESSAGES` (`rental_id`);