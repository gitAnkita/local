package com.paytm.local.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
@ControllerAdvice
public class TestMethodAAdvice {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        System.out.println("TestMethodAAdvice Exception - "+e.getClass());
        return "TestMethodAAdvice Exception";
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ArithmeticException.class)
    public Object handleException(ArithmeticException e) {
        System.out.println("TestMethodAAdvice ArithmeticException - "+e.getClass());
        return "TestMethodAAdvice ArithmeticException";
    }


}
