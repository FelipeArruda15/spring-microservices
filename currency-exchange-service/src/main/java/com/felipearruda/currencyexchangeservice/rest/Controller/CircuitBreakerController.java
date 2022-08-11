package com.felipearruda.currencyexchangeservice.rest.Controller;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")
    @RateLimiter(name="default")
    public String sampleApli() {
        logger.info("Sample api call received");
        return "sample-api";
    }

    public String hardcodedResponse(Exception ex){
        return "fallback-response";
    }

}
