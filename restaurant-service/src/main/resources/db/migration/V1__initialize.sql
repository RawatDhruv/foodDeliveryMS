CREATE TABLE `addresses` (
                             `id` bigint NOT NULL AUTO_INCREMENT,
                             `street` varchar(255) DEFAULT NULL,
                             `zipcode` int DEFAULT NULL,
                             `city` varchar(255) DEFAULT NULL,
                             `state` varchar(255) DEFAULT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `owner_infos` (
                               `id` bigint NOT NULL AUTO_INCREMENT,
                               `username` varchar(255) NOT NULL,
                               `fullname` varchar(255) DEFAULT NULL,
                               `phone_number` varchar(255) DEFAULT NULL,
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `UK8d2o939nnx5333mfwoisgv2lu` (`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `restaurants` (
                               `id` bigint NOT NULL AUTO_INCREMENT,
                               `name` varchar(255) DEFAULT NULL,
                               `description` varchar(255) DEFAULT NULL,
                               `rating` double DEFAULT NULL,
                               `owner_id` bigint DEFAULT NULL,
                               `address_id` bigint DEFAULT NULL,
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `UKqmof4bn52u3t3qtpqlt80ypej` (`address_id`),
                               KEY `FK7rlt8h2259ck6u14ei8ek18gi` (`owner_id`),
                               CONSTRAINT `FK7rlt8h2259ck6u14ei8ek18gi` FOREIGN KEY (`owner_id`) REFERENCES `owner_infos` (`id`),
                               CONSTRAINT `FKf77xr396uxbl0crr0pq0qj2q7` FOREIGN KEY (`address_id`) REFERENCES `addresses` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `contacts` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `phone_number` bigint NOT NULL,
                            `email_address` varchar(255) NOT NULL,
                            `restaurant_id` bigint DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `UK92v40q7dfyegxxeeyfu5hxhsd` (`phone_number`),
                            UNIQUE KEY `UKiold2yl8wm1t4u4tgy8lysuc3` (`email_address`),
                            KEY `FKnde9mdcjsx5apyb1ba2971svs` (`restaurant_id`),
                            CONSTRAINT `FKnde9mdcjsx5apyb1ba2971svs` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurants` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `food_items` (
                              `id` bigint NOT NULL AUTO_INCREMENT,
                              `name` varchar(255) DEFAULT NULL,
                              `description` varchar(255) DEFAULT NULL,
                              `price` decimal(38,2) DEFAULT NULL,
                              `image_url` varchar(255) DEFAULT NULL,
                              `restaurant_id` bigint DEFAULT NULL,
                              `deleted` bit(1) DEFAULT NULL,
                              PRIMARY KEY (`id`),
                              UNIQUE KEY `UKrumkybity72myc6xwj7b56ie2` (`name`,`restaurant_id`),
                              KEY `FK1f3re9f14rtkoyghyuhx3cv9l` (`restaurant_id`),
                              CONSTRAINT `FK1f3re9f14rtkoyghyuhx3cv9l` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurants` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
