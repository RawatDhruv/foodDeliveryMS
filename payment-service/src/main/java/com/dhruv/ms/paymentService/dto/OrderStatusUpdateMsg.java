package com.dhruv.ms.paymentService.dto;

import com.dhruv.ms.paymentService.model.PaymentStatus;

public class OrderStatusUpdateMsg {
    private String paymentId;
    private PaymentStatus paymentStatus;
}
