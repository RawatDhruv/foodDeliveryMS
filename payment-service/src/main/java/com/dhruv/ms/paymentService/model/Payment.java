package com.dhruv.ms.paymentService.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private Long timestamp;
    private Double amount;
    @ManyToOne(cascade = CascadeType.ALL)
    private CreditCard creditCard;
    private String orderId;
    private PaymentStatus paymentStatus;

}
