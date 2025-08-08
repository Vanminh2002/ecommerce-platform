package com.example.productservice.controller;

import com.example.productservice.dto.product.request.CreateProductRequest;
import com.example.productservice.dto.product.response.ProductResponse;
import com.example.productservice.service.product.ProductService;
import jakarta.annotation.Resource;
import org.example.dto.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product-service")
public class ProductController {
    @Resource
    private ProductService productService;


    @PostMapping("create-product")
    ApiResponse<ProductResponse> createProduct(@RequestBody CreateProductRequest request) {
        ProductResponse response = productService.createProduct(request);
        return ApiResponse.success(response);
    }


    @GetMapping("get-all")
    ApiResponse<List<ProductResponse>> getAllProduct() {
        List<ProductResponse> response = productService.getAllProducts();
        return ApiResponse.success(response);
    }

    @GetMapping("get-by/{id}")
    ApiResponse<ProductResponse> getProductById(@PathVariable Long id) {
        ProductResponse response = productService.getProductById(id);
        return ApiResponse.success(response);
    }

    @PutMapping("update/{id}")
    ApiResponse<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody CreateProductRequest request) {
        ProductResponse response = productService.updateProduct(id, request);
        return ApiResponse.success(response);
    }

    @DeleteMapping("delete/{id}")
    ApiResponse<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ApiResponse.success("Deleted");
    }
}
