package com.andrei.microservices.limitsservice.controller;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andrei.microservices.limitsservice.bean.LimitConfiguration;
import com.andrei.microservices.limitsservice.config.Configuration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RefreshScope
@RestController
public class LimitsConfigurationController {

	private Configuration configuration;

	public LimitsConfigurationController(Configuration configuration) {
		this.configuration = configuration;
	}

	@GetMapping("/limits")
	public LimitConfiguration retrieveLimitsFromConfiguration() {
		return new LimitConfiguration(configuration.getMinimum(), configuration.getMaximum());
	}
	
	/**
	 * This is an example, of how a microservice works, if some of the resource is unavailable,
	 * if it is unavaliable, the fallbackRetrieveConfiguration method is called to assume some default value
	 * to allow other microservices that depends on this limit service to work normally.
	 * 
	 */
	@GetMapping("/fault-tolerance-sample")
	@HystrixCommand(fallbackMethod="fallbackRetrieveConfiguration")
	public LimitConfiguration retrieveConfiguration() {
		throw new RuntimeException("Limit Service is not avaliable, Please check it!!!");
	}
	
	public LimitConfiguration fallbackRetrieveConfiguration() {
		return new LimitConfiguration(9, 9999);
	}
}
