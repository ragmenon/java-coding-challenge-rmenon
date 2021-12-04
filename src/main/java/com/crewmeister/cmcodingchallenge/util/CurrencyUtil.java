package com.crewmeister.cmcodingchallenge.util;

import com.crewmeister.cmcodingchallenge.model.Currency;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CurrencyUtil {
    private static final String ONE = "One";
    private static final String IS = "is";
    private static final String EUR = "EUR";

    public List<Currency> listCurrencies(String response) {
        JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
        JsonObject symbols = jsonObject.getAsJsonObject("symbols");
        List<Currency> currencies = new ArrayList<Currency>();
        for (Iterator iterator = symbols.keySet().iterator(); iterator.hasNext(); ) {
            String code = (String) iterator.next();
            currencies.add(new Currency(symbols.get(code).getAsJsonObject().get("description").getAsString(), code));
        }
        return currencies;
    }
}
