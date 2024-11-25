package com.test.customer.service;

import com.test.customer.dto.AddressDto;
import com.test.customer.dto.CustomerDto;
import com.test.customer.model.Address;
import com.test.customer.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toCustomer(CustomerDto customerDto) {

        return Customer.builder().id(customerDto.getId())
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .email(customerDto.getEmail())
                .address(toAddress(customerDto.getAddress()))
                .build();
    }

    public Address toAddress(AddressDto addressDto) {
        return  Address.builder()
                .street(addressDto.getStreet())
                .city(addressDto.getCity())
                .state(addressDto.getState())
                .zipCode(addressDto.getZipCode())
                .build();
    }


    public CustomerDto fromCustomer(Customer customer) {
        AddressDto addressDto = (customer.getAddress() != null) ? fromAddress(customer.getAddress()) : null;
        return new CustomerDto(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getEmail(), addressDto);
    }

    public AddressDto fromAddress(Address address) {
        return new AddressDto(address.getStreet(), address.getCity(), address.getState(), address.getZipCode());
    }

}
