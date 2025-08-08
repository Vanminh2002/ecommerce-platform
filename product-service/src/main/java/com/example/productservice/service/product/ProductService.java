package com.example.productservice.service.product;


import com.example.productservice.dto.product.request.CreateProductRequest;
import com.example.productservice.dto.product.response.ProductResponse;
import com.example.productservice.entities.Product;
import com.example.productservice.mapper.ProductMapper;
import com.example.productservice.repository.ProductRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Resource
    private ProductRepository productRepository;
    @Resource
    private ProductMapper productMapper;

    public ProductResponse createProduct(CreateProductRequest request) {
        if (productRepository.existsByProductName(request.getProductName())) {
            throw new RuntimeException("Product name already exists");
        }
        Product Product = productMapper.toEntity(request);
        Product = productRepository.save(Product);

        return productMapper.toResponse(Product);
    }


    public List<ProductResponse> getAllProducts() {
        List<Product> categories = productRepository.findAll();
        if (categories.isEmpty()) {
            throw new RuntimeException("No products found");
        }
        return categories.stream().map(productMapper::toResponse).collect(Collectors.toList());
    }

    public ProductResponse getProductById(Long id) {
        Product Product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return productMapper.toResponse(Product);
    }

    public void deleteProductById(Long id) {
        productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.deleteById(id);
    }

    public ProductResponse updateProduct(Long id, CreateProductRequest request) {
        Product Product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productMapper.updateProduct(Product, request);
        var save = productRepository.save(Product);
        return productMapper.toResponse(save);
    }
}
