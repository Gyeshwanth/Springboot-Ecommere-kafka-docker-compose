package com.test.payment.Controller;
import jakarta.validation.Valid;
import com.test.payment.dto.PaymentRequest;
import com.test.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;


    @PostMapping()
    public ResponseEntity<Integer> createPayment(@RequestBody @Valid PaymentRequest paymentRequest){

 return ResponseEntity.ok(paymentService.createPayment(paymentRequest));

    }

}
