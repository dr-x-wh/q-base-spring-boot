package com.drx.starter.filter;

import com.drx.base.entity.User;
import com.drx.base.enums.USER_STATE;
import com.drx.base.tools.context.UserContext;
import com.drx.base.tools.response.Result;
import com.drx.base.tools.security.JwtTool;
import com.drx.starter.entity.SysUser;
import com.drx.starter.mapper.SysUserMapper;
import com.drx.starter.repository.RedisService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
public class UserFilter extends OncePerRequestFilter {
    private final RedisService redisService;
    private final SysUserMapper sysUserMapper;
    private final ObjectMapper jacksonObjectMapper;
    @Value("${jwt.secret}")
    private String jwtSecret;

    public UserFilter(SysUserMapper sysUserMapper, RedisService redisService, ObjectMapper jacksonObjectMapper) {
        this.sysUserMapper = sysUserMapper;
        this.redisService = redisService;
        this.jacksonObjectMapper = jacksonObjectMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        try {
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                Map<String, String> map = JwtTool.parseToken(token, jwtSecret);
                String id = map.get("id");
                String session = map.get("session");
                String cacheSession = (String) redisService.getValue(String.format("session_%s", id));
                if (session.equals(cacheSession)) {
                    SysUser sysUser = sysUserMapper.selectById(Long.parseLong(id));
                    Assert.isTrue(sysUser.getState().equals(USER_STATE.NORMAL.getKey()), "用户账户异常");
                    Assert.notNull(sysUser, "用户信息不存在");
                    User user = new User();
                    BeanUtils.copyProperties(sysUser, user);
                    UserContext.set(user);
                }
            }
            filterChain.doFilter(request, response);
        } catch (IOException | ServletException e) {
            try {
                Result<Void> result = Result.error("user filter error");
                String resultStr = jacksonObjectMapper.writeValueAsString(result);
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write(resultStr);
                response.getWriter().flush();
            } catch (Exception ex) {
                log.error("UserFilter 发生错误: {}", e.getMessage());
            }
        } catch (IllegalArgumentException e) {
            try {
                Result<Void> result = Result.error("user info error");
                String resultStr = jacksonObjectMapper.writeValueAsString(result);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write(resultStr);
                response.getWriter().flush();
            } catch (Exception ex) {
                log.error("UserFilter 用户信息查询为空");
            }
        } finally {
            UserContext.clear();
        }
    }
}
