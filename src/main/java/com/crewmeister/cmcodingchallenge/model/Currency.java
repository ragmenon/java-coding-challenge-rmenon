package com.crewmeister.cmcodingchallenge.model;

import java.util.HashMap;
import java.util.Map;

public class Currency {
    public String name;
    public String code;
    Map<String, String> dates = new HashMap<>() {
    };

    public Map<String, String> getDates() {
        return dates;
    }

    public void setDates(Map<String, String> dates) {
        this.dates = dates;
    }

    public Currency(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public Currency(Map<String, String> dates) {
        this.dates = dates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
