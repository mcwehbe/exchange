package com.exchange.domain;

import javax.persistence.*;

@Entity
@Table(name = "currencies")
public final class Currency {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    private final String isoName;
    private final String description;

    public Currency() {
        this.isoName = null;
        this.description = null;
    }

    public Currency(String isoName, String description) {
        this.isoName = isoName;
        this.description = description;
    }

    public String getIsoName() {
        return isoName;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Currency currency = (Currency) o;

        if (id != null ? !id.equals(currency.id) : currency.id != null) return false;
        if (isoName != null ? !isoName.equals(currency.isoName) : currency.isoName != null) return false;
        return description != null ? description.equals(currency.description) : currency.description == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (isoName != null ? isoName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
