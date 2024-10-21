package com.dhruv.ms.restaurantservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "addresses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String street;
    String city;
    String state;
    Integer zipcode;

}
