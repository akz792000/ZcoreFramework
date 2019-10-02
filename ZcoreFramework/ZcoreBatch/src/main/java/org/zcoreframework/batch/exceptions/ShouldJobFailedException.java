package org.zcoreframework.batch.exceptions;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author pooya
 *         date : 4/12/16
 */
public class ShouldJobFailedException extends RuntimeException{

    private final Map<String,Object> properties = new TreeMap<String,Object>();

    public ShouldJobFailedException() {
    }

    public ShouldJobFailedException(String message) {
        super(message);
    }


    public ShouldJobFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShouldJobFailedException(Throwable cause) {
        super(cause);
    }

    public ShouldJobFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


    @SuppressWarnings("unchecked")
    public <T> T get(String name) {
        return (T)properties.get(name);
    }

    public ShouldJobFailedException set(String name, Object value) {
        properties.put(name, value);
        return this;
    }


}
