package com.example.productservice.mapper;

import com.example.productservice.dto.category.request.CreateCategoryRequest;
import com.example.productservice.dto.category.response.CategoryResponse;
import com.example.productservice.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toEntity(CreateCategoryRequest request);

    CategoryResponse toResponse(Category category);


    void updateCategory(@MappingTarget Category category, CreateCategoryRequest request);
}
