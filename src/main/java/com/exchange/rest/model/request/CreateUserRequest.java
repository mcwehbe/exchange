package com.exchange.rest.model.request;

import com.exchange.domain.User;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Malek Wehbe
 */
public class CreateUserRequest {

    private final User user;

    @JsonCreator
    public CreateUserRequest(@JsonProperty("user") User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
