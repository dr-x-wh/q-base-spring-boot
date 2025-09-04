package com.drx.base.tools.context;

import com.drx.base.entity.SysUser;

public class UserContext {
    private static final ThreadLocal<SysUser> userContext = new ThreadLocal<>();

    public static SysUser get() {
        return userContext.get();
    }

    public static void set(SysUser user) {
        userContext.set(user);
    }

    public static void clear() {
        userContext.remove();
    }
}
