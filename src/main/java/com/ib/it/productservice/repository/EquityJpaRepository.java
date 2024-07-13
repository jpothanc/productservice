package com.ib.it.productservice.repository;


import com.ib.it.productservice.models.Equity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquityJpaRepository extends JpaRepository<Equity, String> {
}
