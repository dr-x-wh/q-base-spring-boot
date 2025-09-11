package com.drx.starter.tools;

import com.drx.base.tools.response.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class WebTool {

    public static void sendError(HttpServletResponse response, int code, String message) throws IOException {
        ObjectMapper jacksonObjectMapper = BeanTool.getBean("jacksonObjectMapper", ObjectMapper.class);
        Result<Void> result = Result.error(message);
        String resultStr = jacksonObjectMapper.writeValueAsString(result);
        response.setStatus(code);
        response.getWriter().write(resultStr);
        response.getWriter().flush();
    }

}
