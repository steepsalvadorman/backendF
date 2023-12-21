package com.steep.springboot.backendjava.controller.error_handler.exception;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends RuntimeException {

    private final Long productId;

    public ProductNotFoundException(Long productId) {
        super("Product not found with id: " + productId);
        this.productId = productId;
    }

}
