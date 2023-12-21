package com.steep.springboot.backendjava.dao.repository;

import com.steep.springboot.backendjava.dao.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
