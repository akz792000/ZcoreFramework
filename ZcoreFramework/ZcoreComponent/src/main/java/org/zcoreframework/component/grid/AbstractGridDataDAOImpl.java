/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.grid;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.support.PagedListHolder;
import org.zcoreframework.base.dao.AbstractPyramidDAOImpl;
import org.zcoreframework.base.type.ColumnType;
import org.zcoreframework.component.dateconverter.DateConverter;
import org.zcoreframework.component.dateconverter.DateConverterParam.DateType;

public abstract class AbstractGridDataDAOImpl<M extends GridModel> extends AbstractPyramidDAOImpl<M> implements GridDAO<M> {

	public enum OperationType {
		FILTER, ORDER, PAGING
	}

	private Set<OperationType> operationTypes = new HashSet<>(Arrays.asList(OperationType.FILTER, OperationType.ORDER, OperationType.PAGING));

	private double size = -1;

	private List<Object> data;

	public Set<OperationType> getOperationTypes() {
		return operationTypes;
	}

	@Override
	public void setOperationTypes(Set<OperationType> operationTypes) {
		this.operationTypes = operationTypes;
	}

	public double getSize() {
		return size;
	}

	@Override
	public void setSize(double size) {
		this.size = size;
	}

	public List<Object> getData() {
		return data;
	}

	@Override
	public void setData(List<Object> data) {
		this.data = data;
	}

	public void initializeList() {

		// filter column
		if (operationTypes != null && operationTypes.contains(OperationType.FILTER)) {
			if (!StringUtils.isEmpty(getModel().getClientModel().getFilterColumnsValue())) {
				String[] filters = getModel().getClientModel().getFilterColumnsValue().split(",");
				if (filters.length != 0) {
					for (int row = data.size() - 1; row >= 0; row--) {
					    Object dataObject = data.get(row);
                        Object[] col;
					    if (dataObject instanceof List) {
                            col = ((List) dataObject).toArray();
                            col = (Object[]) col;
                        } else {
                            col = (Object[]) dataObject;
                        }
						for (int cnt = 0; cnt < filters.length; cnt++) {
							String filter = filters[cnt];
							if (!filter.isEmpty()) {
								boolean removeFlag = false;
								GridColumn column = getModel().getColumns().get(cnt);
								switch (column.getType()) {
								case NUMERIC:
								case BOOLEAN:
								case STRING:
								default:
									String strValue = "";
									if (column.getType() == ColumnType.NUMERIC) {
										NumberFormat numberFormat = NumberFormat.getInstance();
										numberFormat.setGroupingUsed(false);
										strValue = numberFormat.format(col[cnt]);
									} else
										strValue = (col[cnt] == null) ? "" : col[cnt].toString();
									String filterValue = filters[cnt];
									if (!strValue.contains(filterValue)){
										removeFlag = true;
									}
									break;
								case GREGORIAN_DATE:
								case PERSIAN_DATE:
									Date valuePersianDate = (Date) col[cnt];
									if (valuePersianDate == null) {
										removeFlag = true;
									} else {
										Date fgcp = DateConverter.getInstance(
												column.getType().equals(ColumnType.PERSIAN_DATE) ? DateType.PERSIAN : DateType.GREGORIAN, column.getMask(),
												filter.toString()).getTime();
										if (fgcp.getTime() != valuePersianDate.getTime()) {
											removeFlag = true;
										}
									}
									break;
								}
								if (removeFlag) {
									data.remove(row);
									break;
								}
							}
						}
					}
				}
			}
		}

		// order data
		if (operationTypes != null && operationTypes.contains(OperationType.ORDER)) {
			if (!StringUtils.isEmpty(getModel().getClientModel().getOrderByItem())) {
				Collections.sort(data, new GridListHolderComparator(getModel().getColumnNo(getModel().getClientModel().getOrderByItem()), (getModel()
						.getClientModel().getOrderByItemSort().equals("asc")), getModel().getColumn(getModel().getClientModel().getOrderByItem()).getType()));
			}
		}
	}

	@Override
	public Boolean isNotResultable() {
		return false;
	}

	@Override
	public Double getResultSize() {
		return size == -1 ? (data == null ? 0 : (double) data.size()) : size;
	}

	@Override
	public List<? extends Object> getResultList() {
		// initialize list
		initializeList();

		// get page list
		PagedListHolder<Object> pagedListHolder = new PagedListHolder<Object>(data == null ? new ArrayList<>() : data);
		pagedListHolder.setPageSize(data == null ? 0 : data.size());

		// set page and size
		if (operationTypes != null && operationTypes.contains(OperationType.PAGING)) {
			if (getModel().getClientModel().getLimitPage() > 0) {
				pagedListHolder.setPageSize(getModel().getClientModel().getLimitPage());
				pagedListHolder.setPage(getModel().getClientModel().getActivePage() - 1);
			}
		}

		return pagedListHolder.getPageList();
	}

}