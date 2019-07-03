package com.exchange.service;

import com.exchange.rest.model.request.CreateOrderRequest;
import com.exchange.domain.*;
import com.exchange.repository.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImpTest {
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();
    private OrderServiceImpl orderServiceImpl;
    @Mock
    private CurrencyAnnotationRepository currencyAnnotationRepository;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private OrderStatusRepository orderStatusRepository;
    @Mock
    private OrderTypeRepository orderTypeRepository;
    private CreateOrderRequest createOrderRequest;

    @Before
    public void setUp() {
        orderServiceImpl = new OrderServiceImpl(
                orderRepository,
                userRepository,
                orderStatusRepository,
                orderTypeRepository,
                currencyAnnotationRepository);
    }


    @Test
    public void createNewOrderTestWithOrderTypeException() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Order Type does not exist");

        //given
        createOrderRequest = new CreateOrderRequest(1.2, 1, 1, 1, 1);
        User user = new User("name");
        given(userRepository.findById(createOrderRequest.getUserId())).willReturn(Optional.of(user));
        OrderStatus orderStatus = new OrderStatus();
        given(orderStatusRepository.findById(createOrderRequest.getOrderStatusId())).willReturn(Optional.of(orderStatus));
        OrderType orderType = new OrderType();
        given(orderTypeRepository.findById(createOrderRequest.getOrderStatusId())).willReturn(Optional.empty());
        //when
        orderServiceImpl.createNewOrder(
                createOrderRequest.getAmount(),
                createOrderRequest.getUserId(),
                createOrderRequest.getCurrencyAnnotationId(),
                createOrderRequest.getOrderStatusId(),
                createOrderRequest.getOrderTypeId());
    }

    @Test
    public void createNewOrderTestWithUserException() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("UserId 100 does not exist");

        //given
        createOrderRequest = new CreateOrderRequest(1.2, 100, 1, 1, 1);
        User user = new User("name");
        given(userRepository.findById(createOrderRequest.getUserId())).willReturn(Optional.empty());
        //when
        orderServiceImpl.createNewOrder(
                createOrderRequest.getAmount(),
                createOrderRequest.getUserId(),
                createOrderRequest.getCurrencyAnnotationId(),
                createOrderRequest.getOrderStatusId(),
                createOrderRequest.getOrderTypeId());
    }

    @Test
    public void createNewOrderTest() throws Exception {

        //given
        createOrderRequest = new CreateOrderRequest(1.2, 100, 1, 1, 1);
        User user = new User("name");
        given(userRepository.findById(createOrderRequest.getUserId())).willReturn(Optional.of(user));
        Currency currencyGbp = new Currency("GBP", "Pound");
        Currency currencyUsd = new Currency("USD", "US Dollar");
        Amount amountUsd = new Amount(1.2);
        CurrencyAnnotation currencyGbpUsd = new CurrencyAnnotation(currencyGbp, currencyUsd, amountUsd);
        given(currencyAnnotationRepository.findById(createOrderRequest.getCurrencyAnnotationId())).willReturn(Optional.of(currencyGbpUsd));
        OrderStatus orderStatus = new OrderStatus("test");
        given(orderStatusRepository.findById(createOrderRequest.getOrderStatusId())).willReturn(Optional.of(orderStatus));
        OrderType orderType = new OrderType("test");
        given(orderTypeRepository.findById(createOrderRequest.getOrderStatusId())).willReturn(Optional.of(orderType));
        //when
        orderServiceImpl.createNewOrder(
                createOrderRequest.getAmount(),
                createOrderRequest.getUserId(),
                createOrderRequest.getCurrencyAnnotationId(),
                createOrderRequest.getOrderStatusId(),
                createOrderRequest.getOrderTypeId());
        //then
        Order order = new Order(user, currencyGbpUsd, orderType, orderStatus, new Amount(1.2));
        verify(orderRepository).save(order);
    }

}
