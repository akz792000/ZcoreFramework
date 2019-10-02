/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.listvalue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;
import org.zcoreframework.base.dao.AbstractPyramidDAOImpl;
import org.zcoreframework.component.entryset.EntrySetSimple;
import org.zcoreframework.component.entryset.EntrySetSimpleList;

public class ListValueQueryDAOImpl extends AbstractPyramidDAOImpl<ListValueQueryModel> implements ListValueDAO<ListValueQueryModel, EntrySetSimpleList> {
	
	private static final Log log = LogFactory.getLog(ListValueQueryDAOImpl.class);	
	
	private Map<String, String> parameters = new HashMap<>();
	
	public Map<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}	
	
	public void setParameter(String key, String value) {
		this.parameters.put(key, value);
	}	
		
	protected String[] getQueryColumns(String queryStr) {
		return queryStr.substring(queryStr.toUpperCase().indexOf("SELECT ") + 7, queryStr.toUpperCase().indexOf(" FROM ")).split(",");
	}	
	
	protected void prepareQuery() {
		String sql = getModel().getQuery();
		// parameters
		if (parameters.size() != 0) {
			for (String key : parameters.keySet())
				sql = sql.replaceAll(":" + key, parameters.get(key));			
		}
		getModel().setQuery(sql);
	}	
		
	protected String createResultSql() {	
		prepareQuery();
		String sql = "";
		String selectStr = "";
		String[] columns = getQueryColumns(getModel().getQuery());
		Assert.isTrue(columns.length <= 2, "Query must have two columns");
		
		switch (getModel().getType()) {
		case JPQL:				
			for (String column : columns) {
				if (!selectStr.isEmpty())
					selectStr += ",";
				//selectStr += " FUNC('TO_CHAR'," + column.trim() + ")";
				selectStr += column.trim();
			}
			sql = getModel().getQuery()
					.replaceFirst("(?i)SELECT (.*?) FROM", "SELECT new org.zcoreframework.component.entryset.EntrySetSimple(" + selectStr + ") FROM");
			break;
		case NATIVE_QUERY:
			for (String column : columns) {
				if (!selectStr.isEmpty())
					selectStr += ",";
				//selectStr += " TO_CHAR(" + column.trim() + ")";
				selectStr += column.trim();
			}
			sql = getModel().getQuery()
					.replaceFirst("(?i)SELECT (.*?) FROM", "SELECT " + selectStr + " FROM");
			break;
		default:
			break;
		}
			
		return sql;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public EntrySetSimpleList getResult() {		
		List<EntrySetSimple> result = null;
		String sql = createResultSql();
		
		if (log.isDebugEnabled())
			log.debug("Result query sql: " + sql);
		
		Query query = null;
		
		switch (getModel().getType()) {
		case JPQL:
			query = getEntityManager().createQuery(sql);
			result = query.getResultList();
			break;
		case NATIVE_QUERY:
			result = new ArrayList<EntrySetSimple>();				
			query = getEntityManager().createNativeQuery(sql);
			for (Object object : query.getResultList())
				result.add(new EntrySetSimple(((Object[]) object)[0].toString(), ((Object[]) object)[1].toString()));			
			break;
		default:
			break;
		}			
	
		return  new EntrySetSimpleList(result);
	}	
	
	@Override
	public Boolean isNotResultable() {
		return getModel().getQuery().indexOf(":") != -1 && parameters.size() == 0;
	}
	
}
