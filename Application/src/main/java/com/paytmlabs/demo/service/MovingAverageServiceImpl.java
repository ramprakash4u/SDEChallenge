package com.paytmlabs.demo.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.paytmlabs.demo.model.APIResponse;
import com.paytmlabs.demo.model.QueueAggregate;

@Service("movingAverageService")
public class MovingAverageServiceImpl implements MovingAverageService {

	private static final Logger LOG = LoggerFactory.getLogger(MovingAverageServiceImpl.class);
		
	@Override
	/*  This method accepts stream of data and last N element period/set for which moving average is calculated and then construct response
	 *  payload for API.
	 *  
	 *  @see com.paytmlabs.demo.service.MovingAverageService#findMovingAverage(java.util.List, java.lang.Integer)
	 *  @param {java.util.List}  accepts list of element stream of type Double on which moving average is calculated
	 *  @param {java.lang.Integer} accepts maxSize of last N element as streamTailset for which moving average is calculated
	 *  @return APIReponse object that contains response payload element for API
	 */
	public APIResponse findMovingAverage(List<Double> stream, Integer streamTailSet) {
		
		APIResponse response =  new APIResponse();	
		List<QueueAggregate> result =  addQueue(stream, streamTailSet);

		response.setResponseTimestamp(Instant.now().toString());
		response.setCorrelationId(UUID.randomUUID());
		response.setResults(result);
		return response;
	}
	
	/*  This method uses datastructure MovingAverageDataStructureIE implementation class to calculate moving average for stream N last element
	 *  @returns object QueueAggregate as result which contains all necessary element that goes in response payload including moving average of
	 *  last N element.
	 *  
	 */
	private List<QueueAggregate> addQueue(List<Double> stream, Integer maxQueueSize) {
		MovingAverageDataStructureIE queue = new MovingAverageDataStructureImpl();
		
		List<QueueAggregate> result = new ArrayList<QueueAggregate> ();
		LinkedList<Double> fullStream = new LinkedList<Double> ();
		LOG.info("Period :" +maxQueueSize);
		
		stream.forEach(element ->   {fullStream.add(element);
		                Double movingAvg = queue.movingAvgOfLastNElement(element, maxQueueSize);
		                LOG.info("Element added :" +element);    
		                LOG.info("Queue :" +Arrays.toString(queue.toArray()));
			            LOG.info("QueueAvg :" +movingAvg);
			            QueueAggregate queueAggregate =  new QueueAggregate(element, movingAvg, maxQueueSize, Arrays.toString(queue.toArray()), Arrays.toString(fullStream.toArray()));
			            result.add(queueAggregate);	
		});

		return result;	
	}

}
