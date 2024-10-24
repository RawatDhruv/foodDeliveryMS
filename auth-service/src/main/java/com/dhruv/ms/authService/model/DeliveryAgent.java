package com.dhruv.ms.authService.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name="DeliveryAgents")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DeliveryAgent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne
    private Address address;
    private Long phoneNumber;
}
