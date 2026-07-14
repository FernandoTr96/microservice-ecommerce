package com.ecomerce.ms_products.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequestDTO(
        @NotBlank(message = "El nombre del producto es requerido")
        String name,
        String description,
        @NotNull(message = "El precio es requerido")
        @Positive(message = "El precio debe ser mayor a cero")
        BigDecimal price
) { }
