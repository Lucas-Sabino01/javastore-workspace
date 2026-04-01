package com.javastore.catalog.adapters.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataProductRepository extends JpaRepository<ProductEntity, Long> {
}