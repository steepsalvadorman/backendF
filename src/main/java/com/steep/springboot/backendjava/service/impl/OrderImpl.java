package com.steep.springboot.backendjava.service.impl;

import com.steep.springboot.backendjava.dao.entity.OrderEntity;
import com.steep.springboot.backendjava.dao.repository.OrderRepository;
import com.steep.springboot.backendjava.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderImpl implements OrderService {

    private final OrderRepository orderRepository;
    @Override
    public List<OrderEntity> listarTodos() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<OrderEntity> obtenerUno(Long codigo) {
        return orderRepository.findById(codigo);
    }



    @Override
    @Transactional
    public OrderEntity insertar(OrderEntity o) {
        return orderRepository.save(o);
    }

    @Override
    public OrderEntity modificar(OrderEntity o) {
        return orderRepository.save(o);
    }

    @Override
    public void eliminar(Long codigo) {
        orderRepository.deleteById(codigo);
    }
}
