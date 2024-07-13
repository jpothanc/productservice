package com.ib.it.productservice.repository;

import com.ib.it.productservice.models.Equity;
import com.ib.it.productservice.models.QueryResponse;
import io.reactivex.rxjava3.core.Observable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.ib.it.productservice.utils.Constants.DATE_FORMATTER;


@Repository
public class EquityDbRepositoryImpl implements EquityRepository{

    @Autowired
    EquityJpaRepository equityRepository;
    @Override
    public QueryResponse findAll() {

        return new QueryResponse.Builder<Equity>()
                .setData(equityRepository.findAll())
                .setSource("db")
                .setTimeStamp(LocalDateTime.now().format(DATE_FORMATTER))
                .build();
    }

    @Override
    public QueryResponse findById(String productCode) {
        return new QueryResponse.Builder<Equity>()
                .setData(equityRepository.findById(productCode).stream().toList())
                .setSource("db")
                .setTimeStamp(LocalDateTime.now().format(DATE_FORMATTER))
                .build();
    }
}
