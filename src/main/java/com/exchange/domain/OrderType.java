package com.exchange.domain;

import javax.persistence.*;

@Entity
@Table(name = "order_types")
public final class OrderType {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    public String getName() {
        return name;
    }

    private final String name;

    public OrderType(String name) {
        this.name = name;
    }
    public OrderType() {
        this.name = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderType orderType = (OrderType) o;

        if (id != null ? !id.equals(orderType.id) : orderType.id != null) return false;
        return name != null ? name.equals(orderType.name) : orderType.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
