package com.ib.it.productservice.controllers;

import com.ib.it.productservice.models.QueryResponse;
import com.ib.it.productservice.services.EquityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/equity")
@Tag(name = "EquityController", description = "Equity Product  API")
public class EquityController {

    @Autowired
    private EquityService equityService;

    @Operation(summary = "Get All Equity Products", description = "Get All Equity Products")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @GetMapping("/products")
    public Mono<ResponseEntity<QueryResponse>> getAll() {

        return Mono.fromFuture(() -> equityService.getAll())
                .map(ResponseEntity::ok)
                .onErrorResume(NoSuchElementException.class, e ->
                        Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build())
                ).onErrorResume(Exception.class, e ->
                        Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));

    }

    @Operation(summary = "Get Equity Product by Product Code", description = "Get Equity Product by Product Code")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @GetMapping("/{product}")
    public Mono<ResponseEntity<QueryResponse>> getEquity(@RequestParam  String code) {
        return Mono.fromFuture(() -> equityService.getEquity(code))
                .map(ResponseEntity::ok)
                .onErrorResume(NoSuchElementException.class, e ->
                        Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build())
                ).onErrorResume(Exception.class, e ->
                        Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
    }
}
