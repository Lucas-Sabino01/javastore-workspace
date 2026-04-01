package com.javastore.catalog.adapters.in.web;

import com.javastore.catalog.core.domain.Product;
import com.javastore.catalog.core.ports.in.CreateProductUseCase;
import com.javastore.catalog.core.ports.in.FindProductsUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final FindProductsUseCase findProductsUseCase;

    public ProductController(CreateProductUseCase createProductUseCase, FindProductsUseCase findProductsUseCase) {
        this.createProductUseCase = createProductUseCase;
        this.findProductsUseCase = findProductsUseCase;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = createProductUseCase.create(product);
        return ResponseEntity.ok(createdProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = findProductsUseCase.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return findProductsUseCase.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}