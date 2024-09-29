package com.rkisuru.tummytime.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerRequest(

        @NotBlank
        String name,

        @NotBlank
        String phone,

        @Email
        String email,

        @NotBlank
        Address address
) {
}
