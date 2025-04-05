package com.ms.orderservice.controller;

import com.ms.orderservice.dto.FullOrderResponse;
import com.ms.orderservice.dto.OrderRequest;
import com.ms.orderservice.dto.OrderResponse;
import com.ms.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private final OrderService orderService;

    @GetMapping("/all")
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        List<OrderResponse> orders = orderService.getAllOrders();
        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<FullOrderResponse> placeOrder(@RequestBody OrderRequest orderRequest) {
        FullOrderResponse orderResponse = orderService.placeOrder(orderRequest);
        return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
    }

    @GetMapping("/get/{order_id}")
    public ResponseEntity<FullOrderResponse> getOrderById(@PathVariable("order_id") Long orderId) {
        FullOrderResponse orderResponse = orderService.getOrderById(orderId);
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

    @GetMapping("/allOrderAndProd")
    public ResponseEntity<List<FullOrderResponse>> getAllOrderDetialsWithProd() {
        List<FullOrderResponse> orders = orderService.getAllOrderedProducts();
        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

}
