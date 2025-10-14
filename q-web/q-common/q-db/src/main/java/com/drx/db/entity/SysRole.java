package com.drx.db.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

/**
 *
 * @TableName sys_role
 */
@TableName(value = "sys_role")
@Data
public class SysRole {
    @TableId
    private UUID id;
    private String code;
    private String name;
    private String description;
    private String state;
    private Date updatedAt;
    private String updatedBy;
    private Date createdAt;
    private String createdBy;
}
