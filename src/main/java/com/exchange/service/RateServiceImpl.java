package com.exchange.service;

import com.exchange.dto.CurrencyPairDto;
import com.exchange.repository.CurrencyAnnotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RateServiceImpl implements RateService {
    private CurrencyAnnotationRepository currencyAnnotationRepository;

    @Autowired
    public RateServiceImpl(final CurrencyAnnotationRepository currencyAnnotationRepository) {
        this.currencyAnnotationRepository = currencyAnnotationRepository;
    }

    @Override
    public List<CurrencyPairDto> getAllRates() {
        return currencyAnnotationRepository.findAll().stream()
                .map(currencyAnnotation -> new CurrencyPairDto(currencyAnnotation))
                .collect(Collectors.toList());
    }
}
