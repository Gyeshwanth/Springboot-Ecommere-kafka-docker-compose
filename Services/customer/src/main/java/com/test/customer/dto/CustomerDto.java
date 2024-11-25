package com.test.customer.dto;


import com.test.customer.model.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerDto {
    private String id;
    @NotNull(message = "Customer firstname is required")
    private String firstName;
    @NotNull(message = "Customer lastname is required")
    private String lastName;
    @Email(message = "Customer email is not a valid email id")
    private String email;
    private AddressDto address;


}
