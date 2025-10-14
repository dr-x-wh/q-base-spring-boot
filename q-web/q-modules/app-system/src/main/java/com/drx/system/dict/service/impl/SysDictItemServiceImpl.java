package com.drx.system.dict.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drx.cache.repository.CacheClient;
import com.drx.db.entity.SysDict;
import com.drx.db.entity.SysDictItem;
import com.drx.system.dict.service.SysDictItemService;
import com.drx.db.mapper.SysDictItemMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

@Service
public class SysDictItemServiceImpl extends ServiceImpl<SysDictItemMapper, SysDictItem> implements SysDictItemService{

    private final BaseMapper<SysDict> sysDictMapper;
    private final CacheClient cacheClient;

    public SysDictItemServiceImpl(BaseMapper<SysDict> sysDictMapper, CacheClient cacheClient) {
        this.sysDictMapper = sysDictMapper;
        this.cacheClient = cacheClient;
    }

    @Override
    public List<SysDictItem> listByCode(String code) {
        LambdaQueryWrapper<SysDict> dictWrapper = new LambdaQueryWrapper<>();
        dictWrapper.eq(SysDict::getCode, code);
        SysDict sysDict = sysDictMapper.selectOne(dictWrapper);
        Assert.notNull(sysDict, "字典不存在: " + code);
        LambdaQueryWrapper<SysDictItem> dictItemWrapper = new LambdaQueryWrapper<>();
        dictItemWrapper.allEq(Map.of(SysDictItem::getDictId, sysDict.getId(), SysDictItem::getState, "1"));
        return baseMapper.selectList(dictItemWrapper);
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
