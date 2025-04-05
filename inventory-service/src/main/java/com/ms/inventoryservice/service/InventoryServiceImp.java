package com.ms.inventoryservice.service;

import com.ms.inventoryservice.dto.InventoryResponse;
import com.ms.inventoryservice.entity.Inventory;
import com.ms.inventoryservice.exception.ProductNotFoundException;
import com.ms.inventoryservice.mapper.InventoryMapper;
import com.ms.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImp implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public List<InventoryResponse> getAllInventory() {
        List<Inventory> inventoryList = inventoryRepository.findAll();

        List<InventoryResponse> inventoryResponseList = inventoryList.stream()
                .map(inventory -> InventoryMapper.inventoryToInventoryResponse(inventory)).toList();
        return inventoryResponseList;
    }

    @Override
    public InventoryResponse getInventoryById(long productId) {

        Inventory inventory = inventoryRepository.findByProductid(productId)
                .orElseThrow(()-> new ProductNotFoundException("Product not found in the Inventory"));

        return InventoryMapper.inventoryToInventoryResponse(inventory);
    }

    @Override
    public InventoryResponse updateInventory(long productId, long updProductInvQuantity) {
        Inventory inventory = inventoryRepository.findById(productId)
                .orElseThrow(()-> new ProductNotFoundException("Product not found in the Inventory"));

        inventory.setProductinvquantity(updProductInvQuantity);
        inventoryRepository.save(inventory);
        return InventoryMapper.inventoryToInventoryResponse(inventory);
    }

    @Override
    public boolean productExists(long productId) {

        Inventory inventory = inventoryRepository.findByProductid(productId)
                .orElseThrow(()-> new ProductNotFoundException("Product not found in the Inventory"));

        if(inventory.getProductinvquantity() > 0){
            return true;
        }
        return false;

//        Inventory inventory = inventoryRepository.findByProductIdAndProductInvQuantityGreaterThan(productId,0);
//
//        return inventory != null;
    }
}
