package com.drx.system.dict.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drx.starter.entity.SysDict;
import com.drx.starter.entity.SysDictItem;
import com.drx.starter.mapper.SysDictItemMapper;
import com.drx.system.dict.service.SysDictItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author tom
 * @description 针对表【sys_dict_item(字典项表)】的数据库操作Service实现
 * @createDate 2025-09-06 10:43:26
 */
@Service
public class SysDictItemServiceImpl extends ServiceImpl<SysDictItemMapper, SysDictItem> implements SysDictItemService {


    private final BaseMapper<SysDict> sysDictMapper;

    public SysDictItemServiceImpl(BaseMapper<SysDict> sysDictMapper) {
        this.sysDictMapper = sysDictMapper;
    }

    @Override
    public List<SysDictItem> listByCode(String code) {
        LambdaQueryWrapper<SysDict> dictWrapper = new LambdaQueryWrapper<>();
        dictWrapper.eq(SysDict::getCode, code);
        SysDict sysDict = sysDictMapper.selectOne(dictWrapper);
        LambdaQueryWrapper<SysDictItem> dictItemWrapper = new LambdaQueryWrapper<>();
        dictItemWrapper.allEq(Map.of(SysDictItem::getDictId, sysDict.getId(), SysDictItem::getState, 1));
        return baseMapper.selectList(dictItemWrapper);
    }

    @Override
    public List<SysDictItem> allListByCode(String code) {
        LambdaQueryWrapper<SysDict> dictWrapper = new LambdaQueryWrapper<>();
        dictWrapper.eq(SysDict::getCode, code);
        SysDict sysDict = sysDictMapper.selectOne(dictWrapper);
        LambdaQueryWrapper<SysDictItem> dictItemWrapper = new LambdaQueryWrapper<>();
        dictItemWrapper.allEq(Map.of(SysDictItem::getDictId, sysDict.getId()));
        return baseMapper.selectList(dictItemWrapper);
    }

}
