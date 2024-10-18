package com.dhruv.ms.restaurantservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "foodItems")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    @ManyToOne(fetch = FetchType.EAGER)
    private Restaurant restaurant;
    @Column(name = "deleted")
    private Boolean deleted = false;
}
