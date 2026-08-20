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

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class InventoryServiceImpl implements InventoryService {

    InventoryMapper mapper;
    InventoryRepository repository;

    @Override
    public InventoryResponseDTO create(InventoryRequestDTO request) {
        Inventory inventory = mapper.toModel(request);
        Inventory inventorySaved = repository.save(inventory);
        return mapper.toResponse(inventorySaved);
    }

    @Override
    public List<InventoryResponseDTO> list() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    public InventoryResponseDTO findById(Long id) {
        Inventory inventory = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Inventory","id",id.toString()));
        return mapper.toResponse(inventory);
    }

    @Override
    public InventoryResponseDTO update(Long id, InventoryRequestDTO request) {
        Inventory inventory = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Inventory","id",id.toString()));
        mapper.updateFromRequest(request, inventory);
        Inventory inventoryUpdated = repository.save(inventory);
        return mapper.toResponse(inventoryUpdated);
    }

    @Override
    public InventoryResponseDTO delete(Long id) {
        Inventory inventory = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Inventory","id",id.toString()));
        repository.deleteById(inventory.getId());
        return mapper.toResponse(inventory);
    }
}
