package com.example.footballmanager.advice;

import java.time.LocalDate;
import com.example.footballmanager.exception.ApiException;
import com.example.footballmanager.exception.BusinessException;
import com.example.footballmanager.exception.ErrorObjects;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {ApiException.class})
    public ResponseEntity<ErrorObjects> handleApiRequestException(ApiException e) {
        ErrorObjects errorObjects = new ErrorObjects();
        errorObjects.setHttpStatus(HttpStatus.BAD_REQUEST.value());
        errorObjects.setDetail(e.getMessage());
        errorObjects.setTime(LocalDate.now());
        return new ResponseEntity<>(errorObjects, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {BusinessException.class})
    public ResponseEntity<ErrorObjects> handleBusinessException(BusinessException e) {
        ErrorObjects errorObjects = new ErrorObjects();
        errorObjects.setHttpStatus(HttpStatus.NOT_FOUND.value());
        errorObjects.setDetail(e.getMessage());
        errorObjects.setTime(LocalDate.now());
        return new ResponseEntity<>(errorObjects, HttpStatus.NOT_FOUND);
    }
}
