package com.paytmlabs.demo.model;


public class QueueAggregate {

	private Double lastNElement;
	private Double movingAverage;
	private Integer period;
	private String set;
	private String stream;
	
	public QueueAggregate(Double lastNElement, Double movingAverage, Integer period, String set, String stream) {
		this.lastNElement = lastNElement;
		this.movingAverage =  movingAverage;
		this.period =  period;
		this.set = set;
		this.stream = stream;
	}

	public Double getLastNElement() {
		return lastNElement;
	}

	public void setLastNElement(Double lastNElement) {
		this.lastNElement = lastNElement;
	}

	public Double getMovingAverage() {
		return movingAverage;
	}

	public void setMovingAverage(Double movingAverage) {
		this.movingAverage = movingAverage;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public String getSet() {
		return set;
	}

	public void setSet(String set) {
		this.set = set;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	

	
	
}
