package com.exchange.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Amount {
    private final double amount;

    public Amount() {
        this.amount = 0;
    }

    public Amount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Only Positive Number is allowed");
        }
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Amount amount1 = (Amount) o;

        return Double.compare(amount1.amount, amount) == 0;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(amount);
        return (int) (temp ^ (temp >>> 32));
    }
}
