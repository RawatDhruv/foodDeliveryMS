package com.dhruv.ms.restaurantservice.dto;

import lombok.Builder;

@Builder
public record RestaurantContactsRequest(Long phoneNumber , String emailAddress) {
}
