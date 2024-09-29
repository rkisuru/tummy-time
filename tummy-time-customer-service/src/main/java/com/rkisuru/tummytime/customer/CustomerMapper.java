package com.rkisuru.tummytime.customer;

import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    public Customer toCustomer(CustomerRequest request) {

        return Customer.builder()
                .name(request.name())
                .email(request.email())
                .phone(request.phone())
                .address(request.address())
                .build();
    }

    public CustomerResponse toCustomerResponse(Customer customer) {

        return CustomerResponse.builder()
                .customerId(customer.getId())
                .customerName(customer.getName())
                .customerEmail(customer.getEmail())
                .customerPhone(customer.getPhone())
                .customerAddress(customer.getAddress())
                .build();
    }

}
