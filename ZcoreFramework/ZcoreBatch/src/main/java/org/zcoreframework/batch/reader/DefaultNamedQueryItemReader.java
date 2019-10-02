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

public abstract class DefaultNamedQueryItemReader<E, T> extends DefaultItemReader<E, T> {

    @RepositoryInstance
    private DefaultRepository defaultRepository;

    private ThreadLocal<Boolean> isRead = new ThreadLocal<>();

    /**
     * @param readInterval If this argument is passed as not null, your query must be ordered.
     */
    public DefaultNamedQueryItemReader(Integer readInterval) {
        super(readInterval);
        isRead.set(false);
    }

    @Override
    protected final List<E> doRead(Integer loadLimit) throws Exception {
        if (loadLimit != null) {
            return (List<E>) defaultRepository.loadResultList(getQueryName(), getQueryParameters(loadLimit));
        }
        if (isRead.get()) return null;

        isRead.set(true);
        return (List<E>) defaultRepository.loadResultList(getQueryName(), getQueryParameters(loadLimit));
    }

    /**
     * <B>Note: </B>If readInterval is not null, your query must be ordered.
     */
    protected abstract String getQueryName() throws Exception;

    protected abstract Map<String, Object> getQueryParameters(Integer loadLimit) throws Exception;

}
