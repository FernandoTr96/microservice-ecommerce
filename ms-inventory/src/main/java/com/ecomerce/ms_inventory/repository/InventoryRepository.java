package com.ecomerce.ms_inventory.repository;

import com.ecomerce.ms_inventory.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    Optional<Inventory> findBySku(String sku);
    boolean existsBySku(String sku);
}
