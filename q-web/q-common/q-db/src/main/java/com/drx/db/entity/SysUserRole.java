package com.drx.db.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

/**
 *
 * @TableName sys_user_role
 */
@TableName(value = "sys_user_role")
@Data
public class SysUserRole {
    @TableId
    private UUID userId;
    @TableId
    private UUID roleId;
    private Date createdAt;
    private String createdBy;
}
