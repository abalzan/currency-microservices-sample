package com.andrei.microservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andrei.microservices.model.Exchange;

public interface ExchangeRepository extends JpaRepository<Exchange, Long>{

	Exchange findByFromAndTo(String from, String to);
}
