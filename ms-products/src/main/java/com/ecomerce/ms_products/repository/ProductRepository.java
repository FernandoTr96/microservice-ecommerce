package com.ecomerce.ms_products.repository;

import com.ecomerce.ms_products.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
