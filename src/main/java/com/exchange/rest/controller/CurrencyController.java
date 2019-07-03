package com.exchange.rest.controller;

import com.exchange.domain.Currency;
import com.exchange.dto.CurrencyPairDto;
import com.exchange.service.CurrencyService;
import com.exchange.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "v1")
public class CurrencyController {

    private final CurrencyService currencyService;
    private final RateService rateServiceImpl;

    @Autowired
    public CurrencyController(
            final CurrencyService currencyService,
            final RateService rateServiceImpl
    ) {
        this.currencyService = currencyService;
        this.rateServiceImpl = rateServiceImpl;
    }

    @GetMapping("/currencies")
    public Iterable<Currency> getAllCurrencies() {
        return currencyService.getAllCurrencies();
    }

    @GetMapping("/rates")
    public List<CurrencyPairDto> getAllRateCurrencies() {
        return rateServiceImpl.getAllRates();
    }
}
