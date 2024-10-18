package com.dhruv.ms.restaurantservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "ownerInfos")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OwnerInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Still present as an auto-generated primary key

    @Column(unique = true, nullable = false)
    private String username;  // Unique username, but not a primary key

    private String fullname;
    private String phoneNumber;
}
