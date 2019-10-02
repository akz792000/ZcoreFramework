/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.grid;

import java.util.List;
import java.util.Map;

import org.zcoreframework.base.component.Component;

public interface Grid extends Component {

	List<List<Object>> getSelectedRows();

	List<Map<String, Object>> getSelectedRowsByName();

	void setParameter(String key, String value);

	GridResultModelImpl getResultModel();

	List<GridColumn> getColumns();

	GridColumn getColumnByName(String name);

	List<GridResultEvent> getEvents();

	void setEvent(List<GridResultEvent> events);

	void addEvent(GridResultEvent event);

	GridModel getModel();
	
	GridDAO<GridModel> getDataAccess();

}
