package com.test.customer.service;

import com.test.customer.Exception.CustomerNotFoundException;
import com.test.customer.dto.CustomerDto;
import com.test.customer.model.Customer;
import com.test.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public Customer  createCustomer(CustomerDto customerDto) {

        return repository.save(mapper.toCustomer(customerDto));

    }


    public void updateCustomer(String id,CustomerDto customerDto) {
        repository.findById(id).map(customer -> mapper.toCustomer(customerDto))
                .orElseThrow(()->new CustomerNotFoundException(String.format("Cannot update customer:: No Customer found with the provided ID:: %s",id)));

    }
    public List<CustomerDto> getAllCustomer() {
    return repository.findAll().stream().map(mapper::fromCustomer).collect(Collectors.toList());
    }

    public CustomerDto getCustomerById(String id) {
        return repository.findById(id)
                .map(mapper::fromCustomer)
                .orElseThrow(() ->
                        new CustomerNotFoundException(String.format("No Customer found with the provided ID: %s", id))
                );
    }

    public void deleteCustomer(String id) {
        repository.deleteById(id);
    }
}
