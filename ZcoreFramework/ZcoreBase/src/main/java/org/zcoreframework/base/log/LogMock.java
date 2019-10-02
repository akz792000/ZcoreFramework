package org.zcoreframework.base.log;

import org.springframework.util.StopWatch;

public class LogMock {

	public enum Type {
		SUCCESS, ERROR, EXCEPTION
	};

	private Type type = Type.SUCCESS;

	private StopWatch stopWatch = new StopWatch();

	private Object object;
	
	private String result;

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public StopWatch getStopWatch() {
		return stopWatch;
	}

	public void setStopWatch(StopWatch stopWatch) {
		this.stopWatch = stopWatch;
	}

	public String getResult() {
		return result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}

	public Object getObject() {
		return object;
	}
	
	public void setObject(Object object) {
		this.object = object;
	}
	
	public LogMock() {
		stopWatch.start();
	}

    public LogMock(Object object) {
        stopWatch.start();
        this.object = object;
    }

}
