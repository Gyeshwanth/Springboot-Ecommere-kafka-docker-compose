package com.test.payment.repsoitry;

import com.test.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRespositry extends JpaRepository<Payment,Integer> {
}
