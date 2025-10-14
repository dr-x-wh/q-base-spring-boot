package com.drx.base.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public static <A> Result<A> success(A data) {
        return new Result<>(0, "OK", data);
    }

    public static <Void> Result<Void> success() {
        return Result.success(null);
    }

    public static <Void> Result<Void> error(String message) {
        return new Result<>(-1, message, null);
    }

    public static <Void> Result<Void> error() {
        return Result.error("ERROR");
    }

}
