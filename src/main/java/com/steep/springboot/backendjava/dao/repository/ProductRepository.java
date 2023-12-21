package com.steep.springboot.backendjava.dao.repository;

import com.steep.springboot.backendjava.dao.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
