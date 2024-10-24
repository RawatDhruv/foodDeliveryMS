package com.dhruv.ms.authService.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="UserCredentials")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserCredential {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String fullName;
    private String username; //email
    private String password;
    private Long phoneNumber;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    private UserRole userRole;
}
