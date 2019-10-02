/**
 * @author Ali Karimizandi
 * @author Zahra Azadi
 * @since 2016
 */

package org.zcoreframework.base.data.impl;

import org.eclipse.persistence.jpa.JpaEntityManager;
import org.eclipse.persistence.queries.StoredFunctionCall;
import org.eclipse.persistence.queries.ValueReadQuery;
import org.zcoreframework.base.data.Parameter;
import org.zcoreframework.base.data.Parameterize;
import org.zcoreframework.base.model.IdField;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TemporalType;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class RepositoryUtils {

    static final String LOAD_MODEL_ENTITY_QUERY_PREFIX = "loadModelBy";

    static String prepareWhereQuery(Map<String, Object> map) {
        String result = "";
        boolean flag = true;
        for (Entry<String, Object> item : map.entrySet()) {
            result += flag ? " where " : " and ";
            String key = item.getKey();
            String parameter = key;
            if (key.indexOf(".") != -1) {
                parameter = parameter.replace(".", "_");
            }
            result += "(:PARAMETER is null or U.KEY = :PARAMETER)".replaceAll("KEY", key).replaceAll("PARAMETER", parameter);
            flag = false;
        }
        return result;
    }

    static void prepareParameter(Query query, Map<String, Object> map) {
        map.forEach((key, value) -> {
            if (key.indexOf(".") != -1) {
                key = key.replace(".", "_");
            }
            query.setParameter(key, value);
        });
    }

    static void setQueryFirstMax(Query query, int activePage, int limitPage) {
        int activePageNumber = activePage - 1;
        activePageNumber = activePageNumber >= 0 ? activePageNumber : 0;
        query.setFirstResult(activePageNumber * limitPage).setMaxResults(limitPage);
    }

    static Long count(EntityManager entityManager, Class<?> clazz) {
        String query = "select count(u) from %s u".replaceAll("%s", clazz.getSimpleName());
        return entityManager.createQuery(query, Long.class).getSingleResult();
    }

    static Long count(EntityManager entityManager, Class<?> clazz, Map<String, Object> map) {
        String querySQL = "select count(U) from " + clazz.getSimpleName() + " U" + prepareWhereQuery(map);
        Query query = entityManager.createQuery(querySQL, Long.class);
        prepareParameter(query, map);
        return (Long) query.getSingleResult();
    }

    static <T> List<T> load(EntityManager entityManager, Class<T> clazz, Map<String, Object> map, int activePage, int limitPage) {
        try {
            String querySQL = "select U from " + clazz.getSimpleName() + " U" + prepareWhereQuery(map);
            Query query = entityManager.createQuery(querySQL);
            prepareParameter(query, map);
            setQueryFirstMax(query, activePage, limitPage);
            List<T> result = query.getResultList();
            return result == null || result.isEmpty() ? null : result;
        } catch (javax.persistence.NoResultException exp) {
            return null;
        }
    }


    static Object loadModel(EntityManager entityManager, Class<?> clazz, IdField<?> idField) {
        String name = idField.getName();
        name = clazz.getSimpleName() + "." + LOAD_MODEL_ENTITY_QUERY_PREFIX + name.substring(0, 1).toUpperCase() + name.substring(1);
        Query query = entityManager.createNamedQuery(name);
        List<?> lists = query.setParameter(idField.getName(), idField.getValue()).getResultList();
        return lists.size() == 0 ? null : lists.get(0);
    }

    static List<?> loadResultList(EntityManager entityManager, String name) {
        try {
            Query query = entityManager.createNamedQuery(name);
            List<?> result = query.getResultList();
            return result == null || result.isEmpty() ? null : result;
        } catch (javax.persistence.NoResultException exp) {
            return null;
        }
    }

    static List<?> loadResultList(EntityManager entityManager, String name, Object value) {
        try {
            Query query = entityManager.createNamedQuery(name);
            query.setParameter("value", value);
            List<?> result = query.getResultList();
            return result == null || result.isEmpty() ? null : result;
        } catch (javax.persistence.NoResultException exp) {
            return null;
        }
    }

    static List<?> loadResultList(EntityManager entityManager, String name, List<Object> parameters) {
        try {
            Query query = entityManager.createNamedQuery(name);
            if (parameters != null) {
                int i = 0;
                for (Object object : parameters) {
                    query.setParameter(++i, object);
                }
            }
            List<?> result = query.getResultList();
            return result == null || result.isEmpty() ? null : result;
        } catch (javax.persistence.NoResultException exp) {
            return null;
        }
    }

    static List<?> loadResultList(EntityManager entityManager, String name, Map<String, Object> parameters) {
        try {
            Query query = entityManager.createNamedQuery(name);
            if (parameters != null) {
                for (Entry<String, Object> entry : parameters.entrySet()) {
                    query.setParameter(entry.getKey(), entry.getValue());
                }
            }
            List<?> result = query.getResultList();
            return result == null || result.isEmpty() ? null : result;
        } catch (javax.persistence.NoResultException exp) {
            return null;
        }
    }

    static Object loadSingleResult(EntityManager entityManager, String name, Object value) {
        try {
            Query query = entityManager.createNamedQuery(name);
            query.setParameter("value", value);
            return query.getSingleResult();
        } catch (javax.persistence.NoResultException exp) {
            return null;
        }
    }

    static Object loadSingleResult(EntityManager entityManager, String name, Map<String, Object> parameters) {
        try {
            Query query = entityManager.createNamedQuery(name);
            for (Entry<String, Object> entry : parameters.entrySet()) {
                query.setParameter(entry.getKey(), entry.getValue());
            }
            return query.getSingleResult();
        } catch (javax.persistence.NoResultException exp) {
            return null;
        }
    }

    static List<?> loadResultListByTemporalType(EntityManager entityManager, String value, Map<String, SimpleImmutableEntry<Object, Object>> parameters) {
        try {
            Query query = entityManager.createNamedQuery(value);
            setParameter(query, parameters);
            List<?> result = query.getResultList();
            return result == null || result.isEmpty() ? null : result;
        } catch (javax.persistence.NoResultException exp) {
            return null;
        }
    }

    static Object loadSingleResultByTemporalType(EntityManager entityManager, String value, Map<String, SimpleImmutableEntry<Object, Object>> parameters) {
        try {
            Query query = entityManager.createNamedQuery(value);
            setParameter(query, parameters);
            return query.getSingleResult();
        } catch (javax.persistence.NoResultException exp) {
            return null;
        }
    }

    static int executeUpdate(EntityManager entityManager, String name, Object value) {
        Query query = entityManager.createNamedQuery(name);
        query.setParameter("value", value);
        return query.executeUpdate();
    }

    static int executeUpdate(EntityManager entityManager, String name, Map<String, Object> parameters) {
        Query query = entityManager.createNamedQuery(name);
        for (Entry<String, Object> entry : parameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.executeUpdate();
    }

    static List<?> loadResultListBySize(EntityManager entityManager, String value, Map<String, Object> parameters, int maxResult) {
        try {
            Query query = entityManager.createNamedQuery(value);
            if (parameters != null) {
                for (Entry<String, Object> entry : parameters.entrySet()) {
                    query.setParameter(entry.getKey(), entry.getValue());
                }
            }
            List<?> result = query.setMaxResults(maxResult).getResultList();
            return result == null || result.isEmpty() ? null : result;
        } catch (javax.persistence.NoResultException exp) {
            return null;
        }
    }

    static List<?> loadResultListBySize(EntityManager entityManager, String value, Map<String, Object> parameters, int activePage, int limitPage) {
        try {
            Query query = entityManager.createNamedQuery(value);
            if (parameters != null) {
                for (Entry<String, Object> entry : parameters.entrySet()) {
                    query.setParameter(entry.getKey(), entry.getValue());
                }
            }
            setQueryFirstMax(query, activePage, limitPage);
            List<?> result = query.getResultList();
            return result == null || result.isEmpty() ? null : result;
        } catch (javax.persistence.NoResultException exp) {
            return null;
        }
    }

    static Object loadFirstResult(EntityManager entityManager, String value, Map<String, Object> parameters) {
        try {
            Query query = entityManager.createNamedQuery(value);
            if (parameters != null) {
                for (Entry<String, Object> entry : parameters.entrySet()) {
                    query.setParameter(entry.getKey(), entry.getValue());
                }
            }
            return query.setMaxResults(1).getSingleResult();
        } catch (javax.persistence.NoResultException exp) {
            return null;
        }
    }

    static Map<String, Parameter> executeProcedure(EntityManager entityManager, String name, Parameterize parameterize) {
        Map<String, Parameter> parameters = parameterize.execute();

        // create stored procedure
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery(name);

        // set parameters
        for (Entry<String, Parameter> entry : parameters.entrySet()) {
            Parameter parameter = entry.getValue();
            String parameterName = entry.getKey();
            storedProcedure.registerStoredProcedureParameter(parameterName, parameter.getType(), parameter.getMode());
            switch (parameter.getMode()) {
                case IN:
                case INOUT:
                    storedProcedure.setParameter(parameterName, parameter.getValue());
                    break;
                default:
                    break;
            }
        }

        // execute
        storedProcedure.execute();

        // set output parameter
        for (Entry<String, Parameter> entry : parameters.entrySet()) {
            Parameter parameter = entry.getValue();
            switch (parameter.getMode()) {
                case OUT:
                case INOUT:
                case REF_CURSOR:
                    parameter.setValue(storedProcedure.getOutputParameterValue(entry.getKey()));
                    break;
                default:
                    break;
            }
        }

        return parameters;
    }

    static Object executeFunction(EntityManager entityManager, String name, Map<String, Object> parameters) {
        StoredFunctionCall functionCall = new StoredFunctionCall();
        functionCall.setProcedureName(name);
        functionCall.setResult("result");
        for (String key : parameters.keySet()) {
            functionCall.addNamedArgumentValue(key, parameters.get(key));
        }
        ValueReadQuery valQuery = new ValueReadQuery();
        valQuery.setCall(functionCall);
        Query query = ((JpaEntityManager) entityManager.getDelegate()).createQuery(valQuery);
        return query.getSingleResult();
    }

    private static void setParameter(Query query, Map<String, SimpleImmutableEntry<Object, Object>> parameters) {
        for (Entry<String, SimpleImmutableEntry<Object, Object>> mapEntry : parameters.entrySet()) {
            SimpleImmutableEntry<Object, Object> map = mapEntry.getValue();
            if (map.getValue() == null)
                query.setParameter(mapEntry.getKey(), map.getValue());
            else
                query.setParameter(mapEntry.getKey(), (Date) map.getKey(), (TemporalType) map.getValue());
        }
    }

    static int delete(EntityManager entityManager, Class<?> clazz, Long id) {
        Query query = entityManager.createQuery("delete from " + clazz.getSimpleName() + " u where u.id = :value");
        return query.setParameter("value", id).executeUpdate();
    }

}