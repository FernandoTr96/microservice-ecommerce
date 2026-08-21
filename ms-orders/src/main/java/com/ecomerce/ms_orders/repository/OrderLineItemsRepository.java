package com.ecomerce.ms_orders.repository;

import com.ecomerce.ms_orders.model.OrderLineItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineItemsRepository extends JpaRepository<OrderLineItems,Long> {
}
