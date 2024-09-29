package com.rkisuru.tummytime.customer;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public List<CustomerResponse> findAll() {

        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toCustomerResponse)
                .collect(Collectors.toList());
    }

    public Customer createCustomer(CustomerRequest request) {

        Customer customer = customerMapper.toCustomer(request);
        return customerRepository.save(customer);
    }

    public CustomerResponse findCustomerById(Long customerId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        return customerMapper.toCustomerResponse(customer);
    }

    public String deleteCustomer(Long customerId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        customerRepository.delete(customer);
        return "Customer deleted Successfully";
    }

    public Customer updateCustomer(Long customerId, CustomerUpdateRequest request) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        if (!request.name().isBlank()) {
            customer.setName(request.name());
            customerRepository.save(customer);
        }
        if (!request.phone().isBlank()) {
            customer.setPhone(request.phone());
            customerRepository.save(customer);
        }
        if (!request.email().isBlank()) {
            customer.setEmail(request.email());
            customerRepository.save(customer);
        }
        if (request.address() != null) {
            customer.setAddress(request.address());
            customerRepository.save(customer);
        }
        return customerRepository.save(customer);
    }
}
