package com.javastore.orders.config;

import com.javastore.orders.adapters.out.messaging.RabbitMQOrderEventPublisherAdapter;
import com.javastore.orders.adapters.out.persistence.OrderRepositoryAdapter;
import com.javastore.orders.core.services.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {

    @Bean
    public OrderService orderService(
            OrderRepositoryAdapter orderRepositoryAdapter,
            RabbitMQOrderEventPublisherAdapter orderEventPublisherAdapter) {

        return new OrderService(orderRepositoryAdapter, orderEventPublisherAdapter);
    }
}