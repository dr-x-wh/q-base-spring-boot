package com.drx.system.dict.controller;

import com.drx.base.entity.User;
import com.drx.base.tools.context.UserContext;
import com.drx.base.tools.response.Result;
import com.drx.starter.annotation.RequireUser;
import com.drx.starter.entity.SysDict;
import com.drx.starter.entity.SysDictItem;
import com.drx.system.dict.service.SysDictItemService;
import com.drx.system.dict.service.SysDictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequireUser
@RestController
@RequestMapping("/dictManager")
public class DictManagerController {

    private final SysDictService sysDictService;
    private final SysDictItemService sysDictItemService;

    public DictManagerController(SysDictService sysDictService, SysDictItemService sysDictItemService) {
        this.sysDictService = sysDictService;
        this.sysDictItemService = sysDictItemService;
    }

    @GetMapping
    public Result<List<SysDict>> list() {
        User user = UserContext.get();
        log.debug("{}", user.toString());
        return Result.success(sysDictService.list());
    }

    @GetMapping("/{code}")
    public Result<List<SysDictItem>> itemListByCode(@PathVariable String code) {
        return Result.success(sysDictItemService.allListByCode(code));
    }

}
