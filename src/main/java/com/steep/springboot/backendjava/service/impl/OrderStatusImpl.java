package com.steep.springboot.backendjava.service.impl;

import com.steep.springboot.backendjava.controller.error_handler.exception.InvalidOrderStatusException;
import com.steep.springboot.backendjava.controller.error_handler.exception.OrderAlreadyCompletedException;
import com.steep.springboot.backendjava.controller.error_handler.exception.OrderNotFoundException;
import com.steep.springboot.backendjava.controller.error_handler.exception.OrderStatusNotFoundException;
import com.steep.springboot.backendjava.dao.entity.OrderStatusEntity;
import com.steep.springboot.backendjava.dao.entity.OrderEntity;
import com.steep.springboot.backendjava.dao.repository.OrderStatusRepository;
import com.steep.springboot.backendjava.dao.repository.OrderRepository;
import com.steep.springboot.backendjava.dto.OrderStatusDTO;
import com.steep.springboot.backendjava.service.OrderStatusService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderStatusImpl implements OrderStatusService {

    private final OrderStatusRepository orderStatusRepository;
    private final OrderRepository orderRepository;
    @Override
    public List<OrderStatusEntity> listarTodos() {
        return orderStatusRepository.findAll();
    }

    @Override
    public OrderStatusEntity obtenerUno(Long codigo) {
        return orderStatusRepository.findById(codigo).get();
    }

    @Override
    public void insertar(OrderStatusEntity os) {
        orderStatusRepository.save(os);
    }

    @Override
    public void modificar(Long orderId, OrderStatusDTO newOrderStatusDTO) {
        OrderEntity entity = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        OrderStatusEntity status = orderStatusRepository.findById(entity.getId())
                .orElseThrow(() -> new OrderStatusNotFoundException(entity.getId()));

        if ("Completed".equals(status.getStatus())) {
            throw new OrderAlreadyCompletedException(orderId);
        }
        if (!isValidStatus(newOrderStatusDTO.getStatus())) {
            throw new InvalidOrderStatusException(newOrderStatusDTO.getStatus());
        }
        status.setStatus(newOrderStatusDTO.getStatus());
        status.setOrder(entity);
        orderStatusRepository.save(status);
    }


    @Override
    public void eliminar(Long codigo) {
        orderStatusRepository.deleteById(codigo);
    }

    private boolean isValidStatus(String status) {
        return "Pending".equals(status) || "InProgress".equals(status) || "Completed".equals(status);
    }
}
