package com.example.bank.exception;

import org.springframework.http.HttpStatus;

public abstract class AbstractException extends RuntimeException {

    private final String title;
    private final HttpStatus status;

    public AbstractException(String title, HttpStatus status) {
        this.title = title;
        this.status = status;
    }

    public AbstractException(String message, String title, HttpStatus status) {
        super(message);
        this.title = title;
        this.status = status;
    }

    public AbstractException(String message, Throwable cause, String title, HttpStatus status) {
        super(message, cause);
        this.title = title;
        this.status = status;
    }

    public AbstractException(Throwable cause, String title, HttpStatus status) {
        super(cause);
        this.title = title;
        this.status = status;
    }

    public AbstractException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String title, HttpStatus status) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.title = title;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
