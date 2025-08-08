package com.example.productservice.controller;

import com.example.productservice.dto.category.request.CreateCategoryRequest;
import com.example.productservice.dto.category.response.CategoryResponse;
import com.example.productservice.entities.Category;
import com.example.productservice.service.category.CategoryService;
import jakarta.annotation.Resource;
import org.example.dto.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category-service")
public class CategoryController {
    @Resource
    private CategoryService categoryService;


    @PostMapping("create-category")
    ApiResponse<CategoryResponse> createCategory(@RequestBody CreateCategoryRequest request) {
        CategoryResponse response = categoryService.createCategory(request);
        return ApiResponse.success(response);
    }


    @GetMapping("get-all")
    ApiResponse<List<CategoryResponse>> getAllCategory() {
        List<CategoryResponse> response = categoryService.getAllCategories();
        return ApiResponse.success(response);
    }

    @GetMapping("get-by/{id}")
    ApiResponse<CategoryResponse> getCategoryById(@PathVariable Long id) {
        CategoryResponse response = categoryService.getCategoryById(id);
        return ApiResponse.success(response);
    }

    @PutMapping("update/{id}")
    ApiResponse<CategoryResponse> updateCategory(@PathVariable Long id, @RequestBody CreateCategoryRequest request) {
        CategoryResponse response = categoryService.updateCategory(id, request);
        return ApiResponse.success(response);
    }
    @DeleteMapping("delete/{id}")
    ApiResponse<String> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
        return ApiResponse.success("Deleted");
    }
}
