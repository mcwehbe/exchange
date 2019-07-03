package com.exchange.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class AmountTest {
    private Amount amount;

    @Test
    public void createPositiveAmount() {
        amount = new Amount(1.2);
        assertThat(amount.getAmount()).isEqualTo(1.2);
    }

    @Test
    public void createAmountWithNoParameter() {
        amount = new Amount();
        assertThat(amount.getAmount()).isEqualTo(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createNegativeAmountShouldThrow() {
        amount = new Amount(-1.2);
    }
}
