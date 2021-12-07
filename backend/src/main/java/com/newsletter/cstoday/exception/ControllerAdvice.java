package com.newsletter.cstoday.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(CsTodayException.class)
    public ResponseEntity<String> handleLogicException(CsTodayException e) {
        log.info("CsTodayException = " + e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleInvalidRequestException(MethodArgumentNotValidException e) {
        String errorMessage = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
        log.info("MethodArgumentNotValidException = " + errorMessage);
        return ResponseEntity.badRequest().body(errorMessage);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleLogicException(IllegalStateException e) {
        logError(e);
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleUnexpectedException(Exception e) {
        logError(e);
        return ResponseEntity.internalServerError().body("관리자에게 문의하세요");
    }

    private void logError(Exception e) {
        StringBuilder errorBuilder = new StringBuilder();
        errorBuilder.append("-----CRITICAL ERROR START-----\n");
        errorBuilder.append("ERROR MESSAGE : " + e.getMessage() + "\n");
        String[] spiltMessages = Arrays.toString(e.getStackTrace()).split(",");
        for (String splitMessage : spiltMessages) {
            errorBuilder.append(splitMessage).append("\n");
        }
        errorBuilder.append("-----CRITICAL ERROR END-----");
        log.error("CRITICAL ERROR \n {}", errorBuilder);
    }
}
