package com.ms.orderservice.mapper;

import com.ms.orderservice.dto.FullOrderResponse;
import com.ms.orderservice.dto.OrderRequest;
import com.ms.orderservice.dto.OrderResponse;
import com.ms.orderservice.dto.ProductDto;
import com.ms.orderservice.entity.Order;

public class OrderMapper {

    public static OrderResponse orderToOrderResponse(Order order) {
        return new OrderResponse(order.getId(), order.getProductid(), order.getQuantity());
    }

    public static Order orderRequestToOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setProductid(orderRequest.productId());
        order.setQuantity(orderRequest.quantity());

        return order;
    }

    public static FullOrderResponse orderAndProductToFullResponse(Order order, ProductDto productDto) {
        FullOrderResponse fullOrderResponse = new FullOrderResponse();
        fullOrderResponse.setOrderId(order.getId());
        fullOrderResponse.setProductId(order.getProductid());
        fullOrderResponse.setQuantity(order.getQuantity());
        fullOrderResponse.setProductName(productDto.name());
        fullOrderResponse.setBrandName(productDto.brand());
        fullOrderResponse.setDescription(productDto.description());
        fullOrderResponse.setTotalPrice(productDto.price()*order.getQuantity());

        return fullOrderResponse;
    }

}
