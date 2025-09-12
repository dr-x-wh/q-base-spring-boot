package com.drx.starter.filter;

import com.drx.base.entity.User;
import com.drx.base.tools.context.UserContext;
import com.drx.starter.entity.SysRole;
import com.drx.starter.entity.SysUser;
import com.drx.starter.mapper.SysUserMapper;
import com.drx.starter.mapper.SysUserRoleMapper;
import com.drx.starter.repository.RedisService;
import com.drx.starter.tools.JwtTool;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class UserFilter implements Filter {

    private final RedisService redisService;
    private final SysUserMapper sysUserMapper;
    private final SysUserRoleMapper sysUserRoleMapper;
    @Value("${jwt.secret}")
    private String jwtSecret;

    public UserFilter(SysUserMapper sysUserMapper, RedisService redisService, SysUserRoleMapper sysUserRoleMapper) {
        this.sysUserMapper = sysUserMapper;
        this.redisService = redisService;
        this.sysUserRoleMapper = sysUserRoleMapper;
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
                String id = map.get("id");
                String session = map.get("session");
                String cacheSession = (String) redisService.getValue(String.format("session_%s", id));
                if (session.equals(cacheSession)) {
                    Long userId = Long.parseLong(id);
                    SysUser sysUser = sysUserMapper.getOneById(userId);
                    if (sysUser != null) {
                        User user = new User();
                        BeanUtils.copyProperties(sysUser, user);
                        List<SysRole> roles = sysUserRoleMapper.getAllByUserId(userId);
                        List<String> list = roles.stream().map(SysRole::getCode).toList();
                        user.setRoles(list);
                        UserContext.set(user);
                    }
                }
            }
            filterChain.doFilter(request, response);
        } finally {
            UserContext.clear();
        }
    }

}
