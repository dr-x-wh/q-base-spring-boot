package com.drx.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.drx.auth.pojo.form.LoginForm;
import com.drx.starter.entity.SysUser;

public interface SysUserService extends IService<SysUser> {

    String login(LoginForm form);

    void logout();

}
