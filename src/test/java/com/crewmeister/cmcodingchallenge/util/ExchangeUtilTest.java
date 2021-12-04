package com.crewmeister.cmcodingchallenge.util;

import com.crewmeister.cmcodingchallenge.model.CurrencyExchange;
import com.crewmeister.cmcodingchallenge.model.Exchange;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExchangeUtilTest {
    private ExchangeUtil util;

    @Before
    public void setUp() {
        util = new ExchangeUtil();
    }

    @Test
    public void testlistExchangesRates() throws JsonProcessingException {
        String value = "{\n" +
                "\t\"date\": \"2021-11-27\",\n" +
                "\t\"rates\": {\n" +
                "\t\t\"AED\": 4.155728,\n" +
                "\t\t\"AFN\": 106.897178\n" +
                "\t}\n" +
                "}";
        CurrencyExchange currencyExchange = util.listExchangesRates(value);
        assertEquals(currencyExchange.getRates().size(), 2);
        assertEquals(currencyExchange.getRates().get("AED"), "4.155728");
    }

    @Test
    public void testLisAllExchangesRates() throws JsonProcessingException {
        String value = "{\n" +
                "\t\"rates\": {\n" +
                "\t\t\"2018-01-01\": {\n" +
                "\t\t\t\"USD\": 1.2005,\n" +
                "\t\t\t\"JPY\": 135.24\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}";
        Exchange currencyExchange = util.lisAllExchangesRates(value);
        assertEquals(currencyExchange.getDates().size(), 1);
        assertEquals(currencyExchange.getDates().get("2018-01-01").size(), 2);
    }
}
