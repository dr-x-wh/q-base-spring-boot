package com.drx.auth.controller;

import com.drx.auth.pojo.form.LoginForm;
import com.drx.auth.service.SysUserService;
import com.drx.core.response.Result;
import com.drx.starter.annotation.RequireUser;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UserController {


    private final SysUserService sysUserService;

    public UserController(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @PostMapping("/login")
    public Result<String> login(@RequestBody @Valid LoginForm form) {
        String token = sysUserService.login(form);
        return Result.success(token);
    }

    @RequireUser
    @PostMapping("/logout")
    public Result<String> logout() {
        sysUserService.logout();
        return Result.success();
    }

}
