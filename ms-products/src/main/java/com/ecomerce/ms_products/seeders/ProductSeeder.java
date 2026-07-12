package com.ecomerce.ms_products.seeders;

import lombok.RequiredArgsConstructor;
import com.ecomerce.ms_products.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import com.ecomerce.ms_products.repository.ProductRepository;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class ProductSeeder implements CommandLineRunner {

    private final ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {

        Product product = Product.builder()
        .name("Samsung Galaxy S24")
        .description("Smartphone con IA")
        .price(BigDecimal.valueOf(12000))
        .build();

        productRepository.save(product);
        System.out.println("Seeder "+product.getName()+" loaded");
    }
}
