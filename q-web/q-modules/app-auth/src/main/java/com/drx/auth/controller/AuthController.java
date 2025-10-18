package com.drx.auth.controller;

import com.drx.api.domain.LoginUser;
import com.drx.auth.dto.LoginDTO;
import com.drx.core.response.Result;
import com.drx.security.annotation.RequireUser;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping
public class AuthController {

    @PostMapping("/login")
    public Result<LoginUser> userInfo(@RequestBody @Valid LoginDTO id) {
        return Result.success();
    }

    @RequireUser
    @PostMapping("/logout")
    public Result<String> login() {
        return Result.success();
    }

}
