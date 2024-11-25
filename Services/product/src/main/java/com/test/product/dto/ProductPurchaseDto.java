package com.test.product.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductPurchaseDto {

    @NotNull(message = "productId is mandatory")
    private int productId;

    @NotNull(message = "Quantity is mandatory")
    private double quantity;

}
