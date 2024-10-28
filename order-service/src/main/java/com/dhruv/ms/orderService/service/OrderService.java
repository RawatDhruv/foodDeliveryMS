package com.dhruv.ms.orderService.service;

import com.dhruv.ms.orderService.config.WebClientConfig;
import com.dhruv.ms.orderService.dto.OrderItemDto;
import com.dhruv.ms.orderService.dto.OrderResponse;
import com.dhruv.ms.orderService.event.OrderPlacedNotification;
import com.dhruv.ms.orderService.model.Order;
import com.dhruv.ms.orderService.model.OrderStatus;
import com.dhruv.ms.orderService.repository.OrderRespository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.dhruv.ms.orderService.model.OrderItem;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    @Autowired
    private OrderRespository orderRespository;
    @Autowired
    private WebClient.Builder webClientBuilder;;
    private final KafkaTemplate<String, OrderPlacedNotification> kafkaTemplate;

    public OrderResponse createOrder(Order order) {
        order.setOrderTime(System.currentTimeMillis());
        order.setTotalAmount(order.getOrderItems()
                .stream()
                .map(e -> e.getPrice().multiply(BigDecimal.valueOf(e.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        order.setOrderStatus(OrderStatus.PENDING);
        orderRespository.save(order);

        List<String> foodItemIds = order.getOrderItems().stream().map(OrderItem::getFoodItemId).toList();
        List<Integer> orderQuantities = order.getOrderItems().stream().map(OrderItem::getQuantity).toList();

        return OrderResponse.builder()
                .orderItems(order.getOrderItems().stream().map(orderItem -> OrderItemDto.builder()
                        .name(orderItem.getName())
                        .price(orderItem.getPrice())
                        .quantity(orderItem.getQuantity())
                        .build()).toList())
                .totalAmount(order.getTotalAmount())
                .orderTime(order.getOrderTime())
                .orderStatus(order.getOrderStatus())
                .address(order.getAddress())
                .build();

    }

    public void updateOrderAfterPayment(List<String> paymentInfo){
        Optional<Order> optionalOrder = orderRespository.findById(Long.valueOf(paymentInfo.get(1)));
        if(optionalOrder.isPresent()){
            Order order = optionalOrder.get();
            if(paymentInfo.get(2).equals("SUCCESS")){
                order.setPaymentId(paymentInfo.get(0));
                order.setOrderStatus(OrderStatus.COMPLETED);
                order.setDeliveryTime(order.getDeliveryTime() + 30*60*1000);
                orderRespository.save(order);

                sendNotificationToRestaurant(order);
            } else{
                order.setOrderStatus(OrderStatus.CANCELLED);
                orderRespository.save(order);
            }
        }
    }

    private void sendNotificationToRestaurant(Order order) {
        OrderPlacedNotification orderPlacedNotification = OrderPlacedNotification.builder()
                .orderId(order.getId())
                .userId(order.getUserId())
                .orderAddress(order.getAddress())
                .foodItemIds(order.getOrderItems().stream()
                        .map(OrderItem::getFoodItemId).toList())
                .foodItemQuantities(order.getOrderItems().stream()
                        .map(OrderItem::getQuantity).toList())
                .build();
        kafkaTemplate.send("order-rest-notification-topic",orderPlacedNotification);
    }

    public void updateOrderStatus(String orderId, OrderStatus orderStatus) {
        Optional<Order> optionalOrder = orderRespository.findById(Long.valueOf(orderId));
        if(optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setOrderStatus(orderStatus);
        }
    }
}
