package com.test.product.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class ProductPurchaseException extends RuntimeException{
private final String message;
}
