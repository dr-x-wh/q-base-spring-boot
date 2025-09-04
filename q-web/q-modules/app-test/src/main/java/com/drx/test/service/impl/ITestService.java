package com.drx.test.service.impl;

import com.drx.base.entity.SysUser;
import com.drx.starter.mapper.SysUserMapper;
import com.drx.test.service.TestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ITestService implements TestService {


    private final SysUserMapper sysUserMapper;

    public ITestService(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

    @Override
    public List<SysUser> getSysUser() {
        return sysUserMapper.selectList(null);
    }
}
