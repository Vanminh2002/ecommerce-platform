package com.example.orderservice.dto.orderDetails.response;

import com.example.orderservice.enums.OrderStatusEnums;
import com.example.orderservice.enums.PaymentMethodEnums;
import com.example.orderservice.enums.PaymentStatusEnums;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailsResponse {
    Long id;

    Long orderId;

    List<Long> productId;

    String productName;

    String productSku;

    int quantity;

    BigDecimal price;

    BigDecimal discount;

    BigDecimal talPrice;

}
