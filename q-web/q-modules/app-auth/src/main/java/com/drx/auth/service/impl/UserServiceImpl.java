package com.drx.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drx.auth.service.UserService;
import com.drx.db.entity.SysUser;
import com.drx.db.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements UserService {
}
