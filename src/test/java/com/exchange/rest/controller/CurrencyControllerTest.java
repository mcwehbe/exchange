package com.exchange.rest.controller;

import com.exchange.domain.Currency;
import com.exchange.dto.CurrencyPairDto;
import com.exchange.service.CurrencyService;
import com.exchange.service.RateService;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(CurrencyController.class)
public class CurrencyControllerTest {

    @MockBean
    private CurrencyService currencyService;
    @MockBean
    private RateService rateServiceImpl;

    @Autowired
    private MockMvc mvc;

    // This object will be magically initialized by the initFields method below.
    private JacksonTester<Iterable<Currency>> jsonCurrencies;
    private JacksonTester<Iterable<CurrencyPairDto>> jsonCurrencyPairs;

    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void getAllCurrenciesTest() throws Exception {
        // given
        Currency currencyGbp = new Currency("GBP", "Pound");
        Currency currencyUsd = new Currency("USD", "US Dollar");
        Iterable<Currency> currencies = Lists.newArrayList(currencyGbp, currencyUsd);
        given(currencyService.getAllCurrencies()).willReturn(currencies);

        //  when
        MockHttpServletResponse response = mvc.perform(
                get("/v1/currencies").accept(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //  then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonCurrencies.write(currencies).getJson());
    }
}
