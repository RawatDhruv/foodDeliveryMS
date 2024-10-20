package com.dhruv.ms.restaurantservice.service;

import com.dhruv.ms.restaurantservice.dto.FoodItemResponse;
import com.dhruv.ms.restaurantservice.dto.RestaurantDto;
import com.dhruv.ms.restaurantservice.dto.RestaurantRequest;
import com.dhruv.ms.restaurantservice.dto.RestaurantResponse;
import com.dhruv.ms.restaurantservice.model.*;
import com.dhruv.ms.restaurantservice.repository.FoodItemRepository;
import com.dhruv.ms.restaurantservice.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final String ROLE = "RESTAURANT_OWNER";

    private final RestaurantRepository restaurantRepository;

    private FoodItemRepository foodItemRepository;

    private WebClient.Builder webClientBuilder;

    public String addRestaurant(RestaurantRequest request, String username) {
        String ownerRole = webClientBuilder.build().get()
                .uri("http://auth-service/api/v1/user/role")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        if(ownerRole == null) {
            return "Error: Owner does not exist. Can't add this restaurant.";
        }
        else if(!ownerRole.equals(ROLE)) {
            return "Error: Given owner is not a restaurant owner. Can't add this restaurant";
        }

        List<RestaurantContacts> restaurantContacts = request.contacts().stream()
                .map(contactRequest -> RestaurantContacts.builder()
                        .phoneNumber(contactRequest.phoneNumber()) // Assuming you have these getters in your contact request
                        .emailAddress(contactRequest.emailAddress())
                        .build())
                .collect(Collectors.toList());

        Address restaurantAddress = Address.builder()
                .address(request.address().street())
                .city(request.address().city())
                .state(request.address().state())
                .zipcode(request.address().zipcode()).build();


        Restaurant restaurant = Restaurant.builder()
                .name(request.name())
                .description(request.description())
                .contactInfo(restaurantContacts)
                .address(restaurantAddress)
                .rating(request.rating())
                .owner(OwnerInfo.builder()
                        .username(username)
                        .build())
                .build();
        restaurantRepository.save(restaurant);
        return "Restaurant with id: " + restaurant.getId() + "added successfully";
    }

    public RestaurantResponse getRestaurant(String id) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(Long.parseLong(id));
        if (optionalRestaurant.isPresent()) {
            Restaurant restaurant = optionalRestaurant.get();
            RestaurantDto restaurantDto = getRestaurantDto(restaurant);
            return RestaurantResponse.builder()
                    .restaurantDto(restaurantDto)
                    .responseCode(200)
                    .msg("Success")
                    .build();
        }
        return RestaurantResponse.builder()
                .responseCode(404)
                .msg("Restaurant not found with id: " + id)
                .build();
    }

    public List<RestaurantDto> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream().map(this::getRestaurantDto).toList();
    }

    private RestaurantDto getRestaurantDto(Restaurant restaurant) {
        List<FoodItem> foodItems = foodItemRepository.findByAllRestaurantId(restaurant.getId());
        return new RestaurantDto(restaurant.getId(),restaurant.getName(),restaurant.getDescription(),
                restaurant.getAddress(),restaurant.getContactInfo(),restaurant.getRating(),mapFoodItemsToResponse(foodItems));
    }

    public List<FoodItemResponse> mapFoodItemsToResponse(List<FoodItem> foodItems) {
        return foodItems.stream()
                .map(foodItem -> new FoodItemResponse(
                        foodItem.getId(),
                        foodItem.getName(),
                        foodItem.getDescription(),
                        foodItem.getPrice(),
                        foodItem.getImageUrl(),
                        foodItem.getRestaurant().getId()
                ))
                .collect(Collectors.toList());
    }

}
