package com.crewmeister.cmcodingchallenge.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
public class RateUtil {
    private static final String ONE = "One";
    private static final String IS = "is";
    private static final String EUR = "EUR";

    public String getExchangesRate(String response, String currency) {
        JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
        JsonObject currencies = jsonObject.getAsJsonObject("rates");
        String value = null;
        for (Iterator iterator = currencies.keySet().iterator(); iterator.hasNext(); ) {
            String key = (String) iterator.next();
            value = key + "-" + currencies.get(key);
        }
        return ONE + " " + currency + " " + IS + " " + value + " " + EUR;
    }
}
