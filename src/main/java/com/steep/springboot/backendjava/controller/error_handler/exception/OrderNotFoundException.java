package com.steep.springboot.backendjava.controller.error_handler.exception;

import lombok.Getter;

@Getter
public class OrderNotFoundException extends RuntimeException {

    private Long orderId;

    public OrderNotFoundException(Long orderId) {
        super("Order not found with id: " + orderId);
        this.orderId = orderId;
    }
}
