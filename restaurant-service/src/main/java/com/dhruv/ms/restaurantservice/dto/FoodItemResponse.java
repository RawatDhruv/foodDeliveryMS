package com.dhruv.ms.restaurantservice.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record FoodItemResponse(Long Id,String name, String description, BigDecimal price, String imageUrl, Long restaurantId) {
}
