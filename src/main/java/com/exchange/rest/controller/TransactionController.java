package com.exchange.rest.controller;


import com.exchange.rest.model.request.CreateOrderRequest;
import com.exchange.rest.model.request.UpdateOrderStatusRequest;
import com.exchange.dto.TransactionDto;
import com.exchange.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "v1/transactions")
public class TransactionController {

    private final OrderService orderService;

    @Autowired
    public TransactionController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    TransactionDto postTransaction(@RequestBody CreateOrderRequest orderRequest) {
        return orderService.createNewOrder(
                orderRequest.getAmount(),
                orderRequest.getUserId(),
                orderRequest.getCurrencyAnnotationId(),
                orderRequest.getOrderStatusId(),
                orderRequest.getOrderTypeId()
        );
    }

    @PatchMapping
    TransactionDto updateOrderStatusTransaction(@RequestBody UpdateOrderStatusRequest updateRequest) {
        return orderService.updateOrderStatus(
                updateRequest.getUserId(),
                updateRequest.getOrderStatusId(),
                updateRequest.getOrderId()
        );
    }

    @GetMapping({"/orderStatus/{orderStatusId}"})
    List<TransactionDto> getOrderTransactionByStatus(@PathVariable("orderStatusId") int orderStatusId) {
        return orderService.getOrderByOrderStatusId(orderStatusId);
    }
}
