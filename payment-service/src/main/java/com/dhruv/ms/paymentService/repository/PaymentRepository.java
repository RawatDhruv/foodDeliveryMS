package com.dhruv.ms.paymentService.repository;

import com.dhruv.ms.paymentService.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
