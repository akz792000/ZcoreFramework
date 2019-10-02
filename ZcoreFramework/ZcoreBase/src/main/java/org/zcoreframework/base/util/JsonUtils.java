/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;

public class JsonUtils {
	
	/*
	 * Yes and no: this is due Java Type Erasure. It is missing because element
	 * type information is not available to Jackson; all it sees is List<?>, and
	 * from that base type (used for finding @JsonTypeInfo) is not available.
	 * Base type must be statically accessed, to use uniforma settings, unlike
	 * actual content serializer.
	 * 
	 * So question then is how to pass the extra information needed to detect
	 * the type. There are two main alternatives:
	 * 
	 * Use a helper class like class TaskList extends ArrayList<Task> { } to
	 * "save" the type; if so, generic element type is available from
	 * super-class (one of oddities of type erasure) Construct ObjectWriter with
	 * specific type: mapper.writerWithType(listTypeConstructedViaTypeFactory
	 * ).writeValueAsString()
	 * 
	 * Note that type erasure is only problematic for root values (value
	 * directly passed to ObjectMapper). Because of this, I recommend not using
	 * Lists or Maps (or any generic types) as root values. They can be made to
	 * work, but are more hassle.
	 */	

	public static Map<String, List<Object>> model(BindingResult result, Boolean translate) {
		Map<String, List<Object>> errors = new HashMap<>();
		for (Object object : result.getAllErrors()) {
			if (object instanceof FieldError) {
				FieldError fieldError = (FieldError) object;
				Object[] args = fieldError.getArguments();
				List<Object> value = new ArrayList<>();
				String code = fieldError.getCode();
				if (translate) {
					code = MessageSourceUtils.getMessage(code);
				}
				value.add(code);
				if (args != null) {
					value.add(args);
				}
				errors.put(fieldError.getField(), value);
			}
		}
		return errors;
	}
	
	public static String encode(Object value) {
		try {
			return new ObjectMapper().writeValueAsString(value);
		} catch (JsonProcessingException e) {
			ReflectionUtils.handleReflectionException(e);
		}
		return null;
	}
	
	public static String encode(Object value, DefaultTyping dti) {
		try {
			return new ObjectMapper().enableDefaultTyping(dti).writeValueAsString(value);
		} catch (JsonProcessingException e) {
			ReflectionUtils.handleReflectionException(e);
		}
		return null;
	}		
	
	public static <T> T decode(String text, Class<T> type) {
		try {
			return new ObjectMapper().readerFor(type).readValue(text);
		} catch (IOException e) {
			ReflectionUtils.handleReflectionException(e);
		}
		return null;
	}	
	
	public static <T> T decode(String text, Class<T> type, DefaultTyping dti) {
		try {
			return new ObjectMapper().enableDefaultTyping(dti).readerFor(type).readValue(text);
		} catch (IOException e) {
			ReflectionUtils.handleReflectionException(e);
		}
		return null;
	}	
	

	public static Map<String, Object> toMap(String text) {
		if (StringUtils.isEmpty(text)) {
			return null;
		}
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.readValue(text, objectMapper.getTypeFactory().constructMapType(Map.class, String.class, Object.class));
		} catch (IOException e) {
			throw new UndeclaredThrowableException(e);
		}
	}

	public static <T> List<List<T>> toListOfList(String text) {
		if (StringUtils.isEmpty(text)) {
			return null;
		}
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.readValue(text, objectMapper.getTypeFactory().constructCollectionType(List.class, List.class));
		} catch (Exception e) {
			throw new UndeclaredThrowableException(e);
		}
	}

	public static List<Object> toList(String text) {
		if (StringUtils.isEmpty(text)) {
			return null;
		}
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.readValue(text, objectMapper.getTypeFactory().constructCollectionType(List.class, Object.class));
		} catch (Exception e) {
			throw new UndeclaredThrowableException(e);
		}
	}

	public static List<Object> toList(InputStream inputStream) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.readValue(inputStream, objectMapper.getTypeFactory().constructCollectionType(List.class, Object.class));
		} catch (Exception e) {
			throw new UndeclaredThrowableException(e);
		}
	}

}
