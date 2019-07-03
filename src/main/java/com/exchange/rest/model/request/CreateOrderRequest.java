package com.exchange.rest.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateOrderRequest {
    private final double amount;
    private final int userId;
    private final int currencyAnnotationId;
    private final int orderStatusId;
    private final int orderTypeId;

    @JsonCreator
    public CreateOrderRequest(
            @JsonProperty("amount") double amount,
            @JsonProperty("userId") int userId,
            @JsonProperty("currencyAnnotationId") int currencyAnnotationId,
            @JsonProperty("orderStatusId") int orderStatusId,
            @JsonProperty("orderTypeId") int orderTypeId
    ) {
        this.amount = amount;
        this.userId = userId;
        this.currencyAnnotationId = currencyAnnotationId;
        this.orderStatusId = orderStatusId;
        this.orderTypeId = orderTypeId;
    }

    public int getUserId() {
        return userId;
    }

    public int getCurrencyAnnotationId() {
        return currencyAnnotationId;
    }

    public int getOrderStatusId() {
        return orderStatusId;
    }

    public int getOrderTypeId() {
        return orderTypeId;
    }

    public double getAmount(){return amount;}
}
