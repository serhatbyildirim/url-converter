package com.trendyol.sample.controller.advice;

import com.trendyol.sample.exception.UrlConvertException;
import com.trendyol.sample.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse(
                "GenericException",
                System.currentTimeMillis(),
                Collections.singletonList("Generic exception occurred.")
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UrlConvertException.class)
    public ResponseEntity<ErrorResponse> handle(UrlConvertException exception) {
        ErrorResponse errorResponse = new ErrorResponse(
                "UrlConvertException",
                System.currentTimeMillis(),
                Collections.singletonList(exception.getMessage())
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
