/**
 *
 * @author Ali Karimizandi
 * @author Zahra Azadi
 * @since 2016
 *
 */

package org.zcoreframework.base.data.base;

import org.zcoreframework.base.data.CriteriaCondition;
import org.zcoreframework.base.model.IdField;

import java.io.Serializable;
import java.util.List;

public interface GenericRepository<T, ID extends Serializable> extends GenericJpaRepository<T, ID>, CommonRepository {

    Long count();

    T loadModel(IdField<?> idField);

    T loadById(ID id);
    
    Long count(CriteriaCondition criteriaCondition);    
    
    List<T> find(CriteriaCondition criteriaCondition);

    int delete(Long id);
    
}
