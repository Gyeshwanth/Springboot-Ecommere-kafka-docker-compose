package com.test.payment.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
        String id,
        @NotNull(message = "firstName is required")
        String firstName,
        @NotNull(message = "lastName is required")
        String lastName,
        @Email(message = "Use valid formatter email")
        @NotNull(message = "email is required")
        String email
) {
}
