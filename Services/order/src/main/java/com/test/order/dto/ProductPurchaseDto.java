package com.test.order.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductPurchaseDto {
    @NotNull(message = "product is mandtory")
    private Integer productId;

    @NotNull(message = "Quantity is mandtory")
    private double quantity;
}
