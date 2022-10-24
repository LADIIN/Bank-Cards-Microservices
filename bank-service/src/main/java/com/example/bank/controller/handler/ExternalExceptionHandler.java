package com.example.bank.controller.handler;

import com.example.bank.controller.dto.ExceptionDto;
import com.example.bank.exception.AbstractException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class ExternalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({AbstractException.class})
    public ExceptionDto handle(HttpServletRequest request, HttpServletResponse response, AbstractException e) {
        response.setStatus(e.getStatus().value());
        return new ExceptionDto(request.getRequestURI(), e.getTitle(), e.getMessage());
    }
}
