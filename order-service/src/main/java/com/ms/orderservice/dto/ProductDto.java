package com.ms.orderservice.dto;

import java.math.BigDecimal;

public record ProductDto(Long id, String name,
                         String brand, String description, Double price) {

}
