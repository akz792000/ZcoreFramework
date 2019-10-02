/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.method;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.zcoreframework.base.core.Executor;
import org.zcoreframework.base.exception.BaseException;
import org.zcoreframework.base.log.LogMock.Type;

public class MethodTemplateExecutorImpl implements MethodTemplateExecutor {

	private final PlatformTransactionManager transactionManager;

	public MethodTemplateExecutorImpl(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	protected DefaultTransactionDefinition getTransactionDefinition() {
		return new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
	}

	public static Type getExceptionType(Throwable throwable) {
	    return throwable instanceof BaseException ? Type.EXCEPTION : Type.ERROR;
    }

	protected void handleException(MethodExecutor methodExecutor, Throwable throwable) throws Exception {
		// log database exception
		TransactionStatus status = transactionManager.getTransaction(getTransactionDefinition());
		try {
			Executor executor = methodExecutor.getLogger(ExceptionUtils.getFullStackTrace(throwable), getExceptionType(throwable));
			if (executor != null) {
				executor.execute();
			}
			transactionManager.commit(status);
		} catch (Exception | Error e) {
			if (!status.isCompleted()) {
				transactionManager.rollback(status);
			}
			throw e;
		}
	}

	@Override
	public Object execute(MethodExecutor methodExecutor) throws Exception {
		TransactionStatus status = transactionManager.getTransaction(getTransactionDefinition());
		String result = null;
        Type type = Type.SUCCESS;
		Executor executor = methodExecutor.getExecutor();
		try {
			return executor.execute();
		} catch (Exception | Error e) {
			boolean rollback = true;

			// handle exception
			Class<? extends Throwable>[] noRollbackFor = methodExecutor.noRollbackFor();
			if (noRollbackFor != null) {
				for (int i = 0; i < noRollbackFor.length; i++) {					
					if (noRollbackFor[i].isAssignableFrom(e.getClass())) {
						rollback = false;
						break;
					}
				}							
			}			
			if (rollback) {
				transactionManager.rollback(status);
				status = transactionManager.getTransaction(getTransactionDefinition());
			}
			result = ExceptionUtils.getFullStackTrace(e);

			// set type
			type = getExceptionType(e);

			// throw exception
			throw e;
		} finally {
			try {
				// log
				executor = methodExecutor.getLogger(result, type);
				if (executor != null) {
					executor.execute();
				}

				//commit
				try {
					transactionManager.commit(status);
				} catch (Exception | Error e) {
					// handle database exception
					handleException(methodExecutor, e);

					// throw exception
					throw e;
				}
			} catch (Exception | Error e) {
				// rollback
				if (!status.isCompleted()) {
					transactionManager.rollback(status);
				}

				// throw exception
				throw e;
			}
		}
	}
	
}
