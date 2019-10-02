/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.domain.descriptor;

import org.eclipse.persistence.config.DescriptorCustomizer;
import org.eclipse.persistence.descriptors.ClassDescriptor;

public class HistoryCustomizer implements DescriptorCustomizer {

	private static final String FIELD_PREFIX = "HISTORY_";
	private static final String START = FIELD_PREFIX + "DATE";
	private static final String END = FIELD_PREFIX + "ACTION";
	private static final String TABLE_POSFIX = "_H";

	public void customize(ClassDescriptor descriptor) {
		HistoryPolicyImpl policy = new HistoryPolicyImpl();
		policy.addHistoryTableName(descriptor.getTableNames().size()==0 ? null :( descriptor.getTableNames().get(0).toString()+ TABLE_POSFIX));
		policy.addStartFieldName(START);
		policy.addEndFieldName(END);
		descriptor.setHistoryPolicy(policy);
	}

}