package com.test.order.dto;

import com.test.order.model.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class OrderRequest {

    private Integer id;
    private String reference;
    @Positive(message = "Order amount should be positive")
    private BigDecimal totalAmount;
    @NotNull(message = "Payment method should be precised")
    private PaymentMethod paymentMethod;
    @NotNull(message = "customer should be present")
    @NotBlank(message = "customer should be present")
    @NotEmpty(message = "customer should be present")
    private String customerId;
    @NotEmpty(message = "You should at least purschase one product")
    private List<ProductPurchaseDto> purchaseRequest;
}
