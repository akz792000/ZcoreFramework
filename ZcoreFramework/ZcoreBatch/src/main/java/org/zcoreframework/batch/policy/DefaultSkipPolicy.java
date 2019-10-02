package org.zcoreframework.batch.policy;

import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.stereotype.Component;
import org.zcoreframework.batch.exceptions.SkippableException;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * Created by z.azadi on 12/12/2016.
 *
 * @author Zahra Azadi
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 */

@Component
public class DefaultSkipPolicy implements SkipPolicy {

    @Override
    public boolean shouldSkip(Throwable t, int skipCount) throws SkipLimitExceededException {
        return (t instanceof SkippableException) ? TRUE : FALSE;
    }
}
