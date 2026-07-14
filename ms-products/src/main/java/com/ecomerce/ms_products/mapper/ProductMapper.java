package com.ecomerce.ms_products.mapper;

import com.ecomerce.ms_products.dto.ProductRequestDTO;
import com.ecomerce.ms_products.dto.ProductResponseDTO;
import com.ecomerce.ms_products.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "id", ignore = true)
    Product toModel(ProductRequestDTO request);
    ProductResponseDTO toResponse(Product product);
    @Mapping(target = "id", ignore = true)
    void updateProductFromRequest(ProductRequestDTO request, @MappingTarget Product product);
}
