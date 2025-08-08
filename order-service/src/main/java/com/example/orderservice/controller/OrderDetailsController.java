package com.example.orderservice.controller;

import com.example.orderservice.dto.orderDetails.request.CreateOrderDetailsRequest;
import com.example.orderservice.dto.orderDetails.request.UpdateOrderDetailsRequest;
import com.example.orderservice.dto.orderDetails.response.OrderDetailsResponse;
import com.example.orderservice.services.OrderDetailsService;
import jakarta.annotation.Resource;
import org.example.dto.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orderDetails-service")
public class OrderDetailsController {
    @Resource
    private OrderDetailsService orderDetailsService;


    @PostMapping("create-orderDetails")
    ApiResponse<OrderDetailsResponse> createOrder(@RequestBody CreateOrderDetailsRequest request) {
        OrderDetailsResponse response = orderDetailsService.createOrder(request);
        return ApiResponse.success(response);
    }

    @GetMapping("get-all")
    ApiResponse<List<OrderDetailsResponse>> getAllOrder() {
        List<OrderDetailsResponse> response = orderDetailsService.getAllOrderDetails();
        return ApiResponse.success(response);
    }

    @GetMapping("get-by/{id}")
    ApiResponse<OrderDetailsResponse> GetById(@PathVariable Long id) {
        OrderDetailsResponse response = orderDetailsService.getOrderDetailsById(id);
        return ApiResponse.success(response);
    }

    @PutMapping("update-orderDetails/{id}")
    ApiResponse<OrderDetailsResponse> updateOrderDetails(@PathVariable Long id, @RequestBody UpdateOrderDetailsRequest request) {
        OrderDetailsResponse response = orderDetailsService.updateOrderDetails(id, request);
        return ApiResponse.success(response);
    }

    @DeleteMapping("delete-orderDetails/{id}")
    ApiResponse<String> deleteOrderDetails(@PathVariable Long id) {
        orderDetailsService.deleteOrderDetails(id);
        return ApiResponse.success("deleted");
    }


}
