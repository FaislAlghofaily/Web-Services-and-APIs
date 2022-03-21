package com.udacity.eurekaserver.api;


import org.springframework.data.rest.webmvc.RepositoryRestExceptionHandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice(basePackageClasses = RepositoryRestExceptionHandler.class)
public class RestControllerAdviceHandler {

    @ExceptionHandler
    ResponseEntity handle(Exception e) {
        return new ResponseEntity("Price Not Found", new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}