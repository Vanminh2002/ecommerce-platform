package com.example.orderservice.dto.orders.request;

import com.example.orderservice.enums.OrderStatusEnums;
import com.example.orderservice.enums.PaymentMethodEnums;
import com.example.orderservice.enums.PaymentStatusEnums;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateOrderRequest {
    OrderStatusEnums status;

    PaymentMethodEnums paymentMethod;

    String shippingAddress;

    String note;
}
