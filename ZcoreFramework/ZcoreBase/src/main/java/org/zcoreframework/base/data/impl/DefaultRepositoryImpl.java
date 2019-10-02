/**
 * @author Ali Karimizandi
 * @author Zahra Azadi
 * @since 2016
 */

package org.zcoreframework.base.data.impl;

import org.zcoreframework.base.data.CriteriaCondition;
import org.zcoreframework.base.data.Parameter;
import org.zcoreframework.base.data.Parameterize;
import org.zcoreframework.base.data.base.DefaultRepository;
import org.zcoreframework.base.model.IdField;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.List;
import java.util.Map;

public class DefaultRepositoryImpl extends DefaultJpaRepositoryImpl implements DefaultRepository {

    @Override
    public <T> Long count(Class<T> clazz) {
        return RepositoryUtils.count(getEntityManager(), clazz);
    }

    @Override
    public Long count(Class<?> clazz, Map<String, Object> map) {
        return RepositoryUtils.count(getEntityManager(), clazz, map);
    }

    @Override
    public <T> List<T> load(Class<T> clazz, Map<String, Object> map, int activePage, int limitPage) {
        return RepositoryUtils.load(getEntityManager(), clazz, map, activePage, limitPage);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T loadModel(Class<T> clazz, IdField<?> idField) {
        return (T) RepositoryUtils.loadModel(getEntityManager(), clazz, idField);
    }

    @Override
    public <T> Long count(Class<T> clazz, CriteriaCondition criteriaCondition) {
        return RepositoryCriteriaUtils.count(getEntityManager(), clazz, criteriaCondition);
    }

    @Override
    public <T> List<T> find(Class<T> clazz, CriteriaCondition criteriaCondition) {
        return RepositoryCriteriaUtils.find(getEntityManager(), clazz, criteriaCondition);
    }

    @Override
    public <T, ID> T loadById(Class<T> clazz, ID id) {
        return find(clazz, id);
    }

    @Override
    public List<?> loadResultList(String name) {
        return RepositoryUtils.loadResultList(getEntityManager(), name);
    }

    @Override
    public List<?> loadResultList(String name, Object value) {
        return RepositoryUtils.loadResultList(getEntityManager(), name, value);
    }

    @Override
    public List<?> loadResultList(String name, List<Object> parameters) {
        return RepositoryUtils.loadResultList(getEntityManager(), name, parameters);
    }

    @Override
    public List<?> loadResultList(String name, Map<String, Object> parameters) {
        return RepositoryUtils.loadResultList(getEntityManager(), name, parameters);
    }

    @Override
    public Object loadSingleResult(String name, Object value) {
        return RepositoryUtils.loadSingleResult(getEntityManager(), name, value);
    }

    @Override
    public Object loadSingleResult(String name, Map<String, Object> parameters) {
        return RepositoryUtils.loadSingleResult(getEntityManager(), name, parameters);
    }

    @Override
    public List<?> loadResultListByTemporalType(String value, Map<String, SimpleImmutableEntry<Object, Object>> parameters) {
        return RepositoryUtils.loadResultListByTemporalType(getEntityManager(), value, parameters);
    }

    @Override
    public Object loadSingleResultByTemporalType(String value, Map<String, SimpleImmutableEntry<Object, Object>> parameters) {
        return RepositoryUtils.loadSingleResultByTemporalType(getEntityManager(), value, parameters);
    }

    @Override
    public int executeUpdate(String name, Object value) {
        return RepositoryUtils.executeUpdate(getEntityManager(), name, value);
    }

    @Override
    public int executeUpdate(String name, Map<String, Object> parameters) {
        return RepositoryUtils.executeUpdate(getEntityManager(), name, parameters);
    }

    @Override
    public List<?> loadResultListBySize(String value, Map<String, Object> parameters, int maxResult) {
        return RepositoryUtils.loadResultListBySize(getEntityManager(), value, parameters, maxResult);
    }

    @Override
    public List<?> loadResultListBySize(String value, Map<String, Object> parameters, int activePage, int limitPage) {
        return RepositoryUtils.loadResultListBySize(getEntityManager(), value, parameters, activePage, limitPage);
    }

    @Override
    public Object loadFirstResult(String value, Map<String, Object> parameters) {
        return RepositoryUtils.loadFirstResult(getEntityManager(), value, parameters);
    }

    @Override
    public Map<String, Parameter> executeProcedure(String name, Parameterize parameterize) {
        return RepositoryUtils.executeProcedure(getEntityManager(), name, parameterize);
    }

    @Override
    public Object executeFunction(String name, Map<String, Object> parameters) {
        return RepositoryUtils.executeFunction(getEntityManager(), name, parameters);
    }

    @Override
    public <T> int delete(Class<T> clazz, Long id) {
        return RepositoryUtils.delete(getEntityManager(), clazz, id);
    }

}
