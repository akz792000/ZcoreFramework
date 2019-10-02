/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.domain.descriptor;

import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.history.HistoryPolicy;
import org.eclipse.persistence.internal.expressions.SQLInsertStatement;
import org.eclipse.persistence.internal.helper.DatabaseTable;
import org.eclipse.persistence.internal.queries.StatementQueryMechanism;
import org.eclipse.persistence.internal.sessions.AbstractRecord;
import org.eclipse.persistence.mappings.DatabaseMapping.WriteType;
import org.eclipse.persistence.queries.DeleteObjectQuery;
import org.eclipse.persistence.queries.ModifyQuery;
import org.eclipse.persistence.queries.ObjectLevelModifyQuery;

public class HistoryPolicyImpl extends HistoryPolicy {

	private static final long serialVersionUID = 1L;

	public void postDelete(ModifyQuery deleteQuery) {
		logicalDelete(deleteQuery, "DELETE");
	}

	public void postUpdate(ObjectLevelModifyQuery writeQuery) {
		logicalInsert(writeQuery, true, "UPDATE");
	}

	public void postInsert(ObjectLevelModifyQuery writeQuery) {
		logicalInsert(writeQuery, false, "INSERT");
	}

	@SuppressWarnings("unchecked")
	public void logicalDelete(ModifyQuery writeQuery, String action) {
		// get modify row
		ClassDescriptor descriptor = writeQuery.getDescriptor();
		AbstractRecord originalModifyRow = writeQuery.getModifyRow();
		AbstractRecord modifyRow = descriptor.getObjectBuilder().buildRow(((DeleteObjectQuery) writeQuery).getObject(), writeQuery.getSession(),
				WriteType.UPDATE);
		modifyRow.putAll(originalModifyRow);

		// create update mechanism
		StatementQueryMechanism updateMechanism = new StatementQueryMechanism(writeQuery);
		for (int i = 0; i < getHistoricalTables().size(); i++) {

			// get table
			DatabaseTable table = getHistoricalTables().get(i);
			if (!checkWastedVersioning(originalModifyRow, table)) {
				continue;
			}

			// add field
			modifyRow.add(getStart(i), getCurrentTime(writeQuery.getSession()));
			modifyRow.add(getEnd(i), action);

			// create insert statement
			SQLInsertStatement insertStatement = new SQLInsertStatement();
			insertStatement.setTable(table);

			// add to update mechanism
			updateMechanism.getSQLStatements().add(insertStatement);
		}

		// issue update
		if (updateMechanism.hasMultipleStatements()) {
			writeQuery.setTranslationRow(modifyRow);
			writeQuery.setModifyRow(modifyRow);
			updateMechanism.updateObject();
		}
	}

	@SuppressWarnings("unchecked")
	public void logicalInsert(ObjectLevelModifyQuery writeQuery, boolean isUpdate, String action) {
		// get modify row
		ClassDescriptor descriptor = getDescriptor();
		AbstractRecord originalModifyRow = writeQuery.getModifyRow();
		AbstractRecord modifyRow = null;
		if (isUpdate) {
			modifyRow = descriptor.getObjectBuilder().buildRow(writeQuery.getObject(), writeQuery.getSession(), WriteType.UPDATE);
			modifyRow.putAll(originalModifyRow);
		} else {
			modifyRow = originalModifyRow;
		}

		// create insert mechanism
		StatementQueryMechanism insertMechanism = new StatementQueryMechanism(writeQuery);
		for (int i = 0; i < getHistoricalTables().size(); i++) {

			// get table
			DatabaseTable table = getHistoricalTables().get(i);
			if (isUpdate && !checkWastedVersioning(originalModifyRow, table)) {
				continue;
			}

			// add field
			modifyRow.add(getStart(i), getCurrentTime(writeQuery.getSession()));
			modifyRow.add(getEnd(i), action);

			// create insert statement
			SQLInsertStatement insertStatement = new SQLInsertStatement();
			insertStatement.setTable(table);

			// add to insert mechanism
			insertMechanism.getSQLStatements().add(insertStatement);
		}

		// issue insert
		if (insertMechanism.hasMultipleStatements()) {
			writeQuery.setTranslationRow(modifyRow);
			writeQuery.setModifyRow(modifyRow);
			insertMechanism.insertObject();
		}
	}

}