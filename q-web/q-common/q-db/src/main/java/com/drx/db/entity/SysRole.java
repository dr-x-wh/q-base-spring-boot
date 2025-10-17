package com.drx.db.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class SysRole {

    private String id;
    private String code;
    private String name;
    private String description;
    private String state;
    private Timestamp updatedAt;
    private String updatedBy;
    private Timestamp createdAt;
    private String createdBy;

}
