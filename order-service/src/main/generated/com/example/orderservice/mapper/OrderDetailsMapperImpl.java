package com.example.orderservice.mapper;

import com.example.orderservice.dto.orderDetails.request.CreateOrderDetailsRequest;
import com.example.orderservice.dto.orderDetails.request.UpdateOrderDetailsRequest;
import com.example.orderservice.dto.orderDetails.response.OrderDetailsResponse;
import com.example.orderservice.entity.OrderDetails;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-07T13:42:59+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Microsoft)"
)
@Component
public class OrderDetailsMapperImpl implements OrderDetailsMapper {

    @Override
    public OrderDetails toEntity(CreateOrderDetailsRequest request) {
        if ( request == null ) {
            return null;
        }

        OrderDetails.OrderDetailsBuilder orderDetails = OrderDetails.builder();

        orderDetails.orderId( request.getOrderId() );
        List<Long> list = request.getProductId();
        if ( list != null ) {
            orderDetails.productId( new ArrayList<Long>( list ) );
        }
        orderDetails.productName( request.getProductName() );
        orderDetails.productSku( request.getProductSku() );
        orderDetails.quantity( request.getQuantity() );
        orderDetails.price( request.getPrice() );
        orderDetails.discount( request.getDiscount() );
        orderDetails.talPrice( request.getTalPrice() );

        return orderDetails.build();
    }

    @Override
    public OrderDetailsResponse toResponse(OrderDetails orderDetails) {
        if ( orderDetails == null ) {
            return null;
        }

        OrderDetailsResponse.OrderDetailsResponseBuilder orderDetailsResponse = OrderDetailsResponse.builder();

        orderDetailsResponse.id( orderDetails.getId() );
        orderDetailsResponse.orderId( orderDetails.getOrderId() );
        List<Long> list = orderDetails.getProductId();
        if ( list != null ) {
            orderDetailsResponse.productId( new ArrayList<Long>( list ) );
        }
        orderDetailsResponse.productName( orderDetails.getProductName() );
        orderDetailsResponse.productSku( orderDetails.getProductSku() );
        orderDetailsResponse.quantity( orderDetails.getQuantity() );
        orderDetailsResponse.price( orderDetails.getPrice() );
        orderDetailsResponse.discount( orderDetails.getDiscount() );
        orderDetailsResponse.talPrice( orderDetails.getTalPrice() );

        return orderDetailsResponse.build();
    }

    @Override
    public void updateOrderDetails(OrderDetails order, UpdateOrderDetailsRequest request) {
        if ( request == null ) {
            return;
        }

        order.setQuantity( request.getQuantity() );
        order.setPrice( request.getPrice() );
        order.setDiscount( request.getDiscount() );
        order.setTalPrice( request.getTalPrice() );
    }
}
