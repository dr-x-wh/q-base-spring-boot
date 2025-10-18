package com.drx.auth.controller;

import com.drx.api.domain.LoginUser;
import com.drx.auth.dto.LoginDTO;
import com.drx.auth.service.SysRoleService;
import com.drx.auth.service.SysUserService;
import com.drx.core.response.Result;
import com.drx.db.entity.SysUser;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping
public class AuthController {


    private final SysUserService sysUserService;
    private final SysRoleService sysRoleService;

    public AuthController(SysUserService sysUserService, SysRoleService sysRoleService) {
        this.sysUserService = sysUserService;
        this.sysRoleService = sysRoleService;
    }

    @GetMapping("/{id}")
    public Result<LoginUser> userInfo(@PathVariable String id) {
        SysUser user = sysUserService.getById(id);
        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(user, loginUser);
        List<String> roles = sysRoleService.getByUserId(user.getId());
        loginUser.setRoles(roles);
        return Result.success(loginUser);
    }

    @PostMapping("/login")
    public Result<String> login(@RequestBody @Valid LoginDTO dto) {
        String token = sysUserService.login(dto);
        return Result.success(token);
    }

    //    @RequireUser
    @PostMapping("/logout")
    public Result<String> logout() {
        sysUserService.logout();
        return Result.success();
    }

}
