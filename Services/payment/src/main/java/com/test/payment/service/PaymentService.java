package com.test.payment.service;

import com.test.payment.dto.PaymentNotificationRequest;
import com.test.payment.dto.PaymentRequest;
import com.test.payment.kakfaConfig.NotificationProducer;
import com.test.payment.model.Payment;
import com.test.payment.repsoitry.PaymentRespositry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRespositry paymentRespositry;

    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;

    public Integer createPayment(PaymentRequest request) {

        Payment payment = paymentRespositry.save(mapper.toPayment(request));

        notificationProducer.sendNotification(new PaymentNotificationRequest(
                request.orderReference(),
                request.amount(),
                request.paymentMethod(),
                request.customer().firstName(),
                request.customer().lastName(),
                request.customer().email()
        ));
     return payment.getId();
    }
}
