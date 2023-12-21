package com.steep.springboot.backendjava.service;

import com.steep.springboot.backendjava.dao.entity.OrderEntity;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<OrderEntity> listarTodos();

    Optional<OrderEntity> obtenerUno(Long codigo);

    OrderEntity insertar(OrderEntity o);

    OrderEntity modificar(OrderEntity o);

    void eliminar(Long codigo);
}
