package com.paytmlabs.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.paytmlabs.demo.model.APIResponse;
import com.paytmlabs.demo.service.MovingAverageService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/v1")

public class DemoController {
	
	private static final Logger LOG = LoggerFactory.getLogger(DemoController.class);
	
	@Autowired
	MovingAverageService movingAverageService;
	
	/*
	 * Please refer to Swagger provided API documentation for request and response payload specification
	 * 
	 * This is main endpoint for controller to calculate moving average of last N element from stream.
	 * @API endpoint @pathParameter- {nthElement} - accepts duration/period to calculate moving average
	 * @API endpoint @requestParameter-stream - accepts stream of element to calculate moving average
	 * @return response payload having following 
	 *    -: movingAverage- Moving average after adding each element from stream
	 *    -: lastNElement - last element added to queue/buffer from stream
	 *    -: period - duration/window of last N element for which moving average if calculated
	 *    -: set - current queue list of elements of size equal to period
	 *    -: stream- entire/full length of stream after adding element from input stream 
	 */
	
	@GetMapping(value = "/get/stream/moving-average/last/{nthElement}")
	@ApiOperation(value = "Retrieve moving-average of the last N elements added",
	              consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<APIResponse> getMovingAverage(@PathVariable("nthElement") Integer nthElementSet, 
			@RequestParam(value = "stream",required = true) List<Double> stream
	             ) {
		
		APIResponse response = movingAverageService.findMovingAverage(stream, nthElementSet);
		
		return new ResponseEntity<> (response,HttpStatus.OK);
	}
	
	/*
	 * This endpoint is to check health of application, incase of application is up and running it returns "Health Ok" and 200 http response code
	 * Please refer to Swagger provided API documentation for request and response payload specification
	 */
	
	@GetMapping(value = "/ApplicationHealth")
	@ApiOperation(value = "perform application health check", 
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	
	@ResponseBody
	public ResponseEntity<String> healthStatus() {
		
	       return new ResponseEntity<String> ("Health ok", HttpStatus.OK);
	}

}
