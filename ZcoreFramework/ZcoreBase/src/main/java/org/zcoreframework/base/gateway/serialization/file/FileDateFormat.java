package org.zcoreframework.base.gateway.serialization.file;


import org.zcoreframework.base.gateway.constant.GatewayConstants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FileDateFormat {
    String pattern() default GatewayConstants.VALUE_DATE_FORMAT_CSV_FILE;
}
