package com.ecomerce.ms_orders.config.client;

import com.ecomerce.ms_orders.service.client.InventoryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class InventoryWebClientConfig {

    @Bean
    public WebClient webClient(){
        return WebClient.builder()
        .baseUrl("http://localhost:8082").build();
    }

    @Bean
    public InventoryClient createClient(WebClient webClient){
        WebClientAdapter adapter = WebClientAdapter.create(webClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(InventoryClient.class);
    }
}
