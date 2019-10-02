package org.zcoreframework.batch.reader;

import java.util.List;

import static org.springframework.util.CollectionUtils.isEmpty;

/**
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 *         Date: 4/22/17
 */

public abstract class DefaultItemReader<E, T> extends AbstractItemReader<T> {

    private final Integer readInterval;

    private ThreadLocal<List<E>> items = new ThreadLocal<>();

    /**
     * @param readInterval If this argument is passed as not null, your query must be ordered.
     */
    public DefaultItemReader(Integer readInterval) {
        this.readInterval = readInterval;
    }

    @Override
    protected final T doRead() throws Exception {
        List<E> threadItems = items.get();
        if (isEmpty(threadItems)) {
            threadItems = doRead(readInterval);
            if (isEmpty(threadItems)) return null;
            items.set(threadItems);
        }

        return convert(threadItems.remove(0));
    }

    /**
     * <B>Note: </B>If readInterval is not null, your query must be ordered.
     */
    protected abstract List<E> doRead(Integer loadLimit) throws Exception;

    /**
     * If E and T is not as same type, you must override this method.
     */
    protected T convert(E item) throws Exception {
        return (T) item;
    }

    @Override
    protected final void logError(Throwable e) throws Exception {
        logError(readInterval, e);
    }

    /**
     * If you want to write your business log for any exception that occurred in reader, you can override this method.
     */
    protected void logError(Integer readInterval, Throwable e) throws Exception {
        // There is not any default implementation.
    }
}
