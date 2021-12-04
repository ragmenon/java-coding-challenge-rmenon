package com.crewmeister.cmcodingchallenge.api;

import com.crewmeister.cmcodingchallenge.controller.CurrencyController;
import com.crewmeister.cmcodingchallenge.util.CurrencyUtil;
import com.crewmeister.cmcodingchallenge.util.ExchangeUtil;
import com.crewmeister.cmcodingchallenge.util.RateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
class CmCodingChallengeApplicationTests {

    @Test
    void contextLoads() {
    }

    @WebMvcTest(CurrencyController.class)
    @ContextConfiguration
    public static class CurrencyControllerTest {
        @Autowired
        private MockMvc mvc;

        @MockBean
        private CurrencyUtil currencyUtil;

        @MockBean
        private ExchangeUtil exchangeUtil;

        @MockBean
        private RateUtil rateUtil;

        @Test
        void contextLoads() {
        }

        @Test
        @WithMockUser
        public void testGetCurrencies() throws Exception {

            this.mvc
                    .perform(MockMvcRequestBuilders.get("/api/currencies"))
                    .andDo(print())
                    .andExpect(content().string("[{\"conversionRate\":2.5}]"))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        @WithMockUser
        public void testGetAllCurrencies() throws Exception {
            this.mvc
                    .perform(MockMvcRequestBuilders.get("/api/availableCurrencies"))
                    .andDo(print())
                    .andExpect(content().string("[{\"conversionRate\":2.5}]"))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        @WithMockUser
        public void testGetExchangesRates() throws Exception {
            this.mvc
                    .perform(MockMvcRequestBuilders.get("/api/exchange/2021-11-28"))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        @WithMockUser
        public void testGetAllExchangesRates() throws Exception {
            this.mvc
                    .perform(MockMvcRequestBuilders.get("/api/exchange/HKD/2018-01-01"))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        @WithMockUser
        public void testGetExchangesRate() throws Exception {
            this.mvc
                    .perform(MockMvcRequestBuilders.get("/api/exchange"))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        }


    }
}
