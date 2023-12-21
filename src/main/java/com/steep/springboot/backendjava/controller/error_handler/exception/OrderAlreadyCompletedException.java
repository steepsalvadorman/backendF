package com.steep.springboot.backendjava.controller.error_handler.exception;

public class OrderAlreadyCompletedException extends RuntimeException {

    public OrderAlreadyCompletedException(Long orderId) {
        super("Order with ID " + orderId + " is already completed and cannot be modified.");
    }
}