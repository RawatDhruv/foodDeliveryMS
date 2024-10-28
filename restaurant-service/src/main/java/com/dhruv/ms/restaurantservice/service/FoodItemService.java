package com.dhruv.ms.restaurantservice.service;

import com.dhruv.ms.restaurantservice.dto.FoodItemDto;
import com.dhruv.ms.restaurantservice.dto.FoodItemResponse;
import com.dhruv.ms.restaurantservice.model.FoodItem;
import com.dhruv.ms.restaurantservice.model.Restaurant;
import com.dhruv.ms.restaurantservice.repository.FoodItemRepository;
import com.dhruv.ms.restaurantservice.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FoodItemService {

    private final FoodItemRepository foodItemRepository;

    private final RestaurantRepository restaurantRepository;

    private WebClient.Builder webClientBuilder;

    private final String ROLE = "RESTAURANT_OWNER";


    public ResponseEntity<String> addFoodItem(FoodItemDto foodItemDto, String username) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(foodItemDto.restaurantId());
        if (optionalRestaurant.isEmpty()) {
            return new ResponseEntity<>("Restaurant not found", HttpStatus.NOT_FOUND);
        }

        Restaurant restaurant = optionalRestaurant.get();
        if (!restaurant.getOwner().getUsername().equals(username)) {
            return new ResponseEntity<>("Access Denied...You are not the OWNER of this restaurant", HttpStatus.FORBIDDEN);
        }

        Optional<FoodItem> optionalFoodItem = foodItemRepository.findByNameAndRestaurant(foodItemDto.name(), restaurant);
        if (optionalFoodItem.isEmpty()) {
            FoodItem foodItem = FoodItem.builder()
                    .name(foodItemDto.name())
                    .description(foodItemDto.description())
                    .price(foodItemDto.price())
                    .imageUrl(foodItemDto.imageUrl())
                    .restaurant(restaurant)
                    .build();

            foodItemRepository.save(foodItem);
            return new ResponseEntity<>("Food item added with id: " + foodItem.getId(), HttpStatus.CREATED);
        }

        FoodItem foodItem = optionalFoodItem.get();
        update(foodItem, foodItemDto);
        return new ResponseEntity<>("Food item updated with id: " + foodItem.getId(), HttpStatus.OK);
    }

    public List<FoodItemResponse> getAllFoodItems(Long restaurantId) {
        List<FoodItem> allFoodItems = foodItemRepository.findAllByRestaurantIdAndDeleted(restaurantId,false);
        return allFoodItems.stream().map(foodItem ->
                FoodItemResponse.builder()
                        .Id(foodItem.getId())
                        .name(foodItem.getName())
                        .description(foodItem.getDescription())
                        .imageUrl(foodItem.getImageUrl())
                        .price(foodItem.getPrice())
                        .restaurantId(foodItem.getRestaurant().getId())
                        .build()

        ).toList();
    }

    public ResponseEntity<String> updateFoodItem(FoodItemDto foodItemDto, String username) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(foodItemDto.restaurantId());
        if (optionalRestaurant.isEmpty()) {
            return new ResponseEntity<>("Restaurant not found", HttpStatus.NOT_FOUND);
        }

        Restaurant restaurant = optionalRestaurant.get();
        if (!restaurant.getOwner().getUsername().equals(username)) {
            return new ResponseEntity<>("Access Denied...You are not the OWNER of this restaurant", HttpStatus.FORBIDDEN);
        }

        Optional<FoodItem> optionalFoodItem = foodItemRepository.findByNameAndRestaurantId(foodItemDto.name(), foodItemDto.restaurantId());
        if (optionalFoodItem.isPresent()) {
            update(optionalFoodItem.get(), foodItemDto);
            return new ResponseEntity<>("Updated Successfully...", HttpStatus.OK);
        }

        return new ResponseEntity<>("Food item not Present", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> removeFoodItem(FoodItemDto foodItemDto, String username) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(foodItemDto.restaurantId());
        if (optionalRestaurant.isEmpty()) {
            return new ResponseEntity<>("Restaurant not found", HttpStatus.NOT_FOUND);
        }

        Restaurant restaurant = optionalRestaurant.get();
        if (!restaurant.getOwner().getUsername().equals(username)) {
            return new ResponseEntity<>("Access Denied...You are not the OWNER of this restaurant", HttpStatus.FORBIDDEN);
        }

        Optional<FoodItem> optionalFoodItem = foodItemRepository.findByNameAndRestaurantId(foodItemDto.name(), foodItemDto.restaurantId());
        if (optionalFoodItem.isPresent()) {
            FoodItem foodItem = optionalFoodItem.get();
            foodItem.setDeleted(true);
            foodItemRepository.save(foodItem);
            return new ResponseEntity<>("Updated Successfully...", HttpStatus.OK);
        }

        return new ResponseEntity<>("Food item not Present", HttpStatus.NOT_FOUND);
    }

    private void update(FoodItem item, FoodItemDto foodItemDto) {
        item.setName(foodItemDto.name());
        item.setDescription(foodItemDto.description());
        item.setImageUrl(foodItemDto.imageUrl());
        item.setPrice(foodItemDto.price());
        item.setDeleted(false);
        foodItemRepository.save(item);
    }
}
