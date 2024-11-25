package com.test.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponse {

    private String customerId;
    private String firstName;
    private String lastName;
    private String email;
}
