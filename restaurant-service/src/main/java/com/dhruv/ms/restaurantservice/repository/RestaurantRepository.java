package com.dhruv.ms.restaurantservice.repository;

import com.dhruv.ms.restaurantservice.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
