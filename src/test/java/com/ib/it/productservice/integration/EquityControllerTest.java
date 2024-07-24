package com.ib.it.productservice.integration;

import com.ib.it.productservice.models.Equity;
import com.ib.it.productservice.models.QueryResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static com.ib.it.productservice.integration.BaseTest.Constants.API_GET_PRODUCT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;


public class EquityControllerTest extends BaseTest {

    @Test
    public void whenGetAllProducts_thenReturnsAllProducts() {

        webTestClient.get()
                .uri(Constants.API_GET_ALL_PRODUCTS)
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody(getResponseType())
                .consumeWith(response -> {
                    QueryResponse responseBody = response.getResponseBody();
                    assertEquals("disk", responseBody.getSource());
                    assertTrue(responseBody.getRecords() > 0);
                    List <Equity> equities =  responseBody.getData();
                    assertTrue(validateEquity(equities.get(0)));
                });
    }
    @ParameterizedTest
    @ValueSource(strings = { "0005.HK", "6758.T"})
    public void whenGetProductByCode_thenReturnsProduct(String productCode) {

        String url = UriComponentsBuilder.fromUriString(API_GET_PRODUCT)
                .queryParam("productCode", productCode)
                .toUriString();

        webTestClient.get()
                .uri(url)
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody(getResponseType())
                .consumeWith(response -> {
                    QueryResponse responseBody = response.getResponseBody();
                    assertEquals("disk", responseBody.getSource());
                    assertTrue(responseBody.getRecords() > 0);
                    List <Equity> equities =  responseBody.getData();
                    assertTrue(validateEquity(equities.get(0)));
                });
    }

    @ParameterizedTest
    @ValueSource(strings = { "xxxx.HK", "xxxx.T"})
    public void whenInvalidGetProductByCode_thenReturnsNotFound(String productCode) {
        String url = UriComponentsBuilder.fromUriString(API_GET_PRODUCT)
                .queryParam("productCode", productCode)
                .toUriString();

        webTestClient.get()
                .uri(url)
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();
    }
}
