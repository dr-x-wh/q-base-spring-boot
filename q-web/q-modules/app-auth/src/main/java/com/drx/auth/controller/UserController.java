package com.drx.auth.controller;

import com.drx.api.annotation.Inner;
import com.drx.api.domain.LoginUser;
import com.drx.auth.dto.RegisterDTO;
import com.drx.auth.result.UserInfo;
import com.drx.auth.service.UserService;
import com.drx.core.response.Result;
import com.drx.security.annotation.RequireUser;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@RequestMapping("/user")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public Result<Void> register(@RequestBody @Valid RegisterDTO dto) {
        log.debug(dto.toString());
        boolean save = userService.save(dto);
        Assert.isTrue(save, "注册失败");
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
