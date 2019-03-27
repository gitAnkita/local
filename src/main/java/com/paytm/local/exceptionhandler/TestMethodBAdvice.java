package com.paytm.local.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
@ControllerAdvice
public class TestMethodBAdvice {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ArithmeticException.class)
    public Object handleException(ArithmeticException e) {
        System.out.println("TestMethodBAdvice ArithmeticException - "+e.getClass());
        return "TestMethodBAdvice ArithmeticException";
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        System.out.println("TestMethodBAdvice Exception - "+e.getClass());
        return "TestMethodBAdvice Exception";
    }

}
