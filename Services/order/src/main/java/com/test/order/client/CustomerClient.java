package com.test.order.client;

import com.test.order.dto.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "customer-service")
public interface CustomerClient {

    @GetMapping("api/v1/customers/{id}")
    public Optional<CustomerResponse> getCustomerById(@PathVariable("id") String id);

}
