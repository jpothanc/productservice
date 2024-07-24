package com.ib.it.productservice.integration;

import com.ib.it.productservice.models.Equity;
import com.ib.it.productservice.models.QueryResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.stream.Stream;


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
    protected static Stream<Object[]> getNewEquity() {
        Equity eq = new Equity()
        {{
            setProductCode("1005.HK");
            setStockName("Test Holdings plc");
            setPrimaryExchange("HKG");
            setCurrency("HKD");
            setLotSize(1000);
            setDay20AverageVolume(1000000L);
            setDay30AverageVolume(1000000L);
            setLastClosePrice(BigDecimal.valueOf(100));
        }};

        return Stream.of(
                new Object[] { eq },
                new Object[] { eq }
        );
    }
    protected static Stream<Object[]> getEquity() {
        Equity eq = new Equity()
        {{
            setProductCode("0005.HK");
            setStockName("HSBC  Holdings plc");
            setPrimaryExchange("HKSE");
            setCurrency("HKD");
            setLotSize(400);
            setDay20AverageVolume(3000000L);
            setDay30AverageVolume(3500000L);
            setLastClosePrice(BigDecimal.valueOf(4257));
        }};

        return Stream.of(
                new Object[] { eq },
                new Object[] { eq }
        );
    }

    @NotNull
    protected static UriComponentsBuilder buildQueryParams(String api, Equity equity) {
        return UriComponentsBuilder.fromUriString(api)
                .queryParam("productCode", equity.getProductCode())
                .queryParam("stockName", equity.getStockName())
                .queryParam("primaryExchange", equity.getPrimaryExchange())
                .queryParam("currency", equity.getCurrency())
                .queryParam("lotSize", equity.getLotSize())
                .queryParam("day20AverageVolume", equity.getDay20AverageVolume())
                .queryParam("day30AverageVolume", equity.getDay30AverageVolume())
                .queryParam("lastClosePrice", equity.getLastClosePrice());

    }

    public static class Constants {
        public static final String API_GET_ALL_PRODUCTS = "/api/v1/equity/products";
        public static final String API_GET_PRODUCT = "/api/v1/equity/product";
        public static final String API_CREATE_PRODUCT = "/api/v1/equity/create";
        public static final String API_UPDATE_PRODUCT = "/api/v1/equity/update";
        public static final String API_DELETE_PRODUCT = "/api/v1/equity/delete";
    }

}
