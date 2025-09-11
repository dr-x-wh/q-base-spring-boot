package com.drx.system.dict.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drx.starter.entity.SysDict;
import com.drx.starter.entity.SysDictItem;
import com.drx.starter.mapper.SysDictItemMapper;
import com.drx.starter.repository.RedisService;
import com.drx.system.dict.service.SysDictItemService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

@Service
public class SysDictItemServiceImpl extends ServiceImpl<SysDictItemMapper, SysDictItem> implements SysDictItemService {

    private final BaseMapper<SysDict> sysDictMapper;
    private final RedisService redisService;

    public SysDictItemServiceImpl(BaseMapper<SysDict> sysDictMapper, RedisService redisService) {
        this.sysDictMapper = sysDictMapper;
        this.redisService = redisService;
    }

    @Override
    public List<SysDictItem> listByCode(String code) {
        List<SysDictItem> value = (List<SysDictItem>) redisService.getValue(String.format("dict_%s", code));
        if (value != null) {
            return value;
        }
        LambdaQueryWrapper<SysDict> dictWrapper = new LambdaQueryWrapper<>();
        dictWrapper.eq(SysDict::getCode, code);
        SysDict sysDict = sysDictMapper.selectOne(dictWrapper);
        Assert.notNull(sysDict, "字典不存在: " + code);
        LambdaQueryWrapper<SysDictItem> dictItemWrapper = new LambdaQueryWrapper<>();
        dictItemWrapper.allEq(Map.of(SysDictItem::getDictId, sysDict.getId(), SysDictItem::getState, 1));
        List<SysDictItem> sysDictItems = baseMapper.selectList(dictItemWrapper);
        redisService.setValue(String.format("dict_%s", code), sysDictItems);
        return sysDictItems;
    }

    @Override
    public List<SysDictItem> allListByCode(String code) {
        LambdaQueryWrapper<SysDict> dictWrapper = new LambdaQueryWrapper<>();
        dictWrapper.eq(SysDict::getCode, code);
        SysDict sysDict = sysDictMapper.selectOne(dictWrapper);
        Assert.notNull(sysDict, "字典不存在: " + code);
        LambdaQueryWrapper<SysDictItem> dictItemWrapper = new LambdaQueryWrapper<>();
        dictItemWrapper.eq(SysDictItem::getDictId, sysDict.getId());
        return baseMapper.selectList(dictItemWrapper);
    }

}
