package com.steep.springboot.backendjava.controller.error_handler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class OrderStatusNotFoundException extends ResponseStatusException {
    public OrderStatusNotFoundException(Long orderStatusId) {
        super(HttpStatus.NOT_FOUND, "OrderStatus not found with id: " + orderStatusId);
    }
}

