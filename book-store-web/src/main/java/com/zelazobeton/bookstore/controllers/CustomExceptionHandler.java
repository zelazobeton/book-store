package com.zelazobeton.bookstore.controllers;

import com.zelazobeton.bookstore.Templates;
import com.zelazobeton.bookstore.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleResourceNotFoundExceptions(ResourceNotFoundException ex){
        System.out.println(ex.toString());
        return Templates.ERROR;
    }

    @ExceptionHandler({RuntimeException.class, UsernameNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleRuntimeExceptions(RuntimeException ex){
        System.out.println(ex.toString());
        return Templates.ERROR;
    }

}
