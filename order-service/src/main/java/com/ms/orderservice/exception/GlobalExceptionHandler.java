package com.ms.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderIdNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleOrderIdNotFoundException(OrderIdNotFoundException ex){
        Map<String,String> error = new HashMap<>();
        error.put("message", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderedProductIdNotFound.class)
    public ResponseEntity<Map<String,String>> handleOrderedProductIdNotFoundException(OrderedProductIdNotFound ex){
        Map<String,String> error = new HashMap<>();
        error.put("message", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotAvailableInTheInventory.class)
    public ResponseEntity<Map<String,String>> handleProductNotAvailableInInventory(ProductNotAvailableInTheInventory ex){
        Map<String,String> error = new HashMap<>();
        error.put("message", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String,String>> handleRuntimeException(RuntimeException ex){
        Map<String,String> error = new HashMap<>();
        error.put("message", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }


}
