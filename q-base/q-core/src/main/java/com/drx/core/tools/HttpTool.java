package com.drx.core.tools;

import com.drx.core.response.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class HttpTool {

    public static <T> void setBody(HttpServletResponse response, Result<T> result) {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json;charset=UTF-8");
        try {
            response.getWriter().write(new ObjectMapper().writeValueAsString(result));
        } catch (IOException _) {
        }
    }

    public static void notPermission(HttpServletResponse response) {
        Result<Void> result = new Result<>();
        result.setCode(10401);
        result.setMessage("权限不足");
        HttpTool.setBody(response, result);
    }
}
