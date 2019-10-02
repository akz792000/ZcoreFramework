package org.zcoreframework.pos.utils;

import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.zcoreframework.base.util.ApplicationContextUtils;

import static org.zcoreframework.base.config.TransactionTemplateDefinitionParser.TRANSACTION_TEMPLATE;

/**
 *
 */
public final class TransactionUtils {

    public static <T> T runAtomically(TransactionCallback<T> action) {
        TransactionTemplate template = ApplicationContextUtils.getBean(TRANSACTION_TEMPLATE, TransactionTemplate.class);
        template.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);

        return template.execute(action);
    }
}
