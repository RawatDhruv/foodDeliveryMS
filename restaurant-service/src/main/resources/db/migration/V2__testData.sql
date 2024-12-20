INSERT INTO `restaurant_service`.`addresses` (`street`, `city`, `state`, `zipcode`) VALUES ('Botanical Garden Metro Station', 'Noida', 'Uttar Pradesh', '201301');
INSERT INTO `restaurant_service`.`addresses` (`street`, `city`, `state`, `zipcode`) VALUES ('Sector 18', 'Noida', 'Uttar Pradesh', '201301');

INSERT INTO `restaurant_service`.`owner_infos` (`username`, `fullname`, `phone_number`) VALUES ('dhruv', 'Dhruv Rawat', '1234567890');

INSERT INTO `restaurant_service`.`restaurants` (`name`, `description`, `rating`, `address_id`, `owner_id`)
VALUES (
           'Dominos',
           'Pizza Chain',
           '5',
           (SELECT id FROM addresses WHERE name='Botanical Garden Metro Station'),
           (SELECT id FROM owner_infos WHERE username='dhruv')
       );

INSERT INTO `restaurant_service`.`restaurants` (`name`, `description`, `rating`, `address_id`, `owner_id`)
VALUES (
           'Mc Donalds',
           'Burger Chain',
           '4',
           (SELECT id FROM addresses WHERE name='Sector 18'),
           (SELECT id FROM owner_infos WHERE username='dhruv')
       );
INSERT INTO `restaurant_service`.`contacts` (`phone_number`, `restaurant_id`, `email_address`)
VALUES (
           '1234567890',
           (SELECT id FROM restaurants WHERE name='Dominos'),
           'shawn@gmail.com'
       );

INSERT INTO `restaurant_service`.`contacts` (`phone_number`, `restaurant_id`, `email_address`)
VALUES (
           '0987654321',
           (SELECT id FROM restaurants WHERE name='Mc Donalds'),
           'dave@gmail.com'
       );


INSERT INTO `restaurant_service`.`food_items` (`deleted`, `price`, `restaurant_id`, `description`, `image_url`, `name`)
VALUES (
           0,
           500,
           (SELECT id FROM restaurants WHERE name='Dominos'),
           'veg pizza',
           '\\dominos\\Veg ExtraVaganza.jpg',
           'Veg ExtraVaganza'
       );

INSERT INTO `restaurant_service`.`food_items` (`deleted`, `price`, `restaurant_id`, `description`, `image_url`, `name`)
VALUES (
           0,
           600,
           (SELECT id FROM restaurants WHERE name='Dominos'),
           'chicken pizza',
           '\\dominos\\Chicken Dominator.jpg',
           'Chicken Dominator'
       );

INSERT INTO `restaurant_service`.`food_items` (`deleted`, `price`, `restaurant_id`, `description`, `image_url`, `name`)
VALUES (
           0,
           100,
           (SELECT id FROM restaurants WHERE name='Mc Donalds'),
           'aloo tikki burger',
           '\\mcdonalds\\Mc Aloo Tikki.jpg',
           'Mc Aloo Tikki'
       );

INSERT INTO `restaurant_service`.`food_items` (`deleted`, `price`, `restaurant_id`, `description`, `image_url`, `name`)
VALUES (
           0,
           250,
           (SELECT id FROM restaurants WHERE name='Mc Donalds'),
           'spicy paneer burger',
           '\\mcdonalds\\Mc Spicy Paneer.jpg',
           'Mc Spicy Paneer'
       );
