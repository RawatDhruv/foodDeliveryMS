package com.dhruv.ms.restaurantservice.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record FoodItemDto(Long id, String name, String description, BigDecimal price, String imageUrl, Long restaurantId) {
}
