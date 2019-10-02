/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.domain.descriptor;

import org.eclipse.persistence.config.DescriptorCustomizer;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.history.HistoryPolicy;

public class HistoryDefaultCustomizer implements DescriptorCustomizer {

	private static final String FIELD_PREFIX = "HISTORY_";
	private static final String START = FIELD_PREFIX + "START";
	private static final String END = FIELD_PREFIX + "END";
	private static final String TABLE_POSFIX = "_H";

	public void customize(ClassDescriptor descriptor) {
		HistoryPolicy policy = new HistoryPolicy();
		policy.addHistoryTableName(descriptor.getTableNames().size()==0 ? null :( descriptor.getTableNames().get(0).toString()+ TABLE_POSFIX));
		policy.addStartFieldName(START);
		policy.addEndFieldName(END);
		descriptor.setHistoryPolicy(policy);
	}

}