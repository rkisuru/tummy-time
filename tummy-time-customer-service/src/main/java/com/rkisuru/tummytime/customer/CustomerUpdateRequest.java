package com.rkisuru.tummytime.customer;

public record CustomerUpdateRequest(

        String name,
        String phone,
        String email,
        Address address
) {
}
