package com.drx.test.controller;

import com.drx.base.entity.SysUser;
import com.drx.base.tools.response.Result;
import com.drx.starter.service.RedisService;
import com.drx.test.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    private final SysUserService sysUserService;
    private final RedisService redisService;

    public TestController(SysUserService sysUserService, RedisService redisService) {
        this.sysUserService = sysUserService;
        this.redisService = redisService;
    }


    @GetMapping("")
    public Result<Object> test() {
        log.debug("test");
        SysUser test = (SysUser) redisService.getValue("test");
        if (test != null) {
            return Result.success(test);
        }
        List<SysUser> list = sysUserService.list();
        redisService.setValue("test", list.getFirst(), 5L);
        return Result.success(list);
    }

}
