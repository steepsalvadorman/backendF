package com.steep.springboot.backendjava.dao.repository;

import com.steep.springboot.backendjava.dao.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Long> {
}
