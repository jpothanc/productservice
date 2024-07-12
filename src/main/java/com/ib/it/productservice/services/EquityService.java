package com.ib.it.productservice.services;

import com.ib.it.productservice.models.Equity;
import com.ib.it.productservice.repository.EquityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquityService {

    @Autowired
    private EquityRepository equityRepository;

    public List<Equity> getAllPersons() {
        return equityRepository.findAll();
    }

    public Equity getPersonById(Long id) {
        return equityRepository.findById(id).orElse(null);
    }

    public Equity savePerson(Equity equity) {
        return equityRepository.save(equity);
    }

    public void deletePerson(Long id) {
        equityRepository.deleteById(id);
    }
}