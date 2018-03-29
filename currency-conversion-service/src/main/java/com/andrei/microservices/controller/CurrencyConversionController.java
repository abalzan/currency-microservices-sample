package com.andrei.microservices.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.andrei.microservices.model.CurrencyConvertion;
import com.andrei.microservices.service.CurrencyExchangeServiceProxy;

@RestController
//@RequestMapping("/currency-converter")
public class CurrencyConversionController {
	
	@Autowired
	private CurrencyExchangeServiceProxy currencyExchangeServiceProxy;

	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConvertion convertCurrency(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		//Feign helps solve this problem because it is very dificult to maintain some things like that
		//===========================OLD CODE BEFORE FEIGN ====================================
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		ResponseEntity<CurrencyConvertion> responseEntity = new RestTemplate().getForEntity(
					"http://localhost:8000/currency-exchange/from/{from}/to/{to}", 
					CurrencyConvertion.class, uriVariables);

		CurrencyConvertion response = responseEntity.getBody();
		//===========================OLD CODE ====================================
		
		return new CurrencyConvertion(response.getId(), from, to, 
					response.getConversionMultiple(), quantity,
					quantity.multiply(response.getConversionMultiple()),
					response.getPort());
	}

	//Feign is a rest service client
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConvertion convertCurrencyFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		CurrencyConvertion response = currencyExchangeServiceProxy.retrieveExchange(from, to);
		
		return new CurrencyConvertion(response.getId(), from, to, 
					response.getConversionMultiple(), quantity,
					quantity.multiply(response.getConversionMultiple()),
					response.getPort());
	}
	
	
}
