package com.ms.inventoryservice.controller;

import com.ms.inventoryservice.dto.InventoryRequest;
import com.ms.inventoryservice.dto.InventoryResponse;
import com.ms.inventoryservice.entity.Inventory;
import com.ms.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private final InventoryService inventoryService;

    @GetMapping("/all")
    public ResponseEntity<List<InventoryResponse>> getAllInventory(){
        List<InventoryResponse> inventoryResponseList = inventoryService.getAllInventory();

        if(inventoryResponseList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(inventoryResponseList, HttpStatus.OK);
    }

    @GetMapping("/get/{product_id}")
    public ResponseEntity<InventoryResponse> getInventoryByProductId(@PathVariable("product_id") Long productId){
        InventoryResponse inventoryResponse = inventoryService.getInventoryById(productId);
        return new ResponseEntity<>(inventoryResponse, HttpStatus.OK);
    }

    @PutMapping("/update/{product_id}")
    public ResponseEntity<InventoryResponse> updateInventoryByProductId(@PathVariable("product_id")Long productId, @RequestParam("updInvQuantity") Long updInvQuantity){
          InventoryResponse updInventoryResponse = inventoryService.updateInventory(productId, updInvQuantity);
          return new ResponseEntity<>(updInventoryResponse, HttpStatus.OK);
    }

    @GetMapping("/productAvailable/{product_id}")
    public ResponseEntity<Boolean> productExists(@PathVariable("product_id") Long productId){
        Boolean productAvailable = inventoryService.productExists(productId);
        return new ResponseEntity<>(productAvailable, HttpStatus.OK);
    }
}
