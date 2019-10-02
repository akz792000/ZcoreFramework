package org.sample.validation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.zcoreframework.validation.annotation.Email;
import org.zcoreframework.validation.annotation.Expression;
import org.zcoreframework.validation.annotation.FixLength;
import org.zcoreframework.validation.annotation.FixSize;
import org.zcoreframework.validation.annotation.Length;
import org.zcoreframework.validation.annotation.Max;
import org.zcoreframework.validation.annotation.MaxLength;
import org.zcoreframework.validation.annotation.MaxSize;
import org.zcoreframework.validation.annotation.Examine;
import org.zcoreframework.validation.annotation.Min;
import org.zcoreframework.validation.annotation.MinLength;
import org.zcoreframework.validation.annotation.MinSize;
import org.zcoreframework.validation.annotation.NotBlank;
import org.zcoreframework.validation.annotation.NotEmpty;
import org.zcoreframework.validation.annotation.NotNull;
import org.zcoreframework.validation.annotation.Range;
import org.zcoreframework.validation.annotation.RegExp;
import org.zcoreframework.validation.annotation.Size;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class ExampleConfigurationTests {
		
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private Validator validator;

	public boolean hasErrors(Object object) {
		BindException exception = new BindException(object, object.getClass().getSimpleName());
		BindingResult result = exception.getBindingResult();
		validator.validate(object, result);
		return result.hasErrors();		
	}
	
	public void checkValidateAnnotation(Object errorVal, Class<?> clazz) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		ValidateObject validateObject = applicationContext.getBean(ValidateObject.class);
		//--> we don't have error
		Assert.isTrue(!hasErrors(validateObject));
		//--> set error value then we have error value
		String fieldName = clazz.getSimpleName().substring(0, 1).toLowerCase() + clazz.getSimpleName().substring(1);
		Field field = ReflectionUtils.findField(validateObject.getClass(), fieldName);
		field.setAccessible(true);
		field.set(validateObject, errorVal);
		Assert.isTrue(hasErrors(validateObject));
	}
		
	@Test
	public void notNull() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {	
		checkValidateAnnotation(null, NotNull.class);
	}
	
	@Test
	public void notBlank() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {	
		checkValidateAnnotation("", NotBlank.class);
	}	
	
	@Test
	public void minLength() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {	
		checkValidateAnnotation("Ali", MinLength.class);
	}	
	
	@Test
	public void maxLength() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {	
		checkValidateAnnotation("Ali Zandi", MaxLength.class);
	}	
	
	@Test
	public void length() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {	
		checkValidateAnnotation("Ali Karimizandi", Length.class);
	}	
	
	@Test
	public void fixLength() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {	
		checkValidateAnnotation("", FixLength.class);
	}	
		
	@Test
	public void notEmpty() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {	
		checkValidateAnnotation(new ArrayList<>(), NotEmpty.class);
	}	
		
	@Test
	public void minSize() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {	
		checkValidateAnnotation(Arrays.asList("ali"), MinSize.class);
	}		
		
	@Test
	public void maxSize() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {	
		checkValidateAnnotation(Arrays.asList("ali", "karimi", "zandi"), MaxSize.class);
	}		
	
	@Test
	public void size() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {	
		checkValidateAnnotation(Arrays.asList("ali", "karimi", "zandi"), Size.class);
	}	
	
	@Test
	public void fixSize() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {	
		checkValidateAnnotation(Arrays.asList("ali", "karimi", "zandi"), FixSize.class);
	}	
	
	@Test
	public void min() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {	
		checkValidateAnnotation(99, Min.class);
	}	
	
	@Test
	public void max() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {	
		checkValidateAnnotation(100.11, Max.class);
	}	
	
	@Test
	public void range() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {	
		checkValidateAnnotation(300, Range.class);
	}		
	
	@Test
	public void expression() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {	
		checkValidateAnnotation(null, Expression.class);
	}
	
	@Test
	public void regExp() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {	
		checkValidateAnnotation("rol_ad", RegExp.class);
	}	
	
	@Test
	public void email() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {	
		checkValidateAnnotation("error@error@error.com", Email.class);
	}
	
	@Test
	public void examine() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {	
		checkValidateAnnotation(null, Examine.class);
	}		
	
}
