package com.tracing.service.emails;

import com.tracing.service.emails.EmailNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class EmailNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(EmailNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String reportNotFoundHandler(EmailNotFoundException ex) {
        return ex.getMessage();
    }
}