package com.exchange.rest.controller;

import com.exchange.rest.model.request.CreateOrderRequest;
import com.exchange.domain.*;
import com.exchange.dto.TransactionDto;
import com.exchange.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {
    @MockBean
    private OrderService orderService;

    @Autowired
    private MockMvc mvc;

    // This object will be magically initialized by the initFields method below.
    private JacksonTester<Order> jsonOrder;
    private JacksonTester<List<TransactionDto>> jsonOrders;
    private JacksonTester<CreateOrderRequest> jsonCreateOrderRequest;

    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void postTransactionTest() throws Exception {
        User user = new User("test");
        Amount amountOrder = new Amount(11);
        OrderType orderType = new OrderType("test");
        OrderStatus orderStatus = new OrderStatus("test");

        Currency currencyGbp = new Currency("GBP", "Pound");
        Currency currencyUsd = new Currency("USD", "US Dollar");
        Amount amountUsd = new Amount(1.2);
        CurrencyAnnotation currencyGbpUsd = new CurrencyAnnotation(currencyGbp, currencyUsd, amountUsd);

        Order order = new Order(user, currencyGbpUsd, orderType, orderStatus, amountOrder);
        //given
        CreateOrderRequest createOrderRequest = new CreateOrderRequest(1.2, 1, 1, 1, 1);
        given(orderService.createNewOrder(
                createOrderRequest.getAmount(),
                createOrderRequest.getUserId(),
                createOrderRequest.getCurrencyAnnotationId(),
                createOrderRequest.getOrderStatusId(),
                createOrderRequest.getOrderTypeId()))
                .willReturn(new TransactionDto(order));

        // when
        MockHttpServletResponse response = mvc.perform(
                post("/v1/transactions").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonCreateOrderRequest.write(createOrderRequest).getJson()))
                .andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    public void getOrderTransactionByStatusTest() throws Exception {
        //given
        User user = new User("test");
        Amount amountOrder = new Amount(11);
        OrderType orderType = new OrderType("test");
        OrderStatus orderStatus = new OrderStatus("test");

        Currency currencyGbp = new Currency("GBP", "Pound");
        Currency currencyUsd = new Currency("USD", "US Dollar");
        Amount amountUsd = new Amount(1.2);
        CurrencyAnnotation currencyGbpUsd = new CurrencyAnnotation(currencyGbp, currencyUsd, amountUsd);

        TransactionDto order = new TransactionDto(new Order(user, currencyGbpUsd, orderType, orderStatus, amountOrder));
        List<TransactionDto> orders = Lists.newArrayList(order, order);

        given(orderService.getOrderByOrderStatusId(1)).willReturn(orders);

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/v1/transactions/orderStatus/1").contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).contains("orderStatus");
        assertThat(response.getContentAsString()).contains("exchangeRate");
        assertThat(response.getContentAsString()).contains("orderType");
        assertThat(response.getContentAsString()).contains("user");
    }
}
