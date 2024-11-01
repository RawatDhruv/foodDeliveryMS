CREATE TABLE `orders` (
                          `order_number` varchar(255) NOT NULL,
                          `order_time` bigint DEFAULT NULL,
                          `delivery_time` bigint DEFAULT NULL,
                          `order_status` tinyint DEFAULT NULL,
                          `total_amount` decimal(38,2) DEFAULT NULL,
                          `restaurant_id` bigint DEFAULT NULL,
                          `user_id` bigint DEFAULT NULL,
                          `payment_id` varchar(255) DEFAULT NULL,
                          `street` varchar(255) DEFAULT NULL,
                          `state` varchar(255) DEFAULT NULL,
                          `city` varchar(255) DEFAULT NULL,
                          `zipcode` int DEFAULT NULL,
                          PRIMARY KEY (`order_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `order_items` (
                               `id` bigint NOT NULL AUTO_INCREMENT,
                               `food_item_id` bigint DEFAULT NULL,
                               `name` varchar(255) DEFAULT NULL,
                               `quantity` int DEFAULT NULL,
                               `price` decimal(38,2) DEFAULT NULL,
                               `order_id` varchar(255) DEFAULT NULL,
                               PRIMARY KEY (`id`),
                               KEY `FKbioxgbv59vetrxe0ejfubep1w` (`order_id`),
                               CONSTRAINT `FKbioxgbv59vetrxe0ejfubep1w` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;