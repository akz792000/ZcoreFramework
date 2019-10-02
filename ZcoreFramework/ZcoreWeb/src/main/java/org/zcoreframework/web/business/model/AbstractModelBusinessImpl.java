/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.web.business.model;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.zcoreframework.base.beans.factory.InitializeAware;
import org.zcoreframework.base.data.RepositoryFactoryBean;
import org.zcoreframework.base.data.base.GenericRepository;
import org.zcoreframework.base.exception.BaseException;
import org.zcoreframework.base.exception.ClientException;
import org.zcoreframework.base.model.Actionable;
import org.zcoreframework.base.model.IdField;
import org.zcoreframework.base.util.ApplicationContextUtils;
import org.zcoreframework.base.util.ReflectionUtils;
import org.zcoreframework.mapping.core.BeanMapper;
import org.zcoreframework.mapping.core.BeanMapper.MappingDirection;

public abstract class AbstractModelBusinessImpl<M extends Actionable<ID>, E, ID extends Serializable> implements BusinessModel<M>, InitializeAware {

	private final BeanMapper beanMapper;

	private final M model;

	private final Type[] types;

	private final GenericRepository<E, ID> repository;

	@SuppressWarnings("unchecked")
	public AbstractModelBusinessImpl() {
		// bean mapper
		beanMapper = (BeanMapper) ApplicationContextUtils.getBean("beanMapper");

		// get types
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();

		// types
		types = genericSuperclass.getActualTypeArguments();

		// model
		model = ApplicationContextUtils.getBeanFactory().createBean((Class<M>) types[0]);

		// repository
		repository = (GenericRepository<E, ID>) RepositoryFactoryBean.getRepository(GenericRepository.class, (Class<E>) types[1]);
	}

	public GenericRepository<E, ID> getRepository() {
		return repository;
	}

	public M getModel() {
		return model;
	}

	@Override
	public void loadModel() throws BaseException {
		// load entity
		E entity = repository.loadModel(model.getIdField());

		// mapping entity to model
		try {
			beanMapper.mapping(model, entity, MappingDirection.INDIRECTION);
		} catch (Exception e) {
			ReflectionUtils.handleReflectionException(e);
		}

		// prepare model
		prepareModel(model, entity);
	}

	public void prepareModel(M model, E entity) throws BaseException {
		// prepare model
	}

	public void prepareEntity(M model, E entity) throws BaseException {
		// prepare entity
	}

	public void validate(M model, E entity) throws BaseException {
		// validate
	}

	public Object invoke(M model, E entity) {
		// invoke action
		try {
			String action = model.getAction();
			if (!StringUtils.isEmpty(action)) {
				Method method = ReflectionUtils.findMethod(repository.getClass(), model.getAction(), new Class<?>[] { Object.class });
				method.invoke(repository, new Object[] { entity });

				return BeanUtils.getPropertyDescriptor(entity.getClass(), model.getIdField().getName()).getReadMethod().invoke(entity);
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			ReflectionUtils.handleReflectionException(e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object execute() throws Exception {
		IdField<ID> idField = model.getIdField();

		// initialize entity
		E entity = null;
		if (idField.getValue() == null) {
			try {
				entity = ((Class<E>) types[1]).newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				ReflectionUtils.handleReflectionException(e);
			}
		} else {
			entity = repository.loadModel(idField);
			if (entity == null) {
				ClientException.deliver("Entity does not exist", new Object[] { types[1].getClass().getSimpleName() });
			}
		}

		// validate
		if (model.getValidate()) {
			validate(model, entity);
		}

		// mapping model to entity
		try {
			beanMapper.mapping(model, entity, MappingDirection.DIRECTION);
		} catch (Exception e) {
			ReflectionUtils.handleReflectionException(e);
		}

		// prepare entity
		prepareEntity(model, entity);

		// invoke
		return invoke(model, entity);

	}

}