package com.drx.test.controller;

import com.drx.base.tools.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("")
    public Result<String> test() {
        log.debug("test");
        return Result.success("OK");
    }

}
