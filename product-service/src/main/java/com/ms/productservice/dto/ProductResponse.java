package com.ms.productservice.dto;

import java.math.BigDecimal;

public record ProductResponse(Long id, String name,
                              String brand, String description,BigDecimal price) {

}
