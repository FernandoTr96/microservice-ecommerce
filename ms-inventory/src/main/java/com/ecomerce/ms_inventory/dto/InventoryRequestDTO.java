package com.ecomerce.ms_inventory.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record InventoryRequestDTO(
        @NotBlank(message = "El sku es requerido")
        String sku,
        @Min(value = 0, message = "La cantidad no puede ser negativa")
        Integer quantity
) { }
