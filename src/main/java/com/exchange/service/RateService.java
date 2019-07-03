package com.exchange.service;

import com.exchange.dto.CurrencyPairDto;

import java.util.List;

public interface RateService {
    List<CurrencyPairDto> getAllRates();
}
