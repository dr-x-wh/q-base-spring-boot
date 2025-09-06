package com.drx.system.controller;

import com.drx.base.tools.response.Result;
import com.drx.starter.entity.SysDict;
import com.drx.starter.entity.SysDictItem;
import com.drx.starter.repository.RedisService;
import com.drx.system.service.SysDictItemService;
import com.drx.system.service.SysDictService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dict")
public class DictController {


    private final SysDictService sysDictService;
    private final SysDictItemService sysDictItemService;
    private final RedisService redisService;

    public DictController(SysDictService sysDictService, SysDictItemService sysDictItemService, RedisService redisService) {
        this.sysDictService = sysDictService;
        this.sysDictItemService = sysDictItemService;
        this.redisService = redisService;
    }

    @GetMapping("")
    public Result<List<SysDict>> list() {
        List<SysDict> dict = (List<SysDict>) redisService.getValue("dict");
        if (dict == null) {
            dict = sysDictService.list();
            redisService.setValue("dict", dict, (long) (10 * 60));
        }
        return Result.success(dict);
    }

    @GetMapping("/{code}")
    public Result<List<SysDictItem>> itemListByCode(@PathVariable String code) {
        List<SysDictItem> dict_item = (List<SysDictItem>) redisService.getValue(String.format("dict_%s", code));
        if (dict_item == null) {
            dict_item = sysDictItemService.listByCode(code);
            redisService.setValue(String.format("dict_%s", code), dict_item, (long) (10 * 60));
        }
        return Result.success(dict_item);
    }

}
