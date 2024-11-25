package com.test.order.service;

import com.test.order.Exception.BussinessException;
import com.test.order.Exception.OrderNotFoundException;
import com.test.order.client.CustomerClient;
import com.test.order.client.PaymentClient;
import com.test.order.client.ProductClient;
import com.test.order.dto.*;

import com.test.order.kakfaConfig.OrderProducer;
import com.test.order.model.Order;
import com.test.order.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    @Transactional
    public Integer createOrder(OrderRequest orderRequest)  {


        CustomerResponse customerResponse = customerClient.getCustomerById(orderRequest.getCustomerId())
                .orElseThrow(() -> new BussinessException(
                        "Cannot create order:: No customer exist with id " + orderRequest.getCustomerId()));


        ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts = productClient.purchaseProducts(orderRequest.getPurchaseRequest());


        if (purchaseProducts.getStatusCode().isError()) {
            throw new BussinessException(
                    "An error occurred while processing the products purchase: " + purchaseProducts.getStatusCode());
        }

        Order order = repository.save(mapper.toOrder(orderRequest));

            for (ProductPurchaseDto productPurchaseDto : orderRequest.getPurchaseRequest()) {
                orderLineService.saveOrderLine(
                        new OrderLineRequest(null, order.getId(), productPurchaseDto.getProductId(), productPurchaseDto.getQuantity())
                );
            }


         PaymentRequest paymentRequest = new PaymentRequest(
                    orderRequest.getTotalAmount(),
                    orderRequest.getPaymentMethod(),
                    orderRequest.getId(),
                    orderRequest.getReference(),
                    customerResponse
            );
            paymentClient.requestOrderPayment(paymentRequest);


        orderProducer.sendOrderConfirmation( new OrderConfirmation(
                    orderRequest.getReference(),
                    orderRequest.getTotalAmount(),
                    orderRequest.getPaymentMethod(),
                    customerResponse,
                    purchaseProducts.getBody()));



        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return repository.findAll().stream().map(mapper::fromOrder).toList();
    }

    public OrderResponse findById(Integer id) {
        return repository.findById(id)
                .map(mapper::fromOrder)
                .orElseThrow(() -> new OrderNotFoundException(String.format("No Order Found with Provided ID: %d", id)));
    }
}
