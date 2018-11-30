package io.aidantaylor.towerdefense.main;

/**
 * 
 * @author Aidan Taylor
 * @date 11/29/2018
 * @description Class that contains 1. a lambda function, 2. the time at which the object was created, and 3. how long after object creation the lambda should be executed.
 */

public class CallbackObject {

	private Callback callback;
	private long waitTime;
	private long creationTime;
	
	public CallbackObject(Callback c, long wait) { // take wait in milliseconds, waitTime becomes nanoseconds
		callback = c;
		waitTime = wait*1000000;
		creationTime = System.nanoTime();
	}
	
	public Callback getCallback() {
		return callback;
	}
	
	public long getWaitTime() {
		return waitTime;
	}
	
	public long getCreationTime() {
		return creationTime;
	}
	
}
