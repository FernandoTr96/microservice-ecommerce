package com.ecomerce.ms_products.service.impl;
import com.ecomerce.ms_products.dto.ProductRequestDTO;
import com.ecomerce.ms_products.dto.ProductResponseDTO;
import com.ecomerce.ms_products.exception.ResourceNotFoundException;
import com.ecomerce.ms_products.mapper.ProductMapper;
import com.ecomerce.ms_products.model.Product;
import com.ecomerce.ms_products.repository.ProductRepository;
import com.ecomerce.ms_products.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class ProductServiceImpl implements ProductService {

    private final ProductMapper mapper;
    private final ProductRepository repository;

    @Override
    public ProductResponseDTO create(ProductRequestDTO request) {
        Product product = mapper.toModel(request);
        Product createdProduct = repository.save(product);
        log.info("Product {} saved", createdProduct.getName());
        return mapper.toResponse(createdProduct);
    }

    @Override
    public List<ProductResponseDTO> list() {
        return repository.findAll()
        .stream()
        .map(mapper::toResponse)
        .toList();
    }

    @Override
    public ProductResponseDTO findById(String id) {
        Product product = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product","id", id));
        return mapper.toResponse(product);
    }

    @Override
    public ProductResponseDTO update(String id, ProductRequestDTO request) {
        Product product = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product","id", id));
        mapper.updateProductFromRequest(request, product);
        Product updatedProduct = repository.save(product);
        return mapper.toResponse(updatedProduct);
    }

    @Override
    public ProductResponseDTO delete(String id) {
        Product product = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product","id", id));
        repository.deleteById(product.getId());
        return mapper.toResponse(product);
    }
}
