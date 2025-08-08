package com.example.orderservice.entity;

import com.example.orderservice.enums.OrderStatusEnums;
import com.example.orderservice.enums.PaymentMethodEnums;
import com.example.orderservice.enums.PaymentStatusEnums;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.entities.BaseEntity;

import java.math.BigDecimal;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Orders extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "user_id")
    Long userId;

    //    mã đơn hàng
    @Column(name = "order_number")
    String orderNumber;

    @Column(name = "status")
    OrderStatusEnums status;

    @Column(name = "total_amount")
    BigDecimal totalAmount;

    @Column(name = "shipping_fee")
    BigDecimal shippingFee;

    @Column(name = "payment_method")
    PaymentMethodEnums paymentMethod;

    @Column(name = "payment_status")
    PaymentStatusEnums paymentStatus;

    @Column(name = "shipping_address")
    String shippingAddress;

    @Column(name = "note")
    String note;
}
