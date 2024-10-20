package com.dhruv.ms.restaurantservice.dto;

public record AddressRequest(String street, String city, String state, Integer zipcode) {
}
