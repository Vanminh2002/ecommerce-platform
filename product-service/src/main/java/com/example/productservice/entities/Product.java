package com.example.productservice.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.entities.BaseEntity;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "product_name")
    String productName;

    @Column(name = "description")
    String description;

    @Column(name = "price")
    BigDecimal price;

//    @Column(name = "images")
//    String images;

    @Column(name = "category_id")
    List<Long> categoryId;
}
