package com.dhruv.ms.restaurantservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "contacts")
public class RestaurantContacts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    Long phoneNumber;
    @Column(unique = true, nullable = false)
    String emailAddress;
}
