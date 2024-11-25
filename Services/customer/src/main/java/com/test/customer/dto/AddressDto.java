package com.test.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@AllArgsConstructor
public class AddressDto{
      private  String street;
        private String city;
        private String state;
        private String zipCode;
}
