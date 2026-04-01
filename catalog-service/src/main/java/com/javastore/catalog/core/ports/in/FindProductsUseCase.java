package com.javastore.catalog.core.ports.in;

import com.javastore.catalog.core.domain.Product;
import java.util.List;
import java.util.Optional;

public interface FindProductsUseCase {
    List<Product> findAll();
    Optional<Product> findById(Long id);
}