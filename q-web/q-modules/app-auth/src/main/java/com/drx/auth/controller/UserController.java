package com.drx.auth.controller;

import com.drx.api.annotation.Inner;
import com.drx.api.domain.LoginUser;
import com.drx.auth.dto.RegisterDTO;
import com.drx.auth.result.UserInfo;
import com.drx.core.response.Result;
import com.drx.security.annotation.RequireUser;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/register")
    public Result<Void> register(@RequestBody @Valid RegisterDTO dto) {
        log.debug(dto.toString());
        return Result.success();
    }

    @RequireUser
    @DeleteMapping("/{id}")
    public Result<Void> remove(@PathVariable String id) {
        return Result.success();
    }

    @RequireUser
    @GetMapping("/{id}")
    public Result<UserInfo> getUser(@PathVariable String id) {
        return Result.success();
    }

    @Inner
    @GetMapping("/inner/{id}")
    public Result<LoginUser> getUserByInner(@PathVariable String id) {
        return Result.success();
    }

}
