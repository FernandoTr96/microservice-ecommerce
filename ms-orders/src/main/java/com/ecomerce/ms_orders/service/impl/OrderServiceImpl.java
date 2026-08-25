package com.ecomerce.ms_orders.service.impl;

import com.ecomerce.ms_orders.dto.OrderRequestDTO;
import com.ecomerce.ms_orders.dto.OrderResponseDTO;
import com.ecomerce.ms_orders.exception.InsufficientStockException;
import com.ecomerce.ms_orders.exception.InventoryServiceException;
import com.ecomerce.ms_orders.exception.ResourceNotFoundException;
import com.ecomerce.ms_orders.mapper.OrderMapper;
import com.ecomerce.ms_orders.model.Order;
import com.ecomerce.ms_orders.repository.OrderRepository;
import com.ecomerce.ms_orders.service.OrderService;
import com.ecomerce.ms_orders.service.client.InventoryClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final InventoryClient inventoryClient;
//    private final WebClient inventoryBuilder;

    @Override
    @Transactional
    public OrderResponseDTO placeOrder(OrderRequestDTO orderRequest) {
        log.info("Colocando nueva orden...");
        // Mapeo manual de items para asegurar la lista
        Order order = orderMapper.toOrder(orderRequest);
        for (var item : order.getOrderLineItemsList()){
            String sku = item.getSku();
            Integer quantity =  item.getQuantity();
            try {
//                inventoryBuilder.put().uri("/api/v1/inventory/" + sku + "/reduce", uriBuilder -> uriBuilder.queryParam("quantity", quantity).build()).retrieve().bodyToMono(String.class).block();
                inventoryClient.reduceStock(sku, quantity);
            }catch (WebClientResponseException e){
                log.info("Error al reducir stock para el producto {} : {}", sku, e.getMessage());
                if (e.getStatusCode() == HttpStatus.NOT_FOUND) throw new ResourceNotFoundException("Inventory", "sku", sku);
                if (e.getStatusCode() == HttpStatus.CONFLICT) throw new InsufficientStockException(sku, quantity, quantity);
                throw new InventoryServiceException(sku);
            }
        }
        order.setOrderNumber(UUID.randomUUID().toString());
        // Guardamos y capturamos la entidad persistida
        Order savedOrder = orderRepository.save(order);
        log.info("Orden guardada con éxito. ID: {}", savedOrder.getId());
        return orderMapper.toOrderResponse(savedOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponseDTO> getAllOrders() {
        return orderRepository.findAll().stream()
        .map(orderMapper::toOrderResponse)
        .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public OrderResponseDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orden", "id", id));
        return orderMapper.toOrderResponse(order);
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new ResourceNotFoundException("Orden", "id", id);
        }
        orderRepository.deleteById(id);
        log.info("Orden eliminada. ID: {}", id);
    }
}
