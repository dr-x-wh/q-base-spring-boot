package com.drx.db.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class SysUserRole {

    private String userId;
    private String roleId;
    private Timestamp createdAt;
    private String createdBy;

}
