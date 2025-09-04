package com.drx.base.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SysDict {
    private Long id;

    private String code;

    private String name;

    private String description;

    private Date updatedAt;

    private String updatedBy;

    private Date createdAt;

    private String createdBy;

}