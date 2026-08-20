package com.ecomerce.ms_inventory.mapper;

import com.ecomerce.ms_inventory.dto.InventoryRequestDTO;
import com.ecomerce.ms_inventory.dto.InventoryResponseDTO;
import com.ecomerce.ms_inventory.model.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface InventoryMapper {
   Inventory toModel(InventoryRequestDTO request);
   @Mapping(target = "inStock", expression = "java(inventory.getQuantity() > 0)")
   InventoryResponseDTO toResponse(Inventory inventory);
   @Mapping(target = "id", ignore = true)
   void updateFromRequest(InventoryRequestDTO request, @MappingTarget Inventory inventory);
}
