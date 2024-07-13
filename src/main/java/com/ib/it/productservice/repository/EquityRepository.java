package com.ib.it.productservice.repository;

import com.ib.it.productservice.models.Equity;
import com.ib.it.productservice.models.QueryResponse;
import io.reactivex.rxjava3.core.Observable;

import java.util.List;

public interface EquityRepository {
    QueryResponse findAll();
    QueryResponse findById(String productCode);
}
