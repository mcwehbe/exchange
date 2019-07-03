package com.exchange.service;

import com.exchange.domain.Currency;
import com.exchange.repository.CurrencyRepository;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyServiceImplTest {
    private CurrencyServiceImpl currencyService;
    @Mock
    private CurrencyRepository currencyRepository;

    @Before
    public void setUp() {
        currencyService = new CurrencyServiceImpl(currencyRepository);
    }

    @Test
    public void getAllCurrenciesTest() {
        //given
        Currency currencyGbp = new Currency("GBP", "Pound");
        Currency currencyUsd = new Currency("USD", "US Dollar");
        Iterable<Currency> currencies = Lists.newArrayList(currencyGbp, currencyUsd);
        given(currencyRepository.findAll()).willReturn(currencies);
        //when
        Iterable<Currency> currencyServiceResult = currencyService.getAllCurrencies();
        //then
        assertThat(currencyServiceResult).isEqualTo(currencies);
    }
}
