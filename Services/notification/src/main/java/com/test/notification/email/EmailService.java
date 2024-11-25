package com.test.notification.email;

import com.test.notification.kafka.order.ProductPurchaseResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;

import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.test.notification.email.EmailTemplates.ORDER_CONFIRMATION;
import static com.test.notification.email.EmailTemplates.PAYMENT_CONFIRMATION;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;


    @Async
    public void sentPaymentSuccessEmail(String toEmail, String customerName, BigDecimal amount,String orderReference) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_RELATED, StandardCharsets.UTF_8.name());
     messageHelper.setFrom("yeshu.fsd@gmail.com");

     final String templateName = PAYMENT_CONFIRMATION.getTemplate();

     Map<String,Object> vars =new HashMap<>();

        vars.put("customerName",customerName);
        vars.put("amount",amount);
        vars.put("orderReference",orderReference);

        Context context = new Context();
        context.setVariables(vars);

        messageHelper.setSubject(PAYMENT_CONFIRMATION.getSubject());

        try {
            String htmlTemplate = templateEngine.process(templateName, context);
            messageHelper.setText(htmlTemplate, true);

            messageHelper.setTo(toEmail);
            mailSender.send(mimeMessage);

            log.info(String.format("Email Successfully sent to %s with template %s",toEmail,htmlTemplate));

        } catch (MessagingException e) {
            log.warn("Warning - Cannot send mail to {}", toEmail);

        }

    }

    @Async
    public void sentOrderConfirmationSuccessEmail(String toEmail, String customerName, BigDecimal amount, String orderReference, List<ProductPurchaseResponse> products) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_RELATED, StandardCharsets.UTF_8.name());
        messageHelper.setFrom("yeshu.fsd@gmail.com");

        final String templateName = ORDER_CONFIRMATION.getTemplate();

        Map<String,Object> vars =new HashMap<>();

        vars.put("customerName",customerName);
        vars.put("totalAmount",amount);
        vars.put("orderReference",orderReference);
        vars.put("products",products);

        Context context = new Context();
        context.setVariables(vars);

        messageHelper.setSubject(ORDER_CONFIRMATION.getSubject());

        try {
            String htmlTemplate = templateEngine.process(templateName, context);
            messageHelper.setText(htmlTemplate, true);

            messageHelper.setTo(toEmail);
            mailSender.send(mimeMessage);

            log.info("Email Successfully sent to {} with template {}", toEmail, htmlTemplate);

        } catch (MessagingException e) {
            log.warn("Warning - Cannot send mail to {}", toEmail);

        }



    }

}
