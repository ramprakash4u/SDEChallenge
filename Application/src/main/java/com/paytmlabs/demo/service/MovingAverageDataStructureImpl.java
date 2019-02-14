package com.paytmlabs.demo.service;

import java.util.NoSuchElementException;
import com.paytmlabs.demo.model.QueueNode;

/*
 * This class is implementation of interface "MovingAverageDataStructureIE" to calculate moving average of last N element.
 * it calculates moving average with time complexity-> O(1) by keeping track of four things head & tail of queue , Size of current queue/buffer
 * and queueTotal as sum of all elements from current queue/buffer. 
 */

class MovingAverageDataStructureImpl<T> implements MovingAverageDataStructureIE {

	private QueueNode<Double> first;
	private QueueNode<Double> last;
	public Integer size = 0;
	public Double queueTotal = 0.0 ;

	/*
	 *  Calculates moving average with time complexity-> O(1)
	 * 	* @see com.paytmlabs.demo.service.MovingAverageDataStructureIE#movingAvgOfLastNElement(java.lang.Double, java.lang.Integer)
	 *  @param {java.lang.Double}  new input element from stream
	 *  @param {java.lang.Integer} size/period of last N element to calculate moving average
	 *  @return {java.lang.Double} moving average of last N element
	 *  
	 *  This method calculates and returns moving average of last N element from stream
	 *     -:  Accepts new element to be added in queue from stream of input data
	 *     -:  Accepts maxSize of queue as duration/period for which moving average needs to be calculated
	 *     -:  Maitains the first(head) element and last(tail) element of queue
	 *     -:  Removes first element entered in FIFO queue buffer, once buffer is full based on input maxSize and decrement queueTotal & queue size
	 *     -:  Maintains the size of queue buffer equal to input maxSize
	 *     -:  Size -> Increment and calculates current total queue/buffer size upon addition of new element
	 *     -:  queueTotal -> Calculates Sum of all elements from current queue/buffer
	 *     -:  calculates moving average with time complexity-> O(1)
	 *     
	 */
	
	@Override
	public Double movingAvgOfLastNElement(Double element, Integer N) {

	    QueueNode<Double> data = new QueueNode<Double> (element);
	
	    if(size >= N) {
		    queueTotal -= removeQueue();
	    } 
	      
		if (last != null) {
			last.setNext(data);
		}

		last = data;

		if(first == null) {
			first = last;
		}
		
		size++;
		queueTotal += element;
	
	    return queueTotal/size;
	}

	
	/*
	 * * @see com.paytmlabs.demo.service.MovingAverageDataStructureIE#removeQueue()
	 * @return {java.lang.Double} removes and return first element from FIFO queue(entered first in queue)
	 * 
	 * This method removes element which entered in queue first in FIFO order
	 */
	@Override
	public Double removeQueue() {
		if(first == null) throw new NoSuchElementException();
		Double data = first.getData();
		first = first.getNext();

		if(first == null) {
			last = null;
		}
		
		size-- ;
		return data;
	}

	/*
	 * 	 * @see com.paytmlabs.demo.service.MovingAverageDataStructureIE#peek()
	 *  @return {java.lang.Double} return first element from FIFO queue(entered first in queue) but doesn't remove element from queue
	 */
	
	@Override
	public Double peek() {
		if (first == null) throw new NoSuchElementException();
		return first.getData();
	}

   /*
    * * @see com.paytmlabs.demo.service.MovingAverageDataStructureIE#toArray()
    * @return array of object that contains all current element for given instance of queue. This can be used for toArray() function to print 
    * current queue elements. 
    */
	
	@Override
	public Object[] toArray() {
		Object[] al = new Object[size];
		QueueNode<Double> head = first;
		for(int i=0; i<=size-1; i++) {
			al[i] = head.getData();
			head = head.getNext();
		}
	
        return al;
    }
}


