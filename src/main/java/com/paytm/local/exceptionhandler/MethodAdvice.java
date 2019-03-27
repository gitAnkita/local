package com.paytm.local.exceptionhandler;

import org.springframework.core.Ordered;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodAdvice {

    Class[] assignableAdviceTypes();

}
