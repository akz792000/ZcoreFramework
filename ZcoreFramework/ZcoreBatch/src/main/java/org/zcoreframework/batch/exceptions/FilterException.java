package org.zcoreframework.batch.exceptions;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by z.azadi on 5/1/2016.
 */
public class FilterException extends RuntimeException {

    private final Map<String, Object> properties = new TreeMap<String, Object>();

    public FilterException() {
    }

    public FilterException(String message) {
        super(message);
    }

    public FilterException(String message, Throwable cause) {
        super(message, cause);
    }

    public FilterException(Throwable cause) {
        super(cause);
    }

    public FilterException(String message,
                           Throwable cause,
                           boolean enableSuppression,
                           boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String name) {
        return (T) properties.get(name);
    }

    public FilterException set(String name, Object value) {
        properties.put(name, value);
        return this;
    }
}
