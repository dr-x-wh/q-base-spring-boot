package com.drx.starter.interceptor;

import com.drx.base.entity.User;
import com.drx.base.tools.context.UserContext;
import com.drx.starter.annotation.RequireUser;
import com.drx.starter.tools.WebTool;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class UserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
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
                String[] roles = requireUser.value();
                if (roles.length == 0) {
                    return true;
                }
                Set<String> rolesSet = new HashSet<>(Arrays.asList(roles));
                HashSet<String> userRolesSet = new HashSet<>(user.getRoles());
                boolean hasRoles = userRolesSet.containsAll(rolesSet);
                if (hasRoles) {
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
