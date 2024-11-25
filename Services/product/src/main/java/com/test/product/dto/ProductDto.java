package com.test.product.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ProductDto {

    private Integer id;
    @NotNull(message = "Product name required")
    private String name;
    @NotNull(message = "Product description required")
    private String description;
    @Positive(message = "available Quantity be positive")
    private double availableQty;
    @Positive(message = "price be positive")

    private BigDecimal price;

  @NotNull(message = "product category is required")
    private Integer categoryId;
}
