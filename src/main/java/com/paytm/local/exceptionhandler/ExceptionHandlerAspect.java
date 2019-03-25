package com.paytm.local.exceptionhandler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class ExceptionHandlerAspect {

    @Autowired
    private ApplicationContext applicationContext;

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
            java.lang.reflect.Method exceptionMethod = null;
            try {
                exceptionMethod = anno.adviceClass().getDeclaredMethod("handleException", e.getClass());
                return exceptionMethod.invoke(applicationContext.getBean(anno.adviceClass()),e);
            }
            catch (NoSuchMethodException e1){
                exceptionMethod = anno.adviceClass().getDeclaredMethod("handleException", Exception.class);
                return exceptionMethod.invoke(applicationContext.getBean(anno.adviceClass()),e);
            }

        }

        return null;
    }



}
