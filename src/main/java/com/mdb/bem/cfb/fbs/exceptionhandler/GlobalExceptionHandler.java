package com.mdb.bem.cfb.fbs.exceptionhandler;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> forException(Exception ex){

        return  new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

}
