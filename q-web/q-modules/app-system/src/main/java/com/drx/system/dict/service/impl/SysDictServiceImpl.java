package com.drx.system.dict.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drx.db.entity.SysDict;
import com.drx.system.dict.service.SysDictService;
import com.drx.db.mapper.SysDictMapper;
import org.springframework.stereotype.Service;

@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService{
}
