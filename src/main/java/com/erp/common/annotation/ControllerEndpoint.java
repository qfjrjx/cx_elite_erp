package com.erp.common.annotation;

import com.erp.common.entity.Strings;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author MrBird
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ControllerEndpoint {

    String operation() default Strings.EMPTY;

    String exceptionMessage() default "FEBS系统内部异常";
}
