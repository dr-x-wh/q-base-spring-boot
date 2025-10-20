package com.drx.db.entity;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class SysUser {

    private UUID id;
    private String username;
    private String password;
    private String nickname;
    private String avatarUrl;
    private String gender;
    private String state;
    private LocalDate updatedAt;
    private String updatedBy;
    private LocalDate createdAt;
    private String createdBy;
}
