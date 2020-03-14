package com.zelazobeton.bookstore.controllers;

import com.zelazobeton.bookstore.Templates;
import com.zelazobeton.bookstore.exceptions.ResourceNotFoundException;
import com.zelazobeton.bookstore.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleResourceNotFoundExceptions(ResourceNotFoundException ex,
                                                   Model model,
                                                   @AuthenticationPrincipal User user){
        System.out.println(ex.toString());
        model.addAttribute("user", user);
        return Templates.ERROR;
    }

    @ExceptionHandler({RuntimeException.class, UsernameNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleRuntimeExceptions(RuntimeException ex,
                                          Model model,
                                          @AuthenticationPrincipal User user){
        System.out.println(ex.toString());
        model.addAttribute("user", user);
        return Templates.ERROR;
    }

}
