package com.crewmeister.cmcodingchallenge.model;

import java.util.Map;

public class CurrencyExchange {
    public CurrencyExchange(Map<String, String> rates) {
        this.rates = rates;
    }

    Map<String, String> rates;


    public Map<String, String> getRates() {
        return rates;
    }

    public void setRates(Map<String, String> rates) {
        this.rates = rates;
    }
}
