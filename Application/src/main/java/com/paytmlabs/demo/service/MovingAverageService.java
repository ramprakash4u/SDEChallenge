package com.paytmlabs.demo.service;

import java.util.List;

import com.paytmlabs.demo.model.APIResponse;

public interface MovingAverageService {
	
	public APIResponse findMovingAverage(List<Double> stream, Integer streamTailSet); 

}
