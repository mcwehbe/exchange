package com.exchange.domain;

import javax.persistence.*;

@Entity
@Table(name = "order_status")
public final class OrderStatus {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    public String getName() {
        return name;
    }

    private final String name;

    public OrderStatus(String name) {
        this.name = name;
    }
    public OrderStatus() {
        this.name = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderStatus that = (OrderStatus) o;

        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
