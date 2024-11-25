package com.test.order.service;

import com.test.order.dto.OrderLineRequest;
import com.test.order.dto.OrderLineResponse;
import com.test.order.model.Order;
import com.test.order.model.OrderLine;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class OrderLineMapper {

    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {

        return OrderLine.builder().
                    id(orderLineRequest.getId()).
                    quantity(orderLineRequest.getQuantity()).
                    order(Order.builder().id(orderLineRequest.getOrderId()).build()).
                    productId(orderLineRequest.getProductId()).
                build();

    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
    return new OrderLineResponse(orderLine.getId(), orderLine.getQuantity());
    }
}
