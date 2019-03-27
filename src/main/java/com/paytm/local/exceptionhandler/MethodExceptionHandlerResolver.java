package com.paytm.local.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.method.annotation.ExceptionHandlerMethodResolver;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MethodExceptionHandlerResolver {

    private final Map<Class<?>, ExceptionHandlerMethodResolver> exceptionHandlerCache =
            new ConcurrentHashMap<>();


    public MethodExceptionHandlerDTO resolveExceptionHandler(MethodAdvice methodAdvice, Exception exception) {

        for (Class adviceType : methodAdvice.assignableAdviceTypes()) {
            ExceptionHandlerMethodResolver resolver = this.exceptionHandlerCache.get(adviceType);

            if (resolver == null) {
                resolver = new ExceptionHandlerMethodResolver(adviceType);
                this.exceptionHandlerCache.put(adviceType, resolver);
            }

            Method method = resolver.resolveMethod(exception);
            if(method != null){
                MethodExceptionHandlerDTO handlerDTO = new MethodExceptionHandlerDTO();
                handlerDTO.setMethod(method);
                handlerDTO.setAdviceType(adviceType);
                return handlerDTO;
            }
        }

        return null;
    }

}
