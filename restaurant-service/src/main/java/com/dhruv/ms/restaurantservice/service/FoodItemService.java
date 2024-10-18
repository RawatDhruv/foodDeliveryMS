package com.dhruv.ms.restaurantservice.service;

import com.dhruv.ms.restaurantservice.dto.FoodItemDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemService {
    public String addFoodItem(FoodItemDto foodItemDto, String username) {
    }

    public List<FoodItemDto> getAllFoodItems(String restaurantId) {
    }

    public String updateFoodItem(FoodItemDto foodItemDto, String username) {
    }

    public void updateFoodItemQuantity(List<String> foodItemIds, List<Integer> orderQuantities) {
    }

    public String removeFoodItem(FoodItemDto foodItemDto, String username) {
    }
}
