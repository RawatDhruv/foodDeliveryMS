package com.dhruv.ms.orderService.dto;

import com.dhruv.ms.orderService.model.Address;
import com.dhruv.ms.orderService.model.OrderStatus;
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
public class OrderResponse {
    private String orderNumber;
//    private String restaurantName;
//    private String userName;
    private Long restaurantId;
    private List<OrderItemDto> orderItems;
    private BigDecimal totalAmount;
    private Long orderTime;
    private OrderStatus orderStatus;
    private Long expectedDeliveryTime;
    private Address address;
    private String paymentId;
}
