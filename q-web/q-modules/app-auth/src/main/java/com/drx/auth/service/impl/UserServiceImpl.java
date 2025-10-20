package com.drx.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.drx.auth.dto.RegisterDTO;
import com.drx.auth.service.UserService;
import com.drx.core.tools.BcryptTool;
import com.drx.db.entity.SysUser;
import com.drx.db.mapper.SysUserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class UserServiceImpl implements UserService {


    private final SysUserMapper sysUserMapper;

    public UserServiceImpl(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

    @Override
    public boolean save(RegisterDTO dto) {
        LambdaQueryWrapper<SysUser> query = new LambdaQueryWrapper<>();
        query.eq(SysUser::getUsername, dto.getUsername());
        Long count = sysUserMapper.selectCount(query);
        Assert.isTrue(count.equals(0L), "用户名已存在");
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(dto, sysUser);
        sysUser.setPassword(BcryptTool.encrypt(dto.getPassword()));
        sysUser.setCreatedBy("register");
        int insert = sysUserMapper.insert(sysUser);
        return insert == 1;
    }
}
