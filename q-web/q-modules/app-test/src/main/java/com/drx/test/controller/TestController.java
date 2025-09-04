package com.drx.test.controller;

import com.drx.base.entity.SysUser;
import com.drx.base.tools.response.Result;
import com.drx.test.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {


    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("")
    public Result<List<SysUser>> test() {
        log.debug("test");
        return Result.success(testService.getSysUser());
    }

}
