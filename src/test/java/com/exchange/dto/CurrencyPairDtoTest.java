package com.exchange.dto;

import com.exchange.domain.Amount;
import com.exchange.domain.Currency;
import com.exchange.domain.CurrencyAnnotation;
import com.exchange.dto.CurrencyPairDto;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CurrencyPairDtoTest {
    private CurrencyPairDto currencyPairDto;

    @Before
    public void setUp() {
        Currency gbp = new Currency("GBP", "GBP");
        Currency usd = new Currency("USD", "USD");
        Amount amount = new Amount(10.11);
        CurrencyAnnotation currencyGbpUsd = new CurrencyAnnotation(gbp, usd, amount);
        currencyPairDto = new CurrencyPairDto(currencyGbpUsd);
    }

    @Test
    public void getCurrencyPairTest() {
        assertThat(currencyPairDto.getCurrencyPair()).isEqualTo("GBP/USD");
    }

    @Test
    public void getRateTest() {
        assertThat(currencyPairDto.getRate()).isEqualTo(10.11);
    }

    @Test
    public void equalsTest() {
        Currency gbp = new Currency("GBP", "GBP");
        Currency usd = new Currency("USD", "USD");
        Amount amount = new Amount(10.11);
        CurrencyAnnotation currencyGbpUsd = new CurrencyAnnotation(gbp, usd, amount);
        assertThat(currencyPairDto).isEqualTo(new CurrencyPairDto(currencyGbpUsd));
    }
}
