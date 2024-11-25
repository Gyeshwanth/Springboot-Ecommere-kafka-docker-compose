package com.test.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductPurchaseResponse {

  private    Integer productId;
    private String productName;
    private String productDescription;
   private BigDecimal productPrice;
    private double productQuantity;
}
