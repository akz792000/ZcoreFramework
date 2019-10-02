/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.map.plain;

import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.zcoreframework.base.util.PlainMap;
import org.zcoreframework.component.map.AbstractMapEntityDAOImpl;
import org.zcoreframework.component.map.BaseMapEntityModel;

public class PlainGenericMapEntityDAOImpl extends AbstractMapEntityDAOImpl<BaseMapEntityModel, PlainMap> {

	@Override
	public PlainMap getResult() {
		PlainMap result = new PlainMap();		
		BaseMapEntityModel model = getModel();
		
		// prepare query
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();		
		CriteriaQuery<Tuple> cq = cb.createTupleQuery();
		Root<?> masterEntity = cq.from(model.getMaster().getEntity());
		cq.multiselect(masterEntity);	
		cq.orderBy(
				cb.asc(masterEntity.get(model.getMaster().getOrder()))
		);		
		TypedQuery<Tuple> tq = getEntityManager().createQuery(cq);
		
		Object masterValue = null;
		for (Tuple tuple : tq.getResultList()) {
			
			// master
			BeanWrapper entityWrapper = new BeanWrapperImpl(tuple.get(0));	
			masterValue = entityWrapper.getPropertyValue(model.getMaster().getKey());			
			result.put(masterValue, bind(model.getMaster().getModel(), model.getMaster().getProperties(), entityWrapper));
			
		}
		
		return result;
	}

}