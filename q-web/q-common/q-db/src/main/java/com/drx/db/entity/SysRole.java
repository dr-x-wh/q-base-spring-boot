package com.drx.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * @TableName sys_role
 */
@TableName(value ="sys_role")
@Data
public class SysRole {
    private Object id;

    private String code;

    private String name;

    private String description;

    private String state;

    private Date updatedAt;

    private String updatedBy;

    private Date createdAt;

    private String createdBy;
}