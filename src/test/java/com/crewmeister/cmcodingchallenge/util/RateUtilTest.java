package com.crewmeister.cmcodingchallenge.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RateUtilTest {

    private RateUtil util;

    @Before
    public void setUp() {
        util = new RateUtil();
    }

    @Test
    public void testGetExchangesRate() throws JsonProcessingException {
        String value = "{\n" +
                "\t\"rates\": {\n" +
                "\t\t\"EUR\": 0.832986\n" +
                "\t}\n" +
                "}";
        String output = util.getExchangesRate(value, "USD");
        assertEquals(output, "One USD is EUR-0.832986 EUR");
    }
}
