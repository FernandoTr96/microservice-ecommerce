package com.ecomerce.ms_orders.exception;

import lombok.Getter;

@Getter
public class InventoryServiceException extends RuntimeException {

    private final String sku;

    public InventoryServiceException(String sku) {
        super(String.format(
            "Error communicating with Inventory Service for SKU '%s'",
            sku
        ));

        this.sku = sku;
    }
}