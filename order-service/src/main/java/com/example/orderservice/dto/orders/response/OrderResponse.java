package com.example.orderservice.dto.orders.response;

import com.example.orderservice.enums.OrderStatusEnums;
import com.example.orderservice.enums.PaymentMethodEnums;
import com.example.orderservice.enums.PaymentStatusEnums;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    Long id;

    Long userId;

    //    mã đơn hàng
    String orderNumber;

    OrderStatusEnums status;

    BigDecimal totalAmount;

    BigDecimal shippingFee;

    PaymentMethodEnums paymentMethod;

    PaymentStatusEnums paymentStatus;

    String shippingAddress;

    String note;
}
