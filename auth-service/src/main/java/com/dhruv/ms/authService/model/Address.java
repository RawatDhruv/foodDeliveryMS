package com.dhruv.ms.authService.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Addresses")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String city;
    private String state;
    private Integer zipcode;
}

