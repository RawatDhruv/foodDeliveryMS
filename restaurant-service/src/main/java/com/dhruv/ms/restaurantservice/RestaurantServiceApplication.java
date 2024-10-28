package com.dhruv.ms.restaurantservice;

import com.dhruv.ms.restaurantservice.utils.OrderPlacedNotification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class RestaurantServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantServiceApplication.class, args);
	}
	@KafkaListener(topics = "order-rest-notification-topic")
	public void receiveNotification(OrderPlacedNotification notification) {
		log.debug("Received notification for order id: {}.", notification.getOrderId());
		log.debug("Mixing Ingredients");
		log.debug("Cooking");
		log.debug("Packing");
		log.debug("Out For Delivery");
		log.debug("Delivered");
	}

}
