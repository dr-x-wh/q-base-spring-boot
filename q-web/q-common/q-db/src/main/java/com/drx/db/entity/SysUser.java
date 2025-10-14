package com.drx.db.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

/**
 *
 * @TableName sys_user
 */
@TableName(value = "sys_user")
@Data
public class SysUser {
    @TableId
    private UUID id;
    private String username;
    private String password;
    private String nickname;
    private String avatarUrl;
    private String gender;
    private String state;
    private Date updatedAt;
    private String updatedBy;
    private Date createdAt;
    private String createdBy;
}
