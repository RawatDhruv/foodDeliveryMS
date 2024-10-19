package com.dhruv.ms.restaurantservice.controller;

import com.dhruv.ms.restaurantservice.dto.FoodItemDto;
import com.dhruv.ms.restaurantservice.service.FoodItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/fooditem")
public class FoodItemController {

    private final FoodItemService foodItemService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addFoodItem(@RequestBody FoodItemDto foodItemDto,
                              @RequestHeader("loggedInUser") String username) {
        return foodItemService.addFoodItem(foodItemDto, username);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public String removeFoodItem(@RequestBody FoodItemDto foodItemDto,
                              @RequestHeader("loggedInUser") String username) {
        return foodItemService.removeFoodItem(foodItemDto, username);
    }

    @GetMapping("/{restaurantId}")
    @ResponseStatus(HttpStatus.OK)
    public List<FoodItemDto> getAllFoodItems(@PathVariable("restaurantId") Long restaurantId) {
        return foodItemService.getAllFoodItems(restaurantId);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public String updateFoodItem(@RequestBody FoodItemDto foodItemDto,
                                 @RequestHeader("loggedInUser") String username) {
        return foodItemService.updateFoodItem(foodItemDto, username);
    }


}
