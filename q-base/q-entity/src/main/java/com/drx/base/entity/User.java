package com.drx.base.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 *
 * @TableName sys_user
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 性别
     */
    private String gender;
    /**
     * 用户权限
     */
    private String role;
    /**
     * 用户状态
     */
    private String state;
    /**
     * 头像URL
     */
    private String avatarUrl;
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