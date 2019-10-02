/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.grid;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.text.MaskFormatter;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.StringUtils;
import org.zcoreframework.base.component.AbstractPyramidComponentImpl;
import org.zcoreframework.base.component.AjaxClientable;
import org.zcoreframework.base.component.Component;
import org.zcoreframework.base.component.ResponseResult;
import org.zcoreframework.base.dao.AbstractPyramidDAOImpl;
import org.zcoreframework.base.event.ResultEvent;
import org.zcoreframework.base.exception.BaseException;
import org.zcoreframework.base.type.ColumnType;
import org.zcoreframework.base.util.ApplicationContextUtils;
import org.zcoreframework.base.util.JsonUtils;
import org.zcoreframework.base.util.MessageSourceUtils;
import org.zcoreframework.component.dateconverter.DateConverter;
import org.zcoreframework.component.dateconverter.DateConverterParam.DateType;
import org.zcoreframework.component.entryset.EntrySetSimple;
import org.zcoreframework.component.entryset.EntrySetSimpleList;
import org.zcoreframework.component.listvalue.ListValue;

public abstract class AbstractGridImpl<D extends AbstractPyramidDAOImpl<M>, M extends GridModel> extends AbstractPyramidComponentImpl<D, M> implements Grid, AjaxClientable {

	private List<List<Object>> selectedRows;

	private List<GridResultEvent> events = new ArrayList<>();

	@Override
	public List<List<Object>> getSelectedRows() {
		return selectedRows;
	}

	public void setSelectedRows(List<List<Object>> selectedRows) {
		this.selectedRows = selectedRows;
	}

	@Override
	public List<GridResultEvent> getEvents() {
		return events;
	}

	@Override
	public void setEvent(List<GridResultEvent> events) {
		this.events = events;
	}

	@Override
	public void addEvent(GridResultEvent event) {
		this.events.add(event);
	}

	protected void processEventHanlder(GridResultModelImpl model) throws BaseException {
		if (model != null) {
			for (ResultEvent<GridResultModelImpl> event : events) {
				event.onGetResult(model);
			}
		}
	}

	public List<Object> getFilterColumnsString() {
		List<Object> result = new ArrayList<Object>();
		for (int i = 0; i < getColumns().size(); i++) {
			Map<String, Object> data = new LinkedHashMap<>();
			GridColumn column = getColumns().get(i);
			ListValue filter = column.getFilter();
			String filterBeanName = column.getFilterBean();
			String mask = column.getMask();
			// filter
			if (filter != null) {
				data.put("type", "select");
				Map<Object, Object> selectData = new LinkedHashMap<>();
				EntrySetSimpleList entrySetSimpleList = filter.getItem();
				for (EntrySetSimple entrySetSimple : entrySetSimpleList.getMember()) {
					selectData.put(entrySetSimple.getValue(), entrySetSimple.getLabel());
				}
				data.put("data", selectData);
			// filter bean
			} else if (!StringUtils.isEmpty(filterBeanName)) {
				ListValue filterBean = (ListValue) ApplicationContextUtils.getApplicationContext().getBean(column.getFilterBean());
				if (filterBean != null) {
					data.put("type", "select");
					Map<Object, Object> selectData = new LinkedHashMap<>();
					EntrySetSimpleList entrySetSimpleList = filterBean.getItem();
					for (EntrySetSimple entrySetSimple : entrySetSimpleList.getMember()) {
						String label = entrySetSimple.getLabel(); 
						label = MessageSourceUtils.getMessage(label);
						selectData.put(entrySetSimple.getValue(), label);
					}
					data.put("data", selectData);
				}
			// mask
			} else if (!StringUtils.isEmpty(mask)) {
				switch (column.getType()) {
				case GREGORIAN_DATE:
				case PERSIAN_DATE:
					data.put("type", "date");
					data.put("data", mask.toLowerCase().replaceAll("[A-Za-z]", "9"));
					break;
				case NUMERIC:
					data.put("type", "numeric");
					data.put("data", mask);
					break;
				case STRING:
					data.put("type", "string");
					data.put("data", mask);
					break;
				default:
					break;
				}
			// numeric
			} else {
				switch (column.getType()) {
				case NUMERIC:
					data.put("type", "numeric");
					data.put("data", "###");
					break;
				case TABLE:
					data.put("type", "table");
					List<String> listColumn = new ArrayList<>();
					for (GridColumn gridColumn : column.getColumns()) {
						listColumn.add(gridColumn.getName());
					}
					data.put("data", listColumn);					
					break;
				default:
					break;
				}
			}
			if (data.size() != 0)
				result.add(data);
			else
				result.add(null);
		}
		return result;
	}

	@Override
	public List<GridColumn> getColumns() {
		return ((GridModel) getModel()).getColumns();
	}

	@Override
	public GridColumn getColumnByName(String name) {
		for (GridColumn column : getColumns()) {
			if (column.getName().equals(name)) {
				return column;
			}
		}
		return null;
	}
		
	protected static Object typeCast(Object value, GridColumn column) {
		switch (column.getType()) {
		case NUMERIC: return Long.parseLong(value.toString());
		case BOOLEAN: return Boolean.parseBoolean(value.toString());
		case PERSIAN_DATE: return DateConverter.getInstance(DateType.PERSIAN, column.getMask(), value.toString()).getTime();
		case GREGORIAN_DATE: return DateConverter.getInstance(DateType.GREGORIAN, column.getMask(), value.toString()).getTime();
		default: return value;
		}
	}
	
	@SuppressWarnings("unchecked")
	private List<Map<String, Object>> getSelectedRowsByName(List<List<Object>> rows, List<GridColumn> columns) {
		List<Map<String, Object>> res = new ArrayList<>();
		if (rows != null) {
			for (List<Object> list : rows) {
				Map<String, Object> e = new LinkedHashMap<>();
				for (int i = 0; i < list.size(); i++) {
					GridColumn column = columns.get(i);
					Object value = list.get(i);
					if (value == null) {
						e.put(column.getName(), value);
					} else {
						if (column.getType().equals(ColumnType.TABLE)) {							
							e.put(column.getName(), getSelectedRowsByName((List<List<Object>>) value, column.getColumns()));
						} else {												
							e.put(column.getName(), typeCast(value, column));
						}
					}
				}
				res.add(e);
			}
		}
		return res;	
	}

	@Override
	public List<Map<String, Object>> getSelectedRowsByName() {
		return getSelectedRowsByName(selectedRows, getColumns());
	}
	
	protected static List<Object> getTableResultList(Object obj, List<GridColumn> columns) {
		List<Object> result = new ArrayList<Object>();								
		// set
		if (obj instanceof Set<?>) {
			Set<?> set = (Set<?>) obj;
			Iterator<?> iterator = set.iterator();
			while (iterator.hasNext()) {
				BeanWrapper beanWrapper = new BeanWrapperImpl(iterator.next());	
				List<Object> items = new ArrayList<>();
				for (GridColumn column : columns) {
					Object value;
					if (column.getStaticValue().isEmpty()) {
						value = getFormatResult(beanWrapper.getPropertyValue(column.getName()), column);
					} else {
						value = typeCast(column.getStaticValue(), column);
					}
					items.add(value);
				}	
				result.add(items);
			}
		}
		return result;
	}
	
	public static Object getFormatResult(Object obj, GridColumn column) {
		Object result;
		if (column.getType().equals(ColumnType.TABLE)) {						
			result = getTableResultList(obj, column.getColumns());
		} else {
			if (!StringUtils.isEmpty(column.getMask())) {
				switch (column.getType()) {
				case GREGORIAN_DATE:
					DateFormat gregorianFormat = new SimpleDateFormat(column.getMask());
					result = obj == null ? obj : gregorianFormat.format(obj);
					break;
				case PERSIAN_DATE:
					DateConverter dc = new DateConverter();
					dc.setFormat(column.getMask());
					dc.setTime((Date) obj);
					result = obj == null ? obj : dc.getFormatDate();
					break;
				/*
				 * done in client base case NUMERIC: DecimalFormat
				 * decimalFormat = new DecimalFormat(column.getMask());
				 * resultObjects.add(decimalFormat.format(obj)); break;
				 */
				case STRING:
					try {
						result = obj;
						if (result != null) {
							MaskFormatter maskFormatter = new MaskFormatter(column.getMask());
							maskFormatter.setPlaceholderCharacter(',');						
							result = maskFormatter.valueToString(result);
						}
					} catch (ParseException e) {
						result = obj;
					}
					break;
				default:
					result = obj;
					break;
				}
			} else {
				result = obj;
			}
		}		
		return result;
	}	
	
	public static List<? extends Object> getFormatResultList(List<?> list, List<GridColumn> columns) {
		if (list.size() > 0) {
			List<Object> resultList = new ArrayList<Object>(list.size());
			if (list.get(0) instanceof List) {
				for (Object objects : list) {
					@SuppressWarnings("unchecked")
					List<Object> listObjects = (List<Object>) objects;
					List<Object> resultObjects = new ArrayList<Object>();
					for (int i = 0; i < listObjects.size(); i++) {
						resultObjects.add(getFormatResult(listObjects.get(i), columns.get(i)));
					}
					resultList.add(resultObjects);
				}								
			} else {
				for (Object objects : list) {
					Object[] arrayObjects = ((Object[]) objects);
					List<Object> resultObjects = new ArrayList<Object>();
					for (int i = 0; i < arrayObjects.length; i++) {
						resultObjects.add(getFormatResult(arrayObjects[i], columns.get(i)));
					}
					resultList.add(resultObjects);
				}
			}
			return resultList;
		} else {
			return list;
		}
	}

	@SuppressWarnings("unchecked")
	public List<? extends Object> getFormatResult(GridDAO<? extends GridModel> dao) {
		List<GridColumn> columns = dao.getModel().getColumns();
		List<? extends Object> resultList = getFormatResultList((List<Object[]>) dao.getResultList(), columns);
		if (dao.getModel().getClientModel().getActivePage() == 1)
			return resultList;
		else {
			if (resultList.size() == 0) {
				dao.getModel().getClientModel().setActivePage(1);
				return getFormatResultList((List<Object[]>) dao.getResultList(), columns);
			} else {
				return resultList;
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public GridResultModelImpl getResultModel() {
		// create dao
		GridDAO<? extends GridModel> dao = (GridDAO<? extends GridModel>) getDao();
		dao.prepare();
		if (dao.isNotResultable()) {
			return null;
		}
		// create model map
		List<? extends Object> data = getFormatResult(dao);
		GridResultModelImpl model = new GridResultModelImpl();
		// set data
		model.setData(data);
		// set total count
		if (dao.getModel().getClientModel().getLimitPage() > 0) {
			model.setTotalCount(dao.getResultSize());
		} else {
			model.setTotalCount((double) data.size());
		}
		return model;
	}

	@Override
	public Object parseValue(String text) {
		return JsonUtils.toListOfList(text);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bindValue(Object object) {
		setSelectedRows((List<List<Object>>) object);
	}

	@Override
	public List<Object> data() throws BaseException {
		List<Object> result = new ArrayList<Object>();

		// add columns
		List<String> columns = new ArrayList<>();
		for (GridColumn column : getColumns()) {
			columns.add(column.getName());
		}
		result.add(columns);

		// add filter
		result.add(getFilterColumnsString());

		// get result model
		GridResultModelImpl resultModel = getResultModel();

		// process event handler
		processEventHanlder(resultModel);

		// add model
		result.add(resultModel != null ? resultModel.model() : null);

		// return result
		return result;
	}

	@Override
	public Object value() {
		return getSelectedRows();
	}

	@Override
	public Component getClientModel() {
		return ((GridModel) getModel()).getClientModel();
	}

	@Override
	public ResponseResult partial() throws BaseException {
		// get result model
		GridResultModelImpl resultModel = getResultModel();

		// process event handler
		processEventHanlder(resultModel);

		// return response result
		return new ResponseResult(resultModel != null ? resultModel.model() : null);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public GridDAO<GridModel> getDataAccess() {		
		return (GridDAO<GridModel>) getDao();
	}

}