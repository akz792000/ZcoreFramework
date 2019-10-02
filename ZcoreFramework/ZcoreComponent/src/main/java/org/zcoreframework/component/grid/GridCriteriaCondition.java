/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.grid;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.zcoreframework.base.data.CriteriaColumn;
import org.zcoreframework.base.data.CriteriaColumn.OrderType;
import org.zcoreframework.base.data.CriteriaCondition;
import org.zcoreframework.base.data.CriteriaExecutor;
import org.zcoreframework.base.data.RepositoryFactoryBean;
import org.zcoreframework.base.data.base.DefaultRepository;
import org.zcoreframework.component.dateconverter.DateConverter;
import org.zcoreframework.component.dateconverter.DateConverterParam.DateType;

public class GridCriteriaCondition extends CriteriaCondition {

	private final Grid grid;

	public GridCriteriaCondition(Grid grid) {
		this.grid = grid;
	}

	public GridCriteriaCondition prepare() {
		// set filter
		if (!StringUtils.isEmpty(grid.getModel().getClientModel().getFilterColumnsValue())) {
			String[] filters = grid.getModel().getClientModel().getFilterColumnsValue().split(",");
			if (filters.length != 0) {
				for (int cnt = 0; cnt < filters.length; cnt++) {
					String filter = filters[cnt];
					if (!filter.isEmpty()) {
						GridColumn column = grid.getModel().getColumns().get(cnt);
						switch (column.getType()) {
						case NUMERIC:
							getColumns().add(new CriteriaColumn(column.getName(), Long.class, Long.parseLong(filter)));
							break;
						case BOOLEAN:
							getColumns().add(new CriteriaColumn(column.getName(), Boolean.class, Boolean.parseBoolean(filter)));
							break;
						case GREGORIAN_DATE:
							DateConverter gc = DateConverter.getInstance(DateType.GREGORIAN, column.getMask(), filter.toUpperCase());
							getColumns().add(new CriteriaColumn(column.getName(), Date.class, gc.getCalendar().getTime()));
							break;
						case PERSIAN_DATE:
							DateConverter pc = DateConverter.getInstance(DateType.PERSIAN, column.getMask(), filter.toUpperCase());
							getColumns().add(new CriteriaColumn(column.getName(), Date.class, pc.getCalendar().getTime()));
							break;
						case STRING:
						default:
							getColumns().add(new CriteriaColumn(column.getName(), String.class, '%' +filter + '%'));
							break;
						}
					}
				}
			}
		}
		return this;
	}

	public GridCriteriaCondition remove(List<String> removeColumns) {
		if (removeColumns != null) {
			getColumns().removeIf(p -> removeColumns.indexOf(p.getName()) != -1);
		}
		return this;
	}

	public GridCriteriaCondition order() {
		if (!StringUtils.isEmpty(grid.getModel().getClientModel().getOrderByItem())) {
			String order = grid.getModel().getClientModel().getOrderByItem();
			GridColumn column = grid.getModel().getColumn(order);
			getColumns().add(new CriteriaColumn(column.getName(), OrderType.valueOf(grid.getModel().getClientModel().getOrderByItemSort().toUpperCase())));
		}
		return this;
	}

	public GridCriteriaCondition paging() {
		setFirstResult((grid.getModel().getClientModel().getActivePage() - 1) * grid.getModel().getClientModel().getLimitPage());
		setMaxResults(grid.getModel().getClientModel().getLimitPage());
		return this;
	}
	
	public <T> Object execute(Class<T> clazz, CriteriaExecutor<T> executor) {
		// create repository
		DefaultRepository repository = (DefaultRepository) RepositoryFactoryBean.getRepository(DefaultRepository.class, null);

		// grid get data access
		GridDAO<GridModel> dao = grid.getDataAccess();

		// set operation type
		dao.setOperationTypes(null);

		// set size
		dao.setSize(repository.count(clazz, this));

		// set data
		List<Object> result = new ArrayList<>();
		for (T entity : (List<T>) repository.find(clazz, this)) {
            Object object = executor.invoke(entity);
            if (object instanceof List) {
                result.add(((List) object).toArray());
            } else {
                result.add(grid.getModel().getDataObject(object));
            }
		}
		dao.setData(result);
		return result;
	}

}
