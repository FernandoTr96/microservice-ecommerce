package com.ecomerce.ms_inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InventoryResponseDTO{
    Long id;
    String sku;
    Integer quantity;
    boolean inStock;
}
