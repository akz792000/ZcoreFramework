package org.sample.validation;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
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
import org.zcoreframework.validation.core.ExaminBeanWrapper;

@Component
@Scope("prototype")
public class ValidateObject {
	
	@NotNull
	public Object notNull = new Object();
	
	@NotBlank
	public String notBlank = "ali";
	
	@MinLength(5)
	public String minLength = "Zandi";
	
	@MaxLength(5)
	public String maxLength = "Zandi";
	
	@Length(min=5, max= 10)
	public String length = "Zandi";
	
	@FixLength(5)
	public String fixLength = "Zandi";	
	
	@NotEmpty
	public List<String> notEmpty = Arrays.asList("ali"); 
	
	@MinSize(2)
	public List<String> minSize = Arrays.asList("ali", "karimi");
	
	@MaxSize(2)
	public List<String> maxSize = Arrays.asList("ali", "karimi");
	
	@Size(min=2, max=2)
	public List<String> size = Arrays.asList("ali", "karimi");
	
	@FixSize(2)
	public List<String> fixSize = Arrays.asList("ali", "karimi");
	
	@Min(100)
	public long min = 100;
	
	@Max(100.1)
	public double max = 100.1;
	
	@Range(min=100, max=200)
	public int range = 100;	
	
	@Expression("#self != null")
	public List<String> expression = Arrays.asList("ali", "karimi");
	
	@RegExp("^ROLE_[A-Z]{1,15}")
	public String regExp = "ROLE_ADMIN";

	@Email
	public String email = "akz792000@yahoo.com";
	
	//@Examine("check")
	@Examine(clazz=ValidateObject.class, value="checkStatic")
	public String examine = "Ali Zandi";
	
	public static boolean check(String object) {
		return object != null && object.equals("Ali Zandi");
	}
	
	public static boolean checkStatic(ExaminBeanWrapper object) {
		return object.getPropertyValue() != null && object.getPropertyValue().equals("Ali Zandi");
	}
	
}
