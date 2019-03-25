package com.paytm.local.exceptionhandler;

import com.paytm.local.controller.TestController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = {TestController.class})
@Slf4j
@Configuration
public class TestControllerAdvice {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String handleException(Exception exception) {

        System.out.println("TestControllerAdvice Exception - "+exception.getClass());
        return "TestControllerAdvice Exception";
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ArithmeticException.class)
    public String handleException(ArithmeticException exception) {

        System.out.println("TestControllerAdvice ArithmeticException - "+exception.getClass());
        return "TestControllerAdvice ArithmeticException";
    }



}
