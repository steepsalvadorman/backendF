package com.steep.springboot.backendjava.service;

import com.steep.springboot.backendjava.dao.entity.OrderStatusEntity;
import com.steep.springboot.backendjava.dto.OrderStatusDTO;

import java.util.List;

public interface OrderStatusService {
    List<OrderStatusEntity> listarTodos();

    OrderStatusEntity obtenerUno(Long codigo);

    void insertar(OrderStatusEntity os);

    void modificar(Long orderId, OrderStatusDTO newStatus);

    void eliminar(Long codigo);
}
