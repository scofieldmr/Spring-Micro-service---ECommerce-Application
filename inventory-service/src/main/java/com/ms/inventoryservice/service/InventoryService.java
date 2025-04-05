package com.ms.inventoryservice.service;

import com.ms.inventoryservice.dto.InventoryResponse;
import com.ms.inventoryservice.entity.Inventory;

import java.util.List;

public interface InventoryService {

    List<InventoryResponse> getAllInventory();

    InventoryResponse getInventoryById(long productId);

    InventoryResponse updateInventory(long productId, long productInvQuantity);

    boolean productExists(long productId);
}
