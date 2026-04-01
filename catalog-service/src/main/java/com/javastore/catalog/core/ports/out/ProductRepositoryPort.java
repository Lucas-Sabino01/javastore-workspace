package com.javastore.catalog.core.ports.out;

import com.javastore.catalog.core.domain.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {
    Product save(Product product);
    List<Product> findAll();
    Optional<Product> findById(Long id);
}