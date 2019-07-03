package com.exchange.service;

import com.exchange.rest.model.request.CreateOrderRequest;
import com.exchange.exception.ForbiddenException;
import com.exchange.exception.ResourceNotFoundException;
import com.exchange.rest.model.request.UpdateOrderStatusRequest;
import com.exchange.domain.*;
import com.exchange.dto.TransactionDto;
import com.exchange.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private OrderStatusRepository orderStatusRepository;
    private OrderTypeRepository orderTypeRepository;
    private CurrencyAnnotationRepository currencyAnnotationRepository;

    @Autowired
    public OrderServiceImpl(
            final OrderRepository orderRepository,
            final UserRepository userRepository,
            final OrderStatusRepository orderStatusRepository,
            final OrderTypeRepository orderTypeRepository,
            final CurrencyAnnotationRepository currencyAnnotationRepository
    ) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.orderTypeRepository = orderTypeRepository;
        this.currencyAnnotationRepository = currencyAnnotationRepository;
    }

    @Override
    public TransactionDto createNewOrder(double amount, int userId, int currencyAnnotationId, int orderStatusId, int orderTypeId) {

        User user = getUser(userId);
        OrderStatus orderStatus = getOrderStatus(orderStatusId);
        OrderType orderType = getOrderType(orderTypeId);
        CurrencyAnnotation currencyAnnotation = getCurrencyAnnotation(currencyAnnotationId);

        Order order = new Order(user, currencyAnnotation, orderType, orderStatus, new Amount(amount));
        return new TransactionDto(orderRepository.save(order));
    }

    @Override
    public TransactionDto updateOrderStatus(int userId, int orderStatusId, int orderId) {

        Order order = getOrderOrThrowException(orderId);
        checkOrderOwnedByUser(orderId, orderStatusId);
        order.updateOrderStatusId(getOrderStatus(orderStatusId));
        orderRepository.save(order);

        return new TransactionDto(order);
    }

    @Override
    public List<TransactionDto> getOrderByOrderStatusId(int orderStatusId) {
        Iterable<Order> orders = orderRepository.findAllByOrderStatusId(orderStatusId);
        return StreamSupport.stream(orders.spliterator(), false)
                .map(order -> new TransactionDto(order))
                .collect(Collectors.toList());
    }

    private void checkOrderOwnedByUser(int orderId, int userId) {
        orderRepository.findByIdAndUserId(orderId, userId)
                .orElseThrow(() -> new ForbiddenException("Updating the transaction is not allowed"));

    }

    private CurrencyAnnotation getCurrencyAnnotation(int currencyAnnotationId) {
        return currencyAnnotationRepository
                .findById(currencyAnnotationId)
                .orElseThrow(() -> new IllegalArgumentException("Currency Annotation not exist"));
    }

    private OrderType getOrderType(int orderTypeId) {
        return orderTypeRepository
                .findById(orderTypeId)
                .orElseThrow(() -> new IllegalArgumentException("Order Type does not exist"));
    }

    private OrderStatus getOrderStatus(int orderStatusId) {
        return orderStatusRepository
                .findById(orderStatusId)
                .orElseThrow(() -> new IllegalArgumentException("Order Status does not exist"));
    }

    private User getUser(int userId) {
        return userRepository
                .findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("UserId %d does not exist", userId))
                );
    }

    private Order getOrderOrThrowException(int orderId) {
        return orderRepository
                .findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("TransactionId %d does not exist", orderId))
                );
    }
}
