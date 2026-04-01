package com.javastore.orders.core.domain;

import java.math.BigDecimal;
import java.util.List;

public class Order {
    private Long id;
    private String userEmail;
    private List<OrderItem> items;
    private BigDecimal totalPrice;
    private String status;

    public Order(Long id, String userEmail, List<OrderItem> items, String status) {
        this.id = id;
        this.userEmail = userEmail;
        this.items = items;
        this.status = status;
        this.totalPrice = calculateTotal(items);
    }

    private BigDecimal calculateTotal(List<OrderItem> items) {
        if (items == null || items.isEmpty()) return BigDecimal.ZERO;

        return items.stream()
                .map(item -> item.getPrice().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }

    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) {
        this.items = items;
        this.totalPrice = calculateTotal(items); // Recalcula se os itens mudarem
    }

    public BigDecimal getTotalPrice() { return totalPrice; }
    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}