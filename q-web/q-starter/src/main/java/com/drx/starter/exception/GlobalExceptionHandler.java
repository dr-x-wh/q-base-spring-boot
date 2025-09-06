package com.drx.starter.exception;

import com.drx.base.tools.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public Result<Void> handleThrowable(Throwable e) {
        log.debug("{}", e.getMessage());
        return Result.error(e.getMessage());
    }

}
