package com.dhruv.ms.orderService.dto;

import com.dhruv.ms.orderService.model.Address;
import com.dhruv.ms.orderService.model.OrderItem;
import com.dhruv.ms.orderService.model.OrderStatus;
import jakarta.persistence.OneToMany;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(Long restaurantId,List<OrderItemDto>orderItems,Address address, Long userId) {
}
