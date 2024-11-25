package com.test.payment.dto;

import com.test.payment.model.PaymentMethod;

import java.math.BigDecimal;

public record PaymentNotificationRequest(

        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String firstName,
        String lastName,
        String email
) {
}
