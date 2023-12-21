package com.steep.springboot.backendjava.controller.error_handler.exception;

public class InvalidOrderStatusException extends RuntimeException {

    public InvalidOrderStatusException(String invalidStatus) {
        super("Invalid order status: " + invalidStatus);
    }
}