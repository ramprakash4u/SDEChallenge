package com.paytmlabs.demo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Validated
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix="defaultStream")

public class InitialStream {
	
	private Integer defaultQueueSize;
	private List<Double> input = new ArrayList<Double> ();

	public Integer getDefaultQueueSize() {
		return defaultQueueSize;
	}

	public void setDefaultQueueSize(Integer defaultQueueSize) {
		this.defaultQueueSize = defaultQueueSize;
	}

	public List<Double> getInput() {
		return input;
	}

	public void setInput(List<Double> input) {
		this.input = input;
	}
    
}
