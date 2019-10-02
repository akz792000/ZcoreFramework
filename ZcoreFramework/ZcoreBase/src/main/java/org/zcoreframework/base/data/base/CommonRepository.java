/**
 *
 * @author Ali Karimizandi
 * @author Zahra Azadi
 * @since 2016
 *
 */

package org.zcoreframework.base.data.base;

import org.zcoreframework.base.data.Parameter;
import org.zcoreframework.base.data.Parameterize;

import javax.persistence.EntityManager;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.List;
import java.util.Map;

public interface CommonRepository {

    Long count(Class<?> clazz, Map<String, Object> map);

    <T> List<T> load(Class<T> clazz, Map<String, Object> map, int activePage, int limitPage);
	
	List<?> loadResultList(String name);
	
    List<?> loadResultList(String name, Object value);
    
    List<?> loadResultList(String name, List<Object> parameters);    

    List<?> loadResultList(String name, Map<String, Object> parameters);

    Object loadSingleResult(String name, Object value);

    Object loadSingleResult(String name, Map<String, Object> parameters);

    List<?> loadResultListByTemporalType(String value, Map<String, SimpleImmutableEntry<Object, Object>> parameters);

    Object loadSingleResultByTemporalType(String value, Map<String, SimpleImmutableEntry<Object, Object>> parameters);
    
    int executeUpdate(String name, Object value);

    int executeUpdate(String name, Map<String, Object> parameters);
    
    List<?> loadResultListBySize(String value, Map<String, Object> parameters, int maxResult);

    List<?> loadResultListBySize(String value, Map<String, Object> parameters, int activePage, int limitPage);
    
    Object loadFirstResult(String value, Map<String, Object> parameters);
    
    Map<String, Parameter> executeProcedure(String name, Parameterize parameterize);
    
    Object executeFunction(String name, Map<String, Object> parameters);

}
