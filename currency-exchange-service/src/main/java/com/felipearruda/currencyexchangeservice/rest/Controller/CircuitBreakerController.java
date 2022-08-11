package com.felipearruda.currencyexchangeservice.rest.Controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.core.Response;


@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")
    @Bulkhead(name ="sample-api")
    public String sampleApli() {
        logger.info("Sample api call received");
        return "sample-api";
    }

    public String hardcodedResponse(Exception ex){
        return "fallback-response";
    }

}
