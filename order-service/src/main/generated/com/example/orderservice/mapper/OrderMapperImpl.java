package com.example.orderservice.mapper;

import com.example.orderservice.dto.orders.request.CreateOrderRequest;
import com.example.orderservice.dto.orders.request.UpdateOrderRequest;
import com.example.orderservice.dto.orders.response.OrderResponse;
import com.example.orderservice.entity.Orders;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-07T13:42:59+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Microsoft)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Orders toEntity(CreateOrderRequest request) {
        if ( request == null ) {
            return null;
        }

        Orders.OrdersBuilder orders = Orders.builder();

        orders.userId( request.getUserId() );
        orders.orderNumber( request.getOrderNumber() );
        orders.status( request.getStatus() );
        orders.totalAmount( request.getTotalAmount() );
        orders.shippingFee( request.getShippingFee() );
        orders.paymentMethod( request.getPaymentMethod() );
        orders.paymentStatus( request.getPaymentStatus() );
        orders.shippingAddress( request.getShippingAddress() );
        orders.note( request.getNote() );

        return orders.build();
    }

    @Override
    public OrderResponse toResponse(Orders order) {
        if ( order == null ) {
            return null;
        }

        OrderResponse.OrderResponseBuilder orderResponse = OrderResponse.builder();

        orderResponse.id( order.getId() );
        orderResponse.userId( order.getUserId() );
        orderResponse.orderNumber( order.getOrderNumber() );
        orderResponse.status( order.getStatus() );
        orderResponse.totalAmount( order.getTotalAmount() );
        orderResponse.shippingFee( order.getShippingFee() );
        orderResponse.paymentMethod( order.getPaymentMethod() );
        orderResponse.paymentStatus( order.getPaymentStatus() );
        orderResponse.shippingAddress( order.getShippingAddress() );
        orderResponse.note( order.getNote() );

        return orderResponse.build();
    }

    @Override
    public void updateOrder(Orders order, UpdateOrderRequest request) {
        if ( request == null ) {
            return;
        }

        order.setStatus( request.getStatus() );
        order.setPaymentMethod( request.getPaymentMethod() );
        order.setShippingAddress( request.getShippingAddress() );
        order.setNote( request.getNote() );
    }
}
