package com.ib.it.productservice.controllers;

import com.ib.it.productservice.models.Equity;
import com.ib.it.productservice.models.QueryResponse;
import com.ib.it.productservice.services.EquityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/equity")
@Tag(name = "EquityController", description = "Equity Product  API")
public class EquityController {

    @Autowired
    private EquityService equityService;

    @Operation(summary = "Get All Equity Products", description = "Get All Equity Products")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @ApiResponse(responseCode = "404", description = "No data found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @GetMapping("/products")
    public Mono<ResponseEntity<QueryResponse>> getAll() {

        return Mono.fromFuture(() -> equityService.getAll())
                .map(ResponseEntity::ok);

    }

    @Operation(summary = "Get Equity Product by Product Code", description = "Get Equity Product by Product Code")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @ApiResponse(responseCode = "404", description = "No data found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @GetMapping("/{product}")
    public Mono<ResponseEntity<QueryResponse>> getEquity(@RequestParam String productCode) {
        return Mono.fromFuture(() -> equityService.getEquity(productCode))
                .map(ResponseEntity::ok);

    }

    @Operation(summary = "Save Equity Product", description = "Save Equity Product")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @PostMapping("/create")
    public Mono<ResponseEntity<QueryResponse>> createEquity(@ModelAttribute Equity equity) {
        return Mono.fromFuture(() -> equityService.saveEquity(equity))
                .map(ResponseEntity::ok);

    }

    @Operation(summary = "Update Equity Product", description = "Update Equity Product")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @PutMapping("/update")
    public Mono<ResponseEntity<QueryResponse>> updateEquity(@RequestParam String code, @ModelAttribute Equity equity) {
        return Mono.fromFuture(() -> equityService.updateEquity(code, equity))
                .map(ResponseEntity::ok);

    }

    @Operation(summary = "Delete Equity Product", description = "Delete Equity Product")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @DeleteMapping("/delete")
    public Mono<ResponseEntity<QueryResponse>> deleteEquity(@RequestParam String code) {
        return Mono.fromFuture(() -> equityService.deleteEquity(code))
                .map(ResponseEntity::ok);

    }

}
