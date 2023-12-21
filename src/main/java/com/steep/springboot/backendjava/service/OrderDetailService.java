package com.steep.springboot.backendjava.service;

import com.steep.springboot.backendjava.dao.entity.OrderDetailEntity;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetailEntity> listarTodos();

    OrderDetailEntity obtenerUno(Long codigo);

    OrderDetailEntity insertar(OrderDetailEntity od);

    void modificar(OrderDetailEntity od);

    void eliminar(Long codigo);

}
