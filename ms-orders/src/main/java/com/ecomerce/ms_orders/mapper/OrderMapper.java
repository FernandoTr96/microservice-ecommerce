package com.ecomerce.ms_orders.mapper;

import com.ecomerce.ms_orders.dto.OrderLineItemsRequestDTO;
import com.ecomerce.ms_orders.dto.OrderLineItemsResponseDTO;
import com.ecomerce.ms_orders.dto.OrderRequestDTO;
import com.ecomerce.ms_orders.dto.OrderResponseDTO;
import com.ecomerce.ms_orders.model.Order;
import com.ecomerce.ms_orders.model.OrderLineItems;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    // 1. De Request a Entidad
    // Mapeamos explícitamente la lista porque los nombres no coinciden
    @Mapping(source = "orderLineItemsDtoList", target = "orderLineItemsList")
    Order toOrder(OrderRequestDTO orderRequest);

    // Método auxiliar (MapStruct lo usa automáticamente para convertir cada ítem de la lista)
    // Aquí NO hace falta @Mapping porque los campos (sku, price, quantity) se llaman igual.
    OrderLineItems toOrderLineItems(OrderLineItemsRequestDTO orderLineItemsRequest);


    // 2. De Entidad a Response
    // Mapeamos explícitamente la lista de vuelta
    @Mapping(source = "orderLineItemsList", target = "orderLineItemsDtoList")
    OrderResponseDTO toOrderResponse(Order order);

    // Método auxiliar para la respuesta
    // Aquí NO hace falta @Mapping porque los campos (id, sku, price, quantity) se llaman igual.
    OrderLineItemsResponseDTO toOrderLineItemsResponse(OrderLineItems orderLineItems);
}