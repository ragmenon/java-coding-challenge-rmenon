package com.crewmeister.cmcodingchallenge.util;

import com.crewmeister.cmcodingchallenge.model.Currency;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrencyUtilTest {
    private CurrencyUtil util;

    @Before
    public void setUp() {
        util = new CurrencyUtil();
    }

    @Test
    public void testListCurrencies() {
        String value = "{\n" +
                "\t\"symbols\": {\n" +
                "\t\t\"AED\": {\n" +
                "\t\t\t\"description\": \"United Arab Emirates Dirham\",\n" +
                "\t\t\t\"code\": \"AED\"\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}";
        List<Currency> list = util.listCurrencies(value);
        assertEquals(list.size(), 1);
        assertEquals(list.get(0).getName(), "United Arab Emirates Dirham");
        assertEquals(list.get(0).getCode(), "AED");
    }
}
