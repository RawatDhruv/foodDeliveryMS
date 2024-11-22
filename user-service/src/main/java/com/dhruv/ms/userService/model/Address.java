package com.dhruv.ms.userService.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "User_Address")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String city;
    private String state;
    private Integer zipcode;
    private Boolean primaryAddress;
}
