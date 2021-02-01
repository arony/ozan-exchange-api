package com.ozan.exchange.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRequestException extends RuntimeException {

    public static final long serialVersionUID = 1L;
    private Errors errors;
    private HttpStatus status;

    public InvalidRequestException(String message, Errors errors) {
        super(message);
        this.errors = errors;
    }

    public InvalidRequestException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public InvalidRequestException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public Errors getErrors() {
        return errors;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
