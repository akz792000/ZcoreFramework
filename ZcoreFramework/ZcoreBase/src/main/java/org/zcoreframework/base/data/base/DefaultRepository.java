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

import java.util.List;

public interface DefaultRepository extends DefaultJpaRepository, CommonRepository {

    <T> Long count(Class<T> clazz);

    <T> T loadModel(Class<T> clazz, IdField<?> idField);

    <T, ID> T loadById(Class<T> clazz, ID id);
    
    <T> Long count(Class<T> clazz, CriteriaCondition criteriaCondition); 
    
    <T> List<T> find(Class<T> clazz, CriteriaCondition criteriaCondition);

    <T> int delete(Class<T> clazz, Long id);

}
