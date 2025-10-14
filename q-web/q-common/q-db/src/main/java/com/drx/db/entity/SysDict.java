package com.drx.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * @TableName sys_dict
 */
@TableName(value ="sys_dict")
@Data
public class SysDict {
    private Object id;

    private String code;

    private String name;

    private String description;

    private Date updatedAt;

    private String updatedBy;

    private Date createdAt;

    private String createdBy;
}