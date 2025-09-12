package com.drx.starter.mapper;

import com.drx.starter.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUser getOneById(@Param("id") Long id);

}
