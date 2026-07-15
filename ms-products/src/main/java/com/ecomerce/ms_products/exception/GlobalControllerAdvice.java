package com.ecomerce.ms_products.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import java.time.Instant;

@SuppressWarnings("unused")
@RestControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFoundException(ResourceNotFoundException e, WebRequest request){
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        detail.setTitle("Resource not found");
        detail.setProperty("Resource", e.getResourceName());
        detail.setProperty("Field", e.getFieldName());
        detail.setProperty("Value", e.getFieldValue());
        detail.setProperty("Timestamp", Instant.now());
        return detail;
    }
}
