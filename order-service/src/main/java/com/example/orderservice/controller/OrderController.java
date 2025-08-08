package com.example.orderservice.controller;

import com.example.orderservice.dto.orders.request.CreateOrderRequest;
import com.example.orderservice.dto.orders.request.UpdateOrderRequest;
import com.example.orderservice.dto.orders.response.OrderResponse;
import com.example.orderservice.services.OrderService;
import jakarta.annotation.Resource;
import org.example.dto.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {
    @Resource
    private OrderService orderService;


    @PostMapping("create-order")
    ApiResponse<OrderResponse> createOrder(@RequestBody CreateOrderRequest request) {
        OrderResponse response = orderService.createOrder(request);
        return ApiResponse.success(response);
    }

    @GetMapping("get-all")
    ApiResponse<List<OrderResponse>> getAllOrder() {
        List<OrderResponse> response = orderService.getAllOrders();
        return ApiResponse.success(response);
    }

    @GetMapping("get-by/{id}")
    ApiResponse<OrderResponse> GetById(@PathVariable Long id) {
        OrderResponse response = orderService.getOrderById(id);
        return ApiResponse.success(response);
    }

    @PutMapping("update-order/{id}")
    ApiResponse<OrderResponse> updateOrder(@PathVariable Long id, @RequestBody UpdateOrderRequest request) {
        OrderResponse response = orderService.updateOrder(id, request);
        return ApiResponse.success(response);
    }

    @DeleteMapping("delete-order/{id}")
    ApiResponse<String> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ApiResponse.success("deleted");
    }


}
