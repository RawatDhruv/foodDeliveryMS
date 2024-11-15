package com.dhruv.ms.paymentService.controller;

import com.dhruv.ms.paymentService.model.Payment;
import com.dhruv.ms.paymentService.services.PaymentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/payment")
@RequiredArgsConstructor
@AllArgsConstructor
public class PaymentController {

    private PaymentService paymentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String pay(@RequestBody Payment payment) {
        return paymentService.processPayment(payment);
    }
}
