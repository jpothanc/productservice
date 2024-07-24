package com.ib.it.productservice.unit;

import com.ib.it.productservice.models.Equity;
import com.ib.it.productservice.models.QueryResponse;
import com.ib.it.productservice.repository.EquityDiskRepositoryImpl;
import com.ib.it.productservice.services.EquityService;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class EquityServiceTest {
    @Mock
    private EquityDiskRepositoryImpl equityDiskRepository;

    @InjectMocks
    private EquityService equityService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        // Mock repository behavior
        QueryResponse<Equity> mockProducts =
                new QueryResponse.Builder<Equity>()
                        .setData(List.of(
                                new Equity() {{
                                    setStockName("Product 1");
                                }}))
                        .setSource("db")
                        .setTimeStamp("2021-09-01T12:00:00")
                        .build();


        given(equityDiskRepository.findAll()).willReturn(mockProducts);

        // When
        QueryResponse response = null;
        try {
            response = equityService.getAll().get();
        } catch (InterruptedException  | ExecutionException e) {
            throw new RuntimeException(e);
        }


        // Then
        then(equityDiskRepository).should(times(1)).findAll();
        assertEquals(1, response.getRecords());
        List<Equity> equities = response.getData();
        assertEquals("Product 1", equities.get(0).getStockName());

    }

}
