package com.steep.springboot.backendjava.service.impl;


import com.steep.springboot.backendjava.controller.error_handler.exception.OrderNotFoundException;
import com.steep.springboot.backendjava.controller.error_handler.exception.ProductNotFoundException;
import com.steep.springboot.backendjava.dao.entity.OrderDetailEntity;
import com.steep.springboot.backendjava.dao.entity.OrderEntity;
import com.steep.springboot.backendjava.dao.entity.ProductEntity;
import com.steep.springboot.backendjava.dao.repository.OrderDetailRepository;
import com.steep.springboot.backendjava.dao.repository.OrderRepository;
import com.steep.springboot.backendjava.dao.repository.ProductRepository;
import com.steep.springboot.backendjava.service.OrderDetailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderDetailImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Override
    public List<OrderDetailEntity> listarTodos() {
        return orderDetailRepository.findAll();
    }

    @Override
    public OrderDetailEntity obtenerUno(Long codigo) {
        return orderDetailRepository.findById(codigo).get();
    }

    @Override
    public OrderDetailEntity insertar(OrderDetailEntity od) {
        Long orderId = od.getOrder() != null ? od.getOrder().getId() : null;

        if (orderId == null) {
            throw new IllegalArgumentException("El ID de la orden no puede ser nulo");
        }

        OrderEntity orden = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
        List<ProductEntity> productos = od.getProducts();
        if (productos == null || productos.isEmpty()) {
            throw new IllegalArgumentException("La lista de productos no puede ser nula o vacía");
        }
        ProductEntity firstProduct = productos.get(0);
        Long productId = firstProduct.getId();
        if (productId == null) {
            throw new IllegalArgumentException("El ID del primer producto no puede ser nulo");
        }
        ProductEntity producto = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        od.setOrder(orden);
        od.setProducts((List<ProductEntity>) producto);
        return orderDetailRepository.save(od);
    }







    @Override
    public void modificar(OrderDetailEntity od) {
        orderDetailRepository.save(od);
    }

    @Override
    public void eliminar(Long codigo) {
        orderDetailRepository.deleteById(codigo);
    }
}
