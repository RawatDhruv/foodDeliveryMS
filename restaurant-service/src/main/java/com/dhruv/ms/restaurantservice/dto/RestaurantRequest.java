package com.dhruv.ms.restaurantservice.dto;

import com.dhruv.ms.restaurantservice.model.Address;
import com.dhruv.ms.restaurantservice.model.RestaurantContacts;

import java.util.List;

public record RestaurantRequest(String name, String description, Address address, List<RestaurantContacts> contacts, Double rating) {
}
