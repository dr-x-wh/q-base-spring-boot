package com.drx.security.interceptor;

import com.drx.api.domain.LoginUser;
import com.drx.core.tools.HttpTool;
import com.drx.security.annotation.RequireUser;
import com.drx.security.context.UserContext;
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
            LoginUser user = UserContext.get();
            if (user == null) {
                HttpTool.notPermission(response);
                return false;
            } else {
                String[] roles = requireUser.value();
                if (roles.length == 0) {
                    return true;
                }
                Set<String> rolesSet = new HashSet<>(Arrays.asList(roles));
                HashSet<String> userRolesSet = new HashSet<>(user.getRoles());
                boolean hasRoles = userRolesSet.containsAll(rolesSet);
                if (hasRoles || userRolesSet.contains("admin") || userRolesSet.contains("administrator")) {
                    return true;
                } else {
                    HttpTool.notPermission(response);
                    return false;
                }
            }
        }
        return true;
    }

}
