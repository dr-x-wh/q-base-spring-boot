package com.drx.db.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class SysDictItem {

    private String id;
    private String dictId;
    private String code;
    private String name;
    private String description;
    private String isDefault;
    private Long sort;
    private String state;
    private Timestamp updatedAt;
    private String updatedBy;
    private Timestamp createdAt;
    private String createdBy;

}
