package com.exchange.transaction.repository;

import com.exchange.transaction.dto.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureWireMock(port = 0)
@ActiveProfiles("test")
public class TransactionRepositoryImplIT {

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    public void getTransactions() throws Exception {
        List<Transaction> transactions = transactionRepository.getTransactions();

        assertEquals(transactions.size(), 2);
        assertEquals("GBP/USD", transactions.get(1).getCurrencyPair());
        assertEquals("ask", transactions.get(1).getOrderType());
        assertEquals(20, transactions.get(1).getAmount(), 0);
        assertEquals(1.2, transactions.get(1).getRate(), 0);
    }

}