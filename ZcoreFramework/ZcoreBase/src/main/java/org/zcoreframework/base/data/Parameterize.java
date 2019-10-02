package org.zcoreframework.base.data;

import java.util.LinkedHashMap;
import java.util.Map;

public interface Parameterize {
	
	void register(Map<String, Parameter> parameters);
	
	default Map<String, Parameter> execute() {
		Map<String, Parameter> parameters = new LinkedHashMap<>();
		register(parameters);
		return parameters;
	};

}
