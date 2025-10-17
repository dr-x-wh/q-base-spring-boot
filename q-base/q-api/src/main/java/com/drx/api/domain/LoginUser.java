package com.drx.api.domain;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
public class LoginUser {
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

    private List<String> roles;
}
