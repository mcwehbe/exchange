package com.exchange.service;

import com.exchange.domain.Currency;

public interface CurrencyService {
    Iterable<Currency> getAllCurrencies();
}
