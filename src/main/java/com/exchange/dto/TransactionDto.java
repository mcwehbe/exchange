package com.exchange.dto;

import com.exchange.domain.*;

import java.time.LocalDateTime;

public class TransactionDto {
    private final Order order;

    public TransactionDto(Order order) {
        this.order = order;
    }

    public User getUser() {
        return order.getUser();
    }

    public CurrencyPairDto getExchangeRate() {
        return new CurrencyPairDto(order.getCurrencyAnnotation());
    }

    public OrderType getOrderType() {
        return order.getOrderType();
    }

    public OrderStatus getOrderStatus() {
        return order.getOrderStatus();
    }

    public double getAmount() {
        return order.getAmount().getAmount();
    }

    public LocalDateTime getCreated_at() {
        return order.getCreatedDate();
    }

    public LocalDateTime getUpdated_at() {
        return order.getUpdatedDate();
    }
}
