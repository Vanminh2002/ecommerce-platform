package com.example.productservice.service.category;

import com.example.productservice.dto.category.request.CreateCategoryRequest;
import com.example.productservice.dto.category.response.CategoryResponse;
import com.example.productservice.entities.Category;
import com.example.productservice.mapper.CategoryMapper;
import com.example.productservice.repository.CategoryRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Resource
    private CategoryRepository categoryRepository;
    @Resource
    private CategoryMapper categoryMapper;

    public CategoryResponse createCategory(CreateCategoryRequest request) {
        if (categoryRepository.existsByName(request.getName())) {
            throw new RuntimeException("Category name already exists");
        }
        Category category = categoryMapper.toEntity(request);
        category = categoryRepository.save(category);

        return categoryMapper.toResponse(category);
    }


    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            throw new RuntimeException("No categories found");
        }
        return categories.stream().map(categoryMapper::toResponse).collect(Collectors.toList());
    }

    public CategoryResponse getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return categoryMapper.toResponse(category);
    }

    public void deleteCategoryById(Long id) {
        categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        categoryRepository.deleteById(id);
    }

    public CategoryResponse updateCategory(Long id, CreateCategoryRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        categoryMapper.updateCategory(category, request);
        var save = categoryRepository.save(category);
        return categoryMapper.toResponse(save);
    }

}
