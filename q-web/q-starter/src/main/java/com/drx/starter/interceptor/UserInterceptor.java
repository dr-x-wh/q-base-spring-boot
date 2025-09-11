package com.drx.starter.interceptor;

import com.drx.base.entity.User;
import com.drx.base.enums.USER_ROLE;
import com.drx.base.tools.context.UserContext;
import com.drx.starter.annotation.RequireUser;
import com.drx.starter.tools.WebTool;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class UserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod handlerMethod)) {
            return true;
        }
        RequireUser methodAnnotation = handlerMethod.getMethod().getAnnotation(RequireUser.class);
        RequireUser classAnnotation = handlerMethod.getBeanType().getAnnotation(RequireUser.class);
        RequireUser requireUser = methodAnnotation != null ? methodAnnotation : classAnnotation;
        if (requireUser != null) {
            User user = UserContext.get();
            if (user == null) {
                WebTool.sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "Please log in and try again");
                return false;
            } else {
                String role = requireUser.value();
                if (role.isEmpty()) {
                    return true;
                }
                String userRole = user.getRole();
                Integer userSort = USER_ROLE.valueOf(userRole.toUpperCase()).getSort();
                Integer roleSort = USER_ROLE.valueOf(role.toUpperCase()).getSort();
                if (roleSort <= userSort) {
                    return true;
                } else {
                    WebTool.sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "Deny access");
                    return false;
                }
            }
        }
        return true;
    }

}
