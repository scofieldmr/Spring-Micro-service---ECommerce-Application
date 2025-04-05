package com.ms.orderservice.service;

import com.ms.orderservice.dto.FullOrderResponse;
import com.ms.orderservice.dto.OrderRequest;
import com.ms.orderservice.dto.OrderResponse;
import com.ms.orderservice.entity.Order;

import java.util.List;

public interface OrderService {

    List<OrderResponse> getAllOrders();

    FullOrderResponse placeOrder(OrderRequest orderRequest);

    FullOrderResponse getOrderById(Long orderId);

    List<FullOrderResponse> getAllOrderedProducts();


}
