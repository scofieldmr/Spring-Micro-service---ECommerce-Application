package com.ms.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FullOrderResponse {

    private Long orderId;
    private Long productId;
    private Long quantity;

    private String productName;
    private String brandName;
    private String description;

    private Double totalPrice;
}
