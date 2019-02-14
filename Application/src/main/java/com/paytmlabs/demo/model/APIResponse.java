package com.paytmlabs.demo.model;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.paytmlabs.demo.enums.ResponseStatus;

import io.swagger.annotations.ApiModelProperty;

@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIResponse {
	
	@JsonProperty("status")
	@ApiModelProperty(example="SUCCESS", position=1, value="Response SUCCESS")
	private String status = ResponseStatus.SUCCESS.getStatus();
	
	@JsonProperty("responseTimestamp")
	@ApiModelProperty(example="2019-10-28T15:01:35", position=2, value="Transaction Timestamp")
	private String responseTimestamp;
	
	@JsonProperty("correlationId")
	@ApiModelProperty(example="5b20ec7eacbcd18e", position=3, value="correlationId")
	private UUID correlationId;
	
	@JsonProperty("result")
	@ApiModelProperty(example = "Moving Average Result", position=4, value="correlationId")
	private List<QueueAggregate> results;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResponseTimestamp() {
		return responseTimestamp;
	}

	public void setResponseTimestamp(String responseTimestamp) {
		this.responseTimestamp = responseTimestamp;
	}

	public UUID getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(UUID uuid) {
		this.correlationId = uuid;
	}


	public List<QueueAggregate> getResults() {
		return results;
	}

	public void setResults(List<QueueAggregate> results) {
		this.results = results;
	}


	

}
