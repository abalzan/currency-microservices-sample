package com.andrei.microservices.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.andrei.microservices.model.Exchange;
import com.andrei.microservices.repository.ExchangeRepository;

@Component
public class Bootstrap implements CommandLineRunner {

	@Autowired
	private ExchangeRepository exchangeRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		exchangeRepository.deleteAll();
		
		List<Exchange> exchanges = new ArrayList<>();
		exchanges.add(new Exchange(1L, "USD", "BLR", BigDecimal.valueOf(3)));
		exchanges.add(new Exchange(2L, "USD", "INR", BigDecimal.valueOf(65)));
		exchanges.add(new Exchange(3L, "EUR", "BLR", BigDecimal.valueOf(4)));
		exchanges.add(new Exchange(4L, "AUD", "BLR", BigDecimal.valueOf(1)));
		
		exchangeRepository.saveAll(exchanges);
		
	}

}
