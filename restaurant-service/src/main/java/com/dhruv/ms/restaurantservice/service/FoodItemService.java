package com.dhruv.ms.restaurantservice.service;

import com.dhruv.ms.restaurantservice.dto.FoodItemDto;
import com.dhruv.ms.restaurantservice.model.FoodItem;
import com.dhruv.ms.restaurantservice.model.Restaurant;
import com.dhruv.ms.restaurantservice.repository.FoodItemRepository;
import com.dhruv.ms.restaurantservice.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
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


    public String addFoodItem(FoodItemDto foodItemDto, String username) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(foodItemDto.restaurantId());
        if (optionalRestaurant.isEmpty()) {
            return "Restaurant not found";
        }
        Restaurant restaurant = optionalRestaurant.get();
        if(!restaurant.getOwner().getUsername().equals(username)) {
            return "Access Denied...You are not the OWNER of this restaurant";
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
            return "Food item added with id: " + foodItem.getId();
        }
        FoodItem foodItem = optionalFoodItem.get();
        update(foodItem, foodItemDto);
        return "Food item added with id: " + foodItem.getId();
    }

    public List<FoodItemDto> getAllFoodItems(Long restaurantId) {
        List<FoodItem> allFoodItems = foodItemRepository.findByRestaurantId(restaurantId);
        return allFoodItems.stream().map(foodItem ->
                FoodItemDto.builder()
                        .id(foodItem.getId())
                        .name(foodItem.getName())
                        .description(foodItem.getDescription())
                        .imageUrl(foodItem.getImageUrl())
                        .price(foodItem.getPrice())
                        .restaurantId(foodItem.getRestaurant().getId())
                        .build()

        ).toList();
    }

    public String updateFoodItem(FoodItemDto foodItemDto, String username) {
        Restaurant restaurant = restaurantRepository.findById(foodItemDto.restaurantId()).get();
        if(!restaurant.getOwner().getUsername().equals(username)) {
            return "Access Denied...You are not the OWNER of this restaurant";
        }
        Optional<FoodItem> optionalFoodItem = foodItemRepository.findByIdAndDeletedFalse(foodItemDto.id());
        if (optionalFoodItem.isPresent()) {
            update(optionalFoodItem.get(),foodItemDto);
            return "Updated Successfully...";
        }
        return "Food item not Present";
    }

    public String removeFoodItem(FoodItemDto foodItemDto, String username) {
        Restaurant restaurant = restaurantRepository.findById(foodItemDto.restaurantId()).get();
        if(!restaurant.getOwner().getUsername().equals(username)) {
            return "Access Denied...You are not the OWNER of this restaurant";
        }
        Optional<FoodItem> optionalFoodItem = foodItemRepository.findById(foodItemDto.id());
        if (optionalFoodItem.isPresent()) {
            FoodItem foodItem = optionalFoodItem.get();
            foodItem.setDeleted(true);
            foodItemRepository.save(foodItem);
            return "Updated Successfully...";
        }
        return "Food item not Present";
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
