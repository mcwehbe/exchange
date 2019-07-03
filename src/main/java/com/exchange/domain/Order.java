package com.exchange.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public final class Order {

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private final User user;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "currencyAnnotation_id", referencedColumnName = "id")
    private final CurrencyAnnotation currencyAnnotation;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "orderType_id", referencedColumnName = "id")
    private final OrderType orderType;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "orderStatus_id", referencedColumnName = "id")
    private OrderStatus orderStatus;

    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "amount", column = @Column(name = "amount"))
    })
    private final Amount amount;

    @Column(name = "created_at", columnDefinition = "datetime")
    private final LocalDateTime created_at = LocalDateTime.now();

    @Column(name = "updated_at", columnDefinition = "datetime")
    private final LocalDateTime updated_at = LocalDateTime.now();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Order(User user,
                 CurrencyAnnotation currencyAnnotation,
                 OrderType orderType,
                 OrderStatus orderStatus,
                 Amount amount
    ) {
        this.user = user;
        this.currencyAnnotation = currencyAnnotation;
        this.orderType = orderType;
        this.orderStatus = orderStatus;
        this.amount = amount;
    }

    public Order() {
        this.user = null;
        this.currencyAnnotation = null;
        this.orderType = null;
        this.orderStatus = null;
        this.amount = null;
    }

    public User getUser() {
        return user;
    }

    public CurrencyAnnotation getCurrencyAnnotation() {
        return currencyAnnotation;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public Amount getAmount() {
        return amount;
    }

    public LocalDateTime getCreatedDate() {
        return created_at;
    }

    public LocalDateTime getUpdatedDate() {
        return updated_at;
    }

    public void updateOrderStatusId(OrderStatus orderStatus){
        this.orderStatus = orderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (user != null ? !user.equals(order.user) : order.user != null) return false;
        if (currencyAnnotation != null ? !currencyAnnotation.equals(order.currencyAnnotation) : order.currencyAnnotation != null)
            return false;
        if (orderType != null ? !orderType.equals(order.orderType) : order.orderType != null) return false;
        if (orderStatus != null ? !orderStatus.equals(order.orderStatus) : order.orderStatus != null) return false;
        if (amount != null ? !amount.equals(order.amount) : order.amount != null) return false;
        return id != null ? id.equals(order.id) : order.id == null;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (currencyAnnotation != null ? currencyAnnotation.hashCode() : 0);
        result = 31 * result + (orderType != null ? orderType.hashCode() : 0);
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (created_at != null ? created_at.hashCode() : 0);
        result = 31 * result + (updated_at != null ? updated_at.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "user=" + user +
                ", currencyAnnotation=" + currencyAnnotation +
                ", orderType=" + orderType +
                ", orderStatus=" + orderStatus +
                ", amount=" + amount +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", id=" + id +
                '}';
    }
}
