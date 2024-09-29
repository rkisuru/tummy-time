package com.rkisuru.tummytime.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {

    private Long customerId;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private Address customerAddress;
}
