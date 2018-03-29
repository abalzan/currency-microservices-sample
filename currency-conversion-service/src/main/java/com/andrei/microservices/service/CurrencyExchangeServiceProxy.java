package com.andrei.microservices.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.andrei.microservices.model.CurrencyConvertion;

//you fill the name parameter with the application name present on application.properties
//this is the name of the microservice you wanna call, in this case "currency-exchange-service"

//you fill the url parameter, this I don't get yet, 
//because if I have more than one microservice what is gonna happen?
@FeignClient(name = "currency-exchange-service", url = "localhost:8000")
public interface CurrencyExchangeServiceProxy {

	// Here we need to define a method to talk with currency Exchange Service
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConvertion retrieveExchange(@PathVariable("from") String from, @PathVariable("to") String to);
}
