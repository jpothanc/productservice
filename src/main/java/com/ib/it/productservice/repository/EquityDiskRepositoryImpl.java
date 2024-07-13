package com.ib.it.productservice.repository;

import com.ib.it.productservice.models.Equity;
import com.ib.it.productservice.models.QueryResponse;
import io.reactivex.rxjava3.core.Observable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EquityDiskRepositoryImpl implements EquityRepository{
    @Override
    public QueryResponse findAll() {

        List<Equity> equities = List.of(
                new Equity(){{
                    setProductCode("EQ1");
                }});
        return new QueryResponse.Builder<Equity>()
                .setData(equities)
                .build();


    }
}
