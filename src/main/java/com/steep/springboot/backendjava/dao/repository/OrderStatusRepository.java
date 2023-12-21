package com.steep.springboot.backendjava.dao.repository;

import com.steep.springboot.backendjava.dao.entity.OrderStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatusEntity, Long> {
}
