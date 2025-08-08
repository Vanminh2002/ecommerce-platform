package com.example.orderservice.services;

import com.example.orderservice.client.user.UserClient;
import com.example.orderservice.client.user.dto.UserDto;
import com.example.orderservice.dto.orders.request.CreateOrderRequest;
import com.example.orderservice.dto.orders.request.UpdateOrderRequest;
import com.example.orderservice.dto.orders.response.OrderResponse;
import com.example.orderservice.entity.Orders;
import com.example.orderservice.mapper.OrderMapper;
import com.example.orderservice.repository.OrderRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Resource
    private OrderRepository orderRepository;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private UserClient userClient;

    public OrderResponse createOrder(CreateOrderRequest request) {
        UserDto userDto = userClient.getById(request.getUserId()).getData();

        if (userDto == null) {
            throw new RuntimeException("User not found");
        }


        if (orderRepository.existsByOrderNumber(request.getOrderNumber())) {
            throw new RuntimeException("Order number already exists");
        }
        Orders order = orderMapper.toEntity(request);

        orderRepository.save(order);
        return orderMapper.toResponse(order);
    }


    public OrderResponse getOrderById(Long id) {
        Orders order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));


        UserDto userDto = userClient.getById(order.getUserId()).getData();

        return OrderResponse.builder()
                .orderNumber(order.getOrderNumber())
                .status(order.getStatus())
                .id(order.getId())
                .paymentStatus(order.getPaymentStatus())
                .shippingAddress(order.getShippingAddress())
                .totalAmount(order.getTotalAmount())
                .userId(userDto.getId())
                .note(order.getNote())
                .shippingFee(order.getShippingFee())
                .paymentMethod(order.getPaymentMethod())
                .build();
    }

    public OrderResponse updateOrder(Long id, UpdateOrderRequest request) {
        Orders order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng nào có id = " + id));

        orderMapper.updateOrder(order, request);
        var savedOrder = orderRepository.save(order);
        return orderMapper.toResponse(savedOrder);
    }

    public List<OrderResponse> getAllOrders() {
        List<Orders> orders = orderRepository.findAll();
        return orders.stream().map(orderMapper::toResponse).collect(Collectors.toList());
    }

    public void deleteOrder(Long id) {
        Orders order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng nào có id = " + id));

        orderRepository.delete(order);

    }
}
