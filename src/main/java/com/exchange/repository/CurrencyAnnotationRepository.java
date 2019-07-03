package com.exchange.repository;

import com.exchange.domain.CurrencyAnnotation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyAnnotationRepository extends CrudRepository<CurrencyAnnotation, Integer> {
    List<CurrencyAnnotation> findAll();
}
