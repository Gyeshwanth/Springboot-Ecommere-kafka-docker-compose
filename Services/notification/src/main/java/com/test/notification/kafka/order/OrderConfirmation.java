package com.test.notification.kafka.order;

import com.test.notification.model.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(

         String orderId,
         BigDecimal totalAmount,
         PaymentMethod paymentMethod,
         CustomerResponse customerResponse,
         List<ProductPurchaseResponse> products
) {
}
