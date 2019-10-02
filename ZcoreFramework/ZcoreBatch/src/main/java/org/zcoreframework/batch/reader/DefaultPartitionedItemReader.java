package org.zcoreframework.batch.reader;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.zcoreframework.batch.model.Partition;

import java.util.List;

import static java.lang.Long.valueOf;
import static java.lang.String.format;
import static java.util.Comparator.comparing;
import static java.util.Optional.ofNullable;
import static org.springframework.util.CollectionUtils.isEmpty;
import static org.zcoreframework.batch.partitioner.DefaultPartitioner.PARTITION_MODEL;

/**
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 *         Date: 4/22/17
 */

public abstract class DefaultPartitionedItemReader<E, T> extends AbstractItemReader<T> implements ItemStream {

    private static final Logger LOGGER = Logger.getLogger(DefaultPartitionedItemReader.class);

    private final Integer readInterval;

    private ThreadLocal<Partition> partition = new ThreadLocal<>();
    private ThreadLocal<Long> startValue = new ThreadLocal<>();
    private ThreadLocal<List<E>> items = new ThreadLocal<>();

    /**
     * @param readInterval If this argument is passed as null, your query can be unordered.
     */
    public DefaultPartitionedItemReader(Integer readInterval) {
        this.readInterval = readInterval;
    }

    @Override
    public final void open(ExecutionContext threadContext) throws ItemStreamException {
        final Partition threadPartition = (Partition) threadContext.get(PARTITION_MODEL);
        partition.set(threadPartition);
        startValue.set(threadPartition.getMinValue());
        readFromThreadContext(threadContext);

        LOGGER.debug(format("Running %s[partition:%s] ...", getStepExecution().getStepName(), threadPartition.toString()));
    }

    /**
     * If you want to read from thread context at initializing of reader, you can override this method.
     */
    protected void readFromThreadContext(ExecutionContext threadContext) {
        // There is not any default implementation.
    }

    @Override
    protected final T doRead() throws Exception {
        final Long threadMaxValue = partition.get().getMaxValue();
        final Long threadStartValue = startValue.get();
        List<E> threadItems = items.get();

        if (isEmpty(threadItems)) {
            final Integer loadLimit = calculateLoadLimit(threadStartValue, threadMaxValue);
            threadItems = sortedRead(threadStartValue, threadMaxValue, loadLimit);
            if (isEmpty(threadItems)) return null;
            threadItems.sort(comparing(this::getValue));
            items.set(threadItems);
            startValue.set(getValue(threadItems.get(threadItems.size() - 1)) + 1);
        }

        return convert(threadItems.remove(0));
    }

    protected abstract Long getValue(E item);

    /**
     * <B>Note: </B>If readInterval is not null, your query must be ordered according to 'value' field.
     */
    protected abstract List<E> sortedRead(Long inclusiveStartValue, Long inclusiveEndValue, Integer loadLimit) throws Exception;

    /**
     * If E and T is not as same type, you must override this method.
     */
    protected T convert(E item) throws Exception {
        return (T) item;
    }

    @Override
    protected final void logError(Throwable e) throws Exception {
        final Long threadMaxValue = partition.get().getMaxValue();
        final Long threadStartValue = startValue.get();
        final Integer loadLimit = calculateLoadLimit(threadStartValue, threadMaxValue);

        logError(threadStartValue, threadMaxValue, loadLimit, e);
    }

    /**
     * If you want to write your business log for any exception that occurred in reader, you can override this method.
     */
    protected void logError(Long inclusiveStartValue, Long inclusiveEndValue, Integer loadLimit, Throwable e) throws Exception {
        // There is not any default implementation.
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        // There is not any default implementation.
    }

    @Override
    public void close() throws ItemStreamException {
        LOGGER.debug(format("%s[partition:%s] is ran.", getStepExecution().getStepName(), partition.get().toString()));
    }

    private Integer calculateLoadLimit(Long startValue, Long maxValue) {
        return ofNullable(readInterval).orElse(valueOf((maxValue - startValue) + 1).intValue());
    }
}
