package com.exchange.service;

import com.exchange.domain.Amount;
import com.exchange.domain.Currency;
import com.exchange.domain.CurrencyAnnotation;
import com.exchange.dto.CurrencyPairDto;
import com.exchange.repository.CurrencyAnnotationRepository;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class RateServiceImplTest {
    private RateServiceImpl rateService;
    @Mock
    private CurrencyAnnotationRepository currencyAnnotationRepository;

    @Before
    public void setUp() {
        rateService = new RateServiceImpl(currencyAnnotationRepository);
    }

    @Test
    public void getAllCurrenciesTest() {
        //given
        Currency currencyGbp = new Currency("GBP", "Pound");
        Currency currencyUsd = new Currency("USD", "US Dollar");
        Amount amountUsd = new Amount(1.2);
        Amount amountGbp = new Amount(1.0);
        CurrencyAnnotation currencyGbpUsd = new CurrencyAnnotation(currencyGbp, currencyUsd, amountUsd);
        CurrencyAnnotation currencyGbpGbp = new CurrencyAnnotation(currencyGbp, currencyGbp, amountGbp);
        List<CurrencyAnnotation> rates = Lists.newArrayList(currencyGbpUsd, currencyGbpGbp);

        given(currencyAnnotationRepository.findAll()).willReturn(rates);
        //when
        List<CurrencyPairDto> getAllRatesResult = rateService.getAllRates();
        //then
        CurrencyPairDto currencyGbpUsdDto = new CurrencyPairDto(currencyGbpUsd);
        CurrencyPairDto currencyGbpGbpDto = new CurrencyPairDto(currencyGbpGbp);
        List<CurrencyPairDto> pairRates = Lists.newArrayList(currencyGbpUsdDto, currencyGbpGbpDto);

        assertThat(getAllRatesResult).isEqualTo(pairRates);
    }
}
