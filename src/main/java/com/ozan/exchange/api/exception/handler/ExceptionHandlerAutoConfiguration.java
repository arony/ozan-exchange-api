package com.ozan.exchange.api.exception.handler;

import com.ozan.exchange.api.exception.EntityNotFoundException;
import com.ozan.exchange.api.exception.InvalidRequestException;
import com.ozan.exchange.api.exception.binding.Error;
import com.ozan.exchange.api.exception.binding.ErrorResource;
import com.ozan.exchange.api.exception.binding.FieldErrorResource;
import java.util.ArrayList;
import java.util.List;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAutoConfiguration extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public @ResponseBody
    ResponseEntity<?> handleEntityNotFound(RuntimeException e, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Error error = new Error(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());

        return handleExceptionInternal(e, error, headers, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public @ResponseBody
    ResponseEntity<?> handleInvalidRequest(RuntimeException e, WebRequest request) {
        InvalidRequestException ire = (InvalidRequestException) e;
        List<FieldErrorResource> fieldErrorResources = new ArrayList<>();
        ResponseEntity<Object> returnObject;

        if (ire.getErrors() != null) {
            List<FieldError> fieldErrors = ire.getErrors().getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                FieldErrorResource fieldErrorResource = new FieldErrorResource();
                fieldErrorResource.setResource(fieldError.getObjectName());
                fieldErrorResource.setField(fieldError.getField());
                fieldErrorResource.setCode(fieldError.getCode());
                fieldErrorResource.setMessage(fieldError.getDefaultMessage());
                fieldErrorResources.add(fieldErrorResource);
            }

            ErrorResource error = new ErrorResource(HttpStatus.BAD_REQUEST.value(), "Invalid request", ire.getMessage());
            error.setFieldErrors(fieldErrorResources);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            returnObject = handleExceptionInternal(e, error, headers, HttpStatus.BAD_REQUEST, request);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            Error error = new Error(HttpStatus.BAD_REQUEST.value(), e.getCause().getMessage(),
                    System.currentTimeMillis());
            returnObject = handleExceptionInternal(e, error, headers, HttpStatus.BAD_REQUEST, request);
        }

        return returnObject;
    }
}