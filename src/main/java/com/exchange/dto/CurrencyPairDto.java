package com.exchange.dto;

import com.exchange.domain.CurrencyAnnotation;

public final class CurrencyPairDto {

    private final CurrencyAnnotation currencyAnnotation;

    public CurrencyPairDto(CurrencyAnnotation currencyAnnotation) {
        this.currencyAnnotation = currencyAnnotation;
    }

    public String getCurrencyPair() {
        return currencyAnnotation.getCurrencyOne().getIsoName()
                + "/" +
                currencyAnnotation.getCurrencyTwo().getIsoName();
    }

    public double getRate() {
        return currencyAnnotation.getRate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CurrencyPairDto that = (CurrencyPairDto) o;

        return currencyAnnotation != null ? currencyAnnotation.equals(that.currencyAnnotation) : that.currencyAnnotation == null;
    }

    @Override
    public int hashCode() {
        return currencyAnnotation != null ? currencyAnnotation.hashCode() : 0;
    }
}
