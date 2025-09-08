package com.drx.system.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drx.starter.entity.SysUser;
import com.drx.starter.mapper.SysUserMapper;
import com.drx.starter.tools.BcryptTool;
import com.drx.system.user.pojo.dto.RegisterDTO;
import com.drx.system.user.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Override
    public void register(RegisterDTO dto) {
        LambdaQueryWrapper<SysUser> nameWra = new LambdaQueryWrapper<>();
        nameWra.eq(SysUser::getUsername, dto.getUsername());
        SysUser nonUser = baseMapper.selectOne(nameWra);
        Assert.isNull(nonUser, "用户名已存在");
        dto.setPassword(BcryptTool.encrypt(dto.getPassword()));
        SysUser user = new SysUser();
        BeanUtils.copyProperties(dto, user);
        user.setCreatedBy("register");
        int insert = baseMapper.insert(user);
        Assert.isTrue(insert == 1, "注册失败");
    }
}
