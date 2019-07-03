package com.exchange.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "currency_annotations")
public final class CurrencyAnnotation {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "currencyOne_id", referencedColumnName = "id")
    private final Currency currencyOne;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "currencyTwo_id", referencedColumnName = "id")
    private final Currency currencyTwo;

    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "amount", column = @Column(name = "rate"))
    })
    private final Amount amount;


    public CurrencyAnnotation() {
        this.currencyOne = null;
        this.currencyTwo = null;
        this.amount = new Amount(0);
    }

    public CurrencyAnnotation(Currency currencyOne, Currency currencyTwo, Amount amount) {
        this.currencyOne = currencyOne;
        this.currencyTwo = currencyTwo;
        this.amount = amount;
    }

    public Currency getCurrencyOne() {
        return currencyOne;
    }

    public Currency getCurrencyTwo() {
        return currencyTwo;
    }

    public double getRate() {
        return amount.getAmount();
    }

    public Amount getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CurrencyAnnotation that = (CurrencyAnnotation) o;

        if (currencyOne != null ? !currencyOne.equals(that.currencyOne) : that.currencyOne != null) return false;
        if (currencyTwo != null ? !currencyTwo.equals(that.currencyTwo) : that.currencyTwo != null) return false;
        return amount != null ? amount.equals(that.amount) : that.amount == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (currencyOne != null ? currencyOne.hashCode() : 0);
        result = 31 * result + (currencyTwo != null ? currencyTwo.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }
}

