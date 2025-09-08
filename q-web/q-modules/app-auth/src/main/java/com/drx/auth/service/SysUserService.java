package com.drx.auth.service;

import com.drx.auth.pojo.dto.LoginDTO;
import com.drx.starter.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SysUserService extends IService<SysUser> {

    String login(LoginDTO dto);

}
