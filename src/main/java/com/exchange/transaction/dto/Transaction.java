package com.exchange.transaction.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = TransactionDeserializer.class)
public class Transaction {

    private final String currencyPair;
    private final double rate;
    private final String orderType;
    private final double amount;

    public Transaction(String currencyPair, double rate, String orderType, double amount) {
        this.currencyPair = currencyPair;
        this.rate = rate;
        this.orderType = orderType;
        this.amount = amount;
    }

    public Transaction() {
        this.currencyPair = null;
        this.rate = 1;
        this.orderType = null;
        this.amount = 0;
    }

    public String getCurrencyPair() {
        return currencyPair;
    }

    public double getRate() {
        return rate;
    }

    public String getOrderType() {
        return orderType;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "currencyPair='" + currencyPair + '\'' +
                ", rate=" + rate +
                ", orderType='" + orderType + '\'' +
                ", amount=" + amount +
                '}';
    }
}
