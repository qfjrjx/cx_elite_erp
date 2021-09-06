package com.erp.common.annotation;

import com.erp.common.condition.OnRedisCacheCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * @author qiufeng
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional(OnRedisCacheCondition.class)
public @interface ConditionOnRedisCache {
}
