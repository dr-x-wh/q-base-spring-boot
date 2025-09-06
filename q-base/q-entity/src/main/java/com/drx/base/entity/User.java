package com.drx.base.entity;

import lombok.Data;

import java.io.Serializable;

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
     * 用户权限
     */
    private String role;
    /**
     * 用户状态
     */
    private String state;
}