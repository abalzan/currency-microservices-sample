package com.andrei.microservices.limitsservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andrei.microservices.limitsservice.bean.LimitConfiguration;
import com.andrei.microservices.limitsservice.config.Configuration;

@RestController
@RequestMapping("/limits")
public class LimitsConfigurationController {

	private Configuration configuration;

	public LimitsConfigurationController(Configuration configuration) {
		this.configuration = configuration;
	}



	@GetMapping
	public LimitConfiguration retrieveLimitsFromConfiguration() {
		return new LimitConfiguration(configuration.getMinimum(), configuration.getMaximum());
	}
}
