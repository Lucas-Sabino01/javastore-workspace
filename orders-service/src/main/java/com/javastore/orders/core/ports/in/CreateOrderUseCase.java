package com.javastore.orders.core.ports.in;

import com.javastore.orders.core.domain.Order;

public interface CreateOrderUseCase {
    Order create(Order order);
}