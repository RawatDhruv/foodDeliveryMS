package com.dhruv.ms.restaurantservice.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "restaurants")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Address address;
    private Double rating;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private OwnerInfo owner;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    private List<RestaurantContacts> contactInfo;
}
