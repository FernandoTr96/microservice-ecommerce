package com.ecomerce.ms_inventory.controller;

import com.ecomerce.ms_inventory.dto.InventoryRequestDTO;
import com.ecomerce.ms_inventory.dto.InventoryResponseDTO;
import com.ecomerce.ms_inventory.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/inventory")
public class InventoryController {
    
    private final InventoryService inventoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryResponseDTO create(@RequestBody @Valid InventoryRequestDTO request){
        return inventoryService.create(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponseDTO> getAll(){
        return inventoryService.list();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InventoryResponseDTO get(@PathVariable Long id){
        return inventoryService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InventoryResponseDTO delete(@PathVariable Long id){
        return inventoryService.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InventoryResponseDTO update(@PathVariable Long id, @RequestBody @Valid  InventoryRequestDTO request){
        return inventoryService.update(id,request);
    }

    @GetMapping("/{sku}/stock")
    @ResponseStatus(HttpStatus.OK)
    public  Boolean inStock(@PathVariable String sku, @RequestParam("quantity") Integer quantity){
        return inventoryService.isInStock(sku, quantity);
    }

    @PutMapping("/{sku}/reduce")
    @ResponseStatus(HttpStatus.OK)
    public String reduceStock(@PathVariable String sku, @RequestParam("quantity") Integer quantity){
        inventoryService.reduceStock(sku, quantity);
        return "Stock reducido para el producto: " + sku;
    }
}
