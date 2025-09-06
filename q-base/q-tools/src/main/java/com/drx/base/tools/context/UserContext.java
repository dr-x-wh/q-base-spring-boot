package com.drx.base.tools.context;

import com.drx.base.entity.User;

public class UserContext {
    private static final ThreadLocal<User> userContext = new ThreadLocal<>();

    public static User get() {
        return userContext.get();
    }

    public static void set(User user) {
        userContext.set(user);
    }

    public static void clear() {
        userContext.remove();
    }
}
