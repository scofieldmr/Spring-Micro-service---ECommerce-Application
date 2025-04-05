package com.ms.inventoryservice.mapper;

import com.ms.inventoryservice.dto.InventoryResponse;
import com.ms.inventoryservice.entity.Inventory;

public class InventoryMapper {

    public static InventoryResponse inventoryToInventoryResponse(Inventory inventory) {
        return new InventoryResponse(inventory.getId(), inventory.getProductid(), inventory.getProductinvquantity());
    }
}
