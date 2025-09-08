package com.drx.system.user.service;

import com.drx.starter.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.drx.system.user.pojo.dto.RegisterDTO;

public interface SysUserService extends IService<SysUser> {

    void register(RegisterDTO dto);

}
