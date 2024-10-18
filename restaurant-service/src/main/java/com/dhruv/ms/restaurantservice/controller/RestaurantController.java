package com.dhruv.ms.restaurantservice.controller;

import com.dhruv.ms.restaurantservice.dto.RestaurantDto;
import com.dhruv.ms.restaurantservice.dto.RestaurantRequest;
import com.dhruv.ms.restaurantservice.dto.RestaurantResponse;
import com.dhruv.ms.restaurantservice.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addRestaurant(@RequestBody RestaurantRequest restaurantRequest,
                                @RequestHeader("loggedInUser") String username) {
        return restaurantService.addRestaurant(restaurantRequest,username);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RestaurantDto> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RestaurantResponse getRestaurant(@PathVariable("id") String id) {
        return restaurantService.getRestaurant(id);
    }


}
