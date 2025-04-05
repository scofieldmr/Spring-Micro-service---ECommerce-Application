package com.ms.orderservice.exception;

public class ProductNotAvailableInTheInventory extends RuntimeException {
    public ProductNotAvailableInTheInventory(String message) {
        super(message);
    }
}
