package com.ecomerce.ms_products.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@SuppressWarnings("unused")
@RestControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFoundException(ResourceNotFoundException e){
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        detail.setTitle("Resource not found");
        detail.setProperty("Resource", e.getResourceName());
        detail.setProperty("Field", e.getFieldName());
        detail.setProperty("Value", e.getFieldValue());
        detail.setProperty("Timestamp", Instant.now());
        return detail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Errors in one or more fields");
        detail.setTitle("Request error");
        detail.setProperty("Timestamp", Instant.now());
        Map<String,String> errors = new HashMap<>();
        e.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        detail.setProperty("Errors", errors);
        return detail;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleException(Exception e, WebRequest request){
        log.error("Exception: {}", e.getMessage(), e);
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected Error");
        detail.setTitle("Internal Server Error");
        detail.setProperty("Timestamp", Instant.now());
        return detail;
    }
}
