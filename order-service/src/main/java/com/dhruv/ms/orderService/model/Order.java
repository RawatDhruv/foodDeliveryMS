package com.dhruv.ms.orderService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private Long orderNumber;
    private String restaurantId;
    @OneToMany
    private List<OrderItem> orderItems;
    private BigDecimal totalAmount;
    private Long orderTime;
    private Address address;
    private Long deliveryTime;
    private OrderStatus orderStatus;
    private String paymentId;
    private String userId;
}