package com.test.payment.dto;




import com.test.payment.model.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(

        Integer id,

        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        Customer customer


) {}
