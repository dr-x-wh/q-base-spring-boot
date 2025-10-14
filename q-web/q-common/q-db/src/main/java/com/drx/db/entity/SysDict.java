package com.drx.db.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

/**
 *
 * @TableName sys_dict
 */
@TableName(value = "sys_dict")
@Data
public class SysDict {
    @TableId
    private UUID id;
    private String code;
    private String name;
    private String description;
    private Date updatedAt;
    private String updatedBy;
    private Date createdAt;
    private String createdBy;
}
