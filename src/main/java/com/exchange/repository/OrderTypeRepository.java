package com.exchange.repository;

import com.exchange.domain.OrderType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderTypeRepository extends CrudRepository<OrderType, Integer> {
}
