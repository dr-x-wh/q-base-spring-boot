package com.drx.system.controller;

import com.drx.base.tools.response.Result;
import com.drx.system.service.SysDictItemService;
import com.drx.system.service.SysDictService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dict")
public class DictController {


    private final SysDictService sysDictService;
    private final SysDictItemService sysDictItemService;

    public DictController(SysDictService sysDictService, SysDictItemService sysDictItemService) {
        this.sysDictService = sysDictService;
        this.sysDictItemService = sysDictItemService;
    }

    @GetMapping("/")
    public Result<Object> getItemByCode(String code) {
        return Result.error();
    }

}
