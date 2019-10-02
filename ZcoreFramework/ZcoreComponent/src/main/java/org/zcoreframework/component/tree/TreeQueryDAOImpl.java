/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.tree;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zcoreframework.base.dao.AbstractPyramidDAOImpl;
import org.zcoreframework.base.type.QueryType;
import org.zcoreframework.component.tree.TreeClientModel.OperationType;

public class TreeQueryDAOImpl extends AbstractPyramidDAOImpl<TreeQueryModel> implements TreeDAO<TreeQueryModel> {

	private static final Log log = LogFactory.getLog(TreeQueryDAOImpl.class);

	private Map<String, String> parameters = new HashMap<String, String>();

	public Map<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	public void setParameter(String key, String value) {
		this.parameters.put(key, value);
	}

	public String getResultListSql() {

		String sql = getModel().getQuery();

		// set query if is ENTITY
		if (getModel().getType().equals(QueryType.ENTITY)) {
			sql = "select t.id, t.name, t.parent, t.seq, (select count(u) from " + sql + " u where u.parent = t.id) " + "from " + sql
					+ " t where :where order by t.parent, t.seq";
		}

		// parameters
		if (parameters.size() != 0) {
			for (String key : parameters.keySet()) {
				sql = sql.replaceAll(":" + key, parameters.get(key));
			}
		}

		// create select sql
		return sql.replaceAll(":where", getModel().getClientModel().getOperation().equals(OperationType.SELF) ? "t.id = :id" : "t.parent = :id")
				.replaceAll(":id", Long.toString(getModel().getClientModel().getId()));

	}

	protected Long getLong(Object object) {
		if (object instanceof Long) {
			return (Long) object;
		} else if (object instanceof Integer) {
			return ((Integer) object).longValue();
		} else {
			return ((BigDecimal) object).longValue();
		}
	}

	public List<? extends Object> getResultList() {

		// create result sql
		String sql = getResultListSql();

		// log
		if (log.isDebugEnabled()) {
			log.debug("Result query sql: " + sql);
		}

		// get query
		Query query = null;
		switch (getModel().getType()) {
		case ENTITY:
		case JPQL:
			query = getEntityManager().createQuery(sql);
			break;
		case NATIVE_QUERY:
			query = getEntityManager().createNativeQuery(sql);
			break;
		}

		// convert objects to tree node model
		@SuppressWarnings("unchecked")
		List<Object[]> lists = query.getResultList();
		List<TreeNodeModel> result = new ArrayList<>();
		for (Object[] object : lists) {
			result.add(new TreeNodeModel(getLong(object[0]), (String) object[1], getLong(object[2]), getLong(object[3]), getLong(object[4])));
		}

		return result;
	}

}