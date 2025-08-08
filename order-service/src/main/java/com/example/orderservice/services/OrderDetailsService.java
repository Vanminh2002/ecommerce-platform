package com.example.orderservice.services;

import com.example.orderservice.dto.orderDetails.request.CreateOrderDetailsRequest;
import com.example.orderservice.dto.orderDetails.request.UpdateOrderDetailsRequest;
import com.example.orderservice.dto.orderDetails.response.OrderDetailsResponse;
import com.example.orderservice.entity.OrderDetails;
import com.example.orderservice.mapper.OrderDetailsMapper;
import com.example.orderservice.repository.OrderDetailsRepository;
import com.example.orderservice.repository.OrderRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailsService {
    @Resource
    private OrderDetailsRepository orderDetailsRepositoryRepository;

    @Resource
    private OrderRepository orderRepository;
    @Resource
    private OrderDetailsMapper orderDetailsMapper;


    public OrderDetailsResponse createOrder(CreateOrderDetailsRequest request) {


        var orderId = orderRepository.findById(request.getOrderId()).orElseThrow(() -> new RuntimeException("Order not found"));

        OrderDetails order = orderDetailsMapper.toEntity(request);

        orderDetailsRepositoryRepository.save(order);
        return orderDetailsMapper.toResponse(order);
    }


    public OrderDetailsResponse getOrderDetailsById(Long id) {
        return orderDetailsMapper.toResponse(orderDetailsRepositoryRepository.findById(id).get());
    }

    public OrderDetailsResponse updateOrderDetails(Long id, UpdateOrderDetailsRequest request) {
        OrderDetails order = orderDetailsRepositoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng nào có id = " + id));

        orderDetailsMapper.updateOrderDetails(order, request);
        var savedOrder = orderDetailsRepositoryRepository.save(order);
        return orderDetailsMapper.toResponse(savedOrder);
    }

    public List<OrderDetailsResponse> getAllOrderDetails() {
        List<OrderDetails> OrderDetails = orderDetailsRepositoryRepository.findAll();
        return OrderDetails.stream().map(orderDetailsMapper::toResponse).collect(Collectors.toList());
    }

    public void deleteOrderDetails(Long id) {
        OrderDetails order = orderDetailsRepositoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng nào có id = " + id));

        orderDetailsRepositoryRepository.delete(order);

    }
}
