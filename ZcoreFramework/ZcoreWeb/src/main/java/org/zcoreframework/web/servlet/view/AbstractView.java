/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.web.servlet.view;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.CollectionUtils;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.support.RequestContext;

public abstract class AbstractView extends WebApplicationObjectSupport implements View {

	private String contentType = "application/xml";
	
	private String characterEncoding = "UTF-8";

	private String requestContextAttribute;

	private final Map<String, Object> staticAttributes = new HashMap<String, Object>();

	private boolean exposePathVariables = true;

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getContentType() {
		return this.contentType;
	}
	
	public String getCharacterEncoding() {
		return characterEncoding;
	}

	public void setCharacterEncoding(String characterEncoding) {
		this.characterEncoding = characterEncoding;
	}

	public void setRequestContextAttribute(String requestContextAttribute) {
		this.requestContextAttribute = requestContextAttribute;
	}

	public String getRequestContextAttribute() {
		return this.requestContextAttribute;
	}

	public void setAttributes(Properties attributes) {
		CollectionUtils.mergePropertiesIntoMap(attributes, this.staticAttributes);
	}

	public void setAttributesMap(Map<String, ?> attributes) {
		if (attributes != null) {
			for (Map.Entry<String, ?> entry : attributes.entrySet()) {
				addStaticAttribute(entry.getKey(), entry.getValue());
			}
		}
	}

	public Map<String, Object> getAttributesMap() {
		return this.staticAttributes;
	}

	public void addStaticAttribute(String name, Object value) {
		this.staticAttributes.put(name, value);
	}


	public Map<String, Object> getStaticAttributes() {
		return Collections.unmodifiableMap(this.staticAttributes);
	}

	public void setExposePathVariables(boolean exposePathVariables) {
		this.exposePathVariables = exposePathVariables;
	}

	public boolean isExposePathVariables() {
		return exposePathVariables;
	}

	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {	
		Map<String, Object> mergedModel = createMergedOutputModel(model, request, response);
		renderMergedOutputModel(mergedModel, request, response);
	}


	protected Map<String, Object> createMergedOutputModel(Map<String, ?> model, HttpServletRequest request,
			HttpServletResponse response) {
		@SuppressWarnings("unchecked")
		Map<String, Object> pathVars = this.exposePathVariables ?
			(Map<String, Object>) request.getAttribute(View.PATH_VARIABLES) : null;

		// Consolidate static and dynamic model attributes.
		int size = this.staticAttributes.size();
		size += (model != null) ? model.size() : 0;
		size += (pathVars != null) ? pathVars.size() : 0;
		Map<String, Object> mergedModel = new HashMap<String, Object>(size);
		mergedModel.putAll(this.staticAttributes);
		if (pathVars != null) {
			mergedModel.putAll(pathVars);
		}
		if (model != null) {
			mergedModel.putAll(model);
		}

		// Expose RequestContext?
		if (this.requestContextAttribute != null) {
			mergedModel.put(this.requestContextAttribute, createRequestContext(request, response, mergedModel));
		}
		
		return mergedModel;
	}

	protected RequestContext createRequestContext(
			HttpServletRequest request, HttpServletResponse response, Map<String, Object> model) {
		return new RequestContext(request, response, getServletContext(), model);
	}

	protected abstract void renderMergedOutputModel(
			Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception;


}

