package com.portfolio.finance_tracker.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<Message> transactionNotFoundException(TransactionNotFoundException e, HttpServletRequest request) {
        return new ResponseEntity<>(Message.builder()
                .dateTime(LocalDateTime.now())
                .code(NOT_FOUND.value())
                .status(NOT_FOUND.getReasonPhrase())
                .message(e.getMessage())
                .uri(request.getRequestURI())
                .method(request.getMethod())
                .build(), NOT_FOUND);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Message> categoryNotFoundException(CategoryNotFoundException e, HttpServletRequest request) {
        return new ResponseEntity<>(Message.builder()
                .dateTime(LocalDateTime.now())
                .code(NOT_FOUND.value())
                .status(NOT_FOUND.getReasonPhrase())
                .message(e.getMessage())
                .uri(request.getRequestURI())
                .method(request.getMethod())
                .build(), NOT_FOUND);
    }
}
