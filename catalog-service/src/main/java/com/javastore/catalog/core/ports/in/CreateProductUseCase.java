package com.javastore.catalog.core.ports.in;

import com.javastore.catalog.core.domain.Product;

public interface CreateProductUseCase {
    Product create(Product product);
}