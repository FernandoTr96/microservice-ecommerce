package com.ecomerce.ms_orders.service;

import com.ecomerce.ms_orders.dto.OrderRequestDTO;
import com.ecomerce.ms_orders.dto.OrderResponseDTO;

import java.util.List;

public interface OrderService {
    OrderResponseDTO placeOrder(OrderRequestDTO orderRequest); // Create
    List<OrderResponseDTO> getAllOrders();                  // Read All
    OrderResponseDTO getOrderById(Long id);                 // Read One
    void deleteOrder(Long id);                           // Delete
}