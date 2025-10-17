package com.drx.security.filter;

import com.drx.api.domain.LoginUser;
import com.drx.api.service.RemoteUserService;
import com.drx.cache.repository.CacheClient;
import com.drx.core.constant.SecurityConstant;
import com.drx.core.response.Result;
import com.drx.core.tools.JwtTool;
import com.drx.security.context.UserContext;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
public class UserFilter implements Filter {
    private final RemoteUserService remoteUserService;
    private final CacheClient cacheClient;
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${spring.application.name}")
    private String appName;

    public UserFilter(RemoteUserService remoteUserService, CacheClient cacheClient) {
        this.remoteUserService = remoteUserService;
        this.cacheClient = cacheClient;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null && authHeader.length() > 7 && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                Map<String, String> map = JwtTool.parseToken(token, jwtSecret);
                String userId = map.get("id");
                String session = map.get("session");
                String cacheSession = (String) cacheClient.getValue(String.format("session_%s", userId));
                if (session.equals(cacheSession)) {
                    Result<LoginUser> result = remoteUserService.getUserInfo(userId, appName);
                    if (result != null && result.getCode().equals(0)) UserContext.set(result.getData());
                }
            }
            filterChain.doFilter(request, response);
        } finally {
            UserContext.clear();
        }
    }

}
