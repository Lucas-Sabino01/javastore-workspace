package com.javastore.catalog.adapters.out.persistence;

import com.javastore.catalog.core.domain.Product;
import com.javastore.catalog.core.ports.out.ProductRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final SpringDataProductRepository repository;

    public ProductRepositoryAdapter(SpringDataProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product save(Product product) {
        ProductEntity entity = new ProductEntity(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockQuantity()
        );

        ProductEntity savedEntity = repository.save(entity);

        return new Product(
                savedEntity.getId(),
                savedEntity.getName(),
                savedEntity.getDescription(),
                savedEntity.getPrice(),
                savedEntity.getStockQuantity()
        );
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll().stream()
                .map(entity -> new Product(
                        entity.getId(),
                        entity.getName(),
                        entity.getDescription(),
                        entity.getPrice(),
                        entity.getStockQuantity()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Product> findById(Long id) {
        return repository.findById(id)
                .map(entity -> new Product(
                        entity.getId(),
                        entity.getName(),
                        entity.getDescription(),
                        entity.getPrice(),
                        entity.getStockQuantity()
                ));
    }
}