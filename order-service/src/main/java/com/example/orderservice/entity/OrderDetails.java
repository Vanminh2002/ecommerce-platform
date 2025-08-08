package com.example.orderservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.entities.BaseEntity;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "order_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetails extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "order_id")
    Long orderId;

    @Column(name = "product_id")
    List<Long> productId;

    @Column(name = "product_name")
    String productName;

    @Column(name = "product_sku")
    String productSku;

    @Column(name = "quantity")
    int quantity;

    @Column(name = "price")
    BigDecimal price;

    @Column(name = "discount")
    BigDecimal discount;

    @Column(name = "tal_price")
    BigDecimal talPrice;


//    order_status_history có thể thêm chức năng

}
