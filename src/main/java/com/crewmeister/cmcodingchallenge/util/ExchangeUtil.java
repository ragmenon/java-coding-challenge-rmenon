package com.crewmeister.cmcodingchallenge.util;

import com.crewmeister.cmcodingchallenge.model.CurrencyExchange;
import com.crewmeister.cmcodingchallenge.model.Exchange;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ExchangeUtil {
    public CurrencyExchange listExchangesRates(String response) throws JsonProcessingException {
        JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
        JsonObject currencies = jsonObject.getAsJsonObject("rates");
        List<String> currency = new ArrayList<String>();
        Map<String, String> resultMap = new HashMap<>();
        for (Iterator iterator = currencies.keySet().iterator(); iterator.hasNext(); ) {
            String key = (String) iterator.next();
            String value = currencies.get(key).toString();
            resultMap.put(key, value);
            currency.add(value);
        }
        return new CurrencyExchange(resultMap);
    }

    public Exchange lisAllExchangesRates(String response) throws JsonProcessingException {
        JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
        JsonObject currencies = jsonObject.getAsJsonObject("rates");
        Map<String, Map<String, String>> rates = new HashMap<>();
        for (Iterator iterator = currencies.keySet().iterator(); iterator.hasNext(); ) {
            String key = (String) iterator.next();
            String value = currencies.get(key).toString();
            Map<String, String> resultMap;
            ObjectMapper mapperObj = new ObjectMapper();
            resultMap = mapperObj.readValue(value,
                    new TypeReference<HashMap<String, String>>() {
                    });
            rates.put(key, resultMap);
        }
        return new Exchange(rates);
    }
}
