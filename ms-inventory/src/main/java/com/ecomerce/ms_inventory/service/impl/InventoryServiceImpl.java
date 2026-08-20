package com.ecomerce.ms_inventory.service.impl;

import com.ecomerce.ms_inventory.dto.InventoryRequestDTO;
import com.ecomerce.ms_inventory.dto.InventoryResponseDTO;
import com.ecomerce.ms_inventory.exception.ResourceNotFoundException;
import com.ecomerce.ms_inventory.mapper.InventoryMapper;
import com.ecomerce.ms_inventory.model.Inventory;
import com.ecomerce.ms_inventory.repository.InventoryRepository;
import com.ecomerce.ms_inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class InventoryServiceImpl implements InventoryService {

    private final InventoryMapper mapper;
    private final InventoryRepository repository;

    @Override
    @Transactional
    public InventoryResponseDTO create(InventoryRequestDTO request) {
        boolean exists = repository.existsBySku(request.getSku());
        if(exists) throw new RuntimeException("El inventario para el SKU "+ request.getSku() +" ya existe");
        Inventory inventory = mapper.toModel(request);
        Inventory inventorySaved = repository.save(inventory);
        log.info("Inventario creado para el SKU: {}", inventorySaved.getSku());
        return mapper.toResponse(inventorySaved);
    }

    @Override
    public List<InventoryResponseDTO> list() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    public InventoryResponseDTO findById(Long id) {
        Inventory inventory = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Inventory","id",id));
        return mapper.toResponse(inventory);
    }

    @Override
    public InventoryResponseDTO update(Long id, InventoryRequestDTO request) {
        Inventory inventory = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Inventory","id",id));
        mapper.updateFromRequest(request, inventory);
        Inventory inventoryUpdated = repository.save(inventory);
        log.info("Inventario con SKU {} actualizado", inventoryUpdated.getSku());
        return mapper.toResponse(inventoryUpdated);
    }

    @Override
    public InventoryResponseDTO delete(Long id) {
        Inventory inventory = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Inventory","id",id));
        repository.deleteById(inventory.getId());
        log.info("Inventario con SKU {} eliminado", inventory.getSku());
        return mapper.toResponse(inventory);
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean isInStock(String sku, Integer quantity) {
        return repository.findBySku(sku).map(i-> i.getQuantity() >= quantity).orElse(false);
    }
}
