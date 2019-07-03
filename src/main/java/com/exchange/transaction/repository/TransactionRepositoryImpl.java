package com.exchange.transaction.repository;

import com.exchange.transaction.dto.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {
    private final RestTemplate restTemplate;

    private final String url;

    public TransactionRepositoryImpl(
            RestTemplate restTemplate,
            @Value("${exchange.transaction.url}") String url) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    @Override
    public List<Transaction> getTransactions() {
        ResponseEntity<List<Transaction>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Transaction>>() {
                }
        );
        return response.getBody();
    }
}
