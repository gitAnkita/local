package com.paytm.local.exceptionhandler;

import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ResponseBody
public @interface MethodExceptionHandler {

    Class adviceClass();

}
