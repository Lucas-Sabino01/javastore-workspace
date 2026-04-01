package com.javastore.orders.core.ports.out;

import com.javastore.orders.core.domain.Order;

public interface OrderEventPublisherPort {
    void publishOrderCreatedEvent(Order order);
}