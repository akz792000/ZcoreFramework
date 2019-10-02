/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.grid;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zcoreframework.base.dao.AbstractPyramidDAOImpl;
import org.zcoreframework.base.type.ColumnType;
import org.zcoreframework.base.type.QueryType;
import org.zcoreframework.component.dateconverter.DateConverter;
import org.zcoreframework.component.dateconverter.DateConverterParam.DateType;
import org.zcoreframework.component.grid.AbstractGridDataDAOImpl.OperationType;

import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.*;

public class GridQueryDAOImpl extends AbstractPyramidDAOImpl<GridQueryModel> implements GridDAO<GridQueryModel> {
	
	private static final Log log = LogFactory.getLog(GridQueryDAOImpl.class);	
	
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
	
	protected void initParameters() {
		String sql = getModel().getQuery();
		// parameters
		if (parameters.size() != 0) {
			for (String key : parameters.keySet())
				sql = sql.replaceAll(":" + key, parameters.get(key));			
		}
		getModel().setQuery(sql);
	}	

	protected String[] getColumnsName() {
		if (getModel().getType() == QueryType.ENTITY) {
			String[] columns = new String[getModel().getColumns().size()];
			for (int cnt = 0; cnt < columns.length; cnt++) 
				columns[cnt] = "g." + getModel().getColumns().get(cnt).getName();
			return columns;		
		} else {
			String query = getModel().getQuery();
			query = query.substring(query.toUpperCase().indexOf("SELECT ") + "SELECT ".length(), query.toUpperCase().indexOf(" FROM "));
			// get columns
			int index;
	        final int len = query.length();
	        List<String> list = new ArrayList<>();
	        String column = "";
	        int flag = 0;
	        for (index = 0 ; index < len; index++) {
	        	char chr = query.charAt(index);
	        	if (chr == ',' && flag == 0) {
	        		list.add(column);
	        		column = "";
	        	} else {
	        		if (chr == '(') {
	        			flag++; 
	        		}
	        		if (chr == ')') {
	        			flag--; 
	        		}
	        		column += query.charAt(index);
	        	}
	        }
	        // add the last one
	        list.add(column);
			// prepare as array of string
	        int resultSize = list.size();
	        String[] result = new String[resultSize];
            return list.subList(0, resultSize).toArray(result);
		}	
	}	
	
    protected boolean hasWhere(String query){
    	int pos = query.toUpperCase().indexOf(" WHERE ");
    	if (pos == -1)
    		return false;    	
        String next = query.substring(pos + 7);
        if (StringUtils.countMatches(next,"(") == StringUtils.countMatches(next,")"))
        	return true;
        else
        	return hasWhere(next);
    };
    
    public String filterConditionSql(String filter, String filterCondition, String column) {
    	if (filterCondition.equals("EQ"))
    		return column + " = '" + filter.replaceAll("'", "''") + "'";
		return column + " LIKE '%" + filter.replaceAll("'", "''") + "%'";
    }
    
	public String filterWhereSql(String filter, String filterCondition, String column, String mask, ColumnType type) {
		switch (type) {	
		case BOOLEAN:
			return column + " = " + Boolean.valueOf(filter);
		case GREGORIAN_DATE:
			try {
				DateConverter dateConverter = DateConverter.getInstance(DateType.GREGORIAN, mask, filter.toUpperCase());
				if (getModel().getType().equals(QueryType.NATIVE_QUERY)) {
					return "TO_CHAR(" + column + ",'" + mask + "') = '" + dateConverter.getFormatDate() + "'";
				} else {
					return "FUNC('TO_CHAR'," + column + ",'" + mask + "') = '" + dateConverter.getFormatDate() + "'";
				}
			} catch (Exception exp) {
				return filterConditionSql(filter, "LIKE", column);
			}
		case PERSIAN_DATE:
			try {
				DateConverter dateConverter = DateConverter.getInstance(DateType.PERSIAN, mask, filter.toUpperCase());				
				dateConverter.convert(DateType.GREGORIAN);
				if (getModel().getType().equals(QueryType.NATIVE_QUERY)) {
					return "TO_CHAR(" + column + ",'" + mask + "') = '" + dateConverter.getFormatDate() + "'";
				} else {
					return "FUNC('TO_CHAR'," + column + ",'" + mask + "') = '" + dateConverter.getFormatDate() + "'";
				}
			} catch (Exception exp) {
				return filterConditionSql(filter, "LIKE", column);
			}	
		case NUMERIC:
			return column + " = " + filter;
		default:
			return filterConditionSql(filter, filterCondition, column);
		}				
	}    

	protected String getWhere() {
		String sql = "";	
		
		// filters
		if (!StringUtils.isEmpty(getModel().getClientModel().getFilterColumnsValue())) {
			String[] filters = getModel().getClientModel().getFilterColumnsValue().split(",");
			String[] filtersCondition = (!StringUtils.isEmpty(getModel().getClientModel().getFilterColumnsCondition())) ? getModel().getClientModel().getFilterColumnsCondition().split(",") : "".split(",");
			String[] columnsName = getColumnsName();
			if (filters.length != 0) {
				for (int cnt = 0; cnt < filters.length; cnt++) {
					if (!filters[cnt].isEmpty()) {
						if (!hasWhere(getModel().getQuery())){
							if (sql.indexOf(" WHERE ") == -1) 
								sql += " WHERE ";
							else
								sql += " AND ";
						} else
							sql += " AND ";
						sql += filterWhereSql(
								filters[cnt], 
								cnt < filtersCondition.length ? filtersCondition[cnt] : "", 
								columnsName[cnt],
								getModel().getColumns().get(cnt).getMask(),
								getModel().getColumns().get(cnt).getType());																
					}							
				}
			}	
		}	
		
		return sql;
	}
	
	protected String getOrderBy() {
		String sql = "";
		
		// get order by
		String[] columnsName = getColumnsName();
		if (!StringUtils.isEmpty(getModel().getClientModel().getOrderByItem())) {
			sql += " ORDER BY " + columnsName[getModel().getColumnNo(getModel().getClientModel().getOrderByItem())];
			if (!getModel().getClientModel().getOrderByItemSort().isEmpty()) 
				sql += " " + getModel().getClientModel().getOrderByItemSort();
		}
		
		return sql;
	}
	
	protected String getResultSizeSql() {
		// init parameters
		initParameters();

		// create select sql
		String sql = "";
		switch (getModel().getType()) {
			case ENTITY:
				sql = "SELECT COUNT(g) FROM " + getModel().getQuery() + " g";				
				break;
			case JPQL:
				String selectStr = "";
				for (String column : getColumnsName()) {
					if (!selectStr.isEmpty())
						selectStr += ",";
					selectStr += " COUNT(" + column.trim() + ")";
				}
				sql = getModel().getQuery().replaceFirst("(?i)SELECT (.*?) FROM", "SELECT " + selectStr + " FROM");
				break;
			case NATIVE_QUERY:	
				sql = getModel().getQuery().replaceFirst("(?i)SELECT (.*?) FROM", "SELECT COUNT(*) FROM");
				break;
		}
		
		// create where sql
		sql += getWhere();	
		
		return sql;
	}
	
	protected String getResultListSql() {
		// init parameters
		initParameters();
		
		// create select sql
		String sql = "";
		switch (getModel().getType()) {
			case ENTITY:
				sql = "SELECT ";
				String[] columnsName = getColumnsName();
				for (int cnt = 0; cnt < columnsName.length; cnt++) {
					if (cnt != 0) 
						sql += ",";
					sql += columnsName[cnt];
				}
				sql += " FROM " + getModel().getQuery() + " g";		
				break;
			case JPQL:
			case NATIVE_QUERY:	
				sql = getModel().getQuery();
				break;
		}
		
		// create where sql
		sql += getWhere();
		
		// create order sql
		sql += getOrderBy();
		
		return sql;
	}	
	
	@Override
	public Boolean isNotResultable() {
		return (getModel().getQuery().indexOf(":") != -1 && parameters.size() == 0) || StringUtils.isEmpty(getModel().getQuery());
	}	
	
	@Override
	public Double getResultSize() {
		// create result size sql
		String sql = getResultSizeSql();
		
		// log
		if (log.isDebugEnabled())
			log.debug("Result size query sql: " + sql);
		
		// get count
		Long count = null;		
		switch (getModel().getType()) {
			case ENTITY:
				count = getEntityManager().createQuery(sql, Long.class).getResultList().get(0);
				break;
			case NATIVE_QUERY:
				Object value = getEntityManager().createNativeQuery(sql).getResultList().get(0);
				if (value instanceof Integer) {
					count = ((Integer) getEntityManager().createNativeQuery(sql).getResultList().get(0)).longValue();					
				} else {				
					count = ((BigDecimal) getEntityManager().createNativeQuery(sql).getResultList().get(0)).longValue();
				}
				break;
			case JPQL:
				Object[] objects = getEntityManager().createQuery(sql, Object[].class).getResultList().get(0);
				count = (Long) objects[0];
				break;
		}	
		
		return count.doubleValue();			
	}
	

	@SuppressWarnings("unchecked")
	@Override
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
		
		// set first and max
		if (getModel().getClientModel().getLimitPage() > 0) 
			query
				.setFirstResult((getModel().getClientModel().getActivePage() - 1) * getModel().getClientModel().getLimitPage())
				.setMaxResults(getModel().getClientModel().getLimitPage());
				
		return query.getResultList();
	}

	@Override
	public void prepare() {
		// prepare
	}
	
	@Override
	public void setData(List<Object> data) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void setSize(double size) {
		throw new UnsupportedOperationException();		
	}
	
	@Override
	public void setOperationTypes(Set<OperationType> operationTypes) {
		throw new UnsupportedOperationException();		
	}
	
}
