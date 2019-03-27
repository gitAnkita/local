package com.paytm.local.exceptionhandler;

import lombok.Data;

import java.lang.reflect.Method;

@Data
public class MethodExceptionHandlerDTO {

    private Method method;

    private Class<?> adviceType;

}
