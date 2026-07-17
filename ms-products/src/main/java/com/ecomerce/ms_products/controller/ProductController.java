package com.ecomerce.ms_products.controller;

import com.ecomerce.ms_products.dto.ProductRequestDTO;
import com.ecomerce.ms_products.dto.ProductResponseDTO;
import com.ecomerce.ms_products.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("unused")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/")
    public String root() {
        return "ms-products is working !!";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDTO create(@RequestBody @Valid ProductRequestDTO request){
        return productService.create(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponseDTO> getAll(){
        return productService.list();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponseDTO get(@PathVariable String id){
        return productService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponseDTO delete(@PathVariable String id){
        return productService.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponseDTO update(@PathVariable String id, @RequestBody @Valid  ProductRequestDTO request){
        return productService.update(id,request);
    }
}
