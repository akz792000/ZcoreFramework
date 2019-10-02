/**
 *
 * @author Ali Karimizandi
 * @since 2016
 *
 */

package org.zcoreframework.base.data.impl;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.zcoreframework.base.data.CriteriaColumn;
import org.zcoreframework.base.data.CriteriaColumn.OrderType;
import org.zcoreframework.base.data.CriteriaCondition;

class RepositoryCriteriaUtils {

	static <T> void where(CriteriaCondition queryCondition, CriteriaBuilder cb, CriteriaQuery<T> cq, Root<T> c) {
		List<Predicate> predList = new LinkedList<Predicate>();
		for (CriteriaColumn column : queryCondition.getColumns()) {
			if (column.getType() != null) {
				if (column.getType().equals(String.class)) {
					predList.add(cb.like(c.get(column.getName()), (String) column.getValue()));
				} else {
					predList.add(cb.equal(c.get(column.getName()), column.getValue()));
				}
			}
		}
		Predicate[] predArray = new Predicate[predList.size()];
		predList.toArray(predArray);
		cq.where(predArray);
	}

	static <T> void order(CriteriaCondition queryCondition, CriteriaBuilder cb, CriteriaQuery<T> cq, Root<?> c) {
		for (CriteriaColumn column : queryCondition.getColumns()) {
			OrderType orderType = column.getOrderType();
			if (!orderType.equals(OrderType.DEFAULT)) {
				cq.orderBy(orderType.equals(OrderType.ASC) ? cb.asc(c.get(column.getName())) : cb.desc(c.get(column.getName())));
			}
		}
	}

	static <T> void paging(TypedQuery<T> query, CriteriaCondition criteriaCondition) {
		if (criteriaCondition.getFirstResult() >= 0) {
			query.setFirstResult(criteriaCondition.getFirstResult());
			query.setMaxResults(criteriaCondition.getMaxResults());
		}
	}

	static <T> List<T> find(EntityManager entityManager, Class<T> clazz, CriteriaCondition criteriaCondition) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(clazz);
		Root<T> c = cq.from(clazz);
		where(criteriaCondition, cb, cq, c);
		order(criteriaCondition, cb, cq, c);
		TypedQuery<T> query = entityManager.createQuery(cq.select(c));
		paging(query, criteriaCondition);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	static <T> Long count(EntityManager entityManager, Class<T> clazz, CriteriaCondition criteriaCondition) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(clazz);
		Root<T> c = cq.from(clazz);
		where(criteriaCondition, cb, cq, c);
		TypedQuery<T> query = entityManager.createQuery(cq.select((Selection<? extends T>) cb.count(c)));
		return (Long) query.getSingleResult();
	}

}
