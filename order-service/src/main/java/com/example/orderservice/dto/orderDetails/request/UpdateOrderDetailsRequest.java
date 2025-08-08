package com.example.orderservice.dto.orderDetails.request;

import com.example.orderservice.enums.OrderStatusEnums;
import com.example.orderservice.enums.PaymentMethodEnums;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateOrderDetailsRequest {


    int quantity;

    BigDecimal price;

    BigDecimal discount;

    BigDecimal talPrice;
}
