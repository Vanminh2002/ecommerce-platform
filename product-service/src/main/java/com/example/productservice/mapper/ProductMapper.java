package com.example.productservice.mapper;

import com.example.productservice.dto.category.request.CreateCategoryRequest;
import com.example.productservice.dto.product.request.CreateProductRequest;
import com.example.productservice.dto.product.response.ProductResponse;
import com.example.productservice.entities.Category;
import com.example.productservice.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(CreateProductRequest request);

    ProductResponse toResponse(Product product);
    void updateProduct(@MappingTarget Product product   , CreateProductRequest request);
}
