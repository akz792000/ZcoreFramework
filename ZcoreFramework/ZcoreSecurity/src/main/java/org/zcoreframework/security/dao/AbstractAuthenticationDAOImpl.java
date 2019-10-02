/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.security.dao;

import org.zcoreframework.base.dao.DefaultEntityManager;
import org.zcoreframework.security.model.UserInfo;

public abstract class AbstractAuthenticationDAOImpl<M extends UserInfo> extends DefaultEntityManager implements AuthenticationDAO<M> {
		
}
