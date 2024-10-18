package com.dhruv.ms.restaurantservice.repository;

import com.dhruv.ms.restaurantservice.model.FoodItem;
import com.dhruv.ms.restaurantservice.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
    @Query("SELECT f FROM FoodItem f WHERE f.deleted = false AND f.restaurant.id = :restaurantId")
    List<FoodItem> findByRestaurantId(@Param("restaurantId") Long restaurantId);
    @Query("SELECT f FROM FoodItem f WHERE f.deleted = false")
    List<Restaurant> findAllActive();
}
