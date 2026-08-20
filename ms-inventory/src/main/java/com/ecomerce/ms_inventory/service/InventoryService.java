package com.ecomerce.ms_inventory.service;

import com.ecomerce.ms_inventory.dto.InventoryRequestDTO;
import com.ecomerce.ms_inventory.dto.InventoryResponseDTO;

import java.util.List;

public interface InventoryService {
    InventoryResponseDTO create(InventoryRequestDTO request);
    List<InventoryResponseDTO> list();
    InventoryResponseDTO findById(Long id);
    InventoryResponseDTO update(Long id, InventoryRequestDTO request);
    InventoryResponseDTO delete(Long id);
    Boolean isInStock(String sku, Integer quantity);
}


