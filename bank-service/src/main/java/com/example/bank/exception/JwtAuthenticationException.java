package com.example.bank.exception;

import org.springframework.http.HttpStatus;

public class JwtAuthenticationException extends AbstractException {

    private static final String INVALID_TOKEN = "Invalid token.";
    private static final HttpStatus FORBIDDEN = HttpStatus.FORBIDDEN;

    public JwtAuthenticationException() {
        super(INVALID_TOKEN, FORBIDDEN);
    }

    public JwtAuthenticationException(String message) {
        super(message, INVALID_TOKEN, FORBIDDEN);
    }

    public JwtAuthenticationException(String message, Throwable cause) {
        super(message, cause, INVALID_TOKEN, FORBIDDEN);
    }

    public JwtAuthenticationException(Throwable cause) {
        super(cause, INVALID_TOKEN, FORBIDDEN);
    }

    public JwtAuthenticationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace, INVALID_TOKEN, FORBIDDEN);
    }
}
