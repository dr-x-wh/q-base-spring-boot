package com.drx.system.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drx.base.entity.User;
import com.drx.base.enums.USER_ROLE;
import com.drx.base.tools.context.UserContext;
import com.drx.starter.entity.SysUser;
import com.drx.starter.mapper.SysUserMapper;
import com.drx.starter.mapper.SysUserRoleMapper;
import com.drx.starter.tools.BcryptTool;
import com.drx.system.user.pojo.form.RegisterForm;
import com.drx.system.user.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    private final SysUserRoleMapper sysUserRoleMapper;

    public SysUserServiceImpl(SysUserRoleMapper sysUserRoleMapper) {
        this.sysUserRoleMapper = sysUserRoleMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(RegisterForm form) {
        LambdaQueryWrapper<SysUser> nonUserQ = new LambdaQueryWrapper<>();
        nonUserQ.eq(SysUser::getUsername, form.getUsername());
        SysUser nonUser = baseMapper.selectOne(nonUserQ);
        Assert.isNull(nonUser, "用户名已存在");
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(form, sysUser);
        sysUser.setPassword(BcryptTool.encrypt(sysUser.getPassword()));
        sysUser.setCreatedBy("register");
        Long count = baseMapper.selectCount(null);
        if (count.equals(0L)) {
            baseMapper.insert(sysUser);
            sysUserRoleMapper.createByCode(sysUser.getId(), USER_ROLE.ADMINISTRATOR.getCode(), "register");
        } else {
            baseMapper.insert(sysUser);
            sysUserRoleMapper.createByCode(sysUser.getId(), USER_ROLE.USER.getCode(), "register");
        }
    }

    @Override
    public void cancel() {
        User user = UserContext.get();
        int i = baseMapper.deleteById(user.getId());
        Assert.isTrue(i != 0, "注销失败");
    }
}
