package com.drx.starter.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 用户表
 * @TableName sys_user
 */
@TableName(value ="sys_user")
@Data
public class SysUser implements Serializable {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 昵称
     */
    @TableField(value = "nickname")
    private String nickname;

    /**
     * 性别
     */
    @TableField(value = "gender")
    private String gender;

    /**
     * 用户权限
     */
    @TableField(value = "role")
    private String role;

    /**
     * 用户状态
     */
    @TableField(value = "state")
    private String state;

    /**
     * 头像URL
     */
    @TableField(value = "avatar_url")
    private String avatar_url;

    /**
     * 更新时间
     */
    @TableField(value = "updated_at")
    private LocalDateTime updated_at;

    /**
     * 更新人
     */
    @TableField(value = "updated_by")
    private String updated_by;

    /**
     * 创建时间
     */
    @TableField(value = "created_at")
    private LocalDateTime created_at;

    /**
     * 创建人
     */
    @TableField(value = "created_by")
    private String created_by;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}