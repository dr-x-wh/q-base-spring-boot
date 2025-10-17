package com.drx.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.drx.auth.dto.LoginDTO;
import com.drx.db.entity.SysRole;
import com.drx.db.entity.SysUser;

import java.util.List;

public interface SysRoleService extends IService<SysRole> {

    List<String> getByUserId(String userId);

}
