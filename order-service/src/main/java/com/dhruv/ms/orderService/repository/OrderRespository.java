package com.dhruv.ms.orderService.repository;

import com.dhruv.ms.orderService.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRespository extends CrudRepository<Order, String> {
}
