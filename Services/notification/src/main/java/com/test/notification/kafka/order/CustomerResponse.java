package com.test.notification.kafka.order;

public record CustomerResponse(
         String customerId,
         String firstName,
         String lastName,
         String email
) {
}
