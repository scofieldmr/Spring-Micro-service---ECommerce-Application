package com.ms.orderservice.client;

import com.ms.orderservice.dto.InventoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventory-service",url = "http://localhost:8102")
public interface InventoryClient {

    @GetMapping("/api/inventory/productAvailable/{product_id}")
    Boolean productExists(@PathVariable("product_id") long productId);

    @GetMapping("/api/inventory/get/{product_id}")
    InventoryDto getInventoryByProductId(@PathVariable("product_id") long productId);

    @PutMapping("/api/inventory/update/{product_id}")
    InventoryDto updateInventory(@PathVariable("product_id") long productId, @RequestParam("updInvQuantity") long productQuantity);

}
