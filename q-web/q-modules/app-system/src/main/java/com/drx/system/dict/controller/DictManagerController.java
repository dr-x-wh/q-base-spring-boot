package com.drx.system.dict.controller;

import com.drx.core.response.Result;
import com.drx.db.entity.SysDict;
import com.drx.db.entity.SysDictItem;
import com.drx.system.dict.service.SysDictItemService;
import com.drx.system.dict.service.SysDictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
//@RequireUser
@RestController
@RequestMapping("/dictManager")
public class DictManagerController {

    private final SysDictService sysDictService;
    private final SysDictItemService sysDictItemService;

    public DictManagerController(SysDictService sysDictService, SysDictItemService sysDictItemService) {
        this.sysDictService = sysDictService;
        this.sysDictItemService = sysDictItemService;
    }

    //    @RequireUser({"admin"})
    @GetMapping
    public Result<List<SysDict>> list() {
        List<SysDict> list = sysDictService.list();
        log.debug(list.toString());
        return Result.success(list);
    }

    //    @RequireUser({"admin"})
    @GetMapping("/{code}")
    public Result<List<SysDictItem>> itemListByCode(@PathVariable String code) {
        return Result.success(sysDictItemService.allListByCode(code));
    }

}
