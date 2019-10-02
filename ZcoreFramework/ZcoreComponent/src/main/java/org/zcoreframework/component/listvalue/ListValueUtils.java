/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.listvalue;

import java.util.List;

import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.util.StringUtils;
import org.zcoreframework.base.model.PropertyModel;
import org.zcoreframework.component.entryset.EntrySet;
import org.zcoreframework.component.entryset.EntrySetSimple;
import org.zcoreframework.component.entryset.EntrySetSimpleList;

public class ListValueUtils {

	public static Boolean addable(EntrySetSimple entrySetSimple, String expression) {
		Boolean result = true;
		if (!StringUtils.isEmpty(expression)) {
			SpelExpressionParser expressionParser = new SpelExpressionParser();
			result = expressionParser.parseExpression(expression).getValue(entrySetSimple, Boolean.class);
		}
		return result;
	}

	public static ListValue valueOf(List<EntrySet> entrySets, String method, String expression) {
		ListValue result = new ListValueStatic();
		EntrySetSimpleList entrySetSimpleList = new EntrySetSimpleList();
		for (EntrySet entrySet : entrySets) {
			String label = StringUtils.isEmpty(method) ? entrySet.getEntryLabel() : entrySet.getEntryMethod(method);
			Object value = entrySet.getEntryValue();
			EntrySetSimple entrySetSimple = new EntrySetSimple(label, value, false, entrySet);
			if (addable(entrySetSimple, expression)) {
				entrySetSimpleList.addMember(entrySetSimple);
			}
		}
		result.setItem(entrySetSimpleList);
		return result;
	}

	public static ListValue valueOf(List<PropertyModel> propertyModels) {
		ListValue result = new ListValueStatic();
		EntrySetSimpleList entrySetSimpleList = new EntrySetSimpleList();
		for (PropertyModel propertyModel : propertyModels) {
			entrySetSimpleList.addMember(new EntrySetSimple(propertyModel.getName(), propertyModel.getCode(), (boolean) propertyModel.getObject()));
		}
		result.setItem(entrySetSimpleList);
		return result;
	}

}
