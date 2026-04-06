package com.javastore.orders.adapters.in.web;

import com.javastore.orders.core.domain.Order;
import com.javastore.orders.core.ports.in.CreateOrderUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;

    public OrderController(CreateOrderUseCase createOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(
            @RequestBody Order order,
            @RequestHeader(value = "X-User-Email", required = false) String userEmailFromGateway) {

        order.setUserEmail(userEmailFromGateway);

        Order createdOrder = createOrderUseCase.create(order);
        return ResponseEntity.ok(createdOrder);
    }
}