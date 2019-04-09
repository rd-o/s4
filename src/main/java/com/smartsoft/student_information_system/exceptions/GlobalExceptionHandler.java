package com.smartsoft.student_information_system.exceptions;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Please check your request")
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public void handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, WebRequest request) {

        final Throwable cause = ex.getCause();
        if (cause == null) {
            //handle current exception
        } else if (cause instanceof JsonParseException) {
            //handle JsonParseException
        } else if (cause instanceof JsonMappingException) {
            //handle JsonMappingException
        }
    }
}
