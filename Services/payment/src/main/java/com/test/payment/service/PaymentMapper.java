package com.test.payment.service;

import com.test.payment.dto.PaymentRequest;
import com.test.payment.model.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {


    public Payment toPayment(PaymentRequest paymentRequest) {

        return Payment.builder()
                .id(paymentRequest.id())
                .paymentMethod(paymentRequest.paymentMethod())
                .amount(paymentRequest.amount())
                .OrderId(paymentRequest.orderId())
                .build();
    }
}
