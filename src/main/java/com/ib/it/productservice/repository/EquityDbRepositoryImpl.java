package com.ib.it.productservice.repository;

import com.ib.it.productservice.models.Equity;
import com.ib.it.productservice.models.QueryResponse;
import io.reactivex.rxjava3.core.Observable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class EquityDbRepositoryImpl implements EquityRepository{

    @Autowired
    EquityJpaRepository equityRepository;
    @Override
    public QueryResponse findAll() {

        return new QueryResponse.Builder<Equity>()
                .setData(equityRepository.findAll())
                .build();
    }
}
