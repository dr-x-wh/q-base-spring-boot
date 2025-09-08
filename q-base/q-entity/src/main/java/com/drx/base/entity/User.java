package com.drx.base.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 *
 * @TableName sys_user
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String nickname;
    private String gender;
    private String role;
    private String state;
    private String avatarUrl;
    private Date updatedAt;
    private String updatedBy;
    private Date createdAt;
    private String createdBy;
}
