package com.drx.starter.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 权限表
 * @TableName sys_role
 */
@TableName(value ="sys_role")
@Data
public class SysRole {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 权限标识
     */
    private String code;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限名称
     */
    private String description;

    /**
     * 状态，1启用，0禁用
     */
    private String state;

    /**
     * 更新时间
     */
    private Date updatedAt;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 创建人
     */
    private String createdBy;
}