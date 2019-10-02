package org.zcoreframework.batch.reader;

import org.apache.log4j.Logger;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.zcoreframework.base.annotation.RepositoryInstance;
import org.zcoreframework.base.beans.factory.InitializeAware;
import org.zcoreframework.base.data.base.DefaultRepository;
import org.zcoreframework.batch.util.TransactionUtils;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by z.azadi on 4/10/2016.
 */
public abstract class AbstractQueryNameItemReader<T> implements ItemReader<T>, InitializeAware {

    private final Logger LOGGER = Logger.getLogger(this.getClass());
    private StepExecution stepExecution;
    private Iterator<?> result;
    private AtomicBoolean isInitialized = new AtomicBoolean(false);

    @RepositoryInstance
    private DefaultRepository defaultRepository;

    private String queryName;

    private Map<String, SimpleImmutableEntry<Object, Object>> parametersTemporalType;

    private Map<String, Object> parameters;

    protected AbstractQueryNameItemReader(String queryName, Map<String, Object> parameters) {
        this.queryName = queryName;
        this.parameters = parameters;
    }

    public AbstractQueryNameItemReader() {
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public abstract Map<String, Object> getParameters(StepExecution stepExecution);

    public void setParametersTemporalType(Map<String, SimpleImmutableEntry<Object, Object>> parametersTemporalType) {
        this.parametersTemporalType = parametersTemporalType;
    }

    public abstract Map<String, SimpleImmutableEntry<Object, Object>> getParametersTemporalType(StepExecution stepExecution);

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public T read() throws Exception, ParseException, NonTransientResourceException {
        try {
            LOGGER.info(String.format("Start to read in Step Name %s with Status %s ",
                    this.stepExecution.getStepName(), this.stepExecution.getStatus()));
            LOGGER.info(String.format("Step  %d  readCount", this.stepExecution.getReadCount()));
            LOGGER.info(String.format("Step  %s  CommitCount", this.stepExecution.getCommitCount()));
            T t = null;
            if (!validation())
                return null;
            if (!isInitialized.getAndSet(true)) {
                LOGGER.info("Starting to execute query");
                List<?> items;
                parametersTemporalType = getParametersTemporalType(stepExecution);
                items = TransactionUtils.executeReadOnly(status -> {
                    if (parametersTemporalType != null)
                        return defaultRepository.loadResultListByTemporalType(queryName, parametersTemporalType);
                    else {
                        parameters = getParameters(stepExecution);
                        return defaultRepository.loadResultList(queryName, parameters);
                    }
                });
                if (items != null) {
                    result = items.iterator();
                }
                LOGGER.info("Query execution already finished!");
            }
            if (result != null && result.hasNext()) {
                LOGGER.info("Trying to remove first item of result");
                t = (T) result.next();

            } else {
                LOGGER.info("Results are null!");
                return null;
            }
            return t;
        } catch (Exception exception) {
            LOGGER.error("An exception occurred in reader", exception);
            return null;
        }
    }

    public StepExecution getStepExecution() {
        return this.stepExecution;
    }

    @BeforeStep
    public void saveStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

    public boolean validation() {
        return true;
    }

}
