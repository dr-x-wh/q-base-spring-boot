package com.drx.system.user.service;

import com.drx.starter.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.drx.system.user.pojo.form.RegisterForm;

public interface SysUserService extends IService<SysUser> {

    void register(RegisterForm form);
    void cancel();

}
