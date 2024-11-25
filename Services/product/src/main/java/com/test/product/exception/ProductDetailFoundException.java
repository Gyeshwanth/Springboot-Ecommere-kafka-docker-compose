package com.test.product.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=true)
@Data
public class ProductDetailFoundException extends RuntimeException {

    private final String msg;

    
}
