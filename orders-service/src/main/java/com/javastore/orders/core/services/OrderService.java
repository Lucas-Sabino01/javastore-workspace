package com.javastore.orders.core.services;

import com.javastore.orders.core.domain.Order;
import com.javastore.orders.core.ports.in.CreateOrderUseCase;
import com.javastore.orders.core.ports.out.OrderEventPublisherPort;
import com.javastore.orders.core.ports.out.OrderRepositoryPort;

public class OrderService implements CreateOrderUseCase {

    private final OrderRepositoryPort orderRepositoryPort;
    private final OrderEventPublisherPort orderEventPublisherPort;

    public OrderService(OrderRepositoryPort orderRepositoryPort, OrderEventPublisherPort orderEventPublisherPort) {
        this.orderRepositoryPort = orderRepositoryPort;
        this.orderEventPublisherPort = orderEventPublisherPort;
    }

    @Override
    public Order create(Order order) {
        if (order.getItems() == null || order.getItems().isEmpty()) {
            throw new IllegalArgumentException("Um pedido não pode ser vazio.");
        }

        order.setStatus("CRIADO");

        Order savedOrder = orderRepositoryPort.save(order);

        orderEventPublisherPort.publishOrderCreatedEvent(savedOrder);

        return savedOrder;
    }
}