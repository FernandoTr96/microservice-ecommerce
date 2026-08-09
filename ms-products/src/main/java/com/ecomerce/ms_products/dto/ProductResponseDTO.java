package com.ecomerce.ms_products.dto;

import java.math.BigDecimal;

public record ProductResponseDTO(
        String id,
        String name,
        String description,
        BigDecimal price,
        boolean inStock
) { }
