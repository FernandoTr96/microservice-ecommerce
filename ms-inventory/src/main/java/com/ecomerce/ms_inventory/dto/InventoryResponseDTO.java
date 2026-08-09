package com.ecomerce.ms_inventory.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record InventoryResponseDTO(
        Long id,
        String sku,
        Integer quantity,
        boolean inStock
) { }
