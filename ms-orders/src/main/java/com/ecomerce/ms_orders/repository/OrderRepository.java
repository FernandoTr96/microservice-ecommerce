package com.ecomerce.ms_orders.repository;

import com.ecomerce.ms_orders.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Long, Order> { }
