package com.javastore.catalog.adapters.in.messaging;

import com.javastore.catalog.adapters.in.messaging.dto.OrderEventDTO;
import com.javastore.catalog.core.ports.in.UpdateStockUseCase;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedListener {

    private final UpdateStockUseCase updateStockUseCase;

    public OrderCreatedListener(UpdateStockUseCase updateStockUseCase) {
        this.updateStockUseCase = updateStockUseCase;
    }

    @RabbitListener(queues = "catalog.order.created.queue")
    public void receiveOrderCreatedEvent(OrderEventDTO event) {
        System.out.println("📦 Processando baixa de estoque para o Pedido #" + event.id());

        // Para cada item do pedido, chama o Core para diminuir o estoque
        event.items().forEach(item ->
                updateStockUseCase.decreaseStock(item.productId(), item.quantity())
        );
    }
}