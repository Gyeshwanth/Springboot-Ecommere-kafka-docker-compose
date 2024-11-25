package com.test.notification.kafka;

import com.test.notification.email.EmailService;
import com.test.notification.kafka.order.OrderConfirmation;
import com.test.notification.kafka.payment.PaymentNotificationRequest;
import com.test.notification.model.Notification;
import com.test.notification.repository.NotificationRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.test.notification.model.NotificationType.ORDER_CONFIRMATION;
import static com.test.notification.model.NotificationType.PAYMENT_CONFIRMATION;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository repository;
     private final EmailService service;

    @KafkaListener(topics = "payment-topic",groupId = "paymentGroup")
    public void consumePaymentSuccessNotification(PaymentNotificationRequest paymentConfirmation) throws MessagingException {
        log.info("Consuming the message from payment-topic :: {}", paymentConfirmation);

        repository.save(Notification.builder()
                        .notificationType(PAYMENT_CONFIRMATION)
                         .notificationDate(LocalDateTime.now())
                         .paymentConfirmation(paymentConfirmation)
                          .build());


       String customerName= paymentConfirmation.firstName()+" "+paymentConfirmation.lastName();

       service.sentPaymentSuccessEmail(paymentConfirmation.email(),customerName,paymentConfirmation.amount(),paymentConfirmation.orderReference());

    }

    @KafkaListener(topics = "order-topic",groupId = "orderGroup")
    public void consumeOrderSuccessNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info("Consuming the message from order-topic :: {}", orderConfirmation);

        repository.save(Notification.builder()
                .notificationType(ORDER_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .orderConfirmation(orderConfirmation)
                .build());

         String customerName= orderConfirmation.customerResponse().firstName()+" "+orderConfirmation.customerResponse().lastName();

        service.sentOrderConfirmationSuccessEmail(orderConfirmation.customerResponse().email(),customerName,orderConfirmation.totalAmount(), orderConfirmation.orderId(),orderConfirmation.products());

    }

}
