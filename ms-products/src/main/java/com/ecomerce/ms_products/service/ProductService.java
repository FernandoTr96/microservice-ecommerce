package com.ecomerce.ms_products.service;

import com.ecomerce.ms_products.dto.ProductRequestDTO;
import com.ecomerce.ms_products.dto.ProductResponseDTO;

import java.util.List;

public interface ProductService {
    ProductResponseDTO create(ProductRequestDTO request);
    List<ProductResponseDTO> list();
    ProductResponseDTO findById(String id);
    ProductResponseDTO update(String id, ProductRequestDTO request);
    ProductResponseDTO delete(String id);
}
