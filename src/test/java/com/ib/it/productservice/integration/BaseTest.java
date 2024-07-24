package com.ib.it.productservice.integration;

import com.ib.it.productservice.models.Equity;
import com.ib.it.productservice.models.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
//@Testcontainers
public abstract class BaseTest {
    @Autowired
    protected WebTestClient webTestClient;

    public ParameterizedTypeReference<QueryResponse<Equity>> getResponseType() {
        //ensures that the WebTestClient correctly deserializes the response into QueryResponse with a
        // nested list of Equity objects.
        return new ParameterizedTypeReference<QueryResponse<Equity>>() {
        };
    }

    public boolean validateEquity(Equity eq) {
        return !(eq.getProductCode().isEmpty() &&
                eq.getStockName().isEmpty() &&
                eq.getPrimaryExchange().isEmpty() &&
                eq.getCurrency().isEmpty());

    }

    public static class Constants {
        public static final String API_GET_ALL_PRODUCTS = "/api/v1/equity/products";
        public static final String API_GET_PRODUCT = "/api/v1/equity/product";
    }

}
