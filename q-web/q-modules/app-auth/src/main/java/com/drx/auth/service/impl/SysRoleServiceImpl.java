package com.drx.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drx.auth.service.SysRoleService;
import com.drx.db.entity.SysRole;
import com.drx.db.entity.SysUserRole;
import com.drx.db.mapper.SysRoleMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    private final BaseMapper<SysUserRole> sysUserRoleMapper;

    public SysRoleServiceImpl(BaseMapper<SysUserRole> sysUserRoleMapper) {
        this.sysUserRoleMapper = sysUserRoleMapper;
    }

    @Override
    public List<String> getByUserId(String userId) {
        return List.of();
    }
}
