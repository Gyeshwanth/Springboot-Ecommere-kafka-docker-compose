package com.test.notification.kafka.order;

import java.math.BigDecimal;

public record ProductPurchaseResponse(
         Integer productId,
         String productName,
         String productDescription,
         double quantity,
         BigDecimal price

) {
}
