package com.ib.it.productservice.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ib.it.productservice.models.Equity;
import com.ib.it.productservice.models.QueryResponse;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.ib.it.productservice.utils.Constants.DATE_FORMATTER;

@Repository
public class EquityDiskRepositoryImpl implements EquityRepository {
    private final ResourceLoader resourceLoader;
    private final Environment environment;

    private Map<String,Equity> cachedEquities;


    public EquityDiskRepositoryImpl(ResourceLoader resourceLoader, Environment environment) {
        this.resourceLoader = resourceLoader;
        this.environment = environment;
        System.out.println("EquityDiskRepositoryImpl created");
        if (cachedEquities == null || cachedEquities.isEmpty())
            loadEquities();
    }

    @Override
    public QueryResponse findAll() {
        var v = cachedEquities.values().stream().toList();
        return new QueryResponse.Builder<Equity>()
                .setData(cachedEquities.values().stream().toList())
                .setSource("disk")
                .setTimeStamp(LocalDateTime.now().format(DATE_FORMATTER))
                .build();
    }

    @Override
    public QueryResponse findById(String productCode) {
        return new QueryResponse.Builder<Equity>()
                .setData(cachedEquities.containsKey(productCode) ? List.of(cachedEquities.get(productCode)) : List.of())
                .setSource("disk")
                .setTimeStamp(LocalDateTime.now().format(DATE_FORMATTER))
                .build();
    }

    @Override
    public QueryResponse save(Equity equity) {
        cachedEquities.remove(equity.getProductCode());
        cachedEquities.put(equity.getProductCode(),equity);
       return new QueryResponse.Builder<Equity>()
               .setData(List.of(equity))
               .setSource("disk")
               .setTimeStamp(LocalDateTime.now().format(DATE_FORMATTER))
               .build();
    }

    @Override
    public QueryResponse deleteById(String productCode) {
        cachedEquities.remove(productCode);
        return new QueryResponse.Builder<Equity>()
                .setData(null)
                .setSource("db")
                .setTimeStamp(LocalDateTime.now().format(DATE_FORMATTER))
                .build();
    }

    @Override
    public QueryResponse updateById(String productCode, Equity equity) {
        cachedEquities.remove(productCode);
        cachedEquities.put(productCode,equity);
        return new QueryResponse.Builder<Equity>()
                .setData(List.of(equity))
                .setSource("disk")
                .setTimeStamp(LocalDateTime.now().format(DATE_FORMATTER))
                .build();
    }

    private void loadEquities() {
        System.out.println("Loading equities from disk");
        String equitiesDiskFile = environment.getProperty("app.equities-disk-file");
        ObjectMapper objectMapper = new ObjectMapper();
        List<Equity> equities = null;
        try {
            String dataFile = "classpath:" + equitiesDiskFile;
            Resource resource = resourceLoader.getResource(dataFile);
            equities= objectMapper.readValue(resource.getInputStream(),
                    objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Equity.class));
            cachedEquities = equities.stream().collect(Collectors.toMap(Equity::getProductCode, equity -> equity));

        } catch (IOException e) {
            System.out.println("Failed to read equities from disk");
        }
    }
}
