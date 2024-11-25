package com.test.order.dto;

import com.test.order.model.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderConfirmation {

    private String orderId;
    private BigDecimal totalAmount;
    private PaymentMethod paymentMethod;
    private CustomerResponse customerResponse;
    private List<ProductPurchaseResponse> products;
}
