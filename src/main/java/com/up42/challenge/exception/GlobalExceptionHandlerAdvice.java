package com.up42.challenge.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeoutException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandlerAdvice {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ExceptionResponse> handleFtpException(BusinessException ex) {
        return handleException(HttpStatus.UNPROCESSABLE_ENTITY, ex);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return handleException(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(TimeoutException.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(TimeoutException ex) {
        return handleException(HttpStatus.REQUEST_TIMEOUT, ex);
    }

    @ExceptionHandler(SQLException.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(SQLException ex) {
        return handleException(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex) {
        return handleException(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }


    private ResponseEntity<ExceptionResponse> handleException(HttpStatus status, Throwable ex) {
        ExceptionResponse res = ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .status(status.value())
                .build();
        return new ResponseEntity<>(res, status);
    }
}
