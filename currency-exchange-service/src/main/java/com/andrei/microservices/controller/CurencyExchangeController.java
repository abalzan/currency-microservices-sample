package com.andrei.microservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andrei.microservices.model.Exchange;
import com.andrei.microservices.repository.ExchangeRepository;

@RestController
@RequestMapping("/currency-exchange")
public class CurencyExchangeController {

	@Autowired
	private Environment environment;

	@Autowired
	private ExchangeRepository exchangeRepository;

	@GetMapping("/from/{from}/to/{to}")
	public Exchange retrieveExchange(@PathVariable String from, @PathVariable String to) {

		Exchange exchange = exchangeRepository.findByFromAndTo(from, to);
		exchange.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		
		return exchange;
	}
}
