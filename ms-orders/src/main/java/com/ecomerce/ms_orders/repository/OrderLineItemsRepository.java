package com.ecomerce.ms_orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineItemsRepository extends JpaRepository<Long, OrderLineItemsRepository> {
}
