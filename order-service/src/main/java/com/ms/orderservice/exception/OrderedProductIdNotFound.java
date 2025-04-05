package com.ms.orderservice.exception;

public class OrderedProductIdNotFound extends RuntimeException {

    public OrderedProductIdNotFound(String message) {
        super(message);
    }
}
