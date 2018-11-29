package io.aidantaylor.towerdefense.main;

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
