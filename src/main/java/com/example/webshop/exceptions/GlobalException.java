package com.example.webshop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler
    public ResponseEntity<ExceptionData> userHandle(NoSuchUserException noSuchUserException) {
        ExceptionData info = new ExceptionData();
        info.setInfo(noSuchUserException.getMessage());
        return new ResponseEntity(info, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionData> itemHandle(NoSuchItemException noSuchItemException) {
        ExceptionData info = new ExceptionData();
        info.setInfo(noSuchItemException.getMessage());
        return new ResponseEntity<>(info, HttpStatus.NOT_FOUND);
    }
}
