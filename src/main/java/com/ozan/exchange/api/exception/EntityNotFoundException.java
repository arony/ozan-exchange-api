package com.ozan.exchange.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

    public static final long serialVersionUID = 1L;
    private Errors errors;
    private HttpStatus status;

    public EntityNotFoundException(String message) {
        super(message);
    }


    public EntityNotFoundException(String message, Errors errors) {
        super(message);
        this.errors = errors;
    }


    public EntityNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public EntityNotFoundException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public Errors getErrors() {
        return errors;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
