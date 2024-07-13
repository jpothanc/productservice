package com.ib.it.productservice.services;

import com.ib.it.productservice.config.CustomQualifier;
import com.ib.it.productservice.models.Equity;
import com.ib.it.productservice.models.QueryResponse;
import com.ib.it.productservice.repository.EquityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.concurrent.CompletableFuture;

@Service
public class EquityService {
    private final EquityRepository equityRepository;

    @Autowired
    public EquityService(@CustomQualifier("EquityRepository") EquityRepository equityRepository) {
        this.equityRepository = equityRepository;
    }

    public CompletableFuture<QueryResponse> getAll() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                var result =  equityRepository.findAll();
                if(result.getData() == null || result.getData().isEmpty()) {
                    throw new NoSuchElementException("No data found");
                }
                return result;
            } catch (Exception e) {
                throw new RuntimeException("Failed to fetch equities", e);
            }
        });
    }

    public CompletableFuture<QueryResponse> getEquity(String productCode) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                var result =  equityRepository.findById(productCode);
                if(result.getData() == null || result.getData().isEmpty()) {
                    throw new NoSuchElementException("No data found");
                }
                return result;
            } catch (Exception e) {
                throw new RuntimeException("Failed to fetch equities", e);
            }
        });
    }
}