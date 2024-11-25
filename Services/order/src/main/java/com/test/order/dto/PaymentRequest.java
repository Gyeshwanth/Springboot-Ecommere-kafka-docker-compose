package com.test.order.dto;

import com.test.order.model.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(

    BigDecimal amount,
    PaymentMethod paymentMethod,
    Integer orderId,
    String orderReference,
    CustomerResponse customer
){}
