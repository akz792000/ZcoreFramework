/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.method;

import org.zcoreframework.base.core.Executor;
import org.zcoreframework.base.log.LogMock.Type;

@FunctionalInterface
public interface MethodExecutor {
	
	Executor getExecutor() throws Exception;

	default Executor getLogger(String result, Type type) throws Exception {
		return null;
	}
	
	default Class<? extends Throwable>[] noRollbackFor() {
		return null;
	};

}
