package com.drx.system.dict.controller;

import com.drx.base.tools.response.Result;
import com.drx.starter.entity.SysDictItem;
import com.drx.starter.repository.RedisService;
import com.drx.system.dict.pojo.result.DictItemResult;
import com.drx.system.dict.service.SysDictItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dict")
public class DictController {

    private final SysDictItemService sysDictItemService;
    private final RedisService redisService;

    public DictController(SysDictItemService sysDictItemService, RedisService redisService) {
        this.sysDictItemService = sysDictItemService;
        this.redisService = redisService;
    }

    @GetMapping("/{code}")
    public Result<List<DictItemResult>> itemListByCode(@PathVariable String code) {
        List<DictItemResult> dict_item = (List<DictItemResult>) redisService.getValue(String.format("dict_%s", code));
        if (dict_item == null) {
            List<SysDictItem> sysDictItems = sysDictItemService.listByCode(code);
            dict_item = sysDictItems.stream().map(item -> {
                DictItemResult result = new DictItemResult();
                BeanUtils.copyProperties(item, result);
                return result;
            }).collect(Collectors.toList());
            redisService.setValue(String.format("dict_%s", code), dict_item, (long) (10 * 60));
        }
        return Result.success(dict_item);
    }

}
