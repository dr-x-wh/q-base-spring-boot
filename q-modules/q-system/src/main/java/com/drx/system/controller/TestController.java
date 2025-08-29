package com.drx.system.controller;

import com.drx.web.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Value("${app:null}")
    private String app;

    @Value("${num:null}")
    private String num;

    @GetMapping("")
    public Result<String> test() {
        log.debug("test");
        return Result.success(app + " and " + num);
    }

}
