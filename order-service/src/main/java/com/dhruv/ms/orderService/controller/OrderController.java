package com.dhruv.ms.orderService.controller;

import com.dhruv.ms.orderService.dto.OrderRequest;
import com.dhruv.ms.orderService.dto.OrderResponse;
import com.dhruv.ms.orderService.model.Order;
import com.dhruv.ms.orderService.model.OrderStatus;
import com.dhruv.ms.orderService.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse placeOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest);
    }

    @PutMapping("/status")
    @ResponseStatus(HttpStatus.OK)
    public void updateOrderStatus(@RequestParam String orderId,  @RequestBody OrderStatus orderStatus) {
        orderService.updateOrderStatus(orderId, orderStatus);
    }
}
