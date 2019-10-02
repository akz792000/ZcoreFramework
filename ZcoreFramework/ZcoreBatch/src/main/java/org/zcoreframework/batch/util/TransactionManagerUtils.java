package org.zcoreframework.batch.util;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.zcoreframework.base.util.ApplicationContextUtils;

import static org.zcoreframework.base.config.TransactionTemplateDefinitionParser.TRANSACTION_TEMPLATE;

/**
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 *         Date: 4/26/17
 */
public class TransactionManagerUtils {

    private static PlatformTransactionManager transactionManager;

    public static PlatformTransactionManager getTransactionManager() {
        return transactionManager;
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        TransactionManagerUtils.transactionManager = transactionManager;
    }

    static TransactionTemplate getTransactionTemplate() {
        return ApplicationContextUtils.getBean(TRANSACTION_TEMPLATE, TransactionTemplate.class);
    }
}
