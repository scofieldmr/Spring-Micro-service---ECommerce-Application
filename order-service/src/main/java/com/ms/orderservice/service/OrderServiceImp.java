package com.ms.orderservice.service;

import com.ms.orderservice.client.InventoryClient;
import com.ms.orderservice.client.ProductClient;
import com.ms.orderservice.dto.*;
import com.ms.orderservice.entity.Order;
import com.ms.orderservice.exception.OrderIdNotFoundException;
import com.ms.orderservice.exception.OrderedProductIdNotFound;
import com.ms.orderservice.exception.ProductNotAvailableInTheInventory;
import com.ms.orderservice.mapper.OrderMapper;
import com.ms.orderservice.repository.OrderRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImp implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private InventoryClient inventoryClient;


    @Override
    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderResponse> orderResponses = orders.stream()
                .map((order -> OrderMapper.orderToOrderResponse(order))).toList();

        return orderResponses;
    }

    @Override
    public FullOrderResponse placeOrder(OrderRequest orderRequest) {

        ProductDto productDto = null;

        try{
            productDto = productClient.findProductById(orderRequest.productId());

        }catch (FeignException.NotFound e) {
            throw new OrderedProductIdNotFound("Ordered Product Id " + orderRequest.productId() + " not Found ");
        }
        if(productDto==null) {
            throw new OrderedProductIdNotFound("Ordered Product Id "+ orderRequest.productId() +" not Found in the Product database");

        }

//        boolean availableInInventory = inventoryClient.productExists(orderRequest.productId());
//        if(availableInInventory==false){
//            throw new ProductNotAvailableInTheInventory("Product not available in Inventory");
//        }

        InventoryDto inventoryDto = inventoryClient.getInventoryByProductId(orderRequest.productId());
        Long invQuantity = inventoryDto.productInvQuantity();
        if(invQuantity<=0){
            throw new ProductNotAvailableInTheInventory("Out of Stock..Product not available in Inventory");
        }

        if(invQuantity<orderRequest.quantity()) {
            throw new RuntimeException("Ordered Product Id "+ orderRequest.productId() +" exceeded the quantity in the Inventory." +
                    "Inventory do not having the quantity");
        }

        Order order = OrderMapper.orderRequestToOrder(orderRequest);
        orderRepository.save(order);

        Long newInvQuantity = invQuantity-orderRequest.quantity();
        inventoryClient.updateInventory(inventoryDto.productId(), newInvQuantity);

        OrderResponse orderResponse = OrderMapper.orderToOrderResponse(order);

        return new FullOrderResponse(orderResponse.id(),orderResponse.productId(),orderResponse.quantity()
                ,productDto.name(),productDto.brand(), productDto.description(), productDto.price()*orderResponse.quantity());

    }

    @Override
    public FullOrderResponse getOrderById(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(()-> new OrderIdNotFoundException("Order not found for the ID :"+ orderId));


        var productDto = productClient.findProductById(order.getProductid());

        if(productDto == null) {
            throw new OrderedProductIdNotFound("Ordered Product Id Not Found : "+ order.getProductid());
        }

        var orderResponse = OrderMapper.orderToOrderResponse(order);
        return new FullOrderResponse(orderResponse.id(),orderResponse.productId(),orderResponse.quantity()
                ,productDto.name(),productDto.brand(), productDto.description(), productDto.price()*orderResponse.quantity());

    }

    @Override
    public List<FullOrderResponse> getAllOrderedProducts() {
        List<Order> orders = orderRepository.findAll();

        List<FullOrderResponse> fullOrderResponses = orders.stream()
                .map(order -> {
                    var productDto = productClient.findProductById(order.getProductid());
                    var orderResponse = OrderMapper.orderToOrderResponse(order);
                    return OrderMapper.orderAndProductToFullResponse(order, productDto);
                }).toList();

        return fullOrderResponses;
    }


}
