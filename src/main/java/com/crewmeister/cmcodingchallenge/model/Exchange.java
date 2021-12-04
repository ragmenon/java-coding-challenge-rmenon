package com.crewmeister.cmcodingchallenge.model;

import java.util.HashMap;
import java.util.Map;

public class Exchange {
    Map<String, Map<String, String>> dates = new HashMap<>() {
    };

    public Map<String, Map<String, String>> getDates() {
        return dates;
    }

    public void setDates(Map<String, Map<String, String>> dates) {
        this.dates = dates;
    }

    public Exchange(Map<String, Map<String, String>> dates) {
        this.dates = dates;
    }

}
