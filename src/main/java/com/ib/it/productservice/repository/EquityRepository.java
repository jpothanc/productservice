package com.ib.it.productservice.repository;


import com.ib.it.productservice.models.Equity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquityRepository extends JpaRepository<Equity, Long> {
}
