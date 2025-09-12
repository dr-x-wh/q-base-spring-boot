package com.drx.system.user.controller;

import com.drx.base.entity.User;
import com.drx.base.tools.context.UserContext;
import com.drx.base.tools.response.Result;
import com.drx.starter.annotation.RequireUser;
import com.drx.system.user.pojo.form.RegisterForm;
import com.drx.system.user.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private final SysUserService sysUserService;

    public UserController(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @RequireUser
    @GetMapping()
    public Result<User> getInfo() {
        return Result.success(UserContext.get());
    }

    @PostMapping()
    public Result<Void> register(@RequestBody RegisterForm form) {
        sysUserService.register(form);
        return Result.success();
    }

    @RequireUser
    @DeleteMapping()
    public Result<Void> remove() {
        sysUserService.cancel();
        return Result.success();
    }

}
