package com.drx.starter.exception;

import com.drx.base.response.Result;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public Result<Void> handleConstraintViolationException(ConstraintViolationException e) {
        String errorMessage = e.getConstraintViolations().stream().map(violation -> {
            String field = violation.getPropertyPath().toString();
            return String.format("%s: %s", field, violation.getMessage());
        }).collect(Collectors.joining("; "));
        log.debug("Validation error param: {}", errorMessage);
        return Result.error(errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getFieldErrors().stream().map(error -> String.format("%s: %s", error.getField(), error.getDefaultMessage())).collect(Collectors.joining("; "));
        log.debug("Validation error body: {}", errorMessage);
        return Result.error(errorMessage);
    }

    @ExceptionHandler(Throwable.class)
    public Result<Void> handleThrowable(Throwable e) {
        log.debug("{}", e.getMessage());
        return Result.error(e.getMessage());
    }

}
