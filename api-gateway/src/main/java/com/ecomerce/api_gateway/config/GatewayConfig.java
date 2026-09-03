package com.ecomerce.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder){
        return builder.routes()
        .route("ms-products", r -> r.path("/api/v1/product/**").uri("lb://MS-PRODUCTS"))
        .route("ms-orders", r -> r.path("/api/v1/order/**").uri("lb://MS-ORDERS"))
        .route("ms-inventory", r -> r.path("/api/v1/inventory/**").uri("lb://MS-INVENTORY"))
        .build();
    }
}
