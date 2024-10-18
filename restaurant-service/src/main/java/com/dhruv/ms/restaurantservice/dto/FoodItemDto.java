package com.dhruv.ms.restaurantservice.dto;

public record FoodItemDto(Long id, String name, String description, Double price, String imageUrl) {
}
