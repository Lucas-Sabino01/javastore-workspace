package com.javastore.catalog.core.ports.in;

public interface UpdateStockUseCase {
    void decreaseStock(Long productId, Integer quantity);
}