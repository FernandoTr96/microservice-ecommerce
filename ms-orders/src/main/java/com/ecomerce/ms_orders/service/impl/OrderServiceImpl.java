package com.ecomerce.ms_orders.service.impl;

import com.ecomerce.ms_orders.dto.OrderRequestDTO;
import com.ecomerce.ms_orders.dto.OrderResponseDTO;
import com.ecomerce.ms_orders.exception.ResourceNotFoundException;
import com.ecomerce.ms_orders.mapper.OrderMapper;
import com.ecomerce.ms_orders.model.Order;
import com.ecomerce.ms_orders.model.OrderLineItems;
import com.ecomerce.ms_orders.repository.OrderRepository;
import com.ecomerce.ms_orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public OrderResponseDTO placeOrder(OrderRequestDTO orderRequest) {
        log.info("Colocando nueva orden...");

        // Mapeo manual de items para asegurar la lista
        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(orderMapper::toOrderLineItems)
                .toList();

        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderLineItemsList(orderLineItems);

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
