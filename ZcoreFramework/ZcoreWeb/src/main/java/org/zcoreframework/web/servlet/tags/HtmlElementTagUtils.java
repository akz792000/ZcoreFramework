/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.web.servlet.tags;

import javax.servlet.jsp.JspException;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.tags.form.TagWriter;

public class HtmlElementTagUtils {
	
	public static void renderMandatoryLabel(TagWriter tagWriter, String style) throws JspException {		
		tagWriter.startTag("label");
		tagWriter.writeAttribute("class", "ui-state-error-text");
		if (!StringUtils.isEmpty(style))
			tagWriter.writeAttribute("style", style);
		tagWriter.forceBlock();
		tagWriter.appendValue(" * ");
		tagWriter.endTag();			
	}

}
