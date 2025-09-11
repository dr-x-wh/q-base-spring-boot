package com.drx.auth.controller;

import com.drx.auth.pojo.dto.LoginDTO;
import com.drx.auth.pojo.dto.RegisterDTO;
import com.drx.auth.service.SysUserService;
import com.drx.base.tools.response.Result;
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
    public Result<String> login(@RequestBody @Valid LoginDTO dto) {
        String token = sysUserService.login(dto);
        return Result.success(token);
    }

    @RequireUser
    @PostMapping("/logout")
    public Result<String> logout() {
        sysUserService.logout();
        return Result.success();
    }

    @PostMapping("/register")
    public Result<Void> register(@RequestBody @Valid RegisterDTO dto) {
        sysUserService.register(dto);
        return Result.success();
    }

    @RequireUser
    @PostMapping("/signOut")
    public Result<Void> signOut() {
        sysUserService.signOut();
        return Result.success();
    }

}
