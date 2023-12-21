package com.steep.springboot.backendjava;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.steep.springboot.backendjava.dao.entity.OrderDetailEntity;
import com.steep.springboot.backendjava.dao.entity.OrderEntity;
import com.steep.springboot.backendjava.dao.entity.ProductEntity;
import com.steep.springboot.backendjava.service.OrderDetailService;
import com.steep.springboot.backendjava.service.OrderService;
import com.steep.springboot.backendjava.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
public class OrderDetailTest {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private OrderService orderService;


    @Test
    public void shouldSaveOrderDetail() throws JsonProcessingException {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName("Product A");
        productEntity.setUnitPrice(50.0);
        productService.insertar(productEntity);
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderNumber("ORD123");
        orderEntity.setDate(LocalDate.now());
        orderEntity.setNumProducts(2);
        orderEntity.setFinalPrice(100.0);
        orderService.insertar(orderEntity);
        OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
        orderDetailEntity.setOrder(orderEntity);
        orderDetailEntity.setProducts((List<ProductEntity>) productEntity);
        orderDetailEntity.setQty(2);
        orderDetailEntity.setTotalPrice(100.0);
        System.out.println("OrderDetailEntity.getOrder().getId(): " + orderDetailEntity.getOrder().getId());
        orderDetailService.insertar(orderDetailEntity);
        assertNotNull(orderDetailEntity.getId());
        assertNotNull(orderDetailEntity.getOrder().getId());
    }
}

