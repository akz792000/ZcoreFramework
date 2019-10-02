package org.zcoreframework.batch.reader;

import org.zcoreframework.base.annotation.RepositoryInstance;
import org.zcoreframework.base.data.base.DefaultRepository;

import java.util.List;
import java.util.Map;

/**
 * Created by z.azadi on 4/10/2016.
 *
 * @author Zahra Azadi
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 */

public abstract class DefaultPartitionedNamedQueryItemReader<E, T> extends DefaultPartitionedItemReader<E, T> {

    @RepositoryInstance
    private DefaultRepository defaultRepository;

    /**
     * @param readInterval If this argument is passed as null, your query can be unordered.
     */
    public DefaultPartitionedNamedQueryItemReader(Integer readInterval) {
        super(readInterval);
    }

    @Override
    protected final List<E> sortedRead(Long inclusiveStartValue, Long inclusiveEndValue, Integer loadLimit) throws Exception {
        return (List<E>) defaultRepository.loadResultListBySize(getSortedQueryName(), getQueryParameters(inclusiveStartValue, inclusiveEndValue), loadLimit);
    }

    /**
     * <B>Note: </B>If readInterval is not null, your query must be ordered according to 'value' field.
     */
    protected abstract String getSortedQueryName() throws Exception;

    protected abstract Map<String, Object> getQueryParameters(Long inclusiveStartValue, Long inclusiveEndValue) throws Exception;

}
