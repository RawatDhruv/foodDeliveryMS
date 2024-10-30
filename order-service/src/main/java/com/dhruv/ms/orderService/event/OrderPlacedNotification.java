package com.dhruv.ms.orderService.event;

import com.dhruv.ms.orderService.model.Address;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
public class OrderPlacedNotification {
    private String orderId;
    private List<Long> foodItemIds;
    private List<Integer> foodItemQuantities;
    private Address orderAddress;
    private Long userId;

    @JsonCreator
    public OrderPlacedNotification(@JsonProperty("orderId") String orderId,
                                   @JsonProperty("foodItemIds") List<Long> foodItemIds,
                                   @JsonProperty("foodItemQuantities") List<Integer> foodItemQuantities,
                                   @JsonProperty("orderAddress") Address orderAddress,
                                   @JsonProperty("userId") Long userId) {
        this.orderId = orderId;
        this.foodItemIds = foodItemIds;
        this.foodItemQuantities = foodItemQuantities;
        this.orderAddress = orderAddress;
        this.userId = userId;
    }
}


