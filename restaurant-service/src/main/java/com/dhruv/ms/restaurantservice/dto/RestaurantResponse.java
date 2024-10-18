package com.dhruv.ms.restaurantservice.dto;


import lombok.Builder;

@Builder
public record RestaurantResponse(RestaurantDto restaurantDto,Integer responseCode, String msg) {
}
