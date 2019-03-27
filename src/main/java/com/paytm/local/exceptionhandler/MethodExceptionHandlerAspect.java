package com.paytm.local.exceptionhandler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Aspect
@Component
public class MethodExceptionHandlerAspect {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private MethodExceptionHandlerResolver exceptionHandlerResolver;

    /*for any method with @annotation*/
    @Pointcut("execution(@com.paytm.local.exceptionhandler.MethodAdvice * *.*(..))")
    public void annotatedMonitorEntityMethod() {}

    @Around("annotatedMonitorEntityMethod()")
    public Object annotationResolver(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        MethodAdvice anno = method.getAnnotation(MethodAdvice.class);

        Object retVal = null ;
        try {
            retVal = joinPoint.proceed();
        } catch (Exception e){
            MethodExceptionHandlerDTO methodExceptionHandlerDTO = exceptionHandlerResolver.resolveExceptionHandler(anno,e);

            if(methodExceptionHandlerDTO == null || methodExceptionHandlerDTO.getMethod() == null){
                throw e;
            }

            retVal = methodExceptionHandlerDTO.getMethod().
                    invoke(
                            applicationContext.getBean(methodExceptionHandlerDTO.getAdviceType()),
                            e
                    );
            setResponseStatus(methodExceptionHandlerDTO.getMethod());

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
