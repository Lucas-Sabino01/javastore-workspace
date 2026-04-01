package com.javastore.orders.adapters.out.messaging;

import com.javastore.orders.core.domain.Order;
import com.javastore.orders.core.ports.out.OrderEventPublisherPort;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQOrderEventPublisherAdapter implements OrderEventPublisherPort {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQOrderEventPublisherAdapter(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publishOrderCreatedEvent(Order order) {
        rabbitTemplate.convertAndSend("orders.exchange", "order.created", order);
        System.out.println("📦 Evento disparado no RabbitMQ: Pedido #" + order.getId() + " criado!");
    }
}