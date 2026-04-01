package com.javastore.catalog.core.services;

import com.javastore.catalog.core.domain.Product;
import com.javastore.catalog.core.ports.in.CreateProductUseCase;
import com.javastore.catalog.core.ports.in.FindProductsUseCase;
import com.javastore.catalog.core.ports.in.UpdateStockUseCase;
import com.javastore.catalog.core.ports.out.ProductRepositoryPort;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class ProductCatalogService implements CreateProductUseCase, FindProductsUseCase, UpdateStockUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    public ProductCatalogService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public Product create(Product product) {
        if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O preço do produto deve ser maior que zero.");
        }

        if (product.getStockQuantity() == null || product.getStockQuantity() < 0) {
            throw new IllegalArgumentException("A quantidade em estoque não pode ser negativa.");
        }

        return productRepositoryPort.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepositoryPort.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepositoryPort.findById(id);
    }
    @Override
    public void decreaseStock(Long productId, Integer quantity) {
        Product product = productRepositoryPort.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado para baixa de estoque: " + productId));

        if (product.getStockQuantity() < quantity) {
            System.err.println("Estoque insuficiente para o produto: " + product.getName());
            return;
        }

        product.setStockQuantity(product.getStockQuantity() - quantity);
        productRepositoryPort.save(product);

        System.out.println("Estoque atualizado: " + product.getName() + " agora tem " + product.getStockQuantity() + " unidades.");
    }
}