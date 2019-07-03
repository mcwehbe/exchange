package com.exchange.rest.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateOrderStatusRequest {
    private final int userId;
    private final int orderStatusId;
    private final int orderId;

    @JsonCreator
    public UpdateOrderStatusRequest(
            @JsonProperty("userId") int userId,
            @JsonProperty("orderStatusId") int orderStatusId,
            @JsonProperty("orderId") int orderId
    ) {
        this.userId = userId;
        this.orderStatusId = orderStatusId;
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public int getOrderStatusId() {
        return orderStatusId;
    }

    public int getOrderId() {
        return orderId;
    }
}
