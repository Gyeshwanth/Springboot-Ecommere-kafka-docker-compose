package com.test.order.service;

import com.test.order.dto.OrderRequest;
import com.test.order.dto.OrderResponse;
import com.test.order.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {


    public Order toOrder(OrderRequest orderRequest) {
        if (orderRequest == null) {
            return null;
        }
        return Order.builder().id(orderRequest.getId())
                .reference(orderRequest.getReference())
                .customerId(orderRequest.getCustomerId())
                .totalAmount(orderRequest.getTotalAmount())
                .paymentMethod(orderRequest.getPaymentMethod())
                .build();

    }

    public OrderResponse fromOrder(Order order) {
        return new OrderResponse(order.getId(),order.getReference(),order.getTotalAmount(),order.getPaymentMethod(),order.getCustomerId());
    }

}
