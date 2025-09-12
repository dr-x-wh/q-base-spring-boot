package com.drx.base.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class User {
    private Long id;
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
