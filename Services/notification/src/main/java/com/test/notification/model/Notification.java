package com.test.notification.model;

import com.test.notification.kafka.order.OrderConfirmation;
import com.test.notification.kafka.payment.PaymentNotificationRequest;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Notification {

    @Id
    private String id;
    private NotificationType notificationType;

    private LocalDateTime notificationDate;
    private OrderConfirmation orderConfirmation;
    private PaymentNotificationRequest paymentConfirmation;
}
