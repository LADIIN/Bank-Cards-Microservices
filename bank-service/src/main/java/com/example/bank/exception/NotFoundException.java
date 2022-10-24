package com.example.bank.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends AbstractException {

    private static final String TITLE = "Resource not found.";
    private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;

    public NotFoundException() {
        super(TITLE, STATUS);
    }

    public NotFoundException(String message) {
        super(message, TITLE, STATUS);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause, TITLE, STATUS);
    }

    public NotFoundException(Throwable cause) {
        super(cause, TITLE, STATUS);
    }

    public NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace, TITLE, STATUS);
    }
}
