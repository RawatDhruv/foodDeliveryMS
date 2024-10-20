package com.dhruv.ms.restaurantservice.controller;

import com.dhruv.ms.restaurantservice.dto.FoodItemDto;
import com.dhruv.ms.restaurantservice.dto.FoodItemResponse;
import com.dhruv.ms.restaurantservice.service.FoodItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/fooditem")
public class FoodItemController {

    private final FoodItemService foodItemService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addFoodItem(@RequestBody FoodItemDto foodItemDto,
                              @RequestHeader("loggedInUser") String username) {
        return foodItemService.addFoodItem(foodItemDto, username);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> removeFoodItem(@RequestBody FoodItemDto foodItemDto,
                              @RequestHeader("loggedInUser") String username) {
        return foodItemService.removeFoodItem(foodItemDto, username);
    }

    @GetMapping("/{restaurantId}")
    @ResponseStatus(HttpStatus.OK)
    public List<FoodItemResponse> getAllFoodItems(@PathVariable("restaurantId") Long restaurantId) {
        return foodItemService.getAllFoodItems(restaurantId);
    }

    @PutMapping
    public ResponseEntity<String> updateFoodItem(@RequestBody FoodItemDto foodItemDto,
                                                 @RequestHeader("loggedInUser") String username) {
        return foodItemService.updateFoodItem(foodItemDto, username);
    }
}
