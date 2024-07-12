package com.ib.it.productservice.controllers;

import com.ib.it.productservice.models.Equity;
import com.ib.it.productservice.services.EquityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/equity")
@Tag(name = "EquityController", description = "Equity Product  API")
public class EquityController {

    @Autowired
    private EquityService equityService;

    @Operation(summary = "Get All Equity Products", description = "Get All Equity Products")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @GetMapping("/")
    public List<Equity> getAllPersons() {
        return equityService.getAllPersons();
    }

}
