package com.javastore.orders.adapters.out.persistence;

import com.javastore.orders.core.domain.Order;
import com.javastore.orders.core.domain.OrderItem;
import com.javastore.orders.core.ports.out.OrderRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderRepositoryAdapter implements OrderRepositoryPort {

    private final SpringDataOrderRepository repository;

    public OrderRepositoryAdapter(SpringDataOrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Order save(Order order) {
        List<OrderItemEntity> itemEntities = order.getItems().stream()
                .map(item -> new OrderItemEntity(item.getProductId(), item.getQuantity(), item.getPrice()))
                .collect(Collectors.toList());

        OrderEntity entity = new OrderEntity(order.getId(), order.getUserEmail(), order.getTotalPrice(), order.getStatus(), itemEntities);

        OrderEntity savedEntity = repository.save(entity);

        List<OrderItem> savedItems = savedEntity.getItems().stream()
                .map(itemEnt -> new OrderItem(itemEnt.getProductId(), itemEnt.getQuantity(), itemEnt.getPrice()))
                .collect(Collectors.toList());

        return new Order(savedEntity.getId(), savedEntity.getUserEmail(), savedItems, savedEntity.getStatus());
    }
}