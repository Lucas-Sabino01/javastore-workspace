package com.javastore.catalog.config;

import com.javastore.catalog.adapters.out.persistence.ProductRepositoryAdapter;
import com.javastore.catalog.core.services.ProductCatalogService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {

    @Bean
    public ProductCatalogService productCatalogService(ProductRepositoryAdapter productRepositoryAdapter) {
        return new ProductCatalogService(productRepositoryAdapter);
    }
}