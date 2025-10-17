package com.drx.db.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class SysUser {

    private String id;
    private String username;
    private String password;
    private String nickname;
    private String avatarUrl;
    private String gender;
    private String state;
    private Timestamp updatedAt;
    private String updatedBy;
    private Timestamp createdAt;
    private String createdBy;

}
