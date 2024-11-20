package com.dhruv.ms.userService.model;


import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String passwordHash;
    private Long phoneNumber;
    private Address address;
    private UserRole userRole;


}
