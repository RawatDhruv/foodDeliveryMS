package com.dhruv.ms.restaurantservice.dto;

import com.dhruv.ms.restaurantservice.model.Address;
import com.dhruv.ms.restaurantservice.model.FoodItem;
import com.dhruv.ms.restaurantservice.model.RestaurantContacts;

import java.util.List;

public record RestaurantDto(Long id, String name, String description, Address address, List<RestaurantContacts> contacts, Double rating,
                            List<FoodItem> foodItems) {
}
