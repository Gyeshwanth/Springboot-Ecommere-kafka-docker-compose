package com.test.customer.controller;

import com.test.customer.dto.CustomerDto;
import com.test.customer.model.Customer;
import com.test.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping()
  public ResponseEntity<?> createCustomer(@RequestBody @Valid CustomerDto customerDto){
      Customer customer = service.createCustomer(customerDto);
      return new ResponseEntity<>(customer, HttpStatus.OK);
  }
   @PutMapping("/{id}")
  public ResponseEntity<?> updateCustomer(@PathVariable ("id") String id, @RequestBody @Valid CustomerDto customerDto){

      service.updateCustomer(id,customerDto);
  return new ResponseEntity<>(customerDto, HttpStatus.ACCEPTED);
    }

    @GetMapping()
    public ResponseEntity<?> getAllCustomer(){
        List<CustomerDto> allCustomer = service.getAllCustomer();
    return  new ResponseEntity<>(allCustomer, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable ("id") String id){
        CustomerDto customerById = service.getCustomerById(id);
        return new ResponseEntity<>(customerById, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable ("id") String id){
        service.deleteCustomer(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
