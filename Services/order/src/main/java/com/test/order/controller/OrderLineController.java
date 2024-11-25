package com.test.order.controller;

import com.test.order.dto.OrderLineResponse;
import com.test.order.service.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orderLines")
@RequiredArgsConstructor
public class OrderLineController {

    private final OrderLineService orderLineService;

    @GetMapping("/{id}")
    public ResponseEntity<List<OrderLineResponse>> findAllOrderItemsById(@PathVariable("id") Integer id){
       return ResponseEntity.ok(orderLineService.findOrderItemsById(id));
    }


}
