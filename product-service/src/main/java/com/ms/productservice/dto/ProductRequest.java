package com.ms.productservice.dto;

import java.math.BigDecimal;

public record ProductRequest(String name, String brand,
                             String description, BigDecimal price) {
}
