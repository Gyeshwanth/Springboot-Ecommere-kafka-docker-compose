package com.test.order.dto;

import com.test.order.model.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Integer orderId;
    private String Reference;
    private BigDecimal totalAmount;
    private PaymentMethod paymentMethod;
    private String customerId;

}
