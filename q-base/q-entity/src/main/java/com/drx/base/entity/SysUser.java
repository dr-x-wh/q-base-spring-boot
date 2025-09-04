package com.drx.base.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SysUser {
    private Long id;

    private String username;

    private String password;

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
