package com.ms.orderservice.dto;

import java.math.BigInteger;

public record OrderRequest (Long productId, Long quantity) {

}
