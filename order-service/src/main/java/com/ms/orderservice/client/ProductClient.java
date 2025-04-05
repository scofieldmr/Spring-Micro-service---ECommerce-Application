package com.ms.orderservice.client;

import com.ms.orderservice.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service",url = "http://localhost:8100")
public interface ProductClient {

    @GetMapping("/api/products/get/{product_id}")
    ProductDto findProductById(@PathVariable("product_id") Long productId);
}
