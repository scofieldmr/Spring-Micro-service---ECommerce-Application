package com.ms.inventoryservice.repository;

import com.ms.inventoryservice.dto.InventoryResponse;
import com.ms.inventoryservice.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findByProductid(long productId);

    Inventory findByProductidAndProductinvquantityGreaterThan(long productId, long i);
}
