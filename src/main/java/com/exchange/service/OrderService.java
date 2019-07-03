package com.exchange.service;

import com.exchange.rest.model.request.CreateOrderRequest;
import com.exchange.rest.model.request.UpdateOrderStatusRequest;
import com.exchange.dto.TransactionDto;

import java.util.List;

public interface OrderService {
    TransactionDto createNewOrder(double amount, int userId, int currencyAnnotationId, int orderStatusId, int orderTypeId);

    TransactionDto updateOrderStatus(int userId, int orderStatusId, int orderId);

    List<TransactionDto> getOrderByOrderStatusId(int orderStatusId);
}
