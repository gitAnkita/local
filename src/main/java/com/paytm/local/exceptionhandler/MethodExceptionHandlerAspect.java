package com.paytm.local.exceptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class MethodExceptionHandlerAspect {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private MethodExceptionHandlerResolver exceptionHandlerResolver;

    /*for any method with @annotation*/
    @Pointcut("execution(@com.paytm.local.exceptionhandler.MethodExceptionHandler * *.*(..))")
    public void annotatedMonitorEntityMethod() {}

    @Around("annotatedMonitorEntityMethod()")
    public Object annotationResolver(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        MethodExceptionHandler anno = method.getAnnotation(MethodExceptionHandler.class);

        Object retVal = null ;
        try {
            retVal = joinPoint.proceed();
        } catch (Exception e){
            java.lang.reflect.Method exceptionMethod = exceptionHandlerResolver.getExceptionHandlerMethod(anno.assignableAdviceType(),e);

            if(exceptionMethod == null){
                throw e;
            }

            retVal = exceptionMethod.invoke(applicationContext.getBean(anno.assignableAdviceType()),e);
            setResponseStatus(exceptionMethod);

        }
        return retVal;
    }

    private void setResponseStatus(java.lang.reflect.Method method){
        if(method.isAnnotationPresent(ResponseStatus.class)) {
            ResponseStatus responseStatus = method.getAnnotation(ResponseStatus.class);
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            response.setStatus(responseStatus.value().value());
        }
    }


}
