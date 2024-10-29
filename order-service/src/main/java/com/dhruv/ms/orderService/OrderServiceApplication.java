package com.dhruv.ms.orderService;

import com.dhruv.ms.orderService.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@RequiredArgsConstructor
@Slf4j
public class OrderServiceApplication {


	private OrderService orderService;

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@KafkaListener(topics = "payment-notification-topic")
	public void receivePaymentNotification(List<String> paymentInfo) {
		log.info("Received payment notification for order id: {}.", paymentInfo.get(1));
		orderService.updateOrderAfterPayment(paymentInfo);
	}

}
