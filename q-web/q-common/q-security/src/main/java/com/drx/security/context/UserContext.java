package com.drx.security.context;

import com.drx.api.domain.LoginUser;

public class UserContext {
    public static final ThreadLocal<LoginUser> userContext = new ThreadLocal<>();

    public static LoginUser get() {
        return userContext.get();
    }

    public static void set(LoginUser user) {
        userContext.set(user);
    }

    public static void clear() {
        userContext.remove();
    }

}
