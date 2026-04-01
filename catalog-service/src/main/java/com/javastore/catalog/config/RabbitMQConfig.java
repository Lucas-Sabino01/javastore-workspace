package com.javastore.catalog.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue catalogOrderQueue() {
        return new Queue("catalog.order.created.queue", true); // true = fila durável (não some se o Rabbit reiniciar)
    }

    @Bean
    public TopicExchange ordersExchange() {
        return new TopicExchange("orders.exchange");
    }

    @Bean
    public Binding binding(Queue catalogOrderQueue, TopicExchange ordersExchange) {
        return BindingBuilder.bind(catalogOrderQueue).to(ordersExchange).with("order.created");
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}