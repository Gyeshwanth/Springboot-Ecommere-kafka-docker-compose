package com.test.order.controller;

import com.test.order.dto.OrderRequest;
import com.test.order.dto.OrderResponse;
import com.test.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping()
    public ResponseEntity<Integer> CreateOrder(@RequestBody @Valid OrderRequest orderRequest)  {
    return  ResponseEntity.ok(service.createOrder(orderRequest));
    }

    @GetMapping()
    public ResponseEntity<List<OrderResponse>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> findByID(@PathVariable("id") Integer id){
        return ResponseEntity.ok(service.findById(id));
    }

}
