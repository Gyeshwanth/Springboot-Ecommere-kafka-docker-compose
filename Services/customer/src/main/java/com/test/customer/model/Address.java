package com.test.customer.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Validated
public class Address {
    private String street;
    private String city;
    private String state;
    private String zipCode;
}
