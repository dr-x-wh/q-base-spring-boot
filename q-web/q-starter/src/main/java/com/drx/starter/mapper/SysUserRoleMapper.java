package com.drx.starter.mapper;

import com.drx.starter.entity.SysRole;
import com.drx.starter.entity.SysUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    List<SysRole> getAllByUserId(@Param("userId") Long userId);

}
