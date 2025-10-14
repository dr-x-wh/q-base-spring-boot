package com.drx.api.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class LoginUser {
    private String id;
    private String username;
    private String nickname;
    private String avatarUrl;
    private String gender;
    private Date updatedAt;
    private String updatedBy;
    private Date createdAt;
    private String createdBy;

    private List<String> roles;
}
