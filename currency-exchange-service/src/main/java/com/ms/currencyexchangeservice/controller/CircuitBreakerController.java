package com.ms.currencyexchangeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import reactor.core.publisher.Mono;

@RestController
public class CircuitBreakerController {
	
	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	@GetMapping("/sample-api")
	@CircuitBreaker(name = "default", fallbackMethod = "fallback")
	public String sampleApi() {
		logger.info("Sample api call received");
		
		ResponseEntity<String> responseEntity = new RestTemplate().getForEntity("http://localhost:8080/dummy-api", String.class);
		return responseEntity.getBody();
//		return "Sample API !!";
	}
	private String fallback(RuntimeException e) {
	    return "test fallback";
	}
}
