/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.map.chain;

import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.zcoreframework.base.util.ChainMap;
import org.zcoreframework.component.map.AbstractMapEntityDAOImpl;

public class ChainGenericMapEntityDAOImpl extends AbstractMapEntityDAOImpl<ChainGenericMapEntityModel, ChainMap> {
						
	public ChainMap getResult() {
		ChainMap result = new ChainMap();		
		ChainGenericMapEntityModel model = getModel();
		
		// prepare query
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();		
		CriteriaQuery<Tuple> cq = cb.createTupleQuery();
		Root<?> masterEntity = cq.from(model.getMaster().getEntity());
		Join<?, ?> detailEntity = masterEntity.join(model.getMaster().getJoin(), JoinType.INNER);		
		cq.multiselect(masterEntity, detailEntity);
		cq.orderBy(
				cb.asc(masterEntity.get(model.getMaster().getOrder())), 
				cb.asc(detailEntity.get(model.getDetail().getOrder()))
		);
		TypedQuery<Tuple> tq = getEntityManager().createQuery(cq);
		
		Object masterValue = null;
		Object detailValue = null;
		for (Tuple tuple : tq.getResultList()) {
			
			// master
			BeanWrapper entityWrapper = new BeanWrapperImpl(tuple.get(0));	
			masterValue = entityWrapper.getPropertyValue(model.getMaster().getKey());			

			// put master
			if (result.getMember().get(masterValue) == null) {
				result.put(masterValue, bind(model.getMaster().getModel(), model.getMaster().getProperties(), entityWrapper));
			}
			
			// put detail
			entityWrapper = new BeanWrapperImpl(tuple.get(1));	
			detailValue = entityWrapper.getPropertyValue(model.getDetail().getKey());					
			result.put(masterValue, detailValue, bind(model.getDetail().getModel(), model.getDetail().getProperties(), entityWrapper));
						
		}
		
		return result;
	}

}