package com.example.orderservice.mapper;

import com.example.orderservice.dto.orderDetails.request.CreateOrderDetailsRequest;
import com.example.orderservice.dto.orderDetails.request.UpdateOrderDetailsRequest;
import com.example.orderservice.dto.orderDetails.response.OrderDetailsResponse;
import com.example.orderservice.dto.orders.request.UpdateOrderRequest;
import com.example.orderservice.entity.OrderDetails;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OrderDetailsMapper {
    OrderDetails toEntity(CreateOrderDetailsRequest request);

    OrderDetailsResponse toResponse(OrderDetails orderDetails);

    void updateOrderDetails(@MappingTarget OrderDetails order, UpdateOrderDetailsRequest request);
}
