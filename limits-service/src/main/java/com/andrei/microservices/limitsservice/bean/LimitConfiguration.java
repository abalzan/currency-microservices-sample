package com.andrei.microservices.limitsservice.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LimitConfiguration {

	private int minimum;
	private int maximum;
}
