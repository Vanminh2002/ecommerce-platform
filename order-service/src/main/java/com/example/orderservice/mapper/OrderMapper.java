package com.example.orderservice.mapper;

import com.example.orderservice.dto.orders.request.CreateOrderRequest;
import com.example.orderservice.dto.orders.request.UpdateOrderRequest;
import com.example.orderservice.dto.orders.response.OrderResponse;
import com.example.orderservice.entity.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Orders toEntity(CreateOrderRequest request);

    OrderResponse toResponse(Orders order);

    void updateOrder(@MappingTarget Orders order, UpdateOrderRequest request);
}
