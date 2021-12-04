package com.crewmeister.cmcodingchallenge.controller;

import com.crewmeister.cmcodingchallenge.model.Currency;
import com.crewmeister.cmcodingchallenge.model.CurrencyConversionRates;
import com.crewmeister.cmcodingchallenge.model.CurrencyExchange;
import com.crewmeister.cmcodingchallenge.model.Exchange;
import com.crewmeister.cmcodingchallenge.util.CurrencyUtil;
import com.crewmeister.cmcodingchallenge.util.ExchangeUtil;
import com.crewmeister.cmcodingchallenge.util.RateUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController()
@RequestMapping("/api")
public class CurrencyController {

    private static final String TIME = "timeseries?start_date=2018-01-01&end_date=2021-11-30&symbols=AED,AFN,ALL,AMD,ANG,AOA,ARS,AUD,AWG,AZN,BAM,BBD,BDT,BGN,BHD,BIF,BMD,BND,BOB,BRL,BSD,BTC,BTN,BWP,BYN,BZD,CAD,CDF,CHF,CLF,CLP,CNH,CNY,COP,CRC,CUC,CUP,CVE,CZK,DJF,DKK,DOP,DZD,EGP,ERN,ETB,EUR,FJD,FKP,GBP,GEL,GGP,GHS,GIP,GMD,GNF,GTQ,GYD,HKD,HNL,HRK,HTG,HUF,IDR,ILS,IMP,INR,IQD,IRR,ISK,JEP,JMD,JOD,JPY,KES,KGS,KHR,KMF,KPW,KRW,KWD,KYD,KZT,LAK,LBP,LKR,LRD,LSL,LYD,MAD,MDL,MGA,MKD,MMK,MNT,MOP,MRO,MRU,MUR,MVR,MWK,MXN,MYR,MZN,NAD,NGN,NIO,NOK,NPR,NZD,OMR,PAB,PEN,PGK,PHP,PKR,PLN,PYG,QAR,RON,RSD,RUB,RWF,SAR,SBD,SCR,SDG,SEK,SGD,SHP,SLL,SOS,SRD,SSP,STD,STN,SVC,SYP,SZL,THB,TJS,TMT,TND,TOP,TRY,TTD,TWD,TZS,UAH,UGX,USD,UYU,UZS,VEF,VES,VND,VUV,WST,XAF,XAG,XAU,XCD,XDR,XOF,XPD,XPF,XPT,YER,ZAR,ZMW,ZWL";
    private static final String BASE = "?base=";
    private static final String SYMBOLS_EUR = "&symbols=EUR";
    private static final String SYMBOLS = "symbols";
    private final String apiUrl = "https://api.exchangerate.host/";

    @Autowired
    RestTemplate restTemplate;

    @Autowired(required = false)
    CurrencyUtil currencyUtil;

    @Autowired(required = false)
    ExchangeUtil exchangeUtil;

    @Autowired(required = false)
    RateUtil rateUtil;

    @GetMapping("/currencies")
    public ResponseEntity<ArrayList<CurrencyConversionRates>> getCurrencies() throws JsonProcessingException {
        ArrayList<CurrencyConversionRates> currencyConversionRates = new ArrayList<CurrencyConversionRates>();
        currencyConversionRates.add(new CurrencyConversionRates(2.5));
        return new ResponseEntity<ArrayList<CurrencyConversionRates>>(currencyConversionRates, HttpStatus.OK);
    }

    @GetMapping("/availableCurrencies")
    public ResponseEntity<List<Currency>> getAllCurrencies() throws FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        return new ResponseEntity<List<Currency>>(currencyUtil.listCurrencies(restTemplate.exchange(apiUrl + SYMBOLS, HttpMethod.GET, entity, String.class).getBody()), HttpStatus.OK);
    }

    @GetMapping("/exchange/{date}")
    public ResponseEntity<CurrencyExchange> getExchangesRates(@PathVariable String date) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        String url = apiUrl + date;
        return new ResponseEntity<CurrencyExchange>(exchangeUtil.listExchangesRates(restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody()), HttpStatus.OK);
    }

    @GetMapping("/exchange")
    public ResponseEntity<Exchange> getAllExchangesRates() throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        String url = apiUrl + TIME;
        return new ResponseEntity<Exchange>(exchangeUtil.lisAllExchangesRates(restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody()), HttpStatus.OK);
    }

    @GetMapping("/exchange/{currency}/{date}")
    public String getExchangesRate(@PathVariable String currency, @PathVariable String date) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        String url = apiUrl + date + BASE + currency + SYMBOLS_EUR;
        return rateUtil.getExchangesRate(restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody(), currency);
    }

}
