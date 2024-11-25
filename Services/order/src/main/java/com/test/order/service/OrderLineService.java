package com.test.order.service;

import com.test.order.dto.OrderLineRequest;

import com.test.order.dto.OrderLineResponse;
import com.test.order.model.OrderLine;
import com.test.order.repository.OrderLineReposiotry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineReposiotry orderLineReposiotry;

    private final OrderLineMapper mapper;

    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {

        OrderLine orderLine = mapper.toOrderLine(orderLineRequest);

        return orderLineReposiotry.save(orderLine).getId();
    }

    public List<OrderLineResponse> findOrderItemsById(Integer orderId) {
        return orderLineReposiotry.findAllByOrderId(orderId).stream().map(mapper::toOrderLineResponse).collect(Collectors.toList());
    }
}