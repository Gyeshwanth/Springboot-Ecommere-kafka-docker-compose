package com.test.notification.kafka.payment;

import com.test.notification.model.PaymentMethod;

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
