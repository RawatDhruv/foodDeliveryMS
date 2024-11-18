package com.dhruv.ms.paymentService.services;


import com.dhruv.ms.paymentService.model.Payment;
import com.dhruv.ms.paymentService.model.PaymentStatus;
import com.dhruv.ms.paymentService.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private WebClient.Builder clientBuilder;
    private KafkaTemplate<String,List<String>> kafkaTemplate;


    public String processPayment(Payment payment) {
        //TODO: check payment method validatity
        // and Something like Razorpay but without buisness registartion
        // and KYC if it exists
        payment.setTimestamp(System.currentTimeMillis());
        payment.setPaymentStatus(PaymentStatus.APPROVED);
        payment = paymentRepository.save(payment);

        // send payment info to order-service
        List<String> paymentInfo = new ArrayList<>();
        paymentInfo.add(String.valueOf(payment.getId()));
        paymentInfo.add(payment.getOrderId());
        paymentInfo.add("SUCCESS");
        kafkaTemplate.send("payment-notification-topic", paymentInfo);

        return "payment id "+payment.getId()+" for order "+payment.getOrderId()+" was " + payment.getPaymentStatus();
    }
}
